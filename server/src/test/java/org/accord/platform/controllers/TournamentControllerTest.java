package org.accord.platform.controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.accord.platform.Application;
import org.accord.platform.models.impl.MatchUpModel;
import org.accord.platform.models.impl.RoomModel;
import org.accord.platform.models.impl.SubmissionModel;
import org.accord.platform.models.impl.TournamentModel;
import org.accord.platform.services.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TournamentControllerTest {
	@Mock
	SimpMessageHeaderAccessor headerAccessor;

	@Resource
	private TournamentService ts; // just to get TournamentModels.

	@Resource
	private TournamentController tc;
	@Resource
	private SubmissionController sc;
	@Resource
	private RoomController rc;

	private RoomModel roomModel;

	@Before
	public void setUp() throws Exception {
		roomModel = new RoomModel("Test", 30);

		Map<String, Object> sessionAttributes = new HashMap<String, Object>();
		Mockito.when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

		// create a test room to submit to.
		rc.createRoom(headerAccessor, roomModel);
	}

	@After
	public void tearDown() {
		roomModel = null;

	}

	@Test
	public void testInitTournament() throws Exception {
		sc.makeSubmission(headerAccessor, new SubmissionModel("popeye's catering"));
		sc.makeSubmission(headerAccessor, new SubmissionModel("subway's catering"));

		tc.setUpTournament(headerAccessor);

		MatchUpModel matchup = ts.getTournamentModel(roomModel).getMatchUp();
		assertNotEquals(null, matchup);
		assertEquals(0, matchup.getFirst().getScore());
		assertEquals(0, matchup.getSecond().getScore());
		assertTrue(matchup.getFirst().getName().equals("popeye's catering")
				|| matchup.getFirst().getName().equals("subway's catering"));
		assertTrue(matchup.getSecond().getName().equals("popeye's catering")
				|| matchup.getSecond().getName().equals("subway's catering"));
	}

	@Test
	public void testVote() throws Exception {
		// make submissions.
		sc.makeSubmission(headerAccessor, new SubmissionModel("popeye's catering"));
		sc.makeSubmission(headerAccessor, new SubmissionModel("subway's catering"));

		tc.setUpTournament(headerAccessor);
		tc.vote(headerAccessor, "popeye's catering");

		TournamentModel tournament = ts.getTournamentModel(roomModel);
		assertTrue(tournament.isDone());
		assertEquals("popeye's catering", tournament.getResults().get(tournament.getResults().size() - 1).getName());
	}

	@Test
	public void testTournament() throws Exception {
		// make submissions.
		sc.makeSubmission(headerAccessor, new SubmissionModel("popeye's catering"));
		sc.makeSubmission(headerAccessor, new SubmissionModel("subway's catering"));
		sc.makeSubmission(headerAccessor, new SubmissionModel("mcdonald's"));
		sc.makeSubmission(headerAccessor, new SubmissionModel("burger king"));

		tc.setUpTournament(headerAccessor);

		TournamentModel tournament = ts.getTournamentModel(roomModel);

		while (!tournament.isDone()) {
			tc.vote(headerAccessor, "popeye's catering");
			tc.vote(headerAccessor, tournament.getMatchUp().getSecond().getName());
		}

		// Popeye's should always win, since we always vote for it before voting
		// for an alternative.
		assertTrue(tournament.isDone());
		assertEquals("popeye's catering", tournament.getResults().get(tournament.getResults().size() - 1).getName());
	}

	@Test
	public void testSingleSubmission() throws Exception {
		sc.makeSubmission(headerAccessor, new SubmissionModel("popeye's catering"));
		tc.setUpTournament(headerAccessor);

		TournamentModel tournament = ts.getTournamentModel(roomModel);
		assertTrue(tournament.isDone());
		assertEquals("popeye's catering", tournament.getResults().get(tournament.getResults().size() - 1).getName());
	}
}
