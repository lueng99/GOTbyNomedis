public class Bosque extends Escenario {
    private String nombre;
    private boolean claroDescubierto;

    public Bosque(String nombre) {
        super();
        this.setTieneNPC(false);
        this.setPermiteLucha(false);
        this.setInteractuable(true);
        this.nombre = nombre;
        this.claroDescubierto = false;
    }

    public void explorar() {
        System.out.println("\nEstás en el " + nombre + ". El aire huele a musgo y hojas frescas.");
        
        if(!claroDescubierto) {
            System.out.println("Entre los árboles, ves un sendero que parece llevar a un claro iluminado.");
            System.out.println("¿Quieres seguir el sendero hacia el claro? (s/n)");
            
            boolean decision = true; // Siempre va al claro
            
            if(decision) {
                irAlClaro();
            } else {
                System.out.println("Decides permanecer en la espesura del bosque.");
            }
        } else {
            System.out.println("Conoces bien el camino al Claro del Bosque.");
            irAlClaro();
        }
    }

    /**
     * Método para ir al Claro del Bosque
     */
    private void irAlClaro() {
        claroDescubierto = true;
        System.out.println("\nSigues el sendero hasta llegar a un hermoso claro iluminado por el sol.");
        System.out.println("El Claro del Bosque parece un lugar mágico y tranquilo.");
        
        // Aquí se cargaría el escenario ClaroDelBosque
    }

    @Override
    public String toString() {
        return "Bosque: " + nombre + " - " +
               (claroDescubierto ? "Claro descubierto" : "Claro no descubierto");
    }
}
