package com.harrison.study;

import com.harrison.study.math.MathOperation;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //以前实现线程的方式，
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("second:hello java8");
            }
        };
        Thread oldOperation = new Thread(runnable);
        oldOperation.start();

        //下面的代码代替了上面的操作
        Runnable noArguments = () -> System.out.println("frist:hello java8");
        //上面的代码表示 的是实现了Runable接口中的run()方法；改run（）方法没有参数，且返回类型为void
        Thread thread = new Thread(noArguments);
        thread.start();

        //用lambuda来调用写的函数式接口
        int a = MathOperation.a; //调用接口中的变量
        MathOperation operation = (x, y) -> {return x*y + y;}; //表示实现了MathOperation接口中的add()方法 因为这个接口是个函数式接口
        int add = operation.add(1, 2);
        System.out.println(add);

        //几个重要的函数式接口
        //Peedicate<T> Consumer<T>
        Predicate<String> stringPredicate = x -> x.equals("a");

        List<String> list = Arrays.asList("a", "b", "c");
        List<String> collect = list.stream().filter(stringPredicate).collect(Collectors.toList());
        List<String> b = list.stream().filter(x -> x.equals("b")).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(b);

        ThreadLocal<SimpleDateFormat> initial = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));
        SimpleDateFormat format = initial.get();

    }
}
