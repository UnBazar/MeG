package org.meg.test;

import org.junit.Before;
import org.junit.Test;
import org.meg.exception.QuebraSistemaException;
import org.meg.model.Administrator;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Section;

public class ModelExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=QuebraSistemaException.class)
	public void testDescricaoException() {
		Description descricao = new Description();
		descricao.setId(0);
	}
}
