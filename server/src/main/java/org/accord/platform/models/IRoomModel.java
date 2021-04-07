package org.accord.platform.models;

import org.accord.platform.models.impl.SubmissionModel;

import java.util.HashSet;

public interface IRoomModel {

    String getTopic();

    void setTopic(String topic);

    String getRoomCode();

    void setRoomCode(String roomCode);

    int getRoomCount();

    void setRoomCount(int roomCount);

    boolean addSubmission(SubmissionModel submission);

    HashSet<SubmissionModel> getSubmissions();

    public void setClosed(boolean closed);

    public boolean isClosed();

    void setDuration(int duration);

    int getDuration();
    
    void setSuggestions(HashSet<String> suggestions);
    
    HashSet<String> getSuggestions();
}
