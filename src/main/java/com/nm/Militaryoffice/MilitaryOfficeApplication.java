package com.nm.Militaryoffice;

import com.nm.Militaryoffice.model.User;
import com.nm.Militaryoffice.model.UserRole;
import com.nm.Militaryoffice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MilitaryOfficeApplication {
	public static void main(String[] args) {

		SpringApplication.run(MilitaryOfficeApplication.class, args);
	}
}

