package sunofkyuss.zwitter.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import sunofkyuss.zwitter.model.Zwit;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;
import sunofkyuss.zwitter.model.Friendship;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "owner")
	private Set<Zwit> zwits = new HashSet<Zwit>();

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToMany
	private Set<Person> friends = new HashSet<Person>();

	public Person(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Person() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@PrePersist
	public void prePersist() {
		System.out.println("persisting");
		createDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("updating");
	}

	public Set<Zwit> getZwits() {
		return this.zwits;
	}

	public void setZwits(final Set<Zwit> zwits) {
		this.zwits = zwits;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Person> getFriends() {
		return this.friends;
	}

	public void setFriends(final Set<Person> friends) {
		this.friends = friends;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (password != null && !password.trim().isEmpty())
			result += "password: " + password;
		if (username != null && !username.trim().isEmpty())
			result += ", username: " + username;
		return result;
	}

}