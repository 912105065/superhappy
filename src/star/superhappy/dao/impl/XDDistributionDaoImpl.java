package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.XDDistributionDao;
import star.superhappy.pojo.XDDistribution;

public class XDDistributionDaoImpl extends BaseDaoImpl<XDDistribution> implements XDDistributionDao{

	
	
	//自定义，读取当前XDDistribution的最大期号id
	@Override
	public int getMaxXXDId() {
		String hql="select MAX(id) from XDDistribution";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}

}
