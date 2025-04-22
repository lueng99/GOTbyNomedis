import java.util.Random;

public class Torre {
    private String nombre;
    private int altura;
    private int interacciones;
    private boolean llaveObtenida;
    private Random random;

    public Torre(String nombre) {
        this.nombre = nombre;
        this.altura = 100 + new Random().nextInt(401); // Random entre 100-500m
        this.interacciones = 0;
        this.llaveObtenida = false;
        this.random = new Random();
    }

    /**
     * Método principal para interactuar con la torre
     */
    public void explorar() {
        System.out.println("\nEstás en la " + nombre + " de " + altura + " metros de altura.");
        
        if(interacciones < 3) {
            interactuarConNPC();
        } else {
            System.out.println("El anciano asiente con la cabeza pero permanece en silencio.");
        }
    }

    /**
     * Sistema de interacción obligatoria por fases con el NPC
     */
    private void interactuarConNPC() {
        interacciones++;
        System.out.println("\nUn anciano con túnica azul se acerca:");
        
        switch(interacciones) {
            case 1:
                System.out.println("'Veo que tienes potencial. Necesito que hagas algo por mí.'");
                System.out.println("'Primero, limpia las mazmorras de este castillo.'");
                System.out.println("[El anciano te señala hacia las mazmorras]");
                break;
                
            case 2:
                if(llaveObtenida) {
                    System.out.println("'¡Has limpiado las mazmorras! Toma esta llave.'");
                    System.out.println("[Recibes la Llave del Cáliz]");
                } else {
                    System.out.println("'¿Aún no has limpiado las mazmorras? No puedo ayudarte hasta que lo hagas.'");
                    interacciones--; // Repetir esta interacción
                }
                break;
                
            case 3:
                System.out.println("'Esa espada que llevas... es especial.'");
                System.out.println("'Ven conmigo a la Cueva Encantada. No es una petición, es una orden.'");
                System.out.println("[El anciano te agarra del brazo con fuerza sobrenatural]");
                System.out.println("[Te teletransporta a la entrada de la Cueva Encantada]");
                break;
        }
    }

    /**
     * Método llamado desde Mazmorras cuando se completa su objetivo
     */
    public void completarMazmorras() {
        this.llaveObtenida = true;
    }

    public boolean tieneLlaveCáliz() {
        return llaveObtenida;
    }

    public int getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return nombre + " (" + altura + "m) - " +
               (llaveObtenida ? "Llave obtenida" : "Llave pendiente");
    }
}
