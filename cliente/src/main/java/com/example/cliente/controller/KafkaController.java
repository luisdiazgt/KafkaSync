package com.example.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.model.KafkaMensaje;
import com.example.cliente.service.KafkaService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
	KafkaService producerService;

    @PostMapping(value = "/text")
	public ResponseEntity<?> sendMessageToKafkaTopic(@RequestBody KafkaMensaje message) throws Exception{

		log.info("Cliente - Mensaje enviado. Mensaje: " + message.getMensaje());

		Object sendReply = producerService.kafkaRequestReply(message.getMensaje());

		log.info("Cliente - Mensaje recibido. Mensaje: " + sendReply.toString());

		return ResponseEntity.ok().body(String.valueOf(sendReply));		
	}
    
}
