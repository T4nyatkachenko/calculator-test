package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InputException {
        System.out.println("Калькулятор умеет выполнять операции с двумя числами: a + b, a - b, a * b, a / b.");
        while (true) {
            System.out.println("Введите выражение: ");
            String userInput = scanner.nextLine();
            System.out.println("Ответ: " + calc(userInput));
        }
    }

    public static String calc(String input) throws InputException {
        char operator = 0;
        int numb1;
        int numb2;
        String result;


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                operator = '+';
            }
            if (input.charAt(i) == '-') {
                operator = '-';
            }
            if (input.charAt(i) == '*') {
                operator = '*';
            }
            if (input.charAt(i) == '/') {
                operator = '/';
            }
        }


        String[] numbers = input.split("[+-/*]");
        if (numbers.length != 2) {
            throw new InputException("Строка не соответствует ни одной из вышеописанных арифметических операций");
        }
        String number1 = numbers[0];
        String number2 = numbers[1];


        if (isArabNumber(number1) && isArabNumber(number2)) { //если числа арабские
            numb1 = Integer.parseInt(number1);
            numb2 = Integer.parseInt(number2);
            if (!(numb1 >= 1 && numb1 <= 10)) {
                throw new InputException("Первое число должно быть от 1 до 10 включительно");
            }
            if (!(numb2 >= 1 && numb2 <= 10)) {
                throw new InputException("Второе число должно быть от 1 до 10 включительно");
            }
            result = String.valueOf(calculate(numb1, numb2, operator));
        } else if (isRomeNumber(number1) && isRomeNumber(number2)) { //если числа римские
            numb1 = romeToArab(number1);
            numb2 = romeToArab(number2);
            if (!(numb1 >= 1 && numb1 <= 10)) {
                throw new InputException("Первое число должно быть от 1 до 10 включительно");
            }
            if (!(numb2 >= 1 && numb2 <= 10)) {
                throw new InputException("Второе число должно быть от 1 до 10 включительно");
            }
            int resultRome = calculate(numb1, numb2, operator);
            if (resultRome <= 0) {
                throw new InputException("В римской системе  числа могут быть только больше " + 0);
            }
            result = arabToRome(resultRome);
        } else {
            throw new InputException("Недопускается использовать одновременно разные системы счисления");
        }
        return result;
    }


    public static String[] romeNumb = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


    public static int romeToArab(String romeNumber) { // преобразование римского числа в арабское
        int index = 0;
        for (int i = 1; i < romeNumb.length; i++) {
            if (romeNumber.equals(romeNumb[i])) {
                index = i;
            }
        }
        return index;
    }

    public static String arabToRome(int arabNumber) {  // преобразование арабского числа в римское
        String numbR = " ";
        if (arabNumber > 0 && arabNumber < romeNumb.length) {
            numbR = romeNumb[arabNumber];
        }
        return numbR;
    }


    static boolean isArabNumber(String numA) { // проверка арабского числа
        try {
            Integer.parseInt(numA);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isRomeNumber(String numR) { // проверка римского числа на наличие в массиве
        for (String s : romeNumb) {
            if (numR.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public static int calculate(int a, int b, char op) throws InputException {
        int result = 0;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (a < b) {
                    throw new InputException("делимое меньше делителя");
                }
                result = a / b;
                break;
        }
        return result;
    }
}
