var columns_lxr = [
    {
        name: 'lxrxm',
        label: '姓名',
        align: 'center',
        width: 70,
        attrs: {"data-rule": "required"}
    },
    {
        name: 'sex',
        label: '性别',
        align: 'center',
        width: 70,
        type: 'select',
        items: [{'': ''}, {'男': '男'}, {'女': '女'}, {'未知': '未知'}]
    },
    {
        name: 'age',
        label: '年龄',
        align: 'center',
        width: 70
    },
    {
        name: 'zw',
        label: '职务',
        align: 'center',
        width: 70
    },
    {
        name: 'tel',
        label: "办公座机",
        align: 'center',
        width: 70
    },
    {
        name: 'cellphone',
        label: "手机",
        align: 'center',
        width: 70
    },
    {
        name: 'email',
        label: '电子邮件',
        align: 'center',
        width: 70
    },
    {
        name: 'qq',
        label: "QQ",
        align: 'center',
        width: 70
    },
    {
        name: 'qtlxfs',
        label: "其他联系方式",
        align: 'center',
        width: 70
    },
    {
        name: 'ps',
        label: "备注",
        align: 'center',
        width: 70
    }

];
var columns_soft = [
    {
        name: 'softID',
        label: '软件ID',
        align: 'center',
        width: 70
    },
    {
        name: 'gysDm',
        label: '供应商代码',
        align: 'center',
        width: 70,
        editHide: 'true',
        hide: true
    },
    {
        name: 'gysMc',
        label: '供应商名称',
        align: 'center',
        width: 70,
        editHide: 'true',
        hide: true
    },
    {
        name: 'modelDm',
        label: '模块代码',
        align: 'center',
        width: 70,
        editHide: 'true',
        hide: true
    },
    {
        name: 'modelMc',
        label: '模块名称',
        align: 'center',
        width: 70,
        editHide: 'true',
        hide: true
    },
    {
        name: 'verDm',
        label: '版本代码',
        align: 'center',
        width: 70,
        editHide: 'true',
        hide: true
    },
    {
        name: 'verMc',
        label: '版本名称',
        align: 'center',
        width: 70,
        hide: true,
        editHide: 'true'

    },
    {
        name: 'gysDmMc',
        label: '供应商',
        align: 'center',
        width: 70,
        attrs: {'readonly': 'readonly'}
    },
    {
        name: 'verDmMc',
        label: '软件版本',
        align: 'center',
        width: 70,
        attrs: {'readonly': 'readonly'}
    },
    {
        name: 'modelDmMc',
        label: '软件模块',
        align: 'center',
        width: 70,
        type: 'lookup',
        attrs: {
            'data-url': "dialogs/selectSoftModel.jsp",
            "data-rule": "required",
            'readonly': 'readonly',
            'data-width': "600",
            'data-height': "490"
        }
    },
    {
        name: 'yhs',
        label: "用户数",
        align: 'center',
        type: 'spinner',
        width: 70
    },
    {
        name: 'gmrq',
        label: "购买日期",
        align: 'center',
        type: 'spinner',
        width: 70,
        type: 'date'
    },
    {
        name: 'ps',
        label: "备注",
        align: 'center',
        width: 70
    }

];
$.CurrentNavtab.find('#add_lxr_datagrid').datagrid({
    showToolbar: true,
    toolbarItem: 'add,edit,del',
    local: 'local',
    dataType: 'json',
    data:'[]',
    sortAll: false,
    filterThead: false,
    editUrl: 'ajaxDone1.html',
    columnMenu: true,
    columns: columns_lxr,
    paging: false,
    editMode: 'dialog',
    showCheckboxcol: true,
    showEditBtnscol: true,
    linenumberAll: true,
    addLocation: 'last',
    fullGrid: true
});
$.CurrentNavtab.find('#add_soft_datagrid').datagrid({
    showToolbar: true,
    toolbarItem: 'add,edit,del',
    local: 'local',
    data:'[]',
    dataType: 'json',
    sortAll: false,
    filterThead: false,
    editUrl: 'ajaxDone1.html',
    columnMenu: true,
    showCheckboxcol: true,
    columns: columns_soft,
    paging: false,
    editMode: 'dialog',
    showEditBtnscol: true,
    linenumberAll: true,
    addLocation: 'last',
    fullGrid: true
});
//获取联系人和软件信息
function getLxr_Soft() {
    setValue("add_lxr_datagrid", 'lxr', columns_lxr);
    setValue("add_soft_datagrid", 'soft', columns_soft);
  //  return CheckExits();
}
;
//按表格设置输入信息！
function setValue(tableId, inputId, columns) {
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
        }
        ;
    }
    $.CurrentNavtab.find("#" + inputId).attr("value", dc);
}
;
function navTabCustomerAjaxDone(json) {
    $(this)
        .bjuiajax('ajaxDone', json)       // 信息提示
    if (json.statusCode == 200) {
        $.CurrentNavtab.find('#add_customer_gsdm').attr("value", json.khdm);
        $.CurrentNavtab.find('#btn_addKhxx_save').attr("disabled", "disabled");
        $.CurrentNavtab.find('#btn_addKhxx_new').removeAttr("disabled");
    }
};
//计算客户代码
function CheckExits() {
    // alert("ddddd");
    var result = true;
    $.ajax({
        url: "CheckExits",
        dataType: 'json',
        async:false,
        data: {
            entity: 'Khxx',
            filedName: 'khmc',
            filedValue: "'" + $.CurrentNavtab.find("#add_customer_gsmc").val() + "'"
        },
        success: function (json) {
            if (json.statusCode == 300) {
                result =  $(this).alertmsg('confirm', json.message+'继续吗？', {displayMode: 'fade', displayPosition: 'topcenter', okName: 'Yes', cancelName: 'no', title: '警告'
                });
            }
            else result = true;
        }
    });
    return result;
}
//保存按钮事件
$.CurrentNavtab.find("#btn_addKhxx_save").bind("click",function(){
    getLxr_Soft();
    $.CurrentNavtab.find("form_addCustomer").submit();
})
//新增按钮事件
$.CurrentNavtab.find("#btn_addKhxx_new").bind("click",function(){
    $.CurrentNavtab.find('#add_lxr_datagrid').datagrid('delRows','0,1,2,3,4,5,6,7,8,9,10');//删除单位联系的1到10行
   // $.CurrentNavtab.find('#add_soft_datagrid').datagrid('refresh');
//clearForm("#form_addCustomer");
    $.CurrentNavtab.find('#add_customer_gsmc').attr("value","");//清空单位代码
    $.CurrentNavtab.find('#add_customer_gsdm').attr("value","");//清空单位名称
    $.CurrentNavtab.find('#add_customer_gzdz').attr("value","");//清空单位地址
    $.CurrentNavtab.find('#add_customer_gzdh').attr("value","");//清空单位电话
    $.CurrentNavtab.find("#btn_addKhxx_save").removeAttr("disabled");//将保存按钮设置为可用
    $.CurrentNavtab.find("#btn_addKhxx_new").attr("disabled","disabled");//将新增按钮设置为不可用状态
})

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})