package com.example.servidor.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class KafkaEvent {
    // KafkaListener echoes the correlation ID and determines the reply topic
    @KafkaListener(groupId="${myproject.consumer-group}", topics = "${myproject.send-topics}")
    @SendTo
    public Message<?> listen(ConsumerRecord<String, Object> consumerRecord) throws InterruptedException {

        String mensaje = String.valueOf(consumerRecord.value());

        log.info("Servidor - Mensaje procesado. Mensaje: " + mensaje);
        
        if(mensaje.equals("uno")){
            // Thread.sleep(15000);
            mensaje = mensaje + "_dos";
        }
        if(mensaje.equals("dos")){
            // Thread.sleep(10000);
            mensaje = mensaje + "_cuatro";
        }
        if(mensaje.equals("tres")){
            // Thread.sleep(5000);
            mensaje = mensaje + "_seis";
        }        

        // String reversedString = new StringBuilder( mensaje ).reverse().toString();
        return MessageBuilder.withPayload( mensaje )
                .build();
        
    }
}
