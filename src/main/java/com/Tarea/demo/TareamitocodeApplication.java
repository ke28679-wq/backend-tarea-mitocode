package com.Tarea.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareamitocodeApplication {
    private static Logger logger = LogManager.getLogger(TareamitocodeApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(TareamitocodeApplication.class, args);
        logger.info("Se inició la aplicación ;)");
	}

}
