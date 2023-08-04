package net.javaguides.springbootrestfulwebservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "SpringBoot Restful WebServices Documentation",
				description = "SpringBoot Restful WebServices Documentation Demo",
				version = "v1.0.0",
				contact = @Contact(
						name = "Ramesh",
						email = "javaguides.net@gmail.com",
						url = "https://www.javaguides.net/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SpringBoot User Management Documentation",
				url = "https://www.javaguides.net/user_management.html"
		)
)
@SpringBootApplication
public class SpringbootRestfulWebservicesApplication {

	@Bean
	ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
