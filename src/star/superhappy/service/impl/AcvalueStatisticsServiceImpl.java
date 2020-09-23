package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.AcvalueStatisticsDao;
import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.AcvalueStatistics;
import star.superhappy.pojo.MainData;
import star.superhappy.service.AcvalueStatisticsService;
import star.superhappy.utils.AnalysisUtils;
import star.superhappy.utils.SHUtils;

public class AcvalueStatisticsServiceImpl implements AcvalueStatisticsService {

	private AcvalueStatisticsDao acvalueStatisticsDao;
	
    private MainDataDao mainDataDao;
    
    
	
	public AcvalueStatisticsDao getAcvalueStatisticsDao() {
		return acvalueStatisticsDao;
	}

	public void setAcvalueStatisticsDao(AcvalueStatisticsDao acvalueStatisticsDao) {
		this.acvalueStatisticsDao = acvalueStatisticsDao;
	}

	
	

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}
	
	
    //通用保存
	@Override
	public void save(AcvalueStatistics acvalueStatistics) {
		acvalueStatisticsDao.save(acvalueStatistics);
	}

	//通用更新
	@Override
	public void update(AcvalueStatistics acvalueStatistics) {
		acvalueStatisticsDao.update(acvalueStatistics);
	}

	//通用删除，根据id号删除
	@Override
	public void delete(int id) {
		acvalueStatisticsDao.delete(id);
	}

	//通用查询，根据id号查询
	@Override
	public AcvalueStatistics get(int id) {
		return acvalueStatisticsDao.get(id);
	}

	
	//通用查询，返回list
	@Override
	public List<AcvalueStatistics> query() {
		return acvalueStatisticsDao.query();
	}

	//自定义，自动更新
	@Override
	public void autoUpdate() {
		int preACValue=0;//记录上一期AC
		if(acvalueStatisticsDao.query().size()==0){
			System.out.println("---AC值统计，进入数据初始化---");
			List<MainData> mdLi=mainDataDao.query();
			for(int i=0;i<mdLi.size();i++){
				MainData md=mdLi.get(i);
				if(md.getId()==2007001){//进入2007001初始化
					AcvalueStatistics ac=new AcvalueStatistics();
					int acValue=AnalysisUtils.ACResult(md.getFirst(),md.getSecond(),md.getThird(),md.getFourth(),md.getFifth());
					//System.out.println(md.getId());
					ac.setId(md.getId());
					ac.setAcvalue(acValue);
					ac.setAcjo(AnalysisUtils.numberToJO(acValue));
					ac.setAcxd(AnalysisUtils.numberToXDByAC(acValue));
					ac.setAc012(Integer.parseInt((AnalysisUtils.numberTo012(acValue))));
					ac.setAczhy(AnalysisUtils.numberToZHY(acValue));
					ac.setAczf(Math.abs(acValue));
					ac.setMd(md);
					//System.out.println(ac);
					acvalueStatisticsDao.save(ac);
					preACValue=acValue;
				}else{
					AcvalueStatistics ac=new AcvalueStatistics();
					int acValue=AnalysisUtils.ACResult(md.getFirst(),md.getSecond(),md.getThird(),md.getFourth(),md.getFifth());
					//System.out.println(md.getId());
					ac.setId(md.getId());
					ac.setAcvalue(acValue);
					ac.setAcjo(AnalysisUtils.numberToJO(acValue));
					ac.setAcxd(AnalysisUtils.numberToXDByAC(acValue));
					ac.setAc012(Integer.parseInt((AnalysisUtils.numberTo012(acValue))));
					ac.setAczhy(AnalysisUtils.numberToZHY(acValue));
					ac.setAczf(Math.abs(acValue-preACValue));
					ac.setMd(md);
					//System.out.println(ac);
					acvalueStatisticsDao.save(ac);
					preACValue=acValue;
				}
				
			}
			
		}else{
			System.out.println("---AC值统计，进入数据自动更新---");
			//获取AC统计的最大id
			int maxACId=acvalueStatisticsDao.getAcvalueStatisticsMaxId();
			//获取MainData的最大
			int maxMDId=mainDataDao.getMaxMainDataId();
			if(maxACId<maxMDId){
				System.out.println("---AC值统计，有数据更新---");
				while(maxACId<maxMDId){
					//获取当前AC值
					int preAcValue=acvalueStatisticsDao.get(maxACId).getAcvalue();
					//System.out.println("上一期AC值:"+preAcValue);
					
					//预判下一期maindata 的Id号
					if(maxACId==mainDataDao.getMaxMainDataIdByYear(maxACId/1000)){
						//下一期数据为新一年第一期数据
						maxACId=((maxACId/1000)+1)*1000+1;
						System.out.println("001期数据");
					}else{
						//当年数据
						maxACId++;
						System.out.println("更新同年数据");
					}
					AcvalueStatistics ac=new AcvalueStatistics();
					System.out.println("更新的期号为："+maxACId);
					MainData md=mainDataDao.get(maxACId);
					int acValue=AnalysisUtils.ACResult(md.getFirst(),md.getSecond(),md.getThird(),md.getFourth(),md.getFifth());
					//System.out.println(md.getId());
					ac.setId(md.getId());
					ac.setAcvalue(acValue);
					ac.setAcjo(AnalysisUtils.numberToJO(acValue));
					ac.setAcxd(AnalysisUtils.numberToXDByAC(acValue));
					ac.setAc012(Integer.parseInt((AnalysisUtils.numberTo012(acValue))));
					ac.setAczhy(AnalysisUtils.numberToZHY(acValue));
					//System.out.println("上一期AC值2:"+preAcValue);
					ac.setAczf(Math.abs(acValue-preAcValue));
					ac.setMd(md);
					//System.out.println(ac);
					acvalueStatisticsDao.save(ac);
				}
			}else{
				System.out.println("---AC值统计，无数据更新---");
			}
		}
	}

}
