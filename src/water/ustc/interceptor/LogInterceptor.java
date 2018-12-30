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
 * e3:�������࣬dom4j��ʽ��xml�ļ��Ķ�ȡ��д�� �ο���https://blog.csdn.net/chianz632/article/details/80358073
 * @author asus
 *
 */
public class LogInterceptor {
	
	private SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//ʱ���ʽ
	
	//��־�ļ�log.xml��·��,��֤����xml�ļ���������棬���������⣺������ͬʵ������preAction����û�����ã�xml�����ڽ���ʱ��ͷ���ֵ����ʼʱ���action����Ϊ��,��ʱʹ�þ�̬�������
	private String path="./log.xml";
	private static String preDate="";//���ʿ�ʼʱ��
	private static String afterDate="";//���ʽ���ʱ��
	private static String actionName="";//action����
	private static String actionResult="";//���ؽ��
	
	//��¼�����action���֡����ʿ�ʼʱ��
	public void preAction(String str) {
		
		//TODO ��¼�����action����
		actionName=str;
		System.out.println("UseSC��preActioin action���֣�"+actionName);
		preDate = sdfs.format(System.currentTimeMillis()) ;//���ʿ�ʼʱ��
	}
	
	//���ʽ���ʱ�䡢���󷵻ؽ��,������Ϣ׷�ӵ���־�ļ�log.xml
	public void afterAction(String str) {
		
		//TODO ���ܷ��صĽ��
		actionResult=str;
		System.out.println("UseSC��afterAction actionResult��"+actionResult);
		afterDate = sdfs.format(System.currentTimeMillis()) ;//���ʽ���ʱ��
		try {
			writeLog();//����������־�ļ�log.xml�����action�ڵ�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//д��־log.xml�ļ�
	private void writeLog() throws Exception {
		File file=new File(path);//��ȡ��־�ļ�
		Document document=null;//xml��Document����
		Element elementLog=null;//��Ԫ��
		Element elementAction=null;//�����ڵ�actoin
		//����ļ����ڣ���ø��ڵ�
		if(file.exists()) {
			SAXReader reader=new SAXReader();
			document=reader.read(path);
			elementLog=document.getRootElement();//��ȡ���ڵ�
			System.out.println("�ҵ���log.xml����");
			//elementAction=elementLog.element("action");
			
		}else {//�ļ�������ʱ�������ļ�����Ӹ�Ԫ�أ���ʱ�������ļ�xml������
			document=DocumentHelper.createDocument();//����xml��Document����
			elementLog=document.addElement("log");//��Ӹ�Ԫ��
			//elementAction=elementLog.addElement("action");//��Ӷ���Ԫ��action
		}
		//���ڵ�log�����action�ӽڵ�
		elementAction=elementLog.addElement("action");//��Ӷ���Ԫ��action
		Element elementName=elementAction.addElement("name");//���name�ڵ�
		elementName.addText(actionName);//Ϊname�ڵ�����ı�
		Element elementStartTime=elementAction.addElement("s-time");//��ӷ��ʿ�ʼʱ��ڵ�
		elementStartTime.addText(preDate);
		Element elementEndTime=elementAction.addElement("e-time");//��ӷ��ʽ���ʱ��ڵ�
		elementEndTime.addText(afterDate);
		Element elementActionName=elementAction.addElement("result");//��ӷ��ؽ���ڵ�
		elementActionName.addText(actionResult);
		//����һ�������ʽ��ÿ���ڵ�Ԫ�ؿ��Զ�����
		OutputFormat outputFormat=OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("UTF-8");
		XMLWriter xmlWrite=new XMLWriter(new FileWriter(file),outputFormat);//д��xml�ļ���λ�ü���ʽ
		xmlWrite.write(document);//��documentд��
		xmlWrite.close();
		System.out.println("д����־�ļ�log.xml�ɹ�����");
	}
}
