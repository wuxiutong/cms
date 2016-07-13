/**
 * Created by wuxiutong on 15/8/23.
 */


function init_rpt_khmxz() {
    var NavTabID = $($.CurrentNavtab).attr("navTab_id");
    if(NavTabID.contains("KHMXZ_")) {
        $.CurrentNavtab.find("#rpt_searche_khdm").val(NavTabID.substring(NavTabID.indexOf("_") + 1, NavTabID.indexOf("&")))
        $.CurrentNavtab.find("#rpt_searche_qsrq").val(NavTabID.substring(NavTabID.indexOf("&") + 1, NavTabID.indexOf("!")))
        $.CurrentNavtab.find("#rpt_searche_jzrq").val(NavTabID.substring(NavTabID.indexOf("!") + 1))
    }
    var khdm = $.CurrentNavtab.find("#rpt_searche_khdm").val();
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var djzt = $.CurrentNavtab.find("#rpt_searche_djzt").val();
    $.CurrentNavtab.find('#datatable_rpt_khmxz').datagrid({
        showToolbar: false,
        toolbarItem: 'all',
        local: 'local',
        dataUrl: 'Get_RPT_KHMXZ?khdm=' + khdm + "&qsrq=" + qsrq + "&jzrq=" + jzrq + "&djzt=" + djzt,
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'center',
                width: 80
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'center',
                width: 120
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'center',
                width: 180
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width: 120,
                calc: 'sum',
                calcTit: '合计',
                calcDecimal: 2
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'center',
                width: 200
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'center',
                width: 200
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'center',
                width: 200
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'center',
                width: 120
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'center',
                width: 120
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'center',
                width: 120
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'center',
                width: 120
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'center',
                width: 120
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'center',
                width: 150
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
//initDatagrid_rpt_khmxz();
$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    // initDatagrid_rpt_khmxz();

    var khdm = $.CurrentNavtab.find("#rpt_searche_khdm").val();
    var qsrq = $.CurrentNavtab.find("#rpt_searche_qsrq").val();
    var jzrq = $.CurrentNavtab.find("#rpt_searche_jzrq").val();
    var djzt = $.CurrentNavtab.find("#rpt_searche_djzt").val();
    var url = 'Get_RPT_KHMXZ?khdm=' + khdm + "&qsrq=" + qsrq + "&jzrq=" + jzrq + "&djzt=" + djzt;
    $($.CurrentNavtab.find("#datatable_rpt_khmxz")).datagrid('refreshByUrl', url);
    //$($.CurrentNavtab.find("#datatable_rpt_khmxz")).datagrid('initTfoot');

});
$.CurrentNavtab.find(".table").bind("dblclick", function (e) {
    var cell = $(e.target).closest("tr");
    var values = $($(cell).find("td[name='id']")).text();
    $(this).navtab({
        id: 'checkCWBills_' + values,//通过navtab的ID传递修改的单据号
        url: 'bills/alterCWBill.jsp',
        title: "[" + values + "]单据信息"
    });
});

init_rpt_khmxz();

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})