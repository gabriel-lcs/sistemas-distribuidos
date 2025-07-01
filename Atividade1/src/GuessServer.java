import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GuessServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        ArrayList<Integer> tent = new ArrayList<>();


        for (int jogador = 0; jogador < 2; jogador++){
            System.out.println("Eu sou Servidor e estou aguardando alguem se conectar comigo me acionando para responder...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Aguardando Cliente se conectar!");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Random rand = new Random();
            int numberToGuess = rand.nextInt(100) + 1; // numero entre 1 e 100
            String inputLine;
            int guess = 0;

            out.println("Sou o Servidor ... Seja Bem-vindo ao jogo de adivinhação! Tente adivinhar um numero entre 1 e 100.");

            while (true) {
                inputLine = in.readLine();
                if (inputLine.startsWith("TENTATIVAS=")) {
                    int tentativas = Integer.parseInt(inputLine.split("=")[1]);
                    tent.add(tentativas);
                    break;
                }

                try {
                    guess = Integer.parseInt(inputLine);
                    if (guess < numberToGuess) {
                        out.println("Sou o Servidor e digo que deve ser numero Maior");
                    } else if (guess > numberToGuess) {
                        out.println("Sou o Servidor e digo que deve ser numero Menor");
                    } else {
                        System.out.println("Você Acertou! Parabens!");
                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        out.println("Sou o Servidor e informo que Acertou! Parabens!");
                    }

                } catch (NumberFormatException e) {
                    out.println("Sou o servidor por favor, digite um numero valido.");
                }
            }
            in.close();
            out.close();
            clientSocket.close();
        }

        if (tent.size() >= 2) {
            System.out.println("Jogador 1 tentou " + tent.get(0));
            System.out.println("Jogador 2 tentou " + tent.get(1));
            System.out.println("----------------------------------------");

            if (tent.get(0) > tent.get(1)){
                System.out.println("Jogador 2 ganhou");
            } else if (tent.get(1) > tent.get(0)) {
                System.out.println("Jogador 1 ganhou");
            } else {
                System.out.println("Empate");
            }

        } else {
            System.out.println("Erro: ainda não recebi as tentativas dos dois jogadores.");
        }

        serverSocket.close();
    }
}