import java.net.*;
import java.io.*;
public class MonitorImpressoras {
    public static void main(String[] args) {
        try {
            String host = InetAddress.getLocalHost().getHostName();
// Simula a lista de impressoras
            String impressoras = "Impressora1, Impressora2, Fax1, Scanner3";
            String mensagem = "Host: " + host + " | Impressoras: " + impressoras;
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensagem);
            System.out.println("Mensagem enviada: " + mensagem);
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}