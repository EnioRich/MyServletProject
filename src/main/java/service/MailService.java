package service;

import org.apache.log4j.Logger;
import utils.RandomHelper;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MailService {
  private static final Logger LOGGER = Logger.getLogger(MailService.class);

  public String sendMail(final String userEmail) {
    final String username = "evakule15@gmail.com";
    final String password = "4619751Egor#";

    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS

    Session session = Session.getInstance(prop,
            new Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
              }
            });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("evakule15@gmail.com"));
      message.setRecipient(
              Message.RecipientType.TO,
              new InternetAddress(userEmail));
      message.setSubject("Purchase confirmation code");
      String randomCode = RandomHelper.randomCode();
      message.setText("Your code for buy accepting: " + randomCode);

      Transport.send(message);
      LOGGER.info("Done!");
      return randomCode;
    } catch (MessagingException e) {
      LOGGER.error("Couldn't send message", e);
      return "";
    }
  }
}