package exercise.seventh;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

	private static final int NUMBER_OF_SNOWBOARDERS = 700;

	public static void main(String[] args) throws InterruptedException {

//		task2 - without countdownlatch
//		Counter counter = new Counter();
//		for (int i = 1; i <= NUMBER_OF_SNOWBOARDERS; i++) {
//			 new Thread(new Snowboarder("#" + i, counter)).start();
//		}
//		System.out.println(Counter.getNumberOfCardsLeft());
//		System.out.println(Counter.getBalance());

		CountDownLatch latch = new CountDownLatch(NUMBER_OF_SNOWBOARDERS);
		Counter counter = new Counter();
		ExecutorService snowboarders = Executors.newFixedThreadPool(15);
		for (int i = 1; i <= NUMBER_OF_SNOWBOARDERS; i++) {
			snowboarders.submit(new Snowboarder("#" + i, counter, latch));
		}

		ExecutorService collectors = Executors.newFixedThreadPool(15);
		for (int i = 0; i < NUMBER_OF_SNOWBOARDERS; i++) {
			collectors.submit(new Collector(counter));
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snowboarders.shutdown();
		collectors.shutdown();
		System.out.println("Balance in the counter: " + counter.getBalance());
		System.out.println("Collectors took: " + Collector.getTotalMoney());

		System.exit(0);
	}
}
