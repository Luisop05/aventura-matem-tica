package adventuremath;

import java.util.List;
import java.util.Random;

/**
 * Representa un banco de preguntas.
 * Esta clase almacena una lista de preguntas y proporciona un método para obtener una pregunta aleatoria.
 */
public class QuestionBank {
    private List<Question> questions;
    private Random random;

    /**
     * Constructor que inicializa el banco de preguntas con una lista personalizada de preguntas.
     *
     * @param customQuestions La lista de preguntas a agregar al banco.
     */
    public QuestionBank(List<Question> customQuestions) {
        this.questions = customQuestions;
        this.random = new Random();
    }

    /**
     * Obtiene una pregunta aleatoria del banco.
     *
     * @return Una pregunta aleatoria, o null si no hay preguntas disponibles.
     */
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }
}
