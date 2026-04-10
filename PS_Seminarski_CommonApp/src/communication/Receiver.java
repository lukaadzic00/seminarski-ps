/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukaa
 */
public class Receiver {
    private Socket socket;
    private final ObjectInputStream in;

    public Receiver(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    public Object receive() throws Exception{
        try {
            return in.readObject();
        } catch (IOException ex) {
            throw new Exception("Error receiving object!\n"+ex.getMessage());
        }
    }
}
