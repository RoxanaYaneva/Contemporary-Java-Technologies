package app.functionality;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.exceptions.AlreadyExisitingTeamException;
import app.exceptions.InvalidInformationException;
import app.exceptions.NonExistantTeamException;
import app.teams.Team;

public class TeamSystem {

	private Map<String, Team> teamList;

	public TeamSystem() {
		this.teamList = new HashMap<>();
	}

	public Map<String, Team> getTeamList() {
		return new HashMap<String, Team>(this.teamList);
	}

	public void addTeamInSystem(Team otherTeam) throws AlreadyExisitingTeamException {
		if (!this.teamList.containsKey(otherTeam.getNameOfTeam())) {
			this.teamList.put(otherTeam.getNameOfTeam(), otherTeam);
		} else {
			throw new AlreadyExisitingTeamException("Team already exists!");
		}
	}

	public void voteForTeam(String name, int points) throws InvalidInformationException, NonExistantTeamException {
		if (points < 0) {
			throw new InvalidInformationException("Invalid number of points");
		}
		if (this.teamList.containsKey(name)) {
			this.teamList.get(name).addPoints(points);
			return;
		}
		throw new NonExistantTeamException("This team does not exist!");
	}

	public List<Team> getTeamsByNumberOfPoint() {
		List<Team> rank = new ArrayList<>(this.teamList.values());
		Collections.sort(rank);
		return rank;
	}

	public void announceWinners(Path path) throws IOException {
		path = path.toAbsolutePath();
		List<Team> sortedTeams = getTeamsByNumberOfPoint();

		File file = path.toFile();
		if (file == null) {
			throw new IllegalArgumentException("File with path " + path + " does not exist.");
		}

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
			for (int i = 0; i < sortedTeams.size(); i++) {
				bufferedWriter.write(i + "." + sortedTeams.get(i).toString() + System.lineSeparator());
			}

			File backupDirectory = new File(path.getParent().toString(), "backup");

			Path backup = Files.createDirectories(backupDirectory.toPath());
			File standingTimestamp = new File(backup.toAbsolutePath().toString(),
					"standing_" + System.currentTimeMillis() + ".txt");
			Files.copy(file.toPath(), standingTimestamp.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
