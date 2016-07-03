//添加隐藏行的值和强制验证
function clearAlterCWBillValueAndVilidate() {
    $.CurrentNavtab.find("#alter_bills_jsrq").val("");
    $.CurrentNavtab.find("#alter_bills_jzrq").val("");
    $.CurrentNavtab.find("#alter_bills_whzq").val(0);
    $('#CWBill').validator('setField', {
        qsrq: null,       //将不再验证
        jzrq: null       //将不再验证
    });
    $.CurrentNavtab.find("#extend_xx").css("display", "none");
}

//修改按钮事件
$.CurrentNavtab.find("#btn_cwbills_xg").bind("click", function (e) {

    //去除所有输入框不可用状态
    removeFormReadonly("#alter_CWBill_form")
    //显示查找带回框（包含日期和查找带回）的按钮！
    $.CurrentNavtab.find("a.bjui-lookup").css("display", 'inline');
    //查找带回框不可用
    $.CurrentNavtab.find("input[data-toggle='lookup']").removeAttr("disabled");
    //显示增加减少按钮！
    $.CurrentNavtab.find("ul.bjui-spinner").css("display", 'inline');
    //去除选择下拉框的不可用属性！
    $.CurrentNavtab.find("button.selectpicker").removeAttr("disabled");

    //单据号和审核人录入人 收款人等只读状态的保持
    $.CurrentNavtab.find('#alter_bills_id').attr("readonly", "readonly");
    $.CurrentNavtab.find('#alter_bills_lrr').attr("readonly", "readonly");
    $.CurrentNavtab.find('#alter_bills_shr').attr("readonly", "readonly");
    $.CurrentNavtab.find('#alter_bills_skr').attr("readonly", "readonly");
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_save").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_save").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_save").removeAttr("disabled")
    $.CurrentNavtab.find("#btn_cwbills_cancel").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_cancel").addClass("btn-orange")
    $.CurrentNavtab.find('#btn_cwbills_cancel').removeAttr("disabled");

})

//审核按钮事件
$.CurrentNavtab.find("#btn_cwbills_sh").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.sh?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[已审核]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用收款和销审按钮
                $.CurrentNavtab.find("#btn_cwbills_xs").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_xs").addClass("btn-blue")
                $.CurrentNavtab.find('#btn_cwbills_xs').removeAttr("disabled");
                $.CurrentNavtab.find("#btn_cwbills_sk").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_sk").addClass("btn-green")
                $.CurrentNavtab.find('#btn_cwbills_sk').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});
//销审按钮事件
$.CurrentNavtab.find("#btn_cwbills_xs").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.xs?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[未审核]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用销审按钮
                $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-blue")
                $.CurrentNavtab.find('#btn_cwbills_xg').removeAttr("disabled");
                $.CurrentNavtab.find("#btn_cwbills_sh").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_sh").addClass("btn-green")
                $.CurrentNavtab.find('#btn_cwbills_sh').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});
//销核按钮事件
$.CurrentNavtab.find("#btn_cwbills_sk").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.sk?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[已经确认收款]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用取消入账按钮
                $.CurrentNavtab.find("#btn_cwbills_tk").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_tk").addClass("btn-blue")
                $.CurrentNavtab.find('#btn_cwbills_tk').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});

//取消入账按钮事件
$.CurrentNavtab.find("#btn_cwbills_tk").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.tk?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[已经取消入账]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用收款和销审按钮
                $.CurrentNavtab.find("#btn_cwbills_xs").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_xs").addClass("btn-blue")
                $.CurrentNavtab.find('#btn_cwbills_xs').removeAttr("disabled");
                $.CurrentNavtab.find("#btn_cwbills_sk").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_sk").addClass("btn-green")
                $.CurrentNavtab.find('#btn_cwbills_sk').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});
//恢复按钮事件
$.CurrentNavtab.find("#btn_cwbills_hf").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.hf?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[已经恢复至未审核]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用修改和审核按钮
                $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-orange")
                $.CurrentNavtab.find('#btn_cwbills_xg').removeAttr("disabled");
                $.CurrentNavtab.find("#btn_cwbills_sh").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_sh").addClass("btn-green")
                $.CurrentNavtab.find('#btn_cwbills_sh').removeAttr("disabled");
                $.CurrentNavtab.find("#btn_cwbills_zf").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_zf").addClass("btn-red")
                $.CurrentNavtab.find('#btn_cwbills_zf').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});

//作废按钮事件
$.CurrentNavtab.find("#btn_cwbills_zf").bind("click", function (e) {
    var values = $($.CurrentNavtab.find('#alter_bills_id')).val();
    //通过ajax审核数据
    $.ajax({
        url: "CWBillBaseServlet.zf?ids=" + values + ";", success: function (json) {
            if (json.statusCode == 200) {
                //初始化修改单据界面元素
                initAlterCWBill();
                $.CurrentNavtab.find('#success_info').text("[已经作废]");
                $.CurrentNavtab.find('#success_info').css("display", "inline");
                //清除按钮样式
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                //启用删除和恢复按钮
                $.CurrentNavtab.find("#btn_cwbills_hf").removeClass("btn-default")
                $.CurrentNavtab.find("#btn_cwbills_hf").addClass("btn-blue")
                $.CurrentNavtab.find('#btn_cwbills_hf').removeAttr("disabled");
            } else {
                $(this).alertmsg("error", json.message);
            }
        }
    });
});



//保存按钮事件
$.CurrentNavtab.find("#btn_cwbills_save").bind("click",function(){
    $.CurrentNavtab.find("#alter_CWBill_form").submit();
});
//取消按钮事件
$.CurrentNavtab.find("#btn_cwbills_cancel").bind("click",function(){
    initAlterCWBill();
    //清除按钮样式
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
    //启用刷新按钮
    $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
    $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-green")
    $.CurrentNavtab.find("#btn_cwbills_xg").removeAttr("disabled")
});
//提交完数据后的增加节点操作
function navTabBillsAjaxDone(json) {
    //  alert("back");
    if (json.statusCode == 200) {
        $(this).alertmsg("info", json.message);
        addFormReadonly("#alter_CWBill_form")
        $.CurrentNavtab.find("a.bjui-lookup").css("display", 'none');
        $.CurrentNavtab.find("ul.bjui-spinner").css("display", 'none');
        $.CurrentNavtab.find("button.selectpicker").attr("disabled", 'disabled');
        $.CurrentNavtab.find('input[data-toggle="datepicker"]').attr("readonly", 'readonly');
        //清除按钮样式
        $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
        $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
        $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
        $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
        $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
        $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
        //启用刷新按钮
        $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
        $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-orange")
        $.CurrentNavtab.find("#btn_cwbills_xg").removeAttr("disabled")
        $.CurrentNavtab.find("#btn_cwbills_sh").removeClass("btn-default")
        $.CurrentNavtab.find("#btn_cwbills_sh").addClass("btn-green")
        $.CurrentNavtab.find("#btn_cwbills_sh").removeAttr("disabled")
        $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
        $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-green")
        $.CurrentNavtab.find("#btn_cwbills_xg").removeAttr("disabled")
        $.CurrentNavtab.find("#btn_cwbills_zf").removeClass("btn-default")
        $.CurrentNavtab.find("#btn_cwbills_zf").addClass("btn-red")
        $.CurrentNavtab.find("#btn_cwbills_zf").removeAttr("disabled")
    } else {
        $(this).alertmsg("error", json.message);
        $.CurrentNavtab.find("#success_info").css("display", "none");
    }
}

//注册单据类型选择框选择项改变事件
$.CurrentNavtab.find("#alter_bills_djlx").change(function () {
    var vs = $.CurrentNavtab.find("#alter_bills_djlx").val();
    if ("whd" == vs) {
        $.CurrentNavtab.find(".alter_bills_fylx").css("display", "table-cell");
        $('#CWBill').validator('setField', {
            fylx: 'required'   //将不再验证
        });
    } else {
        //    设置索引为0的选中
        $.CurrentNavtab.find("#alter_bills_fylx").val('');
        //刷新选择器
        $.CurrentNavtab.find("#alter_bills_fylx").selectpicker('render');
        //clearValueAndVilidate();//清空隐藏行的值和强制验证要求
        $.CurrentNavtab.find("#extend_xx").css("display", "none");
        //设置选择框为隐藏状态
        $.CurrentNavtab.find(".alter_bills_fylx").css("display", "none");
        $('#CWBill').validator('setField', {
            fylx: null,       //将不再验证
        });
    }
});
//注册费用类型的选择框选项变更事件
$.CurrentNavtab.find("#alter_bills_fylx").change(function () {
    var value = $.CurrentNavtab.find("#alter_bills_fylx").val();
    if ("year" == value) {
        $.CurrentNavtab.find("#extend_xx").css("display", " table-row");
        $('#CWBill').validator('setField', {
            qsrq: 'required; date;',       //追加验证
            jzrq: 'required; date;'   //追加验证
        });
    } else if ("other" == value) {
        $.CurrentNavtab.find("#extend_xx").css("display", " table-row");
        $('#CWBill').validator('setField', {
            qsrq: 'date;',       //追加验证
            jzrq: 'date;'   //追加验证
        });
    } else {
        clearAlterCWBillValueAndVilidate();//清空隐藏行的值和强制验证要求
    }
});

function initAlterCWBill() {
    //注册单据类型选择框事件
    var alterCWBillID = $($.CurrentNavtab).attr("navTab_id");
    alterCWBillID = alterCWBillID.substring(alterCWBillID.indexOf("_") + 1)
    //从后台获取内容
    function initAlterCWBillValues(CWBillID) {
        //初始化组件
        $.ajax({
            url: "CWBillBaseServlet.getOneCWBill?id=" + CWBillID, success: function (data) {
                //获取返回的客户信息
                if (data.errorMessage) {
                    $(this).alertmsg("error", data.errorMessage);
                } else {
                    $.CurrentNavtab.find('#alter_bills_id').attr('value', data.id);
                    $.CurrentNavtab.find('#alter_bills_kprq').attr('value', data.kprq);
                    $.CurrentNavtab.find('#alter_bills_khdm').attr('value', data.khdm + "[" + data.khmc + "]");
                    $.CurrentNavtab.find('#alter_bills_xsgs').attr('value', data.gsdm + "[" + data.gsmc + "]");
                    $.CurrentNavtab.find('#alter_bills_kpr').attr('value', data.kpr + "[" + data.kprxm + "]");
                    $.CurrentNavtab.find('#alter_bills_djlx').val(data.djlx);
                    $.CurrentNavtab.find('#alter_bills_p11').val(data.ps);
                    $.CurrentNavtab.find('#alter_bills_je').val(data.je);
                    $.CurrentNavtab.find('#alter_bills_kjnd').val(data.kjnd);
                    $.CurrentNavtab.find("#alter_bills_kjnd").selectpicker('render');
                    $.CurrentNavtab.find('#alter_bills_fph').val(data.fph);
                    $.CurrentNavtab.find('#alter_bills_skr').val(data.skr != "" ? data.skr + "[" + data.skrxm + "]" : "");
                    $.CurrentNavtab.find('#alter_bills_shr').val(data.shr != "" ? data.shr + "[" + data.shrxm + "]" : "");
                    $.CurrentNavtab.find('#alter_bills_lrr').val(data.lrr != "" ? data.lrr + "[" + data.lrrxm + "]" : "");
                    //刷新选择器
                    $.CurrentNavtab.find("#alter_bills_djlx").selectpicker('render');
                    $.CurrentNavtab.find("#alter_bills_djlx").trigger("change");
                    //如果单据类型是维护单则需要设置费用类型
                    if (data.djlx == "whd") {
                        $.CurrentNavtab.find('#alter_bills_fylx').val(data.fylx);
                        $.CurrentNavtab.find("#alter_bills_fylx").selectpicker('render');
                        if (data.fylx == "year" || "other" == data.fylx) {
                            $.CurrentNavtab.find('#alter_bills_whzq').val(data.nx);
                            $.CurrentNavtab.find('#alter_bills_qsrq').val(data.qsrq);
                            $.CurrentNavtab.find('#alter_bills_jzrq').val(data.jzrq);
                            $.CurrentNavtab.find("#alter_bills_fylx").trigger("change");
                        }
                    }

                    $.CurrentNavtab.find("a.bjui-lookup").css("display", 'none');
                    $.CurrentNavtab.find("ul.bjui-spinner").css("display", 'none');
                    //查找带回框不可用
                    $.CurrentNavtab.find("input[data-toggle='lookup']").attr("disabled", 'disabled');
                    $.CurrentNavtab.find("button.selectpicker").attr("disabled", 'disabled');
                    $.CurrentNavtab.find('input[data-toggle="datepicker"]').attr("readonly", 'readonly');


                    //增加所有输入框不可用状态
                    addFormReadonly("#alter_CWBill_form")
                    //显示查找带回框（包含日期和查找带回）的按钮！
                    $.CurrentNavtab.find("a.bjui-lookup").css("display", 'none');
                    //查找带回框不可用
                    $.CurrentNavtab.find("input[data-toggle='lookup']").attr("disabled", "disabled");
                    //显示增加减少按钮！
                    $.CurrentNavtab.find("ul.bjui-spinner").css("display", 'none');
                    //去除选择下拉框的不可用属性！
                    $.CurrentNavtab.find("button.selectpicker").attr("disabled", "disabled");

                    //清除按钮样式
                    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-green");
                    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-red");
                    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-blue");
                    $.CurrentNavtab.find(".btn_groups button.btn").removeClass("btn-orange");
                    $.CurrentNavtab.find(".btn_groups button.btn").addClass("btn-default");
                    $.CurrentNavtab.find(".btn_groups button.btn").attr("disabled", "disabled")
                    //如果未审核状态则启用修改按钮和审核按钮
                    if (data.zt == 0) {
                        $.CurrentNavtab.find("#btn_cwbills_xg").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_xg").addClass("btn-orange")
                        $.CurrentNavtab.find('#btn_cwbills_xg').removeAttr("disabled");
                        $.CurrentNavtab.find("#btn_cwbills_sh").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_sh").addClass("btn-green")
                        $.CurrentNavtab.find('#btn_cwbills_sh').removeAttr("disabled");
                        $.CurrentNavtab.find("#btn_cwbills_zf").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_zf").addClass("btn-red")
                        $.CurrentNavtab.find('#btn_cwbills_zf').removeAttr("disabled");
                    }
                    //如果审核状态则启用销审和确认入账按钮
                    if (data.zt == 1) {
                        $.CurrentNavtab.find("#btn_cwbills_xs").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_xs").addClass("btn-blue")
                        $.CurrentNavtab.find('#btn_cwbills_xs').removeAttr("disabled");
                        $.CurrentNavtab.find("#btn_cwbills_sk").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_sk").addClass("btn-green")
                        $.CurrentNavtab.find('#btn_cwbills_sk').removeAttr("disabled");
                    }
                    //如果确认到账状态则启用取消入账按钮
                    if (data.zt == 2) {
                        $.CurrentNavtab.find("#btn_cwbills_tk").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_tk").addClass("btn-blue")
                        $.CurrentNavtab.find('#btn_cwbills_tk').removeAttr("disabled");
                    }
                    //如果作废状态则启用恢复和删除按钮
                    if (data.zt == -1) {
                        $.CurrentNavtab.find("#btn_cwbills_hf").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_hf").addClass("btn-blue")
                        $.CurrentNavtab.find('#btn_cwbills_hf').removeAttr("disabled");
                        $.CurrentNavtab.find("#btn_cwbills_sc").removeClass("btn-default")
                        $.CurrentNavtab.find("#btn_cwbills_sc").addClass("btn-blue")
                        $.CurrentNavtab.find('#btn_cwbills_sc').removeAttr("disabled");
                    }
                }
            }
        });
    };
    initAlterCWBillValues(alterCWBillID);
};

//初始化修改单据界面元素
initAlterCWBill();
//$.CurrentNavtab.find("a.bjui-lookup").css("display","none");

//$.CurrentNavtab.find("#alter_bills_xsgs").bind("dblclick",function(){
$.CurrentNavtab.find("input[data-toggle='lookup']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})
$.CurrentNavtab.find("input[data-toggle='datepicker']").bind("dblclick", function (e) {
    if ($(e.target).attr("disabled") != "disabled")
        $(e.target).siblings("a.bjui-lookup").trigger("click");
})