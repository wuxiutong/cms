<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/24
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    $(document).ready(function () {
        $("#tableAllZyxx").bind("dblclick", function () {
            var data = $('#test-datagrid').data('selectedTrs');
            $.each(data, function (i, n) {
                var ce = n.cells;
                alert($(ce[searchCell("zydm")]).text());
            });
        });
    });

    //鼠标双击事件
    $.CurrentDialog.find(".table").bind("dblclick", function (e) {
        var cell = $(e.target).closest("tr");
        $($(cell).find("a[data-toggle='lookupback']")).trigger("click");
    });
    //根据列name获取列ID
    function searchCell(titile) {
        var result = null;
        $.each(columns_1, function (index, value) {
            if (titile == (value.name)) {
                result = index;
            }
        });
        return result;
    };

    var columns_1 = [
        {
            name: 'khdmmc',
            label: '客户',
            align: 'left',
            width: '280'
        },
        {
            name: 'ssdq',
            label: '所属地区',
            align: 'left',
            width: '120'
        },
        {
            name: 'khlx',
            label: '客户类型',
            align: 'left',
            width: '120'
        },
        {
            name: 'khjl',
            label: '客户经理',
            align: 'left',
            width: '120'
        },
        {
            name: 'gzdz',
            label: '工作地址',
            align: 'left',
            width: '120'
        }, {
            name: 'selectCell',
            label: '选择',
            align: 'left',
            width: '70'
        }
    ];
    var DataTable_1 = $('#tableAllKhxxForDialog').datagrid({
        gridTitle: '客户信息',
        local: 'local',
        dataUrl: 'KhxxBaseServlet.getAllForDialog?dqdmmc=999999999&keyword=notdisplay',
        dataType: 'json',
        sortAll: true,
        filterAll: false,
        columns: columns_1,
        editUrl: 'ajaxDone1.html',
        editMode: 'dialog',
        paging: false,
        showCheckboxCol: false,
        showEditBtnsCol: false,
        linenumberAll: true,
        fullGrid: false
    })

    $.CurrentDialog.find("#searchBtn").bind("click", function () {
        // initDatagrid_rpt_khmxz();

        var dqdmmc = $.CurrentDialog.find("#khxx_searche_dqxx").val();
        var keyword = $.CurrentDialog.find("#khxx_searche_keyword").val();
        var url = 'KhxxBaseServlet.getAllForDialog?dqdmmc=' + dqdmmc + "&keyword=" + keyword ;
        $($.CurrentDialog.find("#tableAllKhxxForDialog")).datagrid('refreshByUrl', url);
        $($.CurrentDialog.find("#tableAllKhxxForDialog")).datagrid('initTfoot');
    });
</script>
<%--地区信息ztree初始化--%>
<script>
    //选择事件
    function S_NodeCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId),
                nodes = zTree.getCheckedNodes(true)
        var ids = '', names = ''

        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].isParent) {
            } else {
                ids += ',' + nodes[i].id
                names += ',' + nodes[i].name
            }
        }
        if (ids.length > 0) {
            ids = ids.substr(1), names = names.substr(1)
        }

        var $from = $('#' + treeId).data('fromObj')

        if ($from && $from.length) $from.val(names)

    }
    function back() {
        $("#AllRegionForDqxxNoLevelForKhxx").hide();
    }
    //单击事件
    function S_NodeClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)

        zTree.checkNode(treeNode, !treeNode.checked, true, true)
        event.preventDefault()
//    $("#j_select_tree2").hide();
        //alert($(zTree).hide());
    }
    //初始化客户类型
    //初始化地区信息供选择，
    function initDqxxDialogNoLevelForSelectKhxx() {
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            }, check: {
                enable: true
            },
            callback: {
//        beforeClick: zTreeBeforeClick,
                onCheck: S_NodeCheck,
//        onDblClick: zTreeOnDblClick
            },
            async: {
                enable: true,
                url: "RegionBaseServlet.getAll",
                autoParam: ["id", "name=n", "level=lv"],
                otherParam: {"otherParam": "zTreeAsyncTest"},
                dataFilter: filter,
                dataType: "json"
            }
        };

        function zTreeBeforeClick(treeId, treeNode, clickFlag) {
//    if ($("#region").attr("disabled")) {
//      return false;
//    } else {
//      return (treeNode.id !== false);
//    }
        };
        function filter(treeId, parentNode, childNodes) {
            if (!childNodes) return null;
            for (var i = 0, l = childNodes.length; i < l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }

        $.fn.zTree.init($("#AllRegionForDqxxNoLevelForKhxx"), setting)
    }
    //检测是否选择末级
    function checkCurrent() {
        if ('lookupback' != $("#btn_lookbak").attr("data-toggle")) {
        }
        //   $(this).alertmsg('info', '你未选中地区信息或者选择了非末级地区!', {displayMode:'fade', displayPosition:'middlecenter',  title:'提示'})
    }
    //搜索节点
    function searchNode(id) {
        var zTree = $.fn.zTree.getZTreeObj("AllRegion");
        var node = zTree.getNodeByParam("id", id);
        if (node) {
            alert(node.name);
        }
    }
    function returnTrue() {
        return true;
    }
    initDqxxDialogNoLevelForSelectKhxx();
</script>
<div class="bjui-pageHeader">
    <form id="form_ykpKhxx" >
        <div class="row" >
            <div class="col-md-10" align="left">
                <div class="zregionBackground left" style="display: none">
                    <ul id="AllRegionForDqxxNoLevelForKhxx" class="ztree" style="display: none"></ul>
                </div>

                <input id="khxx_searche_dqxx" name="dqdmmc" data-toggle="selectztree"
                       data-tree="#AllRegionForDqxxNoLevelForKhxx" placeholder="请选择所属地区" style="margin-left: 15px "/>
                <input type="text" name="keyword" id="khxx_searche_keyword" placeholder="请输入客户名称关键字" >
            </div>
            <div class="col-md-2">
                <button type="button" id="searchBtn" class="btn btn-green" >检索</button>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table id="tableAllKhxxForDialog" data-height="100%" data-toggle="datagrid" data-width="100%" class="table table-bordered">
    </table>
</div>
