package com.capstoneproject.educonnect.Error;

public final class EmailAlreadyExistException extends RuntimeException {
	 private static final long serialVersionUID = 5861310537366287163L;

	 public EmailAlreadyExistException() {
	        super();
	    }
	 
	    public EmailAlreadyExistException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public EmailAlreadyExistException(final String message) {
	        super(message);
	    }

	    public EmailAlreadyExistException(final Throwable cause) {
	        super(cause);
	    }
}
