package star.superhappy.dao;

import star.superhappy.pojo.MainDataStatistic;

public interface MainDataStatisticDao extends BaseDao<MainDataStatistic>{

	
	
	//自定义，查询最大year
	public int getMaxYear();
	
	//自定义,根据year查询
	public MainDataStatistic getByYear(int year);
}
