package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.TypeAStatisticsDao;
import star.superhappy.pojo.TypeAStatistics;

public class TypeAStatisticsDaoImpl extends BaseDaoImpl<TypeAStatistics> implements TypeAStatisticsDao{

	
	
	//自定义，获取TypeAStatistics的最大id
	@Override
	public int getTypeAStatisticsMaxId() {
		String hql="select MAX(id) from TypeAStatistics";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}

}
