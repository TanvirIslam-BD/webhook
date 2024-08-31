function updateStatus(){
    $("#liveBuildStatus").each(function(ele){
        $.ajax({
            url:"/binary/liveBuildStatus",
            method:"GET",
            dataType:"json",
            context:$(this),
            success:function(content){
                if(content.isEnd === true){
                    window.location.href = "/binary/index"
                }else{
                    $(this).html(content.message)
                }
            },
            error:function(cont){
                window.location.href = "/binary/index"
            }
        });
    })
}

jQuery(document).ready(function () {
    updateStatus()
    setInterval(updateStatus, 2000);
});