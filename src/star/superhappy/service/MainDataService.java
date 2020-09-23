package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.MainData;
import star.superhappy.vo.NumberCount;

public interface MainDataService {

	// 保存
	public void save(MainData mainData);

	// 更新
	public void update(MainData mainData);

	// 删除
	public void delete(int id);

	// 查询，返回单个实体
	public MainData get(int id);

	// 查询，返回List
	public List<MainData> query();

	// 自定义，读取当前mainData的最大期号id
	public int getMaxMainDataId();

	// 自定义，读取当前mainData的最小期号id
	public int getMinMainDataId();

	// 根据年判断该年的最大期号，2017
	public int getMaxMainDataIdByYear(int year);

	// 自定义，根据日期区间，后区号码1，后区号码2，查询出现次数
	public Long getCountByDateAndhqNumber1AndhqNumber2(int a, int b,
			String beginDate, String endDate);

	// 自定义，根据日期区间，后区号码1，后区号码2，查询List
	public List<MainData> queryByDateAndhqNumber1AndhqNumber2(int a, int b,
			String beginDate, String endDate);

	// 自定义，获取mainData的总期数
	public long getCountByMainData();
	
	
	
	//自定义，查询MainData,EasyUI分页显示maindata
    public List<MainData> queryByPage(int pageNo,int pageSize);
	
	
	//以下是service层独立方法

	// 自定义，增加数据并同步更新分析数据
	public void saveAndUpdate(MainData mainData);

	// 自定义，检测一组号码，在历期开奖中的中奖情况
	public String checkByOneData(MainData tempData);
	
	//自定义，按年，号码查询出现次数，更新每年数据
	public void updateMainDataStatistic();
	
	
	//自定义，按期号段，统计后区码出现次数
	public List<NumberCount> statisticMDHQCountByMDId(int startId,int endId);
	
	//自定义，按期号段，统计前区码出现次数
	public List<NumberCount> statisticMDQQCountByMDId(int startId,int endId);
}
