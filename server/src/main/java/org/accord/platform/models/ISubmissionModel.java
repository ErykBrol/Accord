package org.accord.platform.models;

public interface ISubmissionModel {

    public String getName();

    public int getScore();

    public void setScore(int score);

    public void incrementScore();

}
