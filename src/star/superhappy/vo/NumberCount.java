package star.superhappy.vo;


/**
 * 封装号码，出现次数
 * 或者某种类型，出现次数
 * @author Gang
 *
 */
public class NumberCount {

	private int number;//号码，或者某种类型，出现次数（x轴）
	private int count;//出现次数（y轴）
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public NumberCount(int number, int count) {
		super();
		this.number = number;
		this.count = count;
	}
	
	public NumberCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "NumberCount [number=" + number + ", count=" + count + "]";
	}
	
	
	
	
}
