package com.testproject.userservice.service;

//import com.testproject.userservice.DTO.KafkaDTO;
//import com.testproject.userservice.entity.CustomUser;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaService {
//    private KafkaTemplate<String, KafkaDTO> kafkaTemplate;
//
//    public KafkaService(KafkaTemplate<String, KafkaDTO> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void CustomUserInfo(KafkaDTO kafkaDTO) {
//
//        kafkaTemplate.send("user-card", kafkaDTO); // если раздел 1, то можно без ключа и раздела
//    }
//}
    // send(String topic, K key, @Nullable V data) обычно так
    // send(String topic, jInteger partition, K key, @Nullable V data либо так
//    @KafkaListener(topicPartitions =
//    @TopicPartition(topic = "user-card", partitions = "0"),
//            groupId = "order")
    /*@KafkaListener(topicPartitions =
            {
                    @TopicPartition(topic = "user-card", partitions = "0"),
                    @TopicPartition(topic = "user-card", partitions = "3")
            })*/

    // consumer01 ->  [partition-0] <- consumer02
    // group order:
    // consumer01
    // consumer02

