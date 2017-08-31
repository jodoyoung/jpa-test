package la.jpa.domain;

import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Org {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String id;

	@Column
	private String name;

	/*@JsonBackReference()
	@JsonIgnore*/
	@OneToMany(mappedBy = "org", cascade = CascadeType.PERSIST)
	@OrderBy("name ASC")
	@JsonIgnoreProperties("org")
	private SortedSet<User> user;

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

	public SortedSet<User> getUser() {
		return user;
	}

	public void setUser(SortedSet<User> user) {
		this.user = user;
	}
}
