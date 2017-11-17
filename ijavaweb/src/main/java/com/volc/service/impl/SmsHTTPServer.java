package com.volc.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.volc.util.ReadFromFileUtil;
//import com.atoz.action.order.SendSMSAction;
//import com.atoz.util.SpringContextUtil;
public class SmsHTTPServer  extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final String FILEPATH = "/src/main/resources/response/createOrderAndPay.json";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  response.setContentType("text/html;charset=utf-8");
     request.setCharacterEncoding("utf-8");
     response.setCharacterEncoding("utf-8");
     PrintWriter out = response.getWriter();
      //String content = request.getParameter("content");
       //String content = new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8");  
//        String mobiles = request.getParameter("mobiles");
//        String businesscode = request.getParameter("businesscode");
//        String businesstype = request.getParameter("businesstype");
//        if (content == null || "".equals(content) || content.length() <= 0) {
//         System.out.println("http call failed,����content����Ϊ��,�����˳�");
//        } else if (mobiles == null || "".equals(mobiles)
//                || mobiles.length() <= 0) {
//         System.out.println("http call failed,����mobiles����Ϊ��,�����˳�");
//        } else {
         /*SendSMSServiceImpl send = new SendSMSServiceImpl();*/
//         SendSMSAction sendSms = (SendSMSAction) SpringContextUtil.getBean("sendSMS");
//         sendSms.sendSms(content, mobiles, businesscode, businesstype);
//        	out.println("This is HTTP response by POST method... ");
        	//response.setContentType("text/html");
//            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//            out.println("<HTML>");
//            out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//            out.println("  <BODY>");
//            out.print("    This is ");
//            out.print(this.getClass());
//            out.println(", using the GET method");
//            out.println("  </BODY>");
//            out.println("</HTML>");
     String resp = JSON.toJSONString(ReadFromFileUtil.getJsonFromFile(FILEPATH),true);
     out.println(resp);
            out.flush();
            out.close();
         System.out.println("---http call success---");
//        }
//        out.close();
    }
    
    // ���� POST ��������ķ���
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}