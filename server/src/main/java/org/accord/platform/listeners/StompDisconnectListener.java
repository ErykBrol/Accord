package org.accord.platform.listeners;

import org.accord.platform.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;

@Component
public class StompDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

	@Resource
	RoomService roomService;

	@Resource
	private SimpMessagingTemplate simpMessagingTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(StompDisconnectListener.class);

	private static final String ROOM_CODE = "roomCode";

	public void onApplicationEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String roomCode = (String) headerAccessor.getSessionAttributes().get(ROOM_CODE);
		roomService.userLeft(roomCode);
		simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", roomService.getRoomModel(roomCode));
		LOG.info("Disconnect Event: " + headerAccessor.toString());
	}
}