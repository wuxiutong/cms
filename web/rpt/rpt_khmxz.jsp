<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/8/23
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/wxt/rpt/rpt_khmxz.js"></script>
<div class="bjui-pageHeader" style="text-align: center">
    <h4>客户开票明细记录</h4>
    <hr>

    <div>
            <div class="row">
                <div class="col-md-11" align="left">
                    <input class="form-control" id="rpt_searche_khdm" type="text" placeholder="请选择需要查询的客户"
                           data-rule-select="[/^.+\[.*\]$/, '请选择单位']" name="khxx.khdmmc"
                           value="" data-toggle="lookup" name="请选择"
                           data-url="dialogs/selectKhxx.jsp" data-rule="required;date"
                           data-group="khxx" data-width="800" data-height="500"/>
                    <input type="text" name="rpt_searche_qsrq" id="rpt_searche_qsrq" placeholder="请选择开票起始日期" data-toggle="datepicker">
                    <span>至</span>
                    <input type="text" name="rpt_searche_jzrq" id="rpt_searche_jzrq" placeholder="请选择开票截至日期" data-toggle="datepicker">
                    <select class="form-control" name="rpt_searche_djzt" id="rpt_searche_djzt" >
                        <option value="">--所有状态--</option>
                        <option value="0">未审核</option>
                        <option value="1">已审核</option>
                        <option value="2">已收款</option>
                        <option value="-1">已作废</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <button type="button" id="searchBtn" class="btn btn-green">检索</button>
                </div>
            </div>
    </div>
</div>
<div class="bjui-pageContent">
    <table id="datatable_rpt_khmxz" class="table table-bordered tablefixed" data-toggle="datagrid" data-width="100%" data-height="100%"></table>
</div>