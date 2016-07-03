/**
 * Created by wuxiutong on 15/9/17.
 */
//选择事件
function S_NodeCheck_cusomerAlter(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''

    for (var i = 0; i < nodes.length; i++) {
        //如果节点是非子节点则不添加
        if (nodes[i].isParent) {
        } else {
            ids += ',' + nodes[i].id
            if (nodes[i].name.indexOf("-") > 0) {
                names += ',' + nodes[i].name.replaceAll('-', '[') + ']';//仅仅截取name的前面代码
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
//职员信息选择 避免选择的是部门信息
function S_NodeCheck_Zyxx_cusomerAlter(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''

    for (var i = 0; i < nodes.length; i++) {
        //如果节点是非子节点则不添加
        if (nodes[i].isParent) {
        } else if (treeNode.id.startsWith("zy_")) {
            ids += ',' + nodes[i].id
            if (nodes[i].name.indexOf("-") > 0) {
                names += ',' + nodes[i].name.replaceAll('-', '[') + ']';//仅仅截取name的前面代码
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

//点击事件，点击节点后赋值，即可隐藏！
function S_NodeClick_customerAlter (event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)
    zTree.checkNode(treeNode, !treeNode.checked, true, true)
    event.preventDefault();
    if (!treeNode.isParent) {
        $($(event.target).closest('div.tree-box')).hide();
    }
}
//fileter
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

//初始化地区信息供选择，
function initDqxx_customerAlter() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_cusomerAlter,
            onClick: S_NodeClick_customerAlter
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
    $.fn.zTree.init($("#AllRegionForAlterKhxx"), setting)
}

//初始化客户信息供选择，
function initKhlx_customerAlter() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_cusomerAlter,
            onClick: S_NodeClick_customerAlter
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
    $.fn.zTree.init($("#AllKhlxForAlterKhxx"), setting)
}
//初始化客户经理信息供选择，
function initKhjl_customerAlter() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_Zyxx_cusomerAlter,
            onClick: function (event, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(treeId)
                zTree.checkNode(treeNode, !treeNode.checked, true, true)
                event.preventDefault();
                if (!treeNode.isParent && treeNode.id.startsWith("zy_")) {
                    $($(event.target).closest('div.tree-box')).hide();
                }
            }
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
    $.fn.zTree.init($("#AllKhjlForAlterKhxx"), setting)
}
//初始化销售公司信息供选择，
function initxsgs_customerAlter() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_cusomerAlter,
            onClick: S_NodeClick_customerAlter
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
    $.fn.zTree.init($("#AllXsgsForAlterKhxx"), setting)
}
//初始化使用软件信息供选择，
function initSoft_customerAlter() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_cusomerAlter,
            onClick: S_NodeClick_customerAlter
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
    $.fn.zTree.init($("#AllSoftForAlterKhxx"), setting)
}
//初始化地区信息用于客户筛选
initDqxx_customerAlter();
//初始化客户经理信息用于客户筛选
initKhjl_customerAlter();
//初始化客户类型用于客户筛选
initKhlx_customerAlter();
//初始化销售公司信息用于客户筛选
initxsgs_customerAlter()
//初始化使用软件信息用于客户筛选
initSoft_customerAlter()
//
//$.CurrentNavtab.find("#searchBtn").bind("click", function () {
//    // initDatagrid_rpt_khmxz();
//    var khjl = $.CurrentNavtab.find("#khxx_searche_khjl").val();
//    var khlx = $.CurrentNavtab.find("#khxx_searche_khlx").val();
//    var xsgs = $.CurrentNavtab.find("#khxx_searche_xsgs").val();
//    var softModel = $.CurrentNavtab.find("#khxx_searche_soft").val();
//    var dqxx = $.CurrentNavtab.find("#khxx_searche_dqxx").val();
//    var keyword = $.CurrentNavtab.find("#khxx_searche_keyword").val();
//    var mykhxx = $.CurrentNavtab.find("#khxx_searche_myKhxx").val();
//    var url = 'KhxxBaseServlet.getAll?khjl=' + khjl + "&keyword=" + keyword + "&khlx=" + khlx + "&xsgs=" + xsgs + "&softModel="
//        + softModel + "&dqxx=" + dqxx + "&mykhxx=" + mykhxx;
//    $($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('refreshByUrl', url);
//    $($.CurrentNavtab.find("#datagrid_ManaCustomer")).datagrid('initTfoot');
//});
////显示按钮
//$.CurrentNavtab.find("#showBtn").bind("click", function () {
//    if ($.CurrentNavtab.find("#extendsBar").css('display') == "inline")
//        $.CurrentNavtab.find("#extendsBar").css('display', 'none');
//    else
//        $.CurrentNavtab.find("#extendsBar").css('display', 'inline');
//});
////清空查询按钮
//$.CurrentNavtab.find("#reloadsearch").bind("click", function () {
//    //清空地区信息
//    $.fn.zTree.getZTreeObj("AllRegionForDqxxNoLevelForKhxx").checkAllNodes(false);
//    $.CurrentNavtab.find("#khxx_searche_dqxx").val("");
//    //清空客户经理信息
//    $.fn.zTree.getZTreeObj("AllKhjlForKhxx").checkAllNodes(false);
//    $.CurrentNavtab.find("#khxx_searche_khjl").val("");
//    //清空客户类型信息
//    $.fn.zTree.getZTreeObj("AllKhlxForKhxx").checkAllNodes(false);
//    $.CurrentNavtab.find("#khxx_searche_khlx").val("");
//    //清空使用软件信息
//    $.fn.zTree.getZTreeObj("AllSoftForKhxx").checkAllNodes(false);
//    $.CurrentNavtab.find("#khxx_searche_soft").val("");
//    //清空销售公司信息
//    $.fn.zTree.getZTreeObj("AllXsgsForKhxx").checkAllNodes(false);
//    $.CurrentNavtab.find("#khxx_searche_xsgs").val("");
//    $.CurrentNavtab.find("#khxx_searche_keyword").val("");
//});