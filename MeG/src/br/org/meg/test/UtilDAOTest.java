package org.meg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.meg.dao.UtilDAO;

public class UtilDAOTest {
	private UtilDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new UtilDAO();
	}

	@Test
	public void getStateAbbreviationTest(){
		String stateAbbreviation = "AC";
		assertEquals(dao.getStateAbbreviation(1), stateAbbreviation);
	}
	
	@Test
	public void getMinimumWageTest(){
		int year = 2008;
		assertEquals(dao.getMinimumWage(year), 415.00 , 0.001);
	}
	
	@Test
	public void getErrorsTest(){
		assertNotNull(dao.getErrors());
	}

	@Test
	public void getHistoryTest(){
		int id = 1;
		assertNotNull(dao.getHistory(id));
	}
}
