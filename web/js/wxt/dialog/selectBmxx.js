
var settingForDialogBmxx = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        beforeClick: dialogBmxx_zTreeBeforeClick,
        onClick: dialogBmxx_zTreeOnClick,
        onDblClick: dialogBmxx_zTreeOnDblClick
    },
    async: {
        enable: true,
        url: "BmxxBaseServlet.getAll",
        autoParam: ["id", "name=n", "level=lv"],
        otherParam: {"otherParam": "zTreeAsyncTest"},
        dataFilter: filter,
        dataType: "json"
    }
};
function dialogBmxx_zTreeBeforeClick(treeId, treeNode, clickFlag) {
//    if ($("#region").attr("disabled")) {
//      return false;
//    } else {
//      return (treeNode.id !== false);
//    }
};
function dialogBmxx_zTreeOnDblClick(event, treeId, treeNode) {
    //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
   $.CurrentDialog.find("#btn_lookbak").trigger("click");
    // return false;
};
function dialogBmxx_zTreeOnClick(event, treeId, treeNode) {
    $.ajax({
        url: 'BmxxBaseServlet.getOneBmxx',
        data: {bmdm: treeNode.id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            //修改是否可修改属性
           $.CurrentDialog.find("#bmdm").attr("value", json.bmdm);
           $.CurrentDialog.find("#bmmc").attr("value", json.bmmc);
           $.CurrentDialog.find("#ps").attr("value", json.ps);
           $.CurrentDialog.find("#fzr").attr("value", json.fzr);
           $.CurrentDialog.find("#fzrxm").attr("value", json.fzrxm);
            if(treeNode.isParent){
               $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
            }else {
               $.CurrentDialog.find("#btn_lookbak").attr("data-args", "{pid:'1', bmdm:'" + json.bmdm +"', bmmc:'" + json.bmmc +"', bmdmmc:'" + json.bmdm + "[" + json.bmmc + "]" + "'}");
               $.CurrentDialog.find("#btn_lookbak").attr("data-toggle", "lookupback");
                //alert(treeNode.isParent+":"+$("#btn_lookbak").attr("data-args"));
            }

        }
    });
} ;
//检测是否选择末级
function dialogBmxx_checkCurrent(){
    if('lookupback'!=$("#btn_lookbak").attr("data-toggle")){
        return false;
    }
        //$(this).alertmsg('info', '你未选中地区信息或者选择了非末级地区!', {displayMode:'fade', displayPosition:'middlecenter',  title:'提示'})
}
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
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

$(document).ready(
    $.fn.zTree.init($("#AllBmxx"), settingForDialogBmxx)
);