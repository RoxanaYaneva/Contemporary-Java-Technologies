package club.football.footballers;

import java.time.LocalDate;

import club.football.employees.Employee;

public class Footballer extends Employee {

	private Position position;
	private int matchesPerMonth;
	private static final double FB_SALARY = 10_000;
	private static final double BONUS_SUM_PER_MATCH = 250;
	
	
	public Footballer(String name, int age, Position position, LocalDate finalDateOfContract, int matchesPerMonth) {
		super(name, age, finalDateOfContract);
		this.position = position;
		this.matchesPerMonth = matchesPerMonth;
	}

	public void setNumberOfMatchesPerMonth(int matchesPerMonth) {
		this.matchesPerMonth = matchesPerMonth;
	}
	
	public int getNumberOfMathcesPerMonth() {
		return this.matchesPerMonth;
	}
	
	@Override 
	public double calculateSalary() {
		return FB_SALARY + BONUS_SUM_PER_MATCH * matchesPerMonth;
	}
	
	@Override
	public String toString() {
		return  super.toString() + ", position :" + position + ", number of matches per month : " + matchesPerMonth;
	}
}