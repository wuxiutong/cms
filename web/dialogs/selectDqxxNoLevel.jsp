<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="test/javascript" src="/js/wxt/dialog/selectDqxxNoLevel.js"></script>
<div class="bjui-pageHeader" align="center">
    <h4>地区信息</h4>
</div>
<div class="bjui-pageContent">
    <table width="100%" style="width: 100%;height: 100%;border: solid lightgray 1px">
        <tr>
            <td width="40%" align="left" valign="top" style="border-right: solid lightgray 1px">
                <div class="zregionBackground left">
                    <ul id="AllRegionForDqxxNoLevel" class="ztree"></ul>
                </div>
            </td>
            <td width="60%" align="center" valign="top">
                <div>
                    <table class="table_form" style="border: none">
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>地区编号：</label>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <input id="dqdm" name="dqdm" readonly="readonly" type="text" size="20" value=""/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>地区名称：</label>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <input id="dqmc" name="dqmc" readonly="readonly" type="text" size="20" value=""
                                           alt=""/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label id="btn11">邮政编号：</label></td>
                            <td>
                                <p>
                                    <input name="postcode" readonly="readonly" id="postcode" class="text" type="text"
                                           size="20"
                                           value=""/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label for="fzr">业务经理：</label>

                                <p>
                            </td>
                            <td class="td_label">
                                <p style="text-align: left;padding-left: 0px">
                                    <input readonly="readonly" type="text" id="fzr" value="" size="20">
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>负责人姓名：</label>
                                </p>
                            </td>
                            <td><p>
                                <input class="readonly" readonly="readonly" size="20" id="fzrXm" readonly="readonly"
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
                            <td><p>
                                <textarea readonly="readonly" name="ps" id="ps" class="ps" rows="3"
                                          cols="20"></textarea>
                            </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td style="text-align: left;margin-left: 5px">
                                <div class="pageFormContent" style="margin:10px 0">
                                    <%--data-args内容格式：{pid:'1', dqdmmc:'201111'}--%>
                                    <a id="btn_lookbak" onclick="checkCurrent()" href="javascript:;"
                                       data-toggle="lookupback" class="btn btn-blue" title="选择本项"
                                       data-icon="check">选择</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <%--</form>--%>

                </div>
            </td>
        </tr>
    </table>
</div>
