<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<SCRIPT type="text/javascript" src="/js/wxt/basic/soft.js">

</SCRIPT>
<div class="bjui-pageContent" height="100%">
    <div>
        <table style="height: 100%;;width: 100%;border: solid lightgrey 1px">
            <tbody>
            <tr>
                <td colspan="2" style="border-bottom:solid lightgrey 1px" align="center" height="30px"><h3>软件信息管理</h3>
                </td>
            </tr>
            <tr>
                <td style="border-right: solid lightgrey 1px;" width="30%" valign="top" align="left" height="100%">
                    <div>
                        <ul id="gyssoft" class="ztree"></ul>
                    </div>
                </td>
                <td style="" valign="top" align="center" height="100%">
                    <div>
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist" style="display: none" id="bill_box_tabs">
                            <li role="presentation" class="active"><a href="#tab_soft_gys" aria-controls="home"
                                                                      role="tab"
                                                                      data-toggle="tab">供应商</a></li>
                            <li role="presentation"><a href="#tab_soft_ver" aria-controls="profile" role="tab"
                                                       data-toggle="tab">版本</a></li>
                            <li role="presentation"><a href="#tab_soft_model" aria-controls="messages" role="tab"
                                                       data-toggle="tab">模块</a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab_soft_gys" data-width="100%">
                                <!--供应商表单-->
                                <form id="gysForm" data-validator-option="{focusCleanup:true,timely:0}"
                                      data-toggle="validate" method="post" action="Gys.add.action"
                                      data-callback="navTabGysAjaxDone">
                                    <table>
                                        <tr>
                                            <td align="center">
                                                <table class="table_form">
                                                    <tbody>
                                                    <tr>
                                                        <td calss="td_label">
                                                            <p>
                                                                <label>供应商代码：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p style="text-align: left;padding-left: 0px">
                                                                <input readonly="readonly" type="text"
                                                                       data-rule="required" name="gysdm" id="gysdm"
                                                                       value="" size="20">
                                                            </p>

                                                        </td>
                                                        <td calss="td_label">
                                                            <p>
                                                                <label>供应商名称：</label></p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input id="gysmc" name="gysmc" data-rule="required"
                                                                       type="text"
                                                                       size="20"
                                                                       value=""
                                                                       alt=""/>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td calss="td_label">
                                                            <p>
                                                                <label>供应商地址：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input name="address" id="address" class="text"
                                                                       type="text"
                                                                       size="20"
                                                                       value=""/>
                                                            </p>
                                                        </td>
                                                        <td calss="td_label">
                                                            <p>
                                                                <label>备注：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                        <textarea name="ps" id="ps" class="ps" rows="2"
                                                                  cols="20"></textarea>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div class="pageFormContent" align="center"
                                                                 style="margin-top: 20px">
                                                                <button id="save_gys" class="btn btn-blue"
                                                                        style="display: none">
                                                                    保存
                                                                </button>
                                                                <button id="del_gys" class="btn btn-red"
                                                                        style="display: none">删除
                                                                </button>
                                                                <button id="alter_gys" class="btn btn-green"
                                                                        style="display: none">修改
                                                                </button>
                                                                <button id="add_gys" class="btn btn-blue" type="submit">
                                                                    添加
                                                                </button>
                                                                <button type="button" id="btn_softGysCancel"
                                                                        class="btn btn-orange">取消
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
                            <div role="tabpanel" class="tab-pane" id="tab_soft_ver" data-width="100%">
                                <!--版本表单-->
                                <form id="softVerForm" data-validator-option="{focusCleanup:true,timely:0}"
                                      method="post" data-toggle="validate" action="SoftVerBaseServlet.add"
                                      data-callback="navTabVerAjaxDone">
                                    <table>
                                        <tr>
                                            <td align="center">
                                                <table class="table_form" align="center" valign="top"
                                                       style="height: 100%" width="100%">
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>版本代码：</label></p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input data-rule="required" id="verDm" name="verDm"
                                                                       class="remote" type="text"
                                                                       size="20" value=""/>
                                                            </p>
                                                        </td>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>版本名称：</label></p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input data-rule="required" id="verMc" name="verMc"
                                                                       type="text"
                                                                       size="20" value=""
                                                                       alt=""/>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>供应商：</label></p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input id="ver_gysDm" data-rule="required"
                                                                       name="gys.gysDm"
                                                                       value="" size="20" data-toggle="lookup"
                                                                       data-url="dialogs/selectSoftGys.jsp"
                                                                       data-group="gys" data-width="600"
                                                                       data-height="490"/>
                                                            </p>
                                                        </td>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>供应商名称：</label></p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input class="readonly" name="gys.gysMc" id="ver_gysMc"
                                                                       readonly="readonly"
                                                                       type="text"/>

                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>备注：</label></p>
                                                        </td>
                                                        <td colspan="3">
                                                            <p>
                                                        <textarea name="ps" id="verPs" class="ps" rows="2"
                                                                  cols="20"></textarea>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div class="pageFormContent" align="center"
                                                                 style="margin-top: 20px">
                                                                <button id="save_ver" class="btn btn-blue"
                                                                        style="display: none">保存
                                                                </button>
                                                                <button id="del_ver" class="btn btn-red" type="submit"
                                                                        style="display: none">删除
                                                                </button>
                                                                <button id="alter_ver" class="btn btn-green"
                                                                        style="display: none">修改
                                                                </button>
                                                                <button id="add_ver" class="btn btn-blue" type="submit">
                                                                    添加
                                                                </button>
                                                                <button type="button" id="btn_softVerCancel"
                                                                        class="btn btn-orange  ">取消
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </form>

                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab_soft_model">
                                <!--软件模块-->
                                <form id="softModelForm" data-validator-option="{focusCleanup:true,timely:0}"
                                      method="post" data-toggle="validate" action="AddSoftModel"
                                      data-callback="navTabModelAjaxDone">
                                    <table>
                                        <tr>
                                            <td align="center">
                                                <table class="table_form" align="center" valign="top">
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>模块代码：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input id="modeldm" data-rule="required" name="modelDm"
                                                                       class="remote" type="text" size="20" value=""/>
                                                            </p>
                                                        </td>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>模块名称：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input id="modelmc" data-rule="required" name="modelMc"
                                                                       type="text" size="20" value=""
                                                                       alt=""/>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>供应商：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input data-rule="required" data-rule="required"
                                                                       name="model.gysDm" id="model_gysdm"
                                                                       readonly="readonly"
                                                                       type="text"/>
                                                            </p>
                                                        </td>
                                                        <td class="td_label">

                                                            <p>
                                                                <label>供应商名称：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input class="readonly" name="model.gysMc"
                                                                       id="model_gysmc" readonly="readonly"
                                                                       type="text"/>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>软件版本：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input id="model_verdm" data-rule="required"
                                                                       name="model.verDm"
                                                                       value="" size="20" data-toggle="lookup"
                                                                       data-url="dialogs/selectSoftVersion.jsp"
                                                                       data-group="model" data-width="600"
                                                                       data-height="490"/>
                                                            </p>
                                                        </td>
                                                        <td class="td_label">
                                                            <p>
                                                                <label>版本名称：</label>
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <p>
                                                                <input class="readonly" name="model.verMc"
                                                                       id="model_vermc" readonly="readonly"
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
                                                                <textarea name="ps" id="modelps" class="ps" rows="3"
                                                                          cols="30"></textarea>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div class="pageFormContent" align="center"
                                                                 style="margin-top: 20px">
                                                                <button id="save_model" class="btn-blue btn"
                                                                        style="display: none">保存
                                                                </button>
                                                                <button id="del_model" class="btn btn-red" type="submit"
                                                                        style="display: none">删除
                                                                </button>
                                                                <button id="alter_model" class="btn btn-green"
                                                                        style="display: none">修改
                                                                </button>
                                                                <button id="add_model" class="btn btn-blue"
                                                                        type="submit">添加
                                                                </button>
                                                                <button type="button" id="btn_softModelCancel"
                                                                        class="btn btn-orange  ">取消
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td height="30px" style="border-right:solid lightgrey 1px">
                    <div>
                        <button id="addGys" class="btn btn-blue">增加供应商</button>
                        <button id="addSoftVer" class="btn btn-green">增加软件版本</button>
                        <button id="addSoftModel" class="btn btn-red">增加软件模块</button>
                    </div>
                </td>
                <td>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
