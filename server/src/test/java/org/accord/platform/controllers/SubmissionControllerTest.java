package org.accord.platform.controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.accord.platform.models.impl.RoomModel;
import org.accord.platform.models.impl.SubmissionModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class SubmissionControllerTest {
	private RoomModel roomModel;

	@Mock
	SimpMessageHeaderAccessor headerAccessor;

	@Resource
	SubmissionController sc;
	@Resource
	RoomController rc;

	@Before
	public void setUp() {
		roomModel = new RoomModel("Test", 30);

		Map<String, Object> sessionAttributes = new HashMap<String, Object>();
		Mockito.when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);
		Mockito.doCallRealMethod().when(headerAccessor).setSessionAttributes(sessionAttributes);
	}

	@After
	public void tearDown() {
		roomModel = null;
	}

	@Test
	public void submissionTest() throws Exception {
		// create a test room to submit to.
		rc.createRoom(headerAccessor, roomModel);

		// make a submission and make sure the room now has 1 submission.
		sc.makeSubmission(headerAccessor, new SubmissionModel("popeye's catering"));
		assertEquals(roomModel.getSubmissions().size(), 1);
		
		sc.makeSubmission(headerAccessor, new SubmissionModel("subway's catering"));
		assertEquals(roomModel.getSubmissions().size(), 2);

		sc.makeSubmission(headerAccessor, new SubmissionModel("subway's catering"));
		assertEquals(roomModel.getSubmissions().size(), 2);
	}

}
