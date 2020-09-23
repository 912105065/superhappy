package star.superhappy.test;

import java.util.ArrayList;
import java.util.List;

public class NumberCombinationTest {
	
	List<String> qqResult=new ArrayList<String>();//前区最终号码，形式1-2-3-4-5
	
	public void numberQQTest() {
		// 前区，处理6个开奖号码
		//String qqNumber = "1,2,3,4,5,6";// 输入的后区号码1,2,3,4,5,6
		String qqNumber = "1,2,3,4,5,6,7";// 输入的后区号码1,2,3,4,5,6,7
		String[] qqStr= qqNumber.split(",");
		int[] qq = new int[qqStr.length];
		
		int maxCountQQ=35*35*35*35*35;//前区组合最大次数
		//System.out.println(maxCountQQ);
		//String[] qqResult =new String[maxCountQQ];//前区最终号码，形式1-2-3-4-5
		//List<String> qqResult=new ArrayList<String>();//前区最终号码，形式1-2-3-4-5
		// 将String转换成int
		for (int i = 0; i < qqStr.length; i++) {
			// System.out.println(hqStr[i]);
			qq[i] = Integer.parseInt(qqStr[i]);
			//System.out.println(qq[i]);
		}
		//前区组合计数
		int length=qq.length;//记录qq长度
		if(length==5){
			System.out.println("1注");
		}
		if(length==6){//前区六个号1,2,3,4,5,6，推算出6注前区号码
			int count=0;//组合数
			qqResult=qqConvert6(qqResult,qqStr);
			System.out.println("---进入号码个数等于6---");
			for(String str:qqResult){
				if(str!=null)
				System.out.println(str);
				count++;//组合树
			}
			//System.out.println("组合数："+count+"组");
			System.out.println("初始化，共："+qqResult.size()+"组");
		}
		
		
		
		if(length>6){//特殊处理
			System.out.println("---进入号码个数大于6---");
			
			//length=7,截取length大于6的，第7位，第8位
		    //初始化，先处理6个号码，1~6，即先处理前6个号码
			String[] qqStrNew=new String[6];
			qqStrNew[0]=qqStr[0];
			qqStrNew[1]=qqStr[1];
			qqStrNew[2]=qqStr[2];
			qqStrNew[3]=qqStr[3];
			qqStrNew[4]=qqStr[4];
			qqStrNew[5]=qqStr[5];//第6个号码
			System.out.println("第一次处理："+qqStrNew[0]+"-"+qqStrNew[1]+"-"+qqStrNew[2]+"-"+qqStrNew[3]+"-"+qqStrNew[4]+"-"+qqStrNew[5]);
			qqConvert6(qqResult,qqStrNew);
			System.out.println("初始化，出现的号码：");
			int count=0;//count记录号码组数
			for(String str:qqResult){
				if(str!=null){
					System.out.println(str);
					count++;
				}
			}
			
			//System.out.println("初始化，共："+count+"组"+"组");
			System.out.println("初始化，共："+qqResult.size()+"组");
			
			
			//读取大于第6位的号码，拼接已经组合完毕的qqResult
			for(int i=6;i<qqStr.length;i++){
				
				for(int j=0;j<qqResult.size();j++){
					//获取第7位，第8位号码。。。
					String addNumber=qqStr[i];//要加入的新号码
					System.out.println("加入的号码："+addNumber);
					//重新组合号码
					if(qqResult.get(j)!=null){
						System.out.println("qqResult中存储的号码："+qqResult.get(j));
						String[] oldQQStr= qqResult.get(j).split("-");//获取原先注入的开奖号码，格式{1,2,3,4,5}
						
							System.out.println("转换格式后"+oldQQStr[0]+"-"+oldQQStr[1]+"-"+oldQQStr[2]+"-"+oldQQStr[3]+"-"+oldQQStr[4]);
						
						String[] newQQStr=new String[6];//老的5位号码，加入1位新号码
						newQQStr[0]=oldQQStr[0];
						newQQStr[1]=oldQQStr[1];
						newQQStr[2]=oldQQStr[2];
						newQQStr[3]=oldQQStr[3];
						newQQStr[4]=oldQQStr[4];
						newQQStr[5]=addNumber;//要加入的新号码
					    System.out.println("加入新号后的组合号码："+newQQStr[0]+","+newQQStr[1]+","+newQQStr[2]+","+newQQStr[3]+","+newQQStr[4]+","+newQQStr[5]);
					
						qqConvert6V2(newQQStr);
					}
					
				}
			}
			/*System.out.println("最终组合情况：");
			for(String str:qqResult){
				if(str!=null){
					System.out.println(str);
					count++;
				}
			}
			System.out.println("最终，共："+count+"组");*/
			
			
			
			
		}
		
		
	
	}
	
	public List<String> qqConvert6(List<String> qqResult,String[] qqStr){
		System.out.println("---进入qqConvert6---");
		//1,2,3,4,5的情况
		qqResult.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]);
		//qqResult[0]=qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4];
		//System.out.println("1,2,3,4,5的情况："+qqResult[0]);
		//1,2,3,4,6的情况
		qqResult.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[5]);
		//qqResult[1]=qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[5];
		//System.out.println("1,2,3,4,6的情况："+qqResult[0]);
		//1,3,4,5,6的情况
		qqResult.add(qqStr[0]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);
		//qqResult[2]=qqStr[0]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5];
		//System.out.println("1,3,4,5,6的情况："+qqResult[0]);
		//1,2,4,5,6的情况
		qqResult.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);
		//qqResult[3]=qqStr[0]+"-"+qqStr[1]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5];
		//System.out.println("1,2,4,5,6的情况："+qqResult[0]);
		//1,2,3,5,6的情况
		qqResult.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[4]+"-"+qqStr[5]);
		//qqResult[4]=qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[4]+"-"+qqStr[5];
		//System.out.println("1,2,3,5,6的情况："+qqResult[0]);
		//2,3,4,5,6的情况
		qqResult.add(qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);
		//qqResult[5]=qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5];
		//System.out.println("2,3,4,5,6的情况："+qqResult[0]);
		for(String str:qqResult){
			if(str!=null)
			System.out.println(str);
		}
		return qqResult;
	}
	
	public List<String> qqConvert6V2(String[] qqStr){
		System.out.println("---进入qqConvert6V2---");
		List<String> tempLi=new ArrayList<String>();
		//1,2,3,4,5的情况
		tempLi.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]);
	
		//1,2,3,4,6的情况
		tempLi.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[5]);
	
		//1,3,4,5,6的情况
		tempLi.add(qqStr[0]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);

		//1,2,4,5,6的情况
		tempLi.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);
	
		//1,2,3,5,6的情况
		tempLi.add(qqStr[0]+"-"+qqStr[1]+"-"+qqStr[2]+"-"+qqStr[4]+"-"+qqStr[5]);

		//2,3,4,5,6的情况
		tempLi.add(qqStr[1]+"-"+qqStr[2]+"-"+qqStr[3]+"-"+qqStr[4]+"-"+qqStr[5]);
	
		qqResult.addAll(tempLi);
		for(String str:qqResult){
			if(str!=null)
			System.out.println(str);
		}
		return qqResult;
	}
	
	public static void main(String[] args) {
		NumberCombinationTest test=new NumberCombinationTest();
		test.numberQQTest();
	}
}
