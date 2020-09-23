package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.XDDistributionDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.XDDistribution;
import star.superhappy.service.XDDistributionService;

public class XDDistributionServiceImpl implements XDDistributionService {

	private MainDataDao mainDataDao;
	private XDDistributionDao xddistributionDao;

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	public XDDistributionDao getXddistributionDao() {
		return xddistributionDao;
	}

	public void setXddistributionDao(XDDistributionDao xddistributionDao) {
		this.xddistributionDao = xddistributionDao;
	}

	// 保存
	public void save(XDDistribution xxd) {
		xddistributionDao.save(xxd);
	}

	// 更新
	public void update(XDDistribution xxd) {
		xddistributionDao.update(xxd);
	}

	// 删除
	public void delete(int id) {
		xddistributionDao.delete(id);
	}

	// 查询，返回单个实体
	public XDDistribution get(int id) {

		return xddistributionDao.get(id);
	}

	// 查询，返回List
	public List<XDDistribution> query() {

		return xddistributionDao.query();
	}

	// 自定义，初始化，批量更新
	public void batchUpdate() {
		// 1.为空，初始化数据
		if (xddistributionDao.query().isEmpty()) {
			System.out.println("---初始化数据---");
			List<MainData> mdLi = mainDataDao.query();
			for (MainData md : mdLi) {
				int qq1=0;
				int qq2=0;
				int qq3=0;
				
				int hq1=0;
				int hq2=0;
				int hq3=0;
				// 初始化，数组tempA
				int[] tempA = { md.getFirst(), md.getSecond(), md.getThird(),
						md.getFourth(), md.getFifth() };
				for (int i = 0; i < tempA.length; i++) {
					if (tempA[i]<= 12) {
                       qq1++;
					}
					if (tempA[i]>12&&tempA[i]<=24) {
						 qq2++;
					}
					if (tempA[i]>24&&tempA[i]<=35) {
						qq3++;
					}

				}
				// 初始化，数组tempB
				int[] tempB = { md.getSixth(), md.getSeventh() };
				for (int i = 0; i < tempB.length; i++) {
					if (tempB[i]<=4) {
                       hq1++;
					}
					if (tempB[i]>4&&tempB[i]<=8) {
						 hq2++;
					}
					if (tempB[i]>8&&tempB[i]<=12) {
						hq3++;
					}
                    
				}
				XDDistribution xxd=new XDDistribution();
                xxd.setId(md.getId());
                xxd.setMainData(md);
                xxd.setQQ1(qq1);
                xxd.setQQ2(qq2);
                xxd.setQQ3(qq3);
                xxd.setHQ1(hq1);
                xxd.setHQ2(hq2);
                xxd.setHQ3(hq3);
                //System.out.println(xxd);
                xddistributionDao.save(xxd);
			}
		}else{
			System.out.println("---更新数据---");
			int xxdId=xddistributionDao.getMaxXXDId();
			int mainId=mainDataDao.getMaxMainDataId();
			if(xxdId<mainId){
				System.out.println("---xxd数据需要更新---");
				while(xxdId<mainId+1){
					MainData md=mainDataDao.get(xxdId+1);
					if(md!=null){
						int qq1=0;
						int qq2=0;
						int qq3=0;
						
						int hq1=0;
						int hq2=0;
						int hq3=0;
						// 初始化，数组tempA
						int[] tempA = { md.getFirst(), md.getSecond(), md.getThird(),
								md.getFourth(), md.getFifth() };
						for (int i = 0; i < tempA.length; i++) {
							if (tempA[i]<= 12) {
		                       qq1++;
							}
							if (tempA[i]>12&&tempA[i]<=24) {
								 qq2++;
							}
							if (tempA[i]>24&&tempA[i]<=35) {
								qq3++;
							}

						}
						// 初始化，数组tempB
						int[] tempB = { md.getSixth(), md.getSeventh() };
						for (int i = 0; i < tempB.length; i++) {
							if (tempB[i]<=4) {
		                       hq1++;
							}
							if (tempB[i]>4&&tempB[i]<=8) {
								 hq2++;
							}
							if (tempB[i]>8&&tempB[i]<=12) {
								hq3++;
							}
		                    
						}
						XDDistribution xxd=new XDDistribution();
		                xxd.setId(md.getId());
		                xxd.setMainData(md);
		                xxd.setQQ1(qq1);
		                xxd.setQQ2(qq2);
		                xxd.setQQ3(qq3);
		                xxd.setHQ1(hq1);
		                xxd.setHQ2(hq2);
		                xxd.setHQ3(hq3);
		                //System.out.println(xxd);
		                xddistributionDao.save(xxd);
					}
					xxdId++;
				}
				
				
				
			}else if(xxdId==mainId){
				System.out.println("---xxd数据已是最新---");
			}
		}

		// xxid<mainDataId，更新

	}
}
