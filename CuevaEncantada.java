public class CuevaEncantada extends Escenario {
    private String nombre;
    private boolean dragonDerrotado;
    private boolean conversacionInicialRealizada;

    public CuevaEncantada() {
        super();
        this.setTieneNPC(true);       // Tiene el NPC que advierte
        this.setPermiteLucha(true);   // Permite la batalla final
        this.setInteractuable(true);  // Es interactuable
        this.nombre = "Cueva Encantada";
        this.dragonDerrotado = false;
        this.conversacionInicialRealizada = false;
    }

    /**
     * Método principal para explorar la cueva
     */
    public void explorar() {
        System.out.println("\nEstás en la entrada de la " + nombre + ".");
        System.out.println("Un aire gélido y maligno emana de su interior.");
        
        if(!conversacionInicialRealizada) {
            interactuarConNPC();
            conversacionInicialRealizada = true;
        }
        
        if(!dragonDerrotado) {
            System.out.println("\n¿Quieres adentrarte en la cueva para enfrentar al dragón? (s/n)");
            boolean decision = true; // Siempre se enfrenta al dragón 
            
            if(decision) {
                iniciarBatallaDragon();
            } else {
                System.out.println("Decides que no es el momento adecuado...");
            }
        } else {
            System.out.println("\nLa cueva está en silencio. El dragón ha sido derrotado.");
        }
    }

    /**
     * Interacción con el NPC que advierte sobre el peligro
     */
    private void interactuarConNPC() {
        System.out.println("\nUn guardia maltrecho se acerca cojeando:");
        System.out.println("'¡No deberías estar aquí! ¡Es un lugar maldito!'");
        System.out.println("'Muchos guerreros han muerto intentando acabar con lo que habita aquí...'");
        System.out.println("'Ni siquiera con esa espada podrás vencerlo...'");
        System.out.println("El guardia señala hacia las profundidades de la cueva con temor.");
    }

    /**
     * Método para iniciar la batalla contra el dragón
     * (Implementación básica - puedes completar con tu sistema de combate)
     */
    private void iniciarBatallaDragon() {
        System.out.println("\nTe adentras en las profundidades de la cueva...");
        System.out.println("De repente, un enorme dragón emerge de la oscuridad!");
        
        // Aquí va el sistema de combate
        
        // Simulamos una victoria para el ejemplo
        this.dragonDerrotado = true;
        
        System.out.println("\n¡Después de una épica batalla, logras derrotar al dragón!");
        System.out.println("La cueva comienza a temblar mientras el dragón exhala su último aliento.");
    }

    /**
     * Verifica si el dragón ha sido derrotado
     */
    public boolean isDragonDerrotado() {
        return dragonDerrotado;
    }

    @Override
    public String toString() {
        return nombre + " - " +
               (dragonDerrotado ? "Dragón derrotado" : "Dragón presente");
    }
}
