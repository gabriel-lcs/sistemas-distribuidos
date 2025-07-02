package lock;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class LockServiceDistribuido extends UnicastRemoteObject implements LockService {
    private boolean isLocked = false;
    public LockServiceDistribuido() throws RemoteException {}
    public synchronized boolean acquireLock() throws RemoteException {
        if (!isLocked) {
            isLocked = true;
            return true;
        }
        return false;
    }

    public synchronized void releaseLock() throws RemoteException {
        isLocked = false;
    }

    public static void main(String[] args) {
        try {
            LockServiceDistribuido lockService = new LockServiceDistribuido();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("LockService", lockService);
            System.out.println("Servidor de lock distribuido iniciado!");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}