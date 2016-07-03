/**
 * Created by wuxiutong on 2015/8/26.
 */

var settingForBloc = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: bloc_zTreeBeforeClick,
        onClick: bloc_zTreeOnClick
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
function bloc_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#enterprise").attr("disabled")) {
        return false;
    } else
        return (treeNode.id !== false);
};
function bloc_zTreeOnClick(event, treeId, treeNode) {
    $.ajax({
        url: 'BlocBaseServlet.getOneBloc',
        data: {gsdm: treeNode.id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            //修改是否可修改属性
            $.CurrentNavtab.find("#gsdm").attr("readonly", "readonly");
            $.CurrentNavtab.find("#gsmc").attr("readonly", "readonly");
            $.CurrentNavtab.find("#workphone").attr("readonly", "readonly");
            $.CurrentNavtab.find("#type_en").attr("readonly", "readonly");
            $.CurrentNavtab.find("#type_en").attr("disabled", "disabled");
            $.CurrentNavtab.find("#enps").attr("readonly", "readonly");

            $.CurrentNavtab.find("#gsdm").css("background-color", "lightgrey");
            $.CurrentNavtab.find("#gsdm").attr("value", json.gsdm);
            $.CurrentNavtab.find("#gsmc").attr("value", json.gsmc);
            $.CurrentNavtab.find("#workphone").attr("value", json.workphone);
            $.CurrentNavtab.find("#enps").attr("value", json.ps);
            $.CurrentNavtab.find("#type_en").attr("value", json.type_en);
            $.CurrentNavtab.find("#add_en").css("display", "none");
            $.CurrentNavtab.find("#alter_en").css("display", "inline");
            addFormDisabled("#enterpriseForm");
            addFormReadonly("#enterpriseForm");
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

$.fn.zTree.init($("#enterprise"), settingForBloc);
//删除节点
function bloc_removeNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("enterprise");
    var node = zTree.getNodeByParam("id",id);
    if (node) {
        zTree.removeNode(node);
    } else {

    }
};
function returnTrue() {
    return true;
};
//删除节点及其数据库数据

function bloc_delNode() {
    var gsdm = $.CurrentNavtab.find("#gsdm").val();
        $.ajax({
            url: 'BlocBaseServlet.del',
            data: {gsdm: gsdm},
            cache: false,
            async: true,
            type: "POST",
            dataType: 'json',
            success: function (json) {
                if(json.statusCode==200) {
                    //修改是否可修改属性
                    removeFormReadonly("#enterpriseForm");
                    removeFormDisabled("#enterpriseForm");
                    bloc_removeNode(gsdm);
                    $.CurrentNavtab.find("#cancle_en").trigger("click");
                }else{
                    $(this).alertmsg("error",json.message)
                }
            }
        });
}
//修改信息
function bloc_alterNodeAjaxDone(json) {
    //alert("alert");
    var gsdm = json.gsdm;
    var gsmc = json.gsmc;
    var zTree = $.fn.zTree.getZTreeObj("enterprise");
    var node = zTree.getNodeByParam("id", gsdm);
    if (node) {
        node.name = gsdm + "-" + gsmc;
        zTree.updateNode(node);
    }
};
//增加节点
function bloc_addNode(json) {
    var gsdm = json.gsdm;
    var gsmc = json.gsmc;
    var zTree = $.fn.zTree.getZTreeObj("enterprise");
    zTree.addNodes(null, {id: gsdm, pId: 0, isParent: false, name: gsdm + "-" + gsmc});
};

    $.CurrentNavtab.find("#searchNodes").bind("click", function () {
        searchNode("100");
    });
    //去掉修改按钮的默认提交属性 直接return false即可
    $.CurrentNavtab.find("#alter_en").bind("click", function () {
        removeFormReadonly("#enterpriseForm");
        removeFormDisabled("#enterpriseForm");
        $.CurrentNavtab.find("#gsdm").attr("readonly", "readonly");
        $.CurrentNavtab.find("#del_en").css("display", "inline");
        $.CurrentNavtab.find("#alter_en").css("display", "none");
        $.CurrentNavtab.find("#save_en").css("display", "inline");
        $.CurrentNavtab.find("#enterprise").attr("disabled", "disabled");
        $.CurrentNavtab.find("#enterpriseForm").attr("action", "BlocBaseServlet.alter");
        $.CurrentNavtab.find("#enterpriseForm").attr("data-callback", "navTabEnterpriseAjaxDone");
        return false;
    });
    $.CurrentNavtab.find("#save_en").bind("click", function () {
        $.CurrentNavtab.find("#save_en").css("display", "none");
        $.CurrentNavtab.find("#add_en").css("display", "inline");
        $.CurrentNavtab.find("#alter_en").css("display", "none");
        $.CurrentNavtab.find("#del_en").css("display", "none");
        $.CurrentNavtab.find("#enterprise").removeAttr("disabled");
    });
    $.CurrentNavtab.find("#cancle_en").bind("click", function () {
        removeFormReadonly("#enterpriseForm");
        removeFormDisabled("#enterpriseForm");
        clearForm("#enterpriseForm");
        $.CurrentNavtab.find("#enterprise").removeAttr("disabled");
        $.CurrentNavtab.find("#enterprise").removeAttr("readonly");
        $.CurrentNavtab.find("#add_en").css("display", "inline");
        $.CurrentNavtab.find("#alter_en").css("display", "none");
        $.CurrentNavtab.find("#save_en").css("display", "none");
        $.CurrentNavtab.find("#del_en").css("display", "none");
        $.CurrentNavtab.find("#gsdm").css("background-color", "white");
        //更改修改按钮的action为修改
        $.CurrentNavtab.find("#enterpriseForm").attr("action", "BlocBaseServlet.add");
        $.CurrentNavtab.find("#enterpriseForm").attr("data-callback", "navTabEnterpriseAjaxDone");
        return false;
    });
//提交完数据后的增加节点操作
function navTabEnterpriseAjaxDone(json) {
    if (json.statusCode == 200) {
        if (json.altered) { //如果返回到的信息提示是修改则直接修改ztree
            bloc_alterNodeAjaxDone(json);
            $.CurrentNavtab.find("#enterpriseForm").attr("action", "BlocBaseServlet.add");
            clearForm("#enterpriseForm");
            removeFormReadonly("#enterpriseForm")
        } else {
            $.CurrentNavtab.find("#enterpriseForm").attr("action", "BlocBaseServlet.add");
            bloc_addNode(json);
        }
    }else {
        $(this).alertmsg("error",json.message);
    }
};

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})