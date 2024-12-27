package at.spg.diplomprojektbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String token) {
        String subject = "Email Verification";
        String verificationUrl = "https://spring-backend-qk37.onrender.com/api/auth/verify?token=" + token;
        String body = "Please click the link to verify your email: " + verificationUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}