package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.MainDataBasicAnalysisDao;
import star.superhappy.pojo.MainDataBasicAnalysis;

public class MainDataBasicAnalysisDaoImpl extends BaseDaoImpl<MainDataBasicAnalysis> implements MainDataBasicAnalysisDao{

	
	
	//自定义，获取mainDataBasicAnalysisDao最大id
		public int getMainDataBasicAnalysisMaxId(){

			String hql="select Max(id) from MainDataBasicAnalysis";
			Query query=getSession().createQuery(hql);
			
			return (int) query.uniqueResult();
		}
}
