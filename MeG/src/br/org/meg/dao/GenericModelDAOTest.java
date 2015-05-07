package org.meg.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.meg.exception.DAOException;

public class GenericModelDAOTest {
	private GenericModelDAO sectionDao;

	@Before
	public void setUp() throws Exception {
		this.sectionDao = new GenericModelDAO(EnumTable.SECTION);
	}
	
	@Test
	public void testGetName() {
		assertEquals("Construção", sectionDao.getNameFromID(6));
	}
	
	@Test(expected=DAOException.class)
	public void testGetNameException(){
		sectionDao.getNameFromID(404);
	}

	@Test
	public void testGetID() {
		assertEquals(6, sectionDao.getIDFromName("Construção"));
	}

	@Test(expected=DAOException.class)
	public void testGetIDException(){
		sectionDao.getIDFromName("Inexist");
	}
}
