<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/23
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/wxt/rpt/rpt_ykpKhxx.js"></script>
<%--<script src="js/wxt/rpt/rpt_ykpKhxx_searchBar.js"></script--%>
<div class="bjui-pageHeader" style="text-align: center">
    <h4>客户开票记录明细</h4>
    <hr>
    <div style="display: none">
        <%--隐藏的地区信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllRegionForRptYKPKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户类型信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhlxForRptYKPKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户经理信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhjlForRptYKPKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的使用软件信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllSoftForRptYKPKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的销售公司信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllXsgsForRptYKPKhxx" class="ztree" style="display: none"></ul>
        </div>
    </div>
    <div>
        <%--<div class="col-md-10" align="left">--%>
        <%--<input id="rpt_searche_dqxx"  type="text" class="form-control"--%>
        <%--name="ssdq.dqdmmc"  placeholder="请选择地区信息"--%>
        <%--value="" size="20" data-toggle="lookup"--%>
        <%--data-url="dialogs/selectDqxxNoLevel.jsp"--%>
        <%--data-group="ssdq" data-width="600" data-height="490"/>--%>
        <%--<input id="rpt_searche_khlx"  type="text"  class="form-control"--%>
        <%--name="khlx.lxdmmc"  placeholder="请选择单位类型"--%>
        <%--value="" size="20" data-toggle="lookup"--%>
        <%--data-url="dialogs/selectKhlxNoLevel.jsp"--%>
        <%--data-group="khlx" data-width="600" data-height="490"/>--%>
        <%--<input type="text" name="rpt_searche_qsrq" id="rpt_searche_qsrq" placeholder="请选择开票起始日期" data-toggle="datepicker">--%>
        <%--<span>至</span>--%>
        <%--<input type="text" name="rpt_searche_jzrq" id="rpt_searche_jzrq" placeholder="请选择开票截至日期" data-toggle="datepicker">--%>
        <%--<select class="form-control" name="rpt_searche_djlx" id="rpt_searche_djlx" >--%>
        <%--<option value="">--所有类型--</option>--%>
        <%--<option value="whd">维护单</option>--%>
        <%--<option value="xsd">销售单</option>--%>
        <%--</select>--%>
        <%--</div>--%>
        <%--<div class="col-md-1">--%>
        <%--<button type="button" id="searchBtn" class="btn btn-green">检索</button>--%>
        <%--</div>--%>

        <div class="bjui-searchBar" style="text-align: left">
            <div class="pull-left">
                <label>所属地区:</label>
                <input id="rpt_searche_dqxx" name="ssdq.dqdmmc" data-toggle="selectztree"
                       data-tree="#AllRegionForRptYKPKhxx" placeholder="请选择所属地区"/>
                <label>客户名称:</label>
                <input type="text" name="keyword" id="rpt_searche_keyword" placeholder="请输入客户名称关键字">
                <select class="form-control" name="rpt_searche_djlx" id="rpt_searche_djlx">
                    <option value="">--所有类型--</option>
                    <option value="whd">维护单</option>
                    <option value="xsd">销售单</option>
                </select>
                <div id="extends_items" style="display: none">
                    <label>客户经理:</label>
                    <input id="rpt_searche_khjl" name="khjl" data-toggle="selectztree"
                           data-tree="#AllKhjlForRptYKPKhxx" placeholder="请选择客户经理"/>
                    <label>日期范围:</label>
                    <input type="text" name="rpt_searche_qsrq" id="rpt_searche_qsrq" size="12" placeholder="开票起始日期"
                           data-toggle="datepicker">
                    <span>至</span>
                    <input type="text" name="rpt_searche_jzrq" id="rpt_searche_jzrq" size="12" placeholder="开票截至日期"
                           data-toggle="datepicker">
                </div>
                <button type="button" class="btn btn-blue" id="showBtn"><i class="fa fa-angle-double-right"></i></button>
                <div class="pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn-default" id="searchBtn" data-icon="search">检索</button>
                    </div>
                    <div class="btn-group">
                        <a class="btn btn-orange" href="javascript:;" id="reloadsearch" data-toggle="reloadsearch"
                           data-clear-query="true" data-icon="undo">清空查询</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bjui-pageContent"  >
    <table id="datatable_rpt_ykpKhxx" class="table table-bordered tablefixed" data-toggle="datagrid"
           data-width="100%"
           data-height="100%"></table>
</div>