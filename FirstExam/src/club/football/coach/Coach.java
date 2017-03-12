package club.football.coach;

import java.time.LocalDate;

import club.football.employees.Employee;

public class Coach extends Employee {

	private static final int C_SALARY = 8_000;
	private static final int BONUS_SALARY = 5_000;

	private Type typeOfCoach;
	private int numberOfWonTitles;

	public Coach(String name, int age, LocalDate finalDateOfContract, Type typeOfCoach, int numberOfWonTitles) {
		super(name, age, finalDateOfContract);
		this.typeOfCoach = typeOfCoach;
		this.numberOfWonTitles = numberOfWonTitles;
	}

	public int getNumberOfWonTitles() {
		return numberOfWonTitles;
	}

	public void setNumberOfWonTitles(int numberOfWonTitles) {
		this.numberOfWonTitles = numberOfWonTitles;
	}

	@Override
	public String toString() {
		return super.toString() + ", type of coach : " + typeOfCoach + ", number of won titles : " + numberOfWonTitles;
	}

	@Override
	public double calculateSalary() {
		if (this.typeOfCoach == Type.SENIOR && numberOfWonTitles >= 5)
			return C_SALARY + BONUS_SALARY;
		else
			return C_SALARY;
	}
}
