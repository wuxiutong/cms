/**
 * Created by wuxiutong on 15/9/17.
 */
//选择事件
function S_NodeCheck_rpt_whrq(e, treeId, treeNode) {
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
//    $("#AllRegionForZtreeRptWhrqInit").hide();
//}
//fileter
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
 
//初始化地区信息供选择，
function initDqxxZtreeForRptWhrq() {
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
            onCheck: S_NodeCheck_rpt_whrq,
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
    $.fn.zTree.init($("#AllRegionForZtreeRptWhrq"), setting)
}
 
//初始化客户经理信息供选择，
function initKhjlForZtreeForWhrq() {
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
            onCheck: S_NodeCheck_rpt_whrq,
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
    $.fn.zTree.init($("#AllKhjlForZtreeWhrq"), setting)
}
//初始化地区信息用于客户筛选
initDqxxZtreeForRptWhrq();
//初始化客户经理信息用于客户筛选
initKhjlForZtreeForWhrq();
$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    // initDatagrid_rpt_khmxz();
    var khjl = $.CurrentNavtab.find("#khxx_searche_khjl").val();
    var dqxx = $.CurrentNavtab.find("#khxx_searche_dqxx").val();
    var keyword = $.CurrentNavtab.find("#khxx_searche_keyword").val();
    var url = 'Get_RPT_WHRQ?khjl=' + khjl + "&keyword=" + keyword + "&dqxx=" + dqxx ;
    $($.CurrentNavtab.find("#datatable_rpt_whrq")).datagrid('refreshByUrl', url);
    $($.CurrentNavtab.find("#datatable_rpt_whrq")).datagrid('initTfoot');
});

//清空查询按钮
$.CurrentNavtab.find("#reloadsearch").bind("click", function () {
    //清空地区信息
   $.fn.zTree.getZTreeObj("AllRegionForZtreeRptWhrqInit").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_dqxx").val("");
    //清空客户经理信息
   $.fn.zTree.getZTreeObj("AllKhjlForWhrqInit").checkAllNodes(false);
   $.CurrentNavtab.find("#khxx_searche_khjl").val("");

   $.CurrentNavtab.find("#khxx_searche_keyword").val("");
});