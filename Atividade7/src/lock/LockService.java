package lock;

import java.rmi.*;
import java.rmi.server.*;
interface LockService extends Remote {
    boolean acquireLock() throws RemoteException;
    void releaseLock() throws RemoteException;
}
