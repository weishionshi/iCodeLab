package com.a3.demo;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.util.List; 
 
import org.eclipse.jdt.core.dom.AST; 
import org.eclipse.jdt.core.dom.ASTParser; 
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


public class DemoAbstractSyntaxTree {
    public static void main(String[] args) throws Exception { 
        String filePath= System.getProperty("user.dir")+"/src/test/java"+"/com/a3/test/CreateOrderAndPay.java";
        String content = read(filePath); //javaԴ�ļ�
        
//        Map<String, String> compilerOptions = JavaCore.getOptions();
//        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7); //����Java���԰汾
//        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
//        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
//        parser.setCompilerOptions(compilerOptions); //���ñ���ѡ��
        //����������  
        ASTParser parsert = ASTParser.newParser(AST.JLS3); 
        //�趨��������Դ�����ַ�  
        parsert.setSource(content.toCharArray()); 
        //ʹ�ý��������н���������AST�����Ľ��(CompilationUnitΪ���ڵ�)  
        CompilationUnit result = (CompilationUnit) parsert.createAST(null); 
   
        //��ȡ����  
        List types = result.types(); 
        //ȡ����������  
        TypeDeclaration typeDec = (TypeDeclaration) types.get(0); 
   
        //##############��ȡԴ����ṹ��Ϣ#################  
        //����import  
        List importList = result.imports(); 
        //ȡ�ð���  
        PackageDeclaration packetDec = result.getPackage(); 
        //ȡ������  
        String className = typeDec.getName().toString(); 
        //ȡ�ú���(Method)�����б�  
        MethodDeclaration methodDec[] = typeDec.getMethods(); 
        //ȡ�ú���(Field)�����б�  
        FieldDeclaration fieldDec[] = typeDec.getFields(); 
   
        //�������  
        System.out.println("----------��:"); 
        System.out.println(packetDec.getName()); 
        //�������import  
        System.out.println("----------����import:"); 
        for (Object obj : importList) { 
            ImportDeclaration importDec = (ImportDeclaration) obj; 
            System.out.println(importDec.getName()); 
        } 
        //�������  
        System.out.println("----------��:"); 
        System.out.println(className); 
        //ѭ�������������  
        System.out.println("========================"); 
        System.out.println("----------����:"); 
        for (MethodDeclaration method : methodDec) {
            for (Object modifiObj : method.modifiers()) {
                if(modifiObj instanceof NormalAnnotation){
                    System.out.println(((NormalAnnotation) modifiObj).getTypeName());
                }
            }

            System.out.println("modifier:\n"+Modifier.ModifierKeyword.fromFlagValue(method.getModifiers()));
            System.out.println("return type:\n"+method.getReturnType2()); 
            System.out.println("name:\n"+method.getName());
            System.out.println("body:"); 
            System.out.println(method.getBody()); 
            System.out.println("Javadoc:" + method.getJavadoc()); 
   
           
            System.out.println("============= method begin"); 
            System.out.println(method); 
            System.out.println("============= method end"); 
        } 
   
        //ѭ���������  
        System.out.println("----------����:"); 
        for (FieldDeclaration fieldDecEle : fieldDec) { 
            //public static  
            for (Object modifiObj : fieldDecEle.modifiers()) { 
                if(modifiObj instanceof Modifier){
                    Modifier modify = (Modifier) modifiObj; 
                    System.out.print(modify + "-"); 
                }
                
                if(modifiObj instanceof MarkerAnnotation){
                    MarkerAnnotation markerAnnotation =(MarkerAnnotation)modifiObj;
                    System.out.println(markerAnnotation);
                }

            } 
            System.out.println(fieldDecEle.getType()); 
            for (Object obj : fieldDecEle.fragments()) { 
                VariableDeclarationFragment frag = (VariableDeclarationFragment) obj; 
                System.out.println("[FIELD_NAME:]" + frag.getName()); 
            } 
        } 
    } 
   
    private static String read(String filename) throws IOException { 
        File file = new File(filename); 
        byte[] b = new byte[(int) file.length()]; 
        FileInputStream fis = new FileInputStream(file); 
        fis.read(b); 
        fis.close(); 
        return new String(b); 
   
    } 

}