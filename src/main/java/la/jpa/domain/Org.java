package la.jpa.domain;

import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Org {

	@Id
	private String id;

	@Column
	private String name;

	@OneToMany
	@OrderBy("name ASC")
	private SortedSet<User> user;
}
