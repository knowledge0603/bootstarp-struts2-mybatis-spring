package com.test.dtjc.tdsc0004.model;

public class Tdsc0004Model {
	//行号
	private String 	rowNum;	
	//行政区名称
	private String 	xzMc;
	//公示标题
	private String 	gsBt;
	//供应方式
	private String 	gyFs;
	//公示开始时间
	private String 	gsKsSj;
	//公示结束时间
	private String gsJsSj;
	//创建人	
	private String 	cJr;
	//截止日期
	private String 	jzrq;
	//项目状态
	private String 	xmZt;
	//成交公示GUID
	private String 	cjgsGuid;
	//序号
	static int nextID = 1;
	//序号
	private int id;
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getXzMc() {
		return xzMc;
	}
	public void setXzMc(String xzMc) {
		this.xzMc = xzMc;
	}
	public String getGsBt() {
		return gsBt;
	}
	public void setGsBt(String gsBt) {
		this.gsBt = gsBt;
	}
	public String getGyFs() {
		return gyFs;
	}
	public void setGyFs(String gyFs) {
		this.gyFs = gyFs;
	}
	public String getGsKsSj() {
		return gsKsSj;
	}
	public void setGsKsSj(String gsKsSj) {
		this.gsKsSj = gsKsSj;
	}
	public String getGsJsSj() {
		return gsJsSj;
	}
	public void setGsJsSj(String gsJsSj) {
		this.gsJsSj = gsJsSj;
	}
	public String getcJr() {
		return cJr;
	}
	public void setcJr(String cJr) {
		this.cJr = cJr;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public String getXmZt() {
		return xmZt;
	}
	public void setXmZt(String xmZt) {
		this.xmZt = xmZt;
	}
	public String getCjgsGuid() {
		return cjgsGuid;
	}
	public void setCjgsGuid(String cjgsGuid) {
		this.cjgsGuid = cjgsGuid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public Tdsc0004Model(
		 String  rowNum,
		 String  xzMc,
		 String  gsBt,
		 String  gyFs,
		 String  gsKsSj,
		 String  gsJsSj,
		 String  jzrq,
		 String  cJr,		     
		 String  xmZt,
		 String  cjgsGuid		 
    )
    {
        id = nextID++;
        this.rowNum=rowNum;
		this.xzMc=xzMc;
        this.gsBt=gsBt;
        this.gyFs=gyFs;
        this.gsKsSj=gsKsSj;
		this.gsJsSj=gsJsSj;
		this.jzrq=jzrq;
		this.cJr=cJr;
		this.xmZt=xmZt; 
		this.cjgsGuid=cjgsGuid;		
    }
}
