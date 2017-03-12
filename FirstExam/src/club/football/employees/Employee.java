package club.football.employees;

import java.time.LocalDate;

public abstract class Employee implements Comparable<Employee> {

	private String name;
	private int age;
	private LocalDate finalDateOfContract;
	private double salary;

	public Employee(String name, int age, LocalDate finalDateOfContract) {
		this.name = name;
		this.age = age;
		this.finalDateOfContract = finalDateOfContract;
	}

	public LocalDate getFinalDateOfContract() {
		return finalDateOfContract;
	}

	public void setFinalDateOfContract(LocalDate finalDateOfContract) {
		this.finalDateOfContract = finalDateOfContract;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Name : " + name + ", age : " + age + ", final Date Of Contract : " + finalDateOfContract + ", salary : " + salary;
	}
	
	public abstract double calculateSalary();

	@Override
	public int compareTo(Employee employee) {
		if (employee.getFinalDateOfContract().isAfter(this.getFinalDateOfContract())) {
			return 1;
		} else if (employee.getFinalDateOfContract().isBefore(this.getFinalDateOfContract())) {
			return -1;
		}
		return employee.name.compareTo(this.name);
	}
}
