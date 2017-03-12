package exercise.seventh;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Counter {

	public static final int PRICE_OF_CARD = 50;
	private static final int DEPOSIT = 1;

	private static int numberOfCardsLeft = 500;

	private double balance;
	private Map<String, LocalDate> data;

	public Counter() {
		this.balance = 0;
		this.data = new HashMap<>();
	}

	public synchronized void increaseBalance() {
		this.balance += (PRICE_OF_CARD + DEPOSIT);
	}

	public synchronized void decreaseBalance() {
		this.balance -= DEPOSIT;
	}

	private static synchronized void incrementCardNumber() {
		numberOfCardsLeft++;
	}

	private static synchronized void decrementCardNumber() {
		numberOfCardsLeft--;
	}

	public static int getNumberOfCardsLeft() {
		return numberOfCardsLeft;
	}

	public void takeMoney(double money) {
		this.balance -= money;
	}

	public synchronized double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Map<String, LocalDate> getData() {
		return data;
	}

	public synchronized void buyCard(Snowboarder snowboarder) {
		while (numberOfCardsLeft == 0) {
			System.err.println("You should wait!");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(snowboarder.getName() + ": I am buying a card.");
		decrementCardNumber();
		increaseBalance();
		if (this.getBalance() >= Collector.MIN_BALANCE) {
			this.notifyAll();
			System.err.println("There are enough money for the collectors.");
		}
	}

	public synchronized void returnCard(Snowboarder snowboarder) {
		if (numberOfCardsLeft == 0) {
			this.notifyAll();
		}
		System.out.println(snowboarder.getName() + ": I am returning the card.");
		incrementCardNumber();
		decreaseBalance();
	}
}