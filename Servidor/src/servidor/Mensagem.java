
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author André
 */
public class Mensagem {
    private Socket socket;
    private ArrayList<PrintStream> clientes;
    
    public Mensagem(Socket socket,ArrayList<PrintStream> clientes){
        this.socket = socket;
        this.clientes = clientes;
        Thread();
    }
    
    private void Thread(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String mensagem = "";
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            while((mensagem = entrada.readLine())!=null){
                enviarMensagem(mensagem);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        });
        t.start();
    }

    private void enviarMensagem(String mensagem) {
        int i;
        for(i=0;i<clientes.size();i++){
            clientes.get(i).println(mensagem);
            clientes.get(i).flush();
        }
    }
}
