import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Stack<Character> stack = new Stack<>();
        stack.push(' '); //заполняем стек
        int result = 1;
        boolean skobki = true;
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) { //получаем символ из строки
                case '(':
                case '[':
                case '{':
                    stack.push(line.charAt(i));
                    break;
                    case ')':
                        if ((stack.peek() != '(') || stack.empty()) { //проверяем последний символ в стеке
                            skobki = false;
                            break;
                        } else stack.pop(); //убираем символ
                        break;
                    case ']':
                        if ((stack.peek() != '[') || stack.empty()) {
                            skobki = false;
                            break;
                        } else stack.pop();
                        break;
                    case '}':
                        if ((stack.peek() != '{') || stack.empty()) {
                            skobki = false;
                            break;
                        } else stack.pop();
                        break;
                }
                if (!skobki) {
                    result = 0;
                    break;
                }
            }
        stack.pop(); //проверяем, пустой ли стек
        if (!stack.empty()) result = 0;
        if(result==1) System.out.print("YES");
        else System.out.print("NO");
    }
}
