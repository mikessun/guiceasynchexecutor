package com.michaelssun.asynch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * super class for those requiring asych thread execution; a runnable resource
 * should be implemented for thread executor to invoke run method; see
 * EmailService and emailRunnable
 *
 */
public abstract class AsynchExecutor {
	private static Logger logger = LoggerFactory.getLogger(AsynchExecutor.class);
	private static long KEEP_ALIVE_TIME = 1;
	private static ExecutorService executor;
	ThreadFactory namedThreadFactory;

	public AsynchExecutor(int threadPoolSize, int maxPoolSize, int queueSize, String threadNamePrefix) {
		logger.info("Processor configured with {} thread pool size, {} max pool size, {} queueSize, {} threadNamePrefix", threadPoolSize,
				maxPoolSize, queueSize, threadNamePrefix);

		namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "%d").build();
		executor = new ThreadPoolExecutor(threadPoolSize, maxPoolSize, KEEP_ALIVE_TIME, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(queueSize,
				true), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

	}

	void executeTask(Object object, ObjectRunnable runnableResource) {
		logger.debug("executeTask...");
		runnableResource.setObject(object);
		executor.execute(runnableResource);
	}
}
