import java.util.Scanner;

/**
 * Clase Mazmorras actualizada para integración con Torre
 */
public class Mazmorras {
    private int numeroCeldas;
    private boolean tienePrisioneros;
    private Scanner scanner;
    private Torre torre; // Nueva propiedad

    public Mazmorras(int numeroCeldas, boolean tienePrisioneros, Torre torre) {
        this.numeroCeldas = numeroCeldas;
        this.tienePrisioneros = tienePrisioneros;
        this.scanner = new Scanner(System.in);
        this.torre = torre;
    }

    /**
     * Método principal para explorar las mazmorras
     */
    public void explorar() {
        System.out.println("\nEntras a las oscuras mazmorras con " + numeroCeldas + " celdas.");

        if (tienePrisioneros) {
            System.out.println("¡Hay prisioneros en las celdas!");
            // Aquí puedes agregar lo que quieras que pase con los prisioneros
        } else {
            System.out.println("Las celdas están vacías.");
            // Aquí puedes agregar otro tipo de evento si quieres
        }

        completarMazmorras(); // Llamada para indicar que se completaron las mazmorras
    }

    /**
     * Marca las mazmorras como completadas e informa a la torre
     */
    private void completarMazmorras() {
        System.out.println("\n¡Has limpiado las mazmorras!");
        torre.completarMazmorras();
    }

    @Override
    public String toString() {
        return "Mazmorras con " + numeroCeldas + " celdas" +
               (tienePrisioneros ? " (con prisioneros)" : " (vacías)");
    }
}
