package com.michaelssun.asynch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsynchExecutorTest {

	@Before
	public void before() {
	}

	@Test
	public void testTransientProperty() {
		ConcreteAsynchExecutor asynchExector = new ConcreteAsynchExecutor(2, 5, 10, "test-threadpool-");
		Assert.assertTrue(asynchExector.action());
	}

	class ConcreteAsynchExecutor extends AsynchExecutor {

		public ConcreteAsynchExecutor(int threadPoolSize, int maxPoolSize, int queueSize, String threadNamePrefix) {
			super(threadPoolSize, maxPoolSize, queueSize, threadNamePrefix);
		}

		public boolean action() {
			this.executeTask("Hi Java!", new MyRunnable());
			return true;
		}

		class MyRunnable extends ObjectRunnable {
			public void run() {
				System.out.println(Thread.currentThread().getName() + "=" + getObject());
			}

		}

	}
}
