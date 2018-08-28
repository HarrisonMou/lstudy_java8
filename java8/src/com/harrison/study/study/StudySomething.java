package com.harrison.study.study;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.IntStream;

public class StudySomething {

    public static  Boolean isStringEquals(String sa, String sb){
        String frist = getString(sa);
        String secend = getString(sb);
        Boolean flag = false;
        if(frist != null){
            flag = frist.equals(secend);
        } else if(secend != null){
            flag = secend.equals(frist);
        }else {
            return true;
        }
        return flag;
    }

    public static  String getString(String str){
        Stack<Character> stack = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if(chars[i] == '#'){
                try {
                    Character popChar = stack.pop();
                    System.out.println("遇到了#符号,将元素" + popChar + "弹出栈顶");
                    continue;
                } catch (EmptyStackException emptyStackException){
                    //如果删除的是栈顶的元素 就处理异常 并继续循环
                    System.out.println("栈为空，无法弹出元素");
                    continue;
                }
            }
            Character pushChar = stack.push(chars[i]);
            System.out.println("将元素" + pushChar + "进行压栈操作");
        }
        int size = stack.size();
        char[] revChars = new char[size];
        for (int i = size - 1; i >= 0 ; i--){
            revChars[i] = stack.pop();
        }
        return new String(revChars);
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = "abc#";
        IntStream chars = a.chars();

        System.out.println(isStringEquals(a, b));
    }
}
