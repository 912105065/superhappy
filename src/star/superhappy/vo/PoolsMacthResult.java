package star.superhappy.vo;


/**
 * 地址池匹配信息类
 * @author Gang
 *
 */
public class PoolsMacthResult {
	
	private String pools;//pools池中信息
	private int count;//击中个数
	private String result;//击中结果
	
	
	
	
	
	
	public String getPools() {
		return pools;
	}
	public void setPools(String pools) {
		this.pools = pools;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public PoolsMacthResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolsMacthResult(String pools, int count, String result) {
		super();
		this.pools = pools;
		this.count = count;
		this.result = result;
	}
	
	
	@Override
	public String toString() {
		return "PoolsMacthResult [pools=" + pools + ", count=" + count
				+ ", result=" + result + "]";
	}
	
	
	
  
	
	
}
