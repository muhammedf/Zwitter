package sunofkyuss.zwitter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import sunofkyuss.zwitter.model.Person;

/**
 * DAO for Person
 */
@Stateless
public class PersonDao {
	@PersistenceContext(unitName = "Zwitter-persistence-unit")
	private EntityManager em;

	public void create(Person entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Person entity = em.find(Person.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Person findById(Long id) {
		return em.find(Person.class, id);
	}

	public Person update(Person entity) {
		return em.merge(entity);
	}

	public List<Person> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Person> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.zwits LEFT JOIN FETCH p.friends ORDER BY p.id",
				Person.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}

	public Person getPersonByUserName(String username) {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Person> cq = cb.createQuery(Person.class);
			Root<Person> e = cq.from(Person.class);

			cq.where(cb.equal(e.get("username"), username));

			TypedQuery<Person> qu = em.createQuery(cq);
			return qu.getSingleResult();
		} catch (Exception e) {
			
		}
		return null;
	}
}
