package org.accord.platform.models.impl;

import org.accord.platform.models.IMatchUpModel;

public class MatchUpModel implements IMatchUpModel {
	private SubmissionModel first;
	private SubmissionModel second;
	private SubmissionModel winner;

	public MatchUpModel(SubmissionModel first, SubmissionModel second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public SubmissionModel getFirst() {
		return first;
	}

	@Override
	public SubmissionModel getSecond() {
		return second;
	}

	@Override
	public void setFirst(SubmissionModel sm) { first = sm; }

	@Override
	public void setSecond(SubmissionModel sm) {
		second = sm;
	}

	@Override
	public SubmissionModel getWinner() {
		return winner;
	}

	@Override
	public void setWinner(SubmissionModel sm) {
		winner = sm;
	}
	
}
