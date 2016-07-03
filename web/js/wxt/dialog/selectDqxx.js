function initDialogDqxx() {

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i = 0, l = childNodes.length; i < l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {
            beforeClick: zTreeBeforeClick,
            onClick: zTreeOnClick,
            onDblClick: zTreeOnDblClick
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

    function zTreeBeforeClick(treeId, treeNode, clickFlag) {
//    if ($("#region").attr("disabled")) {
//      return false;
//    } else {
//      return (treeNode.id !== false);
//    }
    };
    function zTreeOnDblClick(event, treeId, treeNode) {
        //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
        $("#btn_lookbak").trigger("click");
        // return false;
    };
    function zTreeOnClick(event, treeId, treeNode) {
        $.ajax({
            url: 'RegionBaseServlet.getOneDqxx',
            data: {dqdm: treeNode.id},
            cache: true,
            async: true,
            type: "POST",
            dataType: 'json',
            success: function (json) {
                //修改是否可修改属性
                $("#dqdm").attr("value", json.dqdm);
                $("#dqmc").attr("value", json.dqmc);
                $("#postcode").attr("value", json.postcode);
                $("#fzr").attr("value", json.fzr);
                $("#fzrXm").attr("value", json.fzrXm);
                $("#ps").attr("value", json.ps);
                if (treeNode.isParent) {
                    $("#btn_lookbak").removeAttr("data-toggle");
                } else {
                    $("#btn_lookbak").attr("data-args", "{pid:'1', dqdm:'" + json.dqdm + "', dqdmmc:'" + json.dqdm + "[" + json.dqmc + "]" + "'}");
                    $("#btn_lookbak").attr("data-toggle", "lookupback");
                    //alert(treeNode.isParent+":"+$("#btn_lookbak").attr("data-args"));
                }

            }
        });
    };
    $.fn.zTree.init($("#AllRegionForDialog"), setting)
}
//初始化
initDialogDqxx();
//检测是否选择末级
function checkCurrent(){
    if('lookupback'!=$("#btn_lookbak").attr("data-toggle")){}
     //   $(this).alertmsg('info', '你未选中地区信息或者选择了非末级地区!', {displayMode:'fade', displayPosition:'middlecenter',  title:'提示'})
}
//搜索节点
function searchNode(id) {
    var zTree = $.fn.zTree.getZTreeObj("AllRegion");
    var node = zTree.getNodeByParam("id", id);
    if (node) {
        alert(node.name);
    }
}
function returnTrue() {
    return true;
}
