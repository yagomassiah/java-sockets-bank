import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * cliente
 */
public class cliente {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerConnection ServerConn = new ServerConnection(socket);


       // BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        new Thread(ServerConn).start();
       
        while (true) {
            System.out.println("> ");
            String command = teclado.readLine();
            if (command.equals("sair"))
                break;
            out.println(command);

         /*    String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse); */

        }

       // JOptionPane.showMessageDialog(null, serverResponse);

        socket.close();
        System.exit(0);
    }

}