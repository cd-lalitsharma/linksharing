package com.ttn.linksharing;

import com.ttn.linksharing.controller.SignupController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ttn.linksharing.repository"})
public class LinksharingApplication {

	public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
	    new File(SignupController.photoUploadDir).mkdirs();
		SpringApplication.run(LinksharingApplication.class, args);
	}

}
