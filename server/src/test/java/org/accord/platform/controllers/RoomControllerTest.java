package org.accord.platform.controllers;


import javax.annotation.Resource;

import org.accord.platform.controllers.RoomController;
import org.accord.platform.models.impl.RoomModel;
import org.accord.platform.services.RoomService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ("/test-context.xml")
public class RoomControllerTest {

	private RoomModel roomModel;

	private static final String ROOM_CODE = "roomCode";

	@Mock
	SimpMessageHeaderAccessor headerAccessor;

	@Resource
	private RoomController roomController;

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
	public void createRoomTest() throws Exception {
		assertMockUserInRoom(null);

		roomController.createRoom(headerAccessor, roomModel);

		assertMockUserInRoom(roomModel.getRoomCode());

	}

	@Test
	public void joinRoomTest() throws Exception {
		assertMockUserInRoom(null);

		roomController.joinRoom(headerAccessor, roomModel);

		assertMockUserInRoom(roomModel.getRoomCode());
	}

	public void assertMockUserInRoom(String roomCode) {
		assertEquals(roomCode, headerAccessor.getSessionAttributes().get(ROOM_CODE));
	}
}
