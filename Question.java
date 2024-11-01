package adventuremath; // Declaración del paquete.

public class Question { // Clase Question que representa una pregunta.
    private String questionText; // Texto de la pregunta.
    private int correctAnswer; // Respuesta correcta (debe ser un entero).
    private int pointsValue; // Valor en puntos de la pregunta.
    
    // Constructor que inicializa la pregunta.
    public Question(String questionText, int correctAnswer, int pointsValue) {
        this.questionText = questionText; // Asigna el texto de la pregunta.
        this.correctAnswer = correctAnswer; // Asigna la respuesta correcta.
        this.pointsValue = pointsValue; // Asigna el valor en puntos.
    }

    public String getQuestion() { // Método que devuelve el texto de la pregunta.
        return questionText;
    }

    public int getPointsValue() { // Método para obtener el valor en puntos.
        return pointsValue;
    }

    public boolean checkAnswer(int answer) { // Método para verificar si la respuesta es correcta.
        return answer == correctAnswer; // Devuelve verdadero si la respuesta del jugador es igual a la respuesta correcta.
    }
}
