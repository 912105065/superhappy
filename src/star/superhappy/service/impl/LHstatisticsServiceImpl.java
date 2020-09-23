package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.LHstatisticsDao;
import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.LHstatistics;
import star.superhappy.pojo.MainData;
import star.superhappy.service.LHstatisticsService;

public class LHstatisticsServiceImpl implements LHstatisticsService {

	
	private LHstatisticsDao lhstatisticsDao;
	
	private MainDataDao mainDataDao;  
	
	
	
	public LHstatisticsDao getLhstatisticsDao() {
		return lhstatisticsDao;
	}


	public void setLhstatisticsDao(LHstatisticsDao lhstatisticsDao) {
		this.lhstatisticsDao = lhstatisticsDao;
	}
	
	
	


	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}


	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}


	//保存
	@Override
	public void save(LHstatistics lhstatistics) {
		lhstatisticsDao.save(lhstatistics);
	}

	
	//更新
	@Override
	public void update(LHstatistics lhstatistics) {
		lhstatisticsDao.update(lhstatistics);
	}

	
	
	//删除
	@Override
	public void delete(int id) {
		lhstatisticsDao.delete(id);
	}

	
	//根据id号查询
	@Override
	public LHstatistics get(int id) {
		return lhstatisticsDao.get(id);
	}

	
	//查询返回list
	@Override
	public List<LHstatistics> query() {
		return lhstatisticsDao.query();
	}

	//自定义，批量初始化和更新连号数据
	@Override
	public void bathInitAndUpdate() {
		//查询连号数据，如果为空则初始化，不为空则更新
		if(lhstatisticsDao.query().size()==0){
			System.out.println("---初始化连号统计数据---");
			List<MainData> mdLi=mainDataDao.query();
			for(int i=0;i<mdLi.size();i++){
				MainData md=mdLi.get(i);
				//System.out.println(md);
				//前区连号统计
				int[] tempA = { md.getFirst(), md.getSecond(), md.getThird(),md.getFourth(), md.getFifth() };
				String[] lhStr= {"",""};//lhStr数组记录前区连号信息
				int[] lhCount=new int[2];//lhCount数组记录前区连号组的个数
				int n = 0;//n代表前区号码位数，0-第一位，1-第二位，2-第三位，3-第四位，4-第五位
				// 递归
				while (n < 5) {

					if (n == 0) {
						StringBuffer strA = new StringBuffer();//strA前区连号信息
						int count=1;
						if (tempA[0] + 1 == tempA[1]) {
							strA.append(String.valueOf(tempA[0]) + ","
									+ String.valueOf(tempA[1]));
							n=1;
							if (tempA[1] + 1 == tempA[2]) {
								strA.append("," + String.valueOf(tempA[2]));
								n=2;
								
								if (tempA[2] + 1 == tempA[3]) {
									strA.append("," + String.valueOf(tempA[3]));
									n=3;
									
									if (tempA[3] + 1 == tempA[4]) {
										strA.append("," + String.valueOf(tempA[4]));
										n=4;
										
									}

								}

							}
							//System.out.println(str);
							if(strA!=null&&(!strA.equals(""))){
								if(lhStr[0].equals("")){
									lhStr[0]=strA.toString();
									
								}else{
									lhStr[1]=strA.toString();
									
								}
								
							}
						}
					}
					if (n == 1) {

						StringBuffer strA = new StringBuffer();
						if (tempA[1] + 1 == tempA[2]) {
							strA.append(String.valueOf(tempA[1]) + ","
									+ String.valueOf(tempA[2]));
							n=2;
							if (tempA[2] + 1 == tempA[3]) {
								strA.append("," + String.valueOf(tempA[3]));
								n=3;
								if (tempA[3] + 1 == tempA[4]) {
									strA.append("," + String.valueOf(tempA[4]));
									n=4;
								}

							}
							//System.out.println(str);
							if(strA!=null&&(!strA.equals(""))){
								if(lhStr[0].equals("")){
									lhStr[0]=strA.toString();
									
								}else{
									lhStr[1]=strA.toString();
									
								}
							}
						}
						
					}
					if (n== 2) {
						StringBuffer strA = new StringBuffer();
						if (tempA[2] + 1 == tempA[3]) {
							strA.append(String.valueOf(tempA[2]) + ","
									+ String.valueOf(tempA[3]));
							n=3;
							if (tempA[3] + 1 == tempA[4]) {
								strA.append("," + String.valueOf(tempA[4]));
		                        n=4;
							}
							//System.out.println(str);
							if(strA!=null&&(!strA.equals(""))){
								if(lhStr[0].equals("")){
									lhStr[0]=strA.toString();
									
								}else{
									lhStr[1]=strA.toString();
									
								}
							}
						}
					}
					if (n == 3) {
						StringBuffer strA = new StringBuffer();
						if (tempA[3] + 1 == tempA[4]) {
							strA.append(String.valueOf(tempA[3]) + ","
									+ String.valueOf(tempA[4]));
							n=4;
							//System.out.println(str);
							if(strA!=null&&(!strA.equals(""))){
								if(lhStr[0].equals("")){
									lhStr[0]=strA.toString();
					
								}else{
									lhStr[1]=strA.toString();
									
								}
							}
						}
					}
					n++;
				}
		         
			
				
				
				
					//System.out.println(md.getId()+"-"+lhStr[0]+"-"+lhStr[1]);
					//System.out.println(md.getId());
					//System.out.println("前区连号1："+lhStr[0]);
					//System.out.println("前区连号2："+lhStr[1]);
				    //System.out.println("----------------------------");
		
				
				//后区连号统计
				    StringBuffer strB=new StringBuffer();
				    if(md.getSeventh()-md.getSixth()==1){
				    	strB.append(String.valueOf(md.getSixth())+","+String.valueOf(md.getSeventh()));
				    }
				    LHstatistics lhs=new LHstatistics();
				    lhs.setId(md.getId());
				    lhs.setMainData(md);
				    lhs.setQqlh1(lhStr[0]);
				    lhs.setQqlh1count(getCount(lhStr[0]));//获取前区每组连号的个数
				    lhs.setQqlh2(lhStr[1]);
				    lhs.setQqlh2count(getCount(lhStr[1]));//获取前区每组连号的个数
				    lhs.setHqlh(strB.toString());
				    
				    System.out.println(lhs);
				    lhstatisticsDao.save(lhs);
			}
		}else{
			//System.out.println("---更新连号统计数据---");
			//System.out.println("当前LHstatistics的最大ID为："+lhstatisticsDao.getLHstatisticsMaxId());
			//System.out.println("当前mainData的最大ID为："+mainDataDao.getMaxMainDataId());
			int maxLHId=lhstatisticsDao.getLHstatisticsMaxId();
			int maxMDId=mainDataDao.getMaxMainDataId();
			if(maxLHId<maxMDId+1){
				System.out.println("---更新连号统计数据---");
				while(maxLHId<maxMDId+1){
					MainData md=mainDataDao.get(maxLHId+1);
					//System.out.println(md);
					if(md!=null){
						//前区连号统计
						int[] tempA = { md.getFirst(), md.getSecond(), md.getThird(),md.getFourth(), md.getFifth() };
						String[] lhStr= {"",""};//lhStr数组记录前区连号信息
						int[] lhCount=new int[2];//lhCount数组记录前区连号组的个数
						int n = 0;//n代表前区号码位数，0-第一位，1-第二位，2-第三位，3-第四位，4-第五位
						// 递归
						while (n < 5) {

							if (n == 0) {
								StringBuffer strA = new StringBuffer();//strA前区连号信息
								int count=1;
								if (tempA[0] + 1 == tempA[1]) {
									strA.append(String.valueOf(tempA[0]) + ","
											+ String.valueOf(tempA[1]));
									n=1;
									if (tempA[1] + 1 == tempA[2]) {
										strA.append("," + String.valueOf(tempA[2]));
										n=2;
										
										if (tempA[2] + 1 == tempA[3]) {
											strA.append("," + String.valueOf(tempA[3]));
											n=3;
											
											if (tempA[3] + 1 == tempA[4]) {
												strA.append("," + String.valueOf(tempA[4]));
												n=4;
												
											}

										}

									}
									//System.out.println(str);
									if(strA!=null&&(!strA.equals(""))){
										if(lhStr[0].equals("")){
											lhStr[0]=strA.toString();
											
										}else{
											lhStr[1]=strA.toString();
											
										}
										
									}
								}
							}
							if (n == 1) {

								StringBuffer strA = new StringBuffer();
								if (tempA[1] + 1 == tempA[2]) {
									strA.append(String.valueOf(tempA[1]) + ","
											+ String.valueOf(tempA[2]));
									n=2;
									if (tempA[2] + 1 == tempA[3]) {
										strA.append("," + String.valueOf(tempA[3]));
										n=3;
										if (tempA[3] + 1 == tempA[4]) {
											strA.append("," + String.valueOf(tempA[4]));
											n=4;
										}

									}
									//System.out.println(str);
									if(strA!=null&&(!strA.equals(""))){
										if(lhStr[0].equals("")){
											lhStr[0]=strA.toString();
											
										}else{
											lhStr[1]=strA.toString();
											
										}
									}
								}
								
							}
							if (n== 2) {
								StringBuffer strA = new StringBuffer();
								if (tempA[2] + 1 == tempA[3]) {
									strA.append(String.valueOf(tempA[2]) + ","
											+ String.valueOf(tempA[3]));
									n=3;
									if (tempA[3] + 1 == tempA[4]) {
										strA.append("," + String.valueOf(tempA[4]));
				                        n=4;
									}
									//System.out.println(str);
									if(strA!=null&&(!strA.equals(""))){
										if(lhStr[0].equals("")){
											lhStr[0]=strA.toString();
											
										}else{
											lhStr[1]=strA.toString();
											
										}
									}
								}
							}
							if (n == 3) {
								StringBuffer strA = new StringBuffer();
								if (tempA[3] + 1 == tempA[4]) {
									strA.append(String.valueOf(tempA[3]) + ","
											+ String.valueOf(tempA[4]));
									n=4;
									//System.out.println(str);
									if(strA!=null&&(!strA.equals(""))){
										if(lhStr[0].equals("")){
											lhStr[0]=strA.toString();
							
										}else{
											lhStr[1]=strA.toString();
											
										}
									}
								}
							}
							n++;
						}
				         
					
						
						
						
							//System.out.println(md.getId()+"-"+lhStr[0]+"-"+lhStr[1]);
							//System.out.println(md.getId());
							//System.out.println("前区连号1："+lhStr[0]);
							//System.out.println("前区连号2："+lhStr[1]);
						    //System.out.println("----------------------------");
				
						
						//后区连号统计
						    StringBuffer strB=new StringBuffer();
						    if(md.getSeventh()-md.getSixth()==1){
						    	strB.append(String.valueOf(md.getSixth())+","+String.valueOf(md.getSeventh()));
						    }
						    LHstatistics lhs=new LHstatistics();
						    lhs.setId(md.getId());
						    lhs.setMainData(md);
						    lhs.setQqlh1(lhStr[0]);
						    lhs.setQqlh1count(getCount(lhStr[0]));//获取前区每组连号的个数
						    lhs.setQqlh2(lhStr[1]);
						    lhs.setQqlh2count(getCount(lhStr[1]));//获取前区每组连号的个数
						    lhs.setHqlh(strB.toString());
						    
						    System.out.println(lhs);
						    lhstatisticsDao.save(lhs);
						
						
						
					
				}else{
				System.out.println("---连号统计数据已最新---");
			}
					maxLHId++;
		}
			}
		}
	}
		


	//获取前区每组连号的个数
	public int getCount(String str){
		String[] array=str.split(",");
		int length=array.length;
		if(length==1){
			return 0;
		}else{
			return length;
		}
	}
}
