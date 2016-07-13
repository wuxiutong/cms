<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 2015/8/13
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/wxt/bills/CWBillsBox.js" type="text/javascript"></script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="table-fixed.html" method="post">
        <input type="hidden" name="pageSize" value="${model.pageSize}">
        <input type="hidden" name="pageCurrent" value="${model.pageCurrent}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
    </form>
    <%--按钮组--%>
    <div class="btn_groups" style="margin-left: 10px;margin-top:10px">
        <button type="button" id="btn_cwbills_sx" class="btn btn-green" onclick="btn_cwbills_refresh()"
                data-icon="refresh" aria-label="Left Align">
            <span> 刷新 </span>
            <%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_xg" disabled="disabled" onclick="btn_cwbills_xg()" class="btn btn-default"
                data-icon="edit" aria-label="Left Align">
            <span> 修改 </span>
            <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_sh" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_sh()"
                data-icon="check-circle" aria-label="Left Align">
            <span> 审核 </span>
            <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_xs" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_xs()"
                data-icon="check-circle-o" aria-label="Left Align">
            <span> 销审 </span>
            <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_sk" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_sk()"
                data-icon="credit-card" aria-label="Left Align">
            <span> 确认款项入账 </span>
            <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_tk" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_tk()"
                data-icon="reply" aria-label="Left Align">
            <span> 取消入账 </span>
            <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_zf" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_zf()"
                data-icon="trash" aria-label="Left Align">
            <span> 作废 </span>
            <%--<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_hf" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_hf()"
                data-icon="recycle" aria-label="Left Align">
            <span> 恢复 </span>
            <%--<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>--%>
        </button>
        <button type="button" id="btn_cwbills_sc" disabled="disabled" class="btn btn-default" onclick="btn_cwbills_sc()"
                data-icon="remove" aria-label="Left Align">
            <span> 删除 </span>
            <%--<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>--%>
        </button>

    </div>
</div>
<div class="bjui-pageContent tableContent">

    <div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist" style="display: none" id="bill_box_tabs">
            <li role="presentation" class="active"><a href="#bill_box_all" aria-controls="home" role="tab"
                                                      data-toggle="tab">Home</a></li>
            <li role="presentation"><a href="#bill_box_wsh" aria-controls="profile" role="tab"
                                       data-toggle="tab">Profile</a></li>
            <li role="presentation"><a href="#bill_box_ysh" aria-controls="messages" role="tab" data-toggle="tab">Messages</a>
            </li>
            <li role="presentation"><a href="#bill_box_ysk" aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
            </li>
            <li role="presentation"><a href="#bill_box_yzf" aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
            </li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="bill_box_all" data-width="100%">

                <table id="datagrid_billBox_all" data-width="100%" data-height="95%"
                       class="table table-bordered tablefixed">
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="bill_box_wsh" data-width="100%">


                <table id="datagrid_billBox_wsh" data-width="100%" data-height="95%"
                       class="table table-bordered tablefixed">
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="bill_box_ysh">
                <table id="datagrid_billBox_ysh" data-width="100%" data-height="95%"
                       class="table table-bordered tablefixed">
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="bill_box_ysk">
                <table id="datagrid_billBox_ysk" data-width="100%" data-height="95%"
                       class="table table-bordered tablefixed">
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="bill_box_yzf">
                <table id="datagrid_billBox_yzf" data-width="100%" data-height="95%"
                       class="table table-bordered tablefixed">
                </table>
            </div>
        </div>

    </div>

</div>
<div class="bjui-pageFooter" style="text-align: left">
<div class="btn-group" data-toggle="buttons">
        <label class="btn bill_box_btn btn-blue active" id="tbs_all">
            <input type="radio" name="options" autocomplete="off" checked> 所有单据
            <%--<span class="badge">1000</span>--%>
        </label>
        <label class="btn bill_box_btn btn-orange" id="tbs_wsh">
            <input type="radio" name="options" autocomplete="off"> 未审核
            <%--<span class="badge">77</span>--%>
        </label>
        <label class="btn bill_box_btn btn-blue" id="tbs_ysh">
            <input type="radio" name="options" autocomplete="off"> 已审核
            <%--<span class="badge">3</span>--%>
        </label>
        <label class="btn bill_box_btn btn-green" id="tbs_ysk">
            <input type="radio" name="options" autocomplete="off"> 已收款
            <%--<span class="badge">3</span>--%>
        </label>
        <label class="btn bill_box_btn btn-red" id="tbs_yzf">
            <input type="radio" name="options" autocomplete="off"> 已作废
            <%--<span class="badge">3</span>--%>
        </label>
    </div>
</div>