package star.superhappy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import star.superhappy.dao.MainDataBasicAnalysisDao;
import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.MainDataBasicAnalysis;
import star.superhappy.service.MainDataBasicAnalysisService;
import star.superhappy.utils.AnalysisUtils;

public class MainDataBasicAnalysisServiceImpl implements
		MainDataBasicAnalysisService {

	@Resource
	private MainDataBasicAnalysisDao mainDataBasicAnalysisDao;

	@Resource
	private MainDataDao mainDataDao;

	public MainDataBasicAnalysisDao getMainDataBasicAnalysisDao() {
		return mainDataBasicAnalysisDao;
	}

	public void setMainDataBasicAnalysisDao(
			MainDataBasicAnalysisDao mainDataBasicAnalysisDao) {
		this.mainDataBasicAnalysisDao = mainDataBasicAnalysisDao;
	}

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	// 保存
	public void save(MainDataBasicAnalysis mainDataBasicAnalysis) {
		mainDataBasicAnalysisDao.save(mainDataBasicAnalysis);
	}

	// 通用更新
	public void update(MainDataBasicAnalysis mainDataBasicAnalysis) {
		mainDataBasicAnalysisDao.update(mainDataBasicAnalysis);
	}

	// 删除
	public void delete(int id) {
		mainDataBasicAnalysisDao.delete(id);
	}

	// 查询，返回单个实体
	public MainDataBasicAnalysis get(int id) {
		return mainDataBasicAnalysisDao.get(id);
	}

	// 查询，返回List
	public List<MainDataBasicAnalysis> query() {
		return mainDataBasicAnalysisDao.query();
	}

	// 自定义，初始化，批量更新
	public void batchUpdate() {

		 int mainDataMaxId=mainDataDao.getMaxMainDataId();
		 //int mainDataBasicAnalysisMaxId=mainDataBasicAnalysisDao.getMainDataBasicAnalysisMaxId();
		 
		//获取起始的nowMainDataBasicAnalysisId
		int nowMainDataBasicAnalysisId=0;
		if(mainDataBasicAnalysisDao.query().size()==0){//mainDataBasicAnalysis为空，从2007001开始更新
			nowMainDataBasicAnalysisId=2007001;
		}else{
			nowMainDataBasicAnalysisId=mainDataBasicAnalysisDao.getMainDataBasicAnalysisMaxId()+1;
		}
		
		while(nowMainDataBasicAnalysisId<mainDataMaxId+1){
			MainData mainData=mainDataDao.get(nowMainDataBasicAnalysisId);
			if(mainData!=null){
				MainDataBasicAnalysis mdba=new MainDataBasicAnalysis();
				
				mdba.setId(nowMainDataBasicAnalysisId);
				mdba.setMainData(mainData);
				
				mdba.setFirstJO(AnalysisUtils.numberToJO(mainData.getFirst()));
				mdba.setFirstXD(AnalysisUtils.numberToXDByQian(mainData.getFirst()));
				mdba.setFirstZHY(AnalysisUtils.numberToZHY(mainData.getFirst()));
				mdba.setFirst012(AnalysisUtils.numberTo012(mainData.getFirst()));
				
				mdba.setSecondJO(AnalysisUtils.numberToJO(mainData.getSecond()));
				mdba.setSecondXD(AnalysisUtils.numberToXDByQian(mainData.getSecond()));
				mdba.setSecondZHY(AnalysisUtils.numberToZHY(mainData.getSecond()));
				mdba.setSecond012(AnalysisUtils.numberTo012(mainData.getSecond()));
				
				
				mdba.setThirdJO(AnalysisUtils.numberToJO(mainData.getThird()));
				mdba.setThirdXD(AnalysisUtils.numberToXDByQian(mainData.getThird()));
				mdba.setThirdZHY(AnalysisUtils.numberToZHY(mainData.getThird()));
				mdba.setThird012(AnalysisUtils.numberTo012(mainData.getThird()));
				
				
				mdba.setFourthJO(AnalysisUtils.numberToJO(mainData.getFourth()));
				mdba.setFourthXD(AnalysisUtils.numberToXDByQian(mainData.getFourth()));
				mdba.setFourthZHY(AnalysisUtils.numberToZHY(mainData.getFourth()));
				mdba.setFourth012(AnalysisUtils.numberTo012(mainData.getFourth()));
				
				mdba.setFifthJO(AnalysisUtils.numberToJO(mainData.getFifth()));
				mdba.setFifthXD(AnalysisUtils.numberToXDByQian(mainData.getFifth()));
				mdba.setFifthZHY(AnalysisUtils.numberToZHY(mainData.getFifth()));
				mdba.setFifth012(AnalysisUtils.numberTo012(mainData.getFifth()));
				
				
				mdba.setSixthJO(AnalysisUtils.numberToJO(mainData.getSixth()));
				mdba.setSixthXD(AnalysisUtils.numberToXDByHou(mainData.getSixth()));
				mdba.setSixthZHY(AnalysisUtils.numberToZHY(mainData.getSixth()));
				mdba.setSixth012(AnalysisUtils.numberTo012(mainData.getSixth()));
				
				mdba.setSeventhJO(AnalysisUtils.numberToJO(mainData.getSeventh()));
				mdba.setSeventhXD(AnalysisUtils.numberToXDByHou(mainData.getSeventh()));
				mdba.setSeventhZHY(AnalysisUtils.numberToZHY(mainData.getSeventh()));
				mdba.setSeventh012(AnalysisUtils.numberTo012(mainData.getSeventh()));
				
				mainDataBasicAnalysisDao.save(mdba);
			}
			
			
			nowMainDataBasicAnalysisId++;
		}
			 
		
		
		
	}

}
