package com.harrison.study.stream;

import javax.sound.midi.Track;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 学习使用Stream类中的基本方法
 */
public class Main {
    public static void main(String[] args) {
        //collector()方法: 由Stream里的值生成一个列表，是一个及早求值操作
        //例使用Stream.collector(Collectors.toList())可以将流终止并返回一个int的数组
        //Stream操作都是惰性操作， 因此需要调用类似collect的及早求值方法来终止流来返回想要的结果
        //Stream的of方法使用一组初始值来生成新的Stream流
        List<String> strings = Stream.of("a", "b").collect(Collectors.toList());
        System.out.println("of:" + strings);

        //---------------------------分界线---------------------------------

        //map()函数：可以将一个流中的值转换成一个新的流
        //map()函数的参数是一个<R>Function<T,R> 函数 该函数是一个函数式接口 因此 可以用lambda来表示
        //例：将一个list<String>中的所有值转换成大写
        List<String> oldStrings = Arrays.asList("a", "b", "c", "sdga", "SDsdsf");
        //使用流之前的方法 遍历流将老数据逐个改变成大写
        ListIterator<String> listIterator = oldStrings.listIterator();
        while (listIterator.hasNext()){
            String next = listIterator.next();
            String upperCase = next.toUpperCase();
            listIterator.set(upperCase);
        }
        System.out.println("将list中的所有元素变成大写 使用流之前：" + oldStrings);
        //使用Stream中的map函数
        List<String> newStrings = Arrays.asList("a", "b", "c", "sdga", "SDsdsf");
        List<String> collect = newStrings.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println("将list中的所有元素变成大写 使用流：" + collect);

        Function<String, String> function = s -> s.toUpperCase();
        List<String> stringcollect2 = newStrings.stream().map(function).collect(Collectors.toList());
        System.out.println(stringcollect2);

        List<String> testList = Arrays.asList("hello word", "harrison");
        List<String[]> collect2 = testList.stream().map(s -> s.split("")).collect(Collectors.toList());
        System.out.println(collect2.get(0).length);

        //---------------------------分界线---------------------------------

        //filter()函数: 遍历数据并检查其中的元素时，可以使用filter函数来过滤并挑选出其中符合要求的元素
        //filter()函数的参数是一个<T>Predicate<T>函数作为参数 该函数是一个函数式接口 因此 可以用lambda来表示
        //例：将list<Integer>挑选出其中所有大于10的数
        List<Integer> intList = Arrays.asList(1, 11, 5, 6, 88, 12, 0, 26, 4);
        //使用流之前还是一样 先遍历老数据 去除数据中不符合的数据
        //使用流来操作
        List<Integer> newIntegerList = intList.stream().filter(i -> i > 10).collect(Collectors.toList());
        System.out.println("使用流中的filter函数来挑选出list中所有大于10的数" + newIntegerList);

        Predicate<Integer> predicate = x -> x > 20;
        List<Integer> collect1 = intList.stream().filter(predicate).collect(Collectors.toList());
        System.out.println(collect1);

        //---------------------------分界线---------------------------------

        //flatMap()函数:可以用Stream替换值,然后将多个Stream连接成一个Stream
        //例子： 将两个List合并成一个List
        List<Long> longList = Arrays.asList(1L, 3L, 5L, 9L);
        List<Long> addList = Arrays.asList(4L, 6L,15L);
        List<Long> newLongList = Stream.of(longList, addList).flatMap(x -> x.stream()).collect(Collectors.toList());
        //Function<? super T, ? extends Stream<? extends R>> mapper
        System.out.println("flatMap :" + newIntegerList);

        //---------------------------分界线---------------------------------

        //max和min 函数： Stream中常用的一个求最大值与最小值的方法
        List<Integer> integerList = Arrays.asList(1,5,8,9,100,6,3,2,4,5,2,55,77,84,54,88);
        Integer min = integerList.stream().min((a, b) -> a - b).get();
        System.out.println("最小值: " + min );
        Integer max = integerList.stream().max((a, b) -> a - b).get();
        System.out.println("最大值: " + max);

        //---------------------------分界线---------------------------------

        integerList.stream().forEach(System.out::println);
    }
}
