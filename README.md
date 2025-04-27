# Juego de Drogones 🐲🍍

*Juego de Drogones* es un RPG simple hecho en Java donde controlas a un caballero que debe explorar un laberinto, recoger objetos, luchar contra enemigos y vencer a un dragón para escapar.

---

##  ¿Cómo se juega?

- *Moverse:*  
  Usa las teclas W, A, S, D o las *flechas del teclado* para moverte.
  
- *Objetivo:*  
  Encuentra la *espada legendaria* ⚔ para poder derrotar al *Dragón final* 🐉.  
  Después, escapa a través de la *puerta de salida* 🚪.

- *Interfaz (HUD):*  
  A la izquierda tienes tu *vida* (verde), *energía* (azul) y si tu *ataque* está potenciado (con espada).

- *Batallas:*  
  Cuando entres en contacto con enemigos, automáticamente lucharás turno por turno.

- *Cofres:*  
  Encuentra cofres 🎁 que restauran vida, energía o mejoran tu ataque.

---

##  ¿Cómo está hecho?

- *Lenguaje:* Java 8+
- *Librería gráfica:* Java Swing
- *Organización:*  
  Cada clase (Caballero, Dragon, Cofre, etc.) está separada en su propio archivo para modularidad y claridad.

---

##  Estructura del Proyecto

```bash
JuegoDeDrogones/
 ├── Caballero.java
 ├── Cofre.java
 ├── Constantes.java
 ├── Dragon.java
 ├── Espada.java
 ├── IBatallar.java
 ├── Juego.java      # Motor principal del juego
 ├── Main.java       # Punto de entrada
 ├── Muro.java
 ├── NPC.java
 ├── PuertaSalida.java
 ├── SerDeWesteros.java
 └── TipoCofre.java 
