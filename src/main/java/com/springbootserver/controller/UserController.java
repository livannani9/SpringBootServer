package com.springbootserver.controller;

import java.util.concurrent.ExecutionException;

import org.apache.http.client.methods.HttpHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootserver.model.User;
import com.springbootserver.service.UserService;

import springfox.documentation.spring.web.json.Json;

@RestController
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	

	@Autowired
	private KafkaTemplate<String ,User> kafkaTemplate;

	

	@PostMapping("/save")
	@ResponseBody
	private ResponseEntity<User> addUser(@RequestBody User user) {

		String TOPIC="test";
		LOG.info("**************Methode started at addUser in Controller");

		User savedUser = service.saveUser(user);

		ListenableFuture<SendResult<String,User>> send = kafkaTemplate.send(TOPIC, user);
		
		try {
			System.out.println("kafka ::"+send.toString()+send.get());
		} catch (InterruptedException e) {
			
			System.out.println("exception  **********");
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			System.out.println("exception 2 **********");
			
			e.printStackTrace();
		}
		
		LOG.info("**************Methode End at addUser in Controller : : " + savedUser.getName());

		
		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
	}
	

}
