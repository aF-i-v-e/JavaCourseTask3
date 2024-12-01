package ru.avelichko.NauJava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;

@SpringBootTest
public class SendEmailServiceTest {
    @Value("${TEST_MAIL_RECIPIENT}")
    private String testMailRecipient;

    private final SendEmailService sendEmailService;

    @Autowired
    public SendEmailServiceTest(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Test
    void successfullyEmailSend() {
        String path = Paths.get(System.getProperty("user.dir"))
                .resolve("src").resolve("test").resolve("java")
                .resolve("ru").resolve("avelichko")
                .resolve("NauJava").resolve("resources")
                .resolve("test.pdf").toString();
        sendEmailService.sendEmail(testMailRecipient, "test", path);
    }
}
