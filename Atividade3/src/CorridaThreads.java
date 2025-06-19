import java.util.*;

public class CorridaThreads {
    // Lista sincronizada para registrar a ordem de chegada
    public static List<String> podio = Collections.synchronizedList(new ArrayList<>());

    static class Corredor implements Runnable {
        private String nome;

        public Corredor(String nome) {
            this.nome = nome;
        }

        @Override

        public void run() {
            long tInicio = System.currentTimeMillis();
            Random rand = new Random();
            for (int i = 1; i <= 10; i++) {
                System.out.println(nome + " avançou para a etapa " + i);
                try {
                    Thread.sleep(rand.nextInt(600) + 100); // Pausa aleatória entre 100ms e 700ms
                } catch (InterruptedException e) {
                    System.out.println(nome + " foi interrompido!");
                }
            }
            long tFim = System.currentTimeMillis();
            long tempoTotal = tFim - tInicio;

            System.out.println(nome + " terminou a corrida!");

            CorridaThreads.podio.add(nome + " (" + tempoTotal + " ms)");

        }
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite quantos corredores deseja ter na corrida:");
        int quantidade = scanner.nextInt();

        Thread[] threads = new Thread[quantidade];

        scanner.close();

        for (int i = 0; i < quantidade; i++) {
            String nome = "Corredor " + (i + 1);
            threads[i] = new Thread(new Corredor(nome));
            threads[i].start();
        }

        for (int i = 0; i < quantidade ; i++){
            threads[i].join();
        }
        System.out.println("\n--- Pódio ---");

        for (int i = 0; i < podio.size(); i++) {
            System.out.println((i+1) + "º lugar: " + podio.get(i));
        }
    }
}