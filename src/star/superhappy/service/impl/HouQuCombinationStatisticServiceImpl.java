package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.HouQuCombinationStatisticDao;
import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.HouQuCombinationStatistic;
import star.superhappy.pojo.MainData;
import star.superhappy.service.HouQuCombinationStatisticService;
import star.superhappy.utils.SHUtils;

public class HouQuCombinationStatisticServiceImpl implements HouQuCombinationStatisticService {

	
	private HouQuCombinationStatisticDao hqCombinationStatisticDao;
	private MainDataDao mainDataDao;
	

	public HouQuCombinationStatisticDao getHqCombinationStatisticDao() {
		return hqCombinationStatisticDao;
	}

	public void setHqCombinationStatisticDao(
			HouQuCombinationStatisticDao hqCombinationStatisticDao) {
		this.hqCombinationStatisticDao = hqCombinationStatisticDao;
	}

	
	
	
	
	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	

	
	//保存
	@Override
	public void save(HouQuCombinationStatistic hqCombinationStatistic) {
		hqCombinationStatisticDao.save(hqCombinationStatistic);
	}

	
	//更新
	@Override
	public void update(HouQuCombinationStatistic hqCombinationStatistic) {
		hqCombinationStatisticDao.update(hqCombinationStatistic);
	}

	
	//根据id删除单个实体
	@Override
	public void delete(int id) {
		hqCombinationStatisticDao.delete(id);
	}

	
	//根据id获取单个实体
	@Override
	public HouQuCombinationStatistic get(int id) {
		return hqCombinationStatisticDao.get(id);
	}

	
	//查询，返回list
	@Override
	public List<HouQuCombinationStatistic> query() {
		// TODO Auto-generated method stub
		return hqCombinationStatisticDao.query();
	}

	
	
	//以下service层独立方法
	//初始化，批量插入数据
		@Override
		public void initHqCombinationStatistic() {
			//1.获取mainData的最大期号，和最小期号
			int maxMainDataId=mainDataDao.getMaxMainDataId();
			int minMainDataId=mainDataDao.getMinMainDataId();
			int maxYear=maxMainDataId/1000;
			int minYear=minMainDataId/1000;
			//2.按年开始统计后区组合并赋值
			while(minYear<maxYear+1){
				//i-后区第一位，j-后区第二位
				for(int i=1;i<12;i++)
					for(int j=2;j<13;j++){
						if(i<j){
							HouQuCombinationStatistic hqComStatistic=new HouQuCombinationStatistic();
							hqComStatistic.setHqCombination(SHUtils.hqConvertCombination(i, j));//后区号码，转换成组合形式1-2
							hqComStatistic.setHqComCount(Integer.parseInt(String.valueOf(mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(i, j,SHUtils.ToYearPrevious(minYear), SHUtils.ToYearNext(minYear)))));
							hqComStatistic.setHq012(SHUtils.hqConvert012(i, j));
							hqComStatistic.setHqSum(SHUtils.hqSum(i, j));
							hqComStatistic.setHqinterval(SHUtils.hqInterval(i, j));
							hqComStatistic.setYear(minYear);
							
							System.out.println("插入数据："+hqComStatistic);
							//3.保存
							hqCombinationStatisticDao.save(hqComStatistic);
						}
						
					}
				minYear++;
			}
			
		}
	
	//自动更新后区号码组合统计表
	@Override
	public String autoUpdateHQCombinateStatistic() {
		//1.获取mainData的最大id值和hqCombinationStatistic的组合总次数
		long maxMainDataCount=mainDataDao.getCountByMainData();
		long hqComStCount=hqCombinationStatisticDao.getMaxHQCombinationStatisticDaoId();
		System.out.println("mainData的总次数："+maxMainDataCount);
		System.out.println("后区组合的总次数："+hqComStCount);
		if(maxMainDataCount>hqComStCount){
			System.out.println("后区号码组合统计表有信息需要更新！");
			System.out.println("进入后区组合统计更新准备！");
			//2.求出要更新的mainData的Id号
			int mainDataIdNow=mainDataDao.getMaxMainDataId();//mainData的最大id号
			int gap=mainDataIdNow%1000;//获取当年一共开了多少期，2008007，2008007/1000=7
			System.out.println("mainData最大Id："+mainDataIdNow+"，该年:"+mainDataIdNow/1000+"，该年共开多少期："+gap);
			System.out.println("maxMainDataCount-hqComStCount"+(maxMainDataCount-hqComStCount));
			//3.当年数据更新，未更新期数<=当年已开期数
			if((maxMainDataCount-hqComStCount)<=gap){
				System.out.println("进入同一年后区组合统计更新统计！");
				long startMainDataId=mainDataIdNow-(maxMainDataCount-hqComStCount)+1;//起始期号，假设现在mainDataId-30,差值为3，更新的起始期号为28
				
				if(startMainDataId%1000==1){//001期，如2008001，需要初始化当年的后区组合数据,并统计保存
					System.out.println("进入同一年后区组合更新统计，需要初始化，保存数据");
					int year=(int) (startMainDataId/1000);
					//i-后区第一位，j-后区第二位
					for(int i=1;i<12;i++)
						for(int j=2;j<13;j++){
							if(i<j){
								HouQuCombinationStatistic hqComStatistic=new HouQuCombinationStatistic();
								hqComStatistic.setHqCombination(SHUtils.hqConvertCombination(i, j));//后区号码，转换成组合形式1-2
								hqComStatistic.setHqComCount(Integer.parseInt(String.valueOf(mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(i, j,SHUtils.ToYearPrevious(year), SHUtils.ToYearNext(year)))));
								hqComStatistic.setHq012(SHUtils.hqConvert012(i, j));
								hqComStatistic.setHqSum(SHUtils.hqSum(i, j));
								hqComStatistic.setHqinterval(SHUtils.hqInterval(i, j));
								hqComStatistic.setYear(year);
								//System.out.println("插入数据："+hqComStatistic);
								//3.保存
								hqCombinationStatisticDao.save(hqComStatistic);
							}
						}
			         }else{
				    //当年的后区组合已经初始化，更新数据
				    System.out.println("进入同一年后区组合更新统计，更新数据");
					//查询出需要更新的mainData，同步更新后区号码组合统计表
					for(int i=0;i<(maxMainDataCount-hqComStCount);i++){
						MainData md=mainDataDao.get((int)startMainDataId+i);
						//System.out.println("mainDate数据："+md);
						//获取hqComStatistic
						HouQuCombinationStatistic hqComStatistic=hqCombinationStatisticDao.getByCombinationAndYear(SHUtils.hqConvertCombination(md.getSixth(), md.getSeventh()), md.getId()/1000);
						//System.out.println("原数据："+hqComStatistic);
						//赋入更新的值
						hqComStatistic.setHqComCount(Integer.parseInt(String.valueOf(mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(md.getSixth(),md.getSeventh(),SHUtils.ToYearPrevious(md.getId()/1000), SHUtils.ToYearNext(md.getId()/1000)))));
						
						//System.out.println("更新数据："+hqComStatistic);
						//3.更新
						hqCombinationStatisticDao.update(hqComStatistic);
				}
		}
			}else{
				//4.跨年数据更新，未更新期数>当年已开期数
				//4.1当年后区组合统计的更新
				System.out.println("进入后区组合更新-跨年数据更新");
				int updateNow=gap;//当年要更新的期数
				System.out.println("当年要更新的期数:"+updateNow);
				long startMainDataId=mainDataIdNow-gap+1;//起始期号，假设现在mainDataId-30,差值为3，更新的起始期号为28
				int year=(int) (startMainDataId/1000);
				//i-后区第一位，j-后区第二位
				for(int i=1;i<12;i++)
					for(int j=2;j<13;j++){
						if(i<j){
							HouQuCombinationStatistic hqComStatistic=new HouQuCombinationStatistic();
							hqComStatistic.setHqCombination(SHUtils.hqConvertCombination(i, j));//后区号码，转换成组合形式1-2
							hqComStatistic.setHqComCount(Integer.parseInt(String.valueOf(mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(i, j,SHUtils.ToYearPrevious(year), SHUtils.ToYearNext(year)))));
							hqComStatistic.setHq012(SHUtils.hqConvert012(i, j));
							hqComStatistic.setHqSum(SHUtils.hqSum(i, j));
							hqComStatistic.setHqinterval(SHUtils.hqInterval(i, j));
							hqComStatistic.setYear(year);
							
							//System.out.println("插入数据："+hqComStatistic);
							//3.保存
							hqCombinationStatisticDao.save(hqComStatistic);
						}		
					}
				//4.2上一年后区组合统计的更新
				int updatePreYear=(int) (maxMainDataCount-hqComStCount-gap);//上一年要更新的期数
				//System.out.println("上一年要更新的期数:"+updatePreYear);
				int mainDataMaxIdPreYear=mainDataDao.getMaxMainDataIdByYear(mainDataIdNow/1000-1);//上一年的最大id
				long startMainDataIdPreYear=mainDataMaxIdPreYear-updatePreYear+1;//上一年的起始期号，假设现在mainDataId-30,差值为3，更新的起始期号为28
				//System.out.println("上一年的起始期号"+startMainDataIdPreYear);
				for(int j=0;j<updatePreYear;j++){
					//System.out.println("上一年起始期号："+(int)(startMainDataIdPreYear+j));
					MainData md1=mainDataDao.get((int)startMainDataIdPreYear+j);
					//System.out.println("上一年更新："+md1);
					//赋值 hqComStatistic
					//获取hqComStatistic
					HouQuCombinationStatistic hqComStatistic=hqCombinationStatisticDao.getByCombinationAndYear(SHUtils.hqConvertCombination(md1.getSixth(), md1.getSeventh()), md1.getId()/1000);
					//System.out.println("原数据："+hqComStatistic);
					//赋入更新的值
					hqComStatistic.setHqComCount(Integer.parseInt(String.valueOf(mainDataDao.getCountByDateAndhqNumber1AndhqNumber2(md1.getSixth(),md1.getSeventh(),SHUtils.ToYearPrevious(md1.getId()/1000), SHUtils.ToYearNext(md1.getId()/1000)))));
					
					//System.out.println("更新数据："+hqComStatistic);
					//3.更新
					hqCombinationStatisticDao.update(hqComStatistic);
			}
	   }
		}
		else if(maxMainDataCount<hqComStCount){
			System.out.println("数据异常，请检查！");
		}else if(maxMainDataCount==hqComStCount){
			System.out.println("数据无需更新！");
		}
		return "更新了"+(maxMainDataCount-hqComStCount)+"条数据";
	}






	
	//自定义，根据组合号，年份查询
	@Override
	public HouQuCombinationStatistic getByCombinationAndYear(
			String combination, int year) {
		// TODO Auto-generated method stub
		return hqCombinationStatisticDao.getByCombinationAndYear(combination, year);
	}
	
	
	
	
}
