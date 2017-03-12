package exercise.seventh;

import java.util.concurrent.CountDownLatch;

public class Snowboarder implements Runnable {

	private String name;
	private Counter counter;
	private CountDownLatch countDownLatch;

	public Snowboarder(String name, Counter counter, CountDownLatch countDownLatch) {
		this.name = name;
		this.counter = counter;
		this.countDownLatch = countDownLatch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.buyCard(this);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		counter.returnCard(this);
		countDownLatch.countDown();
	}
}
