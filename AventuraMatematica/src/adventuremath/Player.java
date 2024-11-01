package adventuremath; // Declaración del paquete.

public class Player { // Clase que representa a un jugador.

    private String name; // Nombre del jugador.
    private int points; // Puntos acumulados del jugador.

    // Constructor que inicializa un jugador con un nombre.
    public Player(String name) {
        this.name = name; // Asigna el nombre al jugador.
        this.points = 0; // Inicializa los puntos en 0.
    }

    public String getName() { // Método que devuelve el nombre del jugador.
        return name;
    }

    public int getPoints() { // Método que devuelve la cantidad de puntos del jugador.
        return points;
    }

    public void addPoints(int points) { // Método para añadir puntos al jugador.
        this.points += points; // Aumenta los puntos del jugador por la cantidad pasada.
    }
}
