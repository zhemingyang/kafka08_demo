package com.baifendian;

/**
 * Created by BFD_318 on 2015/4/20.
 */
public class ConsumerDemo extends Thread{
    public static void main(String args[]){
        if(args.length < 4){
            System.out.println("the args is lesser than 4!");
            System.out.println("The parameter sequence is : topic ,groupid ,zk_address , offset_reset");
            System.out.println("topic : topic-name");
            System.out.println("groupid : the consumer group identification.The default value is 'testgrp'");
            System.out.println("zk_address : the zookeeper address used by kafka.The default value is 'localhost:2181'");
            System.out.println("offset_reset : 'smallest' or 'largest'.The default value is 'largest'");
            System.exit(1);
        }
        String topic = args[0];
        String groupid = args[1];
        String zk_address = args[2];
        String offset_reset = args[3];
        Consumer consumer=new Consumer(topic,groupid,zk_address,offset_reset);
        new Thread(consumer).start();

    }
}
