package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //大体思路
        // 1.完成对对逆波兰表达式的计算（后缀表达式）
        // 2.中缀表达书转后缀表达式


        String expression = "1 + ( ( 2 + 3 ) * 4 ) - 5";//注意表达式
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpreesionList = pareSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]

        System.out.printf("expression=%d", calculate(suffixExpreesionList)); // ?




/*
        //测试
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        //String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
        //思路
        //1. 先将 "3 4 + 5 × 6 - " => 放到ArrayList中
        //2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList=" + list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);
*/

    }


    public static List<String> pareSuffixExpressionList(List<String> ls) {
        //符号栈
        Stack<String> s1 = new Stack<>();
        //储存中间结果的：中缀转后缀
        ArrayList<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将 ( 弹出 s1栈， 消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }



    public static List<String> toInfixExpressionList(String s) {
        String[] split = s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele: split) {
            list.add(ele);
        }
        return list;
    }




    public static List<String> getListString(String suffExpression){
        String[] split = suffExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele: split) {
            list.add(ele);
        }
        return list;
    }


    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String item : ls){
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                // pop出两个数，并运算， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());

    }

static class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
        }
    }
}