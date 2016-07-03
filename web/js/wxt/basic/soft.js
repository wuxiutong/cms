/**
 * Created by wuxiutong on 15/8/25.
 */

var settingSoft = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: soft_zTreeBeforeClick,
        onClick: soft_zTreeOnClick
    },
    async: {
        enable: true,
        url: "SoftModelBaseServlet.getAllGysVerModelForTree",
        autoParam: ["id", "name=n", "level=lv"],
        otherParam: {"otherParam": "zTreeAsyncTest"},
        datasoft_filter: soft_filter,
        dataType: "json"
    }
};
function soft_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#gyssoft").attr("disabled")) {
        return false;
    } else {

    }
};
function soft_zTreeOnClick(event, treeId, treeNode) {
    //如果选中的是供应商则执行下面
    if ((treeNode.id).startsWith('gys_')) {
        $.ajax({
            url: 'GysBaseServlet.getOneGys',
            data: {gysDm: (treeNode.id).substr((treeNode.id).indexOf("_") + 1, 100)},
            cache: true,
            async: true,
            type: "POST",
            dataType: 'json',
            success: function (json) {
                //修改是否可修改属性

                $('#bill_box_tabs a[href="#tab_soft_gys"]').tab('show');
                $.CurrentNavtab.find("#gysdm").attr("readonly", "readonly");
                $.CurrentNavtab.find("#gysmc").attr("readonly", "readonly");
                $.CurrentNavtab.find("#ps").attr("readonly", "readonly");
                $.CurrentNavtab.find("#address").attr("readonly", "readonly");
                $.CurrentNavtab.find("#alter_gys").css("display", "inline");
                $.CurrentNavtab.find("#del_gys").css("display", "none");
                $.CurrentNavtab.find("#add_gys").css("display", "none");
                $.CurrentNavtab.find("#save_gys").css("display", "none");
                $.CurrentNavtab.find("#gysdm").attr("value", json.gysdm);
                $.CurrentNavtab.find("#gysmc").attr("value", json.gysmc);
                $.CurrentNavtab.find("#address").attr("value", json.address);
                $.CurrentNavtab.find("#ps").attr("value", json.ps);
            }
        });
    } else if ((treeNode.id).startsWith('ver_')) { //如果是软件版本则执行虾下面
        $.ajax({
            url: 'SoftVerBaseServlet.getOneSoftVer',
            data: {verDm: (treeNode.id).substr((treeNode.id).indexOf("_") + 1, 100)},
            cache: true,
            async: true,
            type: "POST",
            dataType: 'json',
            success: function (json) {

                $('#bill_box_tabs a[href="#tab_soft_ver"]').tab('show');
                //修改是否可修改属性
                addFormReadonly("#softVerForm");
                $.CurrentNavtab.find("#alter_ver").css("display", "inline");
                $.CurrentNavtab.find("#del_ver").css("display", "none");
                $.CurrentNavtab.find("#save_ver").css("display", "none");
                $.CurrentNavtab.find("#add_ver").css("display", "none");
                $.CurrentNavtab.find("#ver_gysDm").attr("value", json.gysdm);
                $.CurrentNavtab.find("#ver_gysDm").siblings("a.bjui-lookup").css("display", 'none');
                $.CurrentNavtab.find("#ver_gysMc").attr("value", json.gysmc);
                $.CurrentNavtab.find("#verPs").attr("value", json.ps);
                $.CurrentNavtab.find("#verDm").attr("value", json.verdm);
                $.CurrentNavtab.find("#verMc").attr("value", json.vermc);
            }
        });
    } else if ((treeNode.id).startsWith('model_')) { //如果是软件版本则执行虾下面
        $.ajax({
            url: 'SoftModelBaseServlet.getOneGysVerModeForJsonByModel',
            data: {modelDm: (treeNode.id).substr((treeNode.id).indexOf("_") + 1, 100)},
            cache: true,
            async: true,
            type: "POST",
            dataType: 'json',
            success: function (json) {
                //修改是否可修改属性
                $('#bill_box_tabs a[href="#tab_soft_model"]').tab('show');
                $.CurrentNavtab.find("#modeldm").attr("readonly", "readonly");
                $.CurrentNavtab.find("#modelmc").attr("readonly", "readonly");
                $.CurrentNavtab.find("#modelps").attr("readonly", "readonly");
                $.CurrentNavtab.find("#model_gysdm").attr("readonly", "readonly");
                $.CurrentNavtab.find("#model_gysmc").attr("readonly", "readonly");
                $.CurrentNavtab.find("#model_vermc").attr("readonly", "readonly");
                $.CurrentNavtab.find("#model_verdm").attr("readonly", "readonly");
                $.CurrentNavtab.find("#model_verdm").attr("disabled", "disabled");
                $.CurrentNavtab.find("#alter_model").css("display", "inline");
                $.CurrentNavtab.find("#del_model").css("display", "none");
                $.CurrentNavtab.find("#save_model").css("display", "none");
                $.CurrentNavtab.find("#add_model").css("display", "none");
                $.CurrentNavtab.find("#model_gysdm").attr("value", json.gysdm);
                $.CurrentNavtab.find("#model_gysmc").attr("value", json.gysmc);
                $.CurrentNavtab.find("#modeldm").attr("value", json.modeldm);
                $.CurrentNavtab.find("#modelmc").attr("value", json.modelmc);
                $.CurrentNavtab.find("#modelps").attr("value", json.ps);
                $.CurrentNavtab.find("#model_verdm").attr("value", json.verdm);
                $.CurrentNavtab.find("#model_verdm").siblings("a.bjui-lookup").css("display", 'none');
                $.CurrentNavtab.find("#model_vermc").attr("value", json.vermc);

            }
        });
    }
};
function soft_filter(treeId, parentNode, childNodes) {
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
function soft_removeNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        zTree.removeNode(node);
    } else {

        alert(" wei zhao dao ");
    }
}
function returnTrue() {
    return true;
}
function soft_delGys() {
    $(this).alertmsg('confirm', '该操作将删除当前选中的节点信息，是否继续？', {
        displayMode: 'fade', displayPosition: 'middlecenter', okCall: function () {
            var gysdm = $.CurrentNavtab.find("#gysdm").val();
            $.ajax({
                url: "GysBaseServlet.del",
                data: {gysdm: gysdm},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if(json.statusCode==200) {
                    clearForm("#gysForm");//清除form中的所有控件内容
                    removeFormReadonly("#gysForm");//去除控件的只读属性
                    removeFormDisabled("#gysForm");//去除控件的只读属性
                    //更改修改按钮的action为增加
                    $.CurrentNavtab.find("#gysForm").attr("action", "GysBaseServlet.add");
                    $.CurrentNavtab.find("#gysForm").attr("data-callback", "navTabSoftGysAjaxDone");
                    $.CurrentNavtab.find("#save_gys").css("display", "none");
                    $.CurrentNavtab.find("#alter_gys").css("display", "inline");
                    $.CurrentNavtab.find("#del_gys").css("display", "none");
                    $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
                    soft_removeNode("gys_" + gysdm);
                    $.CurrentNavtab.find("#btn_softGysCancel").trigger("click");
                    }else{
                        $(this).alertmsg("error",json.message)
                    }
                }
            });
        }
        , okName: 'Yes', cancelName: 'no', title: '警告！'
    })
}
//删除供应商节点及其数据库数据

//删除软件版本节点及其数据库数据
function soft_delVer() {
    $(this).alertmsg('confirm', '该操作将删除当前选中的节点信息，是否继续？', {
        displayMode: 'fade',
        displayPosition: 'middlecenter',
        okCall:
            function () {
                var verDm = $.CurrentNavtab.find("#verDm").val();
                {
                    $.ajax({
                        url: "SoftVerBaseServlet.del",
                        data: {verDm: verDm},
                        cache: false,
                        async: true,
                        type: "POST",
                        dataType: 'json',
                        success: function (json) {
                            if(json.statusCode==200) {
                            clearForm("#softVerForm");//清除form中的所有控件内容
                            removeFormReadonly("#softVerForm");//去除控件的只读属性
                            removeFormDisabled("#softVerForm");//去除控件的只读属性
                            //更改修改按钮的action为增加
                            $.CurrentNavtab.find("#softVerForm").attr("action", "SoftVerBaseServlet.add");
                            $.CurrentNavtab.find("#softVerForm").attr("data-callback", "navTabSoftVerAjaxDone");
                            $.CurrentNavtab.find("#save_ver").css("display", "none");
                            $.CurrentNavtab.find("#alter_ver").css("display", "inline");
                            $.CurrentNavtab.find("#del_ver").css("display", "none");
                            $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
                            soft_removeNode("ver_" + verDm);
                            $.CurrentNavtab.find("#btn_softVerCancel").trigger("click");
                        }else{
                            $(this).alertmsg("error",json.message)
                        }
                        }
                    });
                }
            },
        okName: 'Yes',
        cancelName: 'no',
        title: '警告！'
    })
};
//删除软件版本节点及其数据库数据
function soft_delModel() {
    $(this).alertmsg('confirm', '该操作将删除当前选中的节点信息，是否继续？', {
        displayMode: 'fade',
        displayPosition: 'middlecenter',
        okCall:
            function () {
                var modeldm = $.CurrentNavtab.find("#modeldm").val();
                {
                    $.ajax({
                        url: "SoftModelBaseServlet.del",
                        data: {modeldm: modeldm},
                        cache: false,
                        async: true,
                        type: "POST",
                        dataType: 'json',
                        success: function (json) {
                            if(json.statusCode==200) {
                            clearForm("#softModelForm");//清除form中的所有控件内容
                            removeFormReadonly("#softModelForm");//去除控件的只读属性
                            removeFormDisabled("#softModelForm");//去除控件的只读属性
                            //更改修改按钮的action为增加
                            $.CurrentNavtab.find("#softModelForm").attr("action", "SoftModelBaseServlet.add");
                            $.CurrentNavtab.find("#softModelForm").attr("data-callback", "navTabSoftModelAjaxDone");
                            $.CurrentNavtab.find("#save_model").css("display", "none");
                            $.CurrentNavtab.find("#alter_model").css("display", "inline");
                            $.CurrentNavtab.find("#del_model").css("display", "none");
                            $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
                            soft_removeNode("model_" + modeldm);
                            $.CurrentNavtab.find("#btn_softModelCancel").trigger("click");
                            }else{
                                $(this).alertmsg("error",json.message)
                            }
                        }
                    });
                }
            },
        okName: 'Yes',
        cancelName: 'no',
        title: '警告！'
    })
}
//增加供应商节点
function soft_addGysNode(json) {
    var gysdm = json.gysdm;
    var gysmc = json.gysmc;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    //alert("dm:"+gysdm+";mc: "+gysmc);
    zTree.addNodes(null, {id: 'gys_' + gysdm, pId: 0, isParent: false, name: gysdm + "-" + gysmc});
};
//增加版本节点
function soft_addVerNode(json) {
    var verdm = json.verdm;
    var gysdm = json.gysdm;
    var vermc = json.vermc;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    //alert("dm:"+gysdm+";mc: "+gysmc);
    var node = zTree.getNodeByParam('id', 'gys_' + gysdm)
    zTree.addNodes(node, {id: 'ver_' + verdm, pId: 'gys_' + gysdm, isParent: false, name: verdm + "-" + vermc});
}
;
//增加模块节点
function soft_addModelNode(json) {
    var modelDm = json.modelDm;
    var modelMc = json.modelMc;
    var verDm = json.verDm;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    //alert("dm:"+gysdm+";mc: "+gysmc);
    var node = zTree.getNodeByParam('id', 'ver_' + verDm)
    zTree.addNodes(node, {
        id: 'model_' + modelDm,
        pId: 'ver_' + verDm,
        isParent: false,
        name: modelDm + "-" + modelMc
    });
}
;
//修改供应商节点
function soft_alterGysNode(json) {
    var gysdm = json.gysdm;
    var gysmc = json.gysmc;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    var node = zTree.getNodeByParam("id", "gys_" + gysdm);
    if (node) {
        node.name = gysdm + "-" + gysmc;
        zTree.updateNode(node);
    }
}
;
//修改软件版本节点
function soft_alterVerNode(json) {
    var verdm = json.verdm;
    var vermc = json.vermc;
    var gysdm = json.gysdm;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    var node = zTree.getNodeByParam("id", "ver_" + verdm);
    if (node) {
        node.name = verdm + "-" + vermc;
        if (node.pId == 'gys_' + gysdm) {
            zTree.updateNode(node);
        } else {
            var node_gys = zTree.getNodeByParam("id", "gys_" + gysdm);
            zTree.removeNode(node);
            node.pId = 'gys_' + gysdm;
            zTree.addNodes(node_gys, node, false);
        }
    }
}
;
//修改模块版本节点
function soft_alterModelNode(json) {
    var modeldm = json.modeldm;
    var modelmc = json.modelmc;
    var verdm = json.verdm;
    var zTree = $.fn.zTree.getZTreeObj("gyssoft");
    var node = zTree.getNodeByParam("id", "model_" + modeldm);
    if (node) {
        node.name = modeldm + "-" + modelmc;
        if (node.pId == 'ver_' + verdm) {
            zTree.updateNode(node);
        } else {
            var node_ver = zTree.getNodeByParam("id", "ver_" + verdm);
            zTree.removeNode(node);
            node.pId = 'ver_' + verdm;
            zTree.addNodes(node_ver, node, false);
        }
    }
};
$.fn.zTree.init($.CurrentNavtab.find("#gyssoft"), settingSoft);
//  增加软件版本的按钮事件
$.CurrentNavtab.find("#addSoftVer").bind("click", function () {
    $('#bill_box_tabs a[href="#tab_soft_ver"]').tab('show');
    clearForm("#softVerForm");
    removeFormReadonly("#softVerForm");
    removeFormDisabled("#softVerForm");
    $.CurrentNavtab.find("#softVerForm").attr("action", "SoftVerBaseServlet.add");
    $.CurrentNavtab.find("#softVerForm").attr("data-callback", "navTabSoftVerAjaxDone");
    $.CurrentNavtab.find("#btn_softVerCancel").trigger("click");
});
//  增加软件供应商的按钮事件
$.CurrentNavtab.find("#addGys").bind("click", function () {
    $('#bill_box_tabs a[href="#tab_soft_gys"]').tab('show');
    clearForm("#gysForm");
    removeFormReadonly("#gysForm");
    removeFormDisabled("#gysForm");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#gysForm").attr("action", "GysBaseServlet.add");
    $.CurrentNavtab.find("#gysForm").attr("data-callback", "navTabSoftGysAjaxDone");
    $.CurrentNavtab.find("#btn_softGysCancel").trigger("click");
});
//  增加软件模块的按钮事件
$.CurrentNavtab.find("#addSoftModel").bind("click", function () {
    $('#bill_box_tabs a[href="#tab_soft_model"]').tab('show');
    clearForm("#softModelForm");
    removeFormReadonly("#softModelForm");
    removeFormDisabled("#softModelForm");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#softModelForm").attr("action", "SoftModelBaseServlet.add");
    $.CurrentNavtab.find("#softModelForm").attr("data-callback", "navTabSoftModelAjaxDone");
    $.CurrentNavtab.find("#btn_softModelCancel").trigger("click");
});
//供应商修改按钮操作
$.CurrentNavtab.find("#alter_gys").bind("click", function () {
//                $.CurrentNavtab.find("#gysdm").css("background-color", "lightgrey");
    $.CurrentNavtab.find("#gysdm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#gysmc").removeAttr("readonly");
    $.CurrentNavtab.find("#address").removeAttr("readonly");
    $.CurrentNavtab.find("#ps").removeAttr("readonly");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#gysForm").attr("action", "GysBaseServlet.alter");
    $.CurrentNavtab.find("#gysForm").attr("data-callback", "navTabSoftGysAjaxDone");
    $.CurrentNavtab.find("#save_gys").css("display", "inline");
    $.CurrentNavtab.find("#alter_gys").css("display", "none");
    $.CurrentNavtab.find("#del_gys").css("display", "inline");
    $.CurrentNavtab.find("#gyssoft").attr("disabled", "true");
    $.CurrentNavtab.find("#gyssoft").attr("disabled", "disabled");
    return false;
});
$.CurrentNavtab.find("#save_gys").bind("click", function () {
    $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
});
//供应商删除按钮操作
$.CurrentNavtab.find("#del_gys").bind("click", function () {
    soft_delGys($.CurrentNavtab.find("#gysdm").val());//删除供应商
    return false;
});
$.CurrentNavtab.find("#btn_softGysCancel").bind("click", function () {
    $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
    removeFormDisabled("#gysForm");
    removeFormReadonly("#gysForm");
    clearForm("#gysForm");
    $.CurrentNavtab.find("#add_gys").css("display", "inline");
    $.CurrentNavtab.find("#alter_gys").css("display", "none");
    $.CurrentNavtab.find("#save_gys").css("display", "none");
    $.CurrentNavtab.find("#del_gys").css("display", "none");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#gysForm").attr("action", "GysBaseServlet.add");
    $.CurrentNavtab.find("#gysForm").attr("data-callback", "navTabSoftGysAjaxDone");
});
$.CurrentNavtab.find("#btn_softVerCancel").bind("click", function () {
    $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
    removeFormDisabled("#softVerForm");
    removeFormReadonly("#softVerForm");
    clearForm("#softVerForm");
    $.CurrentNavtab.find("#add_ver").css("display", "inline");
    $.CurrentNavtab.find("#alter_ver").css("display", "none");
    $.CurrentNavtab.find("#save_ver").css("display", "none");
    $.CurrentNavtab.find("#del_ver").css("display", "none");
    $.CurrentNavtab.find("#ver_gysDm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#ver_gysMc").attr("readonly", "readonly");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#softVerForm").attr("action", "SoftVerBaseServlet.add");
    $.CurrentNavtab.find("#softVerForm").attr("data-callback", "navTabSoftVerAjaxDone");
    $.CurrentNavtab.find("#ver_gysDm").siblings("a.bjui-lookup").css("display", 'inline');
});
$.CurrentNavtab.find("#btn_softModelCancel").bind("click", function () {
    $.CurrentNavtab.find("#gyssoft").removeAttr("disabled");
    removeFormDisabled("#softModelForm");
    removeFormReadonly("#softModelForm");
    clearForm("#softModelForm");
    //模块取消按钮事件
    $.CurrentNavtab.find("#modeldm").css("background-color", "white");
    $.CurrentNavtab.find("#add_model").css("display", "inline");
    $.CurrentNavtab.find("#del_model").css("display", "none");
    $.CurrentNavtab.find("#save_model").css("display", "none");
    $.CurrentNavtab.find("#alter_model").css("display", "none");
    $.CurrentNavtab.find("#model_gysdm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#model_gysmc").attr("readonly", "readonly");
    $.CurrentNavtab.find("#model_verdm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#model_vermc").attr("readonly", "readonly");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#softModelForm").attr("action", "SoftModelBaseServlet.add");
    $.CurrentNavtab.find("#softModelForm").attr("data-callback", "navTabSoftModelAjaxDone");
    $.CurrentNavtab.find("#model_verdm").siblings("a.bjui-lookup").css("display", 'inline');
    return false;
});
//版本修改按钮事件响应
$.CurrentNavtab.find("#alter_ver").bind("click", function () {
    removeFormDisabled("#softVerForm");
    removeFormReadonly("#softVerForm");
    $.CurrentNavtab.find("#verDm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#add_ver").css("display", "none");
    $.CurrentNavtab.find("#alter_ver").css("display", "none");
    $.CurrentNavtab.find("#save_ver").css("display", "inline");
    $.CurrentNavtab.find("#del_ver").css("display", "inline");
    $.CurrentNavtab.find("#gyssoft").attr("disabled", "disabled");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#softVerForm").attr("action", "SoftVerBaseServlet.alter");
    $.CurrentNavtab.find("#softVerForm").attr("data-callback", "navTabSoftVerAjaxDone");
    $.CurrentNavtab.find("#ver_gysDm").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#ver_gysDm").attr("readonly", "readonly");
    $.CurrentNavtab.find("#ver_gysMc").attr("readonly", "readonly");
    return false;
});
//版本删除按钮操作
$.CurrentNavtab.find("#del_ver").bind("click", function () {
    soft_delVer($.CurrentNavtab.find("#verDm").val());//删除供应商
    return false;
});
//模块删除按钮操作
$.CurrentNavtab.find("#del_model").bind("click", function () {
    soft_delModel($.CurrentNavtab.find("#modeldm").val());//删除供应商
    return false;
});
//版本模块按钮事件响应
$.CurrentNavtab.find("#alter_model").bind("click", function () {
    $.CurrentNavtab.find("#modelmc").removeAttr("readonly")
    $.CurrentNavtab.find("#modelps").removeAttr("readonly")
    $.CurrentNavtab.find("#model_verdm").attr("readonly","readonly")
    $.CurrentNavtab.find("#model_verdm").siblings("a.bjui-lookup").css("display", 'inline');
    $.CurrentNavtab.find("#model_verdm").removeAttr("disabled")
    $.CurrentNavtab.find("#modeldm").css("background-color", "lightgray");
    $.CurrentNavtab.find("#add_model").css("display", "none");
    $.CurrentNavtab.find("#alter_model").css("display", "none");
    $.CurrentNavtab.find("#save_model").css("display", "inline");
    $.CurrentNavtab.find("#del_model").css("display", "inline");
    $.CurrentNavtab.find("#gyssoft").attr("disabled", "disabled");
    //更改修改按钮的action为修改
    $.CurrentNavtab.find("#softModelForm").attr("action", "SoftModelBaseServlet.alter");
    $.CurrentNavtab.find("#softModelForm").attr("data-callback", "navTabSoftModelAjaxDone");
    return false;
});
//增加好供应商提示
function navTabSoftGysAjaxDone(json) {
    // DWZ.ajaxDone(json);
    if ('200' == json.statusCode) {
        if ("GysBaseServlet.add" == ($.CurrentNavtab.find("#gysForm").attr("action"))) { //如果action是增加则直接增加节点
//                DWZ.ajaxDone(json);
            soft_addGysNode(json);
        } else { //如果不是addGys则修改节点名称
//                DWZ.ajaxDone(json);
            soft_alterGysNode(json); //修改树数据
        }
        //最终执行取消按钮事件清空空间内容
        $("#btn_softGysCancel").trigger('click');
    } else {
        $(this).alertmsg("error", json.message, "错误");
    }
};
//修改软件版本完成函数
function navTabSoftVerAjaxDone(json) {
    // DWZ.ajaxDone(json);
    if ('200' == json.statusCode) {
        if ("SoftVerBaseServlet.add" == ($.CurrentNavtab.find("#softVerForm").attr("action"))) { //如果action是增加则直接增加节点
//                DWZ.ajaxDone(json);
            soft_addVerNode(json);
        } else { //如果不是addSoftVer则修改节点名称
            soft_alterVerNode(json); //修改树数据
            //供应商表单内容，
        }

        //最终执行取消按钮事件清空空间内容
        $("#btn_softVerCancel").trigger('click');
    } else {
        $(this).alertmsg("error", json.message, "错误");
    }
};
//修改版本完成模块
function navTabSoftModelAjaxDone(json) {
    // DWZ.ajaxDone(json);
    if ('200' == json.statusCode) {
        if ("SoftModelBaseServlet.add" == ($.CurrentNavtab.find("#softModelForm").attr("action"))) { //如果action是增加则直接增加节点
//                DWZ.ajaxDone(json);
            soft_addModelNode(json);
        } else { //如果不是addSoftVer则修改节点名称
//                DWZ.ajaxDone(json);
            soft_alterModelNode(json); //修改树数据
        }
        //最终执行取消按钮事件清空空间内容
        $("#btn_softModelCancel").trigger('click');
    } else {
        $(this).alertmsg("error", json.message, "错误");
    }
}
;

$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})