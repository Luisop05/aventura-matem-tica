package adventuremath;

import javafx.application.Application; // Importa la clase Application de JavaFX para crear aplicaciones gráficas.
import javafx.stage.Stage; // Importa la clase Stage para representar una ventana de nivel superior.
import java.util.ArrayList; // Importa ArrayList para usar listas dinámicas.
import java.util.List; // Importa List para manejar listas en general.

/**
 * La clase `Main` es la clase principal de la aplicación de Adventure Math.
 * Extiende la clase `Application` de JavaFX para iniciar la aplicación gráfica.
 */
public class Main extends Application {
    /**
     * Este método se ejecuta cuando se inicia la aplicación.
     * Crea una nueva instancia de la clase `Game` y la inicia utilizando la ventana principal.
     *
     * @param primaryStage la ventana principal de la aplicación
     */
    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(); // Crea una nueva instancia de la clase `Game`
        game.start(primaryStage); // Inicia el juego usando la ventana principal
    }

    /**
     * El método `main` es el punto de entrada de la aplicación.
     * Llama al método `launch` de la clase `Application` para iniciar la aplicación JavaFX.
     *
     * @param args los argumentos de línea de comando (no se utilizan en este caso)
     */
    public static void main(String[] args) {
        launch(args); // Llama al método `launch` para iniciar la aplicación JavaFX
    }
}
