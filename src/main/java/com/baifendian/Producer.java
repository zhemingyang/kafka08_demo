package com.baifendian;

/**
 * Created by BFD_318 on 2015/5/31.
 */

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class Producer extends Thread {
    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final String topic;
    private final String name;
    private final int numsOfMessage;
    private final Properties props = new Properties();

    
    public Producer(String name, String topic, int numsOfMessage) {

        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "192.168.40.158:9092");
        //async send
        props.put("producer.type", "async");
//         the message number each sending 
        props.put("batch.num.messages", "5");
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));

        this.topic = topic;
        this.name = name;
        this.numsOfMessage = numsOfMessage;
    }

    /**
     *
     */
    public void run() {

        int messageNo = 1;
        while (messageNo <= numsOfMessage) { // ；

            String message = new String(name + "'s	Message_" + messageNo + "******");
            KeyedMessage<Integer, String> messageForSend = new KeyedMessage<Integer, String>(topic, message);
            producer.send(messageForSend);
            messageNo++;
            System.out.println("running is in" + messageNo);
        }
        producer.close();
    }
}