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

@RestController
@ConditionalOnWebApplication
public class RestApplicationController {

	@Autowired
	private AirportBusiness airportBusines;
	
	@Value("${filePath}")
	private String filePath;
	
	@GetMapping(path ="/dijkstra/airport/findBestPath/{entries}")
	public String findBestPath(@PathVariable final String entries){
		try {
			this.airportBusines.loadAplication(filePath);
			return this.airportBusines.findBestPath(entries).toString();
		} catch (Exception e) {
			return "erroMerda";
		}
		
	}
	
	@PostMapping(path = "/dijkstra/airport/addConnection")
	public String addConnection(@RequestBody final String connection) {
		try {
			this.airportBusines.loadAplication(filePath);
			return this.airportBusines.addConnection(connection).toString();
		} catch (Exception e) {
			return "erroMerda";
		}
	}
	
	
}
