package com.a3.j2j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Block;

import com.a3.jmeter.element.HttpRequest;
import com.a3.jmeter.element.Jmx;
import com.a3.jmeter.interfac.JmeterElement;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.integration.test.action.template.OpenActionTemplate;

public class Java2Jmeter {

    private static final Boolean DEBUG=true;
    private static final Logger log = LoggerFactory.getLogger(Java2Jmeter.class);
	private static final String basePath= System.getProperty("user.dir")+"/src/test/java";//TBD-������
	//className:package.class,e.g. com.a3.test.CreateOrderAndPay
	private String className;
	private String classSimpleName;
	private Map<String,Jmx> jmxMap =new HashMap<String,Jmx>();
	private ArrayList<HttpRequest> hrList = new ArrayList();
	//����JmeterElement���������г��ֵ��Ⱥ�˳������
	private ArrayList<JmeterElement> jmeterElements= new ArrayList();

	public Java2Jmeter(String className){
	    this.className = className;
	    this.classSimpleName = parseClassSimpleName();
	}
	

	
	
	public void transfer(){
	    
	    //����className���ļ�·������ȡԴ���Ӧ��ASTʵ��
	    AbstractSyntaxTree ast = new AbstractSyntaxTree(getClassFilePath());
	    ast.build();
/*	    
        //��ȡtestcases
	    List<String> testcases = ast.getTestcases();
	    //��ȡmethodBody
	    Map<String,Block> methodBody=ast.getMethodBody();
	    //Ϊÿһ��testcase����һ��jmxʵ��
*/	    
	    //��ʼ��Jmx,������generate����
	    initJmx(ast);
	    


	}
	

	
	/**
	 * ��java�������OpenActionTemplate����PollingOpenActionTemplate���ȫ�����󶼴�����Ӧ��httpRequestElement����
	 * @param httpRequestElement
	 */
	private void initHttpRequestElement(String[] httpRequestElement){
	    Class<?> clzHttpRequest = null;
	    
	    try {
            clzHttpRequest = Class.forName("com.a3.j2j.HttpRequest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	    Constructor<?> cons[] = clzHttpRequest.getConstructors();
	    for(int i=0;i<httpRequestElement.length;i++){
    	    try {
    	        String filePath= "/acquire/order/alipayplus.acquiring.order.createOrderAndPay.xml";//TBD-������,���ݶ��������Զ���ȡxml����
                HttpRequest httpRequest= (HttpRequest)cons[0].newInstance(httpRequestElement[i], filePath);
                hrList.add(httpRequest);
            } catch (InstantiationException e) {

                e.printStackTrace();
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            } catch (IllegalArgumentException e) {

                e.printStackTrace();
            } catch (InvocationTargetException e) {

                e.printStackTrace();
            }
	    }

	}
	
	/**
	 * Ϊÿһ��testcase������Ӧ��jmx�������󣬶�����ΪclassSimpleName_testcaseName
	 * ���ҵ���generate����
	 * @param list
	 */
	private void initJmx(AbstractSyntaxTree ast){
	    //��ȡtestcases
        List<String> tcList = ast.getTestcases();
        //��ȡmethodBody
        Map<String,Block> methodBody=ast.getMethodBody();
        //Ϊÿһ��testcase����һ��jmxʵ��
	    Class<?> clzJmx = null;
        
        try {
            clzJmx = Class.forName("com.a3.jmeter.element.Jmx");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?> cons[] = clzJmx.getConstructors();
        for(int i=0;i<tcList.size();i++){
            try {
                Jmx jmx= (Jmx)cons[0].newInstance(ast,this.classSimpleName+"_"+tcList.get(i),methodBody.get(tcList.get(i)));
                jmx.generate();
                jmxMap.put(tcList.get(i),jmx);
            } catch (InstantiationException e) {

                e.printStackTrace();
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            } catch (IllegalArgumentException e) {

                e.printStackTrace();
            } catch (InvocationTargetException e) {

                e.printStackTrace();
            }
        }
	}
/*	
	private void analyzeByLine(){
	    String filePath = basePath+"/"+className.replaceAll("\\.", "/")+".java";
	    //���ж�ȡjava����

        StringBuffer str=new StringBuffer("");

        File file=new File(filePath);
        FileInputStream is =null;
        BufferedReader in =null;

        try{
            is=new FileInputStream(file);
            InputStreamReader isr= new InputStreamReader(is);
            in= new BufferedReader(isr);
            String line=null;

            while( (line=in.readLine())!=null ){
                
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
*/	
	
	private void outputJmx(){
	    for(HttpRequest hr:hrList){
	        System.out.println(hr.getElementXml());
	    }
	}
	
	private String getClassFilePath(){
	    String path = basePath+"/"+this.className.replaceAll("\\.", "/")+".java";
	    return path;
	}
	
	private String parseClassSimpleName(){
	    String[] string = this.className.split("\\.");
	    return string[string.length-1];
	}
	
	//getter and setter
	   public String getClassName() {
	        return className;
	    }
	    public void setClassName(String className) {
	        this.className = className;
	    }




        public String getClassSimpleName() {
            return classSimpleName;
        }




        public void setClassSimpleName(String classSimpleName) {
            this.classSimpleName = classSimpleName;
        }
	
}