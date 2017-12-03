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
    
    //源码文件名
    private String filePath;
    //className; e.g. com.a3.test.CreateOrderAndPay
    private String className;
    //simpleClaseName;e.g. CreateOrderAndPay
    private String simpleClaseName;
    //OpenActionTemplate或者PollingOpenActionTemplate类的对象
    //e.g. [OpenActionTemplate|createOrderAndPay,OpenActionTemplate|cashierPayresultQuery]
    private List<String> openActionTemplateObjs;
    //用例名，即带有@Test注解的函数名
    //e.g. [createOrderAndPayFullAmount,createOrderAndPayPartialAmount]
    private List<String> testcases;
    //key=用例名(函数名)，value=函数体
    private Map<String,Block> methodBody;
    
    public AbstractSyntaxTree(String filePath){
        this.filePath=filePath;
        this.className=getClassNameFromFileName();
        this.simpleClaseName=getSimpleClassNameFromFileName();
    }
    
    public void build(){
        
        String content = read(filePath); //java源文件
        
//        Map<String, String> compilerOptions = JavaCore.getOptions();
//        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7); //设置Java语言版本
//        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
//        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
//        parser.setCompilerOptions(compilerOptions); //设置编译选项
        
        //创建解析器  
        parsert = ASTParser.newParser(AST.JLS3); 
        //设定解析器的源代码字符  
        parsert.setSource(content.toCharArray()); 
        //使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)  
        result = (CompilationUnit) parsert.createAST(null); 
        //获取类型  
        types = result.types(); 
        //取得类型声明  
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
     * 从java源码中分析出testcase，key=方法名,value=方法体
     */
    private void genTestcasesAndMethodBody(){
        //取得函数(Method)声明列表  
        MethodDeclaration methodDec[] = typeDec.getMethods(); 
        List<String> list=new ArrayList<String>();
        Map<String,Block> methodBlock = new HashMap<String, Block>();
        //根据@Test分析出所有testcases
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
     * 分析出所有OpenActionTemplate或者PollingOpenActionTemplate的实例对象，
     * set到List<String> openActionTemplates数组中
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
        // 获取OpenActionTemplate或者PollingOpenActionTemplate类的全部对象
        Field[] field = cls.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符 e.g. public/private/protected
            int modf = field[i].getModifiers();
            String priv = java.lang.reflect.Modifier.toString(modf);
            // 属性所属的类
            Class<?> type = field[i].getType();
            log.debug(priv + " " + type.getSimpleName() + " " + field[i].getName() + ";");
            if(type.getSimpleName().equals("OpenActionTemplate")||type.getSimpleName().equals("PollingOpenActionTemplate")){
                oatList.add(type.getSimpleName()+"|"+field[i].getName());
            }
        }
        setOpenActionTemplateObjs(oatList);
    }
    
    /**
     * 根据文件路径，转成带package的ClassName
     * @return ClassName
     */
    private String getClassNameFromFileName(){
        return this.filePath.split("/src/test/java/")[1].replaceAll("\\/", ".").replace(".java", "");
    }
    /**
     * 根据文件路径，转成simple ClassName
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