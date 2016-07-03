<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/23
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/wxt/rpt/rpt_whrq.js"></script>
<script src="js/wxt/rpt/rpt_whrq_searchBar.js"></script>
<div class="bjui-pageHeader" style="text-align: center">
    <h4>维护日期统计表</h4>
    <hr>
    <div>
        <form id="form_dwysk">
            <div>
                <%--隐藏的地区信息ztree--%>
                <div class="zregionBackground left" style="display: none">
                    <ul id="AllRegionForZtreeRptWhrq" class="ztree" style="display: none"></ul>
                </div>
                <%--隐藏的客户经理信息ztree--%>
                <div class="zregionBackground left" style="display: none">
                    <ul id="AllKhjlForZtreeWhrq" class="ztree" style="display: none"></ul>
                </div>

            </div>
            <div class="bjui-searchBar">
                <div class="pull-left">
                    <label>所属地区:</label>
                    <input id="khxx_searche_dqxx" name="dqdmmc" data-toggle="selectztree"
                           data-tree="#AllRegionForZtreeRptWhrq" placeholder="请选择所属地区"/>
                    <label>客户经理:</label>
                    <input id="khxx_searche_khjl" name="khjl" data-toggle="selectztree"
                           data-tree="#AllKhjlForZtreeWhrq" placeholder="请选择客户经理"/>
                    <label>客户名称:</label>
                    <input type="text" name="keyword" id="khxx_searche_keyword" placeholder="请输入客户名称关键字">
                </div>
                <div class="pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn-default" id="searchBtn" data-icon="search">查询</button>
                        <a class="btn btn-orange" href="javascript:;" id = "reloadsearch" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageContent">
    <table id="datatable_rpt_whrq" data-toggle="datagrid" class="table table-bordered tablefixed" data-width="100%"
           data-height="100%"></table>
</div>
<div class="bjui-pageFooter">
    <div style="text-align: left">
        <button type="button" class="btn btn-green" id="btn_whrq_refresh"><span>刷新</span></button>
        <button type="button" class="btn btn-red" onclick="get_current_whrq()"><span>预警</span></button>
    </div>
</div>