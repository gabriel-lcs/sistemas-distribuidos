import java.net.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class MonitorarSis {
    public static void main(String[] args) {
        try {
            String host = InetAddress.getLocalHost().getHostName();
            OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            double cpuLoad = osBean.getSystemCpuLoad();
            String cpu = (cpuLoad >= 0)
                    ? String.format("%.1f", cpuLoad * 100) + "%"
                    : "Indisponível";

            long totalMem = osBean.getTotalPhysicalMemorySize();
            long memLivre = osBean.getFreePhysicalMemorySize();
            long memUsada = (totalMem - memLivre) / (1024 * 1024);

            String mensagem = "Host: " + host + " | Cpu: " + cpu + " | Memória: " + memUsada;
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