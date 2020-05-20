package br.com.bexs.dijktra.airport.business;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bexs.dijktra.airport.model.AirportModel;
import br.com.bexs.dijktra.airport.model.UserResponse;
import br.com.bexs.dijktra.airport.repository.AirportFileRepository;

@Service
public class AirportBusiness {

	private static final Long ZERO = 0L;
	private  Map<String,List<AirportModel>> airportGraph;
	private List<AirportModel> airportsVertex;
	
	@Autowired
	private AirportFileRepository airportFileRepository;
	
	
	public void loadAplication(String filePath) throws Exception {
		this.airportFileRepository.setFileFromPath(filePath);
		this.airportGraph = this.airportFileRepository.loadGraphAirports();
		this.airportsVertex = this.buildVertexFromGraph();
	}

	public UserResponse findBestPath(String entryString) {
		//Declaration
		String[] entries = entryString.split("-");
		UserResponse userResponse = new UserResponse(entries);
		//Process
		//INICIALIZA A BUSCA
		this.setupBeginAirport(userResponse.getBegin());
		

		//FOR aqui;
		while(!airportsVertex.isEmpty()) {
			//BUSCA OS ITENS ABERTOS RESTANTES E RETORNA EM ORDEM DE CUSTO
			airportsVertex = this.getOnlyOpenedVertex();
			
			//PEGA O MODELO MAIS PROXIMO E FECHA O VERTICE
			AirportModel current = findNextValidVertex();
						
			//RELAXA BUSCA PELO RELAXAMENTO DOS VERTICES
			airportGraph.get(current.getName()).forEach(node ->{
				//OBTEM O AEROPORTO DA LISTA VERTICES
				AirportModel airportConnection = findOpenedFromNode(node);
				
				//RELAXA OS VERTICES E INDICA O CAMINHO
				if(airportConnection!=null) {
					long currCost = Long.sum(current.getCost(), node.getCost());
					if(airportConnection.getCost()>currCost) {
						StringBuilder sb = new StringBuilder();
						sb.append(current.getPath().concat("-"))
						  .append(airportConnection.getName());
						
						airportConnection.setCost(currCost);
						airportConnection.setPath(sb.toString());
					}
				}
			});
			
			
			if(userResponse.getEnd().equals(current.getName())) {
				userResponse.setCost(current.getCost());
				userResponse.setPath(current.getPath());
				return userResponse;
			}
		}
		return userResponse;
	}
	
	public String addConnection(String connection) throws Exception {
		return airportFileRepository.addConnection(connection);
	}

	private AirportModel findNextValidVertex() {
		 AirportModel current = airportsVertex.stream()
				 .filter(vertex -> !vertex.getCost().equals(Long.MAX_VALUE))
				 .findFirst().orElse(null);		
		if(current!=null)current.setOpened(false);
		return current;
	}

	private List<AirportModel> getOnlyOpenedVertex() {
		return this.airportsVertex.stream()
				.filter(item -> item.isOpened())
				.sorted((x,y)-> x.getCost().compareTo(y.getCost()))
				.collect(Collectors.toList());
	}

	private List<AirportModel> buildVertexFromGraph() {
		//Validade Graph
		return this.airportGraph.keySet().stream()
				.map(new Function<String, AirportModel>(){
					@Override
					public AirportModel apply(String name) {
						AirportModel airport = new AirportModel(name); 
						airport.setCost(Long.MAX_VALUE);
						airport.setOpened(true);
						return airport;
					}
				}).collect(Collectors.toList());
	}

	
	private void setupBeginAirport(String entryAirport) {
		this.airportsVertex.forEach(vertex ->{
			if(vertex.getName().equals(entryAirport)) {
				vertex.setCost(ZERO);
				vertex.setPath(vertex.getName());
			}
		});
	}

	private AirportModel findOpenedFromNode(AirportModel node) {
		return this.airportsVertex.stream()
					.filter(airport -> 
						airport.getName().equals(node.getName())
						&& airport.isOpened())
					.findFirst().orElse(null);
	}


}
