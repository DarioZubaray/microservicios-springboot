package com.github.dariozubaray.springboot.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient // No es obligacion anotar la app como cliente, si esta en el path lo va a ser
@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemsApplication.class, args);
	}

}
