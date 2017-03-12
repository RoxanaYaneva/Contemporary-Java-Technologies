package app.teams;

import app.exceptions.InvalidInformationException;

public class Team implements Comparable<Team> {

	private String nameOfTeam;
	private String nameOfProject;
	private int numberOfPoints;

	public Team(String nameOfTeam, String nameOfProject) throws InvalidInformationException {
		if (nameOfTeam == null || nameOfProject == null) {
			throw new InvalidInformationException("Invalid information!");
		}
		this.nameOfTeam = nameOfTeam;
		this.nameOfProject = nameOfProject;
		this.numberOfPoints = 0;
	}

	public String getNameOfTeam() {
		return nameOfTeam;
	}

	public void setNameOfTeam(String nameOfTeam) {
		this.nameOfTeam = nameOfTeam;
	}

	public String getNameOfProject() {
		return nameOfProject;
	}

	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public void addPoints(int points) {
		this.numberOfPoints += points;
	}

	@Override
	public String toString() {
		return nameOfTeam + "-" + nameOfProject + "-" + numberOfPoints;
	}

	@Override
	public int compareTo(Team team) {
		if (this.numberOfPoints < team.numberOfPoints) {
			return -1;
		} else if (this.numberOfPoints > team.numberOfPoints) {
			return 1;
		} else
			return this.nameOfTeam.compareTo(team.nameOfTeam);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameOfProject == null) ? 0 : nameOfProject.hashCode());
		result = prime * result + ((nameOfTeam == null) ? 0 : nameOfTeam.hashCode());
		result = prime * result + numberOfPoints;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (nameOfTeam == null) {
			if (other.nameOfTeam != null)
				return false;
		} else if (!this.nameOfTeam.equals(other.nameOfTeam)) {
			return false;
		}
		return true;
	}
}
