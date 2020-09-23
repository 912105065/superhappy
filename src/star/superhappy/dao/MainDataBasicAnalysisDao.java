package star.superhappy.dao;

import star.superhappy.pojo.MainDataBasicAnalysis;

public interface MainDataBasicAnalysisDao extends BaseDao<MainDataBasicAnalysis>{

	
	
	//自定义，获取mainDataBasicAnalysisDao最大id
	public int getMainDataBasicAnalysisMaxId();
}
