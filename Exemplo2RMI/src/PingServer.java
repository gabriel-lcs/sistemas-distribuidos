import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class PingServer implements PingService {
    public long ping(int seq) {
        return System.nanoTime(); // Retorna timestamp do servidor
    }
    public static void main(String[] args) throws Exception {
        PingServer obj = new PingServer();
        PingService stub = (PingService)
                UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("PingService", stub);
        System.out.println("Servidor RMI pronto.");
    }
}