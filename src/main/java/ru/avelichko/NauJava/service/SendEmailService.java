package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class SendEmailService {
    @Value("${MAIL_SENDER}")
    private String mailSender;
    @Value("${MAIL_PASSWORD}")
    private String mailPassword;

    private final String body = "Добрый день! Сформирован отчёт.";
    private final String successfulMessage = "Отчёт успешно отправлен на почту ";
    private final String unsuccessfulMessage = "Ошибка при отправке отчёта на почту ";

    public String sendEmail(String recipients, String subject, String fileName) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        try {
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSender, mailPassword);
                }
            };
            Session session = Session.getDefaultInstance(props, authenticator);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailSender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(subject);

            // Создаем тело сообщения
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Создаем multipart для вложений
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Включаем файл как вложение
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            // Компонуем все части в сообщение
            message.setContent(multipart);

            // Отправляем сообщение
            Transport.send(message);
            return successfulMessage + recipients;
        } catch (MessagingException e) {
            e.printStackTrace();
            return unsuccessfulMessage + recipients;
        }
    }
}
