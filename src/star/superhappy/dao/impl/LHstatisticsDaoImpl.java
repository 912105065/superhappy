package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.LHstatisticsDao;
import star.superhappy.pojo.LHstatistics;

public class LHstatisticsDaoImpl extends BaseDaoImpl<LHstatistics> implements LHstatisticsDao{

	
	
	//自定义，获取LHstatistics最大id
	@Override
	public int getLHstatisticsMaxId() {
		String hql="select Max(id) from LHstatistics";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}

}
