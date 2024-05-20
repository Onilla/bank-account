package com.testproject.userservice.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

//@Service
//public class KafkaServiceListener {
//    @KafkaListener(topicPartitions = @TopicPartition(topic = "user-card", partitions = "0"),
//    groupId = "user")
//    public void readData(KafkaDTO kafkaDTO) {
//
//    }
//}
