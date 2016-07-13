<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>贵州志达四方信息管理系统</title>
    <meta name="Keywords" content="贵州志达四方科技,用友，用友政务，账务处理"/>
    <meta name="Description" content="贵州志达四方科技有限公司客户信息管理系统"/>
    <!-- bootstrap - css -->
    <link href="BJUI/themes/css/bootstrap.css" rel="stylesheet">
    <!-- core - css -->
    <link href="BJUI/themes/css/style.css" rel="stylesheet">
    <link href="BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
    <!-- plug - css -->
    <link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
    <link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
    <link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
    <link href="BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
    <!--[if lte IE 7]>
    <link href="BJUI/themes/css/ie7.css" rel="stylesheet">
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lte IE 9]>
    <script src="BJUI/other/html5shiv.min.js"></script>
    <script src="BJUI/other/respond.min.js"></script>
    <![endif]-->
    <!-- jquery -->
    <script src="BJUI/js/jquery-1.7.2.min.js"></script>
    <script src="BJUI/js/jquery.cookie.js"></script>
    <!--[if lte IE 9]>
    <script src="BJUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
    <!-- BJUI.all 分模块压缩版 -->
    <%--<script src="BJUI/js/bjui-all.js"></script>--%>
    <!-- 以下是B-JUI的分模块未压缩版，建议开发调试阶段使用下面的版本 -->

    <script src="BJUI/js/bjui-core.js"></script>
    <script src="BJUI/js/bjui-regional.zh-CN.js"></script>
    <script src="BJUI/js/bjui-frag.js"></script>
    <script src="BJUI/js/bjui-extends.js"></script>
    <script src="BJUI/js/bjui-basedrag.js"></script>
    <script src="BJUI/js/bjui-slidebar.js"></script>
    <script src="BJUI/js/bjui-contextmenu.js"></script>
    <script src="BJUI/js/bjui-navtab.js"></script>
    <script src="BJUI/js/bjui-dialog.js"></script>
    <script src="BJUI/js/bjui-taskbar.js"></script>
    <script src="BJUI/js/bjui-ajax.js"></script>
    <script src="BJUI/js/bjui-alertmsg.js"></script>
    <script src="BJUI/js/bjui-pagination.js"></script>
    <script src="BJUI/js/bjui-util.date.js"></script>
    <script src="BJUI/js/bjui-datepicker.js"></script>
    <script src="BJUI/js/bjui-ajaxtab.js"></script>
    <script src="BJUI/js/bjui-datagrid.js"></script>
    <script src="BJUI/js/bjui-tablefixed.js"></script>
    <script src="BJUI/js/bjui-tabledit.js"></script>
    <script src="BJUI/js/bjui-spinner.js"></script>
    <script src="BJUI/js/bjui-lookup.js"></script>
    <script src="BJUI/js/bjui-tags.js"></script>
    <script src="BJUI/js/bjui-upload.js"></script>
    <script src="BJUI/js/bjui-theme.js"></script>
    <script src="BJUI/js/bjui-initui.js"></script>
    <script src="BJUI/js/bjui-plugins.js"></script>

    <!-- plugins -->
    <!-- swfupload for uploadify && kindeditor -->
    <script src="BJUI/plugins/swfupload/swfupload.js"></script>
    <!-- kindeditor -->
    <script src="BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
    <script src="BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
    <!-- colorpicker -->
    <script src="BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <!-- ztree -->
    <script src="BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
    <!-- nice validate -->
    <script src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
    <script src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
    <!-- bootstrap plugins -->
    <script src="BJUI/plugins/bootstrap.min.js"></script>
    <script src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
    <script src="BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
    <!-- icheck -->
    <script src="BJUI/plugins/icheck/icheck.min.js"></script>
    <!-- dragsort -->
    <script src="BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
    <!-- HighCharts -->
    <script src="BJUI/plugins/highcharts/highcharts.js"></script>
    <script src="BJUI/plugins/highcharts/highcharts-3d.js"></script>
    <script src="BJUI/plugins/highcharts/themes/gray.js"></script>
    <!-- ECharts -->
    <script src="BJUI/plugins/echarts/echarts.js"></script>
    <!-- other plugins -->
    <script src="BJUI/plugins/other/jquery.autosize.js"></script>
    <link href="BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="BJUI/plugins/download/jquery.fileDownload.js"></script>
    <%--吴秀桐 日期格式化--%>
    <script src="js/wxt/SimpleDateFormat.js"></script>
    <%--表单操作_吴秀桐--%>
    <script src="js/wxt.form.js"></script>
    <%--系统各个模块传递的参数--%>
    <script src="/js/wxt/params.js"></script>
    <%--表单样式_吴秀桐--%>
    <link href="styles/wxt.table_form.css" rel="stylesheet">
    <!-- for doc begin -->
    <link type="text/css" rel="stylesheet" href="/js/syntaxhighlighter-2.1.382/styles/shCore.css"/>
    <link type="text/css" rel="stylesheet" href="/js/syntaxhighlighter-2.1.382/styles/shThemeEclipse.css"/>
    <script type="text/javascript" src="/js/syntaxhighlighter-2.1.382/scripts/brush.js"></script>
    <%--<link href="doc/doc.css" rel="stylesheet">--%>
    <%--引入主页的脚本--%>
    <script type="text/javascript" src="js/wxt/init.js">
    </script>


</head>
<body>
<!--[if lte IE 7]>
<!--<div id="errorie">-->
<!--<div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank"-->
<!--href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a-->
<!--href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div>-->
<!--</div>-->
<%--<![endif]-->--%>
<div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse"
                    data-target="#bjui-navbar-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <%--<a class="bjui-navbar-logo" href="#" style="text-decoration:none">--%>
            <img src="images/logo.png">
            <%--<h3 style="color: white">贵州志达四方科技有限公司</h3>--%>
            <%--</a>--%>
        </div>
        <nav id="bjui-navbar-collapse" >
            <ul class="bjui-navbar-right">
                <li class="datetime">
                    <div><span id="bjui-date"></span> <span id="bjui-clock"></span></div>
                </li>
                <%--<li><a href="#">消息 <span class="badge">4</span></a></li>--%>
                <li class="dropdown"><a href="#" id="loginUser" class="dropdown-toggle"
                                        data-toggle="dropdown"><%=request.getSession().getAttribute("username")%> <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="changepwd.jsp" data-toggle="dialog" data-id="changepwd_page" data-mask="true"
                               data-width="400" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span>
                            修改密码&nbsp;</a></li>
                        <li><a href="#">&nbsp;<span class="glyphicon glyphicon-user"></span> 我的资料</a></li>
                        <li class="divider"></li>
                        <li><a href="logout.jsp" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span>
                            注销登陆</a></li>
                    </ul>
                </li>
                <%--<li><a href="index.jsp" title="切换为列表导航(窄版)" style="background-color:#ff7b61;">列表导航栏(窄版)</a></li>--%>
                <li class="dropdown"><a href="#" class="dropdown-toggle theme blue" data-toggle="dropdown" title="切换皮肤"><i
                        class="fa fa-tree"></i></a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i
                                class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                        <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i
                                class="fa fa-tree"></i> 橘子红了</a></li>
                        <li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i
                                class="fa fa-tree"></i> 紫罗兰</a></li>
                        <li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme"
                                              data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 天空蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i
                                class="fa fa-tree"></i> 绿草如茵</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>

    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a
                        href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu"
                     data-heightbox="#bjui-sidebar" data-offsety="26">

                    <div class="panel panel-default collapse in" style="">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="" href="#collapse_CWBill" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-newspaper-o"></i>&nbsp;单据管理
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_CWBill">
                            <div class="panel-body">
                                <ul data-faicon="hand-o-up" class="menu-items">
                                    <li><a data-options="{id:'tabs_newBill', faicon:'terminal'}"
                                           href="bills/newCWBill.jsp" title="新单"><i class="fa fa-pencil-square-o"></i>新单</a>
                                    </li>
                                    <li><a data-options="{id:'tabs_billBox', faicon:'terminal'}"
                                           href="bills/CWBillsBox.jsp" title="单据箱"><i class="fa fa-tasks"></i>单据箱</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="collapsed" href="#collapse_khxx" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-users"></i>&nbsp;客户管理
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_khxx">
                            <div class="panel-body">
                                <ul data-faicon="list" data-tit="客户管理" class="menu-items">
                                    <li>
                                        <a data-options="{id:'tab_newKhxx', faicon:'th-large'}" href="customer/add.jsp"
                                           title="新增客户">
                                            <i class="fa fa-user-plus"></i>新增客户
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tab_CustomerKhxx', faicon:'th-large'}"
                                           href="customer/management.jsp" title="客户管理">
                                            <i class="fa fa-user-secret"></i>客户管理
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <%--统计分析表--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="collapsed" href="#collapse_rpt" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-list"></i>&nbsp;报表统计分析
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_rpt">
                            <div class="panel-body">
                                <ul data-faicon="list" data-tit="统计分析" class="menu-items">
                                    <li>
                                        <a data-options="{id:'tab_rpt_whrq', faicon:'th-large'}"
                                           href="rpt/rpt_whrq.jsp"
                                           title="">
                                            <i class="fa fa-list-alt"></i>维护日期统计分析
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tab_rpt_ykpKhxx', faicon:'th-large'}"
                                           href="rpt/rpt_ykpKhxx.jsp"
                                           title="">
                                            <i class="fa fa-list-alt"></i>客户开票记录统计
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tab_rpt_khmxz', faicon:'th-large'}"
                                           href="rpt/rpt_khmxz.jsp"
                                           title="">
                                            <i class="fa fa-list-alt"></i>客户开票记录明细
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tab_rpt_dwysk', faicon:'th-large'}"
                                           href="rpt/rpt_dwysk.jsp"
                                           title="">
                                            <i class="fa fa-list-alt"></i>预计收费统计表
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="collapsed" href="#collapse_chart" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-area-chart"></i>&nbsp;图表分析
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_chart">
                            <div class="panel-body">
                                <ul data-faicon="list" data-tit="收入按月分析图" class="menu-items">
                                    <li>
                                        <a data-options="{id:'tabs_authorization', faicon:'th-large'}"
                                           href="charts/chart_kpMonth.jsp"
                                           title="收入按月分析图">
                                            <i class="fa fa-bar-chart"></i>收入按月分析图
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="collapsed" href="#collapse_basic" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-book"></i>&nbsp;基础数据管理
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_basic">
                            <div class="panel-body">
                                <ul data-faicon="list" data-tit="基础数据管理" class="menu-items">
                                    <li>
                                        <a data-options="{id:'tabs_region', faicon:'th-large'}"
                                           href="basic/region.jsp"
                                           title="地区信息管理">
                                            <i class="fa fa-map-marker"></i>地区信息管理
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tabs_dwlx', faicon:'th-large'}"
                                           href="basic/dwlx.jsp"
                                           title="单位类型">
                                            <i class="fa fa-sitemap"></i>单位类型
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tabs_gsxx', faicon:'th-large'}"
                                           href="basic/bloc.jsp"
                                           title="公司信息管理">
                                            <i class="fa fa-th-large"></i>公司信息管理
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tabs_soft', faicon:'th-large'}"
                                           href="basic/soft.jsp"
                                           title="软件信息管理">
                                            <i class="fa fa-shopping-cart"></i>软件信息管理
                                        </a>
                                    </li>
                                    <li>
                                        <a data-options="{id:'tabs_bmzy', faicon:'th-large'}"
                                           href="basic/bmzy.jsp"
                                           title="部门职员信息管理">
                                            <i class="fa fa-institution"></i>部门职员信息管理
                                        </a>
                                    </li> <li>
                                    <a data-options="{id:'tabs_init_ysk', faicon:'th-large'}"
                                       href="rpt/rpt_dwysk_init.jsp"
                                       title="初始化收费预算">
                                        <i class="fa fa-pencil"></i>初始化收费预算
                                    </a>
                                </li>
                                    <li>
                                        <a data-options="{id:'tabs_init_whrq', faicon:'th-large'}"
                                           href="rpt/rpt_whrq_init.jsp"
                                           title="初始化维护日期">
                                            <i class="fa fa-calendar"></i>初始化维护日期
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="collapsed" href="#collapse_admin" data-parent="#bjui-accordionmenu"
                                   data-toggle="collapse">
                                    <i class="fa fa-gears"></i>&nbsp;系统管理
                                    <b><i class="fa fa-angle-down"></i></b>
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="collapse_admin">
                            <div class="panel-body">
                                <ul data-faicon="list" data-tit="权限设置" class="menu-items">
                                    <li>
                                        <a data-options="{id:'tabs_authorization', faicon:'th-large'}"
                                           href="admin/authorization.jsp"
                                           title="权限设置">
                                            <i class="fa fa-shield"></i>权限设置
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url="index_layout.jsp"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a>
                        </li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
                        Loading...
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer id="bjui-footer">Copyright &copy; 2015 - 2016　<span>贵州志达科技有限公司</span>　
        <!--
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1252983288'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s23.cnzz.com/stat.php%3Fid%3D1252983288%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
        -->
    </footer>
</div>
</div>
</body>
</html>
