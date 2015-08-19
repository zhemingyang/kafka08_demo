package com.baifendian;

/**
 * Created by BFD_318 on 2015/4/20.
 */
public class ConsumerDemo extends Thread{
    public static void main(String args[]){
        Consumer consumer=new Consumer("test1");
        Thread thread=new Thread(consumer);
        thread.start();
    }
}
