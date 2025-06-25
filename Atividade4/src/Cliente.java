import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // Conecta ao servidor na porta 12345
        Socket socket = new Socket("localhost", 12345);

        // Leitura da entrada do usuário (teclado)
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        // Para enviar dados ao servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Para receber dados do servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Digite uma mensagem: ");
        String mensagem = teclado.readLine();

        // Envia a mensagem para o servidor
        out.println(mensagem);

        // Recebe a resposta do servidor e mostra na tela
        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);

        // Fecha o socket
        socket.close();
    }
}
