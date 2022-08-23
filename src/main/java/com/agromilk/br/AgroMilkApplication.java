package com.agromilk.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		AgroMilkApplication.class,
		Jsr310JpaConverters.class
})
public class AgroMilkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgroMilkApplication.class, args);
	}

}
