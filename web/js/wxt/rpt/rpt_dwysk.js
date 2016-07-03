/**
 * Created by wuxiutong on 15/8/23.
 */

function initDwysk() {

    var dqxx = '99988889999';
    var kjnd = $.CurrentNavtab.find("#rpt_searche_kjnd").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    //var soft = $.CurrentNavtab.find("#rpt_searche_soft").val();
    var keyword = $.CurrentNavtab.find("#rpt_searche_keyword").val();
    $.CurrentNavtab.find('#datatable_rpt_dwysk').datagrid({
        showToolbar: false,
        toolbarItem: 'all',
        local: 'local',
        dataUrl: 'Get_RPT_DWYSK?dqxx=' + dqxx + "&kjnd=" + kjnd + "&khjl=" + khjl + "&keyword=" + keyword,
        dataType: 'json',
        loadType: 'get',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'khdmmc',
                label: '客户',
                align: 'left',
                width: 220
            }, {
                name: 'yjsk',
                label: '预计收款',
                align: 'right',
                width: 80,
                calc: 'sum',
                calcTit: '合计',
                calcDecimal: 2
            }
            , {
                name: 'ysk',
                label: '已收款',
                align: 'right',
                width: 80,
                calc: 'sum',
                calcTit: '合计',
                calcDecimal: 2
            }, {
                name: 'kjnd',
                label: '年度',
                align: 'center',
                width: 80
            }, {
                name: 'ssdq',
                label: '所属地区',
                align: 'center',
                width: 120
            }, {
                name: 'khlx',
                label: '客户类型',
                align: 'center',
                width: 120
            }
        ],
        hiddenFields: [{name: 'deptco222de'}],
        editUrl: 'ajaxDone1.html',
        showCheckboxCol: true,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false,
        showToolbar: false,
        toolbarItem: 'all',
        showTfoot: true
    });
}

initDwysk();
$.CurrentNavtab.find(".table").bind("dblclick", function (e) {
    var cell = $(e.target).closest("tr");
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
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