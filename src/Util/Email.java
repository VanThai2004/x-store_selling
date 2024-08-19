/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TienBB
 */
public class Email {

    public static String taoMaXacNhan() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) { //ma xac nhan co 5 chu so bat ki, tao qua ham ramdom
            verificationCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return verificationCode.toString();
    }

    final String username = "tienbuibao303@gmail.com";
    final String password = "spwgomivwccultng";

    // Hàm gửi mã xác nhận đến email của nhân viên
    public static boolean guiMaXacNhanEmail(String email, String verificationCode) {
        final String username = "tienbuibao303@gmail.com";
        final String password = "spwgomivwccultng";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mã Xác Nhận");
            message.setText("Mã xác nhận của bạn là: " + verificationCode);

            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
            return false;
        }
    }
}
