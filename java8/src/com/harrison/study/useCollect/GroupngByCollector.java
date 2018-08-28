package com.harrison.study.useCollect;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupngByCollector {

    private static final Set<Collector.Characteristics> characteristics = Collections.emptySet();

    public static void main(String[] args) {
        System.out.println("使用Collecotrs.groupingBy()方法");
        List<Integer> ages = Arrays.asList(1,5,8,7,9,11,22,45,35,6,46);
        Map<Boolean, List<Integer>> collect = ages.stream().collect(Collectors.groupingBy(x -> {
            if (x % 2 == 0) return true;
            return false;
        }));
        System.out.println(collect);
        System.out.println("====================================================");
        System.out.println("使用自定义的GroupByCollector.myGroupByMethod()方法");
        Map<Boolean, List<Integer>> myCollector = ages.stream().collect(GroupngByCollector.myGroupByMethod(x -> {
            if (x % 2 == 0) return true;
            return false;
        }));
        System.out.println(myCollector);
    }

    public static <T, K> Collector<T, Map<K, List<T>>, Map<K, List<T>>> myGroupByMethod(Function<T, K> func){
        return new GroupBy<>(func);
    }

    static class GroupBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>{
        private Function<T, K> func;

        public  GroupBy(Function<T, K> func){
            this.func = func;
        }

        @Override
        public Supplier<Map<K, List<T>>> supplier() {
            return () -> new HashMap<K, List<T>>();
        }

        @Override
        public BiConsumer<Map<K, List<T>>, T> accumulator() {
            return (map, item) -> {
                K apply = func.apply(item);
                if(map.get(apply) == null || map.get(apply).size() == 0){
                    List<T> list = new ArrayList<>();
                    list.add(item);
                    map.put(apply, list);
                }else {
                    List<T> list = map.get(apply);
                    list.add(item);
                }

                //可以使用一下代码：
                //K apply = func.apply(item);
                //List<T> elements = map.computeIfAbsent(apply, k -> new ArrayList<>());
                //elements.add(item);
            };
        }

        @Override
        public BinaryOperator<Map<K, List<T>>> combiner() {
            return (map1, map2) ->{
                map1.forEach((key, value) -> value.addAll(map2.get(key)));
                return map1;
            };

            //可以使用以下代码
//            return (left, right) -> {
//                right.forEach((key, value) -> {
//                    left.merge(key, value, (leftValue, rightValue) -> {
//                        leftValue.addAll(rightValue);
//                        return leftValue;
//                    });
//                });
//                return left;
//            };
        }

        @Override
        public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }
}
