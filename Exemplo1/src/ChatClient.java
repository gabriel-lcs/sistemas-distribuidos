import java.net.*;
import java.io.*;
public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 2000);
        BufferedReader in = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String inputLine, outputLine;
        while (true) {
            System.out.print("Cliente: ");
            outputLine = stdIn.readLine();
            out.println(outputLine);
            inputLine = in.readLine();
            System.out.println("Servidor: " + inputLine);
            if (inputLine.equalsIgnoreCase("BYE")) break;
        }
        in.close();
        out.close();
        stdIn.close();
        socket.close();
    }
}