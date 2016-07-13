<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/js/wxt/customer/add.js" type="text/javascript"></script>
<script src="/js/wxt/customer/addCustomer_selectZtree.js" type="text/javascript"></script>
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
    <h4>新增客户信息</h4>
</div>
<div class="bjui-pageContent" height="100%">
    <div>
        <%--隐藏的地区信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllRegionForAddKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户类型信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhlxForAddKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的客户经理信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllKhjlForAddKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的使用软件信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllSoftForAddKhxx" class="ztree" style="display: none"></ul>
        </div>
        <%--隐藏的销售公司信息ztree--%>
        <div class="zregionBackground left" style="display: none">
            <ul id="AllXsgsForAddKhxx" class="ztree" style="display: none"></ul>
        </div>
    </div>
    <form class="form-inline" id="form_addCustomer" data-validator-option="{timely:false}" action="KhxxBaseServlet.add"
          data-toggle="validate" data-callback="navTabCustomerAjaxDone" style="height: auto;width: 100%">
        <div class="form-group">
            <label for="add_customer_gsdm">客户代码：</label>
            <input id="add_customer_gsdm" readonly="readonly" class="form-control" name="khdm" class="remote"
                   type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="add_customer_gsmc">客户名称：</label>
            <input id="add_customer_gsmc" class="form-control" data-rule="required" name="khmc" class="remote"
                   type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="add_customer_gzdz">单位地址：</label>
            <input id="add_customer_gzdz" name="gzdz" class="form-control" type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="add_customer_gzdh">办公电话：</label>
            <input id="add_customer_gzdh" name="bgdh" class="form-control" type="text"
                   size="20"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="add_customer_khlx">单位性质：</label>
            <input id="add_customer_khlx"   class="form-control" size="20" data-rule="required" name="khlx.lxdmmc" data-toggle="selectztree"
                   data-tree="#AllKhlxForAddKhxx" placeholder="请选择客户类型"/>
        </div>
        <div class="form-group">
            <label for="add_customer_ssdq">所属地区：</label>
            <input id="add_customer_ssdq" class="form-control" size="20" data-rule="required"  name="ssdq.dqdmmc" data-toggle="selectztree"
                   data-tree="#AllRegionForAddKhxx" placeholder="请点击选择所属地区"/>
        </div>
        <div class="form-group">
            <label for="add_customer_khjl">客户经理：</label>
            <input id="add_customer_khjl"  data-rule="required" name="khjl.zydmxm" class="form-control" size="20" data-toggle="selectztree"
                   data-tree="#AllKhjlForAddKhxx" placeholder="请选择客户经理"/>
        </div>
        <div class="form-group">
            <label for="add_customer_xsgs">销售公司：</label>
            <input id="add_customer_xsgs" name="xsgs.gsdmmc" data-toggle="selectztree" class="form-control" size="20" data-rule="required"
                   data-tree="#AllXsgsForAddKhxx" placeholder="请选择销售公司"/>
        </div>
        <div class="form-group">
            <label for="add_customer_whrq">维护日期：</label>
            <input type="text" class="date" name="whrq" id="add_customer_whrq" data-toggle="datepicker"
                   data-rule="required;date">
        </div>
        <div class="form-group">
            <label for="add_customer_ps">备注：</label>
            <input id="add_customer_ps" name="ps" class="form-control" type="text"
                   size="40"
                   value=""/>
        </div>
        <div hidden="hidden"><input id='lxr' name="lxr" type="text"/></div>
        <div hidden="hidden"><input id='soft' name="soft" type="text"/></div>
    </form>
    <!-- Tabs -->
    <ul class="nav nav-tabs" role="tablist" style="width: 100%;height: auto">
        <li class="active"><a href="#tab_add_lxr" role="tab" data-toggle="tab">单位联系人</a></li>
        <li><a href="#tab_add_soft" role="tab" data-toggle="tab">单位使用软件</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content" style=" width: 100%;height: auto">
        <div class="tab-pane fade active in" id="tab_add_lxr" style=" width: 100%;height: auto">
            <table id="add_lxr_datagrid" width="100%" height="auto"
                   class="table table-bordered">
            </table>
        </div>
        <div class="tab-pane fade" id="tab_add_soft" style=" width: 100%;height: auto">
            <table id="add_soft_datagrid" width="100%" height="auto"
                   class="table table-bordered">
            </table>
        </div>
    </div>
</div>
<div class="bjui-pageFooter">
    <button style="margin: 5px" type="button" disabled="disabled" id="btn_addKhxx_new" class="btn btn-green">新增</button>
    <button style="margin: 5px" id="btn_addKhxx_save" class="btn btn-green">保存</button>
</div>