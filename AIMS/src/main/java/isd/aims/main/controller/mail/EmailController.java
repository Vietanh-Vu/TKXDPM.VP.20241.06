package isd.aims.main.controller.mail;

import isd.aims.main.entity.order.Order;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Properties;

public class EmailController {
    private final Properties properties = new Properties();
    private final String username = "anh.vv993@gmail.com";
    private final String password = "password";

    public EmailController() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
    }

    public void sendOrderConfirmationEmail(Order order) throws MessagingException, IOException {
        String htmlTemplate = readEmailTemplate();

        String htmlContent = htmlTemplate
                .replace("{{orderId}}", String.valueOf(order.getId()))
                .replace("{{name}}", order.getName())
                .replace("{{email}}", order.getEmail())
                .replace("{{phone}}", order.getPhone())
                .replace("{{address}}", order.getAddress())
                .replace("{{province}}", order.getProvince())
                .replace("{{shipping_fee}}", formatCurrency(order.getShippingFee()))
                .replace("{{paymentType}}", order.getPaymentType())
                .replace("{{paymentStatus}}", order.getPaymentStatus())
                .replace("{{totalAmount}}", formatCurrency(order.getTotalAmount()));

        sendHtmlEmail(order.getEmail(), htmlContent);
        System.out.println("Email sent successfully");
    }

    private void sendHtmlEmail(String to, String htmlContent) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Xác nhận đơn hàng");

        // Set content type là HTML
        message.setContent(htmlContent, "text/html; charset=utf-8");

        Transport.send(message);
    }

    private String readEmailTemplate() throws IOException {
        // Đọc file template từ resources
        try (InputStream inputStream = getClass().getResourceAsStream("/isd/aims/main/email/order.html")) {
            if (inputStream == null) {
                throw new IOException("Could not find email template");
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String formatCurrency(double amount) {
        // Format số tiền theo định dạng tiền tệ Việt Nam
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }
}