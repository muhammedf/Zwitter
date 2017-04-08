package sunofkyuss.zwitter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunofkyuss.zwitter.model.Friendship;

/**
 * DAO for Friendship
 */
@Stateless
public class FriendshipDao {
	@PersistenceContext(unitName = "Zwitter-persistence-unit")
	private EntityManager em;

	public void create(Friendship entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Friendship entity = em.find(Friendship.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Friendship findById(Long id) {
		return em.find(Friendship.class, id);
	}

	public Friendship update(Friendship entity) {
		return em.merge(entity);
	}

	public List<Friendship> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Friendship> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT f FROM Friendship f LEFT JOIN FETCH f.owner LEFT JOIN FETCH f.friend ORDER BY f.id",
						Friendship.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
