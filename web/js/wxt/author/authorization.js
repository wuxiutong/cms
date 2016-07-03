/**
 * Created by wuxiutong on 15/8/29.
 */
$.CurrentNavtab.find("#authorization li a.role").bind("click", function (e) {
  //  $(this).tab('show')
    $($.CurrentNavtab.find(".footer-role")).css("display","inline");
    $($.CurrentNavtab.find(".footer-user")).css("display","none");
})
$.CurrentNavtab.find("#authorization li a.users").bind("click", function (e) {
    //  $(this).tab('show')
    $($.CurrentNavtab.find(".footer-role")).css("display","none");
    $($.CurrentNavtab.find(".footer-user")).css("display","inline");
})


function role_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#ztree_role").attr("disabled")) {
        return false;
    } else {

    }
};
function author_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#ztree_authorization").attr("disabled")) {
       // alert("clicked")
        return false;
    } else {
        $.CurrentNavtab.find("#ztree_authorization").get
    }
};
function role_zTreeOnClick(event, treeId, treeNode) {
    //  alert(treeNode.id);
    var settingForAuthor = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, callback: {
            beforeClick: author_zTreeBeforeClick
        },
        async: {
            enable: true,
            url: "RoleBaseServlet.getOneRoleAuthor?dm=" + treeNode.id,
            autoParam: [],
            dataFilter: filter,
            dataType: "json"
        },
        check: {
            enable: true
        }
    }
    //初始化权限设置树
    $.fn.zTree.init($("#ztree_authorization"), settingForAuthor);
    //修改按钮
    $.CurrentNavtab.find("#alterRoleAuthor").css("display", "inline");
    $.CurrentNavtab.find("#alterRole").css("display", "inline");
    $.CurrentNavtab.find("#delRole").css("display", "inline");
    $.CurrentNavtab.find("#addRole").css("display", "inline");
    $.CurrentNavtab.find("#refreshRole").css("display", "inline");
    $.CurrentNavtab.find("#saveRole").css("display", "none");
    $.CurrentNavtab.find("#cancel").css("display", "none");
};
function navTabAuthorizationAjaxDone(json) {
    if (json.statusCode == 200) {
        $(this).alertmsg("ok", json.message);
    } else {
        $(this).alertmsg("error", json.message);
    }
}
function navTabAjaxDone(json) {
    if (json.statusCode == 200) {
        $(this).alertmsg("ok", json.message);
    } else {
        $(this).alertmsg("error", json.message);
    }
}

function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
var settingForRole = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    }, callback: {
        beforeClick: role_zTreeBeforeClick,
        onClick: role_zTreeOnClick
    },
    async: {
        enable: true,
        url: "RoleBaseServlet.getAll",
        autoParam: [],
        dataFilter: filter,
        dataType: "json"
    }
}
$.fn.zTree.init($("#ztree_role"), settingForRole);

$.CurrentNavtab.find("#alterRole").bind("click", function () {

    var roleTreeObj = $.fn.zTree.getZTreeObj("ztree_role");
    var roleSelectNode = roleTreeObj.getSelectedNodes();
    if (roleSelectNode.length == 1) {
        $(this).dialog({id: 'alterRole', url: 'admin/alterRole.jsp', title: '修改角色',mask:true});
    }
})

$.CurrentNavtab.find("#addRole").bind("click", function () {
    $(this).dialog({id: 'addRole', url: 'admin/addRole.jsp', title: '增加角色',mask:true});
})

$.CurrentNavtab.find("#alterRoleAuthor").bind("click", function () {
    $.CurrentNavtab.find("#ztree_role").attr("disabled", "disabled");
    var treeObj = $.fn.zTree.getZTreeObj("ztree_authorization");
    var nodes = treeObj.getNodes();
    for (var i = 0, l = nodes.length; i < l; i++) {
        treeObj.setChkDisabled(nodes[i], false, true, true);
    }
    $.CurrentNavtab.find("#addRole").css("display", "none");
    $.CurrentNavtab.find("#refreshRole").css("display", "none");
    $.CurrentNavtab.find("#delRole").css("display", "none");
    $.CurrentNavtab.find("#alterRole").css("display", "none");
    $.CurrentNavtab.find("#alterRoleAuthor").css("display", "none");
    $.CurrentNavtab.find("#saveRole").css("display", "inline");
    $.CurrentNavtab.find("#cancel").css("display", "inline");

})

$.CurrentNavtab.find("#saveRole").bind("click", function () {
    var qx = "";
    $.CurrentNavtab.find("#ztree_role").removeAttr("disabled");
    var roleTreeObj = $.fn.zTree.getZTreeObj("ztree_role");
    var treeObj = $.fn.zTree.getZTreeObj("ztree_authorization");
    var nodes1 = treeObj.getCheckedNodes(true);
    var roleSelectNode = roleTreeObj.getSelectedNodes();
    //循环获取勾选的功能
    for (var i = 0, l = nodes1.length; i < l; i++) {
        if(!nodes1[i].isParent) {
            qx = qx + nodes1[i].id + ',';
        }
    }
    $.ajax({
        url: 'RoleBaseServlet.updateRoleAuthor',
        data: {dm: roleSelectNode[0].id, qx: qx},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);

                //循环设置为不可修改
                for (var i = 0, l = nodes1.length; i < l; i++) {
                    treeObj.setChkDisabled(nodes1[i], true, true, true);
                }
                $.CurrentNavtab.find("#addRole").css("display", "inline");
                $.CurrentNavtab.find("#refreshRole").css("display", "inline");
                $.CurrentNavtab.find("#delRole").css("display", "none");
                $.CurrentNavtab.find("#alterRole").css("display", "none");
                $.CurrentNavtab.find("#alterRoleAuthor").css("display", "none");
                $.CurrentNavtab.find("#saveRole").css("display", "none");
                $.CurrentNavtab.find("#cancel").css("display", "none");
            }
            else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
})
$.CurrentNavtab.find("#delRole").bind("click", function () {
    $(this).alertmsg("confirm", "即将删除，是否继续！", {
        okCall: function () {
            var qx = "";
            var roleTreeObj = $.fn.zTree.getZTreeObj("ztree_role");
            var authorTreeObj = $.fn.zTree.getZTreeObj("ztree_authorization");
            var roleSelectNode = roleTreeObj.getSelectedNodes();
            $.ajax({
                url: 'RoleBaseServlet.del',
                data: {dm: roleSelectNode[0].id},
                cache: true,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        //$(this).alertmsg("ok", json.message);
                        roleTreeObj.removeNode(roleSelectNode[0]);
                        $.CurrentNavtab.find("#ztree_role").removeAttr("disabled");
                        $.CurrentNavtab.find("#addRole").css("display", "inline");
                        $.CurrentNavtab.find("#refreshRole").css("display", "inline");
                        $.CurrentNavtab.find("#delRole").css("display", "none");
                        $.CurrentNavtab.find("#alterRole").css("display", "none");
                        $.CurrentNavtab.find("#alterRoleAuthor").css("display", "none");
                        $.CurrentNavtab.find("#saveRole").css("display", "none");
                        $.CurrentNavtab.find("#cancel").css("display", "none");
                        authorTreeObj.destroy();
                    }
                    else {
                        $(this).alertmsg("error", json.message);
                    }
                }
            });
        }, cancelCall: function () {

        }, okName: "是", cancelName: "否"
    })

})

$.CurrentNavtab.find("#refreshRole").bind("click", function () {
    var qx = "";
    var roleTreeObj = $.fn.zTree.getZTreeObj("ztree_role");
    var authorTreeObj = $.fn.zTree.getZTreeObj("ztree_authorization");
    if (authorTreeObj) {
        authorTreeObj.destroy();
    }
    roleTreeObj.destroy();
    $.fn.zTree.init($("#ztree_role"), settingForRole);
    $.CurrentNavtab.find("#ztree_role").removeAttr("disabled");
    $.CurrentNavtab.find("#addRole").css("display", "inline");
    $.CurrentNavtab.find("#refreshRole").css("display", "inline");
    $.CurrentNavtab.find("#delRole").css("display", "none");
    $.CurrentNavtab.find("#alterRole").css("display", "none");
    $.CurrentNavtab.find("#alterRoleAuthor").css("display", "none");
    $.CurrentNavtab.find("#saveRole").css("display", "none");
    $.CurrentNavtab.find("#cancel").css("display", "none");

})

$.CurrentNavtab.find("#cancel").bind("click", function () {
    var qx = "";
    var roleTreeObj = $.fn.zTree.getZTreeObj("ztree_role");
    var authorTreeObj = $.fn.zTree.getZTreeObj("ztree_authorization");
    if (authorTreeObj) {
        authorTreeObj.destroy();
    }
    var roleSelectNode = roleTreeObj.getSelectedNodes();
    role_zTreeOnClick(null, roleSelectNode[0].id, roleSelectNode[0]);
    $.CurrentNavtab.find("#ztree_role").removeAttr("disabled");
})

