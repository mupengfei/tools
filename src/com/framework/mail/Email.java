package com.framework.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
//	private final String host = "smtp.163.com";
//	private final String port = "25";
//	private static final String sender = "mupf@163.com";
//	private static final String password = "mu789456";
	private final String host = "mail.shineenergy.com";
	private final String port = "25";
	private static final String sender = "xn@shineenergy.com";
	private static final String password = "xxx";
	private static final Authenticator auth;
	static{
		auth = null;
//		auth = new SimpleAuthenticator(sender,password);
	}

	public void sendMail(String targetAddr, String subject, String context) {
		Transport trans = null;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
//			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			InternetAddress sendAddr = new InternetAddress(sender);
			msg.setFrom(sendAddr);
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					targetAddr));
			msg.setSubject(subject);
			//msg.setText(context);
			msg.setContent(context, "text/html;CHARSET=utf8");
			msg.saveChanges();
			trans = session.getTransport("smtp");
//			trans.connect(host, sender);
//			trans.send(msg);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	private static class SimpleAuthenticator extends Authenticator{
		private final PasswordAuthentication pa;
		
		public SimpleAuthenticator(String userName,String passWord){
			pa = new PasswordAuthentication(userName,passWord);
		}
		
		protected PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Email em = new Email();
//		em.sendMail("mupf@163.com", "测试", "<a href ='http://www.baidu.com'>ssss</a><br><p>测试</p>");
		em.sendMail("moupf@shineenergy.com", "test", "<html><body><a href ='http://www.baidu.com'>sx</a><br><p>测试</p></body></html>");
	}

}
