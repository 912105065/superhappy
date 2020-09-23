package star.superhappy.pojo;

public class XDDistribution implements java.io.Serializable{
	
	private Integer id;
	private int QQ1;
	private int QQ2;
	private int QQ3;
	private int HQ1;
	private int HQ2;
	private int HQ3;

	
	private MainData mainData;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getQQ1() {
		return QQ1;
	}


	public void setQQ1(int qQ1) {
		QQ1 = qQ1;
	}


	public int getQQ2() {
		return QQ2;
	}


	public void setQQ2(int qQ2) {
		QQ2 = qQ2;
	}


	public int getQQ3() {
		return QQ3;
	}


	public void setQQ3(int qQ3) {
		QQ3 = qQ3;
	}


	public int getHQ1() {
		return HQ1;
	}


	public void setHQ1(int hQ1) {
		HQ1 = hQ1;
	}


	public int getHQ2() {
		return HQ2;
	}


	public void setHQ2(int hQ2) {
		HQ2 = hQ2;
	}


	public int getHQ3() {
		return HQ3;
	}


	public void setHQ3(int hQ3) {
		HQ3 = hQ3;
	}


	public MainData getMainData() {
		return mainData;
	}


	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}


	public XDDistribution() {
		super();
		// TODO Auto-generated constructor stub
	}


	public XDDistribution(Integer id, int qQ1, int qQ2, int qQ3, int hQ1,
			int hQ2, int hQ3, MainData mainData) {
		super();
		this.id = id;
		QQ1 = qQ1;
		QQ2 = qQ2;
		QQ3 = qQ3;
		HQ1 = hQ1;
		HQ2 = hQ2;
		HQ3 = hQ3;
		this.mainData = mainData;
	}


	@Override
	public String toString() {
		return "XDDistribution [id=" + id + ", QQ1=" + QQ1 + ", QQ2=" + QQ2
				+ ", QQ3=" + QQ3 + ", HQ1=" + HQ1 + ", HQ2=" + HQ2 + ", HQ3="
				+ HQ3 + ", mainData=" + mainData + "]";
	}
	
	
	
	
	
    

}
