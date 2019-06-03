

//计算天数差的函数，通用  
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays  
    aDate  =  sDate1.split("-")  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式  
    aDate  =  sDate2.split("-")  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
    return  iDays  
}

function WinOpen() {
	mesg = open("cnrose", "DisplayWindow");
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
	eprnstr = "<!--endprint-->";
	prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
	prnhtml = prnhtml
			+ "<style> @media Print { .Noprn { DISPLAY: none }}</style><div><center><input class=\"Noprn\" type=\"button\" align=\"center\" onclick=\"window.print();\" value=\"打印\"></center></div>";
	mesg.document.write(prnhtml);
	mesg.document.close();
}
function exportExcel() {
	if(document.getElementById("dengjiriqifrom").value ==""){
		alert("请选择开始登记日期！");
		return;
	}
	if(document.getElementById("dengjiriqito").value ==""){
		alert("请选择结束登记日期！");
		return;
	}
	var days = DateDiff(document.getElementById("dengjiriqito").value,  document.getElementById("dengjiriqifrom").value);
	if(parseInt(days)>30){
		alert("请选择登记日期小于一个月的时间！");
		return;
	}
	if(document.getElementById("dengjiriqito").value< document.getElementById("dengjiriqifrom").value){
		alert("结束日期应大于开始日期，请重新选择日期！");
		return;
	}
	document.forms[0].action = "tdcb633export.action?method=execute";
	document.forms[0].submit();
}

//调用父页面定位地图
function createMarkerNSC(smidStr) {
	parent.createMarkerNSC(smidStr); 
	parent.layerMinNSC();
}
function query() {
	//var username = parent.document.getElementById('username').value;
	/*if(document.getElementById("pfrqFrom").value ==""){
		alert("请选择开始批复日期！");
		return;
	}
	if(document.getElementById("pfrqTo").value ==""){
		alert("请选择结束批复日期！");
		return;
	}
	var days = DateDiff(document.getElementById("pfrqTo").value,  document.getElementById("pfrqFrom").value);
	if(parseInt(days)>30){
		alert("请选择批复日期小于一个月的时间！");
		return;
	}
	if(document.getElementById("pfrqTo").value< document.getElementById("pfrqFrom").value){
		alert("结束日期应大于开始日期，请重新选择日期！");
		return;
	}*/
	seachData();
	

	}
	function seachData() {
		var columns=[
		    {title:"序号"},
		    {title:"行政区"},
		    {title:"公示标题"},
		    {title:"供应方式"},
		    {title:"公示开始时间"},
		    {title:"公示结束时间"},
		    {title:"截止日期"},
		    {title:"创建人"},
		    {title:"项目状态"},
		    ];
	 $("#example").dataTable({
	        "scrollX": true,
	        "destroy": true,
            "bServerSide": true,
            "sAjaxSource": "tdsc0004.action?method=execute",
            "bProcessing": true,
            "language": {  
                "sProcessing": "处理中...",  
                "sLengthMenu": "显示 _MENU_ 项结果",  
                "sZeroRecords": "没有匹配结果",  
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",  
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",  
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",  
                "sInfoPostFix": "",  
                "sSearch": "搜索:",  
                "sUrl": "",  
                "sEmptyTable": "表中数据为空",  
                "sLoadingRecords": "载入中...",  
                "sInfoThousands": ",",  
                "oPaginate": {  
                    "sFirst": "首页",  
                    "sPrevious": "上页",  
                    "sNext": "下页",  
                    "sLast": "末页"  
                },  
                "oAria": {  
                    "sSortAscending": ": 以升序排列此列",  
                    "sSortDescending": ": 以降序排列此列"  
                }  
            },
            "sPaginationType": "full_numbers",
            "columnDefs": [ {
			    "targets": "_all",
			    "defaultContent": "-"
			  } ],
			columns: columns,
            "fnServerParams": function (aoData) {  
                 aoData.push({  
                     name: "xmmc",  
                     value: $("#xmmc").val()  
                 },  
                 {  
                     name: "xzq",  
                     value: ($("#xzq").val())  
                 },  
                 {  
                     name: "pfwh",  
                     value: ($("#pfwh").val())  
                 },  
                 {  
                     name: "cjr",  
                     value: ($("#cjr").val())  
                 },  
                     {  
                     name: "pzdw",  
                     value: ($("#pzdw").val())  
                 }, 
                 {  
                     name: "xmzt",  
                     value: ($("#xmzt").val())  
                 },  
                 {  
                      name: "xmlx",  
                      value: ($("#xmlx").val())  
                 }
                ); }
        });
	}

