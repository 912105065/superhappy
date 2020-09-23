package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.HouQuCombinationStatisticDao;
import star.superhappy.pojo.HouQuCombinationStatistic;

public class HouQuCombinationStatisticDaoImpl extends BaseDaoImpl<HouQuCombinationStatistic> implements HouQuCombinationStatisticDao{

	
	//获取后区组合表最大id值
	@Override
	public long getMaxHQCombinationStatisticDaoId() {
		String hql="SELECT SUM(hqcs.hqComCount) FROM  HouQuCombinationStatistic hqcs";
		Query query=getSession().createQuery(hql);
		return   (long) query.uniqueResult();
	}

	
	//自定义，根据组合号，年份查询
	@Override
	public HouQuCombinationStatistic getByCombinationAndYear(
			String combination, int year) {
		String hql="FROM HouQuCombinationStatistic WHERE hqCombination=:combination AND year=:year";
		Query query=getSession().createQuery(hql);
		query.setString("combination", combination)
		     .setInteger("year", year);
		return (HouQuCombinationStatistic) query.list().get(0);
	}

}
