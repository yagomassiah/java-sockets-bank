import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server
 */
public class Server {
    private static final int PORT = 9090;
    private static ArrayList<clienteHandler> clients = new ArrayList<>();
    public static ArrayList<Conta> contas = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    private static Integer numeroContas = 0;
   


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        contas.add(new Conta("Yago", "123", numeroContas));
        numeroContas++;
        contas.add(new Conta("Juliano", "456", numeroContas));
        numeroContas++;
        contas.add(new Conta("Rodrigo", "789", numeroContas));
        numeroContas++;

        while (true) {

            System.out.println("[SERVER] Esperando conexão do cliente");
            Socket client = listener.accept();
            System.out.println("[SERVER] Conectado");

            clienteHandler clientThread = new clienteHandler(client);

            clients.add(clientThread);

            pool.execute(clientThread);
        }
        /*
         * PrintWriter out = new PrintWriter(client.getOutputStream(), true);
         * BufferedReader in = new BufferedReader(new
         * InputStreamReader(client.getInputStream()));
         */
       
    }
    public static String teste() {
        return "teste";
        
    }

    
    public static Conta encontraConta(Integer id) {
        for(Conta conta : contas) {
            if(conta.id == id) {
                return conta;
            }
        }
        return null;
    }

}