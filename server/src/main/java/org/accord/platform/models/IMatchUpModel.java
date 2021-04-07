package org.accord.platform.models;

import org.accord.platform.models.impl.SubmissionModel;

public interface IMatchUpModel {
	SubmissionModel getFirst();
	
	void setFirst(SubmissionModel sm);
	
	SubmissionModel getSecond();
	
	void setSecond(SubmissionModel sm);
	
	SubmissionModel getWinner();
	
	void setWinner(SubmissionModel sm);
}
