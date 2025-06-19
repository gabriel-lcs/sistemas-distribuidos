import java.rmi.Remote;
import java.rmi.RemoteException;
public interface PingService extends Remote {
    long ping(int seq) throws RemoteException;
}