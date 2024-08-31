function updateSubscriptionList() {
    $("#livePackageSubscriptionList").each(function(ele){
        $.ajax({
            url:"/packageSubscription/liveSuspendedPackageSubscriptionList",
            method:"GET",
            context:$(this),
            success:function(content){
                $(this).html(content)
            },
            error:function(cont){

            }
        });
    })
}

jQuery(document).ready(function () {
    updateSubscriptionList()
    setInterval(updateSubscriptionList, 2000);
});