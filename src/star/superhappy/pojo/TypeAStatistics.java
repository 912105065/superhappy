package star.superhappy.pojo;

/**
 * Typeastatistics entity. @author MyEclipse Persistence Tools
 */

public class TypeAStatistics implements java.io.Serializable {

	// Fields

	private Integer id;//id编号
	private Integer typeAs1;//typeA统计值1
	private Integer typeAs2;//typeA统计值2
	private Integer typeAs3;//typeA统计值3
	private Integer typeAs4;//typeA统计值4
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeAs1() {
		return typeAs1;
	}
	public void setTypeAs1(Integer typeAs1) {
		this.typeAs1 = typeAs1;
	}
	public Integer getTypeAs2() {
		return typeAs2;
	}
	public void setTypeAs2(Integer typeAs2) {
		this.typeAs2 = typeAs2;
	}
	public Integer getTypeAs3() {
		return typeAs3;
	}
	public void setTypeAs3(Integer typeAs3) {
		this.typeAs3 = typeAs3;
	}
	public Integer getTypeAs4() {
		return typeAs4;
	}
	public void setTypeAs4(Integer typeAs4) {
		this.typeAs4 = typeAs4;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "TypeAStatistics [id=" + id + ", typeAs1=" + typeAs1
				+ ", typeAs2=" + typeAs2 + ", typeAs3=" + typeAs3
				+ ", typeAs4=" + typeAs4 + "]";
	}
	public TypeAStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TypeAStatistics(Integer id, Integer typeAs1, Integer typeAs2,
			Integer typeAs3, Integer typeAs4) {
		super();
		this.id = id;
		this.typeAs1 = typeAs1;
		this.typeAs2 = typeAs2;
		this.typeAs3 = typeAs3;
		this.typeAs4 = typeAs4;
	}

	
	
	
}