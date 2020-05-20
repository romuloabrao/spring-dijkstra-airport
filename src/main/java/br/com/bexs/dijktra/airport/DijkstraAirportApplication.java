package br.com.bexs.dijktra.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DijkstraAirportApplication {
	
	public static void main(String[] args) {
		ExecuterEnum executer = ExecuterEnum.getExecuter(args[0]);
		
		SpringApplication sp = new SpringApplication(DijkstraAirportApplication.class);
		if(ExecuterEnum.CONSOLE.equals(executer)) {
			sp.setWebApplicationType(WebApplicationType.NONE);
		}
		sp.run(args);
	}

}
