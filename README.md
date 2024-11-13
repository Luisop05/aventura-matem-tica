# Aventura Matemática

## Descripción
Aventura Matemática es un juego educativo diseñado para poner a prueba tus conocimientos matemáticos a través de una serie de preguntas. El objetivo es responder correctamente tantas preguntas como sea posible para acumular puntos.

## Requisitos

Antes de comenzar, asegúrate de tener instalado en tu máquina:

1. **Java Development Kit (JDK)**: Necesitarás JDK 11 o posterior.
2. **JavaFX**: Asegúrate de tener la biblioteca de JavaFX configurada en tu entorno de desarrollo.

## Instalación y Ejecución

### 1. Clonar el Repositorio

Para clonar el repositorio en tu máquina local, abre una terminal y ejecuta:

git clone https://github.com/Luisop05/aventura-matem-tica.git

### 2. Configurar JavaFX


Para Eclipse:
Crear Proyecto: Abre Eclipse y crea un nuevo proyecto Java.

Agregar JARs de JavaFX:

Haz clic derecho en tu proyecto y selecciona Build Path > Configure Build Path.
En la pestaña de Libraries, haz clic en Add External JARs... y selecciona todos los archivos .jar de la carpeta lib de JavaFX.
Configurar Opciones de Ejecución:

En Run Configurations, selecciona tu proyecto y en la pestaña VM Arguments, agrega:

--module-path "ruta/a/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml

### 3. Ejecutar el Juego
Para ejecutar el juego, simplemente ejecuta el archivo Main.java en tu IDE. Asegúrate de que todas las clases (Game.java, Player.java, Question.java) estén en el mismo paquete (adventuremath).

Ejemplo de Ejecución desde la Línea de Comandos
Si prefieres trabajar desde la línea de comandos, sigue estos pasos:

Abre una terminal y navega al directorio raíz del proyecto.

Compila todas las clases Java:

bash

javac src/adventuremath/*.java

Ejecuta el juego con el siguiente comando:

bash

java --module-path "ruta/a/javafx-sdk/lib" --add-modules javafx.controls -cp src adventuremath.Main
(Asegúrate de reemplazar "ruta/a/javafx-sdk/lib" con la ubicación real de tu carpeta lib de JavaFX.)



