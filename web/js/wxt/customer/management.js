$(document).ready(function () {
    $(".getDetail").bind("click", function () {
    });
});
//鼠标双击事件
$.CurrentNavtab.find(".table").bind("dblclick",function(e){
    var cell = $(e.target).closest("tr");
    var khdmmc = $($(cell).find("td[name='khdmmc']")).text();
    var khdm =khdmmc.substring(0,khdmmc.indexOf('['));
    $(this).navtab({
        id: 'alterCustomer_'+khdm,
        url: 'customer/alter.jsp?khdmmc=' + khdm,
        title: "["+khdm+"]客户信息"
    });
});
//刷新按钮事件
function btn_customer_refresh() {
    //var idI = $.CurrentNavtab.find('#datagrid_ManaCustomer li.active a').attr('href');
    $($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('refresh');
}
//编辑按钮事件
function btn_customer_edit() {
    var trs = $($.CurrentNavtab.find("#datagrid_ManaCustomer")).data('selectedTrs');
    if (undefined == trs || trs.length <= 0) {
        $(this).alertmsg("warn", "请选择需要编辑的行");
        return false;
    }
    if (trs.length > 1) {
        $(this).alertmsg("warn", "只能选择一行！");
        return false;
    }
    $.each(trs, function (index, vv) {
        var td = $(trs[index]).find("td[name='khdmmc']").eq(0);
        var khdmmc = $(td).find('div').text().trim();
        window.khdm_alter = khdmmc;
        $(this).navtab({
            id: 'alterCustomer_'+khdmmc,
            url: 'customer/alter.jsp?khdm=' + khdmmc,
            title: "修改客户信息"
        });
    });
}
//添加按钮事件
function btn_customer_add() {
    $(this).navtab({
        id: 'addNewCustomer',
        url: 'customer/add.jsp',
        title: "新增客户信息"
    });
}
//删除按钮事件 btn_delChecked
$.CurrentNavtab.find("#btn_delChecked").bind("click",function() {
    var trs = $($.CurrentNavtab.find("#datagrid_ManaCustomer")).data('selectedTrs');
    if (undefined == trs || trs.length <= 0) {
        $(this).alertmsg("warn", "请选择需要删除的行");
        return false;
    }
    $(this).alertmsg('confirm', '即将删除选中行，是否继续？', {
        displayMode: 'slide', displayPosition: 'topcenter', okName: '确定',
        okCall: function () {
            del_rows(trs);
        },
        cancelCall: function () {
            return false;
        }, cancelName: '取消', title: '警告'
    });
});
function del_rows(trs) {
    var khdm = '';
    $.each(trs, function (index, vv) {
        var td = $(trs[index]).find("td[name='khdmmc']").eq(0);
        var tdValue = $(td).find('div').text();
        if (undefined != tdValue)
            khdm = khdm + tdValue.trim();
        if (index < trs.length) {
            khdm = khdm + ","
        }

    });
    $.ajax({
        url: "KhxxBaseServlet.del?delKhdms=" + khdm, success: function (json) {
            var trs = $($.CurrentNavtab.find("#datagrid_ManaCustomer")).data('selectedTrs');
            if (json.statusCode == 200) {
                $(trs).remove()
                //$($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('showLinenumber','false')
                //$($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('showLinenumber','true')
                $(this).alertmsg("ok", json.message)
            } else {
                $(this).alertmsg("error", json.message)
            }
        }
    })
}

function getDetiles(dd) {
    var thisTr = $(dd).closest('tr');
    var nextTr = $.CurrentNavtab.find('tr.detail2');
    var prevTr = nextTr.prev('tr');
    nextTr.remove();
    if ($(dd).html() == "-") {
        prevTr.find('.getDetail').html("+");
        return;
    };
    prevTr.find('.getDetail').html("+");
    {
        var cel = thisTr.find("td[name='khdmmc']");//$.CurrentNavtab.find('#datagrid_ManaCustomer').find('tr.selected');
        var khdmmc = $(cel[0]).text();
        var  khdm =khdmmc.substring(0,khdmmc.indexOf('['));
        $.ajax({
            url: "KhxxBaseServlet.getLxrAndSoft?khdm=" + khdm, success: function (json) {
                thisTr.after("<tr class ='detail2'  ><td></td><td></td><td><p style='font-weight: bold;text-align: center'>联系人</p></td><td colspan=7 style='height:60px;border: solid  1px'>" + json.lxr + "</td></tr>");
                thisTr.after("<tr class ='detail2'  ><td></td><td></td><td><p style='font-weight: bold;text-align: center'>软件信息</p></td><td colspan=7 style='height:60px;border: solid  1px'>" + json.soft + "</td></tr>");
                dd.html("-");
            }
        });
    }
};
//根据列name获取列ID
function searchCell(sele, titile) {
    var thead = $(sele).closest("thead");
    return result;
};
function delCallback(json) {
    if (json.status == '200') {
        $(this).alertmsg('info', json.message, {autoClose: true, alertTimeout: 2000})
    }
    else {
        $(this).alertmsg('warn', json.message, {autoClose: true, alertTimeout: 2000})

    }
    var data = $('#test-datagrid').data('selectedTrs');
    var rows = (json.deletedRows).split(",")
    $.each(rows, function (index, value) {
        if (null != value && "" != value) {
            $.each(data, function (i, n) {
                var ce = n.cells;
                if (value == $(ce[searchCell("khdm")]).text()) {
                    $(n).trigger('bjui.datagrid.tr.delete')
                }
            });
        }
    });
};

//客户表格列数组变量
var columns_customers = [{
    name: 'btn',
    label: '详细',
    align: 'center',
    width: 35
},
    {
        name: 'khdmmc',
        label: '客户名称',
        align: 'left',
        width:'290'
    },
    {
        name: 'ssdq',
        label: '所属地区',
        align: 'left',
        width:'150'
    },
    {
        name: 'khlx',
        label: '客户类型',
        align: 'left',
        width:'150'
    },
    {
        name: 'bgdh',
        label: '办公电话',
        align: 'left',
        width:'120'
    },
    {
        name: 'khjl',
        label: '客户经理',
        align: 'left',
        width:'85'
    },
    {
        name: 'xsgs',
        label: '销售公司',
        align: 'left',
        width:'250'
    },
    {
        name: 'gzdz',
        label: '工作地址',
        align: 'left',
        width:'180'
    },
];
//初始化客户管理表格
var DataTable_customers = $('#datagrid_ManaCustomer').datagrid({
    //gridTitle: '客户信息',
    showToolbar: false,
    toolbarItem: 'all',
    local: 'local',
    dataUrl: 'KhxxBaseServlet.getAll',
    dataType: 'json',
    loadType: 'GET',
    sortAll: true,
    filterAll: false,
    columns: columns_customers,
    hiddenFields: [{name: 'kh111jl'}],
    editPage: 'customer/alter.jsp',
    editMode: 'navtab',
    paging:false,
    showCheckboxcol: true,
    showEditBtnsCol: true,
    linenumberAll: false,
    fullGrid: true,
    delUrl: 'DelKhxx',//删除按钮有事件调用URL
    delPK: 'khdm',
    delCallback: 'delCallback',
    columnMenu: false
});

//联系人表格
var columns_lxr = [
    {
        name: 'lxrxm',
        label: '姓名',
        align: 'left',
        width: 70
    },
    {
        name: 'sex',
        label: '性别',
        align: 'left',
        width: 120
    },
    {
        name: 'age',
        label: '年龄',
        align: 'left',
        width: 70
    },
    {
        name: 'zw',
        label: '职务',
        align: 'left',
        width: 70
    },
    {
        name: 'bgdh',
        label: '座机',
        align: 'left',
        width: 80
    },
    {
        name: 'cellphone',
        label: '手机',
        align: 'left',
        type: 'date',
        width: 80
    },
    {
        name: 'qq',
        label: 'QQ',
        align: 'left',
        width: 60
    }, {
        name: 'qtlxfs',
        label: '其他联系方式',
        align: 'left',
        width: 60
    }, {
        name: 'ps',
        label: '备注',
        align: 'left',
        width: 60
    }
];

var khdm = '';

var DataTable_ManaLxr = $('#datagrid_ManaLxr').datagrid({
    gridTitle: '联系人信息',
    showToolbar: true,
    toolbarItem: '',
    local: 'local',
    dataUrl: 'GetSomeLxr?khdm=' + khdm,
    dataType: 'json',
    sortAll: true,
    filterAll: false,
    columns: columns_lxr,
    hiddenFields: [{name: 'khjl'}],
    editUrl: 'ajaxDone1.html',
    editMode: 'dialog',
    showCheckboxcol: false,
    showEditBtnsCol: true,
    linenumberAll: true,
    filterThead: false,
    fullGrid: false,
    delUrl: 'DelKhxx',//删除按钮有事件调用URL
    delPK: 'khdm',
    delCallback: 'delCallback',
    contextMenuB: false //数据行上选择出现右键菜单！
})

//导出选中项事件
$.CurrentNavtab.find("#btn_exportChecked").bind("click",function(e) {
    var trs = $($.CurrentNavtab.find("#datagrid_ManaCustomer")).data('selectedTrs');
    if (undefined == trs || trs.length <= 0) {
        $(this).alertmsg("warn", "请选择需要导出的行");
        return false;
    }
    $(this).alertmsg('confirm', '即将导出所有选中行，是否继续？', {
        displayMode: 'slide', displayPosition: 'topcenter', okName: '确定',
        okCall: function () {
            exportOutKhxx_rows(trs);
        },
        cancelCall: function () {
            return false;
        }, cancelName: '取消', title: '警告'
    });
});

//导出所有事件
$.CurrentNavtab.find("#btn_exportAll").bind("click",function(e) {
    var trs = $($.CurrentNavtab.find("#datagrid_ManaCustomer tbody")).find('> tr');
    $(this).alertmsg('confirm', '即将导出所有行，是否继续？', {
        displayMode: 'slide', displayPosition: 'topcenter', okName: '确定',
        okCall: function () {
            exportOutKhxx_rows(trs);
        },
        cancelCall: function () {
            return false;
        }, cancelName: '取消', title: '警告'
    });
});

//导出客户信息方法事件
function exportOutKhxx_rows(trs){
    var khdm = '';
    $.each(trs, function (index, vv) {
        var td = $(trs[index]).find("td[name='khdmmc']").eq(0);
        var tdValue = $(td).find('div').text();
        if(tdValue.indexOf("[")!=-1) {
            tdValue = tdValue.substring(0, tdValue.indexOf('['));
        }
        if (undefined != tdValue)
            khdm = khdm + tdValue.trim();
        if (index < trs.length) {
            khdm = khdm + ";"
        }
    });
            $.fileDownload("ExportKhxxToExcel?exportkhdms="+khdm , {
                failCallback: function(responseHtml, url) {
                    if (responseHtml.trim().startsWith('{')) responseHtml = responseHtml.toObj()
                    $(a).bjuiajax('ajaxDone', responseHtml)
                }
            })
}