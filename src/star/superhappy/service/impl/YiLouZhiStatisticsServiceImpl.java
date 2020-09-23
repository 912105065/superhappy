package star.superhappy.service.impl;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.YiLouDataDao;
import star.superhappy.dao.YiLouZhiStatisticsDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.YiLouData;
import star.superhappy.pojo.YiLouZhiStatistics;
import star.superhappy.service.YiLouZhiStatisticsService;
import star.superhappy.utils.SHUtils;

public class YiLouZhiStatisticsServiceImpl implements YiLouZhiStatisticsService{

	
	private YiLouZhiStatisticsDao yiLouZhiStatisticsDao;
    private MainDataDao mainDataDao;
    private YiLouDataDao yiLouDataDao;

	public YiLouZhiStatisticsDao getYiLouZhiStatisticsDao() {
		return yiLouZhiStatisticsDao;
	}

	public void setYiLouZhiStatisticsDao(YiLouZhiStatisticsDao yiLouZhiStatisticsDao) {
		this.yiLouZhiStatisticsDao = yiLouZhiStatisticsDao;
	}
	
	
	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	public YiLouDataDao getYiLouDataDao() {
		return yiLouDataDao;
	}

	public void setYiLouDataDao(YiLouDataDao yiLouDataDao) {
		this.yiLouDataDao = yiLouDataDao;
	}

	//自定义，初始化
	@Override
	public void initYiLouZhiStatisics() {
		//1.2007001期遗漏值初始化
		//1.1读取2007001期mainData数据
		MainData md2007001=mainDataDao.get(2007001);
		//1.2初始化遗漏值
		YiLouZhiStatistics ylz=new YiLouZhiStatistics();
		ylz.setId(md2007001.getId());
		ylz.setFirstZ(0);
		ylz.setSecondZ(0);
		ylz.setThridZ(0);
		ylz.setFourthZ(0);
		ylz.setFifthZ(0);
		ylz.setSixthZ(0);
		ylz.setSeventhZ(0);
	    ylz.setMainData(md2007001);
		
		yiLouZhiStatisticsDao.save(ylz);
		
	}

	
	
	//自定义，批量插入
	@Override
	public void batchYiLouZhiStatisics() {
		//获取当前YiLouZhiStatistics的最大id
		int maxYiLouZhiStatisicsId=yiLouZhiStatisticsDao.getYiLouZhiStatisticsMaxId();
		//获取当前mainData的最大id
		int maxMainDataId=mainDataDao.getMaxMainDataId();
		
		String year=String.valueOf(maxYiLouZhiStatisicsId);//year，maxYiLouZhiStatisicsId转换成年
		String  year4=year.substring(0,4);//截取2007
		int yiLouZhiStatisicsId=maxYiLouZhiStatisicsId;//yYiLouZhiStatisicsId下一期的id
		if(maxYiLouZhiStatisicsId==mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(year4))){//当期的遗漏值id=根据当年的最大mainDataId
			yiLouZhiStatisicsId=(Integer.parseInt(year4)+1)*1000+1;//2008154最后一期，下一期不是2008155，而是2009001
			//System.out.println("修正为下一年yiLouId："+yiLouZhiStatisicsId);
		}else{
			yiLouZhiStatisicsId=yiLouZhiStatisicsId+1;
			//System.out.println("修正为当年yiLouId："+yiLouZhiStatisicsId);
		}
		
		if(maxMainDataId>(yiLouZhiStatisicsId-1)){//需要更新
			System.out.println("更新遗漏统计值数据。。。");
			while(maxMainDataId>(yiLouZhiStatisicsId-1)){
				
				//遗漏统计值临时存储
				int YiLouSA[]=new int[5];//前区
				int YiLouSB[]=new int[2];//后区
				
				//1.读取mainData，当前期号的下一期,遗漏统计表中未更新的数据
				MainData maindata=mainDataDao.get(yiLouZhiStatisicsId);
				//将mainData的数据保存进入数组temp1，temp2
				int[] temp1 = new int[5];// 存储前区数据
				temp1[0] = maindata.getFirst();
				temp1[1] = maindata.getSecond();
				temp1[2] = maindata.getThird();
				temp1[3] = maindata.getFourth();
				temp1[4] = maindata.getFifth();
				int[] temp2 = new int[2];// 存储后区数据
				temp2[0] = maindata.getSixth();
				temp2[1] = maindata.getSeventh();
				//2.期号判断
				String id=String.valueOf(maindata.getId());
				String id1=id.substring(0,4);//截取2007
				String id2=id.substring(4,7);//截取002
				
				if(id2.equals("001")){//对于2008001期处理，是找上一期的数据2007093
					//获得上一年的最大期号
					int preMainId=mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(id1)-1);
					//获取上一期最大期号的遗漏数据
					//1.获取上一期的YiLouData
					YiLouData preYiLouData=yiLouDataDao.get(preMainId);
					
					//对a[][]和b[][]赋值
					int[][] a=SHUtils.copyToArrayA(preYiLouData);
					int[][] b=SHUtils.copyToArrayB(preYiLouData);
					//前区
					for(int i=0;i<5;i++){
						if(a[temp1[i]-1][2]==2){
							YiLouSA[i]=a[temp1[i]-1][1];
						}
						if(a[temp1[i]-1][2]==3){
							YiLouSA[i]=0;
						}
						if(a[temp1[i]-1][2]==1){
							YiLouSA[i]=0;
						}
					}
					//后区区
					for(int i=0;i<2;i++){
						if(b[temp2[i]-1][2]==2){
							YiLouSB[i]=b[temp2[i]-1][1];
						}
						if(b[temp2[i]-1][2]==3){
							YiLouSB[i]=0;
						}
						if(b[temp2[i]-1][2]==1){
							YiLouSB[i]=0;
						}
					}
					
					//yiLouZhiStatisics赋值
					YiLouZhiStatistics yiLouZhiStatisics=new YiLouZhiStatistics();
					yiLouZhiStatisics.setId(yiLouZhiStatisicsId);
					yiLouZhiStatisics.setFirstZ(YiLouSA[0]);
					yiLouZhiStatisics.setSecondZ(YiLouSA[1]);
					yiLouZhiStatisics.setThridZ(YiLouSA[2]);
					yiLouZhiStatisics.setFourthZ(YiLouSA[3]);
					yiLouZhiStatisics.setFifthZ(YiLouSA[4]);
					yiLouZhiStatisics.setSixthZ(YiLouSB[0]);
					yiLouZhiStatisics.setSeventhZ(YiLouSB[1]);
					yiLouZhiStatisics.setMainData(maindata);
					yiLouZhiStatisticsDao.save(yiLouZhiStatisics);
					
					
				}else{//非001期处理
					//1.获取上一期的YiLouData
					YiLouData preYiLouData=yiLouDataDao.get(yiLouZhiStatisicsId-1);
					//对a[][]和b[][]赋值
					int[][] a=SHUtils.copyToArrayA(preYiLouData);
					int[][] b=SHUtils.copyToArrayB(preYiLouData);
					//前区
					for(int i=0;i<5;i++){
						if(a[temp1[i]-1][2]==2){
							YiLouSA[i]=a[temp1[i]-1][1];
						}
						if(a[temp1[i]-1][2]==3){
							YiLouSA[i]=0;
						}
						if(a[temp1[i]-1][2]==1){
							YiLouSA[i]=0;
						}
					}
					//后区区
					for(int i=0;i<2;i++){
						if(b[temp2[i]-1][2]==2){
							YiLouSB[i]=b[temp2[i]-1][1];
						}
						if(b[temp2[i]-1][2]==3){
							YiLouSB[i]=0;
						}
						if(b[temp2[i]-1][2]==1){
							YiLouSB[i]=0;
						}
					}
					
					//yiLouZhiStatisics赋值
					YiLouZhiStatistics yiLouZhiStatisics=new YiLouZhiStatistics();
					yiLouZhiStatisics.setId(yiLouZhiStatisicsId);
					yiLouZhiStatisics.setFirstZ(YiLouSA[0]);
					yiLouZhiStatisics.setSecondZ(YiLouSA[1]);
					yiLouZhiStatisics.setThridZ(YiLouSA[2]);
					yiLouZhiStatisics.setFourthZ(YiLouSA[3]);
					yiLouZhiStatisics.setFifthZ(YiLouSA[4]);
					yiLouZhiStatisics.setSixthZ(YiLouSB[0]);
					yiLouZhiStatisics.setSeventhZ(YiLouSB[1]);
					yiLouZhiStatisics.setMainData(maindata);
					yiLouZhiStatisticsDao.save(yiLouZhiStatisics);
				}
			
			
				
				//判断yiLouZhiStatisicsId
				//期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
				String tempId=String.valueOf(yiLouZhiStatisicsId);
				String tempId1=id.substring(0,4);//截取2007
				
				if(yiLouZhiStatisicsId==mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(tempId1))){
					yiLouZhiStatisicsId=(Integer.parseInt(tempId1)+1)*1000+1;
					//System.out.println("修正为下一年yiLouId："+yiLouZhiStatisicsId);
				}else{
					yiLouZhiStatisicsId++;
					//System.out.println("修正为当年yiLouId："+yiLouZhiStatisicsId);
				}
				
			
			}
		}else{
			System.out.println("遗漏值统计值数据已经是最新！");
		}
		
	}

	
	//自定义，获取最大Id号
	@Override
	public int getYiLouZhiStatisticsMaxId() {
		// TODO Auto-generated method stub
		return yiLouZhiStatisticsDao.getYiLouZhiStatisticsMaxId();
	}
	
	
}
