package org.accord.platform.models;

import org.accord.platform.models.impl.MatchUpModel;
import org.accord.platform.models.impl.SubmissionModel;

import java.util.ArrayList;

public interface ITournamentModel {

    public ArrayList<SubmissionModel> getContenders();

    public ArrayList<SubmissionModel> getResults();

    public ArrayList<SubmissionModel> getCurrentRound();

    public MatchUpModel getMatchUp();

    public void setMatchUp(MatchUpModel matchup);

    public boolean isDone();

}
