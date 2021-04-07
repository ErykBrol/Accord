package org.accord.platform.models.impl;

public class SubmissionModel {

    private String name;
    private int score = 0;

    public SubmissionModel() {
    }

    public SubmissionModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        score += 1;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof SubmissionModel &&
                ((SubmissionModel) object).getName().equals(getName())
                && ((SubmissionModel) object).getScore() == getScore());
    }

    @Override
    public int hashCode() {
        System.out.println(name.hashCode() + score);
        return name.hashCode() + score;
    }

}
