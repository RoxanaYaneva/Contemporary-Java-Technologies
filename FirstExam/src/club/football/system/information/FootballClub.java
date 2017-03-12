package club.football.system.information;

import club.football.employees.Employee;

import java.time.LocalDate;
import java.util.Arrays;

public class FootballClub {

	private Employee[] employees;
	private static final int CAPACITY_OF_CLUB = 60;

	public FootballClub() {
		this.employees = new Employee[CAPACITY_OF_CLUB];
	}

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	public Employee[] addNewEmployee(Employee employee) throws Exception {
		Employee[] newStaff = new Employee[this.employees.length + 1];
		newStaff = this.employees;
		if (this.employees.length <= CAPACITY_OF_CLUB) {
			newStaff[this.employees.length] = employee;
		} else {
			throw new Exception("The capacity of the football club is full.");
		}
		return newStaff;
	}

	public void employeesWithExpiredContract() {
		System.out.println("Employees with expired contracs :");
		for (int i = 0; i < employees.length; i++) {
			if (LocalDate.now().isAfter(employees[i].getFinalDateOfContract())) {
				System.out.println(employees[i]);
			}
		}
	}

	public void employeesByFinalDateOfContract() {
		Arrays.sort(employees);
		System.out.println("Employees by final date of contract :");
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
	}

	public static double seeSalaryOfEmployee(Employee employee) {
		double salary = employee.calculateSalary();
		return salary;
	}
}
