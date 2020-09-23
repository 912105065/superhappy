package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.BaseDao;
import star.superhappy.dao.PoolsBDao;
import star.superhappy.pojo.PoolsB;

public class PoolsBDaoImpl extends BaseDaoImpl<PoolsB> implements PoolsBDao{

	
	
	//自定义，读取当前poolsB的最大期号id
	@Override
	public int getMaxPoolsBId(){
		
		String hql="select Max(id) from PoolsB";
		Query query=getSession().createQuery(hql);
		
		return (int) query.uniqueResult();
	}
}
