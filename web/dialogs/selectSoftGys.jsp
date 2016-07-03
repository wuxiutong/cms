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
            url: "GysBaseServlet.getAllForTree",
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
//        if ($("#gyssoft").attr("disabled")) {
//            return false;
//        } else {
//
//        }
    };
    function zTreeOnClick(event, treeId, treeNode) {
        //如果选中的是供应商则执行下面
         if (1==1) { //如果是软件版本则执行虾下面
            $.ajax({
                url: 'GysBaseServlet.getOneGys',
                data: {gysDm: (treeNode.id)},
                cache: true,
                async: true,
                type: "POST",
                dataType: 'json',
                success: function (json) {
                    $.CurrentDialog.find("#gysDm").attr("value",json.gysdm);
                    $.CurrentDialog.find("#gysMc").attr("value",json.gysmc);
                    $.CurrentDialog.find("#gysPs").attr("value",json.ps);
                    if(treeNode.isParent){
                        $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
                    }else {
                        $.CurrentDialog.find("#btn_lookbak").attr("data-args", "{gysDm:'"+json.gysdm+"',gysMc:'"+json.gysmc+"',gysDmMc:'"+json.gysdm+"["+json.gysmc+"]"+"'}");
                        $.CurrentDialog.find("#btn_lookbak").attr("data-toggle", "lookupback");
                        //alert(treeNode.isParent+":"+$("#btn_lookbak").attr("data-args"));
                    }
                }
            })
        }
    };
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
        $.fn.zTree.init($.CurrentDialog.find("#getGys"), setting)
    );
</SCRIPT>
<div class="pageContent" height="100%">
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
                        <ul id="getGys" class="ztree"></ul>
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
                                                        <label>供应商代码：</label>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p>
                                                        <input id="gysDm" readonly="readonly" name="gysDm" class="remote" type="text"
                                                               size="20" value=""/>
                                                    </p>
                                                </td>
                                            </tr>
                                                <tr>
                                                <td class="td_label">
                                                    <p>
                                                        <label>供应商名称：</label>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p>
                                                        <input id="gysMc" name="gysMc"  readonly="readonly" class="required" type="text"
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
                                                        <textarea name="ps" id="gysPs"   readonly="readonly" class="ps" rows="2"
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
</div>
