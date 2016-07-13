/**
 * Created by wuxiutong on 15/9/17.
 */
//选择事件
function S_NodeCheck_RptYKPKhxx(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''

    for (var i = 0; i < nodes.length; i++) {
        //如果节点是非子节点则不添加
        if (nodes[i].isParent) {
        } else {
            ids += ',' + nodes[i].id
            if (nodes[i].name.indexOf("-") > 0) {
                names += ',' + nodes[i].name.substring(0,nodes[i].name.indexOf("-"));//仅仅截取name的前面代码
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
function S_NodeCheck_Zyxx_RptYKPKhxx(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''

    for (var i = 0; i < nodes.length; i++) {
        //如果节点是非子节点则不添加
        if (nodes[i].isParent) {
        } else if (nodes[i].id.startsWith("zy_")) {
            ids += ',' + nodes[i].id
            if (nodes[i].name.indexOf("-") > 0) {
                names += ',' + nodes[i].name.substring(0,nodes[i].name.indexOf("-"));//仅仅截取name的前面代码
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
function S_NodeClick_RptYKPKhxx(event, treeId, treeNode) {
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
function initDqxx_RptYKPKhxx() {
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
            chkStyle: "checkbox",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_RptYKPKhxx
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
    $.fn.zTree.init($("#AllRegionForRptYKPKhxx"), setting)
}

//初始化客户信息供选择，
function initKhlx_RptYKPKhxx() {
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
            chkStyle: "checkbox",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_RptYKPKhxx
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
    $.fn.zTree.init($("#AllKhlxForAddKhxx"), setting)
}
//初始化客户经理信息供选择，
function initKhjl_RptYKPKhxx() {
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
            chkStyle: "checkbox",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_Zyxx_RptYKPKhxx
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
    $.fn.zTree.init($("#AllKhjlForRptYKPKhxx"), setting)
}
//初始化销售公司信息供选择，
function initxsgs_RptYKPKhxx() {
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
            chkStyle: "checkbox",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_RptYKPKhxx
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
    $.fn.zTree.init($("#AllXsgsForAddKhxx"), setting)
}
//初始化使用软件信息供选择，
function initSoft_RptYKPKhxx() {
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
            chkStyle: "checkbox",
            radioType: "all"
        },
        callback: {
            onCheck: S_NodeCheck_RptYKPKhxx
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
    $.fn.zTree.init($("#AllSoftForAddKhxx"), setting)
}
//初始化地区信息用于客户筛选
initDqxx_RptYKPKhxx();
//初始化客户经理信息用于客户筛选
initKhjl_RptYKPKhxx();
//初始化客户类型用于客户筛选
initKhlx_RptYKPKhxx();
//初始化销售公司信息用于客户筛选
initxsgs_RptYKPKhxx()
//初始化使用软件信息用于客户筛选
initSoft_RptYKPKhxx()

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
    if ($.CurrentNavtab.find("#extends_items").css('display') == "inline") {
        $.CurrentNavtab.find("#extends_items").css('display', 'none');
        $.CurrentNavtab.find("##showBtn").find("i").removeClass('fa fa-angle-double-left');
        $.CurrentNavtab.find("##showBtn").find("i").addClass('fa fa-angle-double-right');
    }
    else {
        $.CurrentNavtab.find("#extends_items").css('display', 'inline');
        $.CurrentNavtab.find("##showBtn").find("i").removeClass('fa fa-angle-double-right');
        $.CurrentNavtab.find("##showBtn").find("i").addClass('fa fa-angle-double-left');
    }
    //刷新界面高度
    $($.CurrentNavtab).resizePageH();
    //刷新表格大小
    $($.CurrentNavtab.find("#datatable_rpt_ykpKhxx")).datagrid("resizeGridByAuto");
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