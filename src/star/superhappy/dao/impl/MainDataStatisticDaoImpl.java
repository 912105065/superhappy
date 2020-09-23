package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.MainDataStatisticDao;
import star.superhappy.pojo.MainDataStatistic;

public class MainDataStatisticDaoImpl extends BaseDaoImpl<MainDataStatistic> implements MainDataStatisticDao{

	
	//自定义，查询最大year
	@Override
	public int getMaxYear() {
		String hql="SELECT MAX(year) FROM MainDataStatistic";
		return (int) getSession().createQuery(hql).uniqueResult();
	}

	
	//自定义,根据year查询
	@Override
	public MainDataStatistic getByYear(int year) {
		String hql="FROM MainDataStatistic mds WHERE mds.year=:year";
		Query query=getSession().createQuery(hql);
		query.setInteger("year", year);
		return (MainDataStatistic) query.list().get(0);
	}

}
