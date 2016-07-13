/**
 * Created by wuxiutong on 2015/8/26.
 */

//提交完数据后的增加节点操作
function navTabBmxxAjaxDone(json) {
    if (json.statusCode == 200) {
        $.CurrentNavtab.find("#bmzy").alertmsg('ok', json.message);
        //此处添加节点！
        addBmxxNode(json);
    } else {
        $(this).alertmsg('error', json.message);
    }
} //提交完数据后的增加节点操作
function navTabZyxxAjaxDone(json) {
    if (json.statusCode == 200) {
        $.CurrentNavtab.find("#bmzy").alertmsg('ok', json.message);
        //此处添加节点！
        addZyxxNode(json);
    } else {
        $(this).alertmsg('error', json.message);
    }
}
var settingForBMZY = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: bmzy_zTreeBeforeClick,
        onClick: bmzy_zTreeOnClick
    },
    async: {
        enable: true,
        url: "Bmxx.getBmZy.action",
        autoParam: ["id", "name=n", "level=lv"],
        otherParam: {"otherParam": "zTreeAsyncTest"},
        dataFilter: filter,
        dataType: "json"
    }
};
function bmzy_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#bmzy").attr("disabled")) {
        return false;
    } else {

    }
};
function bmzy_zTreeOnClick(event, treeId, treeNode) {
    //  alert(treeNode.id);
    $.ajax({
        url: 'Bmxx.getOneBmOrZy.action',
        data: {dm: treeNode.id, mark: 'zy_'},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (treeNode.id.indexOf("zy_") >= 0) { //如果截取到职员的标志，则
                $.CurrentNavtab.find("#zydm").attr("value", json.zydm);
                $.CurrentNavtab.find("#zyxm").attr("value", json.zyxm);
                $.CurrentNavtab.find("#zy").attr("value", json.zy);
                $.CurrentNavtab.find("#ps_zy").attr("value", json.ps);
                $.CurrentNavtab.find("#birthday").attr("value", json.birthday);
                $.CurrentNavtab.find("#byxx").attr("value", json.byxx);
                $.CurrentNavtab.find("#jtzz").attr("value", json.jtzz);
                $.CurrentNavtab.find("#lxdh").attr("value", json.lxdh);
                $.CurrentNavtab.find("#mz").attr("value", json.mz);
                $.CurrentNavtab.find("#rzrq").attr("value", json.rzrq);
                $.CurrentNavtab.find("#bmzy_sex").attr("value", json.sex);
                $.CurrentNavtab.find("#sfzh").attr("value", json.sfzh);
                $.CurrentNavtab.find("#ssbm").attr("value", json.ssbm);
                $.CurrentNavtab.find("#ssbmMc").attr("value", json.ssbmMc);
                $.CurrentNavtab.find("#xjdz").attr("value", json.xjdz);
                $.CurrentNavtab.find("#xl").attr("value", json.xl);
                $.CurrentNavtab.find("#zydm").attr("readonly", "readonly");
                $.CurrentNavtab.find("#bmxxForm").css("display", "none");
                $.CurrentNavtab.find("#zyxxForm").css("display", "inline");
                //设置修改和删除按钮
                $.CurrentNavtab.find("#save_zy").css("display", "none");
                $.CurrentNavtab.find("#add_zy").css("display", "none");
                $.CurrentNavtab.find("#alter_zy").css("display", "inline");
                $.CurrentNavtab.find("#del_zy").css("display", "none");
                //addFormReadonly("#zyxxForm");
                addFormDisabled("#zyxxForm");
                $.CurrentNavtab.find("#rzrq").siblings("a.bjui-lookup").css("display", 'none');
                $.CurrentNavtab.find("#birthday").siblings("a.bjui-lookup").css("display", 'none');
                $.CurrentNavtab.find("#ssbm").siblings("a.bjui-lookup").css("display", 'none');
                //  $.CurrentNavtab.find("#ssbm").attr("disabled","disabled");
            } else { //否则则是部门信息
                $.CurrentNavtab.find("#bmdm").attr("value", json.bmdm)
                $.CurrentNavtab.find("#bmmc").attr("value", json.bmmc)
                $.CurrentNavtab.find("#fzr").attr("value", json.fzr)
                $.CurrentNavtab.find("#fzrxm").attr("value", json.fzrxm)
                $.CurrentNavtab.find("#ps_bm").attr("value", json.ps)
                $.CurrentNavtab.find("#zyxxForm").css("display", "none");
                $.CurrentNavtab.find("#bmxxForm").css("display", "inline");
                //设置修改和删除按钮
                $.CurrentNavtab.find("#save_bm").css("display", "none");
                $.CurrentNavtab.find("#add_bm").css("display", "none");
                $.CurrentNavtab.find("#alter_bm").css("display", "inline");
                $.CurrentNavtab.find("#del_bm").css("display", "none");
                addFormDisabled("#bmxxForm");
                $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'none');
                //  $.CurrentNavtab.find("#fzr").attr("disabled","disabled");
            }
        }
    });
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
//删除节点
function bmzy_removeNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("bmzy");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        zTree.removeNode(node);
    }
}
function returnTrue() {
    return true;
}
//删除节点及其数据库数据
function delBmxxNode(bmdm) {
    $(this).alertmsg("confirm", "确认删除,是否继续", {
        okCall: function () {
            $.ajax({
                url: 'Bmxx.del.action',
                data: {bmdm: bmdm},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        bmzy_removeNode(bmdm);
                        clearForm("#bmxxForm");
                        $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
                        $.CurrentNavtab.find("#zydm").removeAttr("readonly");
                        //设置修改和删除按钮
                        $.CurrentNavtab.find("#add_bm").css("display", "inline");
                        $.CurrentNavtab.find("#del_bm").css("display", "none");
                        $.CurrentNavtab.find("#alter_bm").css("display", "none");
                        $.CurrentNavtab.find("#save_bm").css("display", "none");
                        $.CurrentNavtab.find("#cancle_zy").trigger("click");
                    } else {
                        $(this).alertmsg("error", json.message);
                    }
                }
            });
        }, cancelCall: function () {

        },
        okName: "确定",
        cancelName: "取消"
    })
};    //删除节点及其数据库数据
function delZyxxNode(zydm) {
    $(this).alertmsg("confirm", "确认删除,是否继续", {
        okCall: function () {
            $.ajax({
                url: 'Zyxx.del.action',
                data: {zydm: zydm},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        var zTree = $.fn.zTree.getZTreeObj("bmzy");
                        var node = zTree.getNodeByParam("id", "zy_" + zydm);
                        if (node) {
                            zTree.removeNode(node);
                            clearForm("#zyxxForm");
                            $.CurrentNavtab.find("#zydm").removeAttr("readonly");
                            $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
                            $.CurrentNavtab.find("#add_zy").css("display", "inline");
                            $.CurrentNavtab.find("#del_zy").css("display", "none");
                            $.CurrentNavtab.find("#alter_zy").css("display", "none");
                            $.CurrentNavtab.find("#save_zy").css("display", "none");
                            $.CurrentNavtab.find("#cancle_zy").trigger("click");
                        }
                    }
                    else {
                        $(this).alertmsg("error", json.message);
                    }
                }
            });
        }, cancelCall: function () {

        },
        okName: "确定",
        cancelName: "取消"
    })
};

//修改职员信息
function alterBmxxDone(json) {
    //alert("alert");
    var bmdm = json.bmdm;
    var bmmc = json.bmmc;
    var zTree = $.fn.zTree.getZTreeObj("bmzy");
    var node = zTree.getNodeByParam("id", bmdm);
    if (node) {
        node.name = bmdm + "-" + bmmc;
        zTree.updateNode(node);
    }
    $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
    clearForm("#bmxxForm");
    $.CurrentNavtab.find("#add_bm").css("display", "inline");
    $.CurrentNavtab.find("#del_bm").css("display", "none");
    $.CurrentNavtab.find("#alter_bm").css("display", "none");
    $.CurrentNavtab.find("#save_bm").css("display", "none");
    $.CurrentNavtab.find("#bmdm").removeAttr("readonly");
    $.CurrentNavtab.find("#bmxxForm").attr("action", "Bmxx.add.action");
    $.CurrentNavtab.find("#bmxxForm").attr("data-callback", "navTabBmxxAjaxDone");
    $.CurrentNavtab.find("#bmzy").alertmsg('ok', json.message);
}
;
//修改职员信息
function alterZyxxDone(json) {
    var zydm = json.zydm;
    var zyxm = json.zyxm;
    var zTree = $.fn.zTree.getZTreeObj("bmzy");
    var node = zTree.getNodeByParam("id", "zy_" + zydm);
    if (node) {
        var parentValue = node.getParentNode().name;
        parentValue = parentValue.substring(0, parentValue.indexOf("-"));
        if (parentValue == json.pid) {
            node.name = zydm + "-" + zyxm;
            zTree.updateNode(node);
        } else {
            zTree.removeNode(node);
            addZyxxNode(json);
        }
    }
    $.CurrentNavtab.find("#add_zy").css("display", "inline");
    $.CurrentNavtab.find("#del_zy").css("display", "none");
    $.CurrentNavtab.find("#alter_zy").css("display", "none");
    $.CurrentNavtab.find("#save_zy").css("display", "none");
    $.CurrentNavtab.find("#zydm").removeAttr("readonly");
    $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
    clearForm("#zyxxForm");
    $.CurrentNavtab.find("#zyxxForm").attr("action", "Zyxx.AddZyxx.action");
    $.CurrentNavtab.find("#bmxxForm").attr("data-callback", "navTabZyxxAjaxDone");
    $.CurrentNavtab.find("#bmzy").alertmsg('ok', json.message);
};
//增加节点
var newCount = 1;
//增加部门节点的操作！
function addBmxxNode(json) {
    var bmdm = json.dm;
    var bmmc = json.mc;
    var parentDm = json.parentDm;
    var zTree = $.fn.zTree.getZTreeObj("bmzy"),
        nodes = zTree.getNodeByParam("id", parentDm)
    zTree.addNodes(nodes, {id: bmdm, pId: parentDm, isParent: false, name: bmdm + "-" + bmmc});
    clearForm("#zyxxForm");
};
//增加职员信息节点的操作
function addZyxxNode(json) {
    var zydm = json.zydm;
    var zyxm = json.zyxm;
    var parentDm = json.parentDm;
    var zTree = $.fn.zTree.getZTreeObj("bmzy"),
        nodes = zTree.getNodeByParam("id", parentDm)
    zTree.addNodes(nodes, {id: 'zy_' + zydm, pId: parentDm, isParent: false, name: zydm + "-" + zyxm});
    $(':input', '#zyxxForm')
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
};
$.CurrentNavtab.find("#addBmxx").bind("click", function () {

    $.CurrentNavtab.find("#zyxxForm").css("display", "none");
    $.CurrentNavtab.find("#bmxxForm").css("display", "inline");
    $.CurrentNavtab.find("#bmdm").removeAttr("readonly");
    $.CurrentNavtab.find("#add_bm").css("display", "inline");
    $.CurrentNavtab.find("#del_bm").css("display", "none");
    $.CurrentNavtab.find("#alter_bm").css("display", "none");
    $.CurrentNavtab.find("#save_bm").css("display", "none");
    clearForm("#bmxxForm");
    removeFormDisabled("#bmxxForm");
    removeFormReadonly("#bmxxForm");
    $.CurrentNavtab.find("#fzrxm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#cancle_bm").trigger("click");
    return false;
});
//读取部门编码分级方案
$.ajax({
    url: "GetFjfa", data: {"dm": "bmfj"}, dataType: "json", success: function (json) {
        $.CurrentNavtab.find("#bmfjfa").text("部门编码分级方案：" + json.fjfa);
    }
});
$.CurrentNavtab.find("#addZyxx").bind("click", function () {
    $.CurrentNavtab.find("#zyxxForm").css("display", "inline");
    $.CurrentNavtab.find("#bmxxForm").css("display", "none");
    $.CurrentNavtab.find("#zydm").removeAttr("readonly");
    $.CurrentNavtab.find("#add_zy").css("display", "inline");
    $.CurrentNavtab.find("#del_zy").css("display", "none");
    $.CurrentNavtab.find("#alter_zy").css("display", "none");
    $.CurrentNavtab.find("#save_zy").css("display", "none");
    clearForm("#zyxxForm");
    $.CurrentNavtab.find("#bmzy").attr("disabled", "disabled");
    removeFormReadonly("#zyxxForm");
    removeFormDisabled("#zyxxForm");
    $.CurrentNavtab.find("#rzrq").attr("readonly", "readonly");
    $.CurrentNavtab.find("#birthday").attr("readonly", "readonly");
    $.CurrentNavtab.find("#cancle_zy").trigger("click");
    return false;
});
$.CurrentNavtab.find("#alter_zy").bind("click", function () {
    $.CurrentNavtab.find("#save_zy").css("display", "inline");
    $.CurrentNavtab.find("#add_zy").css("display", "none");
    $.CurrentNavtab.find("#alter_zy").css("display", "none");
    $.CurrentNavtab.find("#del_zy").css("display", "inline");
    $.CurrentNavtab.find("#bmzy").attr("disabled", "disabled");
    removeFormDisabled("#zyxxForm");
    $.CurrentNavtab.find("#zydm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#ssbm").removeAttr("disabled");
    $.CurrentNavtab.find("#rzrq").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#birthday").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#ssbm").siblings("a.bjui-lookup").css("display", 'inline');
    return false;
});
$.CurrentNavtab.find("#del_zy").bind("click", function () {
    delZyxxNode($.CurrentNavtab.find("#zydm").val().trim());
    return false;
});
$.CurrentNavtab.find("#save_zy").bind("click", function () {
    $.CurrentNavtab.find("#zyxxForm").attr("action", "Zyxx.alter.action");
    $.CurrentNavtab.find("#zyxxForm").attr("data-callback", "alterZyxxDone");
});
$.CurrentNavtab.find("#cancle_zy").bind("click", function () {
    clearForm('#zyxxForm');
    removeFormDisabled('#zyxxForm');
    removeFormReadonly('#zyxxForm');
    $('#zyxxForm').removeAttr("disabled")
    $.CurrentNavtab.find("#save_zy").css("display", "none");
    $.CurrentNavtab.find("#add_zy").css("display", "inline");
    $.CurrentNavtab.find("#alter_zy").css("display", "none");
    $.CurrentNavtab.find("#del_zy").css("display", "none");
    $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
    $.CurrentNavtab.find("#rzrq").attr("readonly", "readonly");
    $.CurrentNavtab.find("#birthday").attr("readonly", "readonly");
    $.CurrentNavtab.find("#rzrq").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#birthday").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#ssbm").siblings("a.bjui-lookup").css("display", 'inline');
    return false;
});
//部门信息编辑
$.CurrentNavtab.find("#del_bm").bind("click", function () {
    delBmxxNode($("#bmdm").val().trim());
    return false;
});

$.CurrentNavtab.find("#alter_bm").bind("click", function () {
    $.CurrentNavtab.find("#add_bm").css("display", "none");
    $.CurrentNavtab.find("#del_bm").css("display", "inline");
    $.CurrentNavtab.find("#alter_bm").css("display", "none");
    $.CurrentNavtab.find("#save_bm").css("display", "inline");
    $.CurrentNavtab.find("#bmdm").attr("readonly", "readonly");
    removeFormDisabled("#bmxxForm");
    $.CurrentNavtab.find("#bmdm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#fzr").removeAttr("disabled");
    $.CurrentNavtab.find("#bmzy").attr("disabled", "disabled");
    $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'inline');
    return false;
});
$.CurrentNavtab.find("#save_bm").bind("click", function () {
    $.CurrentNavtab.find("#bmxxForm").attr("action", "Bmxx.alter.action");
    $.CurrentNavtab.find("#bmxxForm").attr("data-callback", "alterBmxxDone");
});
$.CurrentNavtab.find("#cancle_bm").bind("click", function () {
    clearForm('#bmxxForm');
    removeFormDisabled('#bmxxForm');
    $('#bmxxForm').removeAttr("disabled")
    $.CurrentNavtab.find("#save_bm").css("display", "none");
    $.CurrentNavtab.find("#add_bm").css("display", "inline");
    $.CurrentNavtab.find("#alter_bm").css("display", "none");
    $.CurrentNavtab.find("#del_bm").css("display", "none");
    $.CurrentNavtab.find("#bmzy").removeAttr("disabled");
    $.CurrentNavtab.find("#fzrxm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#fzr").siblings("a.bjui-lookup").css("display", 'inline');
    return false;
});

$.fn.zTree.init($("#bmzy"), settingForBMZY);

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})