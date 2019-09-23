package xyz.bumbing.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "xyz.bumbing.http.config")
@SpringBootApplication
public class HttpAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpAppApplication.class, args);
	}

}
