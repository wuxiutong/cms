<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/23
  Time: 上午7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<SCRIPT type="text/javascript" src="/js/wxt/basic/region.js"></SCRIPT>

<div class="bjui-pageContent">
    <table  style="height:100%;" width="100%">
        <tr>
            <td colspan="2" style="text-align: center;border-bottom: solid lightgrey 1px" height="40px">
                <div>
                    <h3>地区资料</h3>
                </div>
            </td>
        </tr>
        <tr>
            <td height="30px" style="border-right: solid lightgrey 1px">
                <div id="tip" style="text-align: left;"><h6 id="dqfjfa">地区编码分级方案：</h6></div>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td width="30%" style="border-right: solid lightgrey 1px;vertical-align: top">
                <div class="zregionBackground left">
                    <ul id="region" class="ztree"></ul>
                </div>
            </td>
            <td style=" text-align: center;vertical-align: top;border:none;" width="70%">
                <form id="dqxxForm"  method="post" action="RegionBaseServlet.add" data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate" data-callback="navTabDqxxAjaxDone" tyle="text-align: center; margin-top: -30px;border: none">
                    <table class="table_form" style="border: none">
                        <tr>
                            <td class="td_label">
                                <p>
                                    <label>地区编号：</label>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <input id="dqdm" name="dqdm"  data-rule="required;number"    type="text" size="20" value=""/>
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
                                    <input id="dqmc" name="dqmc"   data-rule="required" type="text" size="20" value=""
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
                                    <input name="postcode" id="postcode" class="text" type="text" size="20"
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
                                    <input   readonly="readonly" type="text" name="dqfzr.zydm" id="fzr" value="" size="20" data-toggle="lookup" name="选择职员" data-url="dialogs/selectZyxx.jsp"
                                           data-group="dqfzr" data-width="600" data-height="490">
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
                                <input   name="dqfzr.zyxm" size="20" id="fzrXm"  readonly="readonly"   type="text"/>
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
                                <textarea name="ps" id="ps" class="ps" rows="3" cols="20"></textarea>
                            </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td style="text-align: left;margin-left: 5px">
                                <div  style="margin:10px 0">
                                    <button id="save" class ="btn btn-blue" style="display: none">保存</button>
                                    <button id="del" class ="btn btn-red"  type="submit" style="display: none">删除</button>
                                    <button id="alter" class ="btn btn-green"  style="display: none">修改</button>
                                    <button id="add"  class ="btn btn-red"  type="submit">添加</button>
                                    <button type="button" class ="btn btn-orange"  id="cancle_dqxx">取消</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
</div>
