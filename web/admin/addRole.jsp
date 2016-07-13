<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/29
  Time: 下午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function addRoleAjaxDone(json) {
        if (json.statusCode == 200) {
            $(this).alertmsg("ok", json.message);
            var roleObj = $.fn.zTree.getZTreeObj("ztree_role");
            var newNode = {id: $.CurrentDialog.find("#id").val().trim(), name: $.CurrentDialog.find("#mc").val().trim()}
            roleObj.addNodes(null, newNode);

            $.CurrentDialog.find("#id").val("");
            $.CurrentDialog.find("#mc").val("");
            $.CurrentDialog.find("#ps").val("");


        } else {
            $(this).alertmsg("error", json.message);
        }

        $.CurrentDialog.find("#roleAddForm").removeAttr("action");
        $('#roleAddForm').validator('setField', {
            mc: null,       //将不再验证
        });
    }

    function fsubmit() {
        $('#roleAddForm').validator('setField', {
            mc: 'required',       //将不再验证
        });
        $.CurrentDialog.find("#roleAddForm").attr("action", "RoleBaseServlet.add");
        $.CurrentDialog.find("#roleAddForm").submit();
    }
</script>
<div class="bjui-pageHeader" style="text-align: center">
    <h5>增加角色</h5>
</div>
<div class="bjui-pageContent">
    <form id="roleAddForm" class="form-horizontal" method="post" class="pageForm required-validate"
          data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate" data-callback="addRoleAjaxDone">
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
    <button type="button" id="add" class="btn btn-blue" onClick="javascript:fsubmit()">添加</button>
    <button id="close" class="btn btn-close">关闭</button>
</div>