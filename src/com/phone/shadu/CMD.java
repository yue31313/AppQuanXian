package com.phone.shadu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ִ��cmd�����ȡ�ļ���Ϣ
 * @author jack
 */
   public class CMD {

	   /**
	    * ִ��cmd�����ȡϵͳ�ļ���Ϣ
	    * @param cmd
	    * @param workdirectory
	    * @return
	    * @throws IOException
	    */
	   	public synchronized String run(String[] cmd, String workdirectory)
	   			throws IOException {
	   		String result = "";
	   		try {
	   			ProcessBuilder builder = new ProcessBuilder(cmd);
	   			// set working directory
	   			if (workdirectory != null)
	   				builder.directory(new File(workdirectory));
	   			builder.redirectErrorStream(true);
	   			Process process = builder.start();
	   			InputStream in = process.getInputStream();
	   			byte[] re = new byte[1024];
	   			while (in.read(re) != -1) {
	   				System.out.println(new String(re));
	   				result = result + new String(re);
	   			}
	   			in.close();

	   		} catch (Exception ex) {
	   			ex.printStackTrace();
	   		}
	   		return result;
	   	}
}
