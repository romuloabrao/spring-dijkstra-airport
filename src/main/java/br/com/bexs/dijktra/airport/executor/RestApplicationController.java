package br.com.bexs.dijktra.airport.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bexs.dijktra.airport.business.AirportBusiness;
import br.com.bexs.dijktra.airport.helper.UserEntryBuilder;
import br.com.bexs.dijktra.airport.model.UserEntry;

@RestController
@ConditionalOnWebApplication
public class RestApplicationController {

	@Autowired
	private AirportBusiness airportBusines;
	
	@Value("${filePath}")
	private String filePath;
	
	@GetMapping(path ="/dijkstra/airport/findBestPath/{entries}")
	public String findBestPath(@PathVariable final String entry){
		try {
			UserEntry userEntry = UserEntryBuilder.buildQueryEntry(entry);
			this.airportBusines.loadAplication(filePath);
			return this.airportBusines.findBestPath(userEntry).toString();
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	@PostMapping(path = "/dijkstra/airport/addConnection")
	public String addConnection(@RequestBody final String entry) {
		try {
			UserEntry userEntry = UserEntryBuilder.buildInsertEntry(entry);
			this.airportBusines.loadAplication(filePath);
			return this.airportBusines.addConnection(userEntry).toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
}
