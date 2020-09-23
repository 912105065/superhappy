package star.superhappy.dao;

import star.superhappy.pojo.TypeAStatistics;

public interface TypeAStatisticsDao extends BaseDao<TypeAStatistics> {
	
	
	//自定义，获取TypeAStatistics的最大id
	public int getTypeAStatisticsMaxId();

}
