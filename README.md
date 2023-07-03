# Expression Solver

---

Нужно реализовать класс ExpressionParser с методом
    Expression parse(String expr)
где Expression - это интерфейс с методом
    double execute(Map<String, Double> params) - вычисляет значения выражения для переданных парметров. 
    
Выражения вида: (x + 5) / 10 * ((z + 5) - (y / 5))
Пример параметров: Map.of("x", 1, "y", 2, "z", 3)

Проект делать на базе jdk11, желательно использовать maven для сборки.