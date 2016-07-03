/**
 * Created by wuxiutong on 15/9/17.
 */
//选择事件
function S_NodeCheck_ids(e, treeId, treeNode) {
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
function initDqxxDialogNoLevelForSelectKhxx() {
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
            onCheck: S_NodeCheck_ids,
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
    $.fn.zTree.init($("#AllRegionForDqxxNoLevelForKhxx"), setting)
}

//初始化客户信息供选择，
function initKhlxDialogNoLevelForSelectKhxx() {
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
            onCheck: S_NodeCheck_ids,
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
    $.fn.zTree.init($("#AllKhlxForKhxx"), setting)
}
//初始化客户经理信息供选择，
function initKhjlDialogNoLevelForSelectKhxx() {
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
            onCheck: S_NodeCheck_ids,
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
    $.fn.zTree.init($("#AllKhjlForKhxx"), setting)
}
//初始化销售公司信息供选择，
function initXsgsForSelectKhxx() {
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
            onCheck: S_NodeCheck_ids,
//        onDblClick: zTreeOnDblClick
        },
        async: {
            enable: true,
            url: "BlocBaseServlet.getAll",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
    $.fn.zTree.init($("#AllXsgsForKhxx"), setting)
}
//初始化使用软件信息供选择，
function initSoftForSelectKhxx() {
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
            onCheck: S_NodeCheck_ids,
//        onDblClick: zTreeOnDblClick
        },
        async: {
            enable: true,
            url: "SoftModelBaseServlet.getAllGysVerModelForTree",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
    $.fn.zTree.init($("#AllSoftForKhxx"), setting)
}
//初始化地区信息用于客户筛选
initDqxxDialogNoLevelForSelectKhxx();
//初始化客户经理信息用于客户筛选
initKhjlDialogNoLevelForSelectKhxx();
//初始化客户类型用于客户筛选
initKhlxDialogNoLevelForSelectKhxx();
//初始化销售公司信息用于客户筛选
initXsgsForSelectKhxx()
//初始化使用软件信息用于客户筛选
initSoftForSelectKhxx()

$.CurrentNavtab.find("#searchBtn").bind("click", function () {
    // initDatagrid_rpt_khmxz();
    var khjl = $.CurrentNavtab.find("#khxx_searche_khjl").val();
    var khlx = $.CurrentNavtab.find("#khxx_searche_khlx").val();
    var xsgs = $.CurrentNavtab.find("#khxx_searche_xsgs").val();
    var softModel = $.CurrentNavtab.find("#khxx_searche_soft").val();
    var dqxx = $.CurrentNavtab.find("#khxx_searche_dqxx").val();
    var keyword = $.CurrentNavtab.find("#khxx_searche_keyword").val();
    var mykhxx = $.CurrentNavtab.find("#khxx_searche_myKhxx").val();
    var url = 'KhxxBaseServlet.getAll?khjl=' + khjl + "&keyword=" + keyword + "&khlx=" + khlx + "&xsgs=" + xsgs + "&softModel="
        + softModel + "&dqxx=" + dqxx + "&mykhxx=" + mykhxx;
    $($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('refreshByUrl', url);
    $($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('initTfoot');
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