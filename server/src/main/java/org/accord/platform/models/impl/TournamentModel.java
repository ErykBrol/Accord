package org.accord.platform.models.impl;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.ITournamentModel;

import java.util.ArrayList;

public class TournamentModel implements ITournamentModel{

	private ArrayList<SubmissionModel> contenders = new ArrayList<>();
	private ArrayList<SubmissionModel> results = new ArrayList<>();
	private ArrayList<SubmissionModel> currentRound = new ArrayList<>();
	private MatchUpModel matchup;

	public TournamentModel(IRoomModel roomModel) {
		currentRound.addAll(roomModel.getSubmissions());
	}

	public ArrayList<SubmissionModel> getContenders() {
		return contenders;
	}

	public ArrayList<SubmissionModel> getResults() {
		return results;
	}

	public ArrayList<SubmissionModel> getCurrentRound() {
		return currentRound;
	}

	public MatchUpModel getMatchUp() {
		return matchup;
	}

	public void setMatchUp(MatchUpModel matchup) {
		this.matchup = matchup;
	}

	public boolean isDone() {
		return contenders.size() == 0 && currentRound.size() == 0 && matchup.getFirst() == null
				&& matchup.getSecond() == null;
	}

}
