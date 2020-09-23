package star.superhappy.pojo;

/**
 * Yilouzhistatisticv2 entity. @author MyEclipse Persistence Tools
 */

public class YiLouZhiStatisticV2 implements java.io.Serializable {

	

	private Integer id;//id号
	private Integer noValue;//号码值
	private String  qhType;//前区、后区状态，前区qh，后区hh
	private Integer maxYlvalueNowYear;//当年该号码最大遗漏值
	private Integer maxYlvalue;//历史该号码最大遗漏值
	private Integer nowYlvalue;//该号码当前遗漏值
	private Integer nearYlOneYlvalue;//该号码前一期出现遗漏值
	private Integer nearYlTwoYlvalue;//该号码前二期出现遗漏值
	private Integer nearYlThreeYlvalue;//该号码前三期出现遗漏值
	private Integer nearYlFourYlvalue;//该号码前四期期出现遗漏值
	private Integer nearYlFiveYlvalue;//该号码前五期出现遗漏值
	private Integer nowId;//当前期号
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNoValue() {
		return noValue;
	}
	public void setNoValue(Integer noValue) {
		this.noValue = noValue;
	}
	public String getQhType() {
		return qhType;
	}
	public void setQhType(String qhType) {
		this.qhType = qhType;
	}
	public Integer getMaxYlvalueNowYear() {
		return maxYlvalueNowYear;
	}
	public void setMaxYlvalueNowYear(Integer maxYlvalueNowYear) {
		this.maxYlvalueNowYear = maxYlvalueNowYear;
	}
	public Integer getMaxYlvalue() {
		return maxYlvalue;
	}
	public void setMaxYlvalue(Integer maxYlvalue) {
		this.maxYlvalue = maxYlvalue;
	}
	public Integer getNowYlvalue() {
		return nowYlvalue;
	}
	public void setNowYlvalue(Integer nowYlvalue) {
		this.nowYlvalue = nowYlvalue;
	}
	public Integer getNearYlOneYlvalue() {
		return nearYlOneYlvalue;
	}
	public void setNearYlOneYlvalue(Integer nearYlOneYlvalue) {
		this.nearYlOneYlvalue = nearYlOneYlvalue;
	}
	public Integer getNearYlTwoYlvalue() {
		return nearYlTwoYlvalue;
	}
	public void setNearYlTwoYlvalue(Integer nearYlTwoYlvalue) {
		this.nearYlTwoYlvalue = nearYlTwoYlvalue;
	}
	public Integer getNearYlThreeYlvalue() {
		return nearYlThreeYlvalue;
	}
	public void setNearYlThreeYlvalue(Integer nearYlThreeYlvalue) {
		this.nearYlThreeYlvalue = nearYlThreeYlvalue;
	}
	public Integer getNearYlFourYlvalue() {
		return nearYlFourYlvalue;
	}
	public void setNearYlFourYlvalue(Integer nearYlFourYlvalue) {
		this.nearYlFourYlvalue = nearYlFourYlvalue;
	}
	public Integer getNearYlFiveYlvalue() {
		return nearYlFiveYlvalue;
	}
	public void setNearYlFiveYlvalue(Integer nearYlFiveYlvalue) {
		this.nearYlFiveYlvalue = nearYlFiveYlvalue;
	}
	public Integer getNowId() {
		return nowId;
	}
	public void setNowId(Integer nowId) {
		this.nowId = nowId;
	}
	
	
	
	
	
	public YiLouZhiStatisticV2() {
		super();
	}
	
	
	
	
	
	public YiLouZhiStatisticV2(Integer id, Integer noValue, String qhType,
			Integer maxYlvalueNowYear, Integer maxYlvalue, Integer nowYlvalue,
			Integer nearYlOneYlvalue, Integer nearYlTwoYlvalue,
			Integer nearYlThreeYlvalue, Integer nearYlFourYlvalue,
			Integer nearYlFiveYlvalue, Integer nowId) {
		super();
		this.id = id;
		this.noValue = noValue;
		this.qhType = qhType;
		this.maxYlvalueNowYear = maxYlvalueNowYear;
		this.maxYlvalue = maxYlvalue;
		this.nowYlvalue = nowYlvalue;
		this.nearYlOneYlvalue = nearYlOneYlvalue;
		this.nearYlTwoYlvalue = nearYlTwoYlvalue;
		this.nearYlThreeYlvalue = nearYlThreeYlvalue;
		this.nearYlFourYlvalue = nearYlFourYlvalue;
		this.nearYlFiveYlvalue = nearYlFiveYlvalue;
		this.nowId = nowId;
	}
	@Override
	public String toString() {
		return "YiLouZhiStatisticv2 [id=" + id + ", noValue=" + noValue
				+ ", qhType=" + qhType + ", maxYlvalueNowYear="
				+ maxYlvalueNowYear + ", maxYlvalue=" + maxYlvalue
				+ ", nowYlvalue=" + nowYlvalue + ", nearYlOneYlvalue="
				+ nearYlOneYlvalue + ", nearYlTwoYlvalue=" + nearYlTwoYlvalue
				+ ", nearYlThreeYlvalue=" + nearYlThreeYlvalue
				+ ", nearYlFourYlvalue=" + nearYlFourYlvalue
				+ ", nearYlFiveYlvalue=" + nearYlFiveYlvalue + ", nowId="
				+ nowId + "]";
	}
	
	
	
	
	
	
}