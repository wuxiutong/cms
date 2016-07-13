//定义几个变量，用于记录某个箱子是否已经初始化
var datagrid_billBox_wsh_inited = false;
var datagrid_billBox_ysh_inited = false;
var datagrid_billBox_ysk_inited = false;
var datagrid_billBox_yzf_inited = false;

$.CurrentNavtab.find(".table").bind("dblclick",function(e){
    var cell = $(e.target).closest("tr");
    var values = $($(cell).find("td[name='id']")).text();
    $(this).navtab({
        id: 'checkCWBills_' + values,//通过navtab的ID传递修改的单据号
        url: 'bills/alterCWBill.jsp',
        title: "["+values+"]单据信息"
    });
});
//修改按钮事件
function btn_cwbills_xg() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');

    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }else if (selectedTrs.length != 1) {
        $(this).alertmsg("info", "仅能选择一条记录进行修改！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim();
    });
    //将即将修改的单据号码赋值给全局变量。
    CWBillID_willToBeModified = values;
    $(this).navtab({
        id: 'alterCWBills_' + values,//通过navtab的ID传递修改的单据号
        url: 'bills/alterCWBill.jsp',
        title: "修改单据信息"
    });
}
//审核按钮事件
function btn_cwbills_sh() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.sh?ids=" + values , success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_ysh_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_ysh')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_ysh_init();
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//销审按钮事件
function btn_cwbills_xs() {
    var userid = "";
    var username = "";
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax消除审核数据
    $.ajax({
        url: "CWBillBaseServlet.xs?ids=" + values , success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_wsh_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_wsh')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_wsh_init();
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//收款按钮事件
function btn_cwbills_sk() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax收款标记数据
    $.ajax({
        url: "CWBillBaseServlet.sk?ids=" + values , success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_ysk_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_ysk')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_ysk_init();
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//退款按钮事件
function btn_cwbills_tk() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax退款数据
    $.ajax({
        url: "CWBillBaseServlet.tk?ids=" + values , success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_ysh_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_ysh')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_wsh_init();
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//作废按钮事件
function btn_cwbills_zf() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax作废单据数据
    $.ajax({
        url: "CWBillBaseServlet.zf?ids=" + values , success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_yzf_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_yzf')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_yzf_init()
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//恢复按钮事件
function btn_cwbills_hf() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax恢复数据
    $.ajax({
        url: "CWBillBaseServlet.hf?ids=" + values, success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
                if (datagrid_billBox_wsh_inited) {
                    $($.CurrentNavtab.find('#datagrid_billBox_wsh')).datagrid('refresh');//刷新按钮未审核标签页数据
                } else {
                    //datagrid_billBox_wsh_init();
                }
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//删除按钮事件
function btn_cwbills_sc() {
    var values = '';
    //获取选中行
    var selectedTrs = $.CurrentNavtab.find($('#bill_box_tabs li.active a').attr('href'))
        .eq(0).find('tr.datagrid-selected-tr');
    if (selectedTrs.length <= 0) {
        $(this).alertmsg("info", "请选择单据！");
        return;
    }
    //循环获取先中行中name为ID列的数据
    $(selectedTrs).each(function (index, value) {
        values = values + $(value).find('td[name="id"]').eq(0).find('div').text().trim() + ";";
    });
    //通过ajax删除数据
    $.ajax({
        url: "CWBillBaseServlet.del?ids=" + values, success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                btn_cwbills_refresh() //刷新当前显示标签页的数据
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
}
//刷新按钮事件
function btn_cwbills_refresh() {
    var idI = $.CurrentNavtab.find('#bill_box_tabs li.active a').attr('href');
    $($.CurrentNavtab.find(idI + ' table')).datagrid('refresh');
}
//初始化所有单据内容
function datagrid_billBox_all_init() {
    var datagrid_billBox_all = $('#datagrid_billBox_all').datagrid({
        gridTitle: '所有单据',
        showToolbar: false,
        local: 'local',
        dataUrl: 'CWBillBaseServlet.getAll',
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'left',
                width:'80'
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'left',
                width:'60'
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'left',
                width:'80'
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'left',
                width:'250'
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'left',
                width:'200'
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width:'75'
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'left',
                width:'100'
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'left',
                width:'85'
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'left',
                width:'161'
            }
        ],
        editUrl: 'ajaxDone1.html',
        showCheckboxcol: false,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false,
        hScrollbar:true
    })
}

function datagrid_billBox_wsh_init() {
    var datagrid_billBox_wsh = $('#datagrid_billBox_wsh').datagrid({
        gridTitle: '未审核单据',
        showToolbar: false,
        local: 'local',
        dataUrl: 'CWBillBaseServlet.getSomeZTCWBill?zt=0',
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'left',
                width:'80'
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'left',
                width:'60'
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'left',
                width:'80'
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'left',
                width:'250'
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'left',
                width:'200'
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width:'75'
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'left',
                width:'100'
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'left',
                width:'85'
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'left',
                width:'161'
            }

        ],
        editUrl: 'ajaxDone1.html',
        showCheckboxcol: true,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false,
        hScrollbar:true
    })
    datagrid_billBox_wsh_inited = true;
}

function datagrid_billBox_ysh_init() {
    var datagrid_billBox_ysh = $('#datagrid_billBox_ysh').datagrid({
        gridTitle: '已审核单据',
        showToolbar: false,
        local: 'local',
        dataUrl: 'CWBillBaseServlet.getSomeZTCWBill?zt=1',
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'left',
                width:'80'
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'left',
                width:'60'
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'left',
                width:'80'
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'left',
                width:'250'
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'left',
                width:'200'
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width:'75'
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'left',
                width:'100'
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'left',
                width:'85'
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'left',
                width:'161'
            }
        ],
        editUrl: 'ajaxDone1.html',
        showCheckboxcol: true,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false
    })

    datagrid_billBox_ysh_inited = true;
}

function datagrid_billBox_ysk_init() {
    var datagrid_billBox_ysk = $('#datagrid_billBox_ysk').datagrid({
        gridTitle: '已收款单据',
        showToolbar: false,
        local: 'local',
        dataUrl: 'CWBillBaseServlet.getSomeZTCWBill?zt=2',
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'left',
                width:'80'
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'left',
                width:'60'
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'left',
                width:'80'
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'left',
                width:'250'
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'left',
                width:'200'
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width:'75'
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'left',
                width:'100'
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'left',
                width:'85'
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'left',
                width:'161'
            }
        ],
        editUrl: 'ajaxDone1.html',
        showCheckboxcol: true,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false
    })

    datagrid_billBox_ysk_inited = true;
}

function datagrid_billBox_yzf_init() {
    var datagrid_billBox_yzf = $('#datagrid_billBox_yzf').datagrid({
        gridTitle: '已作废单据',
        showToolbar: false,
        local: 'local',
        dataUrl: 'CWBillBaseServlet.getSomeZTCWBill?zt=-1',
        dataType: 'json',
        sortAll: true,
        filterAll: true,
        columns: [
            {
                name: 'id',
                label: '单据号',
                align: 'left',
                width:'80'
            }, {
                name: 'djlx',
                label: '单据类型',
                align: 'left',
                width:'60'
            }, {
                name: 'fylx',
                label: '费用类型',
                align: 'left',
                width:'80'
            }, {
                name: 'khdmmc',
                label: '付款单位',
                align: 'left',
                width:'250'
            }, {
                name: 'gsdmmc',
                label: '收款单位',
                align: 'left',
                width:'200'
            }
            , {
                name: 'je',
                label: '金额',
                align: 'right',
                width:'75'
            }
            , {
                name: 'ps',
                label: '备注',
                align: 'left',
                width:'100'
            }
            , {
                name: 'kprq',
                label: '开票日期',
                align: 'left',
                width:'85'
            }
            , {
                name: 'kpr',
                label: '开票人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'shr',
                label: '审核人',
                align: 'left',
                width:'85'
            }
            , {
                name: 'skr',
                label: '收款人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrr',
                label: '制单人',
                align: 'left',
                width:'85'
            }, {
                name: 'lrrq',
                label: '制单日期',
                align: 'left',
                width:'161'
            }
        ],
        editUrl: 'ajaxDone1.html',
        showCheckboxcol: true,
        showEditBtnsCol: true,
        linenumberAll: false,
        fullGrid: true,
        columnMenu: false,
        columnShowhide: false,
        columnFilter: false,
        columnLock: false,
        paging: false
    })

   datagrid_billBox_yzf_inited = true;
}

$.CurrentNavtab.find("#tbs_all").bind("click", function (e) {
    var src = e.target || window.event.srcElement;
    $('#bill_box_tabs a[href="#bill_box_all"]').tab('show')
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_sx").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sx").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sx").removeAttr("disabled")
})

$.CurrentNavtab.find("#tbs_wsh").bind("click", function (e) {
    $('#bill_box_tabs a[href="#bill_box_wsh"]').tab('show');
    if (!datagrid_billBox_wsh_inited) {
        datagrid_billBox_wsh_init();
    }
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_sx").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sx").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sx").removeAttr("disabled")
    //启用修改按钮
    $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-orange")
    $.CurrentNavtab.find("#btn_cwbills_xg").removeAttr("disabled")
    //启用审核按钮
    $.CurrentNavtab.find("#btn_cwbills_sh").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sh").addClass("btn-blue")
    $.CurrentNavtab.find("#btn_cwbills_sh").removeAttr("disabled")
    //启用作废按钮
    $.CurrentNavtab.find("#btn_cwbills_zf").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_zf").addClass("btn-blue")
    $.CurrentNavtab.find("#btn_cwbills_zf").removeAttr("disabled")
})

$.CurrentNavtab.find("#tbs_ysh").bind("click", function (e) {
    $('#bill_box_tabs a[href="#bill_box_ysh"]').tab('show')
    if (!datagrid_billBox_ysh_inited) {
        datagrid_billBox_ysh_init();
    }
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_sx").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sx").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sx").removeAttr("disabled")
    //启用销审按钮
    $.CurrentNavtab.find("#btn_cwbills_xs").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_xs").addClass("btn-red")
    $.CurrentNavtab.find("#btn_cwbills_xs").removeAttr("disabled")
    //启用收款按钮
    $.CurrentNavtab.find("#btn_cwbills_sk").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sk").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sk").removeAttr("disabled")
})

$.CurrentNavtab.find("#tbs_ysk").bind("click", function (e) {
    $('#bill_box_tabs a[href="#bill_box_ysk"]').tab('show')
    if (!datagrid_billBox_ysk_inited) {
        datagrid_billBox_ysk_init();
    }
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_sx").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sx").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sx").removeAttr("disabled")
    //启用退款按钮
    $.CurrentNavtab.find("#btn_cwbills_tk").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_tk").addClass("btn-red")
    $.CurrentNavtab.find("#btn_cwbills_tk").removeAttr("disabled")
})

$.CurrentNavtab.find("#tbs_yzf").bind("click", function (e) {
    $('#bill_box_tabs a[href="#bill_box_yzf"]').tab('show')
    if (!datagrid_billBox_yzf_inited) {
        datagrid_billBox_yzf_init();
    }
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_sx").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sx").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sx").removeAttr("disabled")
    //启用恢复按钮
    $.CurrentNavtab.find("#btn_cwbills_hf").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_hf").addClass("btn-orange")
    $.CurrentNavtab.find("#btn_cwbills_hf").removeAttr("disabled")
    //启用删除按钮
    $.CurrentNavtab.find("#btn_cwbills_sc").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_sc").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_sc").removeAttr("disabled")
})
//初始化几个单据箱子
function init() {
    datagrid_billBox_all_init();
}


init();