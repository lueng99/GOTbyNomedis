package JuegoDeDrogones;

import java.util.Random;

public class Dragon extends SerDeWesteros implements IBatallar {
    private Random rand = new Random();

    public Dragon(String nombre) { super(nombre); }

    public void accionEspecial() {
        setEnergia(getEnergia() - 20);
    }

    public void atacar(SerDeWesteros otro) {
        otro.setVida(otro.getVida() - (rand.nextInt(20) + 25));
    }
}
