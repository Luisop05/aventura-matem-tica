package adventuremath;

/**
 * Representa a un jugador en un juego.
 * Un jugador tiene un nombre y una cantidad de puntos.
 */
public class Player {
    /**
     * El nombre del jugador.
     */
    private String name;
    /**
     * Los puntos acumulados por el jugador.
     */
    private int points;

    /**
     * Constructor que inicializa un jugador con un nombre.
     *
     * @param name El nombre del jugador.
     */
    public Player(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede ser nulo o vacío.");
        }
        this.name = name;
        this.points = 0;
    }

    /**
     * Devuelve el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la cantidad de puntos del jugador.
     *
     * @return La cantidad de puntos del jugador.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Añade puntos al jugador.
     *
     * @param points La cantidad de puntos a añadir.
     * @throws IllegalArgumentException si se intenta añadir una cantidad negativa de puntos.
     */
    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("No se pueden añadir puntos negativos.");
        }
        this.points += points;
    }
}
