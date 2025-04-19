public class Escenario {
    // Atributos según los requisitos
    private boolean tieneNPC;          // Indica si el escenario tiene personajes no jugadores
    private boolean permiteLucha;      // Indica si en el escenario se permite combatir
    private boolean interactuable;     // Indica si se puede interactuar con elementos del entorno

    // Constructor de la clase Escenario //
    public Escenario() {
        this.tieneNPC = true;      // Sí tiene NPCs (según requisito)
        this.permiteLucha = false; // No permite lucha (según requisito)
        this.interactuable = false; // No es interactuable (según requisito)
    }

    // Métodos getter para acceder a los atributos//

    //true si el escenario tiene NPCs, false en caso contrario//
    public boolean tieneNPC() {
        return tieneNPC;
    }

    // true si el escenario permite lucha, false en caso contrario
    public boolean permiteLucha() {
        return permiteLucha;
    }

    // true si el escenario es interactuable, false en caso contrario
    public boolean esInteractuable() {
        return interactuable;
    }

    // Métodos setter para modificar los atributos (aunque según requisitos son fijos)

    // Establece si el escenario tiene NPCs valor booleano
    public void setTieneNPC(boolean tieneNPC) {
        this.tieneNPC = tieneNPC;
    }

    // Establece si el escenario permite lucha valor booleano
    public void setPermiteLucha(boolean permiteLucha) {
        this.permiteLucha = permiteLucha;
    }

    // Establece si el escenario es interactuable valor booleano
    public void setInteractuable(boolean interactuable) {
        this.interactuable = interactuable;
    }

    // Método para mostrar la información básica del escenario con la descripción del escenario
    @Override
    public String toString() {
        return "Escenario base - " +
               "NPCs: " + (tieneNPC ? "Sí" : "No") + ", " +
               "Lucha: " + (permiteLucha ? "Sí" : "No") + ", " +
               "Interactuable: " + (interactuable ? "Sí" : "No");
    }
}