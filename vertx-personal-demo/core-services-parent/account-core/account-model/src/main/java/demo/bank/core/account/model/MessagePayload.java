package demo.bank.core.account.model;

import java.io.Serializable;

public class MessagePayload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809973684033568620L;
	private String payload;

	public MessagePayload() {
		super();
	}
	public MessagePayload(String payload) {
		super();
		this.payload = payload;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
}
