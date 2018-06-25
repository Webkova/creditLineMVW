package creditLine.persistence.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private int idclient;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENT_ACCOUNT", joinColumns = { @JoinColumn(name = "idclient") }, inverseJoinColumns = {
			@JoinColumn(name = "idaccount") })
	private List<Account> account;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private String address;

	@Column
	private String nationality;

	@Column
	private String creationDate;

	public Client() {

	}

	public Client(String name, String surname, String address, String nationality) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.nationality = nationality;
		this.creationDate = Instant.now().toString();
	}

	public Client(String name, String surname, String address, String nationality, List<Account> account) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.nationality = nationality;
		this.creationDate = Instant.now().toString();
		this.account = account;
	}

	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", account=" + account + ", name=" + name + ", surname=" + surname
				+ ", address=" + address + ", nationality=" + nationality + ", creationDate=" + creationDate + "]";
	}

	@Override
	public int hashCode() {
		return idclient;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Client other = (Client) obj;
			return idclient == other.idclient;
		}
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}
}
