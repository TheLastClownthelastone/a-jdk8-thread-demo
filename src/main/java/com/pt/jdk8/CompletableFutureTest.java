package com.pt.jdk8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author nate-pt
 * @date 2021/7/8 11:10
 * @Since 1.8
 * @Description
 */
public class CompletableFutureTest {

    @Test
    public void exec1(){
        CompletableFuture.
                supplyAsync(()->1)
                .thenApply(i->i+1)
                .thenApply(i->i*i)
                .whenComplete((r,e)->System.out.println("当前线程为："+Thread.currentThread().getName()+" 结果r:"+r+
                        " 结果e:"+e));
    }

    /**]
     * 创建有返回值的异步任务
     */
    @Test
    public void exec2(){
        CompletableFuture<Double> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("当前线程 :"+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3.1;
        });
        try {
            System.out.println("当前线程 :"+ Thread.currentThread().getName());
            Double aDouble = doubleCompletableFuture.get();
            System.out.println(aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建无返回值的异步任务
     */
    @Test
    public void  exec3(){
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("当前线程 :" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            System.out.println("当前线程 :"+ Thread.currentThread().getName());
            voidCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务完成之后进行回调的方法
     * thenApply
     */
    @Test
    public void exec4(){
        ForkJoinPool forkJoinPool  = new ForkJoinPool();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步调用过程中线程为：" + Thread.currentThread().getName());
            return 4 / 2;
        },forkJoinPool).
                thenApply(result -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("回调过程中线程线程为：" + Thread.currentThread().getName());
                    return String.valueOf(result);
                });

        System.out.println("当前线程为：" + Thread.currentThread().getName());
        try {
            String s = stringCompletableFuture.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步执行任务进行异步回调
     */
    @Test
    public void exec5(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步调用过程中线程为：" + Thread.currentThread().getName());
            return 4 / 2;
        },forkJoinPool).
                thenApplyAsync(result -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("回调过程中线程线程为：" + Thread.currentThread().getName());
                    return String.valueOf(result);
                });

        System.out.println("当前线程为：" + Thread.currentThread().getName());
        try {
            String s = stringCompletableFuture.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行完异步任务的回调
     * 有参数没有返回值
     * 回调方法在主线程中执行
     *
     * thenAccept
     */
    @Test
    public void exec6() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程名称："+Thread.currentThread().getName());
            return 4 / 2;
        })
                .thenAccept((result) -> {
                    System.out.println("线程名称："+Thread.currentThread().getName());
                    System.out.println(result);
                });

        Void unused = voidCompletableFuture.get();
    }

    /**
     * 执行完异步任务的回调
     * 无参数没有返回值
     *
     * 回调过程在主线程中
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void exec7() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程名称："+Thread.currentThread().getName());
            return 4 / 2;
        })
                .thenRun(() -> {
                    System.out.println("线程名称："+Thread.currentThread().getName());
                });

        Void unused = voidCompletableFuture.get();
    }

    /**d
     * 执行过程中有异常
     * 入参为异常对象
     * 返回值是在有异常的时候执行的方返回值
     */
    @Test
    public void exec8() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> 2 / 0).exceptionally(e -> {
            System.out.println(e);
            return 0;
        });

        exceptionally.get();
    }


    /**
     * 执行完任务之后调用 两个参数
     * 如果有异常结果为null 另一个参数为异常对象
     * whenComplete  没有返回值
     */

    @Test
    public void exec9(){
        CompletableFuture.supplyAsync(()->2/0)
                .whenComplete((r,e)-> {
                    System.out.println(r);
                    System.out.println(e);
                });
    }

    /**
     * join方法
     * join方法不会抛出异常
     * get方法会抛出异常
     */
    @Test
    public void exec10() throws ExecutionException, InterruptedException {
        Integer join = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3 / 1;
        })
                .thenApply(i -> i * 3).join();

        System.out.println("join:"+join);

        Integer integer = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2 / 2;
        }).thenApply(i -> i * 3).get();

        System.out.println("integer:"+integer);
    }

}
