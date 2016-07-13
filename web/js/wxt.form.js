/**
 * Created by wuxiutong on 15/4/19.
 */
//清除Form的内容！！！！！
function clearForm(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
};

//设置Form的不可用信息
function addFormDisabled(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .attr("disabled","disabled");
};
//设置Form的只读信息
function addFormReadonly(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .attr("readonly","readonly");
};
//取消Form的只读信息
function removeFormReadonly(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .removeAttr("readonly");
};
//取消Form的不可用信息
function removeFormDisabled(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .removeAttr("disabled");
};
//设置所有的输入框INPUT背景的不可用信息
function clearFormBackground(id){
    $(':input',id)
        .not(':button, :submit, :reset, :hidden')
        .css("background-color","white");
};