/**
 * Created by wuxiutong on 15/8/23.
 */
function get_current_whrq() {
    var now = new Date();//.SimpleDateFormat("yyyy-MM-dd");
    //alert(now);
    var tds = $.CurrentNavtab.find("#datatable_rpt_whrq").find("tr td[name='jzrq'] ")//css('background-color','red');
    $.each(tds, function (index, v) {
        var values = $(tds[index]).text().trim();
        var dateValue;
        try {
            dateValue = new Date(values);
        } catch (e) {
            $(tds[index]).css('background-color', 'red');
            return;
        }
        //alert("年度：" + dateValue.getYear() + "月份：" + dateValue.getMonth())
        //如果获取的日期大于等于当前日期年度
        if (dateValue.getYear() == now.getYear()) {
            //alert("年度相等 ");
            //当前获取的日期大于等于当前日期月份一个月的话
            if (dateValue.getMonth() == now.getMonth() + 1) {
                //alert("月份相差一月 ");
                $(tds[index]).css('background-color', 'yellow');
            } else if (dateValue.getMonth() <= now.getMonth()) {
                //alert("月份已经超过 ");
                $(tds[index]).css('background-color', 'red');
            } else if (dateValue.getMonth() > now.getMonth()) {
                //alert("月份小余");
                $(tds[index]).css('background-color', 'green');
            }
        } else if (dateValue.getYear() > now.getYear()) {
            //alert("当前年度小余");
            $(tds[index]).css('background-color', 'green');
        } else if (dateValue.getYear() < now.getYear()) {
            //alert("当前年度大于 ");
            $(tds[index]).css('background-color', 'red');
        }
    });
}
var datagrid_billBox_all = $('#datatable_rpt_whrq').datagrid({
    gridTitle: '单位维护日期',
    showToolbar: false,
    toolbarItem: 'all',
    local: 'local',
    dataUrl: 'Get_RPT_WHRQ?khjl=&keyword=&dqxx=999988889999',
    dataType: 'json',
    loadType: 'get',
    sortAll: true,
    filterAll: true,
    columns: [
        {
            name: 'khdmmc',
            label: '客户信息',
            align: 'left',
            width: 220
        },  {
            name: 'qsrq',
            label: '维护起始日期',
            align: 'left',
            width: 100
        }, {
            name: 'jzrq',
            label: '维护截至日期',
            align: 'left',
            width: 100
        }, {
            name: 'ssdq',
            label: '所属地区',
            align: 'left',
            width: 120
        },{
            name: 'khjl',
            label: '客户经理',
            align: 'left',
            width: 120
        },{
            name: 'khlx',
            label: '客户类型',
            align: 'left',
            width: 120
        },{
            name: 'updateDjh',
            label: '更新标记(单据号)',
            align: 'left',
            width: 120
        }, {
            name: 'lastUpdate',
            label: '更新日期',
            align: 'left',
            width: 150
        }, {
            name: 'updateUser',
            label: '更新职员',
            align: 'left',
            width: 120
        }
    ],
    hiddenFields: [{name: 'deptco222de'}],
    editUrl: 'ajaxDone1.html',
    showCheckboxCol: true,
    showEditBtnsCol: true,
    linenumberAll: false,
    fullGrid: false,
    columnMenu: false,
    columnShowhide: false,
    columnFilter: false,
    columnLock: false,
    paging: false,
    showToolbar: false,
    toolbarItem: 'all'
});
//刷新按钮
$.CurrentNavtab.find("#btn_whrq_refresh").bind("click", function (e) {
    $.CurrentNavtab.find("#datatable_rpt_whrq").datagrid("refresh");
})
    //表格双击跳转
$.CurrentNavtab.find(".table").bind("dblclick", function (e) {
    var cell = $(e.target).closest("tr");
    //var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    //var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var values = $($(cell).find("td[name='khdmmc']")).text();
    //values = values.substring(0,values.indexOf("["))
    $(this).navtab({
        id: 'KHMXZ_' + values+"&"+"!",//通过navtab的ID传递修改的单据号
        url: 'rpt/rpt_khmxz.jsp',
        title: "[" + values + "]开票明细记录"
    });
});

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})