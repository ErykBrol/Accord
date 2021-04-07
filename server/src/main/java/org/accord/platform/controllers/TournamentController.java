package org.accord.platform.controllers;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.impl.MatchUpModel;
import org.accord.platform.models.impl.SubmissionModel;
import org.accord.platform.models.impl.TournamentModel;
import org.accord.platform.services.RoomService;
import org.accord.platform.services.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class TournamentController {

	@Resource
	private RoomService roomService;

	@Resource
	private TournamentService tournamentService;

	@Resource
	private SimpMessagingTemplate simpMessagingTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);

	private static final String ROOM_CODE = "roomCode";

	@MessageMapping("/room/setup-tournament")
	public void setUpTournament(SimpMessageHeaderAccessor headerAccessor) {
		String roomCode = headerAccessor.getSessionAttributes().get(ROOM_CODE).toString();
		IRoomModel roomModel = roomService.getRoomModel(roomCode);
		tournamentService.createTournament(roomModel);
		LOG.info(String.format("Setup tournament for room %s", roomCode));
		TournamentModel t = tournamentService.getTournamentModel(roomModel);
		if (t.getMatchUp().getFirst() != null && t.getMatchUp().getSecond() != null) {
			LOG.info(String.format("Current matchup: %s v %s", t.getMatchUp().getFirst().getName(),
					t.getMatchUp().getSecond().getName()));
		}

		if (t.isDone()) {
			LOG.info(String.format("Winner for room %s is %s", roomCode,
					t.getResults().get(t.getResults().size() - 1).getName()));
			simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", t.getResults());
		} else {
			simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", t);
		}
	}

	@MessageMapping("/room/vote")
	public void vote(SimpMessageHeaderAccessor headerAccessor, String submission) {
		String roomCode = headerAccessor.getSessionAttributes().get(ROOM_CODE).toString();
		IRoomModel roomModel = roomService.getRoomModel(roomCode);
		tournamentService.vote(roomModel, submission);
		LOG.info(String.format("Vote received for %s in room with room code %s", submission, roomCode));
		MatchUpModel matchup = tournamentService.getTournamentModel(roomModel).getMatchUp();
		LOG.info(String.format("%s: %d v %s: %d", matchup.getFirst().getName(), matchup.getFirst().getScore(),
				matchup.getSecond().getName(), matchup.getSecond().getScore()));
		if (tournamentService.isMatchUpDone(roomModel)) {
			processMatchUp(headerAccessor);
		}
	}

	public void processMatchUp(SimpMessageHeaderAccessor headerAccessor) {
		String roomCode = headerAccessor.getSessionAttributes().get(ROOM_CODE).toString();
		IRoomModel roomModel = roomService.getRoomModel(roomCode);
		MatchUpModel matchup = tournamentService.processMatchUp(roomModel);
		simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", matchup);

		TournamentModel t = tournamentService.getTournamentModel(roomModel);
		if (t.isDone()) {
			LOG.info(String.format("Winner for room %s is %s", roomCode,
					t.getResults().get(t.getResults().size() - 1).getName()));
			simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", t.getResults());
		}
		if (t.getMatchUp().getFirst() != null && t.getMatchUp().getSecond() != null) {
			LOG.info(String.format("Current matchup: %s v %s", t.getMatchUp().getFirst().getName(),
					t.getMatchUp().getSecond().getName()));
			simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", t.getMatchUp());
		}
	}

}
