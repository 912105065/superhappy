package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.TypeAStatisticsDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.TypeAStatistics;
import star.superhappy.service.TypeAStatisticsService;

public class TypeAStatisticsServiceImpl implements TypeAStatisticsService {

	
	private TypeAStatisticsDao typeAStatisticsDao;
	
	
	private MainDataDao  mainDataDao;
	
	
	
	
	public TypeAStatisticsDao getTypeAStatisticsDao() {
		return typeAStatisticsDao;
	}



	public void setTypeAStatisticsDao(TypeAStatisticsDao typeAStatisticsDao) {
		this.typeAStatisticsDao = typeAStatisticsDao;
	}



	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}



	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}



	// 保存
	@Override
	public void save(TypeAStatistics typeAStatistics) {
		typeAStatisticsDao.save(typeAStatistics);
	}

	
	
	// 更新
	@Override
	public void update(TypeAStatistics typeAStatistics) {
		typeAStatisticsDao.update(typeAStatistics);
	}

	
	
	// 根据id，删除
	@Override
	public void delete(int id) {
		typeAStatisticsDao.delete(id);
	}
	
	
	
	
	// 根据id查询，返回单个实体
	@Override
	public TypeAStatistics get(int id) {
		return typeAStatisticsDao.get(id);
	}

	
	
	// 查询，返回List
	@Override
	public List<TypeAStatistics> query() {
		return typeAStatisticsDao.query();
	}


    //自动更新
	@Override
	public void autoBathUpdate() {
	  
		if(typeAStatisticsDao.query().size()==0){
			System.out.println("---typeAStatistics批量更新---");
			//1.为空，批量初始化
			List<MainData> mdLi=mainDataDao.query();
			for(int i=0;i<mdLi.size();i++){
				MainData md=mdLi.get(i);
				int typeAs1=0;//typeA统计值1
				int typeAs2=0;//typeA统计值2
				int typeAs3=0;//typeA统计值3
				int typeAs4=0;//typeA统计值4
				
				int[] arrayA={md.getFirst(),md.getSecond(),md.getThird(),md.getFourth(),md.getFifth()};
				
				for(int j=0;j<arrayA.length;j++){
					if(arrayA[j]>=1&&arrayA[j]<=9){
						typeAs1++;
					}
					if(arrayA[j]>=10&&arrayA[j]<=19){
						typeAs2++;
					}
					if(arrayA[j]>=20&&arrayA[j]<=29){
						typeAs3++;
					}
					if(arrayA[j]>=30&&arrayA[j]<=35){
						typeAs4++;
					}
					
				}
				
				TypeAStatistics typeAStatistics=new TypeAStatistics();
				typeAStatistics.setId(md.getId());
				typeAStatistics.setTypeAs1(typeAs1);
				typeAStatistics.setTypeAs2(typeAs2);
				typeAStatistics.setTypeAs3(typeAs3);
				typeAStatistics.setTypeAs4(typeAs4);
				//System.out.println(typeAStatistics);
				typeAStatisticsDao.save(typeAStatistics);
			}
			
		}else{ //2.自动更新
			int typeASMaxId=typeAStatisticsDao.getTypeAStatisticsMaxId();
			int mainDataMaxId=mainDataDao.getMaxMainDataId();
			if(typeASMaxId==mainDataMaxId){
				System.out.println("---typeAStatistics已经是最新版本---");
			}else if(typeASMaxId<mainDataMaxId){
				System.out.println("---typeAStatistics自动更新---");
				while(typeASMaxId<mainDataMaxId){
					
					typeASMaxId=typeASMaxId+1;
					//System.out.println("下一期id："+typeASMaxId);
					MainData md=mainDataDao.get(typeASMaxId);
					//System.out.println(md);
					if(md!=null){
						int[] arrayA=new int[5];
						if(md!=null){
							arrayA[0]=md.getFirst();
							arrayA[1]=md.getSecond();
							arrayA[2]=md.getThird();
							arrayA[3]=md.getFourth();
							arrayA[4]=md.getFifth();
						}
						
						int typeAs1=0;//typeA统计值1
						int typeAs2=0;//typeA统计值2
						int typeAs3=0;//typeA统计值3
						int typeAs4=0;//typeA统计值4
						
						
						
						for(int j=0;j<arrayA.length;j++){
							if(arrayA[j]>=1&&arrayA[j]<=9){
								typeAs1++;
							}
							if(arrayA[j]>=10&&arrayA[j]<=19){
								typeAs2++;
							}
							if(arrayA[j]>=20&&arrayA[j]<=29){
								typeAs3++;
							}
							if(arrayA[j]>=30&&arrayA[j]<=35){
								typeAs4++;
							}
							
						}
						
						TypeAStatistics typeAStatistics=new TypeAStatistics();
						typeAStatistics.setId(md.getId());
						typeAStatistics.setTypeAs1(typeAs1);
						typeAStatistics.setTypeAs2(typeAs2);
						typeAStatistics.setTypeAs3(typeAs3);
						typeAStatistics.setTypeAs4(typeAs4);
						//System.out.println(typeAStatistics);
						typeAStatisticsDao.save(typeAStatistics);
					}
					
					
					
					
					
				}
				
				
				
			}else{
				System.out.println("---typeAStatistics更新异常---");
			}
			
			
		}
		

		
	}
	
	

}
