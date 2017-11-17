package com.xs.webboottest.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xs.webboottest.domain.People;

@Repository
public class PeopleRepositoryImpl {
	@PersistenceContext
	private EntityManager em;

	public BigDecimal sumAmount(Map<String, Object> conds) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
		Root<People> root = query.from(People.class);
		Predicate predicate = criteriaBuilder.conjunction();
		List<Expression<Boolean>> expressions = predicate.getExpressions();
		
		Object namep = conds.get("namep");
		if (namep != null) {
			expressions.add(criteriaBuilder.equal(root.get("namep"), namep));
		}
		Object id = conds.get("id");
		if (id != null) {
			expressions.add(criteriaBuilder.equal(root.get("id"), id));
		}
		query.where(predicate).getRestriction();
		
		query.multiselect(criteriaBuilder.sum(root.get("acount")));
		TypedQuery<Tuple> q = em.createQuery(query);
		List<Tuple> result = q.getResultList();
		Tuple tuple = result.get(0);
		return (BigDecimal) tuple.get(0);
	}

}
