package com.pt.jdk8.function;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author nate-pt
 * @date 2021/7/9 11:40
 * @Since 1.8
 * @Description 大消费中其中具有两个参数并且没有返回值
 */
public class BiConsumerTest {

    @Test
    public void exec1(){
        BiConsumer biConsumer = new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println(o);
                System.out.println(o2);
            }
        };

        biConsumer.accept("1",2);
    }

    @Test
    public void exec2(){
        String s = "1234,,44";
        System.out.println(s);
        BiFunction<CharSequence, CharSequence, String> charSequenceCharSequenceStringBiFunction = s::replace;
        System.out.println(charSequenceCharSequenceStringBiFunction.apply(",,", ""));
    }

    @Test
    public void exec3(){
        String string = "String";
        StringC c = string::getChars;
        c.getChars(1,2,"ri".toCharArray(),0);
        System.out.println(string);
    }

    @FunctionalInterface
    interface StringC<a,b,c,d>{
        void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin);
    }
}
