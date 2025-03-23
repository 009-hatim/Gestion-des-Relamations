/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String toEmail, String newPassword) {
        final String username = "hatim305koubri@gmail.com"; 
        final String password = "qsuk nzpv iylm wxfs"; 
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            } 
        }); 

        try {
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(username));  
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Réinitialisation de votre mot de passe");
            message.setText("Votre nouveau mot de passe temporaire est : " + newPassword +
                            "\nVeuillez vous connecter et changer votre mot de passe immédiatement.");
        } catch (MessagingException e) {
            e.printStackTrace();  
            throw new RuntimeException("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }
}