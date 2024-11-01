package adventuremath; // Declaración del paquete.

import java.util.List; // Importa List para el manejo de listas.
import java.util.Random; // Importa Random para generar números aleatorios.

public class QuestionBank { // Clase que representa un banco de preguntas.
    private List<Question> questions; // Lista de preguntas.
    private Random random; // Generador de números aleatorios.

    // Constructor que inicializa el banco de preguntas.
    public QuestionBank(List<Question> customQuestions) {
        this.questions = customQuestions; // Asigna las preguntas al banco.
        this.random = new Random(); // Inicializa el generador de números aleatorios.
    }

    public Question getRandomQuestion() { // Método que obtiene una pregunta aleatoria del banco.
        if (questions.isEmpty()) { // Verifica si no hay preguntas disponibles.
            return null; // Retorna nulo si no hay preguntas.
        }
        int index = random.nextInt(questions.size()); // Genera un índice aleatorio.
        return questions.get(index); // Devuelve la pregunta correspondiente al índice.
    }
}
