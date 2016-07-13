<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
  //根据列name获取列ID
  function searchCell(titile){
    var result = null;
    $.each(columns_1,function(index,value){
      if(titile==(value.name)){
        result = index;
      }
    });
    return result;
  };

  //鼠标双击事件
  $.CurrentDialog.find(".table").bind("dblclick",function(e){
    var cell = $(e.target).closest("tr");
    $($(cell).find("a[data-toggle='lookupback']")).trigger("click");
  });
  function openMydialog(obj) {
    $(obj).dialog({id:'mydialog',mask:true, url:'doc/dialog/mydialog.html', title:'我的业务弹窗'});
  };
  //列数组变量
  var columns_1 = [
    {
      name: 'zydm',
      label: '职员代码',
      align: 'center'
    },
    {
      name: 'zyxm',
      label: '职员姓名',
      align: 'center'
    },
    {
      name: 'sex',
      label: '性别',
      align: 'center'
    },
    {
      name: 'ssbm',
      label: '所属部门',
      align: 'center'
    },
    {
      name: 'selectCell',
      label: '选择',
      align: 'center',
      width: 70
    }
  ];
  var DataTable_1 =  $('#tableAllZyxx').datagrid({
    gridTitle : '职员信息',
    local: 'local',
    dataUrl: 'Zyxx.getAll.action',
    dataType: 'json',
    sortAll: true,
    filterAll: false,
    columns: columns_1,
    hiddenFields: [{name:'zydmxm'}],
    editUrl: 'ajaxDone1.html',
    editMode: 'dialog',
    paging: false,
    showCheckboxCol: false,
    showEditBtnsCol: false,
    linenumberAll: true,
    fullGrid:true
  })
</script>
<%--<div class="bjui-pageHeader">--%>
  <%--<form id="pagerForm" data-toggle="ajaxsearch" action="table-edit-lookup.html" method="post">--%>
    <%--<input type="hidden" name="pageCurrent" value="1">--%>
    <%--<input type="hidden" name="pageSize" value="${model.pageSize}">--%>
    <%--<input type="hidden" name="orderField" value="${param.orderField}">--%>
    <%--<div class="bjui-searchBar">--%>
      <%--<label>名称：</label><input type="text" value="" name="code" size="10">&nbsp;--%>
      <%--<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;--%>
      <%--<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>--%>
      <%--<div class="pull-right">--%>
        <%--<button type="button" class="btn-blue" data-toggle="lookupback" data-lookupid="ids" data-warn="请至少选择一项职业" data-icon="check-square-o">选择选中</button>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<div class="bjui-pageContent">
  <table  id="tableAllZyxx"  data-height="100%"  data-toggle="datagrid" data-width="100%" class="table table-bordered">
  </table>
</div>
