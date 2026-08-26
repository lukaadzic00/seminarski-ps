/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.AddressException;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author lukaa
 */
public class EmailService {
    public void sendEmail(Iznajmljivanje iznajmljivanje) throws AddressException, MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        final String sender = "luka.adzic.rs@gmail.com";
        final String password = "bamemwycilgjacku";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(iznajmljivanje.getCitalac().getEmail())
        );
        message.setSubject("Potvrda o iznajmljivanju knjiga");
        String body = "Poštovani,\n\nuspešno ste iznajmili sledeće knjige:\n";
        int i = 1;
        for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavki()) {
            body += "\n" + i + ") " + stavka.getKnjiga().getNaziv() + " | broj dana: " + stavka.getBrojDana() + " | iznos stavke: " + stavka.getIznos() + " dinara";
            i++;
        }
        body = body + "\n\nUKUPAN IZNOS: " + iznajmljivanje.getUkupanIznos() + " dinara";
        
        message.setText(body);
        
        Transport.send(message);
    }
}
