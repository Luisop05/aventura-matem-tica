package adventuremath; // Declaración del paquete.

import javafx.scene.Scene; // Importa la clase Scene para manejar la interfaz gráfica.
import javafx.scene.control.Button; // Importa la clase Button para crear botones.
import javafx.scene.control.Label; // Importa la clase Label para mostrar texto en la interfaz.
import javafx.scene.control.TextField; // Importa la clase TextField para permitir entrada de texto.
import javafx.scene.layout.GridPane; // Importa GridPane para un layout en cuadrícula.
import javafx.stage.Stage; // Importa la clase Stage.

import java.util.ArrayList; // Importa ArrayList para usar listas dinámicas.
import java.util.Collections; // Importa Collections para mezclar preguntas.
import java.util.List; // Importa List para manejar listas en general.

// Clase Game que maneja la lógica del juego.
public class Game {
    private List<Player> players; // Lista de jugadores.
    private int currentPlayerIndex; // Índice del jugador actual.
    private Question currentQuestion; // Pregunta actual.
    private int maxQuestions = 10; // Número máximo de preguntas por jugador.
    private int[] questionsAnswered; // Contador de respuestas dadas por cada jugador.
    private List<String> wrongAnswers; // Lista de respuestas incorrectas.
    private Stage primaryStage; // Stage para la ventana principal.

    // Constructor que inicializa el juego.
    public Game() {
        players = new ArrayList<>(); // Crea la lista de jugadores.
        players.add(new Player("Jugador 1")); // Añade el primer jugador.
        players.add(new Player("Jugador 2")); // Añade el segundo jugador.
        currentPlayerIndex = 0; // Inicializa el índice del jugador actual.
        questionsAnswered = new int[2]; // Crea un arreglo para contar las preguntas respondidas por cada jugador.
        wrongAnswers = new ArrayList<>(); // Inicializa la lista de respuestas incorrectas.
    }

    // Método que inicia el juego.
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Asigna el primaryStage a la variable de clase.

        primaryStage.setTitle("Aventura Matemática"); // Cambia el título de la ventana.

        // Etiquetas de la interfaz.
        Label currentPlayerLabel = new Label("Jugador 1 es tu turno!");
        Label questionLabel = new Label("Presiona 'Enviar Respuesta' para comenzar el juego.");
        Label pointsLabel = new Label("Puntos: 0");
        Label questionCountLabel = new Label("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex]));

        TextField answerField = new TextField(); // Campo de texto para ingresar respuestas.
        answerField.setPromptText("Introduce tu respuesta"); // Indica al usuario qué debe ingresar.

        Button submitButton = new Button("Enviar Respuesta"); // Botón para enviar respuesta.
        
        // Obtener la pregunta inicial.
        currentQuestion = getRandomQuestion(); 
        questionLabel.setText(currentQuestion.getQuestion()); // Muestra la primera pregunta.

        // Acción para el botón de enviar respuesta.
        submitButton.setOnAction(e -> {
            processAnswer(answerField, questionLabel, currentPlayerLabel, pointsLabel, questionCountLabel); // Procesa la respuesta.
        });

        // Agregar acción al campo de texto para detectar Enter.
        answerField.setOnAction(e -> {
            processAnswer(answerField, questionLabel, currentPlayerLabel, pointsLabel, questionCountLabel); // Procesa la respuesta al presionar Enter.
        });

        // Layout de la ventana utilizando GridPane.
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;"); // Color de fondo y padding.

        // Añadiendo elementos al GridPane
        layout.add(currentPlayerLabel, 0, 0); // Fila 0
        layout.add(questionLabel, 0, 1); // Fila 1
        layout.add(pointsLabel, 0, 2); // Fila 2
        layout.add(questionCountLabel, 0, 3); // Fila 3
        layout.add(answerField, 0, 4); // Fila 4
        layout.add(submitButton, 0, 5); // Fila 5

        Scene scene = new Scene(layout, 600, 400); // Establece dimensiones de la ventana.
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Agrega el CSS.

        primaryStage.setScene(scene); // Muestra la escena.
        primaryStage.show(); // Abre la ventana.
    }

    // Método para obtener una pregunta aleatoria del conjunto de preguntas.
    private Question getRandomQuestion() {
        List<Question> questions = getSampleQuestions(); // Obtiene las preguntas desde el método getSampleQuestions.
        Collections.shuffle(questions); // Mezcla las preguntas.
        return questions.get(0); // Retorna una pregunta aleatoria.
    }

    // Método para procesar la respuesta del jugador.
    private void processAnswer(TextField answerField, Label questionLabel, Label currentPlayerLabel, Label pointsLabel, Label questionCountLabel) {
        if (questionsAnswered[currentPlayerIndex] < maxQuestions) { // Verifica si aún hay preguntas restantes.
            String answerText = answerField.getText(); // Obtiene la respuesta ingresada.
            if (!answerText.isEmpty()) { // Verifica que no esté vacío.
                boolean isAnswerCorrect = false; // Inicializa la variable de respuesta correcta.

                // Trata de manejar la respuesta según el tipo.
                try {
                    int answer = Integer.parseInt(answerText); // Intenta convertir la respuesta a entero.
                    isAnswerCorrect = currentQuestion.checkAnswer(answer); // Verifica si la respuesta es correcta.
                } catch (NumberFormatException ex) { // Captura el error de conversión.
                    answerField.clear();
                    answerField.setPromptText("Introduce un número válido."); // Indica al usuario que ingrese un valor correcto.
                    return; // Termina el método si hay un error.
                }

                if (isAnswerCorrect) { // Si la respuesta es correcta.
                    int currentPoints = Integer.parseInt(pointsLabel.getText().split(" ")[1]) + currentQuestion.getPointsValue();
                    pointsLabel.setText("Puntos: " + currentPoints); // Actualiza la etiqueta de puntos.
                } else {
                    // Agrega la respuesta incorrecta a la lista.
                    wrongAnswers.add("Respuesta incorrecta: " + answerText + " para la pregunta: " + currentQuestion.getQuestion());
                }

                questionsAnswered[currentPlayerIndex]++; // Incrementa el contador de preguntas respondidas.
                answerField.clear(); // Limpia el campo de respuesta.

                // Actualiza el conteo de preguntas restantes.
                questionCountLabel.setText("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex]));

                // Si hay más preguntas, obtiene la siguiente.
                if (questionsAnswered[currentPlayerIndex] < maxQuestions) {
                    currentQuestion = getRandomQuestion(); // Obtiene la nueva pregunta.
                    questionLabel.setText(currentQuestion.getQuestion()); // Muestra la nueva pregunta.
                } else { 
                    // Cambia al siguiente jugador.
                    changePlayer(currentPlayerLabel, questionLabel, pointsLabel, questionCountLabel); // Llama al método para cambiar de jugador.
                }
            }
        } else {
            finishGame(); // Si se acabaron las preguntas, finaliza el juego.
        }
    }

    // Método para cambiar de jugador.
    private void changePlayer(Label currentPlayerLabel, Label questionLabel, Label pointsLabel, Label questionCountLabel) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Cambia al siguiente jugador en la lista.
        currentPlayerLabel.setText("Jugador " + (currentPlayerIndex + 1) + " es tu turno!"); // Muestra el nombre del jugador actual.
        pointsLabel.setText("Puntos: 0"); // Resetea datos de puntos.
        questionCountLabel.setText("Preguntas restantes: " + (maxQuestions - questionsAnswered[currentPlayerIndex])); // Actualiza el conteo de preguntas restantes.
        
        if (questionsAnswered[currentPlayerIndex] < maxQuestions) {
            currentQuestion = getRandomQuestion(); // Obtiene la primera pregunta del nuevo jugador.
            questionLabel.setText(currentQuestion.getQuestion()); // Muestra la nueva pregunta.
        } else {
            finishGame(); // Si el nuevo jugador no tiene preguntas restantes, finaliza el juego.
        }
    }

    // Método para finalizar el juego.
    private void finishGame() {
        StringBuilder result = new StringBuilder("Fin del juego!\n\n"); // Crea una cadena para mostrar resultados finales.
        for (Player player : players) { // Recorre los jugadores y sus puntos.
            result.append(player.getName()).append(" - Puntos: ").append(player.getPoints()).append("\n"); // Agrega nombre y puntos.
        }

        // Determinar quién ganó.
        int player1Points = players.get(0).getPoints();
        int player2Points = players.get(1).getPoints();
        if (player1Points > player2Points) {
            result.append("¡Ganó Jugador 1!");
        } else if (player2Points > player1Points) {
            result.append("¡Ganó Jugador 2!");
        } else {
            result.append("¡Es un empate!");
        }

        Label resultLabel = new Label(result.toString()); // Crea una etiqueta para mostrar resultados.

        // Botón para iniciar un nuevo juego.
        Button newGameButton = new Button("Iniciar Nuevo Juego");
        newGameButton.setOnAction(e -> startNewGame()); // Llama a startNewGame cuando se presiona el botón.

        // Layout para mostrar los resultados.
        GridPane layout = new GridPane();
        layout.add(resultLabel, 0, 0); // Añade la etiqueta de resultados.
        layout.add(newGameButton, 0, 1); // Añade el botón para iniciar un nuevo juego.
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;"); // Color de fondo y padding.

        Scene scene = new Scene(layout, 600, 400); // Establece las dimensiones de la escena de finalización.
        
        // Aplica el CSS a la escena de resultados
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Agrega el CSS.

        primaryStage.setScene(scene); // Muestra la escena con los resultados.
        primaryStage.setTitle("Resultados"); // Cambia el título de la ventana.
        primaryStage.show(); // Abre la ventana con los resultados.
    }

    // Método para iniciar un nuevo juego.
    private void startNewGame() {
        currentPlayerIndex = 0; // Reinicia el índice del jugador actual.
        questionsAnswered = new int[2]; // Reinicia el contador de preguntas respondidas para 2 jugadores.
        wrongAnswers.clear(); // Limpia la lista de respuestas incorrectas.

        // Crear un nuevo conjunto de preguntas para cada jugador y añadirlos a la lista de jugadores
        players.clear();
        players.add(new Player("Jugador 1")); // Nuevo jugador 1.
        players.add(new Player("Jugador 2")); // Nuevo jugador 2.

        // Reinicia el juego y muestra el estado inicial.
        start(primaryStage); // Llama al método start para iniciar el nuevo juego.
    }

    // Método para obtener preguntas de muestra.
    private List<Question> getSampleQuestions() {
        List<Question> customQuestions = new ArrayList<>();
        
        // Preguntas más difíciles
        customQuestions.add(new Question("¿Cuánto es 21 x 8?", 168, 20));
        customQuestions.add(new Question("¿Cuál es la raíz cuadrada de 144?", 12, 15));
        customQuestions.add(new Question("¿Qué es 7 elevado a la 3ra potencia?", 343, 25));
        customQuestions.add(new Question("¿Cuánto es 15 veces 15?", 225, 20));
        customQuestions.add(new Question("¿Cuánto es 12 dividido por 0.5?", 24, 20));
        customQuestions.add(new Question("Si x=3, ¿cuánto es 2x + 5?", 11, 20));
        customQuestions.add(new Question("¿Cuántas horas hay en un día?", 24, 10));
        customQuestions.add(new Question("¿Cuánto es 1000 dividido por 25?", 40, 10));
        customQuestions.add(new Question("¿Cuánto es 99 + 37?", 136, 15));

        return customQuestions; // Retorna la lista de preguntas.
    }
}




