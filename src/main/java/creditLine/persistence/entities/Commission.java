package creditLine.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commission {
	
	@Id
	@GeneratedValue
	private int idcommission;
	
	@Column(nullable = false)
	private String concept;
	
	@Column(nullable = false)
	private double minAmount;

	@Column(nullable = false)
	private double maxAmount;
	
	@Column(nullable = false)
	private double charge;

	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String currency;
	
	@Column
	private String creationDate;

	@Column
	private String modificationDate;
	
	@Column
	private String commissioncol;
	
	public Commission() {
	}
	
	public Commission(String concept, double minAmount, double maxAmount, double charge, double amount, String currency) {
		this.concept = concept;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.charge = charge;
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Commission [idcommission=" + idcommission + ", concept=" + concept + ", minAmount=" + minAmount
				+ ", maxAmount=" + maxAmount + ", charge=" + charge + ", amount=" + amount + ", currency=" + currency
				+ ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", commissioncol="
				+ commissioncol + "]";
	}
	
	@Override
	public int hashCode() {
		return idcommission;
	}

	public int getIdcommission() {
		return idcommission;
	}

	public void setIdcommission(int idcommission) {
		this.idcommission = idcommission;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getCommissioncol() {
		return commissioncol;
	}

	public void setCommissioncol(String commissioncol) {
		this.commissioncol = commissioncol;
	}
}
