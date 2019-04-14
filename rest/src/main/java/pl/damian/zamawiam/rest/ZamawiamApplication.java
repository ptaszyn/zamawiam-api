package pl.damian.zamawiam.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.damian.zamawiam.service.ServiceConfig;

@SpringBootApplication(scanBasePackageClasses = ServiceConfig.class)
public class ZamawiamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZamawiamApplication.class, args);
	}

}
