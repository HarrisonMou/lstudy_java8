package com.harrison.study.useCollect;

import com.harrison.study.general.Artist;
import com.harrison.study.general.SampleData;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /**
     * 使用Collectors类中的averagingInt（averagingDouble， averagingLong）来计算平均值
     * 解析：averagingInt 是接收一个Lambda作为参数，将流中的元素转换成int型 然后计算平均值
     */
    @Test
    public void test1(){
        //
        List<Integer> integerList = Arrays.asList(1,5,8,9,6,4);
        Double averagingInt = integerList.stream().collect(Collectors.averagingInt(i -> i));
        System.out.println(averagingInt);

        double average = integerList.stream().mapToInt(x -> x).summaryStatistics().getAverage();
        System.out.println(average);
    }

    /**
     * 使用Collectors类中的maxBy方法：传入一个比较器，可以找出最大值 类似的有哦minBy 找出最小的
     *  使用maxBy 或 minBy 只能找出一个值 按顺序
     */
    @Test
    public void test2(){
        //例1： 找出下面最大的数字
        List<Integer> integerList = Arrays.asList(1,5,8,9,6,4);
        Comparator<Integer> comparator = (x, y) -> (int)x - (int)y;
        Optional<Integer> collect = integerList.stream().collect(Collectors.maxBy(comparator));
        System.out.println(collect.orElse(0));

        //例2：找出乐队中成员最多的乐队
        List<Artist> artists = Arrays.asList(SampleData.anotherThreeMenberBeatles, SampleData.twoMenberBeatles, SampleData.threeMenberBeatles);
        Optional<Artist> artist = artists.stream().collect(Collectors.maxBy((x, y) -> (int) x.getMembers().count() - (int) y.getMembers().count()));
        System.out.println(artist.get().getName());
    }

    /**
     * 数据分块：
     *      使用Collectors类中的partitioningBy: 这个方法接受一个流，并将该流按照 true false 分成2部分
     */
    @Test
    public void test3(){
        //例子： 在乐队列表中 将原有的list分成单人乐队 和 多人乐队
        List<Artist> artists = Arrays.asList(SampleData.johnColtrane, SampleData.twoMenberBeatles, SampleData.threeMenberBeatles, SampleData.paulMcCartney);
        //artist -> artist.isSolo()) 调用的是是否是单人的方法  true:单人 false:多人
        Map<Boolean, List<Artist>> collect = artists.stream().collect(Collectors.partitioningBy(artist -> artist.isSolo()));
        System.out.println(collect);
        //返回结果： {false=[The Beatles with two menbers, The Beatles with three menbers], true=[John Coltrane, Paul McCartney]}
    }

    /**
     * 数据分组
     *      使用Collectors类中的groupingBy函数可以将流分成任意对数据组 与 partitioningBy不同 partitioningBy只能按照 true false分成2组
     *      该方法类似于 mysql中的group by
     */
    @Test
    public void test4(){
        //例子：将一组乐队按照乐队人数进行分组
        List<Artist> artists = Arrays.asList(SampleData.johnColtrane, SampleData.twoMenberBeatles, SampleData.threeMenberBeatles, SampleData.anotherThreeMenberBeatles);
        Map<Long, List<Artist>> collect = artists.stream().collect(Collectors.groupingBy(artist -> artist.getMembers().count()));
        System.out.println(collect);
        //返回结果：{0=[John Coltrane], 2=[The Beatles with two menbers], 3=[The Beatles with three menbers, The Beatles with three menbers （another）]}
    }

    @Test
    public void test5(){
        System.out.println(Stream.of("a", "b", "c").collect(Collectors.joining(",","[","]")));
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x + 1, (x, y) -> x + y)));
        System.out.println(Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(","))));
    }
}
