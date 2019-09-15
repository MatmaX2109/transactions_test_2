package com.parent.validation.kafka;

import com.parent.validation.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    /**
     * Produce mesajul si il pune in coada
     * @param transaction
     */
    public void produceMessage(Transaction transaction){
        LOG.info("Adauga in coada: ", transaction + " cu topicul: "+ topic);

        Message<Transaction> message = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
    }
}
