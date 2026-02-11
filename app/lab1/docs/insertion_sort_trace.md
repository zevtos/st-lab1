# Пункт 2. Модульное тестирование алгоритма: сортировка вставками
## 1. Характерные точки
- OUTER — вход во внешнюю итерацию (выбор key)
- WHILE_TRUE — вход в цикл while при истинном условии (нужен сдвиг)
- SHIFT — выполнен сдвиг элемента вправо
- WHILE_FALSE — цикл while не выполнялся (key на месте)
- INSERT — вставка key (финальное присваивание)

## 2. Наборы входных данных и эталонные трейсы
A) []  
Trace: []

B) [1]  
Trace: []

C) [1,2,3]  
Trace: OUTER, WHILE_FALSE, INSERT, OUTER, WHILE_FALSE, INSERT

D) [3,2,1]  
Trace: OUTER, WHILE_TRUE, SHIFT, INSERT, OUTER, WHILE_TRUE, SHIFT, WHILE_TRUE, SHIFT, INSERT

E) [2,1,2]  
Trace: OUTER, WHILE_TRUE, SHIFT, INSERT, OUTER, WHILE_FALSE, INSERT

F) [-1,3,0,-1]  
Trace: OUTER, WHILE_FALSE, INSERT, OUTER, WHILE_TRUE, SHIFT, INSERT, OUTER, WHILE_TRUE, SHIFT, WHILE_TRUE, SHIFT, INSERT

Реализация: ru.itmo.lab1.sort.InsertionSort#sortWithTrace(int[]) возвращает List<TracePoint>.
Точки трассы заданы в enum ru.itmo.lab1.sort.TracePoint.

