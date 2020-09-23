package star.superhappy.pojo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 主数据表
 * @author Gang
 *
 */

public class MainData implements java.io.Serializable {

	private int id;//期号
	private int first;//第一位号码
	private int second;//第二位号码
	private int third;//第三位号码
	private int fourth;//第四位号码
	private int fifth;//第五位号码
	private int sixth;//第六位号码
	private int seventh;//第七位号码
	private int qhz;//前区和值
	private int hhz;//后区和值
	private String qjob;//前区奇偶比
	private String hjob;//后区奇偶比
	private String qxdb;//前区小大比
	private String hxdb;//后区小大比
	private Date date;//开奖日期
	private String remark;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getThird() {
		return third;
	}
	public void setThird(int third) {
		this.third = third;
	}
	public int getFourth() {
		return fourth;
	}
	public void setFourth(int fourth) {
		this.fourth = fourth;
	}
	public int getFifth() {
		return fifth;
	}
	public void setFifth(int fifth) {
		this.fifth = fifth;
	}
	public int getSixth() {
		return sixth;
	}
	public void setSixth(int sixth) {
		this.sixth = sixth;
	}
	public int getSeventh() {
		return seventh;
	}
	public void setSeventh(int seventh) {
		this.seventh = seventh;
	}
	public int getQhz() {
		return qhz;
	}
	public void setQhz(int qhz) {
		this.qhz = qhz;
	}
	public int getHhz() {
		return hhz;
	}
	public void setHhz(int hhz) {
		this.hhz = hhz;
	}
	public String getQjob() {
		return qjob;
	}
	public void setQjob(String qjob) {
		this.qjob = qjob;
	}
	public String getHjob() {
		return hjob;
	}
	public void setHjob(String hjob) {
		this.hjob = hjob;
	}
	public String getQxdb() {
		return qxdb;
	}
	public void setQxdb(String qxdb) {
		this.qxdb = qxdb;
	}
	public String getHxdb() {
		return hxdb;
	}
	public void setHxdb(String hxdb) {
		this.hxdb = hxdb;
	}
	
	@JSON(format="yyyy-MM-dd")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public MainData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainData(int id, int first, int second, int third, int fourth,
			int fifth, int sixth, int seventh, int qhz, int hhz, String qjob,
			String hjob, String qxdb, String hxdb, Date date, String remark) {
		super();
		this.id = id;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
		this.seventh = seventh;
		this.qhz = qhz;
		this.hhz = hhz;
		this.qjob = qjob;
		this.hjob = hjob;
		this.qxdb = qxdb;
		this.hxdb = hxdb;
		this.date = date;
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "MainData [id=" + id + ", first=" + first + ", second=" + second
				+ ", third=" + third + ", fourth=" + fourth + ", fifth="
				+ fifth + ", sixth=" + sixth + ", seventh=" + seventh
				+ ", qhz=" + qhz + ", hhz=" + hhz + ", qjob=" + qjob
				+ ", hjob=" + hjob + ", qxdb=" + qxdb + ", hxdb=" + hxdb
				+ ", date=" + date + ", remark=" + remark + "]";
	}
	
	
	
	
	
	
	
}