***
Разминка.

Написать метод, который разворачивает строку в обратном порядке и замерить время работы этого метода на 1000, 10 000,
100 000 повторений.<br> 
Оформить надо в виде stand alone java приложения с консольным вводом строки.<br> 
Результатом работы
должны быть: строка, развернутая строка и 3 цифры (время работы).
***

Решение представлено в двух вариантах - упрощенном и немного усложненном (с разбиением ответственности на целевые классы
и применением паттерна Команда).<br> 

Наиболее оптимальным методом для переворота строки (помимо представленного алгоритма итерации со сложностью O(n)) также можно предложить

     new StringBuilder(in).reverse().toString()

(При использовании в многопоточном приложении следует заменить на StringBuffer)<br>

Замеры времени работы реверсивного метода осуществляются с помощью

    System.nanoTime()

(Не потокобезопасен, но точнее, чем System.currentTimeMillis())<br>
С последующим преобразованием в миллисекунды посредством методов класса BigDecimal (без округления).

*Compile and run commands (from the root of the project):*

    // For App Version 1:
    javac -sourcepath ./src -d bin src/main/java/ru/example/reverse/v1/*.java
    java -classpath ./bin ru.example.reverse.v1.StringReverser

    //For App Version 2:
    javac -sourcepath ./src -d bin src/main/java/ru/example/reverse/v2/SimpleStringReverser.java
    java -classpath ./bin ru.example.reverse.v2.SimpleStringReverser

*Create jar*

    // For App Version 1:
    jar cfe StringReverser.jar ru.example.reverse.v1.StringReverser src/main/java/ru/example/reverse/v1/*.java
    //For App Version 2:
    jar cfe SimpleStringReverser.jar ru.example.reverse.v2.SimpleStringReverser src/main/java/ru/example/reverse/v2/*.java

*Add manifest*

    //For App Version 1:
    jar cvfm StringReverser.jar src/main/java/ru/example/reverse/v1/META-INF/MANIFEST.MF src/main/java/ru/example/reverse/v1/*.java
    //For App Version 2:
    jar cvfm SimpleStringReverser.jar src/main/java/ru/example/reverse/v2/META-INF/MANIFEST.MF src/main/java/ru/example/reverse/v2/*.java

*Run jar*
    
    //For App Version 1:
    java -jar StringReverser.jar
    java -cp StringReverser.jar  src/main/java/ru/example/reverse/v1/StringReverser.java

    //For App Version 2:
    java -jar SimpleStringReverser.jar
    java -cp SimpleStringReverser.jar src/main/java/ru/example/reverse/v2/SimpleStringReverser.java
