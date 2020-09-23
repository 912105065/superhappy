package star.superhappy.dao;

import star.superhappy.pojo.LHstatistics;

public interface LHstatisticsDao extends BaseDao<LHstatistics>{

	
	
	//自定义，获取LHstatistics最大id
	public int getLHstatisticsMaxId();
}
