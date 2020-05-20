package br.com.bexs.dijktra.airport;

import java.util.Arrays;

public enum ExecuterEnum{
	REST,
	CONSOLE;
	
	public static ExecuterEnum getExecuter(String type) {
		ExecuterEnum result = Arrays.stream(ExecuterEnum.values())
				.filter( item -> item.toString().equalsIgnoreCase(type))
				.findFirst().orElse(null);
		if(null==result) throw new RuntimeException("Tipo de Parametro Inválido, por favor escolha \"rest\" ou \"console\"");
		return result;
	}
}