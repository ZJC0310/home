package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static Set<String> resultSet = new HashSet<>();
    private static final String exercisesFilePath = "Exercises.txt";
    private static final String answersFilePath = "Answers.txt";

    public static void main(String[] args) {
        int length = args.length;
        if (length != 4 && length != 2) {
            throw new RuntimeException("输入的参数个数有误");
        }
        try {
            start(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(String[] args) throws IOException {
        int num = 0;
        int max = 0;
        String exerciseFile = null;
        String answerFile = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n")) {
                if (i + 1 < args.length) {
                    num = Integer.parseInt(args[i + 1]);
                    if (num <= 0) throw new RuntimeException("生成的表达式数量小于等于0");
                }
            }
            if (args[i].equals("-r")) {
                if (i + 1 < args.length) {
                    max = Integer.parseInt(args[i + 1]);
                    if (max <= 0) throw new RuntimeException("生成的表达式最大范围小于等于0");
                }
            }
            if (args[i].equals("-e")) {
                if (i + 1 < args.length) {
                    exerciseFile = args[i + 1];
                }
            }
            if (args[i].equals("-a")) {
                if (i + 1 < args.length) {
                    answerFile = args[i + 1];
                }
            }
        }

        if (num != 0 && max == 0) max = 10; // 只要求生成题目时，最大范围与题目数一致
        if (num == 0 && max != 0) num = 10; // 只要求题目范围时，默认生成10道题目
        if (num != 0 && max != 0) generate(num, max);
        if (exerciseFile != null && answerFile != null) judge(exerciseFile, answerFile);
        if (num == 0 && max == 0 && exerciseFile == null && answerFile == null)
            throw new RuntimeException("输入的参数有误");
    }

    private static void generate(int num, int max) throws IOException {
        int i = 0;
        FileUtil.ClearFile(exercisesFilePath);
        FileUtil.ClearFile(answersFilePath);
        while (i < num) {
            int numSymbol = (int) (Math.random() * 3 + 1); // 随机生成1-3个运算符
            Equation equation = new Equation(numSymbol, max); // 生成表达式

            // 检查表达式是否有效
            if (!isValidEquation(equation) || !isValidOperatorCount(equation) || !hasValidParentheses(equation)) continue;

            String result = equation.getResult().toString();
            if (!resultSet.isEmpty() && resultSet.contains(result))
                continue;
            resultSet.add(result); // 记录结果，防止重复
            String question = (i + 1) + ". " + equation.getInfixExpression() + "=";
            result = (i + 1) + ". " + result;
            FileUtil.WriteFile(exercisesFilePath, question); // 写入题目
            FileUtil.WriteFile(answersFilePath, result); // 写入答案
            i++;
        }
    }

    private static boolean isValidEquation(Equation equation) {
        // 检查结果是否相等于表达式
        if (equation.getResult().toString().equals("0")) return false; // 不允许结果为0
        if (equation.getInfixExpression().matches(".*\\d=\\d.*")) return false; // 不允许形如"9=9"
        // 检查是否至少有一个运算符
        String infix = equation.getInfixExpression();
        int operatorCount = infix.split("[+\\-×÷]").length - 1; // 计算运算符数量
        return operatorCount > 0; // 确保至少有一个运算符
    }

    private static boolean isValidOperatorCount(Equation equation) {
        String infix = equation.getInfixExpression();
        int operatorCount = infix.split("[+\\-×÷]").length - 1; // 计算运算符数量
        return operatorCount <= 3; // 确保总运算符数量不超过3
    }

    private static boolean hasValidParentheses(Equation equation) {
        String infix = equation.getInfixExpression();
        return !infix.contains("( )") && infix.split("\\(").length - 1 <= 1; // 检查括号内是否有内容
    }

    private static void judge(String exercises, String answers) throws IOException {
        List<String> exerciseList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        FileUtil.ReadFile(exercises, exerciseList); // 读取题目
        FileUtil.ReadFile(answers, answerList); // 读取答案
        if (exerciseList.size() != answerList.size()) {
            throw new RuntimeException("题目数量与答案数量不一致");
        }
        boolean[] tag = new boolean[exerciseList.size()]; // 标记答案是否正确
        int num = 0; // 标记正确答案个数
        for (int i = 0; i < exerciseList.size(); i++) {
            System.out.println("Processing expression: " + exerciseList.get(i)); // 打印表达式
            try {
                Equation equation = new Equation(exerciseList.get(i));
                if (equation.getResult().toString().equals(answerList.get(i))) {
                    tag[i] = true;
                    num++; // 记录正确的答案个数
                }
            } catch (NumberFormatException e) {
                System.err.println("Error processing expression: " + exerciseList.get(i) + " - " + e.getMessage());
            }
        }
        StringBuilder correct = new StringBuilder("Correct:" + num + "(");
        StringBuilder wrong = new StringBuilder("Wrong:" + (exerciseList.size() - num) + "(");
        for (int i = 0; i < tag.length; i++) {
            if (tag[i])
                correct.append(i + 1).append(",");
            else
                wrong.append(i + 1).append(",");
        }
        if (correct.charAt(correct.length() - 1) != '(')
            correct = new StringBuilder(correct.substring(0, correct.length() - 1) + ")");
        else
            correct.append(")"); // 补全括号
        if (wrong.charAt(wrong.length() - 1) != '(')
            wrong = new StringBuilder(wrong.substring(0, wrong.length() - 1) + ")");
        else
            wrong.append(")"); // 补全括号
        FileUtil.WriteFile("Grade.txt", correct.toString());
        FileUtil.WriteFile("Grade.txt", wrong.toString());
    }
}
