package star.superhappy.service.impl;

import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.MainDataStatisticDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.MainDataStatistic;
import star.superhappy.service.MainDataStatisticService;

public class MainDataStatisticServiceImpl implements MainDataStatisticService {

	private MainDataStatisticDao mainDataStatisticDao;

	private MainDataDao mainDataDao;

	public MainDataStatisticDao getMainDataStatisticDao() {
		return mainDataStatisticDao;
	}

	public void setMainDataStatisticDao(
			MainDataStatisticDao mainDataStatisticDao) {
		this.mainDataStatisticDao = mainDataStatisticDao;
	}

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	// 保存
	@Override
	public void save(MainDataStatistic mds) {
		mainDataStatisticDao.save(mds);
	}

	// 更新
	@Override
	public void update(MainDataStatistic mds) {
		mainDataStatisticDao.update(mds);
	}

	// 根据id删除
	@Override
	public void delete(int id) {
		mainDataStatisticDao.delete(id);
	}

	// 根据id查询单个实体
	@Override
	public MainDataStatistic get(int id) {
		return mainDataStatisticDao.get(id);
	}

	// 查询，返回list
	@Override
	public List<MainDataStatistic> query() {
		return mainDataStatisticDao.query();
	}

	// 自定义，批量初始化和更新
	@Override
	public void bathInitAndUpdate() {
		// mainDataStatisticDao.query().size()==0
		if (mainDataStatisticDao.query().size() == 0) {
			System.out.println("---初始化mainData统计---");
			int minMDId = mainDataDao.getMinMainDataId();
			int maxMDId = mainDataDao.getMaxMainDataId();
			int minYear = minMDId / 1000;// 2007
			int maxYear = maxMDId / 1000;// 2018

			while (minYear < maxYear + 1) {
				int minId = minYear * 1000 + 1;
				int maxId = mainDataDao.getMaxMainDataIdByYear(minYear);
				// long no1=mainDataDao.statisticMDFirstCountByMDId(minId,
				// maxId, 1);
				long[] arrayA = new long[35];
				long[] arrayB = new long[12];
				for (int i = 0; i < 35; i++) {
					arrayA[i] = mainDataDao.statisticMDFirstCountByMDId(minId,
							maxId, i + 1)
							+ mainDataDao.statisticMDSecondCountByMDId(minId,
									maxId, i + 1)
							+ mainDataDao.statisticMDThirdCountByMDId(minId,
									maxId, i + 1)
							+ mainDataDao.statisticMDFourthCountByMDId(minId,
									maxId, i + 1)
							+ mainDataDao.statisticMDFifthCountByMDId(minId,
									maxId, i + 1);
				}
				for (int i = 0; i < 12; i++) {
					arrayB[i] = mainDataDao.statisticMDSixthCountByMDId(minId,
							maxId, i + 1)
							+ mainDataDao.statisticMDSeventhCountByMDId(minId,
									maxId, i + 1);
				}
				int qq1count = (int) arrayA[0];
				int qq2count = (int) arrayA[1];
				int qq3count = (int) arrayA[2];
				int qq4count = (int) arrayA[3];
				int qq5count = (int) arrayA[4];
				int qq6count = (int) arrayA[5];
				int qq7count = (int) arrayA[6];
				int qq8count = (int) arrayA[7];
				int qq9count = (int) arrayA[8];
				int qq10count = (int) arrayA[9];
				int qq11count = (int) arrayA[10];
				int qq12count = (int) arrayA[11];
				int qq13count = (int) arrayA[12];
				int qq14count = (int) arrayA[13];
				int qq15count = (int) arrayA[14];
				int qq16count = (int) arrayA[15];
				int qq17count = (int) arrayA[16];
				int qq18count = (int) arrayA[17];
				int qq19count = (int) arrayA[18];
				int qq20count = (int) arrayA[19];
				int qq21count = (int) arrayA[20];
				int qq22count = (int) arrayA[21];
				int qq23count = (int) arrayA[22];
				int qq24count = (int) arrayA[23];
				int qq25count = (int) arrayA[24];
				int qq26count = (int) arrayA[25];
				int qq27count = (int) arrayA[26];
				int qq28count = (int) arrayA[27];
				int qq29count = (int) arrayA[28];
				int qq30count = (int) arrayA[29];
				int qq31count = (int) arrayA[30];
				int qq32count = (int) arrayA[31];
				int qq33count = (int) arrayA[32];
				int qq34count = (int) arrayA[33];
				int qq35count = (int) arrayA[34];
				int hq1count = (int) arrayB[0];
				int hq2count = (int) arrayB[1];
				int hq3count = (int) arrayB[2];
				int hq4count = (int) arrayB[3];
				int hq5count = (int) arrayB[4];
				int hq6count = (int) arrayB[5];
				int hq7count = (int) arrayB[6];
				int hq8count = (int) arrayB[7];
				int hq9count = (int) arrayB[8];
				int hq10count = (int) arrayB[9];
				int hq11count = (int) arrayB[10];
				int hq12count = (int) arrayB[11];
				int year = minYear;
				// int maxId;
				MainDataStatistic mds = new MainDataStatistic(qq1count,
						qq2count, qq3count, qq4count, qq5count, qq6count,
						qq7count, qq8count, qq9count, qq10count, qq11count,
						qq12count, qq13count, qq14count, qq15count, qq16count,
						qq17count, qq18count, qq19count, qq20count, qq21count,
						qq22count, qq23count, qq24count, qq25count, qq26count,
						qq27count, qq28count, qq29count, qq30count, qq31count,
						qq32count, qq33count, qq34count, qq35count, hq1count,
						hq2count, hq3count, hq4count, hq5count, hq6count,
						hq7count, hq8count, hq9count, hq10count, hq11count,
						hq12count, year, maxId % 1000);

				// System.out.println(mds);

				mainDataStatisticDao.save(mds);
				minYear++;

			}

		} else {
			// System.out.println("---更新mainData统计---");
			// 获取mainDataStatistic的最大year和该year的最大id
			int nowYear = mainDataStatisticDao.getMaxYear();
			int nowId = mainDataStatisticDao.getByYear(nowYear).getMaxId();
			int maxMDSId = nowYear * 1000 + nowId;// maxMDSId为最大的期号
			// System.out.println(nowYear+"-"+nowId+"-"+(nowYear*1000+nowId));
			int maxMDId = mainDataDao.getMaxMainDataId();// 获取mainData的最大id
			// System.out.println("md-year:"+maxMDId/1000);
			if (nowYear == maxMDId / 1000) {// 同年数据更新
				System.out.println("---更新mainData统计--同年数据更新---");
				
				int startId = maxMDSId + 1;
				//int maxMDId = mainDataDao.getMaxMainDataIdByYear(nowYear);
				while (startId < maxMDId + 1) {
					MainData md=mainDataDao.get(startId);
					//System.out.println(md);
					MainDataStatistic mds=mainDataStatisticDao.getByYear(nowYear);
					int tempA[]=new int[36];
					int tempB[]=new int[13];
					tempA[1]=mds.getQq1count();
					tempA[2]=mds.getQq2count();
					tempA[3]=mds.getQq3count();
					tempA[4]=mds.getQq4count();
					tempA[5]=mds.getQq5count();
					tempA[6]=mds.getQq6count();
					tempA[7]=mds.getQq7count();
					tempA[8]=mds.getQq8count();
					tempA[9]=mds.getQq9count();
					tempA[10]=mds.getQq10count();
					tempA[11]=mds.getQq11count();
					tempA[12]=mds.getQq12count();
					tempA[13]=mds.getQq13count();
					tempA[14]=mds.getQq14count();
					tempA[15]=mds.getQq15count();
					tempA[16]=mds.getQq16count();
					tempA[17]=mds.getQq17count();
					tempA[18]=mds.getQq18count();
					tempA[19]=mds.getQq19count();
					tempA[20]=mds.getQq20count();
					tempA[21]=mds.getQq21count();
					tempA[22]=mds.getQq22count();
					tempA[23]=mds.getQq23count();
					tempA[24]=mds.getQq24count();
					tempA[25]=mds.getQq25count();
					tempA[26]=mds.getQq26count();
					tempA[27]=mds.getQq27count();
					tempA[28]=mds.getQq28count();
					tempA[29]=mds.getQq29count();
					tempA[30]=mds.getQq30count();
					tempA[31]=mds.getQq31count();
					tempA[32]=mds.getQq32count();
					tempA[33]=mds.getQq33count();
					tempA[34]=mds.getQq34count();
					tempA[35]=mds.getQq35count();
					
					tempB[1]=mds.getHq1count();
					tempB[2]=mds.getHq2count();
					tempB[3]=mds.getHq3count();
					tempB[4]=mds.getHq4count();
					tempB[5]=mds.getHq5count();
					tempB[6]=mds.getHq6count();
					tempB[7]=mds.getHq7count();
					tempB[8]=mds.getHq8count();
					tempB[9]=mds.getHq9count();
					tempB[10]=mds.getHq10count();
					tempB[11]=mds.getHq11count();
					tempB[12]=mds.getHq12count();
					
					int firstCount=md.getFirst();
					//System.out.println("first:"+firstCount);
					int tempFirstCount=tempA[firstCount];
					tempA[firstCount]=tempFirstCount+1;
					//System.out.println("tempA[firstCount]:"+tempA[2]);
					
					int secondCount=md.getSecond();
					int tempSecondCount=tempA[secondCount];
					tempA[secondCount]=tempSecondCount+1;
					
					
					int thirdCount=md.getThird();
					int tempThirdCount=tempA[thirdCount];
					tempA[thirdCount]=tempThirdCount+1;
					
					int fourthCount=md.getFourth();
					int tempFourthCount=tempA[fourthCount];
					tempA[fourthCount]=tempFourthCount+1;
					
					
					int fifthCount=md.getFifth();
					int tempFifthCount=tempA[fifthCount];
					tempA[fifthCount]=tempFifthCount+1;
					
					
					int sixthCount=md.getSixth();
					int tempSixthCount=tempB[sixthCount];
					tempB[sixthCount]=tempSixthCount+1;

					int seventhCount=md.getSeventh();
					int tempSeventhCount=tempB[seventhCount];
					tempB[seventhCount]=tempSeventhCount+1;
					
					//int id=mds.getId();
			        //int year=mds.getYear();
					int maxId=mds.getMaxId()+1;
					
                    mds.setMaxId(maxId);
					mds.setQq1count(tempA[1]);
					mds.setQq2count(tempA[2]);
					mds.setQq3count(tempA[3]);
					mds.setQq4count(tempA[4]);
					mds.setQq5count(tempA[5]);
					mds.setQq6count(tempA[6]);
					mds.setQq7count(tempA[7]);
					mds.setQq8count(tempA[8]);
					mds.setQq9count(tempA[9]);
					mds.setQq10count(tempA[10]);
					mds.setQq11count(tempA[11]);
					mds.setQq12count(tempA[12]);
					mds.setQq13count(tempA[13]);
					mds.setQq14count(tempA[14]);
					mds.setQq15count(tempA[15]);
					mds.setQq16count(tempA[16]);
					mds.setQq17count(tempA[17]);
					mds.setQq18count(tempA[18]);
					mds.setQq19count(tempA[19]);
					mds.setQq20count(tempA[20]);
					mds.setQq21count(tempA[21]);
					mds.setQq22count(tempA[22]);
					mds.setQq23count(tempA[23]);
					mds.setQq24count(tempA[24]);
					mds.setQq25count(tempA[25]);
					mds.setQq26count(tempA[26]);
					mds.setQq27count(tempA[27]);
					mds.setQq28count(tempA[28]);
					mds.setQq29count(tempA[29]);
					mds.setQq30count(tempA[30]);
					mds.setQq31count(tempA[31]);
					mds.setQq32count(tempA[32]);
					mds.setQq33count(tempA[33]);
					mds.setQq34count(tempA[34]);
					mds.setQq35count(tempA[35]);
					
					mds.setHq1count(tempB[1]);
					mds.setHq2count(tempB[2]);
					mds.setHq3count(tempB[3]);
					mds.setHq4count(tempB[4]);
					mds.setHq5count(tempB[5]);
					mds.setHq6count(tempB[6]);
					mds.setHq7count(tempB[7]);
					mds.setHq8count(tempB[8]);
					mds.setHq9count(tempB[9]);
					mds.setHq10count(tempB[10]);
					mds.setHq11count(tempB[11]);
					mds.setHq12count(tempB[12]);
					
					//System.out.println(newMDS);
					mainDataStatisticDao.update(mds);
					
					startId++;
				}
				
				
				
			} else {// 跨年数据更新
				System.out.println("---更新mainData统计--跨年数据更新---");
				// 前一年数据
				int startIdPreYear = maxMDSId + 1;
				int maxMDIdPreYear = mainDataDao
						.getMaxMainDataIdByYear(nowYear);
				while (startIdPreYear < maxMDIdPreYear + 1) {
					MainData md=mainDataDao.get(startIdPreYear);
					//System.out.println(md);
					MainDataStatistic mds=mainDataStatisticDao.getByYear(nowYear);
					int tempA[]=new int[36];
					int tempB[]=new int[13];
					tempA[1]=mds.getQq1count();
					tempA[2]=mds.getQq2count();
					tempA[3]=mds.getQq3count();
					tempA[4]=mds.getQq4count();
					tempA[5]=mds.getQq5count();
					tempA[6]=mds.getQq6count();
					tempA[7]=mds.getQq7count();
					tempA[8]=mds.getQq8count();
					tempA[9]=mds.getQq9count();
					tempA[10]=mds.getQq10count();
					tempA[11]=mds.getQq11count();
					tempA[12]=mds.getQq12count();
					tempA[13]=mds.getQq13count();
					tempA[14]=mds.getQq14count();
					tempA[15]=mds.getQq15count();
					tempA[16]=mds.getQq16count();
					tempA[17]=mds.getQq17count();
					tempA[18]=mds.getQq18count();
					tempA[19]=mds.getQq19count();
					tempA[20]=mds.getQq20count();
					tempA[21]=mds.getQq21count();
					tempA[22]=mds.getQq22count();
					tempA[23]=mds.getQq23count();
					tempA[24]=mds.getQq24count();
					tempA[25]=mds.getQq25count();
					tempA[26]=mds.getQq26count();
					tempA[27]=mds.getQq27count();
					tempA[28]=mds.getQq28count();
					tempA[29]=mds.getQq29count();
					tempA[30]=mds.getQq30count();
					tempA[31]=mds.getQq31count();
					tempA[32]=mds.getQq32count();
					tempA[33]=mds.getQq33count();
					tempA[34]=mds.getQq34count();
					tempA[35]=mds.getQq35count();
					
					tempB[1]=mds.getHq1count();
					tempB[2]=mds.getHq2count();
					tempB[3]=mds.getHq3count();
					tempB[4]=mds.getHq4count();
					tempB[5]=mds.getHq5count();
					tempB[6]=mds.getHq6count();
					tempB[7]=mds.getHq7count();
					tempB[8]=mds.getHq8count();
					tempB[9]=mds.getHq9count();
					tempB[10]=mds.getHq10count();
					tempB[11]=mds.getHq11count();
					tempB[12]=mds.getHq12count();
					
					int firstCount=md.getFirst();
					//System.out.println("first:"+firstCount);
					int tempFirstCount=tempA[firstCount];
					tempA[firstCount]=tempFirstCount+1;
					//System.out.println("tempA[firstCount]:"+tempA[2]);
					
					int secondCount=md.getSecond();
					int tempSecondCount=tempA[secondCount];
					tempA[secondCount]=tempSecondCount+1;
					
					
					int thirdCount=md.getThird();
					int tempThirdCount=tempA[thirdCount];
					tempA[thirdCount]=tempThirdCount+1;
					
					int fourthCount=md.getFourth();
					int tempFourthCount=tempA[fourthCount];
					tempA[fourthCount]=tempFourthCount+1;
					
					
					int fifthCount=md.getFifth();
					int tempFifthCount=tempA[fifthCount];
					tempA[fifthCount]=tempFifthCount+1;
					
					
					int sixthCount=md.getSixth();
					int tempSixthCount=tempB[sixthCount];
					tempB[sixthCount]=tempSixthCount+1;

					int seventhCount=md.getSeventh();
					int tempSeventhCount=tempB[seventhCount];
					tempB[seventhCount]=tempSeventhCount+1;
					
					//int id=mds.getId();
			        //int year=mds.getYear();
					int maxId=mds.getMaxId()+1;
					
                    mds.setMaxId(maxId);
					mds.setQq1count(tempA[1]);
					mds.setQq2count(tempA[2]);
					mds.setQq3count(tempA[3]);
					mds.setQq4count(tempA[4]);
					mds.setQq5count(tempA[5]);
					mds.setQq6count(tempA[6]);
					mds.setQq7count(tempA[7]);
					mds.setQq8count(tempA[8]);
					mds.setQq9count(tempA[9]);
					mds.setQq10count(tempA[10]);
					mds.setQq11count(tempA[11]);
					mds.setQq12count(tempA[12]);
					mds.setQq13count(tempA[13]);
					mds.setQq14count(tempA[14]);
					mds.setQq15count(tempA[15]);
					mds.setQq16count(tempA[16]);
					mds.setQq17count(tempA[17]);
					mds.setQq18count(tempA[18]);
					mds.setQq19count(tempA[19]);
					mds.setQq20count(tempA[20]);
					mds.setQq21count(tempA[21]);
					mds.setQq22count(tempA[22]);
					mds.setQq23count(tempA[23]);
					mds.setQq24count(tempA[24]);
					mds.setQq25count(tempA[25]);
					mds.setQq26count(tempA[26]);
					mds.setQq27count(tempA[27]);
					mds.setQq28count(tempA[28]);
					mds.setQq29count(tempA[29]);
					mds.setQq30count(tempA[30]);
					mds.setQq31count(tempA[31]);
					mds.setQq32count(tempA[32]);
					mds.setQq33count(tempA[33]);
					mds.setQq34count(tempA[34]);
					mds.setQq35count(tempA[35]);
					
					mds.setHq1count(tempB[1]);
					mds.setHq2count(tempB[2]);
					mds.setHq3count(tempB[3]);
					mds.setHq4count(tempB[4]);
					mds.setHq5count(tempB[5]);
					mds.setHq6count(tempB[6]);
					mds.setHq7count(tempB[7]);
					mds.setHq8count(tempB[8]);
					mds.setHq9count(tempB[9]);
					mds.setHq10count(tempB[10]);
					mds.setHq11count(tempB[11]);
					mds.setHq12count(tempB[12]);
					
					//System.out.println(newMDS);
					mainDataStatisticDao.update(mds);
					
					startIdPreYear++;
				}

				// 当年数据,save
				int startIdNowYear =( maxMDId/1000)*1000 + 1;
				long[] arrayA = new long[35];
				long[] arrayB = new long[12];
				for (int i = 0; i < 35; i++) {
					arrayA[i] = mainDataDao.statisticMDFirstCountByMDId(startIdNowYear,
							maxMDId, i + 1)
							+ mainDataDao.statisticMDSecondCountByMDId(startIdNowYear,
									maxMDId, i + 1)
							+ mainDataDao.statisticMDThirdCountByMDId(startIdNowYear,
									maxMDId, i + 1)
							+ mainDataDao.statisticMDFourthCountByMDId(startIdNowYear,
									maxMDId, i + 1)
							+ mainDataDao.statisticMDFifthCountByMDId(startIdNowYear,
									maxMDId, i + 1);
				}
				for (int i = 0; i < 12; i++) {
					arrayB[i] = mainDataDao.statisticMDSixthCountByMDId(startIdNowYear,
							maxMDId, i + 1)
							+ mainDataDao.statisticMDSeventhCountByMDId(startIdNowYear,
									maxMDId, i + 1);
				}
				int qq1count = (int) arrayA[0];
				int qq2count = (int) arrayA[1];
				int qq3count = (int) arrayA[2];
				int qq4count = (int) arrayA[3];
				int qq5count = (int) arrayA[4];
				int qq6count = (int) arrayA[5];
				int qq7count = (int) arrayA[6];
				int qq8count = (int) arrayA[7];
				int qq9count = (int) arrayA[8];
				int qq10count = (int) arrayA[9];
				int qq11count = (int) arrayA[10];
				int qq12count = (int) arrayA[11];
				int qq13count = (int) arrayA[12];
				int qq14count = (int) arrayA[13];
				int qq15count = (int) arrayA[14];
				int qq16count = (int) arrayA[15];
				int qq17count = (int) arrayA[16];
				int qq18count = (int) arrayA[17];
				int qq19count = (int) arrayA[18];
				int qq20count = (int) arrayA[19];
				int qq21count = (int) arrayA[20];
				int qq22count = (int) arrayA[21];
				int qq23count = (int) arrayA[22];
				int qq24count = (int) arrayA[23];
				int qq25count = (int) arrayA[24];
				int qq26count = (int) arrayA[25];
				int qq27count = (int) arrayA[26];
				int qq28count = (int) arrayA[27];
				int qq29count = (int) arrayA[28];
				int qq30count = (int) arrayA[29];
				int qq31count = (int) arrayA[30];
				int qq32count = (int) arrayA[31];
				int qq33count = (int) arrayA[32];
				int qq34count = (int) arrayA[33];
				int qq35count = (int) arrayA[34];
				int hq1count = (int) arrayB[0];
				int hq2count = (int) arrayB[1];
				int hq3count = (int) arrayB[2];
				int hq4count = (int) arrayB[3];
				int hq5count = (int) arrayB[4];
				int hq6count = (int) arrayB[5];
				int hq7count = (int) arrayB[6];
				int hq8count = (int) arrayB[7];
				int hq9count = (int) arrayB[8];
				int hq10count = (int) arrayB[9];
				int hq11count = (int) arrayB[10];
				int hq12count = (int) arrayB[11];
				int year = maxMDId/1000;
				// int maxId;
				MainDataStatistic mds = new MainDataStatistic(qq1count,
						qq2count, qq3count, qq4count, qq5count, qq6count,
						qq7count, qq8count, qq9count, qq10count, qq11count,
						qq12count, qq13count, qq14count, qq15count, qq16count,
						qq17count, qq18count, qq19count, qq20count, qq21count,
						qq22count, qq23count, qq24count, qq25count, qq26count,
						qq27count, qq28count, qq29count, qq30count, qq31count,
						qq32count, qq33count, qq34count, qq35count, hq1count,
						hq2count, hq3count, hq4count, hq5count, hq6count,
						hq7count, hq8count, hq9count, hq10count, hq11count,
						hq12count, year, maxMDId% 1000);

				// System.out.println(mds);

				mainDataStatisticDao.save(mds);

			}
		}
	}

}
