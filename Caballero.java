package JuegoDeDrogones;

import java.util.Random;

public class Caballero extends SerDeWesteros implements IBatallar {
    private Random rand = new Random();
    private boolean tieneEspada = false;

    public Caballero(String nombre) { super(nombre); }

    public void accionEspecial() {
        setEnergia(getEnergia() - 15);
    }

    public void atacar(SerDeWesteros otro) {
        int danio = rand.nextInt(16) + 10;
        if (tieneEspada) danio += 20;
        otro.setVida(otro.getVida() - danio);
    }

    public void recogerEspada() { tieneEspada = true; }
    public boolean tieneEspada() { return tieneEspada; }
}
