<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
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
      url: "Dwlx.getAll.action",
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
      url: 'Dwlx.getOneDwlx.action',
      data: {lxdm: treeNode.id},
      cache: true,
      async: true,
      type: "POST",
      dataType: 'json',
      success: function (json) {
        //修改是否可修改属性
        $.CurrentDialog.find('#lxdm').attr("value", json.lxdm);
        $.CurrentDialog.find("#lxmc").attr("value", json.lxmc);
        $.CurrentDialog.find("#ps").attr("value", json.ps);
        if(treeNode.isParent){
          $.CurrentDialog.find("#btn_lookbak").removeAttr("data-toggle");
        }else {
          $.CurrentDialog.find("#btn_lookbak").attr("data-args", "{pid:'1', lxdmmc:'" + json.lxdm + "[" + json.lxmc + "]" + "'}");
          $.CurrentDialog.find("#btn_lookbak").attr("data-toggle", "lookupback");
          //alert(treeNode.isParent+":"+$("#btn_lookbak").attr("data-args"));
        }

      }
    });
  } ;
  //检测是否选择末级
  function checkCurrent(){
    if('lookupback'!= $.CurrentDialog.find("#btn_lookbak").attr("data-toggle")){}
//      $(this).alertmsg('info', '你未选中地区信息或者选择了非末级地区!', {displayMode:'fade', displayPosition:'middlecenter',  title:'提示'})
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
          $.fn.zTree.init($("#AllDwlx"), setting)
  );

</script>
<div class="bjui-pageContent">
  <table width="100%" style="width: 100%;height: 100%;border: solid lightgray 1px">
    <tr>
      <td colspan="2" align="center" height="20px" style="border-bottom: solid lightgray 1px">
        <h4>单位类型信息</h4>
      </td>
    </tr>
    <tr><td width="40%" align="left" valign="top" style="border-right: solid lightgray 1px">
      <div class="zregionBackground left">
        <ul id="AllDwlx" class="ztree"></ul>
      </div>
    </td>
      <td width="60%" align="center" valign="top">
        <div>
          <table class="table_form" style="border: none">
            <tr>
              <td class="td_label">
                <p>
                  <label>类型代码：</label>
                </p>
              </td>
              <td>
                <p>
                  <input id="lxdm" name="lxdm" readonly="readonly"  type="text" size="20" value=""/>
                </p>
              </td>
            </tr>
            <tr>
              <td class="td_label">
                <p>
                  <label>类型名称：</label>
                </p>
              </td>
              <td>
                <p>
                  <input id="lxmc" name="lxmc"  readonly="readonly" type="text" size="20" value=""
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
              <td><p>
                <textarea readonly="readonly" name="ps" id="ps" class="ps" rows="3" cols="20"></textarea>
              </p>
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td style="text-align: left;margin-left: 5px">
                <div class="pageFormContent" style="margin:10px 0">
                  <%--data-args内容格式：{pid:'1', dqdmmc:'201111'}--%>
                  <a  id="btn_lookbak"  onclick="checkCurrent()"  href="javascript:;" data-toggle="lookupback"  class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </div>
              </td>
            </tr>
          </table>
          <%--</form>--%>

        </div>
      </td>
    </tr>
  </table>
</div>
