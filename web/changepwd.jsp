
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
	<form  id="form_changePWD" data-toggle="validate" method="post"  data-callback="ChangePWDAjaxDone">
		<input type="hidden" name="users.id" value="test">
		<input type="hidden" id="j_pwschange_username" value="admin">
		<input type="hidden" id="j_pwschange_oldpass" name="users.password">
		<input type="hidden" id="j_pwschange_newpass" name="newpassword">
        <hr>
        <div class="form-group">
            <label for="j_pwschange_oldpassword" class="control-label x85">旧密码：</label>
            <input type="password" data-rule="required" name="oldPWD" id="j_pwschange_oldpassword" value="" placeholder="旧密码" size="20">
        </div>
        <div class="form-group" style="margin: 20px 0 20px; ">
            <label for="j_pwschange_newpassword" class="control-label x85">新密码：</label>
            <input type="password" data-rule="新密码:required" name="newPWD" id="j_pwschange_newpassword" value="" placeholder="新密码" size="20">
        </div>
        <div class="form-group">
            <label for="j_pwschange_secpassword" class="control-label x85">确认密码：</label>
            <input type="password" data-rule="required;match(newPWD)"  id="j_pwschange_secpassword" value="" placeholder="确认新密码" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" id="Btn_changePWD" class="btn-blue">保存</button></li>
    </ul>
</div>
<script>
    $.CurrentDialog.find("#Btn_changePWD").bind("click",function(){
        $.CurrentDialog.find("#form_changePWD").attr("action","ChangePWD");
        $.CurrentDialog.find("#form_changePWD").submit();
    });
    function ChangePWDAjaxDone(json){
        if(json.statusCode == 200){
            $(this).alertmsg("ok",json.message)
            $.CurrentDialog.dialog('closeCurrent');
        }else {
            $(this).alertmsg("error",json.message)
        }
    }
</script>
