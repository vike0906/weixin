$(function(){
    //解决表单控件不能回弹 只有微信ios有这个问题
    $("input,select,textarea").blur(function(){
        setTimeout(() => {
            const scrollHeight = document.documentElement.scrollTop || document.body.scrollTop || 0;
        window.scrollTo(0, Math.max(scrollHeight - 1, 0));
    }, 100);
    })

});