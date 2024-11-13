package adventuremath;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La clase `Game` representa la lógica principal del juego de Adventure Math.
 * Se encarga del flujo del juego, incluido el manejo de los jugadores, las preguntas
 * y los resultados finales.
 */
public class Game {
    private List<Player> players; // Lista de jugadores
    private int currentPlayerIndex; // Índice del jugador actual
    private Question currentQuestion; // Pregunta actual
    private int maxQuestions = 10; // Número máximo de preguntas por jugador
    private int[] questionsAnswered; // Contador de preguntas respondidas por cada jugador
    private List<String> wrongAnswers; // Lista de respuestas incorrectas
    private Stage primaryStage; // Ventana principal del juego

    /**
     * Construye una nueva instancia de `Game` e inicializa el juego.
     */
    public Game() {
        players = new ArrayList<>(); // Crea la lista de jugadores
        players.add(new Player("Jugador 1")); // Agrega el primer jugador
        players.add(new Player("Jugador 2")); // Agrega el segundo jugador
        currentPlayerIndex = 0; // Inicializa el índice del jugador actual
        questionsAnswered = new int[2]; // Crea un arreglo para contar las preguntas respondidas por cada jugador
        wrongAnswers = new ArrayList<>(); // Inicializa la lista de respuestas incorrectas
    }

    /**
     * Inicia el juego y configura la ventana del juego.
     *
     * @param primaryStage la ventana principal del juego
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Asigna la ventana principal a la variable de clase
        primaryStage.setTitle("Adventure Math"); // Establece el título de la ventana

        // Crea las etiquetas para la interfaz del juego
        Label currentPlayerLabel = new Label("¡Es el turno del Jugador 1!");
        Label questionLabel = new Label("Presiona 'Enviar Respuesta' para comenzar el juego.");
        Label pointsLabel = new Label("Puntos: 0");
        Label questionCountLabel = new Label("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex]));

        TextField answerField = new TextField(); // Campo de texto para ingresar respuestas
        answerField.setPromptText("Introduce tu respuesta"); // Establece el texto de ayuda para el campo de respuesta

        Button submitButton = new Button("Enviar Respuesta"); // Botón para enviar la respuesta

        // Obtiene la pregunta inicial
        currentQuestion = getRandomQuestion();
        questionLabel.setText(currentQuestion.getQuestion()); // Muestra la primera pregunta

        // Acción para el botón de enviar respuesta
        submitButton.setOnAction(e -> {
            processAnswer(answerField, questionLabel, currentPlayerLabel, pointsLabel, questionCountLabel); // Procesa la respuesta
        });

        // Acción para el campo de texto de respuesta para detectar la tecla Enter
        answerField.setOnAction(e -> {
            processAnswer(answerField, questionLabel, currentPlayerLabel, pointsLabel, questionCountLabel); // Procesa la respuesta al presionar Enter
        });

        // Configura el diseño de la ventana del juego usando GridPane
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;"); // Establece el color de fondo y el relleno

        // Agrega los elementos al GridPane
        layout.add(currentPlayerLabel, 0, 0); // Fila 0
        layout.add(questionLabel, 0, 1); // Fila 1
        layout.add(pointsLabel, 0, 2); // Fila 2
        layout.add(questionCountLabel, 0, 3); // Fila 3
        layout.add(answerField, 0, 4); // Fila 4
        layout.add(submitButton, 0, 5); // Fila 5

        Scene scene = new Scene(layout, 600, 400); // Establece las dimensiones de la ventana
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Agrega la hoja de estilos CSS

        primaryStage.setScene(scene); // Establece la escena para la ventana principal
        primaryStage.show(); // Muestra la ventana del juego
    }

    /**
     * Obtiene una pregunta aleatoria del conjunto de preguntas de muestra.
     *
     * @return una instancia aleatoria de `Question`
     */
    private Question getRandomQuestion() {
        List<Question> questions = getSampleQuestions(); // Obtiene las preguntas de muestra
        Collections.shuffle(questions); // Mezcla las preguntas
        return questions.get(0); // Devuelve una pregunta aleatoria
    }

    /**
     * Procesa la respuesta del jugador.
     *
     * @param answerField          el campo de texto para la respuesta
     * @param questionLabel        la etiqueta para la pregunta actual
     * @param currentPlayerLabel   la etiqueta para el jugador actual
     * @param pointsLabel          la etiqueta para los puntos del jugador
     * @param questionCountLabel   la etiqueta para las preguntas restantes
     */
    private void processAnswer(TextField answerField, Label questionLabel, Label currentPlayerLabel, Label pointsLabel, Label questionCountLabel) {
        if (questionsAnswered[currentPlayerIndex] < maxQuestions) { // Verifica si aún hay preguntas restantes
            String answerText = answerField.getText(); // Obtiene el texto de la respuesta
            if (!answerText.isEmpty()) { // Verifica que la respuesta no esté vacía
                boolean isAnswerCorrect = false; // Inicializa la bandera para una respuesta correcta

                // Intenta manejar la respuesta según el tipo
                try {
                    int answer = Integer.parseInt(answerText); // Intenta convertir la respuesta a entero
                    isAnswerCorrect = currentQuestion.checkAnswer(answer); // Verifica si la respuesta es correcta
                } catch (NumberFormatException ex) { // Captura el error si la respuesta no es un número válido
                    answerField.clear();
                    answerField.setPromptText("Introduce un número válido."); // Indica al usuario que ingrese un valor numérico
                    return; // Termina el método si hay un error
                }

                if (isAnswerCorrect) { // Si la respuesta es correcta
                    int currentPoints = Integer.parseInt(pointsLabel.getText().split(" ")[1]) + currentQuestion.getPointsValue();
                    pointsLabel.setText("Puntos: " + currentPoints); // Actualiza la etiqueta de puntos
                } else {
                    // Agrega la respuesta incorrecta a la lista
                    wrongAnswers.add("Respuesta incorrecta: " + answerText + " para la pregunta: " + currentQuestion.getQuestion());
                }

                questionsAnswered[currentPlayerIndex]++; // Incrementa el contador de preguntas respondidas
                answerField.clear(); // Limpia el campo de respuesta

                // Actualiza el contador de preguntas restantes
                questionCountLabel.setText("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex]));

                // Si hay más preguntas, obtiene la siguiente
                if (questionsAnswered[currentPlayerIndex] < maxQuestions) {
                    currentQuestion = getRandomQuestion(); // Obtiene la nueva pregunta
                    questionLabel.setText(currentQuestion.getQuestion()); // Muestra la nueva pregunta
                } else {
                    changePlayer(currentPlayerLabel, questionLabel, pointsLabel, questionCountLabel); // Cambia al siguiente jugador
                }
            }
        } else {
            finishGame(); // Si se han respondido todas las preguntas, finaliza el juego
        }
    }

    /**
     * Cambia al siguiente jugador.
     *
     * @param currentPlayerLabel la etiqueta para el jugador actual
     * @param questionLabel      la etiqueta para la pregunta actual
     * @param pointsLabel        la etiqueta para los puntos del jugador
     * @param questionCountLabel la etiqueta para las preguntas restantes
     */
    private void changePlayer(Label currentPlayerLabel, Label questionLabel, Label pointsLabel, Label questionCountLabel) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Cambia al siguiente jugador en la lista
        currentPlayerLabel.setText("¡Es el turno del Jugador " + (currentPlayerIndex + 1) + "!"); // Actualiza la etiqueta del jugador actual
        pointsLabel.setText("Puntos: 0"); // Restablece la etiqueta de puntos
        questionCountLabel.setText("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex])); // Actualiza el contador de preguntas restantes

        if (questionsAnswered[currentPlayerIndex] < maxQuestions) {
            currentQuestion = getRandomQuestion(); // Obtiene la primera pregunta para el nuevo jugador
            questionLabel.setText(currentQuestion.getQuestion()); // Muestra la nueva pregunta
        } else {
            finishGame(); // Si el nuevo jugador no tiene más preguntas, finaliza el juego
        }
    }

    /**
     * Finaliza el juego y muestra los resultados finales.
     */
    private void finishGame() {
        StringBuilder result = new StringBuilder("¡Fin del juego!\n\n"); // Crea un StringBuilder para mostrar los resultados finales
        for (Player player : players) { // Itera a través de los jugadores y sus puntos
            result.append(player.getName()).append(" - Puntos: ").append(player.getPoints()).append("\n"); // Agrega el nombre y los puntos del jugador
        }

        // Determina al ganador
        int player1Points = players.get(0).getPoints();
        int player2Points = players.get(1).getPoints();
        if (player1Points > player2Points) {
            result.append("¡Ganó el Jugador 1!");
        } else if (player2Points > player1Points) {
            result.append("¡Ganó el Jugador 2!");
        } else {
            result.append("¡Es un empate!");
        }

        Label resultLabel = new Label(result.toString()); // Crea una etiqueta para mostrar los resultados

        // Botón para iniciar un nuevo juego
        Button newGameButton = new Button("Iniciar Nuevo Juego");
        newGameButton.setOnAction(e -> startNewGame()); // Llama al método startNewGame cuando se presiona el botón

        // Diseño para mostrar los resultados finales
        GridPane layout = new GridPane();
        layout.add(resultLabel, 0, 0); // Agrega la etiqueta de resultados
        layout.add(newGameButton, 0, 1); // Agrega el botón para iniciar un nuevo juego
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;"); // Establece el color de fondo y el relleno

        Scene scene = new Scene(layout, 600, 400); // Establece las dimensiones de la ventana de resultados
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Agrega la hoja de estilos CSS

        primaryStage.setScene(scene); // Establece la escena para la ventana principal
        primaryStage.setTitle("Resultados"); // Establece el título de la ventana
        primaryStage.show(); // Muestra la ventana de resultados finales
    }

    /**
     * Inicia un nuevo juego.
     */
    private void startNewGame() {
        currentPlayerIndex = 0; // Restablece el índice del jugador actual
        questionsAnswered = new int[2]; // Restablece el contador de preguntas respondidas para 2 jugadores
        wrongAnswers.clear(); // Limpia la lista de respuestas incorrectas

        // Crea un nuevo conjunto de preguntas para cada jugador y los agrega a la lista de jugadores
        players.clear();
        players.add(new Player("Jugador 1")); // Nuevo Jugador 1
        players.add(new Player("Jugador 2")); // Nuevo Jugador 2

        // Reinicia el juego y muestra el estado inicial
        start(primaryStage); // Llama al método start para comenzar un nuevo juego
    }

    /**
     * Obtiene las preguntas de muestra para el juego.
     *
     * @return una lista de instancias de `Question`
     */
    private List<Question> getSampleQuestions() {
        List<Question> customQuestions = new ArrayList<>();

        // Agrega preguntas más desafiantes
        customQuestions.add(new Question("¿Cuánto es 21 x 8?", 168, 20));
        customQuestions.add(new Question("¿Cuál es la raíz cuadrada de 144?", 12, 15));
        customQuestions.add(new Question("¿Qué es 7 elevado a la 3ra potencia?", 343, 25));
        customQuestions.add(new Question("¿Cuánto es 15 veces 15?", 225, 20));
        customQuestions.add(new Question("¿Cuánto es 12 dividido por 0.5?", 24, 20));
        customQuestions.add(new Question("Si x=3, ¿cuánto es 2x + 5?", 11, 20));
        customQuestions.add(new Question("¿Cuántas horas hay en un día?", 24, 10));
        customQuestions.add(new Question("¿Cuánto es 1000 dividido por 25?", 40, 10));
        customQuestions.add(new Question("¿Cuánto es 99 + 37?", 136, 15));

        return customQuestions; // Devuelve la lista de preguntas de muestra
    }
}



