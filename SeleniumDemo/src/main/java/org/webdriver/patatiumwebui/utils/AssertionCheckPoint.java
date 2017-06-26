package org.webdriver.patatiumwebui.utils;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.testng.Assert;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 断言类，用于设置检查点的方法,支持自动截图，支持检查点标题设置
 * 种断言都用重载封装了两个方法，一个是带标题（message）参数，一个是不带message参数的根据需要选择
 *单个检查点，因为用try catch封装了，用于解决失败后继续检查的问题这样如果这个检查点失败了，testng并不会认为用例失败了，所以单个
 * 检查点并不算断言，如果你需要控制用例失败的话，需要在testng的方法最后的地方调用AssertionCheckPoint.VerityError(),这个方法会断言当前
 * 方法是否有失败的检查点，只要有一个失败，那么用例就会标记为失败
 */
public class AssertionCheckPoint extends TestBaseCase {
	//收集断言异常用于报表日志展示
	public static List<Error> errors=new ArrayList<Error>();
	//收集断言信息文本，用于报表展示
	public static List<String> assertInfolList=new ArrayList<String>();
	//收集信息文本用于报表展示
	public static List<String> messageList=new ArrayList<String>();
	//记录错误数量
	public static Integer errorIndex=0;
	private static Log log=new Log(AssertionCheckPoint.class);
	private static String formatDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
		return formatter.format(date).toString();
	}
	private static void snapshotInfo()
	{
		WebDriver driver=TestBaseCase.driver;
		ScreenShotUtil screenShot=new ScreenShotUtil(driver);
		//设置截图名字
		Date nowDate=new Date();
		screenShot.setscreenName(AssertionCheckPoint.formatDate(nowDate));
		screenShot.takeScreenshot();
		//添加截图地址信息
		AssertionCheckPoint.messageList.add("snapshot/"+ AssertionCheckPoint.formatDate(nowDate)+ ".jpg");
	}
	/**
	 * 验证actual实际值是否包含预期值exceptStr
	 * @param actual 实际值
	 * @param exceptStr 预期值
	 */
	public  static void verityCationString(String actual,String exceptStr)
	{
		String verityStr="Assert验证：{"+"实际值："+actual+","+"预期值："+exceptStr+"} 实际值是否包含预期值";
		Boolean flagBoolean=actual.contains(exceptStr);
		log.info(flagBoolean.toString());
		try {
			Assert.assertTrue(flagBoolean);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			errors.add(e);
			AssertFailedLog();
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
		}

	}
	/**
	 * 验证actual实际值是否包含预期值exceptStr
	 * @param actual  实际值
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 */
	public  static void verityCationString(String actual,String exceptStr,String Message)
	{
		String verityStr="Assert验证：{"+"实际值："+actual+","+"预期值："+exceptStr+"} 实际值是否包含预期值";
		Boolean flagBoolean=actual.contains(exceptStr);
		log.info(Message+":"+verityStr);
		try {
			Assert.assertTrue(flagBoolean);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
		}

	}
	/**
	 * 验证实际值actual与预期值exceptStr是否相等
	 * @param actual 实际值
	 * @param exceptStr 预期值
	 */
	public static void verityString(String actual,String exceptStr)
	{
		String verityStr="Assert验证：{"+"实际值："+actual+","+"预期值"+exceptStr+"} 实际值与预期值是否一致";
		log.info(verityStr);
		try {
			Assert.assertEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证实际值actual与预期值exceptStr是否相等
	 * @param actual 实际值
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 *  @author Administrator 郑树恒
	 */
	public static void verityString(String actual,String exceptStr,String Message)
	{
		String verityStr="Assert验证：{"+"实际值"+actual+","+"预期值"+exceptStr+"} 实际值与预期值是否一致";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();

		}
	}
	/**
	 * 验证实际值actual与预期值exceptStr是否不相等
	 * @param actual 实际值
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 *  @author Administrator 郑树恒
	 */
	public static void verityNotString(String actual,String exceptStr,String Message)
	{
		String verityStr="Assert验证：{"+"实际值"+actual+","+"预期值"+exceptStr+"} 实际值与预期值是否不相等";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertNotEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();

		}
	}
	/**
	 * 验证布尔值是否与预期一致
	 * @param actual 实际值
	 * @param except 预期值
	 * @param message 信息
	 */
	public static void verityBoolean(Boolean actual, Boolean except, String message) 
	{
		String verityStr="Assert验证：{"+"实际值："+actual+","+"预期值："+except+"} 实际值与预期值是否一致";
		log.info(message+":"+verityStr);
		try {
			Assert.assertEquals(actual, except);
			AssertPassLog();
			assertInfolList.add(message+verityStr+":pass");
			messageList.add(message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(message+verityStr+":failed");
			messageList.add(message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}


	/**
	 * 验证页面是否出现某文本exceptStr
	 * @param exceptStr 预期值
	 */
	public static  void verityTextPresent(String exceptStr)
	{
		String verityStr="【Assert验证】:"+"页面是否出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(verityStr);
		try {
			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
			log.info("定位信息："+exceptStr);
			driver.findElements(By.xpath(exceptStr));
			if (driver.findElements(By.xpath(exceptStr)).size()>0) {
				flag=true;
			}
			else {
				flag=false;
			}
		} catch (NoSuchElementException e) {
			flag=false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error f) {
		
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			f.printStackTrace();
		}


	}

	/**
	 * 验证页面是否出现某文本exceptStr
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 */
	public static  void verityTextPresent(String exceptStr,String Message)
	{
		String verityStr="【Assert验证】:"+"页面是否出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(Message+":"+verityStr);
		try {
			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
			System.out.println(exceptStr);
			List<WebElement> webElements= driver.findElements(By.xpath(exceptStr));
			if (webElements.size()>0) {
				flag=true;
			}
			else {
				flag=false;
			}
		} catch (NoSuchElementException e) {
			flag=false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			f.printStackTrace();
		}


	}

	/**
	 * 验证页面是否没有出现莫文本exceptStr
	 * @param exceptStr 预期值
	 */
	public static  void verityNotTextPresent(String exceptStr)
	{
		String verityStr="【Assert验证】:"+"页面是否没有出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(verityStr);
		try {
			exceptStr="//*[contains(.,'"+exceptStr+"')]";
			driver.findElement(By.xpath(exceptStr));
			flag=false;
		} catch (NoSuchElementException e) {
			flag=true;
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
			System.out.println(flag);
		} catch (Error f) {
			errorIndex++;
			AssertFailedLog();
			errors.add(f);
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			System.out.println(flag);
		}


	}

	/**
	 * 验证页面是否没有出现莫文本exceptStr
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 */
	public static  void verityNotTextPresent(String exceptStr,String Message)
	{
		String verityStr="【Assert验证】:"+"页面是否没有出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(Message+":"+verityStr);
		try {
			exceptStr="//*[contains(.,'"+exceptStr+"')]";
			driver.findElement(By.xpath(exceptStr));
			flag=false;
			System.out.println(flag);
		} catch (NoSuchElementException e) {
			flag=true;
			System.out.println(flag);
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			System.out.println(flag);
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			System.out.println(flag);
			AssertionCheckPoint.snapshotInfo();
		}


	}

	/**
	 * 验证页面是否出现某文本--精确匹配
	 * @param exceptStr 预期值 预期值
	 */
	public static  void verityTextPresentPrecision(String exceptStr)
	{
		String verityStr="【Assert验证】:"+"页面是否出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(verityStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			System.out.println(exceptStr);
			driver.findElement(By.xpath(exceptStr));
			flag=true;
		} catch (NoSuchElementException e) {
			flag=false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		System.out.println(false);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			f.printStackTrace();
		}


	}
	/**
	 * 验证页面是否出现某文本--精确匹配
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 */
	public static  void verityTextPresentPrecision(String exceptStr,String Message)
	{
		String verityStr="【Assert验证】:"+"页面是否出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(Message+":"+verityStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			WebElement webElement=driver.findElement(By.xpath(exceptStr));
			System.out.println(exceptStr);
			flag=true;
		} catch (NoSuchElementException e) {
		
			flag=false;
		}
		System.out.println(flag);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
            f.printStackTrace();
		}


	}

	/**
	 * 验证页面是否没有出现某文本--精确匹配
	 * @param exceptStr 预期值
	 */
	public static  void verityNotTextPresentPrecision(String exceptStr)
	{
		String verityStr="【Assert验证】:"+"页面是否没有出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(verityStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			System.out.println(exceptStr);
			driver.findElement(By.xpath(exceptStr));
			flag=false;
		} catch (NoSuchElementException e) {
			flag=true;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		System.out.println(false);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			f.printStackTrace();
		}

	}

	/**
	 * 验证是页面否没有出现某文本---精确匹配
	 * @param exceptStr 预期值
	 * @param Message 验证中文描述
	 */
	public static  void verityNotTextPresentPrecision(String exceptStr,String Message)
	{
		String verityStr="【Assert验证】:"+"页面是否没有出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
		Boolean flag=false;
		log.info(Message+":"+verityStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			System.out.println(exceptStr);
			driver.findElement(By.xpath(exceptStr));
			flag=false;
		} catch (NoSuchElementException e) {
		
			flag=true;
		}
		System.out.println(flag);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			f.printStackTrace();
		}


	}

	/**
	 * 验证浏览器标题是否与预期值exceptTitle相同
	 * @param exceptTitle 预期标题
	 */
	public static void verityTitle(String exceptTitle)
	{

		String title=driver.getTitle();
		String verityStr="Assert验证:页面title是否与预期值一致{"+"实际网页标题："+title+","+"预期网页标题："+exceptTitle+"}";
		log.info(verityStr);
		try {
			Assert.assertEquals(title, exceptTitle);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}


	/**
	 * 验证浏览器标题是否与预期值exceptTitle相同
	 * @param exceptTitle 预期标题
	 * @param Message 验证中文描述
	 */
	public static void verityTitle(String exceptTitle,String Message)
	{

		String title=driver.getTitle();
		String verityStr="Assert验证:页面title是否与预期值一致{"+"实际网页标题："+title+","+"预期网页标题："+exceptTitle+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(title, exceptTitle);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}

	/**
	 * 验证某元素文本值是否与预期值exceptText一样
	 * @param locator  元素定位信息
	 * @param exceptText 预期文本值
	 */
	public static void verityText(Locator locator,String exceptText)
	{
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String text=webElement.getText();
		String verityStr="Assert验证：某文本值是否与预期值一致{"+"实际值："+text+","+"预期值："+exceptText+"}";
		log.info(verityStr);
		try {
			Assert.assertEquals(text, exceptText);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证某元素文本值是否与预期值exceptText一样
	 * @param locator  元素定位信息
	 * @param exceptText 预期文本值
	 * @param Message 验证中文描述
	 */
	public static void verityText(Locator locator,String exceptText,String Message)
	{
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String text=webElement.getText();
		String verityStr="Assert验证：某文本值是否与预期值一致{"+"实际值："+text+","+"预期值:"+exceptText+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(text, exceptText);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证某元素某个属性值与预期值exceptAttributeValue 一样
	 * @param locator 元素定位信息
	 * @param AttributeName 元素属性名
	 * @param exceptAttributeValue 预期值
	 */
	public static void verityAttribute(Locator locator,String AttributeName,String exceptAttributeValue)
	{
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String attribute=webElement.getAttribute(AttributeName);
		String verityStr="Assert验证：某属性值是否与预期值一致{"+"实际属性值："+attribute+","+"预期属性值："+exceptAttributeValue+"}";
		try {
			Assert.assertEquals(attribute, exceptAttributeValue);
			log.info(verityStr);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}

	/**
	 * 验证某元素某个属性值与预期值exceptAttributeValue 一样
	 * @param locator 元素定位信息
	 * @param AttributeName 元素属性名
	 * @param exceptAttributeValue 预期值
	 * @param Message 验证中文描述
	 */
	public static void verityAttribute(Locator locator,String AttributeName,String exceptAttributeValue,String Message)
	{
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String attribute=webElement.getAttribute(AttributeName);
		String verityStr="Assert验证：某属性值是否与预期值一致{"+"实际属性值："+attribute+","+"预期属性值："+exceptAttributeValue+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(attribute, exceptAttributeValue);
			log.info(verityStr);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证某输入框是否不可编辑
	 * @param locator 元素定位信息
	 */
	public static void VertityNoEdit(Locator locator)
	{
		Boolean  status=false;
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String value1=webElement.getAttribute("disabled");
		String value2=webElement.getAttribute("readOnly");
		String verityStr="【Assert验证】:文本框是否不可编辑{"+"实际值："+status.toString()+"，"+"预期值：false"+"}";
		if(value1.equals("true"))
		{
			status=true;
		}
		else if(value2.equals("true"))
		{
			status=true;
		}
		else {
			status=false;
		}
		log.info(verityStr);
		try {
			Assert.assertTrue(status);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");

		} catch (Error e) {
		
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}

	}

	/**
	 * 验证某输入框是否不可编辑
	 * @param locator 元素定位信息
	 * @param Message 验证中文描述
	 */
	public static void VertityNoEdit(Locator locator,String Message)
	{
		Boolean  status=false;
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String value1=webElement.getAttribute("disabled");
		String value2=webElement.getAttribute("readOnly");
		if(value1.equals("true"))
		{
			status=true;
		}
		else if(value2.equals("true"))
		{
			status=true;
		}
		else {
			status=false;
		}
		String verityStr="【Assert验证】:文本框是否不可编辑{"+"实际值："+status.toString()+"，"+"预期值：false"+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertTrue(status);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}

	}
	/**
	 * 验证某元素可编辑
	 * @param locator 元素定位信息
	 */
	public static void VertityEdit(Locator locator)
	{
		Boolean  status=false;
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String value1=webElement.getAttribute("disabled");
		String value2=webElement.getAttribute("readOnly");
		String verityStr="【Assert验证】:文本框是否可编辑{"+"实际值："+status.toString()+"，"+"预期值：true"+"}";
		if(value1.equals("false"))
		{
			status=true;
		}
		else if(value2.equals("false"))
		{
			status=true;
		} else
		{
			status=false;
		}
		//AssertLog("true", status.toString());
		log.info(verityStr);
		try {
			Assert.assertTrue(status);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证某元素可编辑
	 * @param locator 元素定位信息
	 * @param Message 验证中文描述
	 */
	public static void VertityEdit(Locator locator,String Message)
	{
		Boolean  status=false;
		ElementAction action=new ElementAction();
		WebElement webElement=action.findElement(locator);
		String value1=webElement.getAttribute("disabled");
		String value2=webElement.getAttribute("readOnly");
		String verityStr="【Assert验证】:文本框是否可编辑{"+"实际值："+status.toString()+"，"+"预期值：true"+"}";
		if(value1.equals("false"))
		{
			status=true;
		}
		else if(value2.equals("false"))
		{
			status=true;
		} else
		{
			status=false;
		}
		log.info(Message+":"+verityStr);
		try {
			Assert.assertTrue(status);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");

		} catch (Error e) {
		
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	/**
	 * 验证alert对话框提示信息是否与预期值一致
	 * @param expectAlertText alert 提示框预期信息
	 */
	public static void verityAlertText(String expectAlertText)
	{
		Alert alert=driver.switchTo().alert();
		String  alertText=alert.getText();
		String verityStr="【Assert验证】:弹出的对话框的文本内容是否一致{"+alertText+","+expectAlertText+"}";
		log.info("【Assert验证】:弹出的对话框的文本内容是否一致{"+"实际值："+alertText+","+"预期值"+expectAlertText+"}");
		try {
			Assert.assertEquals(alertText, expectAlertText);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}

	}

	/**
	 * 验证alert对话框提示信息是否与预期值一致
	 * @param expectAlertText alert 提示框预期信息
	 * @param Message 验证中文描述
	 */
	public static void verityAlertText(String expectAlertText,String Message)
	{
		Alert alert=driver.switchTo().alert();
		String  alertText=alert.getText();
		String verityStr="【Assert验证】:弹出的对话框的文本内容是否一致{"+"实际值："+alertText+","+"预期值："+expectAlertText+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(alertText, expectAlertText);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}

	}
	/**
	 * 验证当前URL是否与预期Url一致
	 * @param expectURL 预期URL
	 */
	public static void verityURL(String expectURL)
	{
		String url=driver.getCurrentUrl();
		String verityStr="【Assert验证】:URL是否与预期的一致{"+"实际值："+url+","+"预期值："+expectURL+"}";
		log.info(verityStr);
		try {
			Assert.assertEquals(url, expectURL);
			AssertPassLog();
			assertInfolList.add(verityStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(verityStr+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}

	/**
	 * 验证当前URL是否与预期Url一致
	 * @param expectURL 预期URL
	 * @param Message 验证中文描述
	
	 */
	public static void verityURL(String expectURL,String Message)
	{
		String url=driver.getCurrentUrl();
		String verityStr="【Assert验证】:URL是否与预期的一致{"+"实际值："+url+","+"预期值："+expectURL+"}";
		log.info(Message+":"+verityStr);
		try {
			Assert.assertEquals(url, expectURL);
			AssertPassLog();
			assertInfolList.add(Message+verityStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errors.add(e);
			errorIndex++;
			assertInfolList.add(Message+verityStr+":failed");
			messageList.add(Message+":failed");
			AssertionCheckPoint.snapshotInfo();
			e.printStackTrace();
		}
	}
	//断言成功日志内容
	private static  void AssertPassLog()
	{
		log.info("【Assert验证  pass!】");
	}
	//断言失败日志内容
	private static  void AssertFailedLog()
	{
		log.error("【Assert验证  failed!】");
	}
	//断言日志内容
	private static void AssertLog(String str1,String str2)
	{
		log.info("【Assert验证】:"+"判断[比较]"+"{"+str1+","+str2+"}"+"是否一致[相等]");
	}
	/*判断用例是否含有验证失败的断言，如果有此方法会抛出异常，让testng监听器检测到，
	如果没有不会抛出异常，testng监听器会认为用例成功*/
	public static  void verityError()
	{
		Assert.assertEquals(errors.size(), 0);
		//有找不到元素的异常也认为用例失败
		Assert.assertEquals(ElementAction.noSuchElementExceptions.size(), 0);
	}
	public static void main(String[] args) {
		AssertionCheckPoint.verityCationString("刘兰39", "刘兰");
		AssertionCheckPoint.verityCationString("刘兰39", "刘兰", "验证是否按医生姓名查询出结果");
	}


}
class ScreenShotUtil
{
	public WebDriver driver;
	private String screenName;
	Log log =new Log(this.getClass());
	public void setscreenName(String screenName)
	{
		this.screenName=screenName;
	}
	public ScreenShotUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	private void takeScreenshot(String screenPath) {
		File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(scrFile, new File(screenPath));
			log.error("错误截图："+screenPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void takeScreenshot() {
		String screenName =this.screenName+ ".jpg";
		File dir = new File("test-output\\snapshot");
		if (!dir.exists())
		{dir.mkdirs();}
		String screenPath = dir.getAbsolutePath() + "\\" + screenName;
		this.takeScreenshot(screenPath);
	}
}
