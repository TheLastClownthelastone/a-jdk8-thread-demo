package com.pt.jdk8.function;

import javafx.util.Builder;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;

/**
 * @author nate-pt
 * @date 2021/7/9 9:57
 * @Since 1.8
 * @Description 消费者接口 ，有一个参数没有返回值
 */
public class ConsumerTest {


    @Test
    public void exec1() {
        Consumer consumer = System.out::println;
        Consumer consumer1 = n -> System.out.println(n + "-consumer1");

        //执行完consumer之后在执行consumer1
        //consumer.andThen(consumer1).accept("test");

        // 连续执行accept方法
        consumer.andThen(consumer).andThen(consumer).andThen(consumer).accept("test");
    }

    /**
     * addThen表示在执行完第一个Consumer的accept方法之后执行到下一个里面
     */
    @Test
    public void exec2() {
        Consumer consumer = o -> System.out.println("4");
        consumer.andThen(o -> System.out.println(o + "ddddd")).accept("3333");
    }


    @Test
    public void exec3() {
        ConsumerTest consumerTest = new ConsumerTest();
        BiConsumer<ConsumerTest, String> test = ConsumerTest::test;
        test.accept(consumerTest, "333");
    }

    @Test
    public void exec4() {
        Supplier<ConsumerTest> aNew = ConsumerTest::new;
        ConsumerTest consumerTest = aNew.get();
        Consumer<String> test = consumerTest::test;
        test.accept("3333");
    }

    @Test
    public void exec5() {
        Cust<Double> sin = Math::sin;
        Double doing = sin.doing(3.0);
        System.out.println(doing);
    }

    public void test(String s) {
        System.out.println(s);
    }

    @Test
    public void exec6() {
        Event eut = Event::eut;
        eut.en("dddd");
    }

    @FunctionalInterface
    interface Cust<T> {
        T doing(T t);
    }

    interface Event {
        void en(String q);
        static void eut(String z){
            System.out.println(z);
        }
    }


}
