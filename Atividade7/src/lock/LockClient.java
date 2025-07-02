package lock;

import java.rmi.*;
import java.rmi.server.*;
public class LockClient {
    public static void main(String[] args) throws Exception {
        LockService lockService = (LockService) Naming.lookup("rmi://localhost/LockService");
        if (lockService.acquireLock()) {
            System.out.println("Lock adquirido - Secao critica");
            Thread.sleep(1000);
            lockService.releaseLock();
        } else {
            System.out.println("Lock ocupado - Tente novamente");
        }
    }
}