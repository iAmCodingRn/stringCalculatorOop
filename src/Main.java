
        import java.util.InputMismatchException;
        import java.util.Scanner;
//Задача: программа "Строковый калькулятор"
//        Описание:
//        Создайте консольное приложение "Строковый калькулятор".
//        Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль
//        результат их выполнения.
//
//        Требования:
//        Калькулятор умеет выполнять операции сложения строк, вычитания строки из строки, умножения строки на число и деления строки на число:
//        "a" + "b", "a" - "b", "a" * b, "a" / b. Данные передаются в одну строку(смотрите пример)! Решения, в которых каждая строка,
//        число и арифмитеческая операция передаются с новой строки считаются неверными.
//        Значения строк передаваемых в выражении выделяются двойными кавычками.
//        Результатом сложения двух строк, является строка состоящая из переданных
//        результатом деления строки на число n, является строка в n раз короче исходной (смотрите пример)
//        результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз
//        результатом вычетания строки из строки, является строка в которой удалена переданная подстрока или исходная строка,
//        если в нее нет вхожднеия вычитаемой строки
//        Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. И строки длинной не более 10 символов.
//        Если строка, полученная в результате работы приложения длинее 40 симовлов, то в выводе после 40 символа должны стоять три точки (...)
//        Калькулятор умеет работать только с целыми числами.
//        Первым аргументом выражения, подаваемого на вход, должна быть строка, при вводе пользователем выражения вроде 3 + "hello",
//        калькулятор должен выбросить исключение и прекратить свою работу.
//        При вводе пользователем неподходящих чисел, строк или неподдерживаемых операций (напирмер, деление строки на строку) приложение
//        выбрасывает исключение и завершает свою работу.
//        При вводе пользователем выражения не соответствующего одной из вышеописанных арифметических операций приложение выбрасывает исключение и
//        завершает свою работу.
//        Пример работы программы:
//        Input:
//        "100" + "200"
//
//        Output:
//        "100200"
//
//        Input:
//        "Hello World!" - "World!"
//
//        Output:
//        "Hello "
//
//        Input:
//        "Hi" * 5
//
//        Output:
//        "HiHiHiHiHi"
//
//        Input:
//        "Example Text!!!" / 3
//
//        Output:
//        "Exam"

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number;
    static char operation;
    static String result = "";

    private static void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    public static void main(String[] args) {
        while (true) {
            result = "";
            System.out.println("Введите выражение [\"a\" + \"b\", \"a\" - \"b\", \"a\" * x, \"a\" / x] где a и b - строки длиной не более " +
                    "10 символов а x - число  от 1 до 10 включительно  + [Enter] или " +
                    " [exit] для выхода");

            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                exit();
            }
            char[] uchar = new char[26];
            for (int i = 0; i < userInput.length(); i++) {
                uchar[i] = userInput.charAt(i);
                if (uchar[i] == '+') {
                    operation = '+';
                }
                if (uchar[i] == '-') {
                    operation = '-';
                }
                if (uchar[i] == '*') {
                    operation = '*';
                }
                if (uchar[i] == '/') {
                    operation = '/';
                }
            }

            String[] blocks = userInput.split("[+-/*\"]");

            if (blocks.length == 5) {
                String st00 = blocks[0];
                String st01 = blocks[1];
                String st04 = blocks[4];
                result = calculated(st01, st04, operation);
                if (result.length() > 40) {
                    String rez = result.substring(0, 40);
                    System.out.println(rez + "...");
                } else {
                    System.out.println(result);
                }
            } else {
                String st01 = blocks[1];
                String st03 = null;
                try {
                    st03 = blocks[3];
                } catch (ArrayIndexOutOfBoundsException e) {


                    System.out.println("Неверный формат данных");
                }
                try {
                    number = Integer.parseInt(st03);
                    result = calculated(st01, number, operation);
                } catch (NumberFormatException e) {

                    System.out.println("Неверный формат данных");
                }

                if (result.length() > 40) {
                    String rez = result.substring(0, 40);
                    System.out.println(rez + "...");
                } else {
                    System.out.println(result);
                }
            }
        }
    }


    public static String calculated(String num1, String num2, char op) {

        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                boolean cb = num1.contains(num2);
                if (cb) {
                    int resA = num1.length() - num2.length();
                    result = num1.substring(0, resA);
                } else {
                    result = num1;
                }
                if (cb & num1.length() == num2.length()) {
                    result = "0";
                }
                break;
            case '*':
                System.out.println("Неверный знак операции * (введите + или -)");
                break;
            case '/':
                System.out.println("Неверный знак операции / (введите + или -)");
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }

    public static String calculated(String num1, int num, char op) {

        switch (op) {
            case '+':
                System.out.println("Неверный знак операции + (введите * или /)");

                break;
            case '-':
                System.out.println("Неверный знак операции - (введите * или /)");
                break;
            case '*':
                for (int u = 0; u < num; u++) {
                    result = result + num1;
                }
                break;
//
            case '/':
                try {
                    int resB = num1.length() / num;
                    if (num1.length() == num) {
                        result = "1";
                    } else {
                        result = num1.substring(0, resB);
                    }
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                } finally {
                    if (num1.length() < num) {
                        System.out.println("Делимое меньше делителя");
                    }
                }
//
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}

