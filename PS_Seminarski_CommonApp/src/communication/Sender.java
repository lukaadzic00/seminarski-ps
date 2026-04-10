/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukaa
 */
public class Sender {
    private final ObjectOutputStream out;

    public Sender(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void send(Object obj) throws Exception{
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            throw new Exception("Error sending object: \n"+ex.getMessage());
        }
    }
}
