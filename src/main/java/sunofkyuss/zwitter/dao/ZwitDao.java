package sunofkyuss.zwitter.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import sunofkyuss.zwitter.Visibility;
import sunofkyuss.zwitter.model.Person;
import sunofkyuss.zwitter.model.Zwit;

/**
 * DAO for Zwit
 */
@Stateless
public class ZwitDao {
	@PersistenceContext(unitName = "Zwitter-persistence-unit")
	private EntityManager em;

	public void create(Zwit entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Zwit entity = em.find(Zwit.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Zwit findById(Long id) {
		return em.find(Zwit.class, id);
	}

	public Zwit update(Zwit entity) {
		return em.merge(entity);
	}

	public List<Zwit> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Zwit> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT z FROM Zwit z LEFT JOIN FETCH z.owner ORDER BY z.id",
						Zwit.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
	
	public List<Zwit> listEveryones(Date date, int count) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Zwit> cq = cb.createQuery(Zwit.class);
		Root<Zwit> e = cq.from(Zwit.class);
		
		cq.orderBy(cb.desc(e.get("createDate")));
		cq.where(cb.lessThan(e.get("createDate"), date));
		
		TypedQuery<Zwit> qu = em.createQuery(cq);
		qu.setMaxResults(count);
		return qu.getResultList();
	}

	public List<Zwit> listFriends(Person user, Date date, int count) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Zwit> cq = cb.createQuery(Zwit.class);
		Root<Zwit> e = cq.from(Zwit.class);
		
		cq.orderBy(cb.desc(e.get("createDate")));
		cq.where(cb.and(
					cb.lessThan(e.get("createDate"), date),
					cb.or(
						cb.and(cb.notEqual(e.get("visibility"), Visibility.ME), cb.isMember(user, e.get("owner").get("friends"))),
						cb.equal(e.get("owner").get("id"), user.getId())
						)
					)
				);
		
		TypedQuery<Zwit> qu = em.createQuery(cq);
		qu.setMaxResults(count);
		return qu.getResultList();
	}

	public List<Zwit> listMines(long userId, Date date, int count) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Zwit> cq = cb.createQuery(Zwit.class);
		Root<Zwit> e = cq.from(Zwit.class);
		
		cq.orderBy(cb.desc(e.get("createDate")));
		cq.where(cb.lessThan(e.get("createDate"), date), cb.equal(e.get("owner").get("id"), userId));
		
		TypedQuery<Zwit> qu = em.createQuery(cq);
		qu.setMaxResults(count);
		return qu.getResultList();
	}
}
