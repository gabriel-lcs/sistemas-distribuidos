import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class PingClient {
    public static void main(String[] args) throws Exception {
        Registry registry =
                LocateRegistry.getRegistry("localhost", 1099);
        PingService stub = (PingService)
                registry.lookup("PingService");
        int totalPings = 100;
        long[] rtts = new long[totalPings];
        int received = 0;
        int lost = 0;
        long lastRtt = 0;
        long jitterSum = 0;
        for (int i = 0; i < totalPings; i++) {
            try {
                long start = System.nanoTime();
                stub.ping(i);
                long end = System.nanoTime();
                rtts[received] = end - start;
                if (received > 0) {
                    jitterSum += Math.abs(rtts[received] - rtts[received
                            - 1]);
                }
                received++;
            } catch (Exception e) {
                lost++;
            }
            Thread.sleep(10); // intervalo entre pings
        }
// Calculo das metricas
        long rttSum = 0;
        for (int i = 0; i < received; i++) rttSum += rtts[i];
        double avgRtt = (rttSum / (double) received) /
                1_000_000.0;
        double avgJitter = (received > 1) ? (jitterSum / (double)
                (received - 1)) / 1_000_000.0 : 0.0;
        double lossRate = ((totalPings - received) * 100.0) /
                totalPings;
        System.out.printf("Atraso medio (latencia): %.3f ms\n",
                avgRtt);
        System.out.printf("Jitter medio: %.3f ms\n", avgJitter);
        System.out.printf("Taxa de perda: %.2f%%\n", lossRate);
    }
}