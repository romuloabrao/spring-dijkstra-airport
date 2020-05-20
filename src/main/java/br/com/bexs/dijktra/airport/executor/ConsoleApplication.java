package br.com.bexs.dijktra.airport.executor;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import br.com.bexs.dijktra.airport.business.AirportBusiness;
import br.com.bexs.dijktra.airport.model.UserResponse;

@Component
@ConditionalOnNotWebApplication
public class ConsoleApplication implements CommandLineRunner{

	@Autowired
	private AirportBusiness airportBusines;
	
	@Value("${filePath}")
	private String filePath;


	@Override
	public void run(String... args) throws Exception {
		try {
			//DECLARATION
			this.airportBusines.loadAplication(filePath);
			//PROCESSOR
			System.out.print("Selecione a origem e Destino conforme o template: \"ORI-DST\" ");
		    Scanner s = new Scanner(System.in); // Creates a new Scanner object
		    String entryString = this.buildValidEntry(s.next()); // Scans the number using a method in the Scanner object and assigns it to a variable.
		    s.close();
		    
		    UserResponse userResponse = this.airportBusines.findBestPath(entryString);
		    //RESULT VALIDATION
		    System.out.println("Melhor caminho: ".concat(userResponse.toString()));
		     
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String buildValidEntry(String next) {
		// TODO Auto-generated method stub
		return next;
	}

}
