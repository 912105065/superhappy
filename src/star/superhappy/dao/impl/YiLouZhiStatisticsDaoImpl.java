package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.YiLouZhiStatisticsDao;
import star.superhappy.pojo.YiLouZhiStatistics;

public class YiLouZhiStatisticsDaoImpl extends BaseDaoImpl<YiLouZhiStatistics> implements YiLouZhiStatisticsDao{

	
	
	//自定义，获取最大Id号
	@Override
	public int getYiLouZhiStatisticsMaxId() {
		String hql="select Max(id) from YiLouZhiStatistics";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}

}
