# bootstarp-struts2-mybatis-spring


1、项目中多个表查询，嵌套sql语句，聚合函数等，致使拼写的sql直接返回结果集合。
2、以上场合mybatis，mapper配置使用起来，配置繁琐，一对一，一对多，多对多关系需要考虑进去，类似需要以下的配置

```
		<association property="allocatinglandTable"  column="GD_GUID" javaType="com.test.dtjc.tdsc0002.model.Tdsc0002Model02">
			<!-- id：关联查询用户的唯 一标识
			column：指定唯 一标识用户信息的列
			javaType：映射到user的哪个属性
			 -->
			<id column="GD_GUID" property="GD_GUID"/>
	        <!--使用权人-->			
			<result column="SYQR" property="SYQR"/>
		</association>
```
虽然代码写的少了，配置麻烦了。背着抱着一样重。
3、这种场合下，考虑直接执行sql，又不想用原始的jdbc来操作。
4、给予以上条件，想到mybatis直接拼写sql即原生sql返回结果
     mapper文件 xml的配置
```
	<select id="superSelect" parameterType="String" resultType="java.util.LinkedHashMap" fetchSize="1000"> 
		${sql} 
	</select>

```
注意，这里 **resultType="java.util.LinkedHashMap"** 
代码中这样调用
		List<Tdsc0002Model01> agriculturalList=  sqlSessionTemplate.selectList("superSelect",**new SQLAdapter(sql));**
注意粗体部分
typeAliases中加入
 

     	<typeAlias alias="sqladapter" type="com.test.dtjc.tdsc0000.common.SQLAdapter" />



其中 SQLAdapter  这样写

```
public class SQLAdapter {  
    String sql;  
  
    public SQLAdapter(String sql) {  
        this.sql = sql;  
    }  
  
    public String getSql() {  
        return sql;  
    }  
  
    public void setSql(String sql) {  
        this.sql = sql;  
    }  
}

```

返回的结果集这样处理

```
        String sql = "这里拼写复杂的sql语句，多表嵌套，聚合函数等";
		List<Tdsc0002Model01> agriculturalList=  sqlSessionTemplate.selectList("superSelect",new SQLAdapter(sql));
		Map<String,String> map = new HashMap<String,String>();
		final List<Tdsc0003Model> AgriculturalModelData = new LinkedList<Tdsc0003Model>();
		for(int i=0;i<agriculturalList.size();i++) {
			map.clear();
			for (Map.Entry<String,Integer> entry:((Map<String, Integer>) agriculturalList.get(i)).entrySet()){
			    map.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
			AgriculturalModelData.add(new Tdsc0003Model(
				String.valueOf(i),
				map.get("XZQ_DM"),
				map.get("XM_MC"),
				map.get("XMLX"),
				map.get("PZ_WH"),
				map.get("GD_ZMJ"),
				map.get("GY_MJ"),
				map.get("ZTMC"),
				map.get("PZ_RQ"),
				map.get("CREATE_USER"),
				map.get("GD_GUID")  
			));
		}
		return AgriculturalModelData;
	}

```
注意  代码中 map  的 put方法

```
			for (Map.Entry<String,Integer> entry:((Map<String, Integer>) agriculturalList.get(i)).entrySet()){
			    map.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
```

和 map 的 get方法

```
map.get("CREATE_USER"),
map.get("GD_GUID")  
```
