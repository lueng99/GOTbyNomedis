package JuegoDeDrogones;

public abstract class SerDeWesteros {
    private String nombre;
    private int energia, vida;

    public SerDeWesteros(String nombre) {
        this.nombre = nombre;
        energia = Constantes.ENERGIA_MAXIMA;
        vida = Constantes.VIDA_MAXIMA;
    }

    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = Math.max(0, Math.min(energia, Constantes.ENERGIA_MAXIMA)); }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, Constantes.VIDA_MAXIMA)); }
    public abstract void accionEspecial();
}
