<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/wxt/customer/management.js" type="text/javascript"></script>
<script src="/js/wxt/customer/management_search.js" type="text/javascript"></script>
<div class="bjui-pageHeader">
    <form id="pagerForm"  data-toggle="ajaxsearch" >
        <div>
            <%--隐藏的地区信息ztree--%>
            <div class="zregionBackground left" style="display: none">
                <ul id="AllRegionForDqxxNoLevelForKhxx" class="ztree" style="display: none"></ul>
            </div>
            <%--隐藏的客户类型信息ztree--%>
            <div class="zregionBackground left" style="display: none">
                <ul id="AllKhlxForKhxx" class="ztree" style="display: none"></ul>
            </div>
            <%--隐藏的客户经理信息ztree--%>
            <div class="zregionBackground left" style="display: none">
                <ul id="AllKhjlForKhxx" class="ztree" style="display: none"></ul>
            </div>
            <%--隐藏的使用软件信息ztree--%>
            <div class="zregionBackground left" style="display: none">
                <ul id="AllSoftForKhxx" class="ztree" style="display: none"></ul>
            </div>

            <%--隐藏的销售公司信息ztree--%>
            <div class="zregionBackground left" style="display: none">
                <ul id="AllXsgsForKhxx" class="ztree" style="display: none"></ul>
            </div>

        </div>

        <div class="bjui-searchBar">
            <label>所属地区:</label>
            <input id="khxx_searche_dqxx" name="dqdmmc" data-toggle="selectztree"
                   data-tree="#AllRegionForDqxxNoLevelForKhxx" placeholder="请选择所属地区"/>
            <label>客户名称:</label>
            <input type="text" name="keyword" id="khxx_searche_keyword" placeholder="请输入客户名称关键字">
            <%--<input type="checkbox" id="khxx_searche_myKhxx" value="true" data-toggle="icheck" data-label="我的客户">--%>
            <button type="button" id="showBtn" class="showMoreSearch" data-toggle="moresearch" data-name="custom"><i
                    class="fa fa-angle-double-down"></i></button>
            <button type="button" class="btn-default" id="searchBtn" data-icon="search">查询</button>
            &nbsp;

            <a class="btn btn-orange" href="javascript:;" id = "reloadsearch" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <button  data-icon="edit" type="button" class ="btn btn-green" onclick="btn_customer_edit()">编辑</button>

            <div class="pull-right">
                <%--<button type="button" class="btn-blue" data-url="ajaxDone2.html?id={#bjui-selected}" data-toggle="doajax" data-confirm-msg="确定要删除选中项吗？" data-icon="remove" title="可以在控制台(network)查看被删除ID">删除选中行</button>&nbsp;--%>
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">
                        批量操作<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                        <li><a id="btn_exportAll"  data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span
                                style="color: green;">全部</span></a></li>
                        <li><a id="btn_exportChecked"  data-toggle="doexportchecked" data-confirm-msg="确定要导出选中项吗？"
                               data-idname="expids" data-group="ids">导出<span style="color: red;">选中</span></a></li>
                        <li class="divider"></li>
                        <li><a id="btn_delChecked"  data-toggle="doajaxchecked" data-confirm-msg="确定要删除选中项吗？"
                               data-idname="delids" data-group="ids">删除选中</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="extendsBar" style="display: none;padding-top: 5px">
            <label>客户类型:</label>
            <input id="khxx_searche_khlx" name="khlx" data-toggle="selectztree"
                   data-tree="#AllKhlxForKhxx" placeholder="请选择客户类型"/>
            <label>销售公司:</label>
            <input id="khxx_searche_xsgs" name="xsgs" data-toggle="selectztree"
                   data-tree="#AllXsgsForKhxx" placeholder="请选择销售公司"/>
            <label>使用软件:</label>
            <input id="khxx_searche_soft" name="xsgs" data-toggle="selectztree"
                   data-tree="#AllSoftForKhxx" placeholder="请选择使用软件"/>
            <label>客户经理:</label>
            <input id="khxx_searche_khjl" name="khjl" data-toggle="selectztree"
                   data-tree="#AllKhjlForKhxx" placeholder="请选择客户经理"/>
        </div>
    </form>
</div>
<div class="bjui-pageContent">

    <div style="margin:5px;">
        <hr style="margin: 5px 0">
        <table id="datagrid_ManaCustomer" data-width="100%" data-height="98%" class="table table-bordered">
        </table>
    </div>
</div>