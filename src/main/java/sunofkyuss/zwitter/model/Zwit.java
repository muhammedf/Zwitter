package sunofkyuss.zwitter.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import sunofkyuss.zwitter.model.Person;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import sunofkyuss.zwitter.Visibility;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Zwit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String message;

	@ManyToOne
	private Person owner;

	@Enumerated
	private Visibility visibility;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

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
		if (!(obj instanceof Zwit)) {
			return false;
		}
		Zwit other = (Zwit) obj;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Person getOwner() {
		return this.owner;
	}

	public void setOwner(final Person owner) {
		this.owner = owner;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	@PrePersist
	public void prePersist() {
		createDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (message != null && !message.trim().isEmpty())
			result += "message: " + message;
		return result;
	}

}