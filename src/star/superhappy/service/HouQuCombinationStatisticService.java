/**
 * 
 */
package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.HouQuCombinationStatistic;

/**
 * @author Gang
 * 
 */
public interface HouQuCombinationStatisticService {

	// 通用保存
	public void save(HouQuCombinationStatistic hqCombinationStatistic);

	// 通用更新
	public void update(HouQuCombinationStatistic hqCombinationStatistic);

	// 通用删除
	public void delete(int id);

	// 通用查询，返回单个实体
	public HouQuCombinationStatistic get(int id);

	// 通用查询，返回List
	public List<HouQuCombinationStatistic> query();

	
	
	
	//以下service层独立方法
	// 初始化，批量插入数据
	public void initHqCombinationStatistic();

	
	//自定义，自动更新后区号码组合统计表
	public String autoUpdateHQCombinateStatistic();
	
	
	//自定义，根据组合号，年份查询
	public HouQuCombinationStatistic getByCombinationAndYear(String combination,int year);
}
