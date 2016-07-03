<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 下午5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/wxt/basic/bloc.js"></script>
<div class="bjui-pagecontent">
    <table style="height: 100%;" width="100%">
        <tbody>
        <tr>
            <td colspan="2" align="center" style="border-bottom:solid lightgrey 1px;" height="30px"><h3>销售公司信息管理</h3>
            </td>
        </tr>
        <tr>
            <td describe="td_left" style="border: solid lightgrey 1px" width="30%" height="100%" valign="top"
                align="left">
                <div>
                    <ul id="enterprise" class="ztree"></ul>
                </div>
            </td>
            <td valign="top" height="100%" describe="td_right" align="center">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <form id="enterpriseForm" method="post"   class="pageForm required-validate"
                                  data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate" action="BlocBaseServlet.add" data-callback="navTabEnterpriseAjaxDone" >
                                <table class="table_form">
                                    <tr>
                                        <td class="td_label">
                                            <p>
                                                <label>公司代码：</label>
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <input id="gsdm" name="gsdm" data-rule="required;number"
                                                       data-ok="OK" type="text" size="20"
                                                       value=""/>
                                            </p>
                                        </td>
                                        <td class="td_label">
                                            <p>
                                                <label>公司名称：</label>
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <input id="gsmc" name="gsmc" data-rule="required"
                                                       data-ok="OK" type="text"
                                                       size="20" value="" alt=""/>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_label">
                                            <p>
                                                <label>公司性质：</label>
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <select name="type_en" id="type_en">
                                                    <option value="集团">集团</option>
                                                    <option value="一般纳税人">一般纳税人</option>
                                                    <option value="小规模企业">小规模企业</option>
                                                    <option value="个体工商户">个体工商户</option>
                                                </select>
                                            </p>
                                        </td>
                                        <td class="td_label">
                                            <p>
                                                <label>办公电话：</label>
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <input name="workphone" id="workphone" class="text" type="text"
                                                       size="20" value=""/>
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
                                                <textarea name="ps" id="enps" class="ps" rows="2" cols="20"></textarea>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div class="pageFormContent" align="center"
                                                 style="margin-top: 20px">
                                                <button id="save_en"  class="btn btn-blue" style="display: none">保存</button>
                                                <button id="del_en"    class="btn btn-red" style="display: none" data-toggle="alertmsg" data-options="{msg:'该操作将删除选中的节点数据！',okCall:'bloc_delNode', type:'confirm'}">删除</button>
                                             <button id="alter_en"  class="btn btn-green"  style="display: none">修改</button>
                                                <button id="add_en" class="btn btn-green" type="submit">添加</button>
                                                <button   id="cancle_en" class="btn btn-orange"  >取消</button>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>
