function selectMonth(obj,month) {
    $("a[name='month']").each(function() {
        if(obj == this) {
            $(this).removeClass("weui_btn_default").addClass("weui_btn_primary") ;
        }else {
            $(this).removeClass("weui_btn_primary").addClass("weui_btn_default") ;
        }
        $("input[name='month_in']").val(month) ;
        $("#money").text(parseInt(month) * pay) ;
    });
}
function setMonth(month) {
    $("a[name='month']").each(function() {
        var _this = this ;
        if($(_this).attr("value") == month) {
            $(_this).removeClass("weui_btn_default").addClass("weui_btn_primary") ;
        }else {
            $(_this).removeClass("weui_btn_primary").addClass("weui_btn_default") ;
        }
    });
    $("input[name='month_in']").val(month) ;
    $("#money").text(parseInt(month) * pay) ;
}
function payFuc() {
    $("#pay_button").unbind("click") ;
    var month = $("input[name='month_in']").val() ;
    if(month == null || month == "") {
        $.alert("请选择充值时间") ;
        setMonth("1") ;
        $("#pay_button").bind("click",payFuc()) ;
        return false ;
    }
    var check = /^[0-9]+$/ ;
    if(!check.test(month)) {
        $.alert("预定天数只能是大于0的数字") ;
        setMonth("1") ;
        $("#pay_button").bind("click",payFuc()) ;
        return false ;
    }
    $("#payform").attr("action","/wx/member/recharge/charge");
    $("#payform").submit() ;
}
$(function() {
    setMonth("3") ; //默认三个月
    //绑定支付按钮事件
    $("#pay_button").bind("click",function(){
        payFuc();
    }) ;
    //绑定时间按钮事件
    $("a[name='month']").each(function() {
        var _this = this ;
        $(_this).bind("click",function() {
            selectMonth(_this,$(_this).attr("value") ) ;
        });
    });

    //自定义时间文本框
    var bind_name="input";//定义所要绑定的事件名称
    if(navigator.userAgent.indexOf("MSIE")!=-1) bind_name="propertychange";//判断是否为IE内核 IE内核的事件名称要改为propertychange
    /*输入框键盘离开事件绑定*/
    var timer = null ;
    $("input[name='month_in']").bind(bind_name,function() {
        var _this = this ;
        clearTimeout(timer) ;
        timer = setTimeout(function() {
            var month = $(_this).val() ;
            if(month ==null || month=="" || month=="0") {
                month = "1" ;
            }
            var check = /^[0-9]+$/ ;
            if(!check.test(month)) {
                $.alert("预定天数只能是大于0的数字") ;
                setMonth("1") ;
                return ;
            }
            setMonth(month) ;
        },500);
    });
});