package org.meg.test;

import org.junit.Before;
import org.junit.Test;
import org.meg.exception.SystemBreakException;
import org.meg.model.Description;
import org.meg.model.Section;
import org.meg.model.State;

public class ModelExceptionTest {
	private Description description;
	private Section section;
	private State state;

	@Before
	public void setUp() throws Exception {
		this.description = new Description();
		this.section = new Section();
		this.state = new State();
	}

	@Test
	public void testSetId() {
		// Valid id
		description.setId(3);
		section.setId(7);
		state.setId(7);
	}

	@Test(expected = SystemBreakException.class)
	public void testDescriptionException() {
		description.setId(-1);
	}
	
	@Test(expected = SystemBreakException.class)
	public void testSectionException() {
		section.setId(-1);
	}
	
	@Test(expected = SystemBreakException.class)
	public void testStateException() {
		state.setId(-1);
	}
}
