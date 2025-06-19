import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map; // Usaremos um Map para retornar as disponibilidades

public interface ReservaService extends Remote {
    Map<String, Integer> getDisponibilidades() throws RemoteException;

    boolean fazerReserva(String tipoItem) throws RemoteException;
}
