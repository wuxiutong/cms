<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/29
  Time: 下午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function alterRolejaxDone(json) {
        if (json.statusCode == 200) {
            $(this).bjuiajax('ajaxDone', json);
            $.CurrentDialog.dialog('closeCurrent');
            var userObj = $.fn.zTree.getZTreeObj("ztree_role");
            var selectedNode = userObj.getSelectedNodes();
            selectedNode[0].name = $.CurrentDialog.find("#mc").val();
            userObj.updateNode(selectedNode[0]);

        } else {
            $(this).alertmsg("error", e.message);
        }

        $.CurrentDialog.find("#alterRoleForm").removeAttr("action");
        $('#alterRoleForm').validator('setField', {
            userName: null,       //将不再验证
        });
    };

    $.ajax({
        url: 'RoleBaseServlet.getOneRole',
        data: {dm: $.fn.zTree.getZTreeObj("ztree_role").getSelectedNodes()[0].id},
        cache: true,
        async: true,
        type: "POST",
        dataType: 'json',
        success: function (json) {
            if (json.statusCode == 200) {
                $.CurrentDialog.find("#id").val(json.id);
                $.CurrentDialog.find("#mc").val(json.mc);
                $.CurrentDialog.find("#ps").val(json.ps);
                PreAlterRoleID = "";
            }
            else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
    function alterRolesubmit() {
        $('#alterRoleForm').validator('setField', {
            userName: 'required',
        });
        $.CurrentDialog.find("#alterRoleForm").attr("action","RoleBaseServlet.alter");
        $.CurrentDialog.find("#alterRoleForm").submit();
    }
</script>
<div class="bjui-pageHeader" style="text-align: center">
    <h5>修改角色</h5>
</div>
<div class="bjui-pageContent">
    <form  id="alterRoleForm" class="form-horizontal" method="post" class="pageForm required-validate"
          data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate" data-callback="alterRolejaxDone">
        <div class="form-group">
            <label for="id" class="col-sm-4 control-label">角色ID</label>

            <div class="col-sm-8">
                <input type="text" readonly="readonly" class="form-control" name="id" id="id">
            </div>
        </div>
        <div class="form-group">
            <label for="mc" class="col-sm-4 control-label">角色名称</label>

            <div class="col-sm-8">
                <input type="text" data-rule="required" class="form-control" id="mc" name="mc">
            </div>
        </div>
        <div class="form-group">
            <label for="mc" class="col-sm-4 control-label">角色说明</label>

            <div class="col-sm-8">
                <textarea class="form-control" id="ps" name="ps"></textarea>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageFooter" style="text-align: right">
    <button type="button" id="addNN" class="btn btn-blue" onclick="javascript:alterRolesubmit()">保存</button>
    <button id="close" class="btn btn-close">关闭</button>
</div>