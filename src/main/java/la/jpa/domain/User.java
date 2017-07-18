package la.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
	private String id;

	@Column
	private String name;

	@ManyToOne
	private Org org;

}
