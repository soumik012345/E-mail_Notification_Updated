package mailprepare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class Mailconfigure {
	private String[] recipientToList = null;
	private String[] recipientCcList = null;
	private String smtpHost = "" ;
	private int smtpPort = 465;
	private String smtpEmail = "";
	private String smtpPass = "";
	private String smtpFrom = "";
	private String smtpUser = "";
	private String mailSubject = "Automation Module-2 : Data Status";
	
	private void loadProp() {
		Properties p = new Properties();
		FileInputStream is;
		try {
			is = new FileInputStream("config2.properties");
			p.load(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.smtpHost = p.getProperty("smtpHost");
		this.smtpPort = Integer.parseInt(p.getProperty("smtpPort"));
		this.smtpEmail = p.getProperty("smtpEmail");
		this.smtpPass = p.getProperty("smtpPass");
		this.smtpFrom = p.getProperty("smtpFrom");
		this.smtpUser = p.getProperty("smtpUser");
		this.recipientToList = p.getProperty("recipientToList").split(",", -1);
		this.recipientCcList = p.getProperty("recipientCcList").split(",", -1);
	}
	
	public void emailNotify() {
		System.out.println("Preparing to send email");	
		
		this.loadProp();
		
		try {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", this.smtpHost);
	        props.put("mail.smtp.starttls.enable", "true");
			/* transport er line ta deploy debar somoy bad dibo */
	        props.put("mail.transport.protocol", "smtps");
	        props.put("mail.smtps.auth", "true");
//	        if(this.smtpPass == "" || this.smtpPass == null) {
//	        	props.put("mail.smtps.auth", "false");
//	        }else {
//	        	props.put("mail.smtps.auth", "true");
//	        }
	        String tableResult = "";
	        String tableResult1[];
	        String tableResult2 [];
	        DatabaseJdbc jdbc = new DatabaseJdbc();
	        tableResult =  jdbc.Dbconnection();
	        tableResult1 = jdbc.Dbconnectiontwo();
	        tableResult2 = jdbc.Dbconnectionthree();
	        		
	        String mailBody = "Dear Concern, \r\n  Today's Status Of Adaptive Modulation Dashboard: \r\n Adaptive-Modulation-Huawei = " + tableResult1[0] + " \r\n Adaptive-Modulation-Ericsson = " + tableResult1[1] + " \r\n \r\n Today's Status Of Utilization Dashboard: \r\n  Utilization-Huawei = " + tableResult2[0] + " \r\n Utilization-Ericsson = "+tableResult2[1] +" \r\n \r \n Below tables was not updated today: \r\n  "+ tableResult + " \r\n \r\n \r\n" ;
	        
	        javax.mail.Session mailSession = Session.getDefaultInstance(props);
	        mailSession.setDebug(true);
	        Transport transport = ((javax.mail.Session) mailSession)
	                .getTransport();

	        
	    	
	        javax.mail.Message message = new MimeMessage(mailSession);
	        message.setHeader("Content-Type", "text/html");
	        message.setSubject("Automation Module-2 Failed Data Upload Lists");
	        message.setContent(mailBody, "text/html; charset=utf-8");
	        
	        Address[] a = null; 
			System.out.println("Recipient List (TO) : ");
			
			for (String to : this.recipientToList) {
				System.out.println("|- " + to);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			System.out.println("Recipient List (CC) : ");

			for (String cc : this.recipientCcList) {
				System.out.println("|- " + cc);

				message.addRecipient(Message.RecipientType.CC,  new InternetAddress(cc));
			}
	        
	        
	        transport.connect(this.smtpHost, this.smtpPort, this.smtpFrom,
	                this.smtpPass);
	        message.setFrom(new InternetAddress(this.smtpEmail, this.smtpUser));
	        message.setSubject(this.mailSubject);
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(mailBody);
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        messageBodyPart = new MimeBodyPart();
	        message.setContent(multipart);

	        transport.sendMessage(message,
	                message.getRecipients(javax.mail.Message.RecipientType.TO));

	    }catch (Exception e) {
	    	e.printStackTrace();
	    } 
		
	}
	

}
