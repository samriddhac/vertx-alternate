package demo.bank.core.account.api.exception;


/**
 * Name :- GenericException
 * Purpose :- This class wraps any runtime exception and 
 * provide the exception details to consumer.
 */
public class GenericException {

	private Integer status;
	private String timestamp;
	private String error;
	private String message;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
