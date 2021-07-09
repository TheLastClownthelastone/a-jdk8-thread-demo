package com.pt.jdk8.practice;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author nate-pt
 * @date 2021/7/9 14:13
 * @Since 1.8
 * @Description 练习题
 */
public class PracticeTest {
    /**
     * 编写一个求和函数，计算流中所有数的和。例int addUp(Stream numbers)
     */
    @Test
    public void exec1(){
        Integer integer = IntStream.of(1, 2, 4, 5).boxed().reduce(PracticeTest::sum).get();
        System.out.println(integer);
    }
    public static Integer sum(Integer a,Integer b){
        return a+b;
    }

    /**
     * 编写一个函数，参数为艺术家集合，返回一个字符串集合，其中包含了艺术家的姓名与国籍。
     */
    @Test
    public void exec2(){
        List<String> collect = Stream.of(new Arter("tom", "china"), new Arter("jerry", "japan"))
                .map(PracticeTest::doFiedAdd).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static String doFiedAdd(Arter arter){
        return arter.getName()+"_"+arter.getGj();
    }




}
