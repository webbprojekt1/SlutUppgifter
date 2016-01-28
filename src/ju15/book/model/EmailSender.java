/**
 * Author:Bo Yang
 */
package ju15.book.model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void sendMail(BookInfo bi) {
		Properties props = new Properties();
		props.put("mail.transport.protocol","smtps");
		props.put("mail.smtps.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtps.port", "465");
		props.put("mail.smtps.quitwait", false);

//		Session session = Session.getDefaultInstance(props,
//			new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {

//					return new PasswordAuthentication();

//					return new PasswordAuthentication("youthbo",);

//				}
//			});
		Session session=Session.getDefaultInstance(props);
		session.setDebug(true);

		try {

			//change security access https://www.google.com/settings/security/lesssecureapps
			Message message = new MimeMessage(session);
			message.setSubject("New booking");
			message.setText(bi.getItemnum()+"\n"+bi.getName()+":"+"\n"+bi.getEmail()+"\n"+"From "+bi.getStartDate()
			                +" to "+bi.getEndDate()+"\n"+bi.getMessage());
			message.setFrom(new InternetAddress(bi.getEmail()));
			message.setRecipient(Message.RecipientType.TO,

					new InternetAddress("irina.fatkoulin@gmail.com"));

					

			

			//Transport.send(message);
			Transport transport=session.getTransport();


			transport.connect("irina.fatkoulin@gmail.com", "plushogskolan");

			//transport.connect("myemail","mypassword" );

			



			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}