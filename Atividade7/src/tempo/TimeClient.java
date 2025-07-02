package tempo;

// Cliente sincronizando tempo
import java.rmi.Naming;
public class TimeClient {
    public static void main(String[] args) throws Exception {
        TimeService timeService = (TimeService) Naming.lookup("rmi://localhost/TimeService");

        // Calculo do RTT (Round-Trip Time)
        long start = System.currentTimeMillis();
        long serverTime = timeService.getServerTime();
        long end = System.currentTimeMillis();
        long rtt = end - start;

        // Ajuste do tempo do cliente
        long adjustedTime = serverTime + (rtt / 2);
        System.out.println("Tempo ajustado: " + adjustedTime);
    }
}
