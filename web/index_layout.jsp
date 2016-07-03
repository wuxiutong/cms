
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
</script>
<div class="bjui-pageHeader" style="background:#FFF;">
    <div style="padding: 0 15px;">
        <h2 style="margin-bottom:20px;">
          贵州志达四方科技有限公司　<small>信息化建设 我们一直在努力</small>
        </h2>
        <%--<div style="float:left; width:157px;">--%>
            <%--<div class="alert alert-info" role="alert" style="margin:0 0 5px; padding:10px;">--%>
                <%--&lt;%&ndash;<img src="images/ewm.png" width="135">&ndash;%&gt;--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
<div class="bjui-pageContent">
    <div style="position:absolute;top:15px;right:0;width:300px;" align="left">
        <p style="color: red">请使用那个火狐浏览器：<a href="softWar/FireFox_v40.exe">火狐浏览器下载</a></p>

        <%--<iframe width="100%" height="550" class="share_self" frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=1&noborder=1&isWeibo=1&isFans=0&uid=2838273614&verifier=75a3b95b&dpc=1"></iframe>--%>
    </div>
    <div style="margin-top:5px; margin-right:300px; overflow:hidden;">
    </div>
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
</div>