package star.superhappy.dao;

import star.superhappy.pojo.HouQuCombinationStatistic;

public interface HouQuCombinationStatisticDao extends BaseDao<HouQuCombinationStatistic> {

	//自定义，获取后区组合表最大id值
	public long getMaxHQCombinationStatisticDaoId();
	
	
	//自定义，根据组合号，年份查询
	public HouQuCombinationStatistic getByCombinationAndYear(String combination,int year);

}
