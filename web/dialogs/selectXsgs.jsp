<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

  //鼠标双击事件
  $.CurrentDialog.find(".table").bind("dblclick",function(e){
    var cell = $(e.target).closest("tr");
    $($(cell).find("a[data-toggle='lookupback']")).trigger("click");
  });
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

  function openMydialog(obj) {
    $(obj).dialog({id:'mydialog',mask:true, url:'doc/dialog/mydialog.html', title:'我的业务弹窗'});
  };
  //列数组变量
  var columns_1 = [
    {
      name: 'gsdm',
      label: '公司代码',
      align: 'center'
    },
    {
      name: 'gsmc',
      label: '公司名称',
      align: 'center'
    },
    {
      name: 'workphone',
      label: '办公电话',
      align: 'center'
    },
    {
      name: 'type_en',
      label: '公司类型',
      align: 'center'
    },
    {
      name: 'ps',
      label: '备注',
      align: 'center'
    },
    {
      name: 'selectCell',
      label: '选择',
      align: 'center',
      width: 70
    }
  ];
  var DataTable_1 =  $('#tableAllXsgs').datagrid({
    gridTitle : '销售公司信息',
    local: 'local',
    dataUrl: 'Bloc.getAllForDialog.do',
    dataType: 'json',
    sortAll: true,
    filterAll: false,
    columns: columns_1,
    hiddenFields: [{name:'khjl'}],
    editUrl: 'ajaxDone1.html',
    editMode: 'dialog',
    paging: false,
    showCheckboxCol: false,
    showEditBtnsCol: false,
    linenumberAll: true,
    fullGrid:true
  })
</script>
<div class="bjui-pageContent">
  <table  id="tableAllXsgs"  data-height="100%"  data-toggle="datagrid" data-width="100%" class="table table-bordered">
  </table>
</div>
