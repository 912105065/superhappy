package star.superhappy.dao;

import star.superhappy.pojo.AcvalueStatistics;

public interface AcvalueStatisticsDao extends BaseDao<AcvalueStatistics>{
	
	//自定义，获取AcvalueStatistics的最大id
	public int getAcvalueStatisticsMaxId();

}
