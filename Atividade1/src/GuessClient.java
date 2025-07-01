import java.net.*;
import java.io.*;

public class GuessClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String fromServer;
        String fromUser;

        int tentativas = 0;

        // Mensagem inicial do servidor
        System.out.println(in.readLine());

        while (true) {
            System.out.print("Seu palpite: ");
            fromUser = stdIn.readLine();
            tentativas++;
            out.println(fromUser);
            fromServer = in.readLine();
            System.out.println("Servidor: " + fromServer);

            if (fromServer.startsWith("Sou o Servidor e informo que Acertou! Parabens!")) {
                out.println("TENTATIVAS=" + tentativas);
                System.out.println("Quantidade de tentativas: " + tentativas);
                break;
            }
        }

        in.close();
        out.close();
        stdIn.close();
        socket.close();
    }
}