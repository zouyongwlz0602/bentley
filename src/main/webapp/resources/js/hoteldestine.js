$(function () {

    //$.alert("11111");

    function submit() {
        var defaultForm = $("form[name=defaultForm]") ;

        var destinedate = $("input[name=destinedate]").val() ;

        var daynum = $("input[name=daynum]").val() ;
        if(destinedate == "" || destinedate == null) {
            $.alert("请选择预定日期") ;
            return ;
        }
        if(daynum ==null || daynum=="") {
            $.alert("请输入入住天数") ;
            return ;
        }else{
            var check = /^[0-9]+$/ ;
            if(!check.test(daynum)) {
                $.alert("预定天数只能是大于0的数字") ;
                return ;
            }
            //$.alert(daynum);
            if(parseInt(daynum) > 100) {
                $.alert("预定要天数小于100") ;
                return ;
            }
        }
        defaultForm.submit() ;

    }

    $("#submitButton").click(function() {
        submit() ;
    });
});


