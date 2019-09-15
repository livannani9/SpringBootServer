package com.springbootserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springbootserver.model.User;
import com.springbootserver.repository.UserRepository;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	

	public User saveUser(User user) {

		LOG.info("****** save User Methode Start in Service *************");
		User save = repository.save(user);
		LOG.info("****** save User Methode End  in Service ************* ::"+save.getName());
		
		return save;
	}



}
