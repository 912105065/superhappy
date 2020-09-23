package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.PoolsADao;
import star.superhappy.pojo.PoolsA;


public class PoolsADaoImpl extends BaseDaoImpl<PoolsA> implements PoolsADao{

	
	
	// 自定义，读取当前poolsA的最大期号id
	@Override
	public int getMaxPoolsAId() {
		
		String hql="select Max(id) from PoolsA";
		Query query=getSession().createQuery(hql);
		
		return (int) query.uniqueResult();
	}

}
