package com.pt.jdk8;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author nate-pt
 * @date 2021/7/8 14:58
 * @Since 1.8
 * @Description IntStream流 api学习
 */
public class IntStreamTest {

    /**
     * range方法将从0开始的长度为100个的数塞入stream流中
     */
    @Test
    public void exec1() {
        IntStream.range(0, 100).forEach(i -> System.out.println(i));
    }

    /**
     * of将一类类型的元素塞入stream流中
     */
    @Test
    public void exec2() {
        IntStream.of(2, 4, 5).forEach(i -> System.out.println(i));
    }

    /**
     * anyMatch 当前的stream流中的元素只要有一个元素满足IntPredicate 中的nr则放回true，
     */
    @Test
    public void exec3() {
        boolean b = IntStream.of(2, 4, 5).anyMatch(value -> value > 8);
        System.out.println(b);
    }

    /**
     * average 算出stream流中的平均值
     */
    @Test
    public void exec4() {
        double asDouble = IntStream.of(2, 4, 5).average().getAsDouble();
        System.out.println(asDouble);

        double asDouble1 = IntStream.of(3, 3, 3).average().getAsDouble();
        System.out.println(asDouble1);
    }

    /**
     * 将stream流中的元素由基础数据类型转换成包装类类型
     */
    @Test
    public void exec5() {
        IntStream.of(2, 4, 5).boxed().forEach(System.out::println);
    }

    /**
     * 将两个stream流合并成一个stream流
     */
    @Test
    public void exec6() {
        IntStream.concat(IntStream.of(1, 2, 3), IntStream.of(4, 5, 6)).forEach(System.out::println);
    }

    /**
     * 将stream流中的元素进行重组
     */
    @Test
    public void exec7() {
        List<Integer> list = IntStream.of(1, 2, 4).boxed().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 返回流中元素的个数
     */
    @Test
    public void exec8() {
        long count = IntStream.of(2, 3, 4, 5, 6).count();
        System.out.println(count);
    }


    /**
     * stream流中的元素进行去重复
     */
    @Test
    public void exec9() {
        IntStream.of(1, 2, 2, 4).distinct().forEach(System.out::println);
    }

    /**
     * filter stream 进行元素筛选
     */
    @Test
    public void exec10() {
        IntStream.of(2, 4, 5, 6).filter(i -> i > 2).forEach(System.out::println);
    }

    /**
     * 返回流中任意一个元素
     */
    @Test
    public void exec11() {
        int asInt = IntStream.of(1, 2, 3, 6, 4).filter(i -> i > 1).findAny().getAsInt();
        System.out.println(asInt);
//        int asInt1 = IntStream.of(1, 2, 3, 6, 4).filter(i -> i > 1).findAny().getAsInt();
//        System.out.println(asInt1);

        System.out.println(IntStream.range(0, 100).parallel().findAny().getAsInt());
        System.out.println(IntStream.range(0, 100).parallel().findAny().getAsInt());
        System.out.println(IntStream.range(0, 100).parallel().findAny().getAsInt());
    }

    /**
     * 返回流中的第一个元素
     */
    @Test
    public void exec12() {
        int asInt = IntStream.of(1, 2, 3,6,5).filter(i -> i > 3).findFirst().getAsInt();
        System.out.println(asInt);
    }

    /**
     * 将当前流中的元素可以传递到下一个stream流中
     */
    @Test
    public void exec13() {
        IntStream.of(3, 4, 5).boxed().
                flatMap(integer -> IntStream.of(2, 3).boxed().filter(i -> i.equals(integer))).forEach(System.out::println);
    }

    /**
     * generate 产生无限流 一般配合limit进行使用
     * limit  限制流个数
     */
    @Test
    public void exec14() {
        IntStream.generate(() -> new Random().nextInt(10)).limit(10).forEach(System.out::println);
    }

    /**
     * min 返回流中元素最小的
     * max 返回流中员孙最大的
     */
    @Test
    public void exec15() {
        System.out.println(IntStream.of(1, 3, 5, 6).min().getAsInt());
        System.out.println(IntStream.of(1, 3, 5, 6).max().getAsInt());
    }

    /**
     * iterate 可以产生无限流
     * 其中第一参数表示 stream流的第一个元素
     * 第二个参数表示 后面元素的值
     * 一般结合limit使用
     */
    @Test
    public void exec16() {
        IntStream.iterate(1, i -> i + 3).limit(5).forEach(System.out::println);
    }

    /**
     * stream 流中先将第一个元素和第二元素 进行合并，
     * 将合并和的结果在跟第三元素进行合并 以此类推，直到最后一个元素为止
     */
    @Test
    public void exec17() {
        int asInt = IntStream.of(1, 2, 3, 4).reduce((left, right) -> {
            System.out.println(left);
            System.out.println(right);
            return right - left;
        }).getAsInt();
        System.out.println("最终结果为：" + asInt);
    }

    /**
     * 加上一个参数表示以加上的这个参数进行合并计算
     */
    @Test
    public void exec18() {
        int reduce = IntStream.of(1, 2, 3, 4).reduce(4, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                System.out.println(left);
                System.out.println(right);
                return right - left;
            }
        });

        System.out.println("最终结果为: " + reduce);
    }

    /**
     * stream流中丢弃从对应下标的元素
     */
    @Test
    public void exec19(){
        IntStream.of(1,3,5,4).skip(0).forEach(System.out::println);
    }

    /**
     * 获取在stream流中的元素，与map不同，不可以修改元素的值
     */
    @Test
    public void exec20(){
        IntStream.of(1,2,34,5).peek(value -> {
            System.out.println("进行map方法之前每一个元素为："+value);
        }).map(i->i+3).forEach(System.out::println);
    }


    /**
     * stream流进行排序
     */
    @Test
    public void exec21(){
        IntStream.of(1,2,4,29,1,3).sorted().forEach(System.out::println);
    }

}
