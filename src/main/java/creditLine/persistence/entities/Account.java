package creditLine.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private int idaccount;
	
	@Column(nullable = false)
	private int accountType;
	
	@Column(nullable = false)
	private int accountStatus;
	
	@Column
	private String creationDate;
	
	@Column
	private int modificationDate;
	

	
	public Account(int owner, int accountType) {
		this.accountType = accountType;
		this.accountStatus = 0;
	}
		
	@Override
	public String toString() {
		return "Account [idaccount=" + idaccount + ", accountType=" + accountType + ", accountStatus=" + accountStatus
				+ ", creationDate=" + creationDate + ", modificationDate=" + modificationDate 
				+ "]";
	}

	@Override
	public int hashCode() {
		return idaccount;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Account other = (Account) obj;
            return idaccount == other.idaccount;
        }		
	}

	public int getIdaccount() {
		return idaccount;
	}

	public void setIdaccount(int idaccount) {
		this.idaccount = idaccount;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(int modificationDate) {
		this.modificationDate = modificationDate;
	}



	
}
