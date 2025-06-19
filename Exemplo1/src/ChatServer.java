import java.net.*;
import java.io.*;
public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("Servidor aguardando conexao...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado!");
        BufferedReader in = new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String inputLine, outputLine;
        while (true) {
            inputLine = in.readLine();
            if (inputLine.equalsIgnoreCase("END")) {
                out.println("BYE");
                break;
            }
            System.out.println("Cliente: " + inputLine);
            System.out.print("Servidor: ");
            outputLine = stdIn.readLine();
            out.println(outputLine);
        }
        in.close();
        out.close();
        stdIn.close();
        clientSocket.close();
        serverSocket.close();
    }
}