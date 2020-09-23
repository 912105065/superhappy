package star.superhappy.dao;

import star.superhappy.pojo.PoolsA;

public interface PoolsADao extends BaseDao<PoolsA> {

	
	
	// 自定义方法
	// 自定义，读取当前poolsA的最大期号id
	public int getMaxPoolsAId();
}
