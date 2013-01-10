package at.univie.ct.ga;

public class GAException extends Exception {

	private static final long serialVersionUID = 1L;

	public GAException(String message) {
		super(message);
	}

	public GAException(Throwable cause) {
		super(cause);
	}

	public GAException(String message, Throwable cause) {
		super(message, cause);
	}

	public GAException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
