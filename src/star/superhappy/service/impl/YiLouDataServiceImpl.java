package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.YiLouDataDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.YiLouData;
import star.superhappy.service.YiLouDataService;
import star.superhappy.utils.SHUtils;

public class YiLouDataServiceImpl implements YiLouDataService {

	private MainDataDao mainDataDao;
	private YiLouDataDao yiLouDataDao;

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
	
	
	
	// 保存
	@Override
	public void save(YiLouData yiLouData) {
		yiLouDataDao.save(yiLouData);
	}

	
	// 更新
	@Override
	public void update(YiLouData yiLouData) {
		yiLouDataDao.update(yiLouData);
	}

	
	
	// 删除
	@Override
	public void delete(int id) {
		yiLouDataDao.delete(id);
	}

	
	// 查询，返回单个实体
	@Override
	public YiLouData get(int id) {
		// TODO Auto-generated method stub
		return yiLouDataDao.get(id);
	}

	
	// 查询，返回List
	@Override
	public List<YiLouData> query() {
		// TODO Auto-generated method stub
		return yiLouDataDao.query();
	}

	
	// 自定义，读取当前mainData的最大期号id
	@Override
	public int getMaxYiLouDataId() {
		// TODO Auto-generated method stub
		return yiLouDataDao.getMaxYiLouDataId();
	}
	
	
	
	
	
    //以下service层独立方法
	// 初始化第一期数据的遗漏值，2007001
	@Override
	public void yiLouDataInit() {
		// 1.1获取第一期数据，2007001
		// 1.2对临时temp数组，复制第一期数据2007001
		MainData mainData = mainDataDao.get(2007001);
		int[] temp1 = new int[5];// 存储前区数据
		temp1[0] = mainData.getFirst();
		temp1[1] = mainData.getSecond();
		temp1[2] = mainData.getThird();
		temp1[3] = mainData.getFourth();
		temp1[4] = mainData.getFifth();
		int[] temp2 = new int[2];// 存储后区数据
		temp2[0] = mainData.getSixth();
		temp2[1] = mainData.getSeventh();

		// 2.初始化辅助二维数组，前区，后区遗漏配置初始遗漏信息
		// 存储前区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；
		//a[i][2] flag  0-显示遗漏值,1-显示号码，2-最大遗漏值，下一期就是开奖号码，3-重号
		int[][] a = new int[35][3];
		for (int i = 0; i < 35; i++) {
			a[i][0] = i + 1;//号码1,2,3,4,5,6,7...35
			a[i][1] = 1;//遗漏值，默认为1
			a[i][2] = 0;//flag，0显示遗漏值
			
		}
		// 存储后区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；a[i][2] flag  0-显示遗漏值,1-显示号码，2-最大遗漏值，下一期就是开奖号码，3-重号
		int[][] b = new int[12][3];
		for (int j = 0; j < 12; j++) {
			b[j][0] = j + 1;//号码1,2,3,4,5,6,7...12
			b[j][1] = 1;//遗漏值，默认为1
			b[j][2] = 0;//flag，0显示遗漏值
		}

		// 3.更新二维数组的配置信息
		// 3.1前区，替换a[][]中的数值
	    for(int i=0;i<5;i++){//当期开奖号码的遗漏值处理
	    	a[temp1[i]-1][1]=0;//开奖号码，0显示
	    	a[temp1[i]-1][2]=1;//flag，修改为1，代表中奖的号码
	    }
		
		
	
		// 3.2后区，替换b[][]中的数值
	    for(int i=0;i<2;i++){//当期开奖号码的遗漏值处理
	    	b[temp2[i]-1][1]=0;//开奖号码，0显示
	    	b[temp2[i]-1][2]=1;//flag，修改为1，代表中奖的号码
	    }
	
	
		
		//输出查看
		for(int i=0;i<35;i++){
			System.out.println(a[i][0]+"-"+a[i][1]+"-"+a[i][2]);
		}
		for(int i=0;i<12;i++){
			System.out.println(b[i][0]+"-"+b[i][1]+"-"+b[i][2]);
		}
		//4.保存数据
		//4.1实体类赋值
		YiLouData yiLouData=new YiLouData();
		yiLouData.setId(mainData.getId());
		yiLouData.setMainData(mainData);
		
		//将a[][]和b[][]赋值到yiLouData
        saveYiLouData(yiLouData,a,b);	
		
		
		yiLouDataDao.save(yiLouData);
		

	}

	
	 //保存1期遗漏数据
		@Override
		public void saveDataByOne() {
			//1.读取2008001数据（2007093的下一期）
			MainData maindata=mainDataDao.get(2008001);//maindata本期数据
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
			//3.遗漏值赋值，计算
			if(id2.equals("001")){//期号是001期的处理
				//3.1查询上一期的遗漏值
				//查询上一年的最大期号的遗漏值
				//上一年的最大期号，2008001的上一期
				int preMainId=mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(id1)-1);
				//上一年最大期号的遗漏值
				YiLouData preData=yiLouDataDao.get(preMainId);
				//对a[][]和b[][]赋值
				int[][] a=SHUtils.copyToArrayA(preData);
				int[][] b=SHUtils.copyToArrayB(preData);
				
				
				//对上一期数据更新
				// 存储前区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；
				//a[i][2]flag，0-显示遗漏值,1-显示号码，2-最大遗漏值，下一期就是开奖号码，3-重号
				int[][] preA=new int[35][3];//preA[][]上一期遗漏数据-前区，备份数据copyToArrayA(preData)
				int[][] preB=new int[12][3];//preB[][]上一期遗漏数据-后区，备份数据copyToArrayB(preData)
				
				//preA赋值
				for(int i=0;i<35;i++){
					preA[i][0]=a[i][0];
					preA[i][1]=a[i][1];
					preA[i][2]=a[i][2];
				}
				
				//preB赋值
				for(int i=0;i<12;i++){
					preB[i][0]=b[i][0];
					preB[i][1]=b[i][1];
					preB[i][2]=b[i][2];
				}
				
				//对上一期数据更新
				//前区
				for(int i=0;i<5;i++){
					if(preA[temp1[i]-1][2]==0){//flag-0，表示为遗漏值
						preA[temp1[i]-1][2]=2;//flag置为2，最大遗漏值
					}
					
				}
				//后区
				for(int i=0;i<2;i++){
					if(preB[temp2[i]-1][2]==0){//flag-0，表示为遗漏值
						preB[temp2[i]-1][2]=2;//flag置为2，最大遗漏值
					}
					
				}
				//System.out.println("修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
				//更新上一期的遗漏值
				//System.out.println("更新后的上一期遗漏数据："+saveYiLouData(preData, preA, preB));
				yiLouDataDao.update(saveYiLouData(preData, preA, preB));
				//System.out.println("2修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
				
			    //a[][]的a[i][1]加1和a[i][2]设置为0
				for(int i=0;i<35;i++){
					a[i][1]++;
					a[i][2]=0;
				}
				
				//b[][]的b[i][1]加1和b[i][2]设置为0
				for(int i=0;i<12;i++){
					b[i][1]++;
					b[i][2]=0;
				}
				
				
				//根据当期号码，修改a[][]和b[][]
				// 前区，替换a[][]中的数值
				//System.out.println("循环外上一期号码："+preA[14][0]+"上一期flag："+preA[14][2]);
			    for(int i=0;i<5;i++){//当期开奖号码的遗漏值处理
			    	a[temp1[i]-1][1]=0;//显示号码
			    	
			    	//System.out.println("上一期号码："+preA[temp1[i]-1][0]+"上一期flag："+preA[temp1[i]-1][2]);
			    	if(preA[temp1[i]-1][2]==0){//flag-遗漏值
			    		a[temp1[i]-1][2]=1;//flag-1，显示号码
			    	}
			    	if(preA[temp1[i]-1][2]==1){//flag-显示号码
			    		a[temp1[i]-1][2]=3;//flag-1，重号
			    	}
			    	if(preA[temp1[i]-1][2]==2){//flag-遗漏值max值
			    		a[temp1[i]-1][2]=1;//flag-1，显示号码
			    	}
			    	if(preA[temp1[i]-1][2]==3){//flag-重号
			    		a[temp1[i]-1][2]=3;////flag-1，重号
			    	}
			    	
			
			    }
				
				
			
				// 后区，替换b[][]中的数值
			    for(int i=0;i<2;i++){//当期开奖号码的遗漏值处理
			    	b[temp2[i]-1][1]=0;//显示号码
			    	
			    	if(preB[temp2[i]-1][2]==0){//flag-遗漏值
			    		b[temp2[i]-1][2]=1;
			    	}
			    	if(preB[temp2[i]-1][2]==1){//flag-显示号码
			    		b[temp2[i]-1][2]=3;
			    	}
			    	if(preB[temp2[i]-1][2]==2){//flag-遗漏值max值
			    		b[temp2[i]-1][2]=1;
			    	}
			    	if(preB[temp2[i]-1][2]==3){//flag-重号
			    		b[temp2[i]-1][2]=3;
			    	}
			    	
			    }
			    
			
			   
			    //4.保存遗漏值
			    YiLouData yiLouData=new YiLouData();
				yiLouData.setId(maindata.getId());
				yiLouData.setMainData(maindata);
				
				//将a[][]和b[][]赋值到yiLouData
		        saveYiLouData(yiLouData,a,b);	
				
				
				yiLouDataDao.save(yiLouData);
				
			}else{//期号非001期的处理
				//3.1查询上一期的遗漏值
				YiLouData preData=yiLouDataDao.get(maindata.getId()-1);
				//System.out.println(preData);
				//对a[][]和b[][]赋值
				int[][] a=SHUtils.copyToArrayA(preData);//a[][]上一期遗漏数据-前区
				int[][] b=SHUtils.copyToArrayB(preData);//b[][]上一期遗漏数据-后区
				
				
				//对上一期数据更新
				// 存储前区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；
				//a[i][2]flag，0-显示遗漏值,1-显示号码，2-最大遗漏值，下一期就是开奖号码，3-重号
				int[][] preA=new int[35][3];//preA[][]上一期遗漏数据-前区，备份数据copyToArrayA(preData)
				int[][] preB=new int[12][3];//preB[][]上一期遗漏数据-后区，备份数据copyToArrayB(preData)
				
				//preA赋值
				for(int i=0;i<35;i++){
					preA[i][0]=a[i][0];
					preA[i][1]=a[i][1];
					preA[i][2]=a[i][2];
				}
				
				//preB赋值
				for(int i=0;i<12;i++){
					preB[i][0]=b[i][0];
					preB[i][1]=b[i][1];
					preB[i][2]=b[i][2];
				}
				
				//对上一期数据更新
				//前区
				for(int i=0;i<5;i++){
					if(preA[temp1[i]-1][2]==0){//flag-0，表示为遗漏值
						preA[temp1[i]-1][2]=2;//flag置为2，最大遗漏值
					}
					
				}
				//后区
				for(int i=0;i<2;i++){
					if(preB[temp2[i]-1][2]==0){//flag-0，表示为遗漏值
						preB[temp2[i]-1][2]=2;//flag置为2，最大遗漏值
					}
					
				}
				//System.out.println("修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
				//更新上一期的遗漏值
				//System.out.println("更新后的上一期遗漏数据："+saveYiLouData(preData, preA, preB));
				yiLouDataDao.update(saveYiLouData(preData, preA, preB));
				//System.out.println("2修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
				
			    //a[][]的a[i][1]加1和a[i][2]设置为0
				for(int i=0;i<35;i++){
					a[i][1]++;
					a[i][2]=0;
				}
				
				//b[][]的b[i][1]加1和b[i][2]设置为0
				for(int i=0;i<12;i++){
					b[i][1]++;
					b[i][2]=0;
				}
				
				
				//根据当期号码，修改a[][]和b[][]
				// 前区，替换a[][]中的数值
				//System.out.println("循环外上一期号码："+preA[14][0]+"上一期flag："+preA[14][2]);
			    for(int i=0;i<5;i++){//当期开奖号码的遗漏值处理
			    	a[temp1[i]-1][1]=0;//显示号码
			    	
			    	//System.out.println("上一期号码："+preA[temp1[i]-1][0]+"上一期flag："+preA[temp1[i]-1][2]);
			    	if(preA[temp1[i]-1][2]==0){//flag-遗漏值
			    		a[temp1[i]-1][2]=1;//flag-1，显示号码
			    	}
			    	if(preA[temp1[i]-1][2]==1){//flag-显示号码
			    		a[temp1[i]-1][2]=3;//flag-1，重号
			    	}
			    	if(preA[temp1[i]-1][2]==2){//flag-遗漏值max值
			    		a[temp1[i]-1][2]=1;//flag-1，显示号码
			    	}
			    	if(preA[temp1[i]-1][2]==3){//flag-重号
			    		a[temp1[i]-1][2]=3;////flag-1，重号
			    	}
			    	
			
			    }
				
				
			
				// 后区，替换b[][]中的数值
			    for(int i=0;i<2;i++){//当期开奖号码的遗漏值处理
			    	b[temp2[i]-1][1]=0;//显示号码
			    	
			    	if(preB[temp2[i]-1][2]==0){//flag-遗漏值
			    		b[temp2[i]-1][2]=1;
			    	}
			    	if(preB[temp2[i]-1][2]==1){//flag-显示号码
			    		b[temp2[i]-1][2]=3;
			    	}
			    	if(preB[temp2[i]-1][2]==2){//flag-遗漏值max值
			    		b[temp2[i]-1][2]=1;
			    	}
			    	if(preB[temp2[i]-1][2]==3){//flag-重号
			    		b[temp2[i]-1][2]=3;
			    	}
			    	
			    }
			    
			
			   
			    //4.保存遗漏值
			    YiLouData yiLouData=new YiLouData();
				yiLouData.setId(maindata.getId());
				yiLouData.setMainData(maindata);
				
				//将a[][]和b[][]赋值到yiLouData
		        saveYiLouData(yiLouData,a,b);	
				
				
				yiLouDataDao.save(yiLouData);
			}
		}
		
		
		
		
		//批量保存遗漏数据
		@Override
		public void batchSaveData() {
			//1.判断，是否有更新数据，遗漏表id小于mainData的id
			//读取遗漏表最大的id
			int mainDataId=mainDataDao.getMaxMainDataId();
			//读取maindata表最大id
			int yiLouDataIdNow=yiLouDataDao.getMaxYiLouDataId();
			//System.out.println("mainData的最大Id："+mainDataId+"-遗漏表最大的Id："+yiLouDataIdNow);
			
			
			//3.遗漏值赋值，计算
			//期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
			String year=String.valueOf(yiLouDataIdNow);//year当期的遗漏值id
			String  year4=year.substring(0,4);//截取2007
			int yiLouDataId=yiLouDataIdNow;//yiLouDataId下一期的遗漏id
			if(yiLouDataIdNow==mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(year4))){//当期的遗漏值id=根据当年的最大mainDataId
				yiLouDataId=(Integer.parseInt(year4)+1)*1000+1;//2008154最后一期，下一期不是2008155，而是2009001
				//System.out.println("修正为下一年yiLouId："+yiLouDataId);
			}else{
				yiLouDataId=yiLouDataId+1;
				//System.out.println("修正为当年yiLouId："+yiLouDataId);
			}
			
			if(mainDataId>(yiLouDataId-1)){
				System.out.println("遗漏数据需要更新");
				while(mainDataId>(yiLouDataId-1)){//需要更新遗漏数据
					//1.读取mainData，当前期号的下一期,遗漏表中未更新的数据
					MainData maindata=mainDataDao.get(yiLouDataId);
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
					//3.遗漏值赋值，计算
					if(id2.equals("001")){//期号是001期的处理
						//获得上一年的最大期号
						int preMainId=mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(id1)-1);
						//上一年最大期号的遗漏值
						YiLouData preData=yiLouDataDao.get(preMainId);
						//对a[][]和b[][]赋值
						int[][] a=SHUtils.copyToArrayA(preData);
						int[][] b=SHUtils.copyToArrayB(preData);
						
				
						
						//对上一期数据更新
						// 存储前区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；
						//a[i][2]flag，0-显示遗漏值,1-显示号码，3-最大遗漏值，下一期就是开奖号码，4-重号
						int[][] preA=new int[35][3];//preA[][]上一期遗漏数据-前区，备份数据copyToArrayA(preData)
						int[][] preB=new int[12][3];//preB[][]上一期遗漏数据-后区，备份数据copyToArrayB(preData)
						
						//preA赋值
						for(int i=0;i<35;i++){
							preA[i][0]=a[i][0];
							preA[i][1]=a[i][1];
							preA[i][2]=a[i][2];
						}
						
						//preB赋值
						for(int i=0;i<12;i++){
							preB[i][0]=b[i][0];
							preB[i][1]=b[i][1];
							preB[i][2]=b[i][2];
						}
						
						//对上一期数据更新
						//前区
						for(int i=0;i<5;i++){
							if(preA[temp1[i]-1][2]==0){//flag-0，表示为遗漏值
								preA[temp1[i]-1][2]=2;//flag置为2，最大遗漏值
							}
							
						}
						//后区
						for(int i=0;i<2;i++){
							if(preB[temp2[i]-1][2]==0){//flag-0，表示为遗漏值
								preB[temp2[i]-1][2]=2;//flag置为2，最大遗漏值
							}
							
						}
						//System.out.println("修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
						//更新上一期的遗漏值
						//System.out.println("更新后的上一期遗漏数据："+saveYiLouData(preData, preA, preB));
						yiLouDataDao.update(saveYiLouData(preData, preA, preB));
						//System.out.println("2修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
						
					    //a[][]的a[i][1]加1和a[i][2]设置为0
						for(int i=0;i<35;i++){
							a[i][1]++;
							a[i][2]=0;
						}
						
						//b[][]的b[i][1]加1和b[i][2]设置为0
						for(int i=0;i<12;i++){
							b[i][1]++;
							b[i][2]=0;
						}
						
						
						//根据当期号码，修改a[][]和b[][]
						// 前区，替换a[][]中的数值
						//System.out.println("循环外上一期号码："+preA[14][0]+"上一期flag："+preA[14][2]);
					    for(int i=0;i<5;i++){//当期开奖号码的遗漏值处理
					    	a[temp1[i]-1][1]=0;//显示号码
					    	
					    	//System.out.println("上一期号码："+preA[temp1[i]-1][0]+"上一期flag："+preA[temp1[i]-1][2]);
					    	if(preA[temp1[i]-1][2]==0){//flag-遗漏值
					    		a[temp1[i]-1][2]=1;//flag-1，显示号码
					    	}
					    	if(preA[temp1[i]-1][2]==1){//flag-显示号码
					    		a[temp1[i]-1][2]=3;//flag-1，重号
					    	}
					    	if(preA[temp1[i]-1][2]==2){//flag-遗漏值max值
					    		a[temp1[i]-1][2]=1;//flag-1，显示号码
					    	}
					    	if(preA[temp1[i]-1][2]==3){//flag-重号
					    		a[temp1[i]-1][2]=3;////flag-1，重号
					    	}
					    	
					
					    }
						
						
					
						// 后区，替换b[][]中的数值
					    for(int i=0;i<2;i++){//当期开奖号码的遗漏值处理
					    	b[temp2[i]-1][1]=0;//显示号码
					    	
					    	if(preB[temp2[i]-1][2]==0){//flag-遗漏值
					    		b[temp2[i]-1][2]=1;
					    	}
					    	if(preB[temp2[i]-1][2]==1){//flag-显示号码
					    		b[temp2[i]-1][2]=3;
					    	}
					    	if(preB[temp2[i]-1][2]==2){//flag-遗漏值max值
					    		b[temp2[i]-1][2]=1;
					    	}
					    	if(preB[temp2[i]-1][2]==3){//flag-重号
					    		b[temp2[i]-1][2]=3;
					    	}
					    	
					    }
					    
					
					   
					    //4.保存遗漏值
					    YiLouData yiLouData=new YiLouData();
						yiLouData.setId(maindata.getId());
						yiLouData.setMainData(maindata);
						
						//将a[][]和b[][]赋值到yiLouData
				        saveYiLouData(yiLouData,a,b);	
						
						
						yiLouDataDao.save(yiLouData);
						
						
						
						yiLouDataId++;
					}else{//期号非001期的处理
						//3.1查询上一期的遗漏值
						YiLouData preData=yiLouDataDao.get(yiLouDataId-1);
						//System.out.println(preData);
						//对a[][]和b[][]赋值
						//对a[][]和b[][]赋值
						int[][] a=SHUtils.copyToArrayA(preData);
						int[][] b=SHUtils.copyToArrayB(preData);
						
						
						
						
						//对上一期数据更新
						// 存储前区遗漏值，a[i][0]号码；a[i][1]遗漏值，0代表当期开奖号码与号码值一致，默认设置为1；
						//a[i][2]flag，0-显示遗漏值,1-显示号码，3-最大遗漏值，下一期就是开奖号码，4-重号
						int[][] preA=new int[35][3];//preA[][]上一期遗漏数据-前区，备份数据copyToArrayA(preData)
						int[][] preB=new int[12][3];//preB[][]上一期遗漏数据-后区，备份数据copyToArrayB(preData)
						
						//preA赋值
						for(int i=0;i<35;i++){
							preA[i][0]=a[i][0];
							preA[i][1]=a[i][1];
							preA[i][2]=a[i][2];
						}
						
						//preB赋值
						for(int i=0;i<12;i++){
							preB[i][0]=b[i][0];
							preB[i][1]=b[i][1];
							preB[i][2]=b[i][2];
						}
						
						//对上一期数据更新
						//前区
						for(int i=0;i<5;i++){
							if(preA[temp1[i]-1][2]==0){//flag-0，表示为遗漏值
								preA[temp1[i]-1][2]=2;//flag置为2，最大遗漏值
							}
							
						}
						//后区
						for(int i=0;i<2;i++){
							if(preB[temp2[i]-1][2]==0){//flag-0，表示为遗漏值
								preB[temp2[i]-1][2]=2;//flag置为2，最大遗漏值
							}
							
						}
						//System.out.println("修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
						//更新上一期的遗漏值
						//System.out.println("更新后的上一期遗漏数据："+saveYiLouData(preData, preA, preB));
						yiLouDataDao.update(saveYiLouData(preData, preA, preB));
						//System.out.println("2修正后的前区号码："+preA[14][0]+",flag:"+preA[14][2]);
						
					    //a[][]的a[i][1]加1和a[i][2]设置为0
						for(int i=0;i<35;i++){
							a[i][1]++;
							a[i][2]=0;
						}
						
						//b[][]的b[i][1]加1和b[i][2]设置为0
						for(int i=0;i<12;i++){
							b[i][1]++;
							b[i][2]=0;
						}
						
						
						//根据当期号码，修改a[][]和b[][]
						// 前区，替换a[][]中的数值
						//System.out.println("循环外上一期号码："+preA[14][0]+"上一期flag："+preA[14][2]);
					    for(int i=0;i<5;i++){//当期开奖号码的遗漏值处理
					    	a[temp1[i]-1][1]=0;//显示号码
					    	
					    	//System.out.println("上一期号码："+preA[temp1[i]-1][0]+"上一期flag："+preA[temp1[i]-1][2]);
					    	if(preA[temp1[i]-1][2]==0){//flag-遗漏值
					    		a[temp1[i]-1][2]=1;//flag-1，显示号码
					    	}
					    	if(preA[temp1[i]-1][2]==1){//flag-显示号码
					    		a[temp1[i]-1][2]=3;//flag-1，重号
					    	}
					    	if(preA[temp1[i]-1][2]==2){//flag-遗漏值max值
					    		a[temp1[i]-1][2]=1;//flag-1，显示号码
					    	}
					    	if(preA[temp1[i]-1][2]==3){//flag-重号
					    		a[temp1[i]-1][2]=3;////flag-1，重号
					    	}
					    	
					
					    }
						
						
					
						// 后区，替换b[][]中的数值
					    for(int i=0;i<2;i++){//当期开奖号码的遗漏值处理
					    	b[temp2[i]-1][1]=0;//显示号码
					    	
					    	if(preB[temp2[i]-1][2]==0){//flag-遗漏值
					    		b[temp2[i]-1][2]=1;
					    	}
					    	if(preB[temp2[i]-1][2]==1){//flag-显示号码
					    		b[temp2[i]-1][2]=3;
					    	}
					    	if(preB[temp2[i]-1][2]==2){//flag-遗漏值max值
					    		b[temp2[i]-1][2]=1;
					    	}
					    	if(preB[temp2[i]-1][2]==3){//flag-重号
					    		b[temp2[i]-1][2]=3;
					    	}
					    	
					    }
					    
					
					   
					    //4.保存遗漏值
					    YiLouData yiLouData=new YiLouData();
						yiLouData.setId(maindata.getId());
						yiLouData.setMainData(maindata);
						
						//将a[][]和b[][]赋值到yiLouData
				        saveYiLouData(yiLouData,a,b);	
						
						
						yiLouDataDao.save(yiLouData);
						
						
						
						
						//判断yiLouDataId
						//期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
						String tempId=String.valueOf(yiLouDataId);
						String tempId1=id.substring(0,4);//截取2007
						
						if(yiLouDataId==mainDataDao.getMaxMainDataIdByYear(Integer.parseInt(tempId1))){
							yiLouDataId=(Integer.parseInt(tempId1)+1)*1000+1;
							//System.out.println("修正为下一年yiLouId："+yiLouDataId);
						}else{
							yiLouDataId++;
							//System.out.println("修正为当年yiLouId："+yiLouDataId);
						}
					}
				}
				
				
			}else{
				System.out.println("遗漏数据已经是最新！");
			}
			
		}
	
		
		
    //以下YiLouServiceImpl类辅助方法

	
	
	//保存遗漏数据，参数赋值
	public YiLouData saveYiLouData(YiLouData yiLouData,int a[][],int b[][]){
		yiLouData.setQqNo1(a[0][1]);
		yiLouData.setQqNo1flag(a[0][2]);
		yiLouData.setQqNo2(a[1][1]);
		yiLouData.setQqNo2flag(a[1][2]);
		yiLouData.setQqNo3(a[2][1]);
		yiLouData.setQqNo3flag(a[2][2]);
		yiLouData.setQqNo4(a[3][1]);
		yiLouData.setQqNo4flag(a[3][2]);
		yiLouData.setQqNo5(a[4][1]);
		yiLouData.setQqNo5flag(a[4][2]);
		yiLouData.setQqNo6(a[5][1]);
		yiLouData.setQqNo6flag(a[5][2]);
		yiLouData.setQqNo7(a[6][1]);
		yiLouData.setQqNo7flag(a[6][2]);
		yiLouData.setQqNo8(a[7][1]);
		yiLouData.setQqNo8flag(a[7][2]);
		yiLouData.setQqNo9(a[8][1]);
		yiLouData.setQqNo9flag(a[8][2]);
		yiLouData.setQqNo10(a[9][1]);
		yiLouData.setQqNo10flag(a[9][2]);
		yiLouData.setQqNo11(a[10][1]);
		yiLouData.setQqNo11flag(a[10][2]);
		yiLouData.setQqNo12(a[11][1]);
		yiLouData.setQqNo12flag(a[11][2]);
		yiLouData.setQqNo13(a[12][1]);
		yiLouData.setQqNo13flag(a[12][2]);
		yiLouData.setQqNo14(a[13][1]);
		yiLouData.setQqNo14flag(a[13][2]);
		yiLouData.setQqNo15(a[14][1]);
		yiLouData.setQqNo15flag(a[14][2]);
		yiLouData.setQqNo16(a[15][1]);
		yiLouData.setQqNo16flag(a[15][2]);
		yiLouData.setQqNo17(a[16][1]);
		yiLouData.setQqNo17flag(a[16][2]);
		yiLouData.setQqNo18(a[17][1]);
		yiLouData.setQqNo18flag(a[17][2]);
		yiLouData.setQqNo19(a[18][1]);
		yiLouData.setQqNo19flag(a[18][2]);
		yiLouData.setQqNo20(a[19][1]);
		yiLouData.setQqNo20flag(a[19][2]);
		yiLouData.setQqNo21(a[20][1]);
		yiLouData.setQqNo21flag(a[20][2]);
		yiLouData.setQqNo22(a[21][1]);
		yiLouData.setQqNo22flag(a[21][2]);
		yiLouData.setQqNo23(a[22][1]);
		yiLouData.setQqNo23flag(a[22][2]);
		yiLouData.setQqNo24(a[23][1]);
		yiLouData.setQqNo24flag(a[23][2]);
		yiLouData.setQqNo25(a[24][1]);
		yiLouData.setQqNo25flag(a[24][2]);
		yiLouData.setQqNo26(a[25][1]);
		yiLouData.setQqNo26flag(a[25][2]);
		yiLouData.setQqNo27(a[26][1]);
		yiLouData.setQqNo27flag(a[26][2]);
		yiLouData.setQqNo28(a[27][1]);
		yiLouData.setQqNo28flag(a[27][2]);
		yiLouData.setQqNo29(a[28][1]);
		yiLouData.setQqNo29flag(a[28][2]);
		yiLouData.setQqNo30(a[29][1]);
		yiLouData.setQqNo30flag(a[29][2]);
		yiLouData.setQqNo31(a[30][1]);
		yiLouData.setQqNo31flag(a[30][2]);
		yiLouData.setQqNo32(a[31][1]);
		yiLouData.setQqNo32flag(a[31][2]);
		yiLouData.setQqNo33(a[32][1]);
		yiLouData.setQqNo33flag(a[32][2]);
		yiLouData.setQqNo34(a[33][1]);
		yiLouData.setQqNo34flag(a[33][2]);
		yiLouData.setQqNo35(a[34][1]);
		yiLouData.setQqNo35flag(a[34][2]);
		
		
		yiLouData.setHqNo1(b[0][1]);
		yiLouData.setHqNo1flag(b[0][2]);
		yiLouData.setHqNo2(b[1][1]);
		yiLouData.setHqNo2flag(b[1][2]);
		yiLouData.setHqNo3(b[2][1]);
		yiLouData.setHqNo3flag(b[2][2]);
		yiLouData.setHqNo4(b[3][1]);
		yiLouData.setHqNo4flag(b[3][2]);
		yiLouData.setHqNo5(b[4][1]);
		yiLouData.setHqNo5flag(b[4][2]);
		yiLouData.setHqNo6(b[5][1]);
		yiLouData.setHqNo6flag(b[5][2]);
		yiLouData.setHqNo7(b[6][1]);
		yiLouData.setHqNo7flag(b[6][2]);
		yiLouData.setHqNo8(b[7][1]);
		yiLouData.setHqNo8flag(b[7][2]);
		yiLouData.setHqNo9(b[8][1]);
		yiLouData.setHqNo9flag(b[8][2]);
		yiLouData.setHqNo10(b[9][1]);
		yiLouData.setHqNo10flag(b[9][2]);
		yiLouData.setHqNo11(b[10][1]);
		yiLouData.setHqNo11flag(b[10][2]);
		yiLouData.setHqNo12(b[11][1]);
		yiLouData.setHqNo12flag(b[11][2]);
		
		return yiLouData;
	}

	
	//自定义，根据当前号码遗漏值（除当期击中外），是否存在历史击中遗漏中
	@Override
	public String checkYiLouNowData() {
		// TODO Auto-generated method stub
		//1.获取当前maindata遗漏数据情况
		
		return null;
	}
	
	//遗漏表前区a[][]赋值，放入SHUtils工具类
	/*public int[][] copyToArrayA(YiLouData yiLouData){
		
		int[][] a=new int[35][3];
		a[0][0]=1;
		a[0][1]=yiLouData.getQqNo1();
	    a[0][2]=yiLouData.getQqNo1flag();
	    
	    a[1][0]=2;
		a[1][1]=yiLouData.getQqNo2();
	    a[1][2]=yiLouData.getQqNo2flag();
	    
	    a[2][0]=3;
		a[2][1]=yiLouData.getQqNo3();
	    a[2][2]=yiLouData.getQqNo3flag();
	    
	    a[3][0]=4;
		a[3][1]=yiLouData.getQqNo4();
	    a[3][2]=yiLouData.getQqNo4flag();
	    
	    a[4][0]=5;
		a[4][1]=yiLouData.getQqNo5();
	    a[4][2]=yiLouData.getQqNo5flag();
	    
	    a[5][0]=6;
		a[5][1]=yiLouData.getQqNo6();
	    a[5][2]=yiLouData.getQqNo6flag();
	    
	    a[6][0]=7;
		a[6][1]=yiLouData.getQqNo7();
	    a[6][2]=yiLouData.getQqNo7flag();
	    
	    a[7][0]=8;
		a[7][1]=yiLouData.getQqNo8();
	    a[7][2]=yiLouData.getQqNo8flag();
	    
	    a[8][0]=9;
		a[8][1]=yiLouData.getQqNo9();
	    a[8][2]=yiLouData.getQqNo9flag();
	    
	    a[9][0]=10;
		a[9][1]=yiLouData.getQqNo10();
	    a[9][2]=yiLouData.getQqNo10flag();
	    
	    a[10][0]=11;
		a[10][1]=yiLouData.getQqNo11();
	    a[10][2]=yiLouData.getQqNo11flag();
	    
	    a[11][0]=12;
		a[11][1]=yiLouData.getQqNo12();
	    a[11][2]=yiLouData.getQqNo12flag();
	    
	    a[12][0]=13;
		a[12][1]=yiLouData.getQqNo13();
	    a[12][2]=yiLouData.getQqNo13flag();
	    
	    a[13][0]=14;
		a[13][1]=yiLouData.getQqNo14();
	    a[13][2]=yiLouData.getQqNo14flag();
	    
	    a[14][0]=15;
		a[14][1]=yiLouData.getQqNo15();
	    a[14][2]=yiLouData.getQqNo15flag();
	    
	    a[15][0]=16;
		a[15][1]=yiLouData.getQqNo16();
	    a[15][2]=yiLouData.getQqNo16flag();
	    
	    a[16][0]=17;
		a[16][1]=yiLouData.getQqNo17();
	    a[16][2]=yiLouData.getQqNo17flag();
	    
	    a[17][0]=18;
		a[17][1]=yiLouData.getQqNo18();
	    a[17][2]=yiLouData.getQqNo18flag();
	    
	    a[18][0]=19;
		a[18][1]=yiLouData.getQqNo19();
	    a[18][2]=yiLouData.getQqNo19flag();
	    
	    a[19][0]=20;
		a[19][1]=yiLouData.getQqNo20();
	    a[19][2]=yiLouData.getQqNo20flag();
	    
	    
	    a[20][0]=21;
		a[20][1]=yiLouData.getQqNo21();
	    a[20][2]=yiLouData.getQqNo21flag();
	    
	    a[21][0]=22;
		a[21][1]=yiLouData.getQqNo22();
	    a[21][2]=yiLouData.getQqNo22flag();
	    
	    a[22][0]=23;
		a[22][1]=yiLouData.getQqNo23();
	    a[22][2]=yiLouData.getQqNo23flag();
	    
	    a[23][0]=24;
		a[23][1]=yiLouData.getQqNo24();
	    a[23][2]=yiLouData.getQqNo24flag();
	    
	    a[24][0]=25;
		a[24][1]=yiLouData.getQqNo25();
	    a[24][2]=yiLouData.getQqNo25flag();
	    
	    a[25][0]=26;
		a[25][1]=yiLouData.getQqNo26();
	    a[25][2]=yiLouData.getQqNo26flag();
	    
	    a[26][0]=27;
		a[26][1]=yiLouData.getQqNo27();
	    a[26][2]=yiLouData.getQqNo27flag();
	    
	    a[27][0]=28;
		a[27][1]=yiLouData.getQqNo28();
	    a[27][2]=yiLouData.getQqNo28flag();
	    
	    a[28][0]=29;
		a[28][1]=yiLouData.getQqNo29();
	    a[28][2]=yiLouData.getQqNo29flag();
	    
	    a[29][0]=30;
		a[29][1]=yiLouData.getQqNo30();
	    a[29][2]=yiLouData.getQqNo30flag();
	    
	    a[30][0]=31;
		a[30][1]=yiLouData.getQqNo31();
	    a[30][2]=yiLouData.getQqNo31flag();
	    
	    a[31][0]=32;
		a[31][1]=yiLouData.getQqNo32();
	    a[31][2]=yiLouData.getQqNo32flag();
	    
	    a[32][0]=33;
		a[32][1]=yiLouData.getQqNo33();
	    a[32][2]=yiLouData.getQqNo33flag();
	    
	    a[33][0]=34;
		a[33][1]=yiLouData.getQqNo34();
	    a[33][2]=yiLouData.getQqNo34flag();
	    
	    a[34][0]=35;
		a[34][1]=yiLouData.getQqNo35();
	    a[34][2]=yiLouData.getQqNo35flag();
	    
	    
		
		return a;
	}*/
	
	//遗漏表后区b[][]赋值，放入SHUtils工具类
	/*public int[][] copyToArrayB(YiLouData yiLouData){
		
		int[][] b=new int[12][3];
		
		b[0][0]=1;
		b[0][1]=yiLouData.getHqNo1();
	    b[0][2]=yiLouData.getHqNo1flag();
	    
		b[1][0]=2;
		b[1][1]=yiLouData.getHqNo2();
	    b[1][2]=yiLouData.getHqNo2flag();
	    
		b[2][0]=3;
		b[2][1]=yiLouData.getHqNo3();
	    b[2][2]=yiLouData.getHqNo3flag();
	    
		b[3][0]=4;
		b[3][1]=yiLouData.getHqNo4();
	    b[3][2]=yiLouData.getHqNo4flag();
	    
		b[4][0]=5;
		b[4][1]=yiLouData.getHqNo5();
	    b[4][2]=yiLouData.getHqNo5flag();
	    
		b[5][0]=6;
		b[5][1]=yiLouData.getHqNo6();
	    b[5][2]=yiLouData.getHqNo6flag();
	    
		b[6][0]=7;
		b[6][1]=yiLouData.getHqNo1();
	    b[6][2]=yiLouData.getHqNo7flag();
	    
	    
		b[7][0]=8;
		b[7][1]=yiLouData.getHqNo8();
	    b[7][2]=yiLouData.getHqNo8flag();
	    
		b[8][0]=9;
		b[8][1]=yiLouData.getHqNo9();
	    b[8][2]=yiLouData.getHqNo9flag();
	    
		b[9][0]=10;
		b[9][1]=yiLouData.getHqNo10();
	    b[9][2]=yiLouData.getHqNo10flag();
	    
	    b[10][0]=11;
		b[10][1]=yiLouData.getHqNo11();
	    b[10][2]=yiLouData.getHqNo11flag();
	    
	    b[11][0]=12;
		b[11][1]=yiLouData.getHqNo12();
	    b[11][2]=yiLouData.getHqNo12flag();
		return b;
	}*/

	

	
	
}
