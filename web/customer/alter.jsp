<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/js/wxt/customer/alter.js" type="text/javascript"></script>
<script src="/js/wxt/customer/alterCustomer_selectZtree.js" type="text/javascript"></script>
<style>
    .form-group {
        margin: 10px;
        margin-left: 20px;
    }

    label {
        margin-left: 15px;
    }
</style>
<div class="bjui-pageHeader" align="center">
    <h4>修改客户信息</h4>
</div>
<div class="bjui-pageContent" height="100%">
    <div>
        <%--隐藏的地区信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllRegionForAlterKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户类型信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhlxForAlterKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户经理信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhjlForAlterKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的使用软件信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllSoftForAlterKhxx" class="ztree" style="display: none"></ul>
        </div>

        <%--隐藏的销售公司信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllXsgsForAlterKhxx" class="ztree" style="display: none"></ul>
        </div>

    </div>
    <form class="form-inline" id="form_alterCustomer" data-validator-option="{timely:false}"
          action="KhxxBaseServlet.alter"
          data-toggle="validate" data-callback="navTabCustomerAjaxDone" style="height: auto;width: 100%">
        <div class="form-group">
            <label for="alter_customer_gsdm">客户代码：</label>
            <input id="alter_customer_gsdm" readonly="readonly" class="form-control" name="khdm" class="remote"
                   type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="alter_customer_gsmc">客户名称：</label>
            <input id="alter_customer_gsmc" class="form-control" data-rule="required" name="khmc" class="remote"
                   type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="alter_customer_gzdz">单位地址：</label>
            <input id="alter_customer_gzdz" name="gzdz" class="form-control" type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="alter_customer_gzdh">办公电话：</label>
            <input id="alter_customer_gzdh" name="bgdh" class="form-control" type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="alter_customer_khlx">单位性质：</label>
            <input id="alter_customer_khlx" class="form-control" size="20" data-rule="required" name="khlx.lxdmmc"
                   data-toggle="selectztree"
                   data-tree="#AllKhlxForAlterKhxx" placeholder="请选择客户类型"/>
        </div>
        <div class="form-group">
            <label for="alter_customer_ssdq">所属地区：</label>
            <input id="alter_customer_ssdq" class="form-control" size="20" data-rule="required" name="ssdq.dqdmmc"
                   data-toggle="selectztree"
                   data-tree="#AllRegionForAlterKhxx" placeholder="请点击选择所属地区"/>
        </div>
        <div class="form-group">
            <label for="alter_customer_khjl">客户经理：</label>
            <input id="alter_customer_khjl" data-rule="required" name="khjl.zydmxm" class="form-control" size="20"
                   data-toggle="selectztree"
                   data-tree="#AllKhjlForAlterKhxx" placeholder="请选择客户经理"/>
        </div>
        <div class="form-group">
            <label for="alter_customer_xsgs">销售公司：</label>
            <input id="alter_customer_xsgs" name="xsgs.gsdmmc" data-toggle="selectztree" class="form-control" size="20"
                   data-rule="required"
                   data-tree="#AllXsgsForAlterKhxx" placeholder="请选择销售公司"/>
        </div>
        <div class="form-group">
        <label for="alter_customer_whrq">维护日期：</label>
        <input type="text" class="date" name="whrq" id="alter_customer_whrq" data-toggle="datepicker"
               data-rule="required;date">
    </div>
        <div class="form-group">
            <label for="alter_customer_ps">备注：</label>
            <input id="alter_customer_ps" name="ps" class="form-control" type="text"
                   size="40"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="alter_customer_ps">修改原因：</label>
            <input id="alter_customer_xgyy" name="xgyy" class="form-control" data-rule="required" type="text"
                   size="40"
                   value=""/>
        </div>
        <div hidden="hidden"><input id='lxr' name="lxr" type="text"/></div>
        <div hidden="hidden"><input id='soft' name="soft" type="text"/></div>
    </form>
    <!-- Tabs -->
    <ul class="nav nav-tabs" role="tablist" style="width: 100%;height: auto">
        <li class="active"><a href="#tab_alter_lxr" role="tab" data-toggle="tab">单位联系人</a></li>
        <li><a href="#tab_alter_soft" role="tab" data-toggle="tab">单位使用软件</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content" style=" width: 100%;height: auto">
        <div class="tab-pane fade active in" id="tab_alter_lxr" style=" width: 100%;height: auto">
            <table id="alter_lxr-datagrid" width="100%" height="auto"
                   class="table table-bordered">
            </table>
        </div>
        <div class="tab-pane fade" id="tab_alter_soft" style=" width: 100%;height: auto">
            <table id="alter_soft-datagrid" width="100%" height="auto"
                   class="table table-bordered">
            </table>
        </div>
    </div>
</div>
<div class="bjui-pageFooter">
    <button style="margin: 5px" id="btn_alterKhxx_save" class="btn btn-green">保存</button>
</div>