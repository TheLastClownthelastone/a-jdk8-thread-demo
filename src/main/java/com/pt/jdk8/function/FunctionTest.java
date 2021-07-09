package com.pt.jdk8.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author nate-pt
 * @date 2021/7/9 10:52
 * @Since 1.8
 * @Description 函数式有参数有返回值类型的接口
 */
public class FunctionTest {

    @Test
    public void exec1(){
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                return "ssss";
            }
        };
        Object qqqqq = function.apply("qqqqq");
        System.out.println(qqqqq);
    }

    /**
     * addThen 在第一个Function中的apply方法执行完毕之后，
     * 往下执行第二个Function中的apply方法
     */
    @Test
    public void exec2(){
        Function function = o->"33";

        Object apply = function.andThen(o -> 888).apply("99");
        System.out.println(apply);
    }

    @Test
    public void exec3(){
        // 绝对值方法
        Function<Double, Double> doubleCust = Math::abs;
        Double apply = doubleCust.apply(6.5);
        System.out.println(apply);

        Double apply1 = doubleCust.apply(-5.6);
        System.out.println(apply1);
    }

    @Test
    public void exec4(){
        Ca ca = String::valueOf;
        System.out.println(ca.a(3));
    }

    /**
     * 使用一个类的实例用函数式方法
     * 可以然后B这个跟Ca接口没有关系的类进行赋值
     */
    @Test
    public void exec5(){
        B b = new B();
        Ca aa = b::aa;
        System.out.println(aa.a(3));
    }

    @FunctionalInterface
    interface Ca{
        String a(int a);
    }


    class B{
        String aa(int value){
            return String.valueOf(value);
        }
    }
}
