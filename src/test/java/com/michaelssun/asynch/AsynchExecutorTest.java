package com.michaelssun.asynch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsynchExecutorTest {
	private static Logger logger = LoggerFactory.getLogger(AsynchExecutorTest.class);

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
				logger.debug(Thread.currentThread().getName() + "=" + getObject());
			}

		}

	}
}
