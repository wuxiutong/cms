<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 15/5/20
  Time: 下午5:22
  该文档代表的是每月收款合计对比统计图
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
    <div style="margin:15px auto 0; width:96%;">
        <div class="row" style="padding: 0 8px;">
            <%--<div class="col-md-6">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading"><h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i>饼/漏斗图 <a--%>
                            <%--href="doc/chart/echarts.html" data-toggle="navtab" data-id="doc-echarts"--%>
                            <%--data-title="ECharts图表说明">使用说明</a></h3></div>--%>
                    <%--<div class="panel-body">--%>
                        <%--<div style="mini-width:400px;height:350px" data-toggle="echarts" data-type="pie,funnel"--%>
                             <%--data-url="echarts-pieData.html"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-6">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading"><h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i>中国地图 <a--%>
                            <%--href="doc/chart/echarts.html" data-toggle="navtab" data-id="doc-echarts"--%>
                            <%--data-title="ECharts图表说明">使用说明</a></h3></div>--%>
                    <%--<div class="panel-body">--%>
                        <%--<div style="mini-width:400px;height:350px" data-toggle="echarts" data-type="map"--%>
                             <%--data-url="echarts-mapData.html"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i>服务到期按月统计图
                    </h3>
                    </div>
                    <div class="panel-body">
                        <div style="mini-width:400px;height:350px" id="echarts_month" data-toggle="echarts"
                             data-type="bar,line" data-url="CHART_SKMonth?kjnd=2015-2016"></div>
                        <%--<div style="mini-width:400px;height:350px" data-toggle="echarts" data-type="bar,line" data-url="echarts-barData.html"></div>--%>
                    </div>
                    <div class="panel-footer">
                        <select name="kjnd" id="select_kjnd" onchange="initEcharts();">
                            <option value="2015-2016">2015-2016年</option>
                            <option value="2016-2017">2016-2017年</option>
                        </select>
                        <%--<button id="btn_click1">click1</button>--%>
                        <%--<button id="btn_click">click</button>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">关闭</button>
        </li>
    </ul>
</div>
<script>
    //  $.CurrentNavtab.find("select_kjnd").bind("selectedChange",function(e){
    //var $echarts = $.CurrentNavtab.find("echarts_month");

    var chart_echarts_month;
    function  initEcharts( ) {
        var value = "CHART_SKMonth?kjnd="+$.CurrentNavtab.find("#select_kjnd").val();
        $.CurrentNavtab.find("#echarts_month").attr("data-url",+value);
        var $echarts = $.CurrentNavtab.find("#echarts_month");
        $echarts.each(function () {
            var $element = $(this)
            var options = $element.data()
            var theme = options.theme ? options.theme : 'default'
            var typeArr = options.type.split(',')

            require.config({
                paths: {
                    echarts: BJUI.PLUGINPATH + 'echarts'
                }
            })

            require(
                    [
                        'echarts',
                        'echarts/theme/' + theme,
                        'echarts/chart/' + typeArr[0],
                        typeArr[1] ? 'echarts/chart/' + typeArr[1] : 'echarts'
                    ],
                    function (ec, theme) {
                        chart_echarts_month = ec.init($element[0], theme)

//                        $.get(options.url, function (chartData) {
                        $.get(value, function (chartData) {
                            chart_echarts_month.setOption(chartData)
                        }, 'json')
                    }
            )
        })
    }
    $.CurrentNavtab.find("#btn_click").bind('click', function () {
//        var myChart = require('echarts').init($.CurrentNavtab.find("#echarts_month"));
        chart_echarts_month.refresh();
//        alert("clicked");
    })
</script>