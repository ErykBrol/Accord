package org.accord.platform.services;

import org.accord.platform.models.IRoomModel;
import org.accord.platform.models.ISubmissionModel;
import org.accord.platform.models.impl.MatchUpModel;
import org.accord.platform.models.impl.SubmissionModel;
import org.accord.platform.models.impl.TournamentModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class TournamentService {
	private HashMap<IRoomModel, TournamentModel> tournaments = new HashMap<>();

	public void createTournament(IRoomModel roomModel) {
		TournamentModel tournament;
		if (roomModel.getSubmissions().size() == 1) {
			SubmissionModel winner = (new ArrayList<SubmissionModel>(roomModel.getSubmissions())).get(0);
			roomModel.getSubmissions().clear();
			tournament = new TournamentModel(roomModel);
			tournament.getResults().add(winner);
		} else {
			tournament = new TournamentModel(roomModel);
		}

		tournament.setMatchUp(new MatchUpModel(getNextMatchUp(tournament.getCurrentRound()),
				getNextMatchUp(tournament.getCurrentRound())));
		tournaments.put(roomModel, tournament);

	}

	public MatchUpModel processMatchUp(IRoomModel roomModel) {
		TournamentModel tournament = tournaments.get(roomModel);
		ArrayList<SubmissionModel> contenders = tournament.getContenders();
		ArrayList<SubmissionModel> results = tournament.getResults();
		ArrayList<SubmissionModel> currentRound = tournament.getCurrentRound();
		MatchUpModel matchup = tournament.getMatchUp();

		SubmissionModel first = matchup.getFirst();
		SubmissionModel second = matchup.getSecond();

		if (first.getScore() > second.getScore()) {
			contenders.add(first);
			matchup.setWinner(first);
			results.add(second);
		} else {
			contenders.add(second);
			matchup.setWinner(second);
			results.add(first);
		}
		first.setScore(0);
		second.setScore(0);
		MatchUpModel prevMatchup = matchup;

		// Start next matchup.
		tournament.setMatchUp(new MatchUpModel(getNextMatchUp(currentRound), getNextMatchUp(currentRound)));
		matchup = tournament.getMatchUp();
		first = matchup.getFirst();
		second = matchup.getSecond();
		if (first == null && second == null) {
			if (contenders.size() == 1 && currentRound.size() == 0) {
				results.add(contenders.remove(0));
			} else {
				tournament.setMatchUp(setUpNextRound(currentRound, contenders));
			}
		} else if (first == null) {
			matchup.setFirst(contenders.remove(0));
		} else if (second == null) {
			matchup.setSecond(contenders.remove(0));
		}

		return prevMatchup; // previous matchup info to return to clients.
	}

	private SubmissionModel getNextMatchUp(ArrayList<SubmissionModel> currentRound) {
		if (currentRound.size() == 0) {
			return null;
		} else {
			return currentRound.remove(0);
		}
	}

	private MatchUpModel setUpNextRound(ArrayList<SubmissionModel> currentRound,
			ArrayList<SubmissionModel> contenders) {
		currentRound.addAll(contenders);
		contenders.clear();
		return new MatchUpModel(getNextMatchUp(currentRound), getNextMatchUp(currentRound));
	}

	public void vote(IRoomModel roomModel, String submission) {
		MatchUpModel matchUp = tournaments.get(roomModel).getMatchUp();
		if (matchUp.getFirst().getName().equals(submission)) {
			matchUp.getFirst().incrementScore();
		} else if (matchUp.getSecond().getName().equals(submission)) {
			matchUp.getSecond().incrementScore();
		}
	}

	public TournamentModel getTournamentModel(IRoomModel roomModel) {
		return tournaments.get(roomModel);
	}

	public boolean isMatchUpDone(IRoomModel roomModel) {
		MatchUpModel matchUp = tournaments.get(roomModel).getMatchUp();
		return roomModel.getRoomCount() == matchUp.getFirst().getScore() + matchUp.getSecond().getScore();
	}

}
