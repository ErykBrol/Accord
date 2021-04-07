package org.accord.platform.controllers;

import org.accord.platform.models.impl.SubmissionModel;
import org.accord.platform.services.RoomService;
import org.accord.platform.services.SuggestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class SubmissionController {

    @Resource
    private RoomService roomService;
    
    @Resource
    private SuggestionsService suggestionsService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(SubmissionController.class);

    private static final String ROOM_CODE = "roomCode";

    @MessageMapping("/room/submission")
    public void makeSubmission(SimpMessageHeaderAccessor headerAccessor, SubmissionModel submission) {
        String roomCode = headerAccessor.getSessionAttributes().get(ROOM_CODE).toString();
        LOG.info(String.format("Submission made to room with room code: %s", roomCode));
        if (roomService.makeSubmission(roomCode, submission)) {
            LOG.info(String.format("Submission made to room with room code: %s", roomCode));
            simpMessagingTemplate.convertAndSend("/topic/room/" + roomCode + "/status", submission);
        }
        suggestionsService.addSuggestion(roomService.getRoomModel(roomCode).getTopic(), submission.getName());
    }

}
