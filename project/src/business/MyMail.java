package business;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.io.File;
import java.util.Properties;
import entity.MailMessage;
import entity.SMTPServer;

public class MyMail {
	
	public Session getMailSession(SMTPServer mailServer, final String from, final String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", mailServer.getServer());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", mailServer.getPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		//Get the session object
		Session session = Session.getInstance(props, 
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		return session;
	}
	
	//send a MailMessage
	public boolean sendMail(MailMessage mm, Session session) throws Exception{
		
		//Create a default MimeMessage Object
		Message message = new MimeMessage(session);
		
		//Set from: header  field of the header
		message.setFrom(new InternetAddress(mm.getFrom()));
		
		//Set to: header  field of the header
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mm.getTo()));
		
		//Set subject: header field
		message.setSubject(MimeUtility.encodeText(mm.getSubject(), "UTF-8", null));
		//message.setSubject(mm.getSubject());
		
		//Now set the actual message content
		
		//Set text
		Multipart multipart = new MimeMultipart();
        MimeBodyPart text = new MimeBodyPart();
        text.setText(mm.getMessage(),"UTF-8");
        multipart.addBodyPart(text);
        
        //Set attachment
		File file = new File(mm.getPath());
		if (file.exists()) {
			MimeBodyPart attachment = new MimeBodyPart();
			DataSource source = new FileDataSource(file.getPath());
			attachment.setDataHandler(new DataHandler(source));
			//attachment.setFileName(file.getName());
			String fileName = file.getName();
			attachment.setFileName(MimeUtility.encodeText(fileName, "UTF-8", null));
			multipart.addBodyPart(attachment);
		}
		
        message.setContent(multipart);
		//Send message
		Transport.send(message);
		return true;
	}
}
