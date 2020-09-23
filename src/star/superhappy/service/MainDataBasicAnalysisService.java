package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.MainDataBasicAnalysis;

public interface MainDataBasicAnalysisService {

	
	
	//保存
	public void save(MainDataBasicAnalysis mainDataBasicAnalysis);
	
    //通用更新
	public void update(MainDataBasicAnalysis mainDataBasicAnalysis);
	
    //删除
	public void delete(int id);
	
    //查询，返回单个实体
	public MainDataBasicAnalysis get(int id);
	
    //查询，返回List
	public List<MainDataBasicAnalysis> query();
	
	// 自定义，初始化，批量更新
	public void batchUpdate();
}
