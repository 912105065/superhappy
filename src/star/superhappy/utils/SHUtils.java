package star.superhappy.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

import star.superhappy.pojo.YiLouData;

public class SHUtils {

	
	//根据传入的a和b的值，输出为字符串a-b
	public static String hqConvertCombination(int a,int b){
		String temp1=String.valueOf(a);
		String temp2=String.valueOf(b);
		
		return temp1+"-"+temp2;
	}
	
	
	//后区012路组合转换
	public static String hqConvert012(int a,int b){
		
		return Convert012(a)+Convert012(b);
	}
	
	//后区号码求和
	public static int hqSum(int a,int b){
		return a+b;
	}
	
	//后区号码求跨度
	public static int hqInterval(int a,int b){
		return b-a;
	}
	
	
	//根据输入的year，转换成year的前一年的最后一天，格式yyyy-MM-dd
	//year=2018，则转换成2017-12-31
	public static String ToYearPrevious(int year){
		int temp=year-1;
		String y=String.valueOf(temp)+"-12-31";
		return y;
	}
	
	//根据输入的year，转换成year的后一年的第一天，格式yyyy-MM-dd
		//year=2018，则转换成2019-01-01
		public static String ToYearNext(int year){
			int temp=year+1;
			String y=String.valueOf(temp)+"-01-01";
			return y;
		}
	
	//单个号码012路分析转换
	public static String Convert012(int number){
		if(number%3==0){
			return String.valueOf(0);
		}
		if(number%3==1){
			return String.valueOf(1);
		}
		if(number%3==2){
			return String.valueOf(2);
		}
		return null;
	}
	
	
	//遗漏表，前区a[][]赋值
	public static int[][] copyToArrayA(YiLouData yiLouData){
			
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
		}
		
		//遗漏表，后区b[][]赋值
		public  static int[][] copyToArrayB(YiLouData yiLouData){
			
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
			b[6][1]=yiLouData.getHqNo7();
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
		}

		
		//将pools数据转换诚String
		public static String poolsDataToString(List<Integer> pools) {

			// pools信息，号码
			StringBuffer strPools = new StringBuffer();
			for (int i = 0; i < pools.size(); i++) {
				strPools.append(pools.get(i));
				strPools.append(",");
			}
			String newStrPools = null;
			if (strPools != null && !strPools.equals("")) {
				int index1 = strPools.lastIndexOf(",");
				newStrPools = strPools.substring(0, index1);
			}

			return strPools.toString();
		}
		
		
}
