package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.AcvalueStatisticsDao;
import star.superhappy.pojo.AcvalueStatistics;

public class AcvalueStatisticsDaoImpl extends BaseDaoImpl<AcvalueStatistics> implements AcvalueStatisticsDao{

	
	//自定义，获取AcvalueStatistics的最大id
	@Override
	public int getAcvalueStatisticsMaxId() {
		String hql="SELECT MAX(id) FROM AcvalueStatistics";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}

}
