package sample.pre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // TODO: 별도 Config로 분리하는게 좋을 듯?
@SpringBootApplication
public class PreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreApplication.class, args);
	}
}
