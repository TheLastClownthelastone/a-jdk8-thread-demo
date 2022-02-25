package com.pt.jdk8;

import org.junit.Test;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nate-pt
 * @date 2021/7/9 9:10
 * @Since 1.8
 * @Description 测试stream
 */
public class StreamTest {

    @Test
    public void exec1(){
        boolean b = Stream.of(1, "3", 3.5).allMatch(i -> ((Comparable) i).equals("3"));

        System.out.println(b);
    }

    @Test
    public void exec2(){
        List<Double> collect = Stream.generate(() -> new Random().nextDouble() * 12).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 合并流
     */
    @Test
    public void exec3(){
        Serializable reduce = Stream.of("1", 2, "3").reduce("2", ((s, serializable) -> {
//            System.out.println(s);
//            System.out.println(serializable);
            return s;
        }));

        System.out.println(reduce);
    }


    @Test
    public void reduceTest() {
        Optional accResult = Stream.of(1, 2, 3, 4).reduce((acc, item) -> {
            System.out.println("acc : " + acc);
            acc += item;
            System.out.println("item: " + item);
            System.out.println("acc+ : " + acc);
            System.out.println("--------");
            return acc;
        });
        System.out.println(accResult);
    }
}
