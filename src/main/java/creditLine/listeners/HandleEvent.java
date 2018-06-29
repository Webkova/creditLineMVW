package creditLine.listeners;

import org.springframework.context.ApplicationEvent;

import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;

public class HandleEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String eventType;
	private Client client;
	private Account account;
	
	private int id;

	public HandleEvent(Object source, String eventType, Client client) {
		super(source);
		this.eventType = eventType;
		this.client = client;
	}
	
	public HandleEvent(Object source, String eventType, Client client, int idclient) {
		super(source);
		this.eventType = eventType;
		this.client = client;
		this.client.setIdclient(idclient);
	}
	
	public HandleEvent(Object source, String eventType, int id) {
		super(source);
		this.eventType = eventType;
		this.id = id;
	}
	
	public HandleEvent(Object source, String eventType, Account account) {
		super(source);
		this.eventType = eventType;
		this.account = account;	
	}
	
	public HandleEvent(Object source, String eventType, Account account, int idaccount) {
		super(source);
		this.eventType = eventType;
		this.account = account;	
		this.account.setIdaccount(idaccount);
	}
	

	public String getEventType() {
		return eventType;
	}
	
	public Client getClient() {
		return client;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "HandleEvent [eventType=" + eventType + ", client=" + client + ", account=" + account + ", id=" + id
				+ "]";
	}

	
}
