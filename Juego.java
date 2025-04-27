package JuegoDeDrogones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Juego extends JFrame implements KeyListener {
    private JPanel panel;
    private CanvasJuego canvas;
    private Object[][] mapa;
    private Caballero jugador;
    private int x = 1, y = 1;
    private boolean dragonVivo = true;
    private boolean jugando = false;
    private Random rand = new Random();
    private Set<Object> entidadesHeridas = new HashSet<>();

    public Juego() {
        setTitle("Juego de Drogones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        canvas = new CanvasJuego();
        panel.add(canvas, BorderLayout.CENTER);
        add(panel);

        mapa = new Object[Constantes.MAPA_TAMANO][Constantes.MAPA_TAMANO];
        jugador = new Caballero("Jugador");

        inicializarMapa();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        pack();
    }

    private void inicializarMapa() {
        for (int i = 0; i < Constantes.MAPA_TAMANO; i++) {
            mapa[0][i] = new Muro();
            mapa[Constantes.MAPA_TAMANO - 1][i] = new Muro();
            mapa[i][0] = new Muro();
            mapa[i][Constantes.MAPA_TAMANO - 1] = new Muro();
        }
        for (int y = 2; y < Constantes.MAPA_TAMANO - 2; y += 2) {
            for (int x = 2; x < Constantes.MAPA_TAMANO - 2; x += 2) {
                mapa[y][x] = new Muro();
                int dir = rand.nextInt(4);
                int dx = (dir == 0 ? 1 : dir == 1 ? -1 : 0);
                int dy = (dir == 2 ? 1 : dir == 3 ? -1 : 0);
                if (x + dx > 0 && x + dx < Constantes.MAPA_TAMANO && y + dy > 0 && y + dy < Constantes.MAPA_TAMANO)
                    mapa[y + dy][x + dx] = new Muro();
            }
        }

        for (int i = 0; i < 40; i++) {
            ponerObjeto(new Cofre(TipoCofre.values()[rand.nextInt(3)]));
            ponerObjeto(new NPC("Bandido", "Bandido"));
            ponerObjeto(new NPC("Monstruo", "Monstruo"));
        }

        ponerObjeto(new Espada());
        mapa[Constantes.MAPA_TAMANO - 3][Constantes.MAPA_TAMANO - 3] = new Dragon("Balerion el Terror Negro");
        mapa[Constantes.MAPA_TAMANO - 2][Constantes.MAPA_TAMANO - 2] = new PuertaSalida();
    }

    private void ponerObjeto(Object obj) {
        int a, b;
        do {
            a = rand.nextInt(Constantes.MAPA_TAMANO);
            b = rand.nextInt(Constantes.MAPA_TAMANO);
        } while ((a == x && b == y) || mapa[b][a] != null);
        mapa[b][a] = obj;
    }

    private void moverJugador(int dx, int dy) {
        int nx = x + dx, ny = y + dy;
        if (nx < 0 || ny < 0 || nx >= Constantes.MAPA_TAMANO || ny >= Constantes.MAPA_TAMANO) return;
        Object obj = mapa[ny][nx];

        if (obj instanceof Muro) return;
        if (obj instanceof Cofre) { activarCofre((Cofre) obj); mapa[ny][nx] = null; }
        else if (obj instanceof Espada) { jugador.recogerEspada(); mostrar("¡Has encontrado la Espada Legendaria! ⚔️"); mapa[ny][nx] = null; }
        else if (obj instanceof NPC) { if (!batalla((NPC) obj)) perder(); mapa[ny][nx] = null; }
        else if (obj instanceof Dragon) { if (!batallaDragon((Dragon) obj)) perder(); mapa[ny][nx] = null; dragonVivo = false; }
        else if (obj instanceof PuertaSalida) { if (!dragonVivo) ganar(); else mostrar("¡Debes vencer al Dragón antes de escapar!"); return; }

        x = nx;
        y = ny;
        canvas.repaint();
    }

    private boolean batalla(NPC enemigo) {
        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            jugador.atacar(enemigo);
            herirEntidad(enemigo);
            if (enemigo.getVida() <= 0) {
                mostrar("¡Venciste a " + enemigo.getNombre() + "!");
                return true;
            }
            enemigo.atacar(jugador);
            herirEntidad(jugador);
            if (jugador.getVida() <= 0) return false;
        }
        return true;
    }

    private boolean batallaDragon(Dragon dragon) {
        if (!jugador.tieneEspada()) {
            mostrar("¡El dragón te incinera sin la espada legendaria!");
            return false;
        }
        while (jugador.getVida() > 0 && dragon.getVida() > 0) {
            jugador.atacar(dragon);
            herirEntidad(dragon);
            if (dragon.getVida() <= 0) {
                mostrar("¡Has derrotado al Dragón!");
                return true;
            }
            dragon.atacar(jugador);
            herirEntidad(jugador);
            if (jugador.getVida() <= 0) return false;
        }
        return true;
    }

    private void activarCofre(Cofre cofre) {
        if (cofre.tipo == TipoCofre.CURA) {
            jugador.setVida(jugador.getVida() + 30);
            mostrar("¡Cofre de curación! +30 vida");
        } else if (cofre.tipo == TipoCofre.ESCUDO) {
            jugador.setEnergia(jugador.getEnergia() + 30);
            mostrar("¡Cofre de energía! +30 energía");
        } else if (cofre.tipo == TipoCofre.ATAQUE) {
            jugador.accionEspecial();
            mostrar("¡Acción especial!");
        }
    }

    private void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void ganar() {
        JOptionPane.showMessageDialog(this, "¡Valiente " + jugador.getNombre() + ", has conquistado Drogones! 🎉");
        System.exit(0);
    }

    private void perder() {
        JOptionPane.showMessageDialog(this, "¡Has sido derrotado! Fin del juego...");
        System.exit(0);
    }

    private void herirEntidad(final Object entidad) {
        entidadesHeridas.add(entidad);
        canvas.repaint();
        new javax.swing.Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                entidadesHeridas.remove(entidad);
                canvas.repaint();
            }
        }).start();
    }

    public void keyPressed(KeyEvent e) {
        if (!jugando && e.getKeyCode() == KeyEvent.VK_ENTER) {
            String nombre = JOptionPane.showInputDialog(this, "¿Cómo te llamas, caballero de Drogones?");
            if (nombre == null || nombre.trim().isEmpty()) nombre = "Jugador";
            jugador = new Caballero(nombre.trim());
            jugando = true;
            canvas.repaint();
            return;
        }
        if (!jugando) return;

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) moverJugador(0, -1);
        else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) moverJugador(0, 1);
        else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) moverJugador(-1, 0);
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) moverJugador(1, 0);
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    class CanvasJuego extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!jugando) {
                g.setFont(new Font("Arial", Font.BOLD, 32));
                g.drawString("Juego de Drogones", 300, 200);
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                g.drawString("Pulsa ENTER para comenzar", 280, 300);
                return;
            }
            int hudWidth = 200;
            int cs = Math.min((getWidth() - hudWidth) / Constantes.MAPA_TAMANO, getHeight() / Constantes.MAPA_TAMANO);
            g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, cs));

            for (int j = 0; j < Constantes.MAPA_TAMANO; j++) {
                for (int i = 0; i < Constantes.MAPA_TAMANO; i++) {
                    dibujar(g, i, j, cs, hudWidth);
                }
            }
            dibujarHUD(g, hudWidth);
        }

        private void dibujar(Graphics g, int i, int j, int cs, int hudWidth) {
            Object obj = mapa[j][i];
            String emoji = "";
            if (obj instanceof NPC) {
                NPC npc = (NPC) obj;
                if ("Bandido".equals(npc.tipo)) emoji = "🛡️";
                else if ("Monstruo".equals(npc.tipo)) emoji = "👹";
            } else if (obj instanceof Cofre) emoji = "🎁";
            else if (obj instanceof Espada) emoji = "⚔️";
            else if (obj instanceof Muro) emoji = "🧱";
            else if (obj instanceof Dragon) emoji = "🐉";
            else if (obj instanceof PuertaSalida) emoji = "🚪";

            if (i == x && j == y) { emoji = "🍍"; obj = jugador; }
            g.setColor(entidadesHeridas.contains(obj) ? Color.RED : Color.BLACK);
            g.drawString(emoji, hudWidth + i * cs, j * cs + cs);
        }

        private void dibujarHUD(Graphics g, int hudWidth) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, hudWidth, getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Jugador:", 10, 30);
            g.drawString(jugador.getNombre(), 10, 55);

            g.drawString("Vida:", 10, 95);
            g.setColor(Color.GREEN);
            g.fillRect(10, 100, jugador.getVida(), 15);

            g.setColor(Color.WHITE);
            g.drawString("Energía:", 10, 145);
            g.setColor(Color.CYAN);
            g.fillRect(10, 150, jugador.getEnergia(), 15);

            g.setColor(Color.WHITE);
            g.drawString("Ataque:", 10, 195);
            g.drawString(jugador.tieneEspada() ? "⚔️ Potenciado" : "Normal", 10, 220);
        }
    }
}
