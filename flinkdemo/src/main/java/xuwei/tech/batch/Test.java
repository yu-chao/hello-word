package xuwei.tech.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import scala.sys.process.ProcessBuilderImpl.FileInput;

public class Test {
public static void main(String[] args) throws IOException {
	Properties properties = System.getProperties();
	Enumeration<Object> keys = properties.keys();
	
	while(keys.hasMoreElements()){
		Object nextElement = keys.nextElement();
		System.out.println(nextElement.toString()+"  "+properties.getProperty(nextElement.toString()));
	}
	/*
	File file = new File("C://work-space\\flinkdemo\\src\\test\\resources\\aa.txt");
	
	FileInputStream fileInputStream = new FileInputStream("C://work-space\\flinkdemo\\src\\test\\resources\\test.txt");
	
	byte[] b=new byte[10];
	int read = fileInputStream.read(b);
	
	FileOutputStream cc=new FileOutputStream(file);
	cc.write(b);
	cc.close();
	
	System.out.println(new String(b,"utf-8"));*/
	  //指定编码格式 utf-8
    
    //指定gbk编码格式
    OutputStreamWriter osw2=new OutputStreamWriter(new FileOutputStream("C://work-space\\flinkdemo\\src\\test\\resources\\GBK.txt"), "GBK");
  
	//String encode = URLEncoder.encode("神奇的我","ISO-8859-1");
    byte[] bytes = "神奇的我".getBytes("GBK");
	String encode = new String(bytes,"GBK");
//    osw2.write(encode);
    osw2.write(encode);
    osw2.close();

	
	System.out.println(new String(encode.getBytes("ISO-8859-1"),"utf-8"));
}
}
