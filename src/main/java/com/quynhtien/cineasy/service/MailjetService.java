package com.quynhtien.cineasy.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailjetService {

    @Value("${mailjet.apikey}")
    @NonFinal
    String apiKey;

    @Value("${mailjet.secretkey}")
    @NonFinal
    String secretKey;

    @Value("${mailjet.sender-email}")
    @NonFinal
    String fromEmail;

    @Value("${fe.url}")
    @NonFinal
    String feUrl;

    public void sendVerificationEmail(String to, UUID emailVerificationTokenId) {
        try {
            // ✅ Correct Mailjet client initialization for version 5.x
            MailjetClient client = new MailjetClient(apiKey, secretKey);

            String verifyUrl = feUrl + "/verify?token=" + emailVerificationTokenId;

            MailjetRequest request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", fromEmail)
                                            .put("Name", "Cineasy"))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", to)
                                                    .put("Name", "User")))
                                    .put(Emailv31.Message.SUBJECT, "Confirm your email")
                                    .put(Emailv31.Message.TEXTPART, "Click the link to verify your account: " + verifyUrl)
                                    .put(Emailv31.Message.HTMLPART,
                                            "<h3>Click the link to verify your account:</h3>" +
                                                    "<a href=\"" + verifyUrl + "\">Verify Email</a>")
                                    .put(Emailv31.Message.CUSTOMID, "EmailVerification")
                            )
                    );

            MailjetResponse response = client.post(request);
            log.info("Mailjet response status: {}", response.getStatus());
            log.info("Mailjet response data: {}", response.getData());

        } catch (Exception e) {
            log.error("Failed to send verification email", e);
        }
    }
}
