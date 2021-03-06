package com.epam.services.implementation.initialize;

import com.epam.model.Users;
import com.epam.repo.UserRepository;
import org.apache.spark.sql.SparkSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @EventListener(ContextRefreshedEvent.class)
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

}
