import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 * ServerConnection
 */
public class ServerConnection implements Runnable {
    private Socket server;
    private BufferedReader in;
    // private PrintWriter out;

    public ServerConnection(Socket s) throws IOException {
        server = s;

        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {

        String serverResponse = null;
        try {
            while (true) {
                serverResponse = in.readLine();
                if (serverResponse == null)
                    break;
                System.out.println("Servidor: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
            in.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
