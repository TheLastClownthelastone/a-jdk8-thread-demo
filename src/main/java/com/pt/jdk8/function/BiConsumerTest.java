package com.pt.jdk8.function;

import org.junit.Test;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author nate-pt
 * @date 2021/7/9 11:40
 * @Since 1.8
 * @Description 大消费中其中具有两个参数并且没有返回值
 */
public class BiConsumerTest {

    @Test
    public void exec1() {
        BiConsumer biConsumer = new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println(o);
                System.out.println(o2);
            }
        };

        biConsumer.accept("1", 2);
    }

    @Test
    public void exec2() {
        String s = "1234,,44";
        System.out.println(s);
        BiFunction<CharSequence, CharSequence, String> charSequenceCharSequenceStringBiFunction = s::replace;
        System.out.println(charSequenceCharSequenceStringBiFunction.apply(",,", ""));
    }

    @Test
    public void exec3() {
        String string = "String";
        StringC c = string::getChars;
        c.getChars(1, 2, "ri".toCharArray(), 0);
        System.out.println(string);
    }

    @FunctionalInterface
    interface StringC<a, b, c, d> {
        void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin);
    }

    @Test
    public void exec4() {
        Serializable serializable = Stream.of("2", 3, 2.1).reduce(Objects::equals).get();

        System.out.println(serializable);
    }

    @Test
    public void exec5() {
        // 调用boxed类型先将基础类型进行转换
        List<Integer> collect = IntStream.of(1, 2, 3).boxed().filter(BiConsumerTest::check).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static boolean check(Integer a) {
        if (a >= 3) {
            return true;
        }
        return false;
    }

    @Test
    public void exec7(){
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                return "3";
            }
        };

        System.out.println(function.apply(3));



    }






}

@FunctionalInterface
interface MyFuction<T,E>{
     T doing(E a);
}