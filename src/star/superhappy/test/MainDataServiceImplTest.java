package star.superhappy.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.MainData;
import star.superhappy.service.MainDataService;
import star.superhappy.utils.Util;
import star.superhappy.vo.NumberCount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class MainDataServiceImplTest {

	@Resource
	private MainDataService mainDataServcie;

	// 测试保存
	@Test
	public void testSave() {
		/*for (int i = 0; i < 5; i++) {
			MainData mainData = new MainData();
			mainData.setId(i);
			mainData.setFirst(1);
			mainData.setSecond(2);
			mainData.setThird(3);
			mainData.setFourth(4);
			mainData.setFifth(5);
			mainData.setSixth(6);
			mainData.setSeventh(7);
			mainData.setQhz(15);
			mainData.setHhz(13);
			mainData.setQjob("3:2");
			mainData.setHjob("1:1");
			mainData.setQxdb("5:0");
			mainData.setHxdb("0:2");
			mainData.setDate(Util.doConvertToDate("2017-12-31"));
			// mainData.setRemark("");
			mainDataServcie.save(mainData);
		}*/
		/*
		 * MainData mainData=new MainData(); mainData.setMainId(2017200);
		 * mainData.setFirst(1); mainData.setSecond(2); mainData.setThird(3);
		 * mainData.setFourth(4); mainData.setFifth(5); mainData.setSixth(6);
		 * mainData.setSeventh(7); mainData.setQhz(15); mainData.setHhz(13);
		 * mainData.setQjob("3:2"); mainData.setHjob("1:1");
		 * mainData.setQxdb("5:0"); mainData.setHxdb("0:2");
		 * mainData.setDate(Util.doConvertToDate("2017-12-31"));
		 */
		// mainData.setRemark("");
		// mainDataServcie.save(mainData);
	}


	// 测试更新
	@Test
	public void testUpdate() {
		MainData mainData = new MainData();
		mainData.setId(2008001);
		mainData.setFirst(1);
		mainData.setSecond(2);
		mainData.setThird(11);
		mainData.setFourth(34);
		mainData.setFifth(35);
		mainData.setSixth(3);
		mainData.setSeventh(10);
		mainData.setQhz(15);
		mainData.setHhz(13);
		mainData.setQjob("3:2");
		mainData.setHjob("1:1");
		mainData.setQxdb("5:0");
		mainData.setHxdb("0:2");
		mainData.setDate(Util.doConvertToDate("2008-1-2"));
		// mainData.setRemark("");
		mainDataServcie.update(mainData);
	}

	// 测试删除
	@Test
	public void testDelete() {
		mainDataServcie.delete(2017200);
	}

	// 测试get
	@Test
	public void testGet() {
		System.out.println(mainDataServcie.get(2018107));
	}

	// 测试query
	@Test
	public void testQuery() {
		List<MainData> li = mainDataServcie.query();
		for (MainData mainData : li) {
			System.out.println(mainData);
		}
	}

	// 测试批量到数据，并更新
	@Test
	public void saveAndUpdate() {

		try {
			Workbook wbook = Workbook.getWorkbook(new FileInputStream(
					"d:/inpuTest.xls"));
			Sheet sheet = wbook.getSheet(0);
			int columns = sheet.getColumns();
			int rows = sheet.getRows();
			// System.out.println("共有"+columns+"列"+rows+"行");//共有10列
			Cell cellA1 = sheet.getCell(0, 0);// 列,行
			// System.out.println(cellA1.getContents());
			// 第二行
			/*
			 * for(int i=0;i<columns;i++){ Cell c=sheet.getCell(i,1);
			 * System.out.print(c.getContents()+"\t"); }
			 */
			System.out.println();

			// 数据
			for (int i = 1; i < rows; i++) {// 行
				/*
				 * for(int j=0;j<columns;j++){//列 Cell c=sheet.getCell(j,i);
				 * System.out.print(c.getContents()+"\t"); }
				 */
				String id = sheet.getCell(0, i).getContents();

				if (!id.equals("")) {
					if (mainDataServcie.get(Integer.parseInt(id)) == null) {// 根据期号查询该期数据是否已经录入，未录入
						MainData mainData = new MainData();
						mainData.setId(Integer.parseInt(sheet.getCell(0, i)
								.getContents()));
						mainData.setFirst(Integer.parseInt(sheet.getCell(1, i)
								.getContents()));
						mainData.setSecond(Integer.parseInt(sheet.getCell(2, i)
								.getContents()));
						mainData.setThird(Integer.parseInt(sheet.getCell(3, i)
								.getContents()));
						mainData.setFourth(Integer.parseInt(sheet.getCell(4, i)
								.getContents()));
						mainData.setFifth(Integer.parseInt(sheet.getCell(5, i)
								.getContents()));
						mainData.setSixth(Integer.parseInt(sheet.getCell(6, i)
								.getContents()));
						mainData.setSeventh(Integer.parseInt(sheet
								.getCell(7, i).getContents()));
						mainData.setDate(Util.doConvertToDate("20"
								+ sheet.getCell(8, i).getContents()));// 开奖日期
						mainData.setRemark(sheet.getCell(9, i).getContents());
						// System.out.println(mainData);
						// System.out.print(sheet.getCell(0,i).getContents()+"\t");
						// System.out.print(sheet.getCell(1,i).getContents()+"\t");
						// System.out.print(sheet.getCell(2,i).getContents()+"\t");
						// System.out.print(sheet.getCell(3,i).getContents()+"\t");
						// System.out.print(sheet.getCell(4,i).getContents()+"\t");
						// System.out.print(sheet.getCell(5,i).getContents()+"\t");
						// System.out.print(sheet.getCell(6,i).getContents()+"\t");
						// System.out.print(sheet.getCell(7,i).getContents()+"\t");
						// System.out.print(sheet.getCell(8,i).getContents()+"\t");
						// System.out.println(sheet.getCell(9,i).getContents()+"\t");
						mainDataServcie.saveAndUpdate(mainData);
						System.out.println("期号："
								+ Integer.parseInt(sheet.getCell(0, i)
										.getContents()) + "，数据录入成功！");
					} else {// 根据期号查询该期数据是否已经录入，已录入，查询到第1个已录入信息，跳出循环
						System.out.println("异常，该期数据已经录入，请检查！");
						System.out.println("期号："
								+ Integer.parseInt(sheet.getCell(0, i)
										.getContents()));
						break;
					}
				}

			}
			wbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 测试，检测一组号码，在历期开奖中的中奖情况
	@Test
	public void checkByOneData() {
		MainData mainData = new MainData();
		mainData.setId(2018200);
		mainData.setFirst(2);
		mainData.setSecond(3);
		mainData.setThird(4);
		mainData.setFourth(9);
		mainData.setFifth(19);
		mainData.setSixth(5);
		mainData.setSeventh(12);
		// System.out.println(m.hxdb(mainData));
		System.out.println(mainDataServcie.checkByOneData(mainData));

	}

	// 读取当前mainData的最大期号id
	@Test
	public void testGetMaxMainDataId() {
		System.out.println(mainDataServcie.getMaxMainDataId());
	}

	// 读取当前mainData的最小期号id
	@Test
	public void testGetMinMainDataId() {
		System.out.println(mainDataServcie.getMinMainDataId());
	}

	// 根据日期区间，后区号码1，后区号码2，查询出现次数
	@Test
	public void getCountByDateAndhqNumber1AndhqNumber2() {
		System.out.println(mainDataServcie
				.getCountByDateAndhqNumber1AndhqNumber2(2, 2, "2006-12-31",
						"2008-01-01"));
	}

	// 根据日期区间，后区号码1，后区号码2，查询List
	@Test
	public void queryByDateAndhqNumber1AndhqNumber2() {
		List<MainData> mdLi = mainDataServcie
				.queryByDateAndhqNumber1AndhqNumber2(3, 4, "2006-12-31",
						"2008-01-01");
		for (MainData md : mdLi) {
			System.out.println(md);
		}
	}

	// 自定义，按年，号码查询出现次数，更新每年数据
	@Test
	public void updateMainDataStatistic() {
		 mainDataServcie.updateMainDataStatistic();
	}
	
	
	//自定义，按期号段，统计后区码出现次数
	@Test
	public void statisticMDHQCountByMDId(){
		 List<NumberCount> liNumCount=mainDataServcie.statisticMDHQCountByMDId(2020077, 2020086);
		 System.out.println(liNumCount);
	}
	
	//自定义，按期号段，统计前区码出现次数
    @Test
	public void statisticMDQQCountByMDId(){
    	 List<NumberCount> liNumCount=mainDataServcie.statisticMDQQCountByMDId(2020077, 2020086);
		 System.out.println(liNumCount);
    }
}
