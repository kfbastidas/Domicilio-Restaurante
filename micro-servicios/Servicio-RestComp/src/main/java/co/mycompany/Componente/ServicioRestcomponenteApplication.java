package co.mycompany.Componente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author kevith Felipe Bastidas
 *
 */
@SpringBootApplication
@EnableFeignClients
public class ServicioRestcomponenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRestcomponenteApplication.class, args);
	}

}
