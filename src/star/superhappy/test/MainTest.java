package star.superhappy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.MainData;
import star.superhappy.service.AcvalueStatisticsService;
import star.superhappy.service.HouQuCombinationStatisticService;
import star.superhappy.service.LHstatisticsService;
import star.superhappy.service.MainDataBasicAnalysisService;
import star.superhappy.service.MainDataService;
import star.superhappy.service.MainDataStatisticService;
import star.superhappy.service.PoolsAService;
import star.superhappy.service.PoolsBService;
import star.superhappy.service.TypeAStatisticsService;
import star.superhappy.service.XDDistributionService;
import star.superhappy.service.YiLouDataService;
import star.superhappy.service.YiLouZhiStatisticV2Service;
import star.superhappy.service.YiLouZhiStatisticsService;
import star.superhappy.utils.AnalysisUtils;

//数据库各表自动更新
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class MainTest {

	@Resource
	private MainDataService mainDataServcie;

	@Resource
	private YiLouDataService yiLouDataService;

	@Resource
	private HouQuCombinationStatisticService hqCombinationStatisticService;

	@Resource
	private YiLouZhiStatisticsService yiLouZhiStatisticsService;

	@Resource
	private PoolsAService poolsAService;

	@Resource
	private PoolsBService poolsBService;

	@Resource
	private MainDataBasicAnalysisService mainDataBasicAnalysisService;
	
	@Resource
	private XDDistributionService xddistributionService;
	
	@Resource
	private LHstatisticsService lhstatisticsService;
	
	@Resource
	private MainDataStatisticService mainDataStatisticService;
	
	@Resource
	private AcvalueStatisticsService acvalueStatisticsService;
	
	@Resource
	private TypeAStatisticsService typeAStatisticsService;
	
	@Resource
	private YiLouZhiStatisticV2Service yiLouZhiStatisticV2Service;

	// 各表自动更新，除mainData主表外
	@Test
	public void updateBatch() {
		// 获取mainData中最大的mainDataId
		// int maxMainDataId = mainDataServcie.getMaxMainDataId();
		// 获取yiLouDataId中最大的yiLouDataIId
		// int maxYiLouDataId = yiLouDataService.getMaxYiLouDataId();
		// 获取yiLouZhiStatisticsId中最大的yiLouDataIId
		// int maxYiLouZhiStatisticsId =
		// yiLouZhiStatisticsService.getYiLouZhiStatisticsMaxId();

		// 遗漏表同步更新
		if (yiLouDataService.get(2007001) == null) {// 遗漏表2007001期未初始化
			yiLouDataService.yiLouDataInit();
			yiLouDataService.batchSaveData();
		} else {// 遗漏表2007001期已初始化
			yiLouDataService.batchSaveData();
		}

		// 后区组合统计同步更新
		hqCombinationStatisticService.autoUpdateHQCombinateStatistic();
		// 遗漏值统计表更新
		yiLouZhiStatisticsService.batchYiLouZhiStatisics();
		// 池A更新
		poolsAService.batchUpdate(3);
		// 池B更新
		poolsBService.batchUpdate(3);
		// mainDataBasicAnalysis更新
		mainDataBasicAnalysisService.batchUpdate();
		//xddistribution更新
		xddistributionService.batchUpdate();
		//lhstatistics更新
		lhstatisticsService.bathInitAndUpdate();
		//mainDataStatistic更新
		mainDataStatisticService.bathInitAndUpdate();
		//acvalueStatistics更新
		acvalueStatisticsService.autoUpdate();
		//typeAStatistics更新
		typeAStatisticsService.autoBathUpdate();
		//yiLouZhiStatisticV2更新
		yiLouZhiStatisticV2Service.batchUpdate();

	}

	// 一个数组，赋值给另一数组
	@Test
	public void copyArray() {
		int a[] = { 1, 2, 3 };
		int b[] = a;
		System.out.println("1复制后的数组b：");
		for (int data : b) {
			System.out.println(data);// 123
		}
		b[0] = 3;
		System.out.println("2修改后的数组a：");
		for (int data : a) {
			System.out.println(data);// 323
		}

	}

	// 前区测试
	@Test
	public void numberQQTest() {
		// 前区
		String qqNumber = "1,2,3,4,5,6,7,8";// 输入的后区号码1,2,3
		// String qqNumber = "8,2,3,4,5,6,7,1";// 输入的后区号码1,2,3
		System.out.println("前区号码：" + qqNumber);
		int qqCount = 0;// 记录前区组合数
		String[] qqStr = qqNumber.split(",");
		int[] qq = new int[qqStr.length];

		// 将String转换成int
		for (int i = 0; i < qqStr.length; i++) {
			// System.out.println(hqStr[i]);
			qq[i] = Integer.parseInt(qqStr[i]);
			// System.out.println(hq[i]);
		}

		// Set<String> qqSet = new HashSet<String>();
		List<String> qqLi = new ArrayList<String>();
		// 后区组合计数
		for (int i = 0; i < qq.length; i++)
			for (int j = 0; j < qq.length; j++)
				for (int k = 0; k < qq.length; k++)
					for (int l = 0; l < qq.length; l++)
						for (int m = 0; m < qq.length; m++) {
							if (qq[i] < qq[j] && qq[j] < qq[k] && qq[k] < qq[l]
									&& qq[l] < qq[m]) {
								qqLi.add(qq[i] + "-" + qq[j] + "-" + qq[k]
										+ "-" + qq[l] + "-" + qq[m]);
								// System.out.println(qq[i] + "-" + qq[j]+"-"+
								// qq[k]+"-"+qq[l]+"-"+qq[m]);
								qqCount++;
							}

						}
		System.out.println("前区组合数：" + qqCount);
		System.out.println("前区组合明细：");

		// 前区明细
		for (String str : qqLi) {
			System.out.println(str);
		}
	}

	// 后区测试
	@Test
	public void numberHQTest() {
		// 后区
		String hqNumber = "1,2,3,4,5,6";// 输入的后区号码1,2,3
		System.out.println("后区号码：" + hqNumber);
		int hqCount = 0;// 记录后区组合数
		String[] hqStr = hqNumber.split(",");
		int[] hq = new int[hqStr.length];

		// 将String转换成int
		for (int i = 0; i < hqStr.length; i++) {
			// System.out.println(hqStr[i]);
			hq[i] = Integer.parseInt(hqStr[i]);
			// System.out.println(hq[i]);
		}
		List<String> hqLi = new ArrayList<String>();
		// 后区组合计数
		for (int i = 0; i < hq.length; i++)
			for (int j = 0; j < hq.length; j++) {
				if (hq[i] < hq[j]) {
					// System.out.println(hq[i]+"-"+hq[j]);
					hqLi.add(hq[i] + "-" + hq[j]);
					hqCount++;
				}

			}
		System.out.println("后区组合数：" + hqCount);
		System.out.println("后区组合明细：");

		// 后区明细
		for (String str : hqLi) {
			System.out.println(str);
		}
	}

	// 号码出现统计整合前区后区
	@Test
	public void testNumber() {

		String qqNumber = "1,2,3,4,5,6,7,8";// 输入的后区号码1,2,3
		String hqNumber = "1,2,3,4,5,6";// 输入的后区号码1,2,3

		// 前区
		System.out.println("前区号码：" + qqNumber);
		int qqCount = 0;// 记录前区组合数
		String[] qqStr = qqNumber.split(",");
		int[] qq = new int[qqStr.length];

		// 将String转换成int
		for (int i = 0; i < qqStr.length; i++) {
			// System.out.println(hqStr[i]);
			qq[i] = Integer.parseInt(qqStr[i]);
			// System.out.println(hq[i]);
		}

		// Set<String> qqSet = new HashSet<String>();
		List<String> qqLi = new ArrayList<String>();
		// 后区组合计数
		for (int i = 0; i < qq.length; i++)
			for (int j = 0; j < qq.length; j++)
				for (int k = 0; k < qq.length; k++)
					for (int l = 0; l < qq.length; l++)
						for (int m = 0; m < qq.length; m++) {
							if (qq[i] < qq[j] && qq[j] < qq[k] && qq[k] < qq[l]
									&& qq[l] < qq[m]) {
								qqLi.add(qq[i] + "-" + qq[j] + "-" + qq[k]
										+ "-" + qq[l] + "-" + qq[m]);
								// System.out.println(qq[i] + "-" + qq[j]+"-"+
								// qq[k]+"-"+qq[l]+"-"+qq[m]);
								qqCount++;
							}

						}
		System.out.println("前区组合数：" + qqCount);
		System.out.println("前区组合明细：");

		// 前区明细
		for (String str : qqLi) {
			System.out.println(str);
		}

		// 后区
		System.out.println("后区号码：" + hqNumber);
		int hqCount = 0;// 记录后区组合数
		String[] hqStr = hqNumber.split(",");
		int[] hq = new int[hqStr.length];

		// 将String转换成int
		for (int i = 0; i < hqStr.length; i++) {
			// System.out.println(hqStr[i]);
			hq[i] = Integer.parseInt(hqStr[i]);
			// System.out.println(hq[i]);
		}
		List<String> hqLi = new ArrayList<String>();
		// 后区组合计数
		for (int i = 0; i < hq.length; i++)
			for (int j = 0; j < hq.length; j++) {
				if (hq[i] < hq[j]) {
					// System.out.println(hq[i]+"-"+hq[j]);
					hqLi.add(hq[i] + "-" + hq[j]);
					hqCount++;
				}

			}
		System.out.println("后区组合数：" + hqCount);
		System.out.println("后区组合明细：");

		// 后区明细
		for (String str : hqLi) {
			System.out.println(str);
		}

		System.out.println("********总计************");
		System.out.println("组合数：" + qqCount * hqCount);
		System.out.println("明细：");
		for (int i = 0; i < qqLi.size(); i++)
			for (int j = 0; j < hqLi.size(); j++) {
				System.out.println(qqLi.get(i) + "+" + hqLi.get(j));
			}

	}

	// 数组去除重复项
	@Test
	public void testDistinct() {
		int array[] = { 1, 2, 3, 4, 1 };
		int outCount = 0;// 记录地址池B中的排除个数

		List<Integer> tempLiB = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (!tempLiB.contains(array[i])) {
				tempLiB.add(array[i]);
				outCount++;
			}
		}
		Object[] tempArray = tempLiB.toArray();
		System.out.println("outCount:" + outCount);
		int arrayBOut[] = new int[outCount];// 地址池B中的排除号码
		/*
		 * for(Object temp:tempArray){ System.out.println(temp); }
		 */
		for (int i = 0; i < arrayBOut.length; i++) {
			arrayBOut[i] = (int) tempArray[i];
		}

		for (int temp : arrayBOut) {
			System.out.println(temp);
		}

		System.out.println(2007003 % 1000 - 1);

	}

	// 测试地址池与开奖号码的匹配
	@Test
	public void match() {
		List<Integer> pools = new ArrayList<Integer>();
		pools.add(1);
		pools.add(2);
		pools.add(3);
		pools.add(4);

		int arrayB[] = { 3 };

		int count = 0;
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < arrayB.length; i++) {
			if (pools.contains(arrayB[i])) {
				count++;
				str.append(arrayB[i]);
				str.append(",");

			}
		}
		// System.out.println("count:"+count);
		// System.out.println("str:"+str.toString());
		int index = str.lastIndexOf(",");
		// System.out.println(index);
		String newStr = str.substring(0, index);
		System.out.println(newStr);
	}

	// 根据号码，判断质合一
	@Test
	public void numberToZHY() {
		System.out.println(AnalysisUtils.numberToZHY(25));

	}

	// 测试连号判断
	@Test
	public void testLianHao() {
		List<MainData> mdLi=mainDataServcie.query();
		for(MainData md:mdLi){
			int[] tempA = { md.getFirst(), md.getSecond(), md.getThird(),md.getFourth(), md.getFifth() };
			String[] lhStr= {"",""};
			int i = 0;//i代表前区的位数
			// 递归
			while (i < 5) {

				if (i == 0) {
					StringBuffer str = new StringBuffer();
					if (tempA[0] + 1 == tempA[1]) {
						str.append(String.valueOf(tempA[0]) + ";"
								+ String.valueOf(tempA[1]));
						i=1;
						if (tempA[1] + 1 == tempA[2]) {
							str.append(";" + String.valueOf(tempA[2]));
							i=2;
							if (tempA[2] + 1 == tempA[3]) {
								str.append(";" + String.valueOf(tempA[3]));
								i=3;
								if (tempA[3] + 1 == tempA[4]) {
									str.append(";" + String.valueOf(tempA[4]));
									i=4;
								}

							}

						}
						//System.out.println(str);
						if(str!=null&&(!str.equals(""))){
							if(lhStr[0].equals("")){
								lhStr[0]=str.toString();
							}else{
								lhStr[1]=str.toString();
							}
							
						}
					}
				}
				if (i == 1) {

					StringBuffer str = new StringBuffer();
					if (tempA[1] + 1 == tempA[2]) {
						str.append(String.valueOf(tempA[1]) + ";"
								+ String.valueOf(tempA[2]));
						i=2;
						if (tempA[2] + 1 == tempA[3]) {
							str.append(";" + String.valueOf(tempA[3]));
							i=3;
							if (tempA[3] + 1 == tempA[4]) {
								str.append(";" + String.valueOf(tempA[4]));
								i=4;
							}

						}
						//System.out.println(str);
						if(str!=null&&(!str.equals(""))){
							if(lhStr[0].equals("")){
								lhStr[0]=str.toString();
							}else{
								lhStr[1]=str.toString();
							}
						}
					}
					
				}
				if (i == 2) {
					StringBuffer str = new StringBuffer();
					if (tempA[2] + 1 == tempA[3]) {
						str.append(String.valueOf(tempA[2]) + ";"
								+ String.valueOf(tempA[3]));
						i=3;
						if (tempA[3] + 1 == tempA[4]) {
							str.append(";" + String.valueOf(tempA[4]));
	                        i=4;
						}
						//System.out.println(str);
						if(str!=null&&(!str.equals(""))){
							if(lhStr[0].equals("")){
								lhStr[0]=str.toString();
							}else{
								lhStr[1]=str.toString();
							}
						}
					}
				}
				if (i == 3) {
					StringBuffer str = new StringBuffer();
					if (tempA[3] + 1 == tempA[4]) {
						str.append(String.valueOf(tempA[3]) + ";"
								+ String.valueOf(tempA[4]));
						i=4;
						//System.out.println(str);
						if(str!=null&&(!str.equals(""))){
							if(lhStr[0].equals("")){
								lhStr[0]=str.toString();
							}else{
								lhStr[1]=str.toString();
							}
						}
					}
				}
				i++;
			}
	         
		
				System.out.println(md.getId()+"-"+lhStr[0]+"-"+lhStr[1]);
			
		}
		
		
		
		


	}
	
	
	@Test
	public void stringTest(){
		String str="";
		String[] array=str.split(",");
		System.out.println(array[0]);
	}
	
	@Test
	public void acText(){
		int[] arrayA={10,11,16,18,19};
		int[] rs=new int[10];
		rs[0]=arrayA[1]-arrayA[0];
		rs[1]=arrayA[2]-arrayA[0];
		rs[2]=arrayA[3]-arrayA[0];
		rs[3]=arrayA[4]-arrayA[0];
		rs[4]=arrayA[2]-arrayA[1];
		rs[5]=arrayA[3]-arrayA[1];
		rs[6]=arrayA[4]-arrayA[1];
	    rs[7]=arrayA[3]-arrayA[2];
	    rs[8]=arrayA[4]-arrayA[2];
	    rs[9]=arrayA[4]-arrayA[3];
	    
	    Set<Integer> rsSet=new HashSet<Integer>();
	    for(int i:rs){
	    	//System.out.println(i);
	    	rsSet.add(i);
	    }
	    
	    //System.out.println(rsSet.size());
	    int acValue=rsSet.size()-(5-1);
	    System.out.println("AC:"+acValue);
	    
	}
	
	
}
