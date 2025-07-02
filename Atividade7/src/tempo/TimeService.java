package tempo;

import java.rmi.*;
interface TimeService extends Remote {
    long getServerTime() throws RemoteException;
}
