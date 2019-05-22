package edu.handong.analysis.utils;

public class NotEnoughArgumentException extends Exception {
	public NotEnoughArgumentException() {
		super("edu.handong.analysis.utils.NotEnoughArgumentException occured!");
	}
	public NotEnoughArgumentException(String message) {
		super(message);
	}

}
	