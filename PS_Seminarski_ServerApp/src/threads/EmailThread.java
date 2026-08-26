/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import email.EmailService;
import jakarta.mail.MessagingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Iznajmljivanje;

/**
 *
 * @author lukaa
 */
public class EmailThread extends Thread {

    private Iznajmljivanje iznajmljivanje;
    
    public EmailThread(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }
    
    @Override
    public void run() {
        EmailService emailService = new EmailService();
        try {
            emailService.sendEmail(iznajmljivanje);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
