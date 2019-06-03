package com.test.dtjc.tdsc0002.action;

import com.test.dtjc.tdsc0000.common.SQLAdapter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.test.dtjc.tdsc0002.model.Tdsc0002Model01;

public class Tdsc0002Action extends ActionSupport{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//设置解码方式，对于简体中文，使用UTF-8解码
		String gdGuid=request.getParameter("gdGuid");//
		//gdGuid = "002895bf-5cda-4ee8-bd92-1d8aa873b022";
		String sql ="";
		//以下是划拨供应sql
		sql = sql +"SELECT"
			+ " A.XZQ_DM   AS XZQ_DM,"
			+ " A.XM_MC    AS XM_MC,"
			+ " A.GD_GUID  AS GD_GUID,"
			+ " A.PZ_WH    AS PZ_WH,"
			+ " A.GD_ZMJ   AS GD_ZMJ,"
			+ " A.GY_MJ    AS GY_MJ,"
			+ " A.XM_ZT    AS XM_ZT,"
			+ " A.PZ_RQ    AS PZ_RQ,"
			+ " A.ZD_GUID  AS ZD_GUID,"
			+ " A.PZ_JG    AS PZ_JG,"
			+ " A.GY_FS    AS GY_FS,"
			+ " A.ZD_BH    AS ZD_BH,"
			+ " A.TD_JB    AS TD_JB,"
			+ " A.KFLY_BZ  AS KFLY_BZ,"
			+ " A.DZ_BA_BH AS DZ_BA_BH,"
			+ " A.TZZT_XZ  AS TZZT_XZ,"
			+ " A.HY_FL    AS HY_FL,"
			+ " B.SYQR     AS SYQR"
			+ " FROM T_GDXM A,T_HBGY_KZ B" 
			+ " WHERE A.GD_GUID = B.GD_GUID" 
			+ " AND A.GD_GUID = '"+gdGuid+"'";
		List<Tdsc0002Model01> agricultural = sqlSessionTemplate.selectList("superSelect",new SQLAdapter(sql));
		Map<String,String> map = new HashMap<String,String>();
		//以下是出让合同查询用sql
		if(agricultural.size()==0) {
			sql = "";
			sql = sql + " SELECT " 
					  + " F.GYGG_GUID, "
					  + " F.BH, "
					  + " F.DZ_BA_BH, "
					  + " F.XM_MC,"
					  + " F.GY_FS,"
					  + " F.PZ_RQ,"
					  + " F.PZ_WH,"
					  + " F.TZZT_XZ,"
					  + " F.TD_JB ,"
					  + " F.HY_FL,"
					  + " F.GYGG_ZDBH,"
					  + " F.WGGZD_SM,"
					  + " D.* "
					+ " FROM  ( SELECT" 
					+ "           DISTINCT C.GYGG_GUID,"
					+ "           B.BH,"
					+ "           B.DZ_BA_BH,"
					+ "           B.XM_MC,"
					+ "           B.GY_FS,"
					+ "           B.PZ_RQ,"
					+ "           B.PZ_WH,"
					+ "           B.TZZT_XZ,"
					+ "           B.TD_JB ,"
					+ "           B.HY_FL,"
					+ "           A.GYGG_ZDBH,"
					+ "           A.WGGZD_SM"
					+ "         FROM "
					+ "           T_CRGY_KZ A," 
					+ "           T_GDXM B,"
					+ "           T_GYGG_ZD C"
					+ "         WHERE A.GD_GUID = B.GD_GUID"
					+ "           AND B.GD_GUID = '"+gdGuid 
					+ "'          AND C.GYGG_ZD_GUID = A.GYGG_ZD_GUID) F,T_GYGG D"
					+ "  WHERE F.GYGG_GUID = D.GYGG_GUID";

			agricultural = sqlSessionTemplate.selectList("superSelect",new SQLAdapter(sql));
		}
		for(int i=0;i<agricultural.size();i++) {
			map.clear();
			for (Map.Entry<String,Integer> entry:((Map<String, Integer>) agricultural.get(i)).entrySet()){
			    map.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		//Tdsc0002Model01 agricultural =sqlSessionTemplate.selectOne("selectAllocatingland",gdGuid);
		if(!agricultural.isEmpty()) {
			//项目名称
			request.setAttribute("xmMc", map.get("XM_MC"));
			//批准机关
			request.setAttribute("pzJg", map.get("PZ_JG"));
			//批准文号
			request.setAttribute("pfWh", map.get("PZ_WH") );
			String temp = "<a href=\"tdsc0002.action?method=execute&gdGuid="+map.get("PZ_WH")+"\" target=\"_blank\"><span >"+map.get("PZ_WH")+"</span></a>";
			request.setAttribute("pfWhLink", temp);
			//总面积
			request.setAttribute("gdZmj", map.get("GD_ZMJ"));
			//项目状态
			request.setAttribute("xmZt", map.get("XM_ZT"));
			//批准日期
			request.setAttribute("pzRq", map.get("PZ_RQ"));
	         //行政区代码
			request.setAttribute("xzqDm", map.get("XZQ_DM"));
	        //供应面积
			request.setAttribute("gyMj", map.get("GY_MJ"));
	        //供应方式
			request.setAttribute("gyFs", map.get("GY_FS"));
	        //宗地标识
			request.setAttribute("zdGuid", map.get("ZD_GUID"));
	        //项目状态
			request.setAttribute("xmZt", map.get("XM_ZT"));
	        //宗地编号
			request.setAttribute("zdBh", map.get("ZD_BH"));
	        //划拨建设用地使用权人
			request.setAttribute("hbSyqr", map.get("SYQR"));
	        //土地级别
			request.setAttribute("tdjb", map.get("TD_JB"));
	        //电子监管编号
			request.setAttribute("dzjgbh", map.get("DZ_BA_BH"));
	        //投资主体性质
			request.setAttribute("tzztxz", map.get("TZZT_XZ"));
	        //行业分类
			request.setAttribute("hyfl", map.get("HY_FL"));
		}
		return "success";
	}
}
