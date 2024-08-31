function updatePrice(){
    var price = $("#productDataList").find(":selected").data("price")
    $("#price").val(price)
}

var defineOnChange = function () {
    $("#productDataList").change(function(){
        updatePrice()
    })
};
jQuery(document).ready(function () {
    updatePrice()
    defineOnChange()
});