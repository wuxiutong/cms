<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/29
  Time: 下午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function addUserAjaxDone(json) {
        if (json.statusCode == 200) {
            $(this).alertmsg("ok", json.message);
            var userObj = $.fn.zTree.getZTreeObj("ztree_users");
            var newNode = {
                id: $.CurrentDialog.find("#userID").val().trim(),
                name: $.CurrentDialog.find("#userName").val().trim()
            }
            userObj.addNodes(null, newNode);
//最后才清空
            $.CurrentDialog.find("#userID").val("");
            $.CurrentDialog.find("#userName").val("");
            $.CurrentDialog.find("#password").val("");
            $.CurrentDialog.find("#ps").val("");
        } else {
            $(this).alertmsg("error", json.message);
        }

        $.CurrentDialog.find("#userAddForm").removeAttr("action");
        $('#userAddForm').validator('setField', {
            mc: null,       //将不再验证
        });
    }
    function addUsersubmit() {
        $('#userAddForm').validator('setField', {
            mc: 'required',       //将不再验证
        });
        $.CurrentDialog.find("#userAddForm").attr("action", "UserBaseServlet.add");
        $.CurrentDialog.find("#userAddForm").submit();
    }
</script>
<div class="bjui-pageHeader" style="text-align: center">
    <h5>增加用户</h5>
</div>
<div class="bjui-pageContent">
    <form id="userAddForm" class="form-horizontal" method="post" class="pageForm required-validate"
          data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate" data-callback="addUserAjaxDone">
        <div class="form-group">
            <label for="userID" class="col-sm-4 control-label">用户ID</label>

            <div class="col-sm-8">
                <input type="text" class="form-control" name="userID" id="userID">
            </div>
        </div>
        <div class="form-group">
            <label for="userName" class="col-sm-4 control-label">用户名称</label>

            <div class="col-sm-8">
                <input type="text" data-rule="" class="form-control" id="userName" name="userName">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">初始密码</label>

            <div class="col-sm-8">
                <input type="text" data-rule="" class="form-control" id="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <label for="ps" class="col-sm-4 control-label">角色说明</label>

            <div class="col-sm-8">
                <textarea class="form-control" id="ps" name="ps"></textarea>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageFooter" style="text-align: right">
    <button type="button" id="add" class="btn btn-blue" onClick="javascript:addUsersubmit()">添加</button>
    <button id="close" class="btn btn-close">关闭</button>
</div>