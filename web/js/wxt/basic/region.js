/**
 * Created by wuxiutong on 2015/8/26.
 */
var settingForRegion = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: region_zTreeBeforeClick,
        onClick: region_zTreeOnClick
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
function region_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#region").attr("disabled")) {
        return false;
    } else {
        return (treeNode.id !== false);
    }
}
;
function region_zTreeOnClick(event, treeId, treeNode) {
    $.ajax({
        url: 'RegionBaseServlet.getOneDqxx',
        data: {dqdm: treeNode.id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            //修改是否可修改属性
            $.CurrentNavtab.find("#dqdm").attr("readonly", "readonly");
            $.CurrentNavtab.find("#dqmc").attr("readonly", "readonly");
            $.CurrentNavtab.find("#postcode").attr("readonly", "readonly");
            $.CurrentNavtab.find("#fzr").attr("readonly", "readonly");
            $.CurrentNavtab.find("#fzrXm").attr("readonly", "readonly");
            $.CurrentNavtab.find("#ps").attr("readonly", "readonly");

            $.CurrentNavtab.find("#alter").css("display", "inline");
            $.CurrentNavtab.find("#dqdm").css("background-color", "lightgrey");
            $.CurrentNavtab.find("#dqdm").attr("value", json.dqdm);
            $.CurrentNavtab.find("#dqmc").attr("value", json.dqmc);
            $.CurrentNavtab.find("#postcode").attr("value", json.postcode);
            $.CurrentNavtab.find("#fzr").attr("value", json.fzr);
            $.CurrentNavtab.find("#fzrXm").attr("value", json.fzrXm);
            $.CurrentNavtab.find("#ps").attr("value", json.ps);
            $.CurrentNavtab.find("#add").css("display", "none");
            $.CurrentNavtab.find("#alter").css("display", "inline");
            $.CurrentNavtab.find("#fzr").attr("disabled", "disabled");
            $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'none');
        }
    });
}
;
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
//搜索节点
function region_searchNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("region");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        alert(node.name);
    }
} //删除节点
function region_removeNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("region");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        zTree.removeNode(node);
    }
}
function returnTrue() {
    return true;
}
//删除节点及其数据库数据
function region_delNode(dqdm) {
    $(this).alertmsg("confirm", "确认删除，继续？", {
        okCall:  function() {
            $.ajax({
                url: 'RegionBaseServlet.del',
                data: {dqdm: dqdm},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        //修改是否可修改属性
                        $.CurrentNavtab.find("#dqdm").removeAttr("readonly");
                        $.CurrentNavtab.find("#dqmc").removeAttr("readonly");
                        $.CurrentNavtab.find("#postcode").removeAttr("readonly");
                        $.CurrentNavtab.find("#fzr").removeAttr("readonly");
                        $.CurrentNavtab.find("#ps").removeAttr("readonly");
                        $.CurrentNavtab.find("#dqdm").css("background-color", "white");
                        $.CurrentNavtab.find("#dqdm").attr("value", "");
                        $.CurrentNavtab.find("#dqmc").attr("value", "");
                        $.CurrentNavtab.find("#postcode").attr("value", "");
                        $.CurrentNavtab.find("#fzr").attr("value", "");
                        $.CurrentNavtab.find("#ps").attr("value", "");
                        $.CurrentNavtab.find("#add").css("display", "inline");
                        $.CurrentNavtab.find("#alter").css("display", "none");
                        $.CurrentNavtab.find("#del").css("display", "none");
                        $.CurrentNavtab.find("#save").css("display", "none");
                        region_removeNode(dqdm);
                        $.CurrentNavtab.find("#cancle_dqxx").trigger("click");
                    }else {
                        $(this).alertmsg("error", json.message);
                    }
                }
            });
        }
        , cancelCall: function () {
            return false;
        }, okName: "确认", cancelName: "取消"
    })


}
//修改地区信息
function alterNodeAjaxDone(json) {
    if(json.statusCode==200) {
        var dqdm = json.dqdm;
        var dqmc = json.dqmc;
        var zTree = $.fn.zTree.getZTreeObj("region");
        var node = zTree.getNodeByParam("id", dqdm);
        if (node) {
            node.name = dqdm + "-" + dqmc;
            zTree.updateNode(node);
            $.CurrentNavtab.find("#cancle_dqxx").trigger("click");
        }
    }else {
        $(this).alertmsg("error",json.message);
    }
};
//增加节点
var newCount = 1;
function region_addNode(json) {
    var dqdm = json.dqdm;
    var dqmc = json.dqmc;
    var parentDm = json.parentDm;
    var zTree = $.fn.zTree.getZTreeObj("region"),
        nodes = zTree.getNodeByParam("id", parentDm)
    zTree.addNodes(nodes, {id: dqdm, pId: parentDm, isParent: false, name: dqdm + "-" + dqmc});
    $.CurrentNavtab.find("#cancle_dqxx").trigger("click");
};

$.fn.zTree.init($.CurrentNavtab.find("#region"), settingForRegion),
    // $.CurrentNavtab.find("#add").bind("click", {isParent:false}, add),
    $.CurrentNavtab.find("#region_searchNodes").bind("click", function () {
        region_searchNode("100");
    }),
    //绑定删除事件
    $.CurrentNavtab.find("#del").bind("click", function () {
        region_delNode($.CurrentNavtab.find("#dqdm").val());
        $.CurrentNavtab.find("#region").removeAttr("disabled");
        return false;
    }),
    //去掉修改按钮的默认提交属性 直接return false即可
    $.CurrentNavtab.find("#alter").bind("click", function () {
        $.CurrentNavtab.find("#dqmc").removeAttr("readonly");
        $.CurrentNavtab.find("#postcode").removeAttr("readonly");
        $.CurrentNavtab.find("#ps").removeAttr("readonly");
        $.CurrentNavtab.find("#del").css("display", "inline");
        $.CurrentNavtab.find("#alter").css("display", "none");
        $.CurrentNavtab.find("#save").css("display", "inline");
        $.CurrentNavtab.find("#region").attr("disabled", "disabled");
        $.CurrentNavtab.find("#fzr").removeAttr("disabled");
        $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'inline');
        return false;
    }),
    $.ajax({
        url: "GetFjfa", data: {"dm": "dqfj"}, dataType: "json", success: function (json) {
            $.CurrentNavtab.find("#dqfjfa").text("地区编码分级方案：" + json.fjfa);
        }
    }),
    $.CurrentNavtab.find("#save").bind("click", function () {
        $.CurrentNavtab.find("#dqxxForm").attr("action", "RegionBaseServlet.alter");
        $.CurrentNavtab.find("#save").css("display", "none");
        $.CurrentNavtab.find("#add").css("display", "inline");
        $.CurrentNavtab.find("#alter").css("display", "none");
        $.CurrentNavtab.find("#del").css("display", "none");
        $.CurrentNavtab.find("#region").removeAttr("disabled");
    }),
    $.CurrentNavtab.find("#cancle_dqxx").bind("click", function () {
        removeFormDisabled("#dqxxForm");
        clearForm("#dqxxForm");
        removeFormReadonly("#dqxxForm");
        $.CurrentNavtab.find("#fzr").attr("readonly", "readonly");
        $.CurrentNavtab.find("#fzrXm").attr("readonly", "readonly");
        $.CurrentNavtab.find("#dqdm").css("background-color", "white");
        $.CurrentNavtab.find("#region").removeAttr("disabled");
        $.CurrentNavtab.find("#region").removeAttr("readonly");
        $.CurrentNavtab.find("#add").css("display", "inline");
        $.CurrentNavtab.find("#alter").css("display", "none");
        $.CurrentNavtab.find("#save").css("display", "none");
        $.CurrentNavtab.find("#del").css("display", "none");
        $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'inline');
        return false;
    })
//提交完数据后的增加节点操作
function navTabDqxxAjaxDone(json) {
    if (json.statusCode == 200) {
        if (json.altered) { //如果返回到的信息提示是修改则直接修改ztree
            alterNodeAjaxDone(json);
            $.CurrentNavtab.find("#dqxxForm").attr("action", "RegionBaseServlet.add");
            $.CurrentNavtab.find("#dqdm").attr("value", "");
            $.CurrentNavtab.find("#dqdm").removeAttr("readonly");
            $.CurrentNavtab.find("#dqdm").css("background-color", "white");
            $.CurrentNavtab.find("#dqmc").attr("value", "");
            $.CurrentNavtab.find("#ps").attr("value", "");
            $.CurrentNavtab.find("#fzr").attr("value", "");
            $.CurrentNavtab.find("#postcode").attr("value", "");
        } else {
            region_addNode(json);
        }
    }
}
function ztree_returnjson() {
    // $.ajax()
    return [{id: 1, pid: 0, name: '表单元素', children: [{id: 10, pId: 1, name: '按钮'}, {id: 11, pId: 1, name: '文本框'}]}]

}

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})