function copyImplementer(){
    $(".implementer-copy").click(function(ele){
        var textField = $(this).closest(".implementer-wrapper").find(".implementer-text")
        textField.select()
        document.execCommand("copy");
    })
}

jQuery(document).ready(function () {
    copyImplementer()
});