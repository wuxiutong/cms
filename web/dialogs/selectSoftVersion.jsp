<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<SCRIPT type="text/javascript">
   // var selectSoftModelZtree ;
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
            url: "SoftVer.getAll.action",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
            dataType: "json"
        }
    };
   function zTreeOnDblClick(event, treeId, treeNode) {
       $.CurrentDialog.find("#btn_lookbak").trigger("click");
   };
    function zTreeBeforeClick(treeId, treeNode, clickFlag) {
        if ( $.CurrentDialog.find("#ztree_softVer").attr("disabled")) {
            return false;
        } else {

        }
    }
    ;
    function zTreeOnClick(event, treeId, treeNode) {
        //如果选中的是供应商则执行下面
        if ((treeNode.id).startsWith('gys_')) {
            $.CurrentDialog.find("#verDm").attr("value","");
            $.CurrentDialog.find("#verMc").attr("value","");
            $.CurrentDialog.find("#ps").attr("value","");
              $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
        } else if ((treeNode.id).startsWith('model_')) { //如果是软件版本则执行虾下面
            $.CurrentDialog.find("#verDm").attr("value","");
            $.CurrentDialog.find("#verMc").attr("value","");
            $.CurrentDialog.find("#ps").attr("value","");
            $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
        } else if ((treeNode.id).startsWith('ver_')) { //如果是软件版本则执行虾下面
            $.ajax({
                url: 'SoftVer.getOneSoftVer.action',
                data: {verDm: (treeNode.id).substr('ver_'.length, (treeNode.id).length)},
                cache: true,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
//                    var verNode = treeNode.getParentNode();
//                    var gysNode = verNode.getParentNode();
//                    var verDm = (verNode.id).substr((verNode.id).indexOf("_")+1, (verNode.id).length);
//                    var verMc =  (verNode.name).substr(verDm.length+1, (verNode.name).length);
//                    var gysDm = (gysNode.id).substr((gysNode.id).indexOf("_")+1, (gysNode.id).length);
//                    var gysMc =  (gysNode.name).substr(verDm.length+1, (gysNode.name).length);
                    $.CurrentDialog.find("#verDm").attr("value",json.verdm);
                    $.CurrentDialog.find("#verMc").attr("value",json.vermc);
                    $.CurrentDialog.find("#ps").attr("value",json.ps);
                    if(treeNode.isParent){
                        $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
                    }else {
                        $.CurrentDialog.find("#btn_lookbak").attr("data-args", "{gysDm:'"+json.gysdm+"',gysMc:'"+json.gysmc+"',gysDmMc:'"+json.gysdm+"["+json.gysmc+"]"+"',verMc:'"+json.vermc+"',verDm:'"+json.verdm+"',verDmMc:'"+json.verdm+"["+json.vermc + "]'}");
                        $.CurrentDialog.find("#btn_lookbak").attr("data-toggle", "lookupback");
                        //alert(treeNode.isParent+":"+$("#btn_lookbak").attr("data-args"));
                    }
                }
            });
        }

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
    function searchNode(id) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByParam("id", id);
        if (node) {
            alert(node.name);
        }
    }
    function returnTrue() {
        return true;
    }
    $(document).ready(
                    $.fn.zTree.init( $.CurrentDialog.find("#ztree_softVer"), setting)
    );
</SCRIPT>
<div class="pageContent" height="100%">
    <form data-validator-option="{timely:false}">
    <div>
        <table style="height: 100%;;width: 100%;border: solid lightgrey 1px">
            <tbody>
            <tr>
                <td colspan="2" style="border-bottom:solid lightgrey 1px" align="center" height="30px"><h3>软件信息管理</h3>
                </td>
            </tr>
            <tr>
                <td style="border-right: solid lightgrey 1px;" width="50%" valign="top" align="left" height="100%">
                    <div>
                        <ul id="ztree_softVer" class="ztree"></ul>
                    </div>
                </td>
                <td style="" valign="top" align="center" height="100%">
                    <div>
                        <!--软件模块-->
                            <table id = 'Table_softModel'>
                                <tr>
                                    <td align="center">
                                        <table class="table_form" align="center" valign="top">
                                            <tr>
                                                <td width="20" class="td_label">
                                                    <p>
                                                        <label>版本代码：</label>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p>
                                                        <input id="verDm" readonly="readonly" name="verDm" class="remote" type="text"
                                                               size="20" value=""/>
                                                    </p>
                                                </td>
                                            </tr>
                                                <tr>
                                                <td class="td_label">
                                                    <p>
                                                        <label>模块名称：</label>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p>
                                                        <input id="verMc" name="verMc"  readonly="readonly" class="required" type="text"
                                                               size="20" value=""
                                                               alt=""/>
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="td_label">
                                                    <p>
                                                        <label>备注：</label>
                                                    </p>
                                                </td>
                                                <td colspan="3">
                                                    <p>
                                                        <textarea name="ps" id="verPs"   readonly="readonly" class="ps" rows="2"
                                                                  cols="20"></textarea>
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="text-align: center;margin-left: 5px" colspan="2">
                                                    <div class="pageFormContent" style="margin:10px 0">
                                                        <%--data-args内容格式：{pid:'1', dqdmmc:'201111'}--%>
                                                        <a  id="btn_lookbak"  href="javascript:;" data-toggle="lookupback"  class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
        </form>
</div>
