package org.accord.platform.services;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.impl.RoomModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ("/test-context.xml")
public class RoomServiceTest {
	
    private IRoomModel roomModel;
    
    @Resource
    private RoomService roomService;

    @Before
    public void setUp() {
        roomModel = new RoomModel("Test", 30);
    }

    @After
    public void tearDown() {
        roomModel = null;
    }


    @Test
    public void createRoomTest() throws Exception {
        roomService.createRoom(roomModel);
        assertTrue(roomService.validateRoom(roomModel));
    }

    @Test
    public void validateRoomTest() {
        assertFalse(roomService.validateRoom(roomModel));
    }

    @Test
    public void checkRoomCount() throws Exception {
        roomService.createRoom(roomModel);
        String roomCode = roomModel.getRoomCode();
        assertEquals(0, roomService.getRoomModel(roomCode).getRoomCount());
        roomService.userJoined(roomCode);
        assertEquals(1, roomService.getRoomModel(roomCode).getRoomCount());
        roomService.userLeft(roomCode);
        assertEquals(0, roomService.getRoomModel(roomCode).getRoomCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void joinInvalidRoomShouldThrowException() throws Exception {
        roomService.userJoined("Hello World");
    }

}