import java.net.*;
import java.io.*;
public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6000);
            System.out.println("Servidor aguardando conexões na porta 6000...");
            while (true) {
                Socket cliente = servidor.accept();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(cliente.getInputStream()));
                String mensagem = in.readLine();
                System.out.println("Recebido: " + mensagem);
                cliente.close();
            }
        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}