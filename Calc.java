package com.company;

import java.util.Arrays;

class MyExcept extends Exception {
    private int detail;

    public MyExcept(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "MyExcept{"
                + ", message=" + getMessage()
                + "} ";
    }
}

public class Calc {

    String[] resultRom = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XXXL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    // массив римских цифр
    String[] romNum = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    // массив арабских цифр
    String[] arNum = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    // массив операторов
    String[] opertors = new String[]{"-", "+", "*", "/"};

    String input;

    public Calc(String input) throws MyExcept {

        returnResult(parserInput(input));


    }




    public String parserInput(String input){

        this.input = input;
        // предлагаю пользователю ввести данные
        System.out.println("Введите операцию, пример 2+3: ");
        // удаляю лишние символы
        String str1 = input.replaceAll("\\s", "");
        // возвращаю выражение
        return str1;

    }

    public String returnResult(String expression) throws MyExcept{

        // проверяю длину строки
        if (expression.length() <= 9){

            for(String oper: opertors){
                if(expression.contains(oper)){

                    // левый операнд
                    String leftOperand = expression.substring(0, expression.indexOf(oper));
                    // правый операнд
                    String rightOperand = expression.substring(expression.indexOf(oper) + 1);

                    // является ли левый операнд элементом romNum
                    boolean romFirstOperandBool = Arrays.asList(romNum).contains(leftOperand);
                    // является ли правый операнд элементом romNum
                    boolean romLastOperandBool = Arrays.asList(romNum).contains(rightOperand);

                    // является ли левый операнд элементом arNum
                    boolean arFirstOperandBool = Arrays.asList(arNum).contains(leftOperand);
                    // является ли правый операнд элементом arNum
                    boolean arLastOperandBool = Arrays.asList(arNum).contains(rightOperand);

                    switch (oper){
                        case "+":

                            if (romFirstOperandBool && romLastOperandBool){
                                // левый преобразованный операнд
                                int convertedLeftOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(leftOperand)]);
                                // правый преобразованный операнд
                                int convertedRightOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(rightOperand)]);

                                int result = convertedLeftOperand + convertedRightOperand;
                                System.out.println(resultRom[result]);
                                return resultRom[result];

                            }else if (arFirstOperandBool && arLastOperandBool){

                                System.out.println(Integer.valueOf(leftOperand) );


                                int resInt = Integer.valueOf(leftOperand) + Integer.valueOf(rightOperand);
                                String reStr = Integer.toString(resInt);
                                System.out.println(reStr);
                                return reStr;



                            }else if (Integer.valueOf(leftOperand) > 10){
                                throw new MyExcept ("Числа не могут быть больше 10");
                            }
                            else if(arFirstOperandBool || arLastOperandBool){
                                throw new MyExcept ("Неправильное выражение, либо 2+2 либо I+I");
                            }

                            break;

                        case "-":

                            if (romFirstOperandBool && romLastOperandBool){
                                // левый преобразованный операнд
                                int convertedLeftOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(leftOperand)]);
                                // правый преобразованный операнд
                                int convertedRightOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(rightOperand)]);

                                if (convertedLeftOperand < convertedRightOperand){
                                    throw new MyExcept ("В арабских цифрах нет отрицательных чисел");

                                }else if((convertedLeftOperand - convertedRightOperand) == 0){
                                    throw new MyExcept ("В римских цифрах нет ноля");

                                }else {
                                    int result = convertedLeftOperand - convertedRightOperand;
                                    System.out.println(resultRom[result]);
                                    return resultRom[result];
                                }



                            }else if (arFirstOperandBool && arLastOperandBool){
                                int resInt = Integer.valueOf(leftOperand) - Integer.valueOf(rightOperand);
                                String reStr = Integer.toString(resInt);
                                System.out.println(reStr);
                                return reStr;

                            }else if (arFirstOperandBool || arLastOperandBool){
                                throw new MyExcept ("Неправильное выражение, либо 2-2 либо I-I");
                            }

                            break;

                        case "*":
                            if (romFirstOperandBool && romLastOperandBool){
                                // левый преобразованный операнд
                                int convertedLeftOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(leftOperand)]);
                                // правый преобразованный операнд
                                int convertedRightOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(rightOperand)]);

                                int result = convertedLeftOperand * convertedRightOperand;
                                System.out.println(resultRom[result]);
                                return resultRom[result];

                            }else if (arFirstOperandBool && arLastOperandBool){
                                int resInt = Integer.valueOf(leftOperand) * Integer.valueOf(rightOperand);
                                String reStr = Integer.toString(resInt);
                                System.out.println(reStr);
                                return reStr;

                            }else if (arFirstOperandBool || arLastOperandBool){
                                throw new MyExcept ("Неправильное выражение, либо 2*2 либо I*I");
                            }

                            break;

                        case "/":

                            if (romFirstOperandBool && romLastOperandBool){
                                System.out.println("sfg");
                                // левый преобразованный операнд
                                int convertedLeftOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(leftOperand)]);
                                // правый преобразованный операнд
                                int convertedRightOperand = Integer.parseInt(arNum[Arrays.asList(romNum).indexOf(rightOperand)]);

                                if ((convertedLeftOperand / convertedRightOperand) == 0){
                                    throw new MyExcept ("В римских нет нолей");

                                }else {
                                    int result = convertedLeftOperand / convertedRightOperand;
                                    System.out.println(resultRom[result]);
                                    return resultRom[result];
                                }

                            }else if (arFirstOperandBool && arLastOperandBool){

                                if (Integer.valueOf(rightOperand) == 0){
                                    throw new MyExcept ("Деление на ноль запрещено");

                                }else {
                                    int resInt = Integer.valueOf(leftOperand) / Integer.valueOf(rightOperand);
                                    String reStr = Integer.toString(resInt);
                                    System.out.println(reStr);
                                    return reStr;
                                }

                            }else if (arFirstOperandBool || arLastOperandBool){
                                throw new MyExcept ("Неправильное выражение, либо 2/2 либо I/I");
                            }

                            break;

                    }

                }
            }

        }if(expression.length() > 9){
            throw new MyExcept ("Слишком длинное выражение");

        }if(expression.length() < 3){
            throw new MyExcept ("Слишком короткое выражение");
        }


        return null;
    }

}


