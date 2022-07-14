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

*Compile and run commands (from the project root to the "target" directory):*

    ***For App Version 1:***

    javac -sourcepath ./src -d target src/main/java/ru/example/reverse/v1/*.java &&\
    cd target && jar cfm StringReverser-v1-0.0.1-SNAPSHOT.jar ../src/main/java/ru/example/reverse/v1/META-INF/MANIFEST.MF ru/example/reverse/v1/*.class &&\
    java -jar StringReverser-v1-0.0.1-SNAPSHOT.jar

    ***For App Version 2:***
    cd .. &&\
    javac -sourcepath ./src -d target src/main/java/ru/example/reverse/v2/*.java &&\
    cd target && jar cfm SimpleStringReverser-v2-0.0.1-SNAPSHOT.jar ../src/main/java/ru/example/reverse/v2/META-INF/MANIFEST.MF ru/example/reverse/v2/*.class &&\
    java -jar SimpleStringReverser-v2-0.0.1-SNAPSHOT.jar
