package cn.edu.ncut.generate;

import java.io.*;

/**
 * 生成类
 * @author lh
 *
 */
public class GenerateServiceClass {

	private static String[] MBOName = {"HKPerson","HKOrg","HKOrgPerson"};
	

	public static void main(String[] args) throws IOException {
		File mboFile = new File("src/templates/daoImpl.property");
		File mboSetFile = new File("src/templates/serviceImpl.property");
		
		for (String string : MBOName) {
			String mboStr = txt2String(mboFile).replaceAll("LXHCZ", string).replaceAll("LXHCZSet", string + "Set");
			String mboSetStr = txt2String(mboSetFile).replaceAll("LXHCZ", string).replaceAll("LXHCZSet", string + "Set");
		
			WriteStringToFile("src/"+string+".java",mboStr);
			WriteStringToFile("src/"+string+"Set.java",mboSetStr);
		}
		
		System.out.println("Generate Success!");
	}

	/*
	 * 往文件里写入字符串
	 */
	public static void WriteStringToFile(String filePath,String context) {
		try {
			File file = new File(filePath);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.println(context);// 往文件里写入字符串
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
