package com.a3.ast;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST; 
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit; 
import org.eclipse.jdt.core.dom.FieldDeclaration; 
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration; 
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration; 
import org.eclipse.jdt.core.dom.TypeDeclaration; 
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

public class AbstractSyntaxTree {
    private static final Logger log = LoggerFactory.getLogger(AbstractSyntaxTree.class);
    private static final Boolean DEBUG=true;
    
    private static ASTParser parsert;
    private static CompilationUnit result;
    private static List<?> types;
    private static TypeDeclaration typeDec;
    
    //Դ���ļ���
    private String filePath;
    //className; e.g. com.a3.test.CreateOrderAndPay
    private String className;
    //simpleClaseName;e.g. CreateOrderAndPay
    private String simpleClaseName;
    //OpenActionTemplate����PollingOpenActionTemplate��Ķ���
    //e.g. [OpenActionTemplate|createOrderAndPay,OpenActionTemplate|cashierPayresultQuery]
    private List<String> openActionTemplateObjs;
    //��������������@Testע��ĺ�����
    //e.g. [createOrderAndPayFullAmount,createOrderAndPayPartialAmount]
    private List<String> testcases;
    //key=������(������)��value=������
    private Map<String,Block> methodBody;
    
    public AbstractSyntaxTree(String filePath){
        this.filePath=filePath;
        this.className=getClassNameFromFileName();
        this.simpleClaseName=getSimpleClassNameFromFileName();
    }
    
    public void build(){
        
        String content = read(filePath); //javaԴ�ļ�
        
//        Map<String, String> compilerOptions = JavaCore.getOptions();
//        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7); //����Java���԰汾
//        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
//        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
//        parser.setCompilerOptions(compilerOptions); //���ñ���ѡ��
        
        //����������  
        parsert = ASTParser.newParser(AST.JLS3); 
        //�趨��������Դ�����ַ�  
        parsert.setSource(content.toCharArray()); 
        //ʹ�ý��������н���������AST�����Ľ��(CompilationUnitΪ���ڵ�)  
        result = (CompilationUnit) parsert.createAST(null); 
        //��ȡ����  
        types = result.types(); 
        //ȡ����������  
        typeDec = (TypeDeclaration) types.get(0); 
        
        genTestcasesAndMethodBody();
        genOpenActionTemplateObj();
    }
    
    
    
    private static String read(String filename) { 
        File file = new File(filename); 
        byte[] b = new byte[(int) file.length()]; 
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            fis.read(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
 
        return new String(b); 
   
    } 
    
    /**
     * ��javaԴ���з�����testcase��key=������,value=������
     */
    private void genTestcasesAndMethodBody(){
        //ȡ�ú���(Method)�����б�  
        MethodDeclaration methodDec[] = typeDec.getMethods(); 
        List<String> list=new ArrayList<String>();
        Map<String,Block> methodBlock = new HashMap<String, Block>();
        //����@Test����������testcases
        for (MethodDeclaration method : methodDec) {
            
            for (Object modifiObj : method.modifiers()) {
                if(modifiObj instanceof NormalAnnotation && ((NormalAnnotation) modifiObj).getTypeName().toString().equalsIgnoreCase("test")){
                    
                    list.add(method.getName().toString());
                    methodBlock.put(method.getName().toString(), method.getBody());
                    log.debug(((NormalAnnotation) modifiObj).getTypeName()+list.toString());
                    break;
                }
            }

            if(DEBUG){
                log.info("modifier:\n"+Modifier.ModifierKeyword.fromFlagValue(method.getModifiers()));
                log.info("return type:\n"+method.getReturnType2()); 
                log.info("name:\n"+method.getName());
                log.info("body:\n"+method.getBody());       
            }
        } 
        setTestcases(list);
        setMethodBody(methodBlock);
    }
    
    /**
     * ����������OpenActionTemplate����PollingOpenActionTemplate��ʵ������
     * set��List<String> openActionTemplates������
     * e.g.
     * [OpenActionTemplate|createOrder,OpenActionTemplate|queryOrder]
     */
    private void genOpenActionTemplateObj(){
        Class<?> cls =null;
        List<String> oatList = new ArrayList<String>();
        try {
            cls = Class.forName(this.className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }                
        // ��ȡOpenActionTemplate����PollingOpenActionTemplate���ȫ������
        Field[] field = cls.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // Ȩ�����η� e.g. public/private/protected
            int modf = field[i].getModifiers();
            String priv = java.lang.reflect.Modifier.toString(modf);
            // ������������
            Class<?> type = field[i].getType();
            log.debug(priv + " " + type.getSimpleName() + " " + field[i].getName() + ";");
            if(type.getSimpleName().equals("OpenActionTemplate")||type.getSimpleName().equals("PollingOpenActionTemplate")){
                oatList.add(type.getSimpleName()+"|"+field[i].getName());
            }
        }
        setOpenActionTemplateObjs(oatList);
    }
    
    /**
     * �����ļ�·����ת�ɴ�package��ClassName
     * @return ClassName
     */
    private String getClassNameFromFileName(){
        return this.filePath.split("/src/test/java/")[1].replaceAll("\\/", ".").replace(".java", "");
    }
    /**
     * �����ļ�·����ת��simple ClassName
     * @return simple ClassName
     */
    private String getSimpleClassNameFromFileName(){
        String[] str = this.filePath.split("\\/");
        return str[str.length-1].replace(".java", "");
    }
    
    //getter and setter
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getOpenActionTemplateObjs() {
        return openActionTemplateObjs;
    }

    public void setOpenActionTemplateObjs(List<String> openActionTemplateObjs) {
        this.openActionTemplateObjs = openActionTemplateObjs;
    }

    public List<String> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<String> testcases) {
        this.testcases = testcases;
    }

    public Map getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(Map methodBody) {
        this.methodBody = methodBody;
    }
    
    
}