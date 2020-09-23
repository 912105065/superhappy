package star.superhappy.dao.impl;

import java.util.List;

import org.hibernate.Query;

import star.superhappy.dao.YiLouDataDao;
import star.superhappy.pojo.YiLouData;

public class YiLouDaoImpl extends BaseDaoImpl<YiLouData> implements YiLouDataDao{

	//读取当前mainData的最大期号id
	@Override
	public int getMaxYiLouDataId() {
		String sql="select MAX(mainId) from yiloudata";
		Query query=getSession().createSQLQuery(sql);
		return (int) query.uniqueResult();
	}

	
	//获取号码当年的最大遗漏值
	@Override
	public int getMaxNonYiLouZhiByYear(String number, String flag,int startId, int endId) {
		// TODO Auto-generated method stub
		//select MAX(qqNo1) from yiloudata where qqNo1Flag=2;
		String hql="select MAX( "+number+") from yiloudata where yilouid>=:startId and yilouid<=:endId and "+flag+"=2";
		Query query=getSession().createSQLQuery(hql);
		query.setInteger("startId", startId)
		     .setInteger("endId", endId);
		//return (int) query.uniqueResult();
		if(query.uniqueResult()==null){
			return -1;
		}else{
			return (int) query.uniqueResult();
		}
	}



	//获取号码的最大遗漏值
	@Override
	public int getMaxNonYiLouZhi(String number, String flag) {
		//select MAX(qqNo1) from yiloudata where qqNo1Flag=2;
				String hql="select MAX( "+number+") from yiloudata where "+flag+"=2";
				Query query=getSession().createSQLQuery(hql);
				return (int) query.uniqueResult();
	}

	//获取号码当期的遗漏值
	@Override
	public int getYiLouZhiById(String number,int id) {
		// TODO Auto-generated method stub
		//select qqNo1 from yiloudata where yiLouId=2019148;
		String sql="select "+number+" from yiloudata where yilouid =:id";
		Query query=getSession().createSQLQuery(sql);
		query.setInteger("id", id);
		return (int) query.uniqueResult();
	}


	//获取近n期号码击中的遗漏值
	@Override
	public List<Number> queryJZYiLouZhiByN(String number,String flag, int n) {
		// TODO Auto-generated method stub
		//select qqNo1 from yiloudata where qqNo1Flag=2 or qqNo1Flag=3 ORDER BY yiLouId desc limit 5;
		String sql="select "+number+" from yiloudata where "+flag+"=2 or "+flag+"=3 order by yiLouId desc";
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(0)
		     .setMaxResults(n);
		return query.list();
	}

}
