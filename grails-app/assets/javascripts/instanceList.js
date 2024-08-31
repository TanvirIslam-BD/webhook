function copyImplementer(){
    $(".implementer-copy").click(function(ele){
        var textField = $(this).closest(".implementer-wrapper").find(".implementer-text")
        textField.select()
        document.execCommand("copy");
    })
}

function updateInstanceList() {
    $("#instance-list").each(function(ele){
        $.ajax({
            url:"/instance/liveInstanceList",
            method:"GET",
            context:$(this),
            success:function(content){
                $(this).html(content)
                copyImplementer()
            },
            error:function(cont){

            }
        });
    })
}

jQuery(document).ready(function () {
    updateInstanceList()
    setInterval(updateInstanceList, 2000);
});