import java.net.*;
import java.io.*;
import java.util.Random;

public class GuessServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Eu sou Servidor e estou aguardando alguem se conectar comigo me acionando para responder...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Aguardando Cliente se conectar!");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1; // numero entre 1 e 100
        String inputLine;
        int guess = 0;

        out.println("Sou o Servidor ... Seja Bem-vindo ao jogo de adivinhacao! Tente adivinhar um numero entre 1 e 100.");

        while (true) {
            inputLine = in.readLine();
            try {
                guess = Integer.parseInt(inputLine);
                if (guess < numberToGuess) {
                    out.println("Sou o Servidor e digo que deve ser numero Maior");
                } else if (guess > numberToGuess) {
                    out.println("Sou o Servidor e digo que deve ser numero Menor");
                } else {
                    out.println("Sou o Servidor e informo que Acertou! Parabens!");
                    break;
                }
            } catch (NumberFormatException e) {
                out.println("Sou o servidor por favor, digite um numero valido.");
            }
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
