package com.quynhtien.cineasy.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${fe.url}")
    private String frontendUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String to, UUID token) {
        String verifyUrl = frontendUrl + "/verifyEmail?token=" + token;

        String htmlContent = """
            <html>
                <body style="font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 20px;">
                    <div style="max-width: 500px; margin: auto; background: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.05);">
                        <h2 style="color: #000;">Welcome to Cineasy 🎬</h2>
                        <p>Thank you for registering! Please confirm your email by clicking below:</p>
                        <a href="%s"
                           style="display:inline-block; padding:12px 24px; background-color:#000; color:#fff;
                                  text-decoration:none; border-radius:6px; box-shadow: 0 4px 8px rgba(0,0,0,0.2);
                                  font-weight:bold;">
                            Verify Email
                        </a>
                        <hr style="margin-top:30px; border:none; border-top:1px solid #ddd;">
                        <p style="font-size: 12px; color: #777;">— The Cineasy Team 🍿</p>
                        <div style="text-align:center; margin-bottom:20px;">
                        <img src="https://cineasyimages.blob.core.windows.net/cineasy-images/cineasy-email.jpg" alt="Cineasy Logo"\s
                        style="max-width:500px; height:auto; border-radius:4px;"/>
                        </div>
                    </div>
                </body>
            </html>
           \s""".formatted(verifyUrl);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}

