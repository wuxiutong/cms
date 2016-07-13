<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/29
  Time: 下午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    $.ajax({
        url: 'GetOneUser',
        data: {userID:  $.fn.zTree.getZTreeObj("ztree_users").getSelectedNodes()[0].id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (json.statusCode == 200) {
                PreAlterRoleID = "";
                $.CurrentDialog.find("#userID").val(json.userID);
                $.CurrentDialog.find("#userName").val(json.userName);
                $.CurrentDialog.find("#ps").val(json.ps);
            }
            else {
                $(this).alertmsg("error", json.message);
            }
        }
    });

  function alterUserAjaxDone(json) {
      if (json.statusCode == 200) {
//          $(this).alertmsg("ok", json.message);
          $.CurrentDialog.find("#save").attr("disabled","disabled");
          $.CurrentDialog.dialog('closeCurrent');
          var userObj = $.fn.zTree.getZTreeObj("ztree_users");
          var selectedNode = userObj.getSelectedNodes();
          selectedNode[0].name= $.CurrentDialog.find("#userName").val();
          userObj.updateNode(selectedNode[0]);
      } else {
          $(this).alertmsg("error", json.message);
      }
      $.CurrentDialog.find("#userAlterForm").removeAttr("action");
      $('#userAlterForm').validator('setField', {
          userName: null,       //将不再验证
      });
  }

    function alterUsersubmit(){
        $('#userAlterForm').validator('setField', {
        userName: 'required',       //将不再验证
    });
        $.CurrentDialog.find("#userAlterForm").attr("action","updateUserRoles.alter");
        $.CurrentDialog.find("#userAlterForm").submit();
    }
</script>
<div class="bjui-pageHeader" style="text-align: center">
  <h5>修改用户信息</h5>
  </div>
<div class="bjui-pageContent">
  <form id ="userAlterForm" class="form-horizontal" method="post" class="pageForm required-validate"
        data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate"  data-callback="alterUserAjaxDone">
      <div class="form-group">
        <label for="userID" class="col-sm-4 control-label">用户ID</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" readonly="readonly" name = "userID" id="userID"  >
        </div>
      </div>
      <div class="form-group">
        <label for="userName" class="col-sm-4 control-label">用户名称</label>
        <div class="col-sm-8">
          <input type="text" data-rule="required" class="form-control" id="userName" name="userName"  >
        </div>
      </div>
      <div class="form-group">
        <label for="ps" class="col-sm-4 control-label">角色说明</label>
        <div class="col-sm-8">
          <textarea  class="form-control" id="ps" name="ps"  ></textarea>
        </div>
      </div>
    </form>
  </div>
<div class="bjui-pageFooter" style="text-align: right">
  <button  type="button" id="save" class="btn btn-blue" onClick="javascript:alterUsersubmit()">保存</button>
  <button    id="close" class="btn btn-close">关闭</button>
</div>