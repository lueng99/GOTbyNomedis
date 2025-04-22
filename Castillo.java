import java.util.Scanner;

public class Castillo extends Escenario {
    private String nombre;
    private Torre torre;
    private Mazmorras mazmorras;
    private Scanner scanner;

    public Castillo(String nombre, Torre torre, Mazmorras mazmorras) {
        super();
        this.setTieneNPC(false);
        this.setPermiteLucha(false);
        this.setInteractuable(true);
        
        this.nombre = nombre;
        this.torre = torre;
        this.mazmorras = mazmorras;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método para explorar el castillo con interacción por teclado
     */
    public void explorarCastillo() {
        System.out.println("\nEstás en el " + nombre);
        System.out.println("¿A dónde quieres ir?");
        System.out.println("a - Explorar la torre (" + torre + ")");
        System.out.println("d - Bajar a las mazmorras (" + mazmorras + ")");
        System.out.println("s - Salir del castillo");

        boolean salir = false;
        
        while(!salir) {
            System.out.print("\nElige una opción (a/d/s): ");
            String input = scanner.nextLine().toLowerCase();
            
            switch(input) {
                case "a":
                    torre.explorar();
                    break;
                case "d":
                    mazmorras.explorar();
                    break;
                case "s":
                    System.out.println("Abandonas el castillo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Usa a, d o s.");
            }
        }
    }

    @Override
    public String toString() {
        return "Castillo: " + nombre + "\n" +
               "  - " + torre + "\n" +
               "  - " + mazmorras + "\n" +
               "Características: " +
               "NPCs: " + (tieneNPC() ? "Sí" : "No") + ", " +
               "Lucha: " + (permiteLucha() ? "Sí" : "No") + ", " +
               "Interactuable: " + (esInteractuable() ? "Sí" : "No");
    }
}
