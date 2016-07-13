/**
 * Created by wuxiutong on 15/8/25.
 */
var settingForDwlx = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: dwlx_zTreeBeforeClick,
        onClick: dwlx_zTreeOnClick
    },
    async: {
        enable: true,
        url: "Dwlx.getAll.action",
        autoParam: ["id", "name=n", "level=lv"],
        otherParam: {"otherParam": "zTreeAsyncTest"},
        dataFilter: dwlx_filter,
        dataType: "json"
    }
};
function dwlx_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#ztree_dwlx").attr("disabled")) {
        return false;
    } else
        return (treeNode.id !== false);
};
function dwlx_zTreeOnClick(event, treeId, treeNode) {
    $.ajax({
        url: 'Dwlx.getOneDwlx.action',
        data: {lxdm: treeNode.id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            //修改是否可修改属性
            $.CurrentNavtab.find("#lxdm").attr("readonly", "readonly");
            $.CurrentNavtab.find("#lxmc").attr("readonly", "readonly");
            $.CurrentNavtab.find("#dwlxps").attr("readonly", "readonly");
            $.CurrentNavtab.find("#lxdm").css("background-color", "lightgrey");
            $.CurrentNavtab.find("#lxdm").attr("value", json.lxdm);
            $.CurrentNavtab.find("#lxmc").attr("value", json.lxmc);
            $.CurrentNavtab.find("#dwlxps").attr("value", json.ps);
            $.CurrentNavtab.find("#add_dwlx").css("display", "none");
            $.CurrentNavtab.find("#alter_dwlx").css("display", "inline");
        }
    });
};
function dwlx_filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
//搜索节点
function searchNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        alert(node.name);
    }
} //删除节点
function dwlx_removeNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("ztree_dwlx");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        zTree.removeNode(node);
    }
}
function returnTrue() {
    return true;
}
//删除节点及其数据库数据
function dwlx_delNode(lxdm) {
    $(this).alertmsg("confirm", "确认删除，是否继续？", {
        mask: true, okName: "确定", cancleName: "取消", okCall: function () {
            $.ajax({
                url: 'Dwlx.del.action',
                data: {lxdm: lxdm},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if(json.statusCode==200){
                    clearForm("#dwlxForm");
                    //修改是否可修改属性
                    $.CurrentNavtab.find("#lxdm").removeAttr("readonly");
                    $.CurrentNavtab.find("#lxdm").removeAttr("readonly");
                    $.CurrentNavtab.find("#dwlxps").removeAttr("readonly");
                    $.CurrentNavtab.find("#lxmc").removeAttr("readonly");
                    $.CurrentNavtab.find("#ztree_dwlx").removeAttr("disabled");
                    $.CurrentNavtab.find("#ztree_dwlx").removeAttr("readonly");
                    $.CurrentNavtab.find("#dwlxForm").attr("action", "AddDwlx");
                    dwlx_removeNode(lxdm);
                    $.CurrentNavtab.find("#cancle_dwlx").trigger("click");
                    }else{
                        $(this).alertmsg("error", json.message);
                    }
                }
            });
        }, cancelCall: function () {
            return false;
        }
    })

}
//修改地区信息
function dwlx_alterNodeAjaxDone(json) {
    //alert("alert");
    var lxdm = json.lxdm;
    var lxmc = json.lxmc;
    var zTree = $.fn.zTree.getZTreeObj("ztree_dwlx");
    var node = zTree.getNodeByParam("id", lxdm);
    if (node) {
        node.name = lxdm + "-" + lxmc;
        zTree.updateNode(node);
    }
};
//增加节点
function dwlx_addNode(json) {
    var lxdm = json.lxdm;
    var lxmc = json.lxmc;
    var pearentDm = json.pearentDm;
    var zTree = $.fn.zTree.getZTreeObj("ztree_dwlx");
    var pearentNode = zTree.getNodeByParam("id", pearentDm);
    if (pearentNode) {
        zTree.addNodes(pearentNode, {id: lxdm, pId: pearentDm, isParent: false, name: lxdm + "-" + lxmc});
    } else {
        zTree.addNodes(null, {id: lxdm, pId: 0, isParent: false, name: lxdm + "-" + lxmc});
    }
    $.CurrentNavtab.find("#cancle_dwlx").trigger("click");
};
//var ztree_dwlx =
//if(null!=$.fn.zTree.getZTreeObj("ztree_dwlx")){
//    alert($.fn.zTree.getZTreeObj("ztree_dwlx"))
//}else{
$.fn.zTree.init($("#ztree_dwlx"), settingForDwlx);
//}
//ztree_dwlx$.fn.zTree.getZTreeObj("ztree_dwlx");
//绑定删除事件
$.CurrentNavtab.find("#del_dwlx").bind("click", function () {
    dwlx_delNode($("#lxdm").val());
    return false;
});

$.ajax({
    url: "GetFjfa", data: {"dm": "dwlxfj"}, dataType: "json", success: function (json) {
        $("#dwlxfj").text("单位类型编码分级方案：" + json.fjfa);
    }
});

//去掉修改按钮的默认提交属性 直接return false即可
$.CurrentNavtab.find("#alter_dwlx").bind("click", function () {
    removeFormReadonly("#dwlxForm");
    $("#lxdm").attr("readonly", "readonly");
    $("#del_dwlx").css("display", "inline");
    $("#alter_dwlx").css("display", "none");
    $("#save_dwlx").css("display", "inline");
    $("#ztree_dwlx").attr("disabled", "disabled");
    $("#dwlxForm").attr("action", "Dwlx.alter.action");
    //$("#dwlxForm").attr("onsubmit", "return validateCallback(this, navTabDwlxAjaxDone,'确认修改吗？');");
    return false;
});

$.CurrentNavtab.find("#save_dwlx").bind("click", function () {
    $("#save_dwlx").css("display", "none");
    $("#add_dwlx").css("display", "inline");
    $("#alter_dwlx").css("display", "none");
    $("#del_dwlx").css("display", "none");
    $("#ztree_dwlx").removeAttr("disabled");
});

$.CurrentNavtab.find("#cancle_dwlx").bind("click", function () {
    removeFormReadonly("#dwlxForm");
    removeFormDisabled("#dwlxForm");
    clearForm("#dwlxForm");
    $.CurrentNavtab.find("#ztree_dwlx").removeAttr("disabled");
    $.CurrentNavtab.find("#lxdm").css("background-color", "white");
    $.CurrentNavtab.find("#ztree_dwlx").removeAttr("readonly");
    $.CurrentNavtab.find("#add_dwlx").css("display", "inline");
    $.CurrentNavtab.find("#alter_dwlx").css("display", "none");
    $.CurrentNavtab.find("#save_dwlx").css("display", "none");
    $.CurrentNavtab.find("#del_dwlx").css("display", "none");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#dwlxForm").attr("action", "Dwlx.add.action");
    //$.CurrentNavtab.find("#dwlxForm").attr("onsubmit", "return validateCallback(this, navTabDwlxAjaxDone,'确认提交吗？');");
    return false;
});

//提交完数据后的增加节点操作
function navTabAlterDWLXAjaxDone(json) {
    if (json.statusCode == 200) {
        if (json.altered) { //如果返回到的信息提示是修改则直接修改ztree
            dwlx_alterNodeAjaxDone(json);
            $.CurrentNavtab.find("#dwlxForm").attr("action", "Dwlx.add.action");
            clearForm("#dwlxForm");
            removeFormReadonly("#dwlxForm")
        } else {
            $.CurrentNavtab.find("#dwlxForm").attr("action", "Dwlx.add.action");
            dwlx_addNode(json);
        }
    } else {
        $(this).alertmsg("error", json.message);
    }
}

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})