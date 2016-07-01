package com.baifendian;

/**
 * Created by BFD_318 on 2015/5/31.
 */
public class ProducerDemo {
    public static void main(String args[]){
        if (args.length < 4){
            System.out.println("The parameter sequence is : brokerlist,topic,numsOfProducer,numsOfMessage.");
            System.out.println("brokerlist:kafka broker list");
            System.out.println("topic: topic name");
            System.out.println("numsOfProducer:the number of producer thread");
            System.out.println("numsOfMessage:the number of messasge sent in each thread");
        }
        StartThread(args[0],args[1],args[2],args[3]);
    }
  
    public static void StartThread(String brokerlist, String topic,String numsOfProducer,String numsOfMessage) {
        int numberOfProducer = Integer.valueOf(numsOfProducer);
        int numberOfMessage = Integer.valueOf(numsOfMessage);
        for (int i = 1; i <= numberOfProducer; i++) {
            String name = "Producer" + i;
            new Producer(name, topic, numberOfMessage,brokerlist).run();
        }
    }
}
