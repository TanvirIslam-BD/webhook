function updateStatus(){
    $("#instanceLiveStatus").each(function(ele){
        var instance = $("#instance").val()
        var otat = $("#otat").val()
        $.ajax({
            url:"/instance/liveStatus?otat="+otat+"&instance="+instance,
            method:"GET",
            dataType:"json",
            context:$(this),
            success:function(content){
                if(content.isEnd === true){
                    window.location.href = "/instance/index"
                }
                $(this).html(content.message)
            }
        });
    })
}

jQuery(document).ready(function () {
    updateStatus()
    setInterval(updateStatus, 2000);
});