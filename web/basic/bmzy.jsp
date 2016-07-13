<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 下午12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<SCRIPT type="text/javascript" src="/js/wxt/basic/bmzy.js"></SCRIPT>
<style>
    p {
        margin: auto;
    }

    .table_form tr {
        border: none;
    }

    .table_form tr td {
        width: 60px;
    }
</style>

<div class="bjui-pageContent">
    <table width="100%" style="height: 100%">
        <tbody>
        <tr>
            <td style="text-align: center ;border-bottom: solid lightgrey 1px" colspan="2" height="30px">
                <div><h3>部门职员信息</h3></div>
            </td>
        </tr>
        <tr>
            <td height="30px"
                style="border-left: solid lightgrey 1px;border-right: solid lightgrey 1px;border-bottom: solid lightgrey 1px">
                <div id="tip" style="text-align: left;"><h6 id="bmfjfa">部门编码分级方案：</h6></div>
            </td>
            <td valign="top" align="center" rowspan="3" style="border-bottom: solid lightgrey 1px;border-right: solid lightgrey 1px;">
                <div style="margin-top: 15px">
                    <form style="display: none" id="bmxxForm" data-validator-option="{focusCleanup:true,timely:0}"
                          data-toggle="validate" data-callback="navTabBmxxAjaxDone" method="post" action="Bmxx.add.action">
                            <table>
                                <tr>
                                    <td style="text-align: center">
                                        <table class="table_form" width="400px">
                                            <tbody>
                                            <tr>
                                                <td class="td_label">
                                                    <p>
                                                        <label>部门编号：</label>
                                                    </p>
                                                </td>
                                                <td class="td_content" width="100px">
                                                    <p>
                                                        <input id="bmdm" name="bmdm" data-rule="required;number"
                                                               type="text" size="20"
                                                               value=""/>
                                                    </p>
                                                </td>
                                                <td class="td_label">
                                                    <p>
                                                        <label>部门名称：</label>
                                                    </p></td>
                                                <td class="td_content" width="100px">
                                                    <p>
                                                        <input id="bmmc" name="bmmc" data-rule="required"
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
                                                    <input readonly="readonly" type="text" name="bmfzr.zydm" id="fzr"
                                                           value="" size="20" data-toggle="lookup" name="选择职员"
                                                           data-url="dialogs/selectZyxx.jsp"
                                                           data-group="bmfzr" data-width="600" data-height="490">
                                                </p>
                                                </td>
                                                <td class="td_label">
                                                    <p>
                                                        <label>负责人姓名：</label>
                                                    </p>
                                                </td>
                                                <td class="td_content"><p>
                                                    <input class="readonly" name="bmfzr.zyxm" id="fzrxm"
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
                                                        <textarea name="ps" id="ps_bm" class="ps" rows="2"
                                                                  cols="20"></textarea>
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">
                                                    <div class="pageFormContent" align="center"
                                                         style="margin-top: 20px">
                                                        <button id="save_bm" class="btn btn-blue" style="display: none">
                                                            保存
                                                        </button>
                                                        <button id="del_bm" class="btn btn-red" type="submit"
                                                                style="display: none">删除
                                                        </button>
                                                        <button id="alter_bm" class="btn btn-green"
                                                                style="display: none">修改
                                                        </button>
                                                        <button id="add_bm" class="btn btn-blue" type="submit">添加
                                                        </button>
                                                        <button type="button" class="btn btn-orange" id="cancle_bm">取消
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                    </form>
                    <form name="zyxxForm" id="zyxxForm" data-validator-option="{focusCleanup:true,timely:0}"
                          data-toggle="validate" data-callback="navTabZyxxAjaxDone" action="Zyxx.add.action"
                          method="post">
                        <table>
                            <tr>
                                <td style="text-align: center">
                                    <table class="table_form" width="100%"
                                           style="word-wrap:break-word;">
                                        <tbody>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>职员代码：</label>
                                                </p>
                                            </td>
                                            <td>
                                                <p>
                                                    <input id="zydm" name="zydm" data-rule="required" type="text"
                                                           size="20"
                                                           value=""/>
                                                </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>职员姓名：</label>
                                                </p>
                                            </td>
                                            <td><p>
                                                <input id="zyxm" name="zyxm" data-rule="required" type="text" size="20"
                                                       value=""
                                                       alt=""/>
                                            </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>性别：</label></p>
                                            </td>
                                            <td>
                                                <p>
                                                    <select name="sex" id="bmzy_sex" class="sex">
                                                        <option value="男"> 男</option>
                                                        <option value="女"> 女</option>
                                                    </select>
                                                </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>民族：</label></p>
                                            </td>
                                            <td><p>
                                                <input name="mz" id="mz" class="text" type="text" size="20" value=""/>
                                            </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>生 日：</label></p>
                                            </td>
                                            <td><p>
                                                <input type="text" id='birthday' name="birthday"
                                                       data-toggle="datepicker"
                                                       readonly="true"/>
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>联系电话：</label>
                                                </p>
                                            </td>
                                            <td><p>
                                                <input name="lxdh" id="lxdh" type="text" size="20"
                                                       value=""/>
                                            </p></td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>身份证号：</label>
                                                </p>
                                            </td>
                                            <td><p>
                                                <input name="sfzh" id="sfzh" class="text" type="text" size="20"
                                                       value=" "/>
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>家庭住址：</label></p>
                                            </td>
                                            <td><p>
                                                <input name="jtzz" id="jtzz" class="text" type="text" size="20"
                                                       value="云南省"/>
                                            </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>现居地址：</label></p>
                                            </td>
                                            <td><p>
                                                <input name="xjdz" id="xjdz" class="text" type="text" size="20"
                                                       value="贵阳市"/>
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>毕业学校：</label>
                                                </p>
                                            </td>
                                            <td><p>
                                                <input name="byxx" id="byxx" class="text" type="text" size="20"
                                                       value="贵阳学院"/>
                                            </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>专 业：</label></p></td>
                                            <td><p>
                                                <input name="zy" id="zy" data-rule="required" type="text" size="20"
                                                       value=""/>
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>学历：</label>
                                                </p>
                                            </td>
                                            <td>
                                                <p>
                                                    <select name="xl" id="xl" class="select">
                                                        <option label="研究生" value="研究生">研究生</option>
                                                        <option label="全日制本科" value="全日制本科" selected="true">全日制本科
                                                        </option>
                                                        <option label="大专" value="大专">大专</option>
                                                        <option label="中专" value="中专">中专</option>
                                                        <option label="其他" value="其他">其他</option>
                                                    </select>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>所属部门：</label></p></td>
                                            <td><p>
                                                <input readonly="readonly" data-rule="required" name="ssbm.bmdm" id="ssbm" type="text"
                                                       value="" size="20" data-toggle="lookup" name="选择部门"
                                                       data-url="dialogs/selectBmxx.jsp"
                                                       data-group="ssbm" data-width="600" data-height="490">
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>部门名称：</label></p></td>
                                            <td><p>
                                                <input class="readonly" name="ssbm.bmmc" id="ssbmMc" readonly="readonly"
                                                       type="text"/>
                                            </p></td>
                                        </tr>
                                        <tr>
                                            <td class="td_label">
                                                <p>
                                                    <label>入职日期：</label></p></td>
                                            <td><p>
                                                <input type="text" id="rzrq" name="rzrq" data-toggle="datepicker"
                                                       readonly="true"
                                                       value=""/>
                                            </p>
                                            </td>
                                            <td class="td_label">
                                                <p>
                                                    <label>备注：</label>
                                                </p></td>
                                            <td><p>
                                                <textarea name="ps" id="ps_zy" class="ps" rows="1" cols="20"></textarea>
                                            </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <div class="pageFormContent" align="center" style="margin-top: 20px">
                                                    <button id="save_zy" class="btn btn-blue" type="submit"
                                                            style="display: none">保存
                                                    </button>
                                                    <button id="del_zy" class="btn btn-red" style="display: none">删除
                                                    </button>
                                                    <button id="alter_zy" class="btn btn-green" style="display: none">
                                                        修改
                                                    </button>
                                                    <button id="add_zy" class="btn btn-blue" type="submit">添加</button>
                                                    <button type="button" class="btn btn-orange" id="cancle_zy">取消
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </td>
        </tr>
        <tr>
            <td width="30%" valign="top"
                style="border-left: solid lightgrey 1px;border-right: solid lightgrey 1px;text-align: left">
                <div>
                    <ul id="bmzy" class="ztree"></ul>
                </div>
            </td>
        </tr>
        <tr style="height:35px ">
            <td style="border: solid lightgrey 1px;border-top: none">
                <div>
                    <button class="btn btn-blue" id="addBmxx">增加部门</button>
                    <button class="btn btn-orange" id="addZyxx">增加职员</button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>