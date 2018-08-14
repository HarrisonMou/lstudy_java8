package com.harrison.study.math;

/**
 * 定义一个有关数学操作的函数式接口
 * 函数式接口：
 *  定义：
 *      函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 *  优势：
 *      函数式接口可以被隐式转换为lambda表达式
 *      函数式接口可以现有的函数友好地支持 lambda
 *
 *  接口的改变：
 *      在java8以前的版本中，定义一个接口时，所有的方法必须是抽象方法，不能有具体实现，这是java语法规定的
 *      在java8中的接口可以有属性，可以有抽象方法，也可以有具体的方法
 */
public interface MathOperation {
    public int a = 1; //在接口中可以定义属性 public形式

    /**
     * 加法
     * @param x
     * @param y
     */
    public abstract int add(int x, int y); //抽象方法

    /**
     * 乘法
     * @param x
     * @param y
     * @return
     */
    default int multiply(int x, int y){  //在接口中定义有实现方式的函数
        return x*y;
    }

    /**
     * 减法
     * @param x
     * @param y
     * @return
     */
    default int subtraction(int x, int y){
        return x-y;
    }
}
