var columns_alterCustomer_lxr = [
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
var columns_alterCustomer_soft = [
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
var initComp = function initComponent(lxr, soft) {
//初始化联系人和使用该软件table组件
    $.CurrentNavtab.find('#alter_lxr-datagrid').datagrid({
        showToolbar: true,
        toolbarItem: 'add,edit,del',
        local: 'local',
        dataUrl: 'datagrid/datagrid-null-json.js',
        data: lxr,
        dataType: 'json',
        sortAll: false,
        editUrl: 'ajaxDone1.html',
        filterThead: false,
        columnMenu: true,
        columns: columns_alterCustomer_lxr,
        paging: false,
        editMode: 'dialog',
        showCheckboxcol: true,
        showEditBtnscol: true,
        linenumberAll: true,
        addLocation: 'last',
        fullGrid: true
    });
    $.CurrentNavtab.find('#alter_soft-datagrid').datagrid({
        showToolbar: true,
        toolbarItem: 'add,edit,del',
        local: 'local',
        dataUrl: 'datagrid/datagrid-null-json.js',
        data: soft,
        editUrl: 'ajaxDone1.html',
        dataType: 'json',
        sortAll: false,
        filterThead: false,
        columnMenu: true,
        showCheckboxcol: true,
        columns: columns_alterCustomer_soft,
        paging: false,
        editMode: 'dialog',
        showEditBtnscol: true,
        linenumberAll: true,
        addLocation: 'last',
        fullGrid: true
    });
}
//从后台获取内容
var initValues = function () {
    var khdm_alter = $($.CurrentNavtab).attr("navTab_id");
    khdm_alter = khdm_alter.substring(khdm_alter.indexOf("_") + 1)
    //初始化组件
    $.ajax({
        url: "KhxxBaseServlet.getOneKhxx?khdmmc=" + khdm_alter, success: function (data) {
            //获取返回的客户信息
            var khxx = data.khxx;
            $.CurrentNavtab.find('#alter_customer_gsdm').attr('value', khxx.khdm);
            $.CurrentNavtab.find('#alter_customer_gsmc').val(khxx.khmc);
            $.CurrentNavtab.find('#alter_customer_gzdz').val(khxx.gzdz);
            $.CurrentNavtab.find('#alter_customer_gzdh').val(khxx.bgdh);
            $.CurrentNavtab.find('#alter_customer_khlx').val(khxx.khlx);
            $.CurrentNavtab.find('#alter_customer_ssdq').val(khxx.ssdq);
            $.CurrentNavtab.find('#alter_customer_khjl').val(khxx.khjl);
            $.CurrentNavtab.find('#alter_customer_xsgs').val(khxx.xsgs);
            $.CurrentNavtab.find('#alter_customer_whrq').val(khxx.whrq);
            $.CurrentNavtab.find('#alter_customer_ps').val(khxx.ps);
            var khxx_lxr = data.khxx_lxr;
            var khxx_soft = data.khxx_soft;
            initComp(khxx_lxr, khxx_soft);
        }
    });
};

//初始化界面组件
//初始化组件值
//获取联系人和软件信息
function getAlterLxrSoftValue() {
    setAlterCustomerLxrSoftValue("alter_lxr-datagrid", 'lxr', columns_alterCustomer_lxr);
    setAlterCustomerLxrSoftValue("alter_soft-datagrid", 'soft', columns_alterCustomer_soft);
};
//在提交时获取联系人和使用软件信息！
function setAlterCustomerLxrSoftValue(tableId, inputId, columns) {
    var $trs = $.CurrentNavtab.find('#' + tableId + ' tr');
    var dc = '';
    for (tr_i = 0; tr_i < $trs.length; tr_i++) {
        var $tds = $($trs[tr_i]).find("td");
//            因为该处列有个序列号行，所以从1才是计数才正确
        var dd = "";
        for (td_i = 0; td_i < $tds.length; td_i++) {
            if (td_i == 0) {
                dd = '{' + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '",'
            } else if ((td_i + 1) == $tds.length) {
                dd = dd + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '"}'
            } else {
                dd = dd + eval(columns[td_i]).name + ':' + '"' + $($tds[td_i]).text() + '",'
            }
        }
        ;
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
};

function navTabCustomerAjaxDone(json) {
    $(this)
        .bjuiajax('ajaxDone', json)       // 信息提示
    if (json.statusCode == 200) {
        $.CurrentNavtab.find('#add_customer_gsdm').attr("value", json.khdm);
        $.CurrentNavtab.find('#btn_alterKhxx_save').attr("disabled","disabled");
    }
};
//初始化组件值
initValues();
$.CurrentNavtab.find("#btn_alterKhxx_save").bind("click",function(){
    getAlterLxrSoftValue();
    $.CurrentNavtab.find("form_alterCustomer").submit();
})


$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})