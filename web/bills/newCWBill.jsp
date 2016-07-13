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

    #add_bills_djlx option
    td {
        /*border: solid blue 1px;*/
    }
</style>
<script src="/js/wxt/bills/newCWBill.js" type="text/javascript"></script>
<div class="bjui-pageContent" style="height: 100%;text-align: center;vertical-align: middle">
    <form class="form-horizontal" data-validator-option="{focusCleanup:true,timely:0}" action="CWBillBaseServlet.add"
          method="post"
          data-toggle="validate" id="CWBill" data-callback="navTabBillsAjaxDone">
        <table style="margin: 0 auto">
            <tr>
                <td>
                    <span id="success_info" class="label label-danger" style="display: none">已经保存</span></td>
                <td colspan="4" align="center">
                    <h3 style="text-align: center" class="page-header">新增单据</h3></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <label for="add_bills_id" class="control-label">&nbsp;单&nbsp;据&nbsp;号：</label>
                    <input type="text" readonly="readonly" name="id" class="form-control" id="add_bills_id"
                           placeholder="保存后自动生成">
                </td>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                    <label for="add_bills_kprq" class="control-label">开票日期：</label>
                    <input type="text" class="date" name="kprq" id="add_bills_kprq" data-toggle="datepicker"
                           data-rule="required;date">
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <fieldset>
                        <table id="nr">

                            <tr>
                                <td>
                                    <label for="add_bills_khdm" class="control-label">付款单位：</label></td>
                                <td>
                                    <input id="add_bills_khdm" type="text" placeholder="请输入"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择单位']"
                                           name="khxx.khdmmc"
                                           value="" data-toggle="lookup" name="请选择"
                                           data-url="dialogs/selectKhxx.jsp"
                                           data-group="khxx" data-width="800" data-height="500"/>
                                </td>
                                <td>
                                    <label for="add_bills_xsgs" class="control-label">收款单位：</label>
                                </td>
                                <td>
                                    <input id="add_bills_xsgs" type="text" placeholder="请输入"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择单位']"
                                           name="xsgs.gsdmmc"
                                           value="" data-toggle="lookup" name="xsgs.gsdmmc"
                                           data-url="dialogs/selectXsgs.jsp"
                                           data-group="xsgs" data-width="600" data-height="490"/>
                                </td>
                                <td>
                                    <label for="add_bills_kpr" class="control-label">开票人：</label>
                                </td>
                                <td>
                                    <input id="add_bills_kpr" class="form-control" type="text"
                                           data-rule="required; select" data-rule-select="[/^.+\[.*\]$/, '请选择人员']"
                                           name="khjl.zydmxm"
                                           value="" size="20" data-toggle="lookup" name="khjl.zydmmc"
                                           data-url="dialogs/selectZyxx.jsp"
                                           data-group="khjl" data-width="600" data-height="490" placeholder="请选择"/>
                                </td>
                            </tr>
                            <tr>
                                <td >
                                    <label for="add_bills_kjnd" class="control-label">所属年度：</label>
                                </td>
                                <td >
                                    <select class="form-control" name="kjnd" id="add_bills_kjnd"
                                            data-toggle="selectpicker" data-rule="required">
                                        <option value="">--请选择年度--</option>
                                        <option value="2015-2016">2015-2016</option>
                                        <option value="2016-2017">2016-2017</option>
                                    </select>
                                </td>
                                <td>
                                    <label for="add_bills_djlx" class="control-label">单据类型：</label>
                                </td>
                                <td>
                                    <select class="form-control" name="djlx" id="add_bills_djlx"
                                            data-toggle="selectpicker"
                                            data-rule="required">
                                        <option value="">--请选择--</option>
                                        <option value="xsd">销售单</option>
                                        <option value="whd">维护单</option>
                                    </select>
                                </td>
                                <td class="add_bills_fylx" style="display: none;">
                                    <label for="add_bills_djlx" class="control-label">费用类型：</label>
                                </td>
                                <td class="add_bills_fylx" style="display: none;">
                                    <select class="form-control" name="fylx" id="add_bills_fylx"
                                            data-toggle="selectpicker">
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
                                    <label for="add_bills_whzq" class="control-label ">维护年数：</label>
                                </td>
                                <td>
                                    <input type="text" name="nx" class="form-control" id="add_bills_whzq"
                                           value="0"
                                           data-toggle="spinner" data-min="0" data-max="100" data-step="1" size="5"
                                           data-rule="integer">
                                </td>
                                <td>
                                    <label for="add_bills_qsrq" class="control-label ">起始日期：</label>
                                </td>
                                <td>
                                    <input type="text" name="qsrq" class="form-control date" id="add_bills_qsrq"
                                           data-toggle="datepicker">
                                </td>
                                <td>
                                    <label  for="add_bills_jzrq" class="control-label ">结束日期：</label>
                                </td>
                                <td>

                                    <input type="text" name="jzrq" onlybtn=true class="form-control date" id="add_bills_jzrq"
                                           data-toggle="datepicker">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="add_bills_je" class="control-label">开票金额：</label>
                                </td>
                                <td>
                                    <input type="text" name="je" class="form-control" id="add_bills_je"
                                           data-rule="required;number"
                                           placeholder="请输入金额">
                                </td>
                                <td>
                                    <label for="add_bills_p11" class="control-label">备注信息：</label>
                                </td>
                                <td>
                                    <input type="text" name="ps" class="form-control" id="add_bills_p11"
                                           placeholder="请输入">
                                </td>
                                <td>
                                    <label for="add_bills_xsgs" class="control-label">&nbsp;发&nbsp;票&nbsp;号：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="fph" id="add_bills_fph"
                                           placeholder="请输入">
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label for="add_bills_xsgs" class="control-label">&nbsp;录&nbsp;入&nbsp;人：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="lrrdmmc" readonly="readonly"
                                           id="add_bills_lrr"
                                           placeholder="请输入">
                                </td>
                                <td>
                                    <label for="add_bills_je" class="control-label">&nbsp;审&nbsp;核&nbsp;人：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="shrdmmc" readonly="readonly"
                                           id="add_bills_shr"
                                           placeholder="请输入">
                                </td>
                                <td>
                                    <label for="add_bills_khdm" class="control-label">&nbsp;收&nbsp;款&nbsp;人：</label>
                                </td>
                                <td colspan="5">
                                    <input type="text" class="form-control" name="skrdmmc" readonly="readonly"
                                           id="add_bills_skr"
                                           placeholder="请输入">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="6" align="center">
                    <button type="reset" style="display: none;" onclick="btn_alterCWBill_reset()" class="btn-blue"
                            id="btn_newCWBill_reset">新增
                    </button>
                    <button type="submit" class="btn-green" id="btn_newCWBill_submit">提交</button>
                    <%--<button class="btn-close" onsubmit="javascript: return false;">关闭</button>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
