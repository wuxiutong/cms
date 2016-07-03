/**
 * Created by wuxiutong on 15/8/23.
 */

function initYkpKhxx() {

    var dqxx = '9999888889999'//$.CurrentNavtab.find("#rpt_searche_dqxx").val();
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var djlx = $.CurrentNavtab.find("#rpt_searche_djlx").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    var keyword = $.CurrentNavtab.find("#rpt_searche_keyword").val();
    $.CurrentNavtab.find('#datatable_rpt_ykpKhxx').datagrid({
        showToolbar: false,
        toolbarItem: 'all',
        local: 'local',
        dataUrl: 'Get_RPT_YKPKHXX?dqxx=' + dqxx + "&qsrq=" + qsrq + "&jzrq=" + jzrq + "&khjl=" + khjl + "&djlx=" + djlx+ "&keyword=" + keyword,
        dataType: 'json',
        loadType: 'get',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'khdmmc',
                label: '客户',
                align: 'left',
                width: 280
            }, {
                name: 'kpsl',
                label: '开票数量',
                align: 'right',
                width: 60
            }
            , {
                name: 'je',
                label: '合计金额',
                align: 'right',
                width: 120,
                calc: 'sum',
                calcTit: '合计',
                calcDecimal: 2
            }, {
                name: 'ssdq',
                label: '所属地区',
                align: 'left',
                width: 120
            }, {
                name: 'khlx',
                label: '客户类型',
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
        toolbarItem: 'all',
        showTfoot: true
    });
}

//}
//initDatagrid_rpt_khmxz();
$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    // initDatagrid_rpt_khmxz();

    var dqxx = $.CurrentNavtab.find("#rpt_searche_dqxx").val();
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var djlx = $.CurrentNavtab.find("#rpt_searche_djlx").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    var keyword = $.CurrentNavtab.find("#rpt_searche_keyword").val();
    var url = 'Get_RPT_YKPKHXX?dqxx=' + dqxx + "&qsrq=" + qsrq + "&jzrq=" + jzrq + "&khjl=" + khjl+"&djlx=" + djlx+"&keyword=" + keyword;
    $($.CurrentNavtab.find("#datatable_rpt_ykpKhxx")).datagrid('refreshByUrl', url);
    $($.CurrentNavtab.find("#datatable_rpt_ykpKhxx")).datagrid('initTfoot');

});
$.CurrentNavtab.find(".table").bind("dblclick", function (e) {
    var cell = $(e.target).closest("tr");
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var values = $($(cell).find("td[name='khdmmc']")).text();
    //values = values.substring(0,values.indexOf("["))
    $(this).navtab({
        id: 'KHMXZ_' + values+"&"+qsrq+"!"+jzrq,//通过navtab的ID传递修改的单据号
        url: 'rpt/rpt_khmxz.jsp',
        title: "[" + values + "]开票明细记录"
    });
});

initYkpKhxx();

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})