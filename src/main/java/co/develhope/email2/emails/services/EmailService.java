package co.develhope.email2.emails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendTo(String email, String title, String text) throws MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<h1>Welcome Samurai!>!</h1>" +
                    "<h2>Never fade away! </h2>" +
                    "<img src='https://tech4gamers.com/wp-content/uploads/2022/04/Cyberpunk-image.png'>" +
                    "<h3>" + text + "</h3>";
            helper.setText(htmlMsg, true);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setFrom(sender);
            javaMailSender.send(mimeMessage);
    }
}
