    //添加隐藏行的值和强制验证
    var clearValueAndVilidate = function () {
        $.CurrentNavtab.find("#add_bills_jsrq").val("");
        $.CurrentNavtab.find("#add_bills_jzrq").val("");
        $.CurrentNavtab.find("#add_bills_whzq").val(0);
        $('#CWBill').validator('setField', {
            qsrq: null,       //将不再验证
            jzrq: null,        //将不再验证
            nx: null,       //将不再验证
        });
        $.CurrentNavtab.find("#extend_xx").css("display", "none");
    }
    //注册单据类型选择框选择项改变事件
    $.CurrentNavtab.find("#add_bills_djlx").change(function () {
        var vs = $.CurrentNavtab.find("#add_bills_djlx").val();
        if ("whd" == vs) {
            $.CurrentNavtab.find(".add_bills_fylx").css("display", "table-cell");
            $('#CWBill').validator('setField', {
                fylx: 'required'   //将接受验证
            });
        } else {
        //    设置索引为0的选中
            $.CurrentNavtab.find("#add_bills_fylx").val('');
            //刷新选择器
            $.CurrentNavtab.find("#add_bills_fylx").selectpicker('render');
            clearValueAndVilidate();//清空隐藏行的值和强制验证要求
            $.CurrentNavtab.find("#extend_xx").css("display", "none");
            //设置选择框为隐藏状态
            $.CurrentNavtab.find(".add_bills_fylx").css("display", "none");
            $('#CWBill').validator('setField', {
                fylx: null,       //将不再验证
                kjnd: null,       //将不再验证
                nx: null      //将不再验证
            });
        }
    });
    //注册费用类型的选择框选项变更事件
    $.CurrentNavtab.find("#add_bills_fylx").change(function () {
            var value = $.CurrentNavtab.find("#add_bills_fylx").val();
            if ("year" == value ) {
                $.CurrentNavtab.find("#extend_xx").css("display", " table-row");
                $.CurrentNavtab.find(".add_bills_kjnd").css("display", " table-row");
                $('#CWBill').validator('setField', {
                    qsrq: 'required; date;',       //追加验证
                    jzrq: 'required; date;',  //追加验证
                    nx: 'required;'   //追加验证
                });
                //获取当前选中的客户代码
                var khdmmc  = $.CurrentNavtab.find("#add_bills_khdm").attr("value");
                if(khdmmc.length>0) {
                    //获取服务的截至日期
                    $.ajax({
                        url: "Get_RPT_WHQSRQ?khdmmc=" + khdmmc, success: function (data) {
                            //获取返回的客户信息
                            if (data.jzrq != null && data.jzrq != '') {
                                $.CurrentNavtab.find("#add_bills_qsrq").val(data.jzrq);
                            }
                        }
                    });
                }
            }else if ("other" == value ) {
                $.CurrentNavtab.find("#extend_xx").css("display", " table-row");
                $('#CWBill').validator('setField', {
                    qsrq:null,       //追加验证
                    jzrq:null,   //追加验证
                    nx:null   //追加验证
                }); //获取当前选中的客户代码
                var khdmmc  = $.CurrentNavtab.find("#add_bills_khdm").attr("value");
                if(khdmmc.length>0) {
                    //获取服务的截至日期
                    $.ajax({
                        url: "Get_RPT_WHQSRQ?khdmmc=" + khdmmc, success: function (data) {
                            //获取返回的客户信息
                            if (data.jzrq != null && data.jzrq != '') {
                                $.CurrentNavtab.find("#add_bills_qsrq").val(data.jzrq);
                            }
                        }
                    });
                }
            } else {
                clearValueAndVilidate();//清空隐藏行的值和强制验证要求
            }
        }
    );
//重置按钮事件
function btn_alterCWBill_reset(){
    $.CurrentNavtab.find("#success_info").css("display", "none");
    $.CurrentNavtab.find("#add_bills_djlx").val("");
    $.CurrentNavtab.find("#add_bills_djlx").trigger("change");
    $.CurrentNavtab.find("#add_bills_djlx").selectpicker('render');
    $.CurrentNavtab.find("#btn_newCWBill_reset").css("display", "none");
    $.CurrentNavtab.find("#btn_newCWBill_submit").css("display", "inline");
}
//提交完数据后的增加节点操作
function navTabBillsAjaxDone(json) {
    //  alert("back");
    if (json.statusCode == 200) {
        $(this).alertmsg("info", json.message);
        $.CurrentNavtab.find("#add_bills_id").val(json.id);
        $.CurrentNavtab.find("#success_info").css("display", "inline");
        $.CurrentNavtab.find("#btn_newCWBill_submit").css("display", "none");
        $.CurrentNavtab.find("#btn_newCWBill_reset").css("display", "inline");
    } else {
        $(this).alertmsg("error", json.message);
        $.CurrentNavtab.find("#success_info").css("display", "none");
    }
}
$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})