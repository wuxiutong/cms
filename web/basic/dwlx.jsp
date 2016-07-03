<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
</style>
<script src="/js/wxt/basic/dwlx.js"></script>
<div class="bjui-pageHeader" style="text-align: center">
    <h3>
        客户类型信息管理
    </h3>
</div>
<div class="bjui-pageContent">
    <table id="dwlx_table" style="height:100%;" width="100%">
        <tr>
            <td height="30px" style="border-right: solid lightgrey 1px" width="30%">
                <div>
                    <div id="tip" style="text-align: left"><h6 id="dwlxfj">单位类型编码分级方案：</h6></div>
                </div>
            </td>
            <td rowspan="2" style=" text-align: center;vertical-align: top;border: solid lightgrey 1px;">
                <form id="dwlxForm" method="post" action="DwlxBaseServlet.add" class="pageForm required-validate"
                      data-validator-option="{focusCleanup:true,timely:0}" data-toggle="validate"
                      data-callback="navTabAlterDWLXAjaxDone">
                    <table style="margin:15px auto;">
                        <tr style="height: 30px">
                            <td style="text-align: right">
                                <label for="lxdm"><span>&nbsp;代&nbsp;码&nbsp;：</span></label>
                            </td>
                            <td>
                                <input id="lxdm" name="lxdm" data-rule="required;number" type="text" size="30"
                                       value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                <label for="lxmc">类型名称：</label>
                            </td>
                            <td>
                                <input id="lxmc" name="lxmc" data-rule="required" type="text" size="30" value=""
                                       alt=""/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                <label for="dwlxps">&nbsp;备&nbsp;注&nbsp;：</label>
                            </td>
                            <td>
                                <textarea name="ps" id="dwlxps" class="ps" rows="3" cols="30"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="" align="center" style="margin-top: 20px">
                                    <button id="save_dwlx" class="btn btn-blue" style="display: none">保存</button>
                                    <button id="del_dwlx" class="btn btn-red" type="submit" style="display: none">删除
                                    </button>
                                    <button id="alter_dwlx" class="btn btn-red" style="display: none">修改</button>
                                    <button id="add_dwlx" class="btn btn-green" type="submit">添加</button>
                                    <button type="button" class="btn btn-orange" id="cancle_dwlx">取消</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
        <tr>
            <td style="border: solid lightgrey 1px;">
                <div style="width:30%;height: 100%">
                    <ul id="ztree_dwlx" class="ztree"></ul>
                </div>
            </td>
        </tr>
    </table>
</div>