package org.webdriver.patatiumwebui.utils;

import java.io.*;

public class FileManger {

	/**
	 * 递归删除文件
	 * @param file
	 */
	private void deleteFile(File file)
	{
		if (file.isDirectory()) {
			File[] files=file.listFiles();
			//递归条用删除方法
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}
	/**
	 * 删除文件夹和密码
	 * @param workspaceRootPath 文件目录
	 */
	public  void clearFile(String workspaceRootPath)
	{
		File file=new File(workspaceRootPath);
		if (file.exists()) {
			deleteFile(file);
		}

	}

    /**
     *
     * @param path 写入文件目标路径
     * @param encode 写入文件编码
     * @param append 是否文本追加模式
     * @param content 写入文本
     */
	public void writeWithEncode(String path,String encode,boolean append,String content)
	{
		File file=new File(path);
		if (file.exists())
		{
			file.delete();
		}
		try {
			file.createNewFile();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file,append),encode));
            bufferedWriter.write(content);
            bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    /**
     *
     * @param file 写入文件文件
     * @param encode 写入文件编码
     * @param append 是否文本追加模式
     * @param content 写入文本
     */
    public void writeWithEncode(File file,String encode,boolean append,String content)
    {
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file,append),encode));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	public  void readFileToString(String path)
	{
		try {
			FileInputStream fileInputStream=new FileInputStream(new File(path));
			StringBuffer sb=new StringBuffer();
			byte[] b=new byte[1024];
			int startbyte=-1;
			while ((startbyte= fileInputStream.read(b)) != -1) {// 读取
				sb.append(b);
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 复制图片及其他文件
	 * @param sourceRelativePath 源文件相对路径
	 * @param targetRelativePath 目标文件相对路径
	 */
	public void copyFile(String sourceRelativePath,String targetRelativePath)
	{
		//读取流字节流
		FileInputStream fileInputStream=null;
		//写入流字节流
		OutputStream imageOutputStream=null;
		try {
			fileInputStream=new FileInputStream(new File(sourceRelativePath));
			imageOutputStream=new FileOutputStream(targetRelativePath);// 与复制到的目的关联起来，这里的图片的名称可以与原来的相同，也可以不一样
			byte[] b = new byte[1024];// 定义字节数组，并指定长度
			int startbyte = -1;
			while ((startbyte= fileInputStream.read(b)) != -1) {// 读取
				imageOutputStream.write(b, 0, startbyte);// 写入，读多少写入多少，所以用 write(b,0,len)
			}
			System.out.println("复制文件成功");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if (fileInputStream!=null) {

				try {
					fileInputStream.close();

				} catch (IOException e2) {
					// TODO: handle exception
					System.out.println("读取流关闭失败");
				}
			}
			if (imageOutputStream!=null) {

				try {
					imageOutputStream.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("输出流关闭失败");
				}

			}
		}

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}


}
