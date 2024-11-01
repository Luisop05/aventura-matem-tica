package adventuremath;

import javafx.application.Application; // Importa la clase Application de JavaFX para crear aplicaciones gráficas.
import javafx.stage.Stage; // Importa la clase Stage para representar una ventana de nivel superior;
import java.util.ArrayList; // Importa ArrayList para usar listas dinámicas;
import java.util.List; // Importa List para manejar listas en general;

public class Main extends Application { // Clase principal que extiende Application.
    @Override
    public void start(Stage primaryStage) { // Método que se ejecuta al iniciar la aplicación.
        Game game = new Game(); // Crea una nueva instancia de Game sin preguntas personalizadas.
        game.start(primaryStage); // Inicia el juego usando la ventana principal.
    }

    public static void main(String[] args) { // Método principal que arranca la aplicación.
        launch(args); // Llama al método launch para iniciar JavaFX.
    }
}
