package creditLine.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CreditLine {

	@Id
	@GeneratedValue
	private int reference;
	
	@ManyToOne
	@JoinColumn
	private Commission commission;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String currency;
	
	
	@Column(nullable = false)
	private int creditState;
	
	
	public CreditLine() {		
	}
	
	public CreditLine(double amount, String currency) {
		this.amount = amount;
		this.currency = currency;
		this.creditState = 1;
	}
	
	public CreditLine(double amount, String currency, double limit, Commission commission) {
		this.amount = amount;
		this.currency = currency;
		this.creditState = 1;
		this.commission = commission;
	}

		
	@Override
	public String toString() {
		return "CreditLine [reference=" + reference + ", commission=" + commission + ", amount=" + amount
				+ ", currency=" + currency + ", creditState=" + creditState +  "]";
	}

	@Override
	public int hashCode() {
		return reference;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public int getCreditState() {
		return creditState;
	}

	public void setCreditState(int creditState) {
		this.creditState = creditState;
	}


	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}
	
}
