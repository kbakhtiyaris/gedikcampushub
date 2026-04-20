package tr.gedik.campushub.GedikCampusHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableJpaRepositories(basePackages = "tr.gedik.campushub.GedikCampusHub.repository")
public class GedikCampusHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GedikCampusHubApplication.class, args);
	}
}
