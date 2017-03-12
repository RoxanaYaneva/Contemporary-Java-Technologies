package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.exceptions.AlreadyExisitingTeamException;
import app.exceptions.InvalidInformationException;
import app.exceptions.NonExistantTeamException;
import app.functionality.TeamSystem;
import app.teams.Team;

public class SystemTest {

	private TeamSystem system;

	@Before
	public void initialize() {
		this.system = new TeamSystem();
	}

	@Test(expected = AlreadyExisitingTeamException.class)
	public void testAddTeamInSystemAlreadyExistingTeam()
			throws InvalidInformationException, AlreadyExisitingTeamException {
		Team team1 = new Team("GoGo", "SofiaGo");
		Team team2 = new Team("GoGo", "SofiaRun");
		system.addTeamInSystem(team1);
		system.addTeamInSystem(team2);
	}

	@Test(expected = InvalidInformationException.class)
	public void testAddTeamInSystemInavalidNameOfTeam()
			throws InvalidInformationException, AlreadyExisitingTeamException {
		Team team = new Team(null, "SofiaGo");
		system.addTeamInSystem(team);
	}

	@Test(expected = InvalidInformationException.class)
	public void testAddTeamInSystemInvalidNameOfProject()
			throws InvalidInformationException, AlreadyExisitingTeamException {
		Team team = new Team("GoGo", null);
		system.addTeamInSystem(team);
	}

	@Test()
	public void testAddTeamInSystem() throws AlreadyExisitingTeamException, InvalidInformationException {
		Team team1 = new Team("GoRunners", "SofiaGo");
		Team team2 = new Team("GoGo", "SofiaRun");
		system.addTeamInSystem(team1);
		system.addTeamInSystem(team2);
		assertEquals(system.getTeamList().size(), 2);
	}

	@Test
	public void testVoteForTeam()
			throws AlreadyExisitingTeamException, InvalidInformationException, NonExistantTeamException {
		Team team1 = new Team("GoRunners", "SofiaGo");
		Team team2 = new Team("GoGo", "SofiaRun");
		system.addTeamInSystem(team1);
		system.addTeamInSystem(team2);
		system.voteForTeam("GoRunners", 13);
		assertEquals(team1.getNumberOfPoints(), 13);
	}

	@Test(expected = NonExistantTeamException.class)
	public void testVoteForTeamNonExistant()
			throws InvalidInformationException, AlreadyExisitingTeamException, NonExistantTeamException {
		String name = "Go";
		Team team = new Team("GoGo", "SofiaGo");
		system.addTeamInSystem(team);
		system.voteForTeam(name, 23);
	}

	@Test(expected = InvalidInformationException.class)
	public void testVoteForTeamInvalidInfo()
			throws InvalidInformationException, AlreadyExisitingTeamException, NonExistantTeamException {
		Team team = new Team("GoRunners", "SofiaGo");
		system.addTeamInSystem(team);
		system.voteForTeam("GoRunners", -12);
	}
}
