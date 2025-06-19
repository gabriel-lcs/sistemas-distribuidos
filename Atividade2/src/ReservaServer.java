import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.rmi.RemoteException;

public class ReservaServer implements ReservaService {
    private Map<String, Integer> disponibilidades;

    public ReservaServer() throws RemoteException {

        disponibilidades = new ConcurrentHashMap<>();
        disponibilidades.put("passagem", 5);
        disponibilidades.put("hotel", 3);
        disponibilidades.put("evento", 10);
        System.out.println("Servidor inicializado com disponibilidades: " + disponibilidades);
    }

    @Override
    public Map<String, Integer> getDisponibilidades() throws RemoteException {
        // Retorna uma cópia do mapa para evitar modificações externas diretas
        // e garantir que o cliente veja o estado atual.
        return new ConcurrentHashMap<>(disponibilidades);
    }

    @Override
    public boolean fazerReserva(String tipoItem) throws RemoteException {
        synchronized (this) {
            Integer quantidadeAtual = disponibilidades.get(tipoItem);
            if (quantidadeAtual != null && quantidadeAtual > 0) {
                disponibilidades.put(tipoItem, quantidadeAtual - 1);
                System.out.println("Reserva de '" + tipoItem + "' efetuada. Nova disponibilidade: " + disponibilidades.get(tipoItem));
                return true;
            } else {
                System.out.println("Falha ao reservar '" + tipoItem + "': Indisponível.");
                return false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ReservaServer obj = new ReservaServer();

        ReservaService stub = (ReservaService) UnicastRemoteObject.exportObject(obj, 0);

        Registry registry = LocateRegistry.createRegistry(1099);

        registry.bind("ReservaService", stub);

        System.out.println("Servidor de Reservas RMI pronto na porta 1099.");
    }
}