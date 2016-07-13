/**
 * Created by wuxiutong on 15/8/23.
 */

function getLxr_Soft() {
    setValue("add_lxr_datagrid", 'lxr', columns_lxr);
    setValue("add_soft_datagrid", 'soft', columns_soft);
};
//获取表格信息的内容
function  getTableValue(tableId, columns) {
    var $trs = $("#" + tableId + ' tr');
    var dc = '';
    for (tr_i = 0; tr_i < $trs.length; tr_i++) {
        var $tds = $($trs[tr_i]).find("td");
//            因为该处列有个序列号行，所以从1才是计数才正确
        var dd = "";
        for (td_i = 0; td_i < $tds.length; td_i++) {
            //alert($($tds[td_i+1]).text());
            if (td_i == 0) {
                dd = '{' + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '",'
            } else if ((td_i + 1) == $tds.length) {
                dd = dd + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '"}'
            } else {
                dd = dd + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '",'
            }
        }
        //如果是第一行且是最后一行
        if (tr_i == 0 && ((tr_i + 1) == $trs.length)) {
            dc = '[' + dd + ']';
        }
        //如果是第一行直接先加上‘[’
        else if (tr_i == 0) {
            dc = '[' + dd + ',';
        } else
        //如果是最后一行直接增加结尾标志']'
        if ((tr_i + 1) == $trs.length) {
            dc = dc + dd + ']';
        } else {
            dc = dc + dd + ',';
        };
    }
    return dc;
};
var initDwysk_Clumns = [
    {
        name: 'khdmmc',
        label: '客户',
        align: 'left',
        width: 220,
        attrs:{readonly:'readonly'}
    }, {
        name: 'qsrq',
        label: '起始日期',
        align: 'center',
        type:'date',
        width: 120,
        rule:'date'
    }, {
        name: 'jzrq',
        label: '截止日期',
        align: 'center',
        type:'date',
        width: 120,
        rule:'required;date',
    }, {
        name: 'ssdq',
        label: '所属地区',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }, {
        name: 'khlx',
        label: '客户类型',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }, {
        name: 'khjl',
        label: '客户经理',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }, {
        name: 'updateDjh',
        label: '更新标志',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }, {
        name: 'updateUser',
        label: '更新者',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }, {
        name: 'lastUpdate',
        label: '更新日期',
        align: 'center',
        width: 120,
        edit:false,
        attrs:{readonly:'readonly'}
    }
];
function initWhrq_init() {
    var dqxx = $.CurrentNavtab.find("#rpt_searche_dqxx").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    var khlx = $.CurrentNavtab.find("#rpt_searche_khlx").val();

    $.CurrentNavtab.find('#datatable_rpt_whrq_init').datagrid({
        showToolbar: false,
        toolbarItem: 'all',
        local: 'local',
        dataUrl: 'Get_RPT_KhxxForInitWHRQ?khjl=999988889999' + "&keyword=&dqxx=",
        dataType: 'json',
        loadType: 'get',
        sortAll: true,
        filterAll: true,
        columns: initDwysk_Clumns,
        hiddenFields: [{name: 'deptco222de'}],
        editUrl: 'InitRptBaseServlet.updateWHRQ',
        editMode:'dialog',
        showCheckboxCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false,
        showToolbar: false,
        toolbarItem: 'all',
        showTfoot: true,
        showEditbtnscol:true
    });
}
initWhrq_init();
$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    var dqxx = $.CurrentNavtab.find("#rpt_searche_dqxx").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    var khlx = $.CurrentNavtab.find("#rpt_searche_khlx").val();
    var url = 'Get_RPT_KhxxForInitWHRQ?dqxx=' + dqxx +  "&khlx=" + khlx + "&khjl=" + khjl;
    $($.CurrentNavtab.find("#datatable_rpt_whrq_init")).datagrid('refreshByUrl', url);
    $($.CurrentNavtab.find("#datatable_rpt_whrq_init")).datagrid('initTfoot');
});
$.CurrentNavtab.find(".table").bind("dblclick", function (e) {
    var tr = $(e.target).closest("tr");
    $.CurrentNavtab.find('#datatable_rpt_whrq_init').datagrid("doEditRow",tr);
});

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})