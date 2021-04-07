package org.accord.platform.models.impl;

import org.accord.platform.models.IRoomModel;

import java.util.HashSet;

public class RoomModel implements IRoomModel {
	private String roomCode;
	private String topic;
	private int roomCount;
	private HashSet<SubmissionModel> submissions = new HashSet<SubmissionModel>();
	private HashSet<String> suggestions = new HashSet<>();
	private boolean closed = false;

	private int duration;

	public RoomModel() {
	}

	public RoomModel(String topic, int duration) {
		this.topic = topic;
		this.duration = duration;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public boolean addSubmission(SubmissionModel submission) {
		if (submissions.contains(submission))
			return false;
		return submissions.add(submission);
	}

	public HashSet<SubmissionModel> getSubmissions() {
		return submissions;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isClosed() {
		return closed;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void setSuggestions(HashSet<String> suggestions) {
		this.suggestions = suggestions;
	}

	@Override
	public HashSet<String> getSuggestions() {
		return this.suggestions;
	}

}