package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.HouQuCombinationStatistic;
import star.superhappy.service.HouQuCombinationStatisticService;
import star.superhappy.utils.SHUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class HouQuCombinationStatisticServiceImplTest {
	
	
	@Resource
	private HouQuCombinationStatisticService hqCombinationStatisticService;

	@Test
	public void test(){
		/*for(int i=1;i<12;i++)
		  for(int j=2;j<13;j++){
			if(i<j){
				System.out.println(i+"-"+j);
			}
		}*/
		//System.out.println(2007001/1000);
	
		System.out.println(SHUtils.hqConvertCombination(2, 3));

		System.out.println(SHUtils.hqConvert012(2, 3));
		
		System.out.println(SHUtils.hqSum(2, 3));
		System.out.println(SHUtils.hqInterval(2, 3));
		
		
		System.out.println(SHUtils.ToYearPrevious(2018));

		System.out.println(SHUtils.ToYearNext(2018));
	}
	
	
	//初始化后区组合统计表，根据maindata中数据一次性更新
	@Test
	public void initHqCombinationStatistic(){
		hqCombinationStatisticService.initHqCombinationStatistic();
	}
	
	
	//自定义，根据组合号，年份查询
	@Test
	public void getByCombinationAndYear(){
		System.out.println(hqCombinationStatisticService.getByCombinationAndYear("1-2", 2017));
		}
	
	
	//自动更新后区号码组合统计表 
	@Test
	public void autoUpdateHQCombinateStatistic(){
		hqCombinationStatisticService.autoUpdateHQCombinateStatistic();
	}

}
