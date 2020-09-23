package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.AcvalueStatistics;
import star.superhappy.pojo.MainData;
import star.superhappy.service.AcvalueStatisticsService;
import star.superhappy.service.MainDataService;
import star.superhappy.utils.AnalysisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class AcvalueStatisticsServiceImplTest {

	@Resource
	private AcvalueStatisticsService acvalueStatisticsService;
	
	@Resource
	private MainDataService mainDataService;

	@Test
	public void testSave() {
		MainData md=mainDataService.get(2007001);
		System.out.println(md);
		AcvalueStatistics ac=new AcvalueStatistics();
		ac.setId(md.getId());
		int acValue=AnalysisUtils.ACResult(md.getFirst(),md.getSecond(),md.getThird(),md.getFourth(),md.getFifth());
		ac.setId(md.getId());
		ac.setAcvalue(acValue);
		ac.setAcjo(AnalysisUtils.numberToJO(acValue));
		ac.setAcxd(AnalysisUtils.numberToXDByAC(acValue));
		ac.setAc012(Integer.parseInt((AnalysisUtils.numberTo012(acValue))));
		ac.setAczhy(AnalysisUtils.numberToZHY(acValue));
		ac.setAczf(Math.abs(acValue));
		ac.setMd(md);
		System.out.println(ac);
		acvalueStatisticsService.save(ac);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {

	}

	@Test
	public void testQuery() {
		System.out.println(acvalueStatisticsService.query());
	}

	// 自定义，自动更新
	@Test
	public void testAutoUpdate() {
		// TODO Auto-generated method stub
		acvalueStatisticsService.autoUpdate();
		//System.out.println(2007001/1000);
		//System.out.println((2007001/1000+1)*1000+1);
	}

}
