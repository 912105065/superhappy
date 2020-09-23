package star.superhappy.dao;

import star.superhappy.pojo.XDDistribution;


public interface XDDistributionDao extends BaseDao<XDDistribution> {
	
	
	
	
	//自定义，读取当前XDDistribution的最大期号id
		public int getMaxXXDId();

}
