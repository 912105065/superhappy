package star.superhappy.service.impl;

import java.util.List;

import org.hibernate.Query;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.YiLouDataDao;
import star.superhappy.dao.YiLouZhiStatisticV2Dao;
import star.superhappy.pojo.YiLouZhiStatisticV2;
import star.superhappy.service.YiLouZhiStatisticV2Service;

public class YiLouZhiStatisticV2ServiceImpl implements YiLouZhiStatisticV2Service{

	
	private YiLouZhiStatisticV2Dao yiLouZhiStatisticV2Dao;
	private MainDataDao mainDataDao;
    private YiLouDataDao yiLouDataDao;
    
    
    
	
	public YiLouZhiStatisticV2Dao getYiLouZhiStatisticV2Dao() {
		return yiLouZhiStatisticV2Dao;
	}

	public void setYiLouZhiStatisticV2Dao(
			YiLouZhiStatisticV2Dao yiLouZhiStatisticV2Dao) {
		this.yiLouZhiStatisticV2Dao = yiLouZhiStatisticV2Dao;
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

	
	//保存
	@Override
	public void save(YiLouZhiStatisticV2 ylzsc) {
		yiLouZhiStatisticV2Dao.save(ylzsc);
	}

	
	//更新
	@Override
	public void update(YiLouZhiStatisticV2 ylzsc) {
		// TODO Auto-generated method stub
		yiLouZhiStatisticV2Dao.update(ylzsc);
	}

	
	//根据id删除
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		yiLouZhiStatisticV2Dao.delete(id);
	}

	
	//查询，返回单个实体
	@Override
	public YiLouZhiStatisticV2 get(int id) {
		// TODO Auto-generated method stub
		return yiLouZhiStatisticV2Dao.get(id);
	}

	
	//查询，返回List
	@Override
	public List<YiLouZhiStatisticV2> query() {
		// TODO Auto-generated method stub
		return yiLouZhiStatisticV2Dao.query();
	}

	
	//自定义，更新YiLouZhiStatisticV2
	@Override
	public void batchUpdate() {
		//初始化YiLouZhiStatisticV2
		if(yiLouZhiStatisticV2Dao.query().size()==0){
			System.out.println("===进入yiLouZhiStatisticV2初始化和更新===");
			for(int i=1;i<36;i++){
				YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
				ylzsV2.setId(i);
				ylzsV2.setNoValue(i);
				ylzsV2.setQhType("qq");
				ylzsV2.setMaxYlvalueNowYear(0);
				ylzsV2.setMaxYlvalue(0);
				ylzsV2.setNowYlvalue(0);
				ylzsV2.setNearYlOneYlvalue(0);
				ylzsV2.setNearYlTwoYlvalue(0);
				ylzsV2.setNearYlThreeYlvalue(0);
				ylzsV2.setNearYlFourYlvalue(0);
				ylzsV2.setNearYlFiveYlvalue(0);
				ylzsV2.setNowId(0);
				yiLouZhiStatisticV2Dao.save(ylzsV2);
			  }
			for(int i=1;i<13;i++){
				YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
				ylzsV2.setId(i+35);
				ylzsV2.setNoValue(i);
				ylzsV2.setQhType("hq");
				ylzsV2.setMaxYlvalueNowYear(0);
				ylzsV2.setMaxYlvalue(0);
				ylzsV2.setNowYlvalue(0);
				ylzsV2.setNearYlOneYlvalue(0);
				ylzsV2.setNearYlTwoYlvalue(0);
				ylzsV2.setNearYlThreeYlvalue(0);
				ylzsV2.setNearYlFourYlvalue(0);
				ylzsV2.setNearYlFiveYlvalue(0);
				ylzsV2.setNowId(0);
				yiLouZhiStatisticV2Dao.save(ylzsV2);
			}
			
			
			//更新YiLouZhiStatisticV2
			//1.读取当前最大mainDataId
			int maxMainId=mainDataDao.getMaxMainDataId();
			int minMainIdOneYeay=(maxMainId/1000)*1000+1;//当年起始maindataId
			
			
			//2.各号码遗漏数据查询
			for(int i=1;i<48;i++){
				if(i<=35){
					//号码qqNo1,qqNo1Flag,hqNo1,hqNo2Flag拼装
					String qqNon="qqNo"+String.valueOf(i);
					String qqNonFlag="qqNo"+String.valueOf(i)+"Flag";
					//System.out.println(qqNon+"-"+qqNonFlag);
					//a[i][0]  显示号码
					//a[i][1]  遗漏值或0，0代码为号码值
					//a[i][2]  flag  0-显示遗漏值,1-显示号码显示[i]0，2-最大遗漏值，下一期就是开奖号码，3-重号
					
					
					//YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
					YiLouZhiStatisticV2 ylzsV2=yiLouZhiStatisticV2Dao.get(i);
					//前区号码，当年最大遗漏值
					//System.out.println(yiLouDataDao.getMaxNonYiLouZhiByYear(qqNon, qqNonFlag, minMainIdOneYeay,maxMainId));
					//ylzsV2.setMaxYlvalueNowYear(yiLouDataDao.getMaxNonYiLouZhiByYear(qqNon, qqNonFlag, minMainIdOneYeay,maxMainId));
					int maxYlvalueNowYear=yiLouDataDao.getMaxNonYiLouZhiByYear(qqNon, qqNonFlag, minMainIdOneYeay,maxMainId);
					
					if(maxYlvalueNowYear==-1){
						ylzsV2.setMaxYlvalueNowYear(null);
					}else{
						ylzsV2.setMaxYlvalueNowYear(maxYlvalueNowYear);
					}
					
					//前区号码，最大遗漏值
					//System.out.println(yiLouDataDao.getMaxNonYiLouZhi(qqNon, qqNonFlag));
					ylzsV2.setMaxYlvalue(yiLouDataDao.getMaxNonYiLouZhi(qqNon, qqNonFlag));
					
					//前区号码，当前遗漏值
					//System.out.println(yiLouDataDao.getYiLouZhiById(qqNon, maxMainId));
					ylzsV2.setNowYlvalue(yiLouDataDao.getYiLouZhiById(qqNon, maxMainId));
					
					
					//前区近5期击中号码遗漏值
					//System.out.println(yiLouDataDao.queryJZYiLouZhiByN(qqNon,qqNonFlag, 5));
					List<Number> number=yiLouDataDao.queryJZYiLouZhiByN(qqNon,qqNonFlag, 5);
					ylzsV2.setNearYlOneYlvalue((Integer) number.get(0));
					ylzsV2.setNearYlTwoYlvalue((Integer) number.get(1));
					ylzsV2.setNearYlThreeYlvalue((Integer) number.get(2));
					ylzsV2.setNearYlFourYlvalue((Integer) number.get(3));
					ylzsV2.setNearYlFiveYlvalue((Integer) number.get(4));
					ylzsV2.setNowId(maxMainId);
					ylzsV2.setId(i);
					ylzsV2.setQhType("qq");
					yiLouZhiStatisticV2Dao.update(ylzsV2);
					
				}else{
					String hqNon="hqNo"+String.valueOf(i-35);
					String hqNonFlag="hqNo"+String.valueOf(i-35)+"Flag";
					//YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
					YiLouZhiStatisticV2 ylzsV2=yiLouZhiStatisticV2Dao.get(i);
					//后区号码，当年最大遗漏值
					//System.out.println(yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId));
					//ylzsV2.setMaxYlvalueNowYear(yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId));
                    int maxYlvalueNowYear=yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId);
					
					if(maxYlvalueNowYear==-1){
						ylzsV2.setMaxYlvalueNowYear(null);
					}else{
						ylzsV2.setMaxYlvalueNowYear(maxYlvalueNowYear);
					}
					
					
					
					//后区号码，最大遗漏值
					//System.out.println(yiLouDataDao.getMaxNonYiLouZhi(hqNon, hqNonFlag));
					ylzsV2.setMaxYlvalue(yiLouDataDao.getMaxNonYiLouZhi(hqNon, hqNonFlag));
					
					
					//后区号码，当前遗漏值
					//System.out.println(yiLouDataDao.getYiLouZhiById(hqNon, maxMainId));
					ylzsV2.setNowYlvalue(yiLouDataDao.getYiLouZhiById(hqNon, maxMainId));
					
					
					//后区近5期击中号码遗漏值
					//System.out.println(yiLouDataDao.queryJZYiLouZhiByN(hqNon,hqNonFlag, 5));
					List<Number> number=yiLouDataDao.queryJZYiLouZhiByN(hqNon,hqNonFlag, 5);
					ylzsV2.setNearYlOneYlvalue((Integer) number.get(0));
					ylzsV2.setNearYlTwoYlvalue((Integer) number.get(1));
					ylzsV2.setNearYlThreeYlvalue((Integer) number.get(2));
					ylzsV2.setNearYlFourYlvalue((Integer) number.get(3));
					ylzsV2.setNearYlFiveYlvalue((Integer) number.get(4));
					ylzsV2.setNowId(maxMainId);
					ylzsV2.setId(i);
					ylzsV2.setQhType("hq");
					yiLouZhiStatisticV2Dao.update(ylzsV2);
				}
			}
			
			}else{
			System.out.println("===进入yiLouZhiStatisticV2更新===");
			
			//更新YiLouZhiStatisticV2
			//1.读取当前最大mainDataId
			int maxMainId=mainDataDao.getMaxMainDataId();
			int minMainIdOneYeay=(maxMainId/1000)*1000+1;//当年起始maindataId
			
			
			if(yiLouZhiStatisticV2Dao.getNowId()!=maxMainId){
				System.out.println("===进入更新===");
				//2.各号码遗漏数据查询
				for(int i=1;i<48;i++){
					if(i<=35){
						//号码qqNo1,qqNo1Flag,hqNo1,hqNo2Flag拼装
						String qqNon="qqNo"+String.valueOf(i);
						String qqNonFlag="qqNo"+String.valueOf(i)+"Flag";
						//System.out.println(qqNon+"-"+qqNonFlag);
						//a[i][0]  显示号码
						//a[i][1]  遗漏值或0，0代码为号码值
						//a[i][2]  flag  0-显示遗漏值,1-显示号码显示[i]0，2-最大遗漏值，下一期就是开奖号码，3-重号
						
						
						//YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
						YiLouZhiStatisticV2 ylzsV2=yiLouZhiStatisticV2Dao.get(i);
						//前区号码，当年最大遗漏值
						//System.out.println(yiLouDataDao.getMaxNonYiLouZhiByYear(qqNon, qqNonFlag, minMainIdOneYeay,maxMainId));
						
						int maxYlvalueNowYear=yiLouDataDao.getMaxNonYiLouZhiByYear(qqNon, qqNonFlag, minMainIdOneYeay,maxMainId);
						
						if(maxYlvalueNowYear==-1){
							ylzsV2.setMaxYlvalueNowYear(null);
						}else{
							ylzsV2.setMaxYlvalueNowYear(maxYlvalueNowYear);
						}
						
						
						
						//前区号码，最大遗漏值
						//System.out.println(yiLouDataDao.getMaxNonYiLouZhi(qqNon, qqNonFlag));
						ylzsV2.setMaxYlvalue(yiLouDataDao.getMaxNonYiLouZhi(qqNon, qqNonFlag));
						
						//前区号码，当前遗漏值
						//System.out.println(yiLouDataDao.getYiLouZhiById(qqNon, maxMainId));
						ylzsV2.setNowYlvalue(yiLouDataDao.getYiLouZhiById(qqNon, maxMainId));
						
						
						//前区近5期击中号码遗漏值
						//System.out.println(yiLouDataDao.queryJZYiLouZhiByN(qqNon,qqNonFlag, 5));
						List<Number> number=yiLouDataDao.queryJZYiLouZhiByN(qqNon,qqNonFlag, 5);
						ylzsV2.setNearYlOneYlvalue((Integer) number.get(0));
						ylzsV2.setNearYlTwoYlvalue((Integer) number.get(1));
						ylzsV2.setNearYlThreeYlvalue((Integer) number.get(2));
						ylzsV2.setNearYlFourYlvalue((Integer) number.get(3));
						ylzsV2.setNearYlFiveYlvalue((Integer) number.get(4));
						ylzsV2.setNowId(maxMainId);
						ylzsV2.setId(i);
						ylzsV2.setQhType("qq");
						yiLouZhiStatisticV2Dao.update(ylzsV2);
						
					}else{
						String hqNon="hqNo"+String.valueOf(i-35);
						String hqNonFlag="hqNo"+String.valueOf(i-35)+"Flag";
						//YiLouZhiStatisticV2 ylzsV2=new YiLouZhiStatisticV2();
						YiLouZhiStatisticV2 ylzsV2=yiLouZhiStatisticV2Dao.get(i);
						//后区号码，当年最大遗漏值
						//System.out.println(yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId));
						//ylzsV2.setMaxYlvalueNowYear(yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId));
						int maxYlvalueNowYear=yiLouDataDao.getMaxNonYiLouZhiByYear(hqNon, hqNonFlag, minMainIdOneYeay,maxMainId);
						
						if(maxYlvalueNowYear==-1){
							ylzsV2.setMaxYlvalueNowYear(null);
						}else{
							ylzsV2.setMaxYlvalueNowYear(maxYlvalueNowYear);
						}
						
						
						
						//后区号码，最大遗漏值
						//System.out.println(yiLouDataDao.getMaxNonYiLouZhi(hqNon, hqNonFlag));
						ylzsV2.setMaxYlvalue(yiLouDataDao.getMaxNonYiLouZhi(hqNon, hqNonFlag));
						
						
						//后区号码，当前遗漏值
						//System.out.println(yiLouDataDao.getYiLouZhiById(hqNon, maxMainId));
						ylzsV2.setNowYlvalue(yiLouDataDao.getYiLouZhiById(hqNon, maxMainId));
						
						
						//后区近5期击中号码遗漏值
						//System.out.println(yiLouDataDao.queryJZYiLouZhiByN(hqNon,hqNonFlag, 5));
						List<Number> number=yiLouDataDao.queryJZYiLouZhiByN(hqNon,hqNonFlag, 5);
						ylzsV2.setNearYlOneYlvalue((Integer) number.get(0));
						ylzsV2.setNearYlTwoYlvalue((Integer) number.get(1));
						ylzsV2.setNearYlThreeYlvalue((Integer) number.get(2));
						ylzsV2.setNearYlFourYlvalue((Integer) number.get(3));
						ylzsV2.setNearYlFiveYlvalue((Integer) number.get(4));
						ylzsV2.setNowId(maxMainId);
						ylzsV2.setId(i);
						ylzsV2.setQhType("hq");
						yiLouZhiStatisticV2Dao.update(ylzsV2);
					}
				}
			}else{
				System.out.println("===yiLouZhiStatisticV2已经是最新数据===");
			}
			
			
	    }
	}

	@Override
	public void getMaxNonYiLouZhiByYear() {
		// TODO Auto-generated method stub
		System.out.println(yiLouDataDao.getMaxNonYiLouZhiByYear("qqNo1", "qqNo1Flag", 2020001,2020001));
	}

}
