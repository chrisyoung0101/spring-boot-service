package com.claim.springbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages="com.claim")
@SpringBootApplication
public class SpringBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackEndApplication.class, args);
	}

}


/*Postman
 *http://localhost:8080/submitStudentDetails
 *raw -> JSON
 *{
	"firstName":"Eddie",
	"lastName":"Teddy",
	"email":"text@email.com",
	"int":35,
	"telephone":"6024566"
}
*/