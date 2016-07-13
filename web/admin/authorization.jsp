<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/29
  Time: 下午5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/wxt/author/authorization.js"></script>
<script src="/js/wxt/author/user.js"></script>
<div class="bjui-pageHeader">
    <%--<h3>权限管理</h3>--%>
    <ul class="nav nav-tabs" role="tablist" id="authorization">
        <li role="presentation" class="active"><a href="#role"  class="role" aria-controls="role" role="tab"
                                                       data-toggle="tab">角色管理</a></li>
        <li role="presentation" class="users"><a href="#users"  class="users" aria-controls="users" role="tab"
                                                 data-toggle="tab">操作员管理</a></li>
    </ul>
</div>
<div class=" bjui-pageContent">
    <div class="tab-content" data-width="100%" data-height="98%">
        <div role="tabpanel" class="tab-pane active" id="role" data-width="100%" data-height="100%">
            <table width="100%" height="80%">
                <tr>
                    <td width="30%" style="text-align:center;border: solid lightgray 1px"><h6>角色</h6></td>
                    <td width="40%" style="text-align:center;border: solid lightgray 1px"><h6>角色权限</h6></td>
                </tr>
                <tr>
                    <td id="authorLeft" width="40%" height="100%"
                        style="text-align: left;vertical-align: top;border: solid lightgray 1px;border-bottom: none">
                        <ul id="ztree_role" class="ztree"></ul>
                    </td>
                    <td rowspan="2" id="authorRight" width="60%" height="100%"
                        style="text-align:left;vertical-align:top;border: solid lightgray 1px">
                        <div style="float:left; width:100%; height:70%; ">
                            <ul id="ztree_authorization" class="ztree"></ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="border: solid lightgray 1px;border-top: none;pxtext-align:left;vertical-align:bottom"
                        height="31">

                    </td>
                </tr>
            </table>


        </div>
        <div role="tabpanel" class="tab-pane" id="users" data-width="100%">
            <table width="100%" height="80%">
                <tr>
                    <td width="30%" style="text-align:center;border: solid lightgray 1px"><h6>用户</h6></td>
                    <td width="30%" style="text-align:center;border: solid lightgray 1px"><h6>已授权角色</h6></td>
                    <td width="10%" style="text-align:center;border: solid lightgray 1px"></td>
                    <td width="30%" style="text-align:center;border: solid lightgray 1px"><h6>未授权角色</h6></td>
                </tr>
                <tr>
                    <td id="UsersLeft" height="100%"
                        style="text-align: left;vertical-align: top;border: solid lightgray 1px;border-bottom: none">
                        <ul id="ztree_users" class="ztree"></ul>
                    </td>
                    <td rowspan="2" id="UserRight" height="100%"
                        style="text-align:left;vertical-align:top;border: solid lightgray 1px">
                        <div style="float:left; width:100%; height:100%; overflow:auto;">
                            <ul id="ztree_UserAuthor" class="ztree"></ul>
                        </div>
                    </td>
                    <td rowspan="2" style="text-align: center;vertical-align: middle;border: solid lightgray 1px">
                        <button id="ungrant" class="btn btn-default"><span>&gt;&gt;</span></button>
                        <br/>
                        <hr>
                        <button id="grant" class="btn btn-default"><span>&lt;&lt;</span></button>
                    </td>
                    <td rowspan="2" style="border: solid lightgray 1px">
                        <div style="float:left; width:100%; height:100%; overflow:auto;">
                            <ul id="ztree_AuthorItems" class="ztree"></ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="border: solid lightgray 1px;border-top: none;pxtext-align:left;vertical-align:bottom"
                        height="31">

                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="bjui-pageFooter">
    <div class="footer-role">
        <button id="refreshRole" class="btn btn-blue">刷新</button>
        <button id="addRole" class="btn btn-green">增加角色</button>
        <button id="alterRoleAuthor" class="btn btn-red" style="display: none">修改权限</button>
        <button id="alterRole" class="btn btn-red" style="display: none">修改角色</button>
        <button id="saveRole" class="btn btn-blue" style="display: none">保存</button>
        <button id="delRole" class="btn btn-orange" style="display: none">删除</button>
        <button id="cancel" class="btn btn-orange" style="display: none">取消</button>
    </div>
    <div class="footer-user" style="display: none">
        <button id="refreshUser" class="btn btn-blue">刷新</button>
        <button id="addUser" class="btn btn-green">增加用户</button>
        <button id="alterUser" class="btn btn-red" style="display: none">修改用户</button>
        <button id="delUser" class="btn btn-orange" style="display: none">删除</button>
    </div>
</div>