import java.util.Stack;

public class Calculator {
    public static String ExpressionToRPN(String Expr) {// это для того чтобы предложение переводить в ОПЗ
        String current = ""; //стринг , в который будет записываться обратная польская запись
        Stack<Character> stack = new Stack<>(); //связка для математических знаков
        int priority; //приоритет

        for (int i = 0; i < Expr.length(); i++) { //для каждого элемента предложения
            priority = getPriority(Expr.charAt(i)); //обозначить приоритет
            if (priority == 0) current += Expr.charAt(i); //Если приоритет равен нулю - то есть элемент - число, просто добавить число в current
            if (priority == 1) stack.push(Expr.charAt(i));//Если приоритет равен 1, то есть элемент - "(", запихать ее в связку
            if (priority > 1) { //если приоритет больше 1, то есть элемент - матиматический знак
                current += " "; //сначала добавить пробел, чтобы в выражении чтоб после всего дерьма что у нас уже в кюренте был пробел
                while (!stack.empty()) { //если в связке есть какой нибудь знак
                    if (getPriority(stack.peek()) >= priority) { // и приоритет самого верхнего знака больше или равно приоритету текущего знака который мы собрались запихнуть
                        current += stack.pop(); //берем этот верхний знак из связки и добавляем к кьюренту
                    } else break; // в остальных случаях забиваем
                }
                stack.push(Expr.charAt(i));  // засовываем в стек текущий элемент
            }
            if (priority == -1) { //если приоритет равен -1, то есть элемент - это ")"
                current += " "; // добавляем к кьюренту пробел, по красоте
                while (getPriority(stack.peek()) != 1) current += stack.pop(); //пока верхний элемент связки не равен открывающей скобке "("
                stack.pop(); //берем верхний элемент
            }

        }

        while (!stack.isEmpty()) current += stack.pop(); //пока связка не станет пустой добавляем все что берем из связки в кьюрент


        return current; // метод возвращает нам секси ОПЗ
    }

    public static double RPNtoAnswer(String rpn) { // делаем из ОПЗ нормальный ответ
        String operand = new String(); //добавляем операнд, то бишь строку с числами.
        Stack<Double> stack = new Stack<>();//создаем связку, работаем с дабл для божественности кода
        for (int i = 0; i < rpn.length(); i++) { //проходимся по всем элементам ОПЗ
            if(rpn.charAt(i) == ' ')continue; //если в нашей ОПЗ пробел, идем дальше, гоз гоймуруг
            if (getPriority(rpn.charAt(i)) == 0) {// если наткнулись на цифру(приоритет -0) (если нам попалось число оно обрабатывается тут
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) { //пока мы не встретим пробел и пока все еще попадаются цифры(все еще одно число)
                    operand += rpn.charAt(i++); //добавляем в наш стринг любое что разделено пробелом и является числом
                    if (i == rpn.length()) break; // если мы дошли до конца, стоп детка,хорош
                }
                stack.push(Double.parseDouble(operand)); //парсим то что только что было добавлено в дабл и добавляем в связку
                operand = new String(); //новый операнд, короче очищаем операнд.
            }
            if ((getPriority(rpn.charAt(i)) > 1)){ // если нам попадаются знаки они обрабатываются в этом иф

                double a = stack.pop(), b = stack.pop(); // берем а - верхнее число из связки и б -следующее верхнее число
                if(rpn.charAt(i) == '+' )stack.push(b+a); // если в ОПЗ попался плюс, плюсуем верхние элементы связки, и ставим на место получившееся, то есть теперь у нас а = сумма а и б.
                if(rpn.charAt(i) == '-' )stack.push(b-a);// описываться не буду все с остальными понятно
                if(rpn.charAt(i) == '*' )stack.push(b*a);
                if(rpn.charAt(i) == '/' )stack.push(b/a);
            }
        }
        return stack.pop(); // метод возвращает нам долгожданный и дегендарый ответ
    }

    private static int getPriority(char token) { // тут расписан метод определения приоритетов
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;

    }
}
