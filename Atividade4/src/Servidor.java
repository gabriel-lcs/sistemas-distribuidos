import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor iniciado na porta 12345...");

        while (true) {
            // Espera um cliente se conectar
            Socket cliente = servidor.accept();

            // Cria nova thread para atender o cliente
            new Thread(() -> {
                try {
                    // Leitura e escrita no socket do cliente
                    BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

                    // Lê mensagem do cliente
                    String mensagem = in.readLine();

                    // Converte a mensagem para maiúsculas e envia de volta
                    if (mensagem != null) {
                        out.println(mensagem.toUpperCase());
                    }

                    // Fecha a conexão com o cliente
                    cliente.close();
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}