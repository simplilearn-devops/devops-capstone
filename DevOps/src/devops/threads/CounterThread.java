package devops.threads;

public class CounterThread implements Runnable {
	private Counter counter ;
	
	public CounterThread( Counter counter ) {
		this.counter = counter ;
	}

	@Override
	public void run() {
		for ( int i = 0 ; i < 10000 ; ++i ) {
			counter.increment();
			counter.decrement();
		}
	}

	public static void main( String[] args ) throws InterruptedException {
		Counter c = new Counter() ;
		Thread t1 = new Thread( new CounterThread( c ) ) ;
		Thread t2 = new Thread( new CounterThread( c ) ) ;
		t1.start();
		t2.start() ;
		t1.join() ;
		t2.join() ;
		System.out.println( "Counter " + c.getCount() ) ;
	}
}
