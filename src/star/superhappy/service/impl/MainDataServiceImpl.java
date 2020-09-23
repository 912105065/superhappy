package star.superhappy.service.impl;

import java.util.ArrayList;
import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.MainData;
import star.superhappy.service.MainDataService;
import star.superhappy.utils.AnalysisUtils;
import star.superhappy.vo.NumberCount;

public class MainDataServiceImpl implements MainDataService {

	private MainDataDao mainDataDao;

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	// 保存
	@Override
	public void save(MainData mainData) {
		mainDataDao.save(mainData);
	}

	// 更新
	@Override
	public void update(MainData mainData) {
		mainDataDao.update(mainData);
	}

	// 根据id删除
	@Override
	public void delete(int id) {
		mainDataDao.delete(id);
	}

	// 根据id获取一个实体对象
	@Override
	public MainData get(int id) {
		return mainDataDao.get(id);
	}

	// 查询，返回list
	@Override
	public List<MainData> query() {
		return mainDataDao.query();
	}

	// 读取当前mainData的最大期号id
	@Override
	public int getMaxMainDataId() {
		// TODO Auto-generated method stub
		return mainDataDao.getMaxMainDataId();
	}

	// 读取当前mainData的最大期号id
	@Override
	public int getMinMainDataId() {
		// TODO Auto-generated method stub
		return mainDataDao.getMinMainDataId();
	}

	// 根据日期区间，后区号码1，后区号码2，查询出现次数
	@Override
	public Long getCountByDateAndhqNumber1AndhqNumber2(int a, int b,
			String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(a, b,
				beginDate, endDate);
	}

	// 根据日期区间，后区号码1，后区号码2，查询List
	@Override
	public List<MainData> queryByDateAndhqNumber1AndhqNumber2(int a, int b,
			String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return mainDataDao.queryByDateAndhqNumber1AndhqNumber2(a, b, beginDate,
				endDate);
	}

	// 根据年判断该年的最大期号，2017
	@Override
	public int getMaxMainDataIdByYear(int year) {
		// TODO Auto-generated method stub
		return mainDataDao.getMaxMainDataIdByYear(year);
	}

	// 自定义，获取mainData的总期数
	@Override
	public long getCountByMainData() {
		// TODO Auto-generated method stub
		return mainDataDao.getCountByMainData();
	}

	// 自定义，查询MainData,EasyUI分页显示maindata
	@Override
	public List<MainData> queryByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return mainDataDao.queryByPage(pageNo, pageSize);
	}

	// 以下是service层独立方法
	// 自定义，增加数据并同步更新分析数据
	@Override
	public void saveAndUpdate(MainData mainData) {
		// 1.判断该期数据是否出现，交给Action层

		// 2.重新封装数据，通过逻辑计算和值，奇偶比，小大比
		MainData newData = new MainData();
		newData.setId(mainData.getId());
		newData.setFirst(mainData.getFirst());
		newData.setSecond(mainData.getSecond());
		newData.setThird(mainData.getThird());
		newData.setFourth(mainData.getFourth());
		newData.setFifth(mainData.getFifth());
		newData.setSixth(mainData.getSixth());
		newData.setSeventh(mainData.getSeventh());
		newData.setQhz(mainData.getFirst() + mainData.getSecond()
				+ mainData.getThird() + mainData.getFourth()
				+ mainData.getFifth());// 前和值
		newData.setHhz(mainData.getSixth() + mainData.getSeventh());// 后和值

		newData.setQjob(qjob(mainData));// 前区奇偶比值
		newData.setHjob(hjob(mainData));// 后区奇偶比值
		newData.setQxdb(qxdb(mainData));// 前区小大比值
		newData.setHxdb(hxdb(mainData));// 后区小大比值

		newData.setDate(mainData.getDate());
		newData.setRemark(mainData.getRemark());

		// 3.保存MainData
		mainDataDao.save(newData);

	}

	// 自定义，检测一组号码，在历期开奖中的中奖情况
	public String checkByOneData(MainData tempData) {
		StringBuffer checkStr = new StringBuffer();// 获取检测结果
		// 1.将tempData转换为数组
		int[] temp = AnalysisUtils.ObjectConvertToArray(tempData);
		// 2.1读取各期数据
		List<MainData> mainDataLi = mainDataDao.query();
		for (int i = 0; i < mainDataLi.size(); i++) {
			// System.out.println(mainDataLi.get(i));
			// 2.2将各期数据转换为数组
			int[] mainData = AnalysisUtils.ObjectConvertToArray(mainDataLi
					.get(i));
			checkStr.append(AnalysisUtils.checkMacth(temp, mainData) + ";");// 检测一组号码，在历期开奖中的中奖情况

		}
		System.out.println(checkStr.toString());
		return AnalysisUtils.matchResult(checkStr.toString());// 返回中奖情况统计结果
	}

	// 以下是MainDataServiceIml中的辅助方法
	// 求前区奇偶比值
	public String hjob(MainData mainData) {
		int[] temp = { mainData.getSixth(), mainData.getSeventh() };
		int jCount = 0;// 奇数出现次数
		int oCount = 0;// 偶数出现次数
		for (int i = 0; i < 2; i++) {
			if (temp[i] % 2 != 0) {
				jCount++;
			}
			if (temp[i] % 2 == 0) {
				oCount++;
			}
		}
		return jCount + ":" + oCount;
	}

	// 求前区奇偶比值
	public String qjob(MainData mainData) {
		int[] temp = { mainData.getFirst(), mainData.getSecond(),
				mainData.getThird(), mainData.getFourth(), mainData.getFifth() };
		int jCount = 0;// 奇数出现次数
		int oCount = 0;// 偶数出现次数
		for (int i = 0; i < 5; i++) {
			if (temp[i] % 2 != 0) {
				jCount++;
			}
			if (temp[i] % 2 == 0) {
				oCount++;
			}
		}
		return jCount + ":" + oCount;
	}

	// 求前区小大比值
	public String qxdb(MainData mainData) {
		int[] temp = { mainData.getFirst(), mainData.getSecond(),
				mainData.getThird(), mainData.getFourth(), mainData.getFifth() };
		int xCount = 0;// 小数出现次数
		int dCount = 0;// 大数出现次数
		for (int i = 0; i < 5; i++) {
			if (temp[i] <= 18) {
				xCount++;
			}
			if (temp[i] > 18) {
				dCount++;
			}
		}

		return xCount + ":" + dCount;
	}

	// 求后区小大比值
	public String hxdb(MainData mainData) {
		int[] temp = { mainData.getSixth(), mainData.getSeventh() };
		int xCount = 0;// 小数出现次数
		int dCount = 0;// 大数出现次数
		for (int i = 0; i < 2; i++) {
			if (temp[i] <= 6) {
				xCount++;
			}
			if (temp[i] > 6) {
				dCount++;
			}
		}

		return xCount + ":" + dCount;
	}

	
	//自定义，按年，号码查询出现次数，更新每年数据
	@Override
	public void updateMainDataStatistic() {
		System.out.println(mainDataDao.statisticMDSixthCountByMDId(2007001, 2007093, 2)+mainDataDao.statisticMDSeventhCountByMDId(2007001, 2007093, 2));
		
	}

	
	//自定义，按期号段，统计后区码出现次数
	@Override
	public List<NumberCount> statisticMDHQCountByMDId(int startId,int endId){
		int[] hqArray={0,1,2,3,4,5,6,7,8,9,10,11,12};
		List<NumberCount> liNumcount=new ArrayList<NumberCount>();
		for(int i=1;i<hqArray.length;i++){
			NumberCount numCount=new NumberCount();
			int sixthNumberCount=(int) mainDataDao.statisticMDSixthCountByMDId(startId, endId, hqArray[i]);
			int seventhNumberCount=(int) mainDataDao.statisticMDSeventhCountByMDId(startId, endId, hqArray[i]);
			numCount.setNumber(hqArray[i]);
			//System.out.println(hqArray[i]);
			numCount.setCount(sixthNumberCount+seventhNumberCount);
			liNumcount.add(numCount);
		}
		return liNumcount;
	}

	
	//自定义，按期号段，统计前区码出现次数
	@Override
	public List<NumberCount> statisticMDQQCountByMDId(int startId, int endId) {
		int[] qqArray=new int[36];
		for(int i=0;i<qqArray.length;i++){
			qqArray[i]=i;
		}
		List<NumberCount> liNumcount=new ArrayList<NumberCount>();
		for(int i=1;i<qqArray.length;i++){
			NumberCount numCount=new NumberCount();
			int firstNumberCount=(int) mainDataDao.statisticMDFirstCountByMDId(startId, endId, qqArray[i]);
			int secondNumberCount=(int) mainDataDao.statisticMDSecondCountByMDId(startId, endId, qqArray[i]);
			int thirdNumberCount=(int) mainDataDao.statisticMDThirdCountByMDId(startId, endId, qqArray[i]);
			int fourthNumberCount=(int) mainDataDao.statisticMDFourthCountByMDId(startId, endId, qqArray[i]);
			int fifthNumberCount=(int) mainDataDao.statisticMDFifthCountByMDId(startId, endId, qqArray[i]);
			
			numCount.setNumber(qqArray[i]);
			//System.out.println(hqArray[i]);
			numCount.setCount(firstNumberCount+secondNumberCount+thirdNumberCount+fourthNumberCount+fifthNumberCount);
			liNumcount.add(numCount);
		}
		return liNumcount;
	}

}
