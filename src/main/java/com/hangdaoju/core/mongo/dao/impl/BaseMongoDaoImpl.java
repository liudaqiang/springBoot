package com.hangdaoju.core.mongo.dao.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hangdaoju.core.Page;
import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.mongo.dao.BaseMongoDao;
import com.hangdaoju.util.ReflectionUtils;

public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {
	@Autowired
	private MongoTemplate mongoTemplate;

	// private static final int DEFAULT_SKIP = 0;
	// private static final int DEFAULT_LIMIT = 200;
	/**
	 * spring mongodb 集成操作类
	 */

	/**
	 * 查询所有
	 */
	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(this.getEntityClass());
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	@Override
	public List<T> findByCondition(T t) {
		Query query = buildBaseQuery(t);
		return mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 查询一条数据
	 */
	@Override
	public T findOne(T t) {
		return mongoTemplate.findOne(buildBaseQuery(t), this.getEntityClass());
	}

	/**
	 * 根据id查询数据
	 */
	@Override
	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	@Override
	public T findById(String id, String collectionName) {
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}

	/**
	 * 分页条件查询
	 */
	@Override
	public Page<T> findPage(Page<T> page, T t) {
		Query query = buildBaseQuery(t);
		long count = this.count(t);
		page.setTotal(count);
		int pageNumber = page.getPageNumber();
		int pageSize = page.getPageSize();
		query.with(new Sort(Sort.Direction.DESC, "update_time"));
		query.skip((pageNumber - 1) * pageSize).limit(pageSize);
		List<T> rows = this.find(query);
		page.setRows(rows);
		return page;
	}

	/**
	 * 修改数据(仅修改一条)
	 */
	@Override
	public void update(Query query, Update update) {
		mongoTemplate.findAndModify(query, update, this.getEntityClass()); // 最多更新一次文档
	}

	/**
	 * 修改所有符合条件的数据
	 */
	@Override
	public void updateMulti(Query query, Update update) {
		mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	@Override
	public void updateById(String id, T t) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = buildBaseUpdate(t);
		update(query, update);
	}

	/**
	 * 保存数据
	 */
	@Override
	public T save(T entity) {
		mongoTemplate.insert(entity);
		return entity;
	}

	/**
	 * 查询返回list长度
	 */
	@Override
	public long count(T t) {
		Query query = buildBaseQuery(t);
		return mongoTemplate.count(query, this.getEntityClass());
	}

	/**
	 * 根据id删除
	 */
	@Override
	public void delete(T entity) {
		mongoTemplate.remove(entity);
	}

	/**
	 * 查询后删除
	 */
	@Override
	public T findAndDelete(Query query) {
		return mongoTemplate.findAndRemove(query, this.getEntityClass());
	}

	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	private Class<T> getEntityClass() {
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 通过实体类过去简单Query仅支持全AND查询
	 * 
	 * @param t
	 * @return
	 */
	private Query buildBaseQuery(T t) {
		Query query = new Query();

		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(t);
				if (value != null) {
					QueryField queryField = field.getAnnotation(QueryField.class);
					if (queryField != null) {
						query.addCriteria(queryField.type().buildCriteria(queryField, field, value));
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return query;
	}

	private Update buildBaseUpdate(T t) {
		Update update = new Update();

		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(t);
				if (value != null) {
					update.set(field.getName(), value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return update;
	}

	@Override
	public List<T> sortByCondition(T t, Sort sort) {
		if (null == sort) {
			return findByCondition(t);
		}
		Query query = buildBaseQuery(t);
		query.with(sort);
		return mongoTemplate.find(query, this.getEntityClass());
	}
}
