package com.clear2pay.labs.java8;

public class ExecutorsTest {
	/*public static void main(String[] args) throws InterruptedException {
		List l =  callService();
		
		System.out.println(l);
	}
	
	
	public static List callService() {
		ExecutorService executor = Executors.newWorkStealingPool();
		
		List<Callable<Task>> callables = Arrays.asList(() -> new com.clear2pay.rbpsh.java8.T1Task(),
		        () -> new T2Task());
		
		List ll = null;
		
		try {
			ll = executor.invokeAll(callables)
	    .stream()
	    .map(future -> {
	        try {
	        	Task task = future.get();
	        	if(task.isApplicable()) {
	        		return task.work(new ASIMessage());
	        	}
	        	return null;
	        }
	        catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    })
	    .filter(Objects::nonNull)
	   // .sort(new MyComp())
	    .collect(toList());
		} catch (InterruptedException e) {

        }
		executor.shutdown();
		return ll;
	}*/
}
