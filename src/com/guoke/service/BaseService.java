package com.guoke.service;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.guoke.model.BaseEntity;

@Repository
@Transactional
public class BaseService <T extends BaseEntity>{
	@PersistenceContext
	private EntityManager entityManager;
	

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	public String getEntityName() {
		return getEntityClass().getSimpleName();
	}
	
	
	public void insert(T model) {
		// TODO Auto-generated method stub
		entityManager.persist(model);
	}


	public T update(T model) {
		// TODO Auto-generated method stub
	    return	entityManager.merge(model);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		T model = findById(id);
		if (model != null) {
			delete(model);
		}
		return true;
	}
	
	
	public boolean batchDelete(String keys) {
		return executeHQL("Delete FROM "+getEntityName()+" Where Id in ("+keys+")");
	}

	public T findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(getEntityClass(), id);
	}

	public boolean delete(T model) {
		// TODO Auto-generated method stub
		entityManager.remove(model);
		return true;
	}

	public boolean executeHQL(String hql,Object...values) {
		// TODO Auto-generated method stub
		Query q = getQueryNoTyped(hql,values);
		return q.executeUpdate()>0;
	}
	
	
	public void ExecTransaction(Consumer <BaseService<T>> func) {
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		try {
			func.accept(this);
			entityTransaction.commit();
		}catch(Exception e){
			entityTransaction.rollback();
			throw e;
		}
	}
	
	
	public TypedQuery<T> getQuery(String hql,Object... values) {
		TypedQuery<T> query = entityManager.createQuery(hql,getEntityClass());
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	public TypedQuery<T> getQuery(Consumer <QueryBuilder> func) {
		QueryBuilder queryBuilder=new QueryBuilder();
		func.accept(queryBuilder);
		StringBuilder hql =new StringBuilder();
		hql.append(String.format("From %s", getEntityName()));
		
		if(!StringUtils.isBlank(queryBuilder.getWhere())) {
			hql.append(String.format(" where %s", queryBuilder.getWhere()));
		}
		if(!StringUtils.isBlank(queryBuilder.getOrder())) {
			hql.append(String.format(" order by %s", queryBuilder.getOrder()));
		}
		
		TypedQuery<T> query = entityManager.createQuery(hql.toString(),getEntityClass());
		return query;
	}
	
	public Query getQueryNoTyped(String hql,Object... values) {
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	public T create(T model) {
		model.setCreateTime(new Timestamp(new Date().getTime()));
		model.setUpdateTime(new Timestamp(new Date().getTime()));
		model.setId(0);
		return this.update(model);
	}
	
	public T create(T model,Map<String, String> map) {
		return create(model);
	}
	
	public T edit(T model) {
		model.setUpdateTime(new Timestamp(new Date().getTime()));
	    return this.update(model);
	}
	
	public T edit(T model,Map<String, String> map) {
	    return edit(model);
	}

    
    public List<T> getList(String hql,Object... values){
    	TypedQuery<T> query = getQuery(hql,values);
    	List<T> list = query.getResultList();
    	return list;
	}
    
    public List<T> getList(Consumer <QueryBuilder> func){
    	TypedQuery<T> query = getQuery(func);
    	List<T> list = query.getResultList();
    	return list;
	}

	
	public List<T> getList(Map<String, String> map){
		return getList(q->{});
	}
	

	public Map<String, Object> getPageList(int page, int rows,Consumer <QueryBuilder> func) {
		// TODO Auto-generated method stub
		
		TypedQuery<T> query = getQuery(func);
		int total = query.getResultList().size();
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<T> list = query.getResultList();
		
		Map<String, Object> map=new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("data", list);
		map.put("count", total);
		return map; 
	}
	
	public Map<String, Object> getPageList(int page, int rows,Map<String, String> request) {	
		
		return getPageList(page,rows,q->{q.Where(this.getEntityClass(), request);});
	}

	
}
