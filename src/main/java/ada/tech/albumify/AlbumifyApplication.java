package ada.tech.albumify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/*
@EntityScan(basePackages = "ada.tech.albumify.domain.entities")
*/

@SpringBootApplication
public class AlbumifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumifyApplication.class, args);
		System.out.println("Running");
	}

}
