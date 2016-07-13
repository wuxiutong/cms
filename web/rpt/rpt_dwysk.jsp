<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/23
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/wxt/rpt/rpt_dwysk.js"></script>
<script src="js/wxt/rpt/rpt_dwysk_searchBar.js"></script>
<div class="bjui-pageHeader" style="text-align: center">
    <h4>单位预计收费表</h4>
    <hr>
    <div style="display: none">
        <%--隐藏的地区信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllRegionForRptDWYSK" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户类型信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhlxForRptDWYSK" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户经理信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhjlForRptDWYSK" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的使用软件信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllSoftForRptDWYSK" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的销售公司信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllXsgsForRptDWYSK" class="ztree" style="display: none"></ul>
        </div>
    </div>
    <div>
        <form id="form_dwysk">
            <div class="row">
                <div class="col-md-11" align="left">
                    <label>所属地区:</label>
                    <input id="rpt_searche_dqxx" name="ssdq.dqdmmc" size="20" class="form-control"
                           data-toggle="selectztree"
                           data-tree="#AllRegionForRptDWYSK" placeholder="请选择所属地区"/>
                    <label>客户经理:</label>
                    <input id="rpt_searche_khjl"   size="20" class="form-control"
                           name="zyxx.zydmxm" data-toggle="selectztree"
                           data-tree="#AllKhjlForRptDWYSK" placeholder="请选择客户经理"/>
                    <label>客户名称:</label>
                    <input type="text" name="keyword" id="rpt_searche_keyword" placeholder="请输入客户名称关键字">
                    <select class="form-control" name="rpt_searche_kjnd" id="rpt_searche_kjnd">
                        <option value="">--请选择年度--</option>
                        <option value="2015-2016">2015-2016</option>
                        <option value="2016-2017">2016-2017</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <button type="button" id="searchBtn" class="btn btn-green">检索</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageContent">
    <table id="datatable_rpt_dwysk" class="table table-bordered tablefixed" data-toggle="datagrid" data-width="100%"
           data-height="100%"></table>
</div>