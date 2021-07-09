package com.pt.jdk8.function;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author nate-pt
 * @date 2021/7/9 11:37
 * @Since 1.8
 * @Description 服务提供者接口
 */
public class SupplierTest {

    @Test
    public void exec1(){
        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return "1111";
            }
        };

        Object o = supplier.get();
        System.out.println(o);
    }
}
