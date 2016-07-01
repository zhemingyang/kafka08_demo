
package com.baifendian;


import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Consumer extends Thread
{
    private final ConsumerConnector consumer;
    private final String topic;
    private String groupid = "testgrp";
    private String zk_address = "localhost:2181";
    private String offset_reset = "largest";

    public Consumer(String topic,String groupid,String zk_address,String offset_reset)
    {
        this.topic = topic;
        this.groupid = groupid;
        this.zk_address = zk_address;
        this.offset_reset = offset_reset;
        Properties props = new Properties();
        props.put("zookeeper.connect",zk_address);
        props.put("group.id",groupid);
        props.put("zookeeper.session.timeout.ms", "6000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", offset_reset);
        ConsumerConfig config = new ConsumerConfig(props);
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                config);
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream =  consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while(it.hasNext())
            System.out.println("the message is :"+new String(it.next().message()));
    }
}
