package br.com.bexs.dijktra.airport.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import br.com.bexs.dijktra.airport.model.AirportModel;

@Component
public class AirportFileRepository {
	
	private File file;
	private final String SEPARATOR =",";
	
	public Map<String, List<AirportModel>> loadGraphAirports() throws Exception{
		Map<String, List<AirportModel>> graphAirports = new HashMap<String, List<AirportModel>>();
		try{
			BufferedReader reader = Files.newBufferedReader(file.toPath());
			this.buildGraph(graphAirports, reader.lines().collect(Collectors.toList()));
			reader.close();
			
		}catch(Exception e){
			 throw new RuntimeException("Erro criar o grafo",e);
		}
		return graphAirports;
	}

	public String addConnection(String connection) throws Exception{
		try{
			this.validateGraphStrutcture(connection);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.file,true));
			writer.newLine();
			writer.append(connection);
			writer.close();
		}catch(Exception e){
			 throw new RuntimeException("Erro criar o grafo",e);
		}
		return "Conexao Inserida com Sucesso";
	}

	private void validateGraphStrutcture(String connection) throws IOException {
		Map<String, List<AirportModel>> graphAirports = new HashMap<String, List<AirportModel>>();
		BufferedReader reader = Files.newBufferedReader(file.toPath());
		List<String> lines = reader.lines().collect(Collectors.toList());
		reader.close();
		lines.add(connection);
		this.buildGraph(graphAirports,lines);
	}
	
	private void buildGraph(Map<String, List<AirportModel>> graphAirports, List<String> lines) {
		lines.forEach(line ->{
			String[] lineItens = this.buildValidLine(line);
			String cityA = lineItens[0];
			String cityB = lineItens[1];
			Long cost = Long.parseLong(lineItens[2]);
			
			this.insertOnGraph(graphAirports, cityA, cityB, cost);
			this.insertOnGraph(graphAirports, cityB, cityA, cost);
		});
	}


	private void insertOnGraph(Map<String, List<AirportModel>> graphAirports, String cityA, String cityB, Long cost) {
		if(!graphAirports.containsKey(cityA)) {
			graphAirports.put(cityA, new ArrayList<AirportModel>());
		}
		
		AirportModel  airportNode = new AirportModel(cityB);
		airportNode.setCost(cost);
		if(graphAirports.get(cityA).contains(airportNode))
			throw new RuntimeException("Grafo invalido: Existem registros duplicados");
		graphAirports.get(cityA).add(airportNode);
	}


	private String[] buildValidLine(String line) {
		// TODO Validar inconsistencias 
		String[] itens = line.split(SEPARATOR);
		return itens;
	}


	public void setFileFromPath(String filePath) {
		this.file = new File(filePath);
	}





}
