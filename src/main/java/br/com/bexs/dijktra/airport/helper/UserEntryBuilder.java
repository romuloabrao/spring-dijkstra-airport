package br.com.bexs.dijktra.airport.helper;

import java.util.regex.Pattern;

import br.com.bexs.dijktra.airport.model.UserEntry;

public class UserEntryBuilder {

	private final static String ENTRY_QUERY_VALIDATOR = "[A-Z]{3}-[A-Z]{3}";
	private final static String ENTRY_INSERT_VALIDATOR = "[A-Z]{3}\\,[A-Z]{3}\\,[0-9]{1,3}";
	
	public static UserEntry buildQueryEntry(String entry) {
		//SANITY CHECK
		if(!Pattern.matches(ENTRY_QUERY_VALIDATOR, entry)||entry.length()>11) throw new RuntimeException("parametros de consulta inválida: ".concat(entry));
		String[] entries = entry.split("-");
		return new UserEntry(entries[0],entries[1],null);
	}

	public static UserEntry buildInsertEntry(String entry) {
		//SANITY CHECK
		if(!Pattern.matches(ENTRY_INSERT_VALIDATOR, entry)||entry.length()>7) throw new RuntimeException("parametros de inserção inválidos: ".concat(entry));
		String[] entries = entry.split(",");
		if(!(Long.parseLong(entries[2])>0)) throw new RuntimeException("custo da conexão não pode ser 0");
		return new UserEntry(entries[0],entries[1],Long.parseLong(entries[2]));
	}
	
	
}
