----
Разминка.

Написать метод, который разворачивает строку в обратном порядке и замерить время работы этого метода на 1000, 10 000,
100 000 повторений. Оформить надо в виде stand alone java приложения с консольным вводом строки. Результатом работы
должны быть: строка, развернутая строка и 3 цифры (время работы).
---

Решение представлено в двух вариантах - упрощенном и немного усложненном (с разбиением ответственности на целевые классы
и применением паттерна Команда). Наиболее оптимальным методом для переворота строки был выбран

     new StringBuffer(in).reverse().toString()

(При использовании в многопоточном приложении следует заменить на StringBuffer)
Для примера также добавлен менее оптимальный алгоритм.

Замеры времени работы реверсивного метода осуществляются с помощью

    System.nanoTime()

(Не потокобезопасен, но точнее System.currentTimeMillis())
С последующим преобразованием в миллисекунды посредством методов класса BigDecimal (без округления).

*Compile and run commands (from the root of the project):*

    javac -sourcepath ./src -d bin src/main/java/ru/example/reverse/v1/StringReverser.java
    java -classpath ./bin main.java.ru.example.reverse.v1.StringReverser

    javac -sourcepath ./src -d bin src/main/java/ru/example/reverse/v2/SimpleStringReverser.java
    java -classpath ./bin main.java.ru.example.reverse.v2.SimpleStringReverser


*Create jar (from bin)*

    jar cfe StringReverser.jar src.main.java.ru.example.reverse.v1.StringReverser ./bin/main/java/ru/example/reverse/v1/StringReverser.class
    jar cfe SimpleStringReverser.jar main.java.ru.example.reverse.v2.SimpleStringReverser ./bin/main/java/ru/example/reverse/v2/SimpleStringReverser.class


    jar cfe SimpleStringReverser.jar main.java.ru.example.reverse.v2.SimpleStringReverser src/main/java/ru/example/reverse/v2/SimpleStringReverser.java

*Add manifest*

    jar cvfm StringReverser.jar ./src/main/java/ru/example/reverse/v1/META-INF/MANIFEST.MF ./bin/main/java/ru/example/reverse/v1/StringReverser.class
    jar cvfm SimpleStringReverser.jar ./src/main/java/ru/example/reverse/v2/META-INF/MANIFEST.MF ./bin/main/java/ru/example/reverse/v2/SimpleStringReverser.class


    jar cvfm SimpleStringReverser.jar src/main/java/ru/example/reverse/v2/META-INF/MANIFEST.MF bin/main/java/ru/example/reverse/v2/SimpleStringReverser.class

*Run jar*

    java -jar StringReverser.jar
    java -jar SimpleStringReverser.jar