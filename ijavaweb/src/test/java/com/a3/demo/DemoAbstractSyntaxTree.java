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
        String content = read(filePath); //java源文件
        
//        Map<String, String> compilerOptions = JavaCore.getOptions();
//        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7); //设置Java语言版本
//        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
//        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
//        parser.setCompilerOptions(compilerOptions); //设置编译选项
        //创建解析器  
        ASTParser parsert = ASTParser.newParser(AST.JLS3); 
        //设定解析器的源代码字符  
        parsert.setSource(content.toCharArray()); 
        //使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)  
        CompilationUnit result = (CompilationUnit) parsert.createAST(null); 
   
        //获取类型  
        List types = result.types(); 
        //取得类型声明  
        TypeDeclaration typeDec = (TypeDeclaration) types.get(0); 
   
        //##############获取源代码结构信息#################  
        //引用import  
        List importList = result.imports(); 
        //取得包名  
        PackageDeclaration packetDec = result.getPackage(); 
        //取得类名  
        String className = typeDec.getName().toString(); 
        //取得函数(Method)声明列表  
        MethodDeclaration methodDec[] = typeDec.getMethods(); 
        //取得函数(Field)声明列表  
        FieldDeclaration fieldDec[] = typeDec.getFields(); 
   
        //输出包名  
        System.out.println("----------包:"); 
        System.out.println(packetDec.getName()); 
        //输出引用import  
        System.out.println("----------引用import:"); 
        for (Object obj : importList) { 
            ImportDeclaration importDec = (ImportDeclaration) obj; 
            System.out.println(importDec.getName()); 
        } 
        //输出类名  
        System.out.println("----------类:"); 
        System.out.println(className); 
        //循环输出函数名称  
        System.out.println("========================"); 
        System.out.println("----------函数:"); 
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
   
        //循环输出变量  
        System.out.println("----------变量:"); 
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