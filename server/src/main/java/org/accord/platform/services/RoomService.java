package org.accord.platform.services;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.impl.SubmissionModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import javax.annotation.Resource;

@Component
public class RoomService {
	@Resource
	private SuggestionsService suggestionsService;

	private HashMap<String, IRoomModel> rooms = new HashMap<>();

	public void createRoom(IRoomModel roomModel) {
		String roomCode = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
		roomModel.setRoomCode(roomCode);
		roomModel.setSuggestions(suggestionsService.getSuggestions(roomModel.getTopic()));
		rooms.put(roomCode, roomModel);
	}

	public boolean validateRoom(IRoomModel roomModel) {
		return rooms.keySet().stream().anyMatch(key -> key.equals(roomModel.getRoomCode()));
	}

	public void userJoined(String roomCode) {
		IRoomModel room = rooms.get(roomCode);
		if (room == null)
			throw new IllegalArgumentException();
		room.setRoomCount(room.getRoomCount() + 1);
	}

	public void userLeft(String roomCode) {
		IRoomModel room = rooms.get(roomCode);
		room.setRoomCount(room.getRoomCount() - 1);
	}

	public IRoomModel getRoomModel(String roomCode) {
		return rooms.get(roomCode);
	}

	public void start(String roomCode) {
		rooms.get(roomCode).setClosed(true);
	}

	public boolean isClosed(String roomCode) {
		return rooms.get(roomCode).isClosed();
	}

	public boolean makeSubmission(String roomCode, SubmissionModel submission) {
		return rooms.get(roomCode).addSubmission(submission);
	}

}
