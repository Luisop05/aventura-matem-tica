package adventuremath;

/**
 * Representa una pregunta en un juego.
 * Una pregunta tiene un texto, una respuesta correcta y un valor en puntos.
 */
public class Question {
    /**
     * El texto de la pregunta.
     */
    private String questionText;
    /**
     * La respuesta correcta a la pregunta.
     */
    private int correctAnswer;
    /**
     * El valor en puntos de la pregunta.
     */
    private int pointsValue;

    /**
     * Constructor que inicializa una pregunta.
     *
     * @param questionText  El texto de la pregunta.
     * @param correctAnswer La respuesta correcta a la pregunta.
     * @param pointsValue   El valor en puntos de la pregunta.
     */
    public Question(String questionText, int correctAnswer, int pointsValue) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.pointsValue = pointsValue;
    }

    /**
     * Devuelve el texto de la pregunta.
     *
     * @return El texto de la pregunta.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Devuelve la respuesta correcta a la pregunta.
     *
     * @return La respuesta correcta a la pregunta.
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Devuelve el valor en puntos de la pregunta.
     *
     * @return El valor en puntos de la pregunta.
     */
    public int getPointsValue() {
        return pointsValue;
    }

    /**
     * Verifica si una respuesta es correcta.
     *
     * @param answer La respuesta del jugador.
     * @return `true` si la respuesta es correcta, `false` en caso contrario.
     */
    public boolean checkAnswer(int answer) {
        return answer == correctAnswer;
    }
}
