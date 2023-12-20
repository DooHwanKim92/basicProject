package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Getter
@Setter
public class SendMail {
    @Getter
    private static int randomNumber;
    public static void naverMailSend(String emailAddress) {
        String user = "rmfpsvldj01@naver.com";
        String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.naver.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

            message.setSubject("[WSMS] ID/비밀번호 찾기 보안코드 입니다.");

            int randomNumber = (int)(Math.random()*1000+1);

            message.setText("아래 코드를 프로그램에 숫자로 입력해주세요.\n ※ 보안 코드 : [" + randomNumber + "]");

            setRandomNumber(randomNumber);

            Transport.send(message);
            System.out.println("보안코드 메일 발송 성공!!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void naverMailSend(String emailAddress,String title, String text) {
        String user = "rmfpsvldj01@naver.com";
        String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.naver.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

            message.setSubject("[WSMS 메일발송] "+title);
            message.setText(text);

            setRandomNumber(randomNumber);

            Transport.send(message);
            System.out.println("메일 발송 성공!!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void setRandomNumber(int randomNumber) {
        SendMail.randomNumber = randomNumber;
    }
}
