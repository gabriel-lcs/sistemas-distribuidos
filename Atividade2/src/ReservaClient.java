// ReservaClient.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReservaClient {
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        ReservaService service = (ReservaService) registry.lookup("ReservaService");
        System.out.println("Cliente conectado ao Servidor de Reservas RMI.");

        // --- Exemplo 1: Consultar disponibilidades ---
        System.out.println("\n--- Consultando Disponibilidades Iniciais ---");
        Map<String, Integer> disponibilidadesIniciais = service.getDisponibilidades();
        disponibilidadesIniciais.forEach((tipo, qtd) ->
                System.out.println("  " + tipo + ": " + qtd + " disponíveis")
        );

        // --- Exemplo 2: Fazer uma reserva simples ---
        System.out.println("\n--- Tentando reservar uma Passagem ---");
        boolean reservouPassagem = service.fazerReserva("passagem");
        System.out.println("Reserva de Passagem efetuada? " + reservouPassagem);

        // Consultar novamente para ver a mudança
        System.out.println("\n--- Consultando Disponibilidade de Passagem Após Reserva ---");
        System.out.println("  Passagem: " + service.getDisponibilidades().get("passagem") + " disponíveis");


        // --- Exemplo 3: Simulação de Concorrência ---
        System.out.println("\n--- Simulando 7 clientes tentando reservar HOTÉIS simultaneamente ---");
        // Criamos um pool de threads para simular vários clientes agindo ao mesmo tempo
        ExecutorService executor = Executors.newFixedThreadPool(7);
        final String tipoItemConcorrente = "hotel"; // O item que será disputado

        for (int i = 1; i <= 7; i++) {
            final int clienteId = i;
            executor.submit(() -> {
                try {
                    System.out.println("  Cliente " + clienteId + " tentando reservar '" + tipoItemConcorrente + "'...");
                    boolean reservou = service.fazerReserva(tipoItemConcorrente);
                    if (reservou) {
                        System.out.println("  Cliente " + clienteId + " **CONSEGUIU** reservar um " + tipoItemConcorrente + "!");
                    } else {
                        System.out.println("  Cliente " + clienteId + " *NÃO* conseguiu reservar " + tipoItemConcorrente + " (indisponível).");
                    }
                } catch (Exception e) {
                    System.err.println("  Erro para o cliente " + clienteId + " ao reservar '" + tipoItemConcorrente + "': " + e.getMessage());
                }
            });
        }

        executor.shutdown(); // Desliga o executor após todas as tarefas serem submetidas
        executor.awaitTermination(1, TimeUnit.MINUTES); // Espera que todas as tarefas terminem

        System.out.println("\n--- Verificando Disponibilidade Final de HOTÉIS Após Concorrência ---");
        System.out.println("  " + tipoItemConcorrente + ": " + service.getDisponibilidades().get(tipoItemConcorrente) + " disponíveis");
    }
}