package com.michaelssun.asynch;

/**
 * runnable object to implement the action inside run()
 *
 */
public abstract class ObjectRunnable implements Runnable {
	private Object object;

	public ObjectRunnable() {
	}

	protected Object getObject() {
		return object;
	}

	protected void setObject(Object object) {
		this.object = object;
	}

}