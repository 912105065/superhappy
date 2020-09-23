package star.superhappy.dao;

import star.superhappy.pojo.PoolsB;

public interface PoolsBDao extends BaseDao<PoolsB> {

	
	
	//自定义方法
	//自定义，读取当前poolsB的最大期号id
	public int getMaxPoolsBId();
	
	
}
