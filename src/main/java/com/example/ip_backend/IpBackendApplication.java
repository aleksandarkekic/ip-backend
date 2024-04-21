package com.example.ip_backend;

import com.example.ip_backend.services.EmailService;
import com.example.ip_backend.services.ProgramService;
import com.example.ip_backend.services.UserService;
import com.example.ip_backend.services.impl.EmailServiceImpl;
import com.example.ip_backend.services.impl.ProgramServiceImpl;
import com.example.ip_backend.services.impl.ScheduledEmailSend;
import com.example.ip_backend.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Timer;

@SpringBootApplication
public class IpBackendApplication {


    public static void main(String[] args) {
		SpringApplication.run(IpBackendApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}

}
