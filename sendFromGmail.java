/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test_sendmail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * note : you must turn on funtion in account gmail :https://www.google.com/settings/security/lesssecureapps
 * this Class user to sent mail form Gmail to Gmail, yahoo mail , rocket maill....
 * @author Phạm Ngọc Hiếu
 */

public class sendFromGmail {
    /**
     * 
     * @param from : mail adress sender
     * @param pass : passworld mail adress sender
     * @param to : adress resever
     * @param title : title mail
     * @param content : content mail
     */
    public static void sendMail(String from,String pass,String to,String title,String content)
{  
   String host = "smtp.gmail.com";
   // Get system properties
   Properties properties = System.getProperties();
   // Setup mail server
   properties.put("mail.smtp.starttls.enable", "true");
   properties.put("mail.smtp.host", host);
   properties.put("mail.smtp.user", from);
   properties.put("mail.smtp.password", pass);
   properties.put("mail.smtp.port", "587");
   properties.put("mail.smtp.auth", "true");
   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);
   try{
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(title);

      // Now set the actual message
      message.setText(content);

      // Send message
      Transport transport = session.getTransport("smtp");
      transport.connect(host,from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}
