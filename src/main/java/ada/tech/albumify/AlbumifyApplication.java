package ada.tech.albumify;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
@EntityScan(basePackages = "ada.tech.albumify.domain.entities")
*/

@SpringBootApplication
@RequiredArgsConstructor
@EnableFeignClients
public class AlbumifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumifyApplication.class, args);
		System.out.println("Running");
	}

}
