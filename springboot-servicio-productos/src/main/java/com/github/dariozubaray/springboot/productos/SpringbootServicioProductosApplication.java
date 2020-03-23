package com.github.dariozubaray.springboot.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // No es obligacion anotar la app como cliente, si esta en el path lo va a ser
@SpringBootApplication
@EntityScan({"com.github.dariozubaray.springboot.commons.models.entity"})
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
