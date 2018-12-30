package water.ustc.interceptor;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * 
 * e3:拦截器类，dom4j方式对xml文件的读取与写入 参考：https://blog.csdn.net/chianz632/article/details/80358073
 * @author asus
 *
 */
public class LogInterceptor {
	
	private SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
	
	//日志文件log.xml的路径,验证发现xml文件输出到桌面，但遇到问题：创建不同实例导致preAction函数没被调用，xml中至于结束时间和返回值，开始时间和action名字为空,暂时使用静态变量解决
	private String path="./log.xml";
	private static String preDate="";//访问开始时间
	private static String afterDate="";//访问结束时间
	private static String actionName="";//action名字
	private static String actionResult="";//返回结果
	
	//记录请求的action名字、访问开始时间
	public void preAction(String str) {
		
		//TODO 记录请求的action名字
		actionName=str;
		System.out.println("UseSC中preActioin action名字："+actionName);
		preDate = sdfs.format(System.currentTimeMillis()) ;//访问开始时间
	}
	
	//访问结束时间、请求返回结果,并将信息追加到日志文件log.xml
	public void afterAction(String str) {
		
		//TODO 接受返回的结果
		actionResult=str;
		System.out.println("UseSC中afterAction actionResult："+actionResult);
		afterDate = sdfs.format(System.currentTimeMillis()) ;//访问结束时间
		try {
			writeLog();//创建或向日志文件log.xml中添加action节点
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//写日志log.xml文件
	private void writeLog() throws Exception {
		File file=new File(path);//读取日志文件
		Document document=null;//xml的Document对象
		Element elementLog=null;//根元素
		Element elementAction=null;//二级节点actoin
		//如果文件存在，获得根节点
		if(file.exists()) {
			SAXReader reader=new SAXReader();
			document=reader.read(path);
			elementLog=document.getRootElement();//获取根节点
			System.out.println("找到了log.xml！！");
			//elementAction=elementLog.element("action");
			
		}else {//文件不存在时，创建文件并添加根元素，此时创建的文件xml在桌面
			document=DocumentHelper.createDocument();//创建xml的Document对象
			elementLog=document.addElement("log");//添加根元素
			//elementAction=elementLog.addElement("action");//添加二级元素action
		}
		//根节点log下添加action子节点
		elementAction=elementLog.addElement("action");//添加二级元素action
		Element elementName=elementAction.addElement("name");//添加name节点
		elementName.addText(actionName);//为name节点添加文本
		Element elementStartTime=elementAction.addElement("s-time");//添加访问开始时间节点
		elementStartTime.addText(preDate);
		Element elementEndTime=elementAction.addElement("e-time");//添加访问结束时间节点
		elementEndTime.addText(afterDate);
		Element elementActionName=elementAction.addElement("result");//添加返回结果节点
		elementActionName.addText(actionResult);
		//创建一种输出格式，每个节点元素可自动换行
		OutputFormat outputFormat=OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("UTF-8");
		XMLWriter xmlWrite=new XMLWriter(new FileWriter(file),outputFormat);//写入xml文件的位置及格式
		xmlWrite.write(document);//将document写入
		xmlWrite.close();
		System.out.println("写入日志文件log.xml成功！！");
	}
}
