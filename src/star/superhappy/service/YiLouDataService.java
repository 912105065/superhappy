package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.YiLouData;

public interface YiLouDataService {

	// 通用保存
	public void save(YiLouData yiLouData);

	// 通用更新
	public void update(YiLouData yiLouData);

	// 通用删除
	public void delete(int id);

	// 通用查询，返回单个实体
	public YiLouData get(int id);

	// 通用查询，返回List
	public List<YiLouData> query();

	// 自定义，读取当前mainData的最大期号id
	public int getMaxYiLouDataId();

	
	

	// 以下service层独立方法
	// 自定义，初始化第一期数据的遗漏值，2007001
	public void yiLouDataInit();

	// 自定义，保存1期遗漏数据
	public void saveDataByOne();

	// 自定义，批量保存遗漏数据
	public void batchSaveData();
	
	//自定义，根据当前号码遗漏值（除当期击中外），是否存在历史击中遗漏中
	public String checkYiLouNowData();
}
