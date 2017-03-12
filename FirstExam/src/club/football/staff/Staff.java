package club.football.staff;

import java.time.LocalDate;

import club.football.employees.Employee;

public class Staff extends Employee {

	private String position;
	private int yearsOfExperience;
	private static final int S_SALARY = 1_000;
	private static final double BONUS_PERCENT_BY_YEAR = 0.05;

	public Staff(String name, int age, LocalDate finalDateOfContract, String position, int yearsOfExperience) {
		super(name, age, finalDateOfContract);
		this.position = position;
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	@Override
	public String toString() {
		return super.toString() + ", position : " + position + ", yearsOfExperience : " + yearsOfExperience;
	}

	@Override
	public double calculateSalary() {
		return S_SALARY + yearsOfExperience * (S_SALARY * BONUS_PERCENT_BY_YEAR);
	}
}