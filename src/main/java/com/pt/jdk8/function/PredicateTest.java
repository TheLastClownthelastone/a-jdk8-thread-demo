package com.pt.jdk8.function;

import org.junit.Test;

import java.awt.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author nate-pt
 * @date 2021/7/9 11:10
 * @Since 1.8
 * @Description 断言接口测试
 */
public class PredicateTest {

    @Test
    public void exec1(){
        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                if (o == null) {
                    return false;
                }else {
                    return true;
                }
            }
        };

        boolean test = predicate.test(new Object());

        boolean test1 = predicate.test(null);

        System.out.println(test);
        System.out.println(test1);

    }


    /**
     * and 将前一个接口中的test方法返回值和当前接口test方法返回值进行合并
     */
    @Test
    public void exec2(){
        Predicate predicate = o -> o==null;

        boolean test = predicate.and(o -> o.equals("3")).test("3");
        System.out.println(test);
    }

    /**
     * negate 方法对test方法结果取非操作
     */
    @Test
    public void exec3(){
        Predicate<Integer> predicate = o->o!=2;

        boolean test = predicate.negate().test(2);
        System.out.println(test);
    }

    @Test
    public void exec4(){
        Supplier<Object> aNew = Object::new;
        Object o = aNew.get();
        Predicate predicate = o::equals;
        boolean test = predicate.test(1);
        System.out.println(test);
    }

    /**
     * 静态方法获取函数式表达式 类名::方法名称
     * 非静态方法获取函数式表达式 实例对象::方法名称
     */
    @Test
    public void exec5(){
         Cast cast = "22"::equals;
        System.out.println(cast.cast("11"));
    }

    interface  Cast{
        boolean cast(Object a);
    }
}
