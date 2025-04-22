public class ClaroDelBosque extends Escenario {
    private String nombre;
    private boolean fuenteDescubierta;

    public ClaroDelBosque() {
        super();
        this.setTieneNPC(true);  // Podría haber un NPC
        this.setPermiteLucha(false);
        this.setInteractuable(true);
        this.nombre = "Claro del Bosque";
        this.fuenteDescubierta = false;
    }

    public void explorar() {
        System.out.println("\nEstás en el " + nombre + ". Un círculo perfecto de hierba rodeado de altos árboles.");
        
        if(!fuenteDescubierta) {
            System.out.println("En el centro descubres una pequeña fuente de aguas cristalinas.");
            this.fuenteDescubierta = true;
        } else {
            System.out.println("La fuente sigue emanando su agua cristalina en el centro del claro.");
        }
    }

    @Override
    public String toString() {
        return nombre + " - " +
               (fuenteDescubierta ? "Fuente descubierta" : "Fuente no descubierta");
    }
}
