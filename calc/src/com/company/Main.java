package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String nums = readUsingFiles ("input");
        Calculator calculator = new Calculator(nums);
        System.out.println(nums + " = " + calculator.PlusAndMinus());
    }
    //* Метод считавающий данные из файла input
    public static String readUsingFiles(String input) throws IOException {
        return new String(Files.readAllBytes(Paths.get(input)));
    }

       //*Класс описывающий калькулятор

    
    public static class Calculator {
        File output = new File("output");
        PrintWriter printWriter = new PrintWriter(output);

        String[] space;
        int indexspace;
        //* Конструктор класса
        public Calculator(String nums) throws FileNotFoundException {
            this.space = nums.split(" ");
            this.indexspace = 0;
        }

        //* Метод сложения и вычитания
        //* Выполняет функции проверки корректности данных
        public double PlusAndMinus() {
            try {

                double first = DivisionAndMultiplication();
                while (indexspace < space.length) {
                    String operator = space[indexspace];
                    if (!operator.equals("+") && !operator.equals("-")) {
                        return 0;
                    } else {
                        indexspace++;
                    }
                    double second = DivisionAndMultiplication();
                    if (operator.equals("+")) {
                        first += second;
                    } else {
                        first -= second;
                    }
                    printWriter.println(first);
                    printWriter.close();
                }
                return first;

            } catch (Exception e) {
                printWriter.println("Некорректный ввод!");
                printWriter.close();
                System.exit(0);
            }
            return 0;
        }
        //* Метод умножения и деления
        public double DivisionAndMultiplication() {

            double first = ExponentdReamainder();
            while (indexspace < space.length) {
                String operator = space[indexspace];

                if (!operator.equals("*") && !operator.equals("/")) {
                    break;
                } else {
                    indexspace++;
                }

                double second = ExponentdReamainder();
                if (operator.equals("*")) {
                    first *= second;
                } else {
                    first /= second;
                }
                printWriter.println(first);
                printWriter.close();
            }
            return first;
        }
        //* Метод нахождения остатка от деления и возведения в степень

        public double ExponentdReamainder() {
            double first = Double.parseDouble(space[indexspace++]);
            while (indexspace < space.length) {
                String operator = space[indexspace];
                if (!operator.equals("%") && !operator.equals("^")){
                    break;
                }else {
                    indexspace++;
                }
                double second = Double.parseDouble(space[indexspace++]);
                if (operator.equals("%")) {
                    first %= second;

                } else {
                    first = Math.pow(first,second);
                }
                printWriter.println(first);
                printWriter.close();
            }
            return first;
        }


    }
}
