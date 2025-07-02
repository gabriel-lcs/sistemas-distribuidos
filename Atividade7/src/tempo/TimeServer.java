package tempo;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;

// Servidor de tempo
public class TimeServer extends UnicastRemoteObject implements TimeService {
    public TimeServer() throws RemoteException {}

    public long getServerTime() throws RemoteException {
        return Instant.now().toEpochMilli();
    }

    public static void main(String[] args) throws Exception {

        LocateRegistry.createRegistry(1099);
        TimeServer server = new TimeServer();
        Naming.rebind("TimeService", server);
        System.out.println("Servidor de tempo pronto.");
    }
}

