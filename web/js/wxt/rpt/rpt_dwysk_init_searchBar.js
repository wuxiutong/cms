/**
 * Created by wuxiutong on 15/9/17.
 */
//选择事件
function S_NodeCheck_rpt_dwysk_init(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''

    for (var i = 0; i < nodes.length; i++) {
        //如果节点是非子节点则不添加
        if (nodes[i].isParent) {
        } else {
            ids += ',' + nodes[i].id
            if (nodes[i].name.indexOf("-") > 0) {
                names += ',' + nodes[i].name.substring(0, nodes[i].name.indexOf("-"))//仅仅截取name的前面代码
            } else {
                names += ',' + nodes[i].name//仅仅截取name的前面代码
            }
        }
    }
    if (ids.length > 0) {
        ids = ids.substr(1), names = names.substr(1)
    }

    var $from = $('#' + treeId).data('fromObj')

    if ($from && $from.length) $from.val(names)

}

//function back() {
//    $("#AllRegionForDqxxNoLevelForKhxx").hide();
//}
//fileter
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)

    zTree.checkNode(treeNode, !treeNode.checked, true, true)
    event.preventDefault()
//    $("#j_select_tree2").hide();
    //alert($(zTree).hide());
}
//初始化地区信息供选择，
function initDqxx_rpt_dwysk_init() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true
        },
        callback: {
//        beforeClick: zTreeBeforeClick,
            onCheck: S_NodeCheck_rpt_dwysk_init,
//        onDblClick: zTreeOnDblClick
        },
        async: {
            enable: true,
            url: "RegionBaseServlet.getAll",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
    $.fn.zTree.init($("#AllRegionForRptInitDWYSK"), setting)
}

//初始化客户信息供选择，
function initKhlx_rpt_dwysk_init() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true
        },
        callback: {
//        beforeClick: zTreeBeforeClick,
            onCheck: S_NodeCheck_rpt_dwysk_init,
//        onDblClick: zTreeOnDblClick
        },
        async: {
            enable: true,
            url: "DwlxBaseServlet.getAll",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
    $.fn.zTree.init($("#AllKhlxForRptInitDWYSK"), setting)
}
//初始化客户经理信息供选择，
function initKhjl_rpt_dwysk_init() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true
        },
        callback: {
//        beforeClick: zTreeBeforeClick,
            onCheck: S_NodeCheck_rpt_dwysk_init,
//        onDblClick: zTreeOnDblClick
        },
        async: {
            enable: true,
            url: "BmxxBaseServlet.getBmZy",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
    $.fn.zTree.init($("#AllKhjlForRptInitDWYSK"), setting)
}

//初始化地区信息用于客户筛选
initDqxx_rpt_dwysk_init();
//初始化客户经理信息用于客户筛选
initKhjl_rpt_dwysk_init();
//初始化客户类型用于客户筛选
//initDqxx_rpt_dwysk_init();
//初始化销售公司信息用于客户筛选
//initXsgs_rpt_dwysk()
//初始化使用软件信息用于客户筛选
//initSoft_rpt_dwysk()

$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    // initDatagrid_rpt_khmxz();
    var dqxx = $.CurrentNavtab.find("#rpt_searche_dqxx").val();
    var kjnd = $.CurrentNavtab.find("#rpt_searche_kjnd").val();
    var khjl = $.CurrentNavtab.find("#rpt_searche_khjl").val();
    var keyword = $.CurrentNavtab.find("#rpt_searche_keyword").val();
    var url = 'Get_RPT_KhxxForInitDwysk?dqxx=' + dqxx + "&kjnd=" + kjnd + "&khjl=" + khjl + "&keyword=" + keyword;
        $($.CurrentNavtab.find("#datatable_rpt_dwysk_init")).datagrid('refreshByUrl', url);
    $($.CurrentNavtab.find("#datatable_rpt_dwysk_init")).datagrid('initTfoot');
});
//显示按钮
$.CurrentNavtab.find("#showBtn").bind("click", function () {
    if ($.CurrentNavtab.find("#extendsBar").css('display') == "inline")
        $.CurrentNavtab.find("#extendsBar").css('display', 'none');
    else
        $.CurrentNavtab.find("#extendsBar").css('display', 'inline');
});
//清空查询按钮
$.CurrentNavtab.find("#reloadsearch").bind("click", function () {
    //清空地区信息
   $.fn.zTree.getZTreeObj("AllRegionForDqxxNoLevelForKhxx").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_dqxx").val("");
    //清空客户经理信息
   $.fn.zTree.getZTreeObj("AllKhjlForKhxx").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_khjl").val("");
    //清空客户类型信息
   $.fn.zTree.getZTreeObj("AllKhlxForKhxx").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_khlx").val("");
    //清空使用软件信息
   $.fn.zTree.getZTreeObj("AllSoftForKhxx").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_soft").val("");
    //清空销售公司信息
   $.fn.zTree.getZTreeObj("AllXsgsForKhxx").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_xsgs").val("");
   $.CurrentNavtab.find("#khxx_searche_keyword").val("");
});