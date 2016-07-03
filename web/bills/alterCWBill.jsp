<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 2015/8/13
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #nr tr {
        /*width: 60px;*/
        border-bottom: solid lightgrey 1px;
        height: 60px;
    }

    #nr tr td {
        vertical-align: middle;
    }

    td label {
        margin-left: 20px;
    }

    td input {
        margin-right: 20px;
    }

    td select {
        margin-right: 20px;
    }

    #alter_bills_djlx option
    td {
        /*border: solid blue 1px;*/
    }
</style>
<script src="/js/wxt/bills/alterCWBill.js" type="text/javascript"></script>
<div class="bjui-pageHeader">
    <div class="btn_groups" style="margin-left: 10px;margin-top:10px">
        <button type="button" id="btn_cwbills_xg" class="btn btn-green" data-icon="edit" aria-label="Left Align">
            <span> 修改 </span>
        </button>
        <button type="button" id="btn_cwbills_sh" disabled="disabled" class="btn btn-default" data-icon="check-circle"
                aria-label="Left Align">
            <span> 审核 </span>
        </button>
        <button type="button" id="btn_cwbills_xs" disabled="disabled" class="btn btn-default" data-icon="check-circle-o"
                aria-label="Left Align">
            <span> 销审 </span>
        </button>
        <button type="button" id="btn_cwbills_sk" disabled="disabled" class="btn btn-default" data-icon="credit-card"
                aria-label="Left Align">
            <span> 确认款项入账 </span>
        </button>
        <button type="button" id="btn_cwbills_tk" disabled="disabled" class="btn btn-default" data-icon="reply"
                aria-label="Left Align">
            <span> 取消入账 </span>
        </button>
        <button type="button" id="btn_cwbills_zf" disabled="disabled" class="btn btn-default" data-icon="trash"
                aria-label="Left Align">
            <span> 作废 </span>
        </button>
        <button type="button" id="btn_cwbills_hf" disabled="disabled" class="btn btn-default" data-icon="recycle"
                aria-label="Left Align">
            <span> 恢复 </span>
        </button>
        <button type="button" id="btn_cwbills_save" disabled="disabled" class="btn btn-default" data-icon="recycle"
                aria-label="Left Align">
            <span> 保存 </span>
        </button>
        <button type="button" id="btn_cwbills_cancel" disabled="disabled" class="btn btn-default" data-icon="recycle"
                aria-label="Left Align">
            <span> 取消 </span>
        </button>
    </div>
</div>
<div class="bjui-pageContent" style="height: 100%;text-align: center;vertical-align: middle">
    <form class="form-horizontal" id="alter_CWBill_form" data-validator-option="{focusCleanup:true,timely:0}"
          action="CWBillBaseServlet.alter" method="post"
          data-toggle="validate" id="CWBill" data-callback="navTabBillsAjaxDone">
        <table style="margin: 0 auto">
            <tr>
                <td>
                    <span id="success_info" class="label label-danger" style="display: none">已经保存</span></td>
                <td colspan="4" align="center">
                    <h3 style="text-align: center" class="page-header">财务开票单据</h3></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <label for="alter_bills_id" class="control-label">&nbsp;单&nbsp;据&nbsp;号：</label>
                    <input type="text" readonly="readonly" name="id" class="form-control" id="alter_bills_id"
                           placeholder="保存后自动生成">
                </td>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                    <label for="alter_bills_kprq" class="control-label">开票日期：</label>
                    <input type="text" class="form-control date" data-onlybtn=true name="kprq" id="alter_bills_kprq"
                           data-toggle="datepicker" data-rule="required;date">
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <fieldset>
                        <table id="nr">

                            <tr>
                                <td>
                                    <label for="alter_bills_khdm" class="control-label">付款单位：</label></td>
                                <td>
                                    <input id="alter_bills_khdm" type="text"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择单位']"
                                           name="khxx.khdmmc"
                                           value="" data-toggle="lookup" name="请选择"
                                           data-url="dialogs/selectKhxx.jsp"
                                           data-group="khxx" data-width="800" data-height="500"/>
                                </td>
                                <td>
                                    <label for="alter_bills_xsgs" class="control-label">收款单位：</label>
                                </td>
                                <td>
                                    <input id="alter_bills_xsgs" type="text"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择单位']"
                                           name="xsgs.gsdmmc"
                                           value="" data-toggle="lookup" name="xsgs.gsdmmc"
                                           data-url="dialogs/selectXsgs.jsp"
                                           data-group="xsgs" data-width="600" data-height="490"/>
                                </td>
                                <td>
                                    <label for="alter_bills_kpr" class="control-label">开票人：</label>
                                </td>
                                <td>
                                    <input id="alter_bills_kpr" class="form-control" type="text"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择人员']"
                                           name="khjl.zydmxm"
                                           value="" size="20" data-toggle="lookup" name="khjl.zydmmc"
                                           data-url="dialogs/selectZyxx.jsp"
                                           data-group="khjl" data-width="600" data-height="490"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="alter_bills_kjnd" class="control-label">归属年度：</label>
                                </td>
                                <td>
                                    <select class="form-control" name="kjnd" id="alter_bills_kjnd" data-rule="required">
                                        <option value="">--请选择年度--</option>
                                        <option value="2015-2016">2015-2016</option>
                                        <option value="2016-2017">2016-2017</option>
                                    </select>
                                </td>
                                <td>
                                    <label for="alter_bills_djlx" class="control-label">单据类型：</label>
                                </td>
                                <td>
                                    <select class="form-control" name="djlx" id="alter_bills_djlx" data-rule="required">
                                        <option value="">--请选择--</option>
                                        <option value="xsd">销售单</option>
                                        <option value="whd">维护单</option>
                                    </select>
                                </td>
                                <td class="alter_bills_fylx" style="display: none;">
                                    <label for="alter_bills_djlx" class="control-label">费用类型：</label>
                                </td>
                                <td class="alter_bills_fylx" style="display: none;">
                                    <select class="form-control" name="fylx" id="alter_bills_fylx">
                                        <option value="">--请选择--</option>
                                        <option value="year">年度维护费</option>
                                        <option value="once">按次维护费</option>
                                        <option value="jzf">建账费</option>
                                        <option value="other">其他</option>
                                    </select>
                                </td>
                            </tr>
                            <tr id="extend_xx" style="display:none">
                                <td>
                                    <label for="alter_bills_whzq" class="control-label ">维护年数：</label>
                                </td>
                                <td>
                                    <input type="text" name="nx" class="form-control" id="alter_bills_whzq"
                                           value="0"
                                           data-toggle="spinner" data-min="0" data-max="100" data-step="1" size="5"
                                           data-rule="integer">
                                </td>
                                <td>
                                    <label for="alter_bills_qsrq" class="control-label ">起始日期：</label>
                                </td>
                                <td>
                                    <input type="text" name="qsrq" data-onlybtn=true class="form-control date"
                                           id="alter_bills_qsrq"
                                           data-toggle="datepicker">
                                </td>
                                <td>
                                    <label for="alter_bills_jzrq" class="control-label ">结束日期：</label>
                                </td>
                                <td>

                                    <input type="text" name="jzrq" data-onlybtn=true class="form-control date"
                                           id="alter_bills_jzrq"
                                           data-toggle="datepicker">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="alter_bills_je" class="control-label">开票金额：</label>
                                </td>
                                <td>
                                    <input type="text" name="je" class="form-control" id="alter_bills_je"
                                           data-rule="required;number">
                                </td>
                                <td>
                                    <label for="alter_bills_p11" class="control-label">备注信息：</label>
                                </td>
                                <td>
                                    <input type="text" name="ps" class="form-control" id="alter_bills_p11">
                                </td>
                                <td>
                                    <label for="alter_bills_fph" class="control-label">&nbsp;发&nbsp;票&nbsp;号：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="fph" id="alter_bills_fph">
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label for="alter_bills_lrr" class="control-label">&nbsp;制&nbsp;单&nbsp;人：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" readonly="readonly" name="lrrdmmc"
                                           readonly="readonly" id="alter_bills_lrr">
                                </td>
                                <td>
                                    <label for="alter_bills_shr" class="control-label">&nbsp;审&nbsp;核&nbsp;人：</label>
                                </td>
                                <td>
                                    <input type="text" readonly="readonly" class="form-control" name="shrdmmc"
                                           readonly="readonly" id="alter_bills_shr">
                                </td>
                                <td>
                                    <label for="alter_bills_skr" class="control-label">&nbsp;收&nbsp;款&nbsp;人：</label>
                                </td>
                                <td colspan="5">
                                    <input type="text" readonly="readonly" class="form-control" name="skrdmmc"
                                           readonly="readonly" id="alter_bills_skr">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="6" align="center">

                    <%--<button class="btn-close"  onsubmit="javascript: return false;">关闭</button>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
