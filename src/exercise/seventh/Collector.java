package exercise.seventh;

public class Collector implements Runnable {

	public static final int MIN_BALANCE = 1_500;

	private Counter counter;

	private static double totalMoney;

	public Collector(Counter counter) {
		this.counter = counter;
	}

	public synchronized void addMoneyToTresor() {
		totalMoney += MIN_BALANCE;
		this.counter.takeMoney(MIN_BALANCE);
	}

	@Override
	public void run() {
		synchronized (this.counter) {
			try {
				while (counter.getBalance() < MIN_BALANCE) {
					System.out.println("Waiting for money...");
					this.counter.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("I am adding money to the tresor.");
			addMoneyToTresor();
		}
	}

	public static double getTotalMoney() {
		return totalMoney;
	}

	public static void setTotalMoney(double totalMoney) {
		Collector.totalMoney = totalMoney;
	}
}
