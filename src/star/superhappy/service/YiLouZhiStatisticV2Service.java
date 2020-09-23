package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.YiLouZhiStatisticV2;

public interface YiLouZhiStatisticV2Service {

	//保存
	public void save(YiLouZhiStatisticV2 ylzsc);

	//更新
	public void update(YiLouZhiStatisticV2 ylzsc);

	//根据id删除
	public void delete(int id);

	//查询，返回单个实体
	public YiLouZhiStatisticV2 get(int id);

	//查询，返回List
	public List<YiLouZhiStatisticV2> query();
	
	
	//自定义，更新YiLouZhiStatisticV2
	public void batchUpdate();
	
	
	//测试
	public void getMaxNonYiLouZhiByYear();
}
