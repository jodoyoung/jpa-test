package la.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Comparable<User>{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String id;

	@Column
	private String name;

	@Override
	public int compareTo(User o) {
		return 0;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
//	@JsonManagedReference()
	@JsonIgnoreProperties("user")
	private Org org;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}


}

