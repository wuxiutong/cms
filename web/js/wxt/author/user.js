/**
 * Created by wuxiutong on 15/8/30.
 */


function user_zTreeBeforeClick(treeId, treeNode, clickFlag) {
    if ($.CurrentNavtab.find("#ztree_users").attr("disabled")) {
        //alert("clicked")
        return false;
    } else {
        // $.CurrentNavtab.find("#ztree_authorization").get
    }
};
function user_zTreeOnClick(event, treeId, treeNode) {
    //  alert(treeNode.id);
    var settingForUserAuthor = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, callback: {
            beforeClick: user_zTreeBeforeClick
        },
        async: {
            enable: true,
            url: "UserBaseServlet.getUserRoles?dm=" + treeNode.id,
            autoParam: [],
            dataFilter: filter,
            dataType: "json"
        },
        check: {
            enable: true
        }
    };
    var settingForAuthorItems = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }, callback: {
            beforeClick: user_zTreeBeforeClick
        },
        async: {
            enable: true,
            url: "UserBaseServlet.getOtherRoles?dm=" + treeNode.id,
            autoParam: [],
            dataFilter: filter,
            dataType: "json"
        },
        check: {
            enable: true
        }
    }
    //初始化已经授权树
    $.fn.zTree.init($("#ztree_UserAuthor"), settingForUserAuthor);
    //初始化未授权用户
    $.fn.zTree.init($("#ztree_AuthorItems"), settingForAuthorItems);
    //修改按钮;
    $.CurrentNavtab.find("#alterUser").css("display", "inline");
    $.CurrentNavtab.find("#delUser").css("display", "inline");
    $.CurrentNavtab.find("#addUser").css("display", "inline");
    $.CurrentNavtab.find("#refreshUser").css("display", "inline");
};
var settingForUsers = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    }, callback: {
        beforeClick: user_zTreeBeforeClick,
        onClick: user_zTreeOnClick
    },
    async: {
        enable: true,
        url: "UserBaseServlet.getAll",
        autoParam: [],
        dataFilter: filter,
        dataType: "json"
    }
}
$.fn.zTree.init($("#ztree_users"), settingForUsers);
//刷新按钮事件
$.CurrentNavtab.find("#refreshUser").bind("click", function () {
    var userAuthorObj = $.fn.zTree.getZTreeObj("ztree_UserAuthor");
    userAuthorObj.destroy()
    var AuthorItemsObj = $.fn.zTree.getZTreeObj("ztree_AuthorItems");
    AuthorItemsObj.destroy();
    var userObj = $.fn.zTree.getZTreeObj("ztree_users");
    userObj.destroy();
    $.fn.zTree.init($("#ztree_users"), settingForUsers);
});

//授权按钮事件
$.CurrentNavtab.find("#grant").bind("click", function () {
    var userObj = $.fn.zTree.getZTreeObj("ztree_users");
    var userNode = userObj.getSelectedNodes();
    var userID = userNode[0].id;
    if (userID) { //检测如果用户未选中任何项目
        var userAuthorObj = $.fn.zTree.getZTreeObj("ztree_UserAuthor")
        var AuthorItemsObj = $.fn.zTree.getZTreeObj("ztree_AuthorItems")
        var authorNodes = AuthorItemsObj.getCheckedNodes();
        if (authorNodes.length > 0) {
            //获取授权
            var nodes = AuthorItemsObj.getCheckedNodes();
            var nodes_old = userAuthorObj.getNodes();
            var ssrole = "";
            for (var j = 0; j < nodes_old.length; j++) {
                ssrole = ssrole + nodes_old[j].id + ","
            }
            for (var i = 0; i < nodes.length; i++) {
                ssrole = ssrole + nodes[i].id + ","
            }
            $.ajax({
                url: 'UserBaseServlet.updateUserRoles',
                data: {userID: userID, ssrole: ssrole},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        for (var i = 0, l = authorNodes.length; i < l; i++) {
                            AuthorItemsObj.removeNode(authorNodes[i]);
                        }
                        userAuthorObj.addNodes(null, authorNodes);
                    } else {
                        $(this).alertmsg("error", json.message)
                        isOK = false;
                    }
                }
            });
        } else {
            $(this).alertmsg("warn", "请先选择")
        }
    } else {
        $(this).alertmsg("warn", "未选中user！")
    }
});
//取消授权按钮事件
$.CurrentNavtab.find("#ungrant").bind("click", function () {
    var userObj = $.fn.zTree.getZTreeObj("ztree_users");
    var userNode = userObj.getSelectedNodes();
    var userID = userNode[0].id;
    if (userID) { //检测如果用户未选中任何项目
        var userAuthorObj = $.fn.zTree.getZTreeObj("ztree_UserAuthor")
        var AuthorItemsObj = $.fn.zTree.getZTreeObj("ztree_AuthorItems")
        var userNodes = userAuthorObj.getCheckedNodes();
        if (userNodes.length > 0) {
            //获取授权
            var nodes = userAuthorObj.getNodes();
            var nodes_notSelect  = userAuthorObj.getCheckedNodes();
            var ssrole = "";
            for (var i = 0; i < nodes.length; i++) {
                //检测该节点是否是选中状态，如果是选择状态则跳过
                for(var j = 0 ;j<nodes_notSelect.length;j++){
                     if(nodes[i].id!==nodes_notSelect[j].id){
                         ssrole = ssrole + nodes[i].id + ","
                     }
                }
            }
            $.ajax({
                url: 'UserBaseServlet.updateUserRoles',
                data: {userID: userID, ssrole: ssrole},
                cache: false,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    if (json.statusCode == 200) {
                        //修改是否可修改属性
                        for (var i = 0, l = userNodes.length; i < l; i++) {
                            userAuthorObj.removeNode(userNodes[i]);
                        }
                        AuthorItemsObj.addNodes(null, userNodes);
                    } else {
                        $(this).alertmsg("error", json.message)
                    }
                }
            });
        } else {
            $(this).alertmsg("warn", "请先选择")
        }
    } else {
        $(this).alertmsg("warn", "未选中user！")
    }
});
//增加按钮事件
$.CurrentNavtab.find("#addUser").bind("click", function () {
    $(this).dialog({id: 'addUser', url: 'admin/addUser.jsp', title: '增加用户', mask: true});
});
//修改按钮事件
$.CurrentNavtab.find("#alterUser").bind("click", function () {
    var userObj = $.fn.zTree.getZTreeObj("ztree_users");
    var userNode = userObj.getSelectedNodes();
    if (userNode.length > 0) {
        $(this).dialog({id: 'alterUser', url: 'admin/alterUser.jsp', title: '修改用户', mask: true});
    }
});
//删除用户按钮事件
$.CurrentNavtab.find("#delUser").bind("click", function () {
    var userObj = $.fn.zTree.getZTreeObj("ztree_users");
    var selectedNode = userObj.getSelectedNodes();
    $.ajax({
        url: 'UserBaseServlet.del',
        data: {userID: selectedNode[0].id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (json.statusCode == 200) {
                $(this).alertmsg("ok", json.message);
                userObj.removeNode(selectedNode[0]);
            }
            else {
                $(this).alertmsg("error", json.message);
            }
        }
    });

});
//更新数据库中的用户权限信息
function updateUserAuthor(userID, ssrole) {
    var isOK = false;
    $.ajax({
        url: 'UserBaseServlet.updateUserRoles',
        data: {userID: userID, ssrole: ssrole},
        cache: false,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (json.statusCode == 200) {
                //修改是否可修改属性
                isOK = true;
            } else {
                $(this).alertmsg("error", json.message)
                isOK = false;
            }
        }
    });
    return isOK;
}
