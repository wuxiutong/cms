<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="test/javascript" src="/js/wxt/dialog/selectBmxx.js"></script>
<div class="bjui-pageContent">
    <table width="100%" style="width: 100%;height: 100%;border: solid lightgray 1px">
        <tr>
            <td colspan="2" align="center" height="20px" style="border-bottom: solid lightgray 1px">
                <h4>部门信息</h4>
            </td>
        </tr>
        <tr>
            <td width="40%" align="left" valign="top" style="border-right: solid lightgray 1px">
                <div class="zregionBackground left">
                    <ul id="AllBmxx" class="ztree"></ul>
                </div>
            </td>
            <td width="60%" align="center" valign="top">
                <div>
                    <table class="table_form" width="100%">
                        <tbody>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>部门编号：</label>
                                </p>
                            </td>
                            <td class="td_content" width="100px">
                                <p>
                                    <input readonly="readonly" id="bmdm" name="bmdm"
                                           data-ok="OK" type="text" size="20"
                                           value=""/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>部门名称：</label>
                                </p></td>
                            <td class="td_content" width="100px">
                                <p>
                                    <input readonly="readonly"  id="bmmc" name="bmmc"
                                           type="text" size="20"
                                           value=""
                                           alt=""/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>部门负责人：</label>
                                </p>
                            </td>
                            <td class="td_content"><p>
                                <input  readonly="readonly" id="fzr" name="fzr"  type="text" size="20"
                                       value=""
                                       alt=""/>
                            </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>负责人姓名：</label>
                                </p>
                            </td>
                            <td class="td_content"><p>
                                <input readonly="readonly"  class="readonly" name="fzrxm" id="fzrxm"
                                       readonly="readonly"
                                       type="text"/>
                            </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>备注：</label>
                                </p>
                            </td>
                            <td colspan="3">
                                <p>
                                    <textarea readonly="readonly"  name="ps" id="ps" class="ps" rows="2" cols="20"></textarea>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <div style="margin:10px 0">
                                    <a id="btn_lookbak" onclick=" dialogBmxx_checkCurrent()" href="javascript:;"
                                       data-toggle="lookupback" class="btn btn-blue" title="选择本项"
                                       data-icon="check">选择</a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</div>
