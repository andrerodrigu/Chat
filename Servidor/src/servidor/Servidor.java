
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André
 */
public class Servidor {
    
    
    public static void main(String[] args) {
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        try {
            
            ServerSocket server = new ServerSocket(12345);
            Socket socket;
            
            while(true){
                
            socket = server.accept();
            System.out.println("Conectado!");
            
            clientes.add(new PrintStream(socket.getOutputStream()));
            
            Mensagem mensagem = new Mensagem(socket,clientes);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
