package org.accord.platform.controllers;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.impl.RoomModel;
import org.accord.platform.services.RoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class RoomController {

	@Resource
	private RoomService roomService;

	@Resource
	private SimpMessagingTemplate simpMessagingTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

	private static final String ROOM_CODE = "roomCode";

	@MessageMapping("/room/create")
	@SendToUser("/queue/room")
	public RoomModel createRoom(SimpMessageHeaderAccessor headerAccessor, RoomModel roomModel) {
		roomService.createRoom(roomModel);
		LOG.info(String.format("Room created with code: %s", roomModel.getRoomCode()));
		joinRoom(headerAccessor, roomModel.getRoomCode());
		// We're using this object just as a test for now to demonstrate sending
		// a message to the client
		return roomModel;
	}

	@MessageMapping("/room/join")
	public void joinRoom(SimpMessageHeaderAccessor headerAccessor, RoomModel roomModel) {
		if (roomService.validateRoom(roomModel)) {
			if (roomService.isClosed(roomModel.getRoomCode())) {
				LOG.info(String.format("Room %s is closed.", roomModel.getRoomCode()));
			} else {
				joinRoom(headerAccessor, roomModel.getRoomCode());
			}
		} else {
			LOG.info(String.format("%s is an invalid room code.", roomModel.getRoomCode()));
			simpMessagingTemplate.convertAndSend("/topic/room/" + roomModel.getRoomCode() + "/status",
					String.format("%s is an invalid room code.", roomModel.getRoomCode()));
		}
	}

	private void joinRoom(SimpMessageHeaderAccessor headerAccessor, String roomCode) {
		roomService.userJoined(roomCode);
		LOG.info(String.format("Room joined with code: %s", roomCode));
		// Keep track of which room a user is in via their session
		Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
		sessionAttributes.put(ROOM_CODE, roomCode);
		headerAccessor.setSessionAttributes(sessionAttributes);
		simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", roomService.getRoomModel(roomCode));
	}

	@MessageMapping("/room/close-room")
	public void start(SimpMessageHeaderAccessor headerAccessor) {
		String roomCode = headerAccessor.getSessionAttributes().get(ROOM_CODE).toString();
		roomService.start(roomCode);
		LOG.info(String.format("Room %s is now closed for joining.", roomCode));
		simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", roomService.getRoomModel(roomCode));
	}
}
