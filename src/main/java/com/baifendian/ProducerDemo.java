package com.baifendian;

/**
 * Created by BFD_318 on 2015/5/31.
 */
public class ProducerDemo {
    public static void main(String args[]){
        StartThread(2, "test", 10);
    }
  
    public static void StartThread(int numsOfProducer, String topic,int numsOfMessage) {

        for (int i = 1; i <= numsOfProducer; i++) {
            String name = "Producer" + i;
            new Producer(name, topic, numsOfMessage).run();
        }
    }
}
