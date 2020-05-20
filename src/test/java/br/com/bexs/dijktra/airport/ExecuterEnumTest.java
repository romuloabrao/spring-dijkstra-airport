package br.com.bexs.dijktra.airport;

import org.junit.Assert;
import org.junit.Test;



class ExecuterEnumTest {

	@Test
	public void returnValidParameters() {
		Assert.assertTrue(ExecuterEnum.CONSOLE.equals(ExecuterEnum.getExecuter("console")));
		Assert.assertTrue(ExecuterEnum.REST.equals(ExecuterEnum.getExecuter("rest")));
	}
	
	@Test(expected = RuntimeException.class )
	public void returnException() {
		ExecuterEnum.getExecuter("erro");
	}

}
