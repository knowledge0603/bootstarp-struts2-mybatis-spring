package com.test.dtjc.tdsc0004.action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext; //key   
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.opensymphony.xwork2.ActionSupport;

import com.test.dtjc.tdsc0000.common.SQLAdapter;
import com.test.dtjc.tdsc0004.model.Tdsc0004Model;
import com.test.dtjc.tdsc0000.common.DataTablesParamUtility;
import com.test.dtjc.tdsc0000.common.JQueryDataTableParamModel;

public class Tdsc0004Action extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings("unchecked")
	public List<Tdsc0004Model> findRecord() throws SQLException {
		String sql = "";
		sql = sql  
			   +" SELECT " 
			   +"   B.XZQMC,"
			   +"   A.GSBT,"
			   +"   C.GY_MC,"
			   +"   A.GS_SJ_S,"
			   +"   A.GS_SJ_E,"
			   +"   A.JZRQ,"
			   +"   A.CREATE_USER,"
		       +"   D.XMMC,"
		       +"   A.CJGS_GUID"			   
			   +" FROM"  
			   +"   T_CJGS A,"
			   +"   TDLY.MY_XZQ B,"
			   +"   TDLY.ENUM_CJGS_GYFS C,"
			   +"   TDLY.ENUM_XMZT D"
			   +" WHERE" 
			   +"   A.XZQ_DM = B.XZQDM"
			   +"   AND C.GY_FS = A.GY_FS"
			   +"   AND A.XM_ZT = D.XMZT";
		List<Tdsc0004Model> agriculturalList=  sqlSessionTemplate.selectList("superSelect",new SQLAdapter(sql));
		Map<String,String> map = new HashMap<String,String>();
		final List<Tdsc0004Model> AgriculturalModelData = new LinkedList<Tdsc0004Model>();
		for(int i=0;i<agriculturalList.size();i++) {
			map.clear();
			for (Map.Entry<String,Integer> entry:((Map<String, Integer>) agriculturalList.get(i)).entrySet()){
			    map.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
			AgriculturalModelData.add(new Tdsc0004Model(
				String.valueOf(i),
				map.get("XZQMC"),
				map.get("GSBT"),
				map.get("GY_MC"),
				map.get("GS_SJ_S"),
				map.get("GS_SJ_E"),
				map.get("JZRQ"),
				map.get("CREATE_USER"),
				map.get("XMMC"),
				map.get("CJGS_GUID")				
			));
		}
		return AgriculturalModelData;
	}

	Integer rownum=50;
    /// <summary>
    /// Index of the column that is used for sorting
    /// </summary>
	public String execute() throws IOException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		request.getParameter("parmeter1");
		request.getParameter("parmeter2");
		String xmmc = ServletActionContext.getRequest().getParameter("xmmc");
		//string temp = request["parmeter1"];
		JQueryDataTableParamModel param = DataTablesParamUtility
				.getParam(request);
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords; // value will be set when code filters
		JsonArray data = new JsonArray(); // data that will be shown in the
											// table
		//iTotalRecords = findCount();
		iTotalRecords =sqlSessionTemplate.selectOne("selectAgriculturalCount",rownum);
		//iTotalRecords =200;
		List<Tdsc0004Model> agriculturalModel = new LinkedList<Tdsc0004Model>();
		for (Tdsc0004Model c : findRecord()) {
			if (c.getcJr().toLowerCase().contains(param.sSearch.toLowerCase())
				|| c.getGsBt().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getGsJsSj().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getGsKsSj().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getGyFs().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getJzrq().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getJzrq().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getRowNum().toLowerCase()
						.contains(param.sSearch.toLowerCase())
				|| c.getXmZt().toLowerCase()
						.contains(param.sSearch.toLowerCase()
								)
				|| c.getXzMc().toLowerCase().contains(param.sSearch.toLowerCase()
						)
				)
			{
				agriculturalModel.add(c); //
			}
		}
		iTotalDisplayRecords = agriculturalModel.size();
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		Collections.sort(agriculturalModel, new Comparator<Tdsc0004Model>() {
			@Override
			public int compare(Tdsc0004Model c1, Tdsc0004Model c2) {
				switch (sortColumnIndex) {
				case 1:
					return c1.getcJr().compareTo(c2.getcJr()) * sortDirection;
				case 2:
					return c1.getGsBt().compareTo(c2.getGsBt())* sortDirection;
				case 3:
					return c1.getGsJsSj().compareTo(c2.getGsJsSj()) * sortDirection;
				case 4:
					return c1.getGsKsSj().compareTo(c2.getGsKsSj()) * sortDirection;
				case 5:
					return c1.getGyFs().compareTo(c2.getGyFs()) * sortDirection;
				case 6:
					return c1.getJzrq().compareTo(c2.getJzrq()) * sortDirection;
				case 7:
					return c1.getRowNum().compareTo(c2.getRowNum()) * sortDirection;
				case 8:
					return c1.getXmZt().compareTo(c2.getXmZt()) * sortDirection;
				case 9:
					return c1.getXzMc().compareTo(c2.getXzMc()) * sortDirection;
				}
				return 0;
			}
		});
		if (agriculturalModel.size() < param.iDisplayStart + param.iDisplayLength) {
			agriculturalModel = agriculturalModel.subList(param.iDisplayStart, agriculturalModel.size());
		} else {
			agriculturalModel = agriculturalModel.subList(param.iDisplayStart,
					param.iDisplayStart + param.iDisplayLength);
		}
		try {
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords",
					iTotalDisplayRecords);
			for (Tdsc0004Model c : agriculturalModel) {
				JsonArray row = new JsonArray();
				//rownum不为空添加get值,为空添加"-"
				if(c.getRowNum()!= null&& c.getRowNum().length() != 0) {
					row.add(new JsonPrimitive(c.getRowNum()));	
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getXzMc()!= null&& c.getXzMc().length() != 0) {
					row.add(new JsonPrimitive(c.getXzMc()));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getGsBt()!= null&& c.getGsBt().length() != 0){
					String temp = "<a href=\"tdsc0005.action?method=execute&cjgsGuid="+c.getCjgsGuid()+"\" target=\"_blank\"><span >"+c.getGsBt()+"</span></a>";
					row.add(new JsonPrimitive(temp));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getGyFs()!= null&& c.getGyFs().length() != 0) {
					row.add(new JsonPrimitive(c.getGsBt()));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getGsKsSj()!= null&& c.getGsKsSj().length() != 0) {
					row.add(new JsonPrimitive(c.getGsKsSj()));	
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getGsJsSj()!= null&& c.getGsJsSj().length() != 0) {
					row.add(new JsonPrimitive(c.getGsJsSj()));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getJzrq()!= null&& c.getJzrq().length() != 0) {
					row.add(new JsonPrimitive(c.getJzrq()));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getcJr()!= null&& c.getcJr().length() != 0){
					row.add(new JsonPrimitive(c.getcJr()));
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				if(c.getXmZt()!= null&& c.getXmZt().length() != 0) {
					row.add(new JsonPrimitive(c.getXmZt()));	
				}else {
					row.add(new JsonPrimitive("-"));	
				}
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}
		return null;
	}
}
