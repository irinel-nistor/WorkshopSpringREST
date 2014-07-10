var orderFormat = '<li><div class="order"><span><strong>%s</strong> tshirt of size <strong>%s</strong> with message \'<em>%s</em>\'</span> <input type="button" id="delete-%d" class="small-button" value="Remove" /></div></li>';
var refreshTimout = 1000;

$(document).ready(function(){
    loadOrders();
    refreshOrders();
    $("#createOrderForm").submit(function(e){
        $.ajax({
            url: "http://localhost:8080/workshop/api/orders",
            headers: {
                'Content-Type': 'application/json'
            },
            type: "POST",
            dataType: "json",
            data: JSON.stringify(createOrder()),
            success: function(){loadOrders()}
        });
        e.preventDefault();
    });
})

function refreshOrders(){
    setTimeout(function(){
		loadOrders();
    }, refreshTimout);
}

function loadOrders(){
	$.ajax({
		url: "http://localhost:8080/workshop/api/orders",
		type: "GET",
		dataType: "json",
		success: function(result){
            populateOrders(result);
			refreshOrders();
		}
	});
}

function populateOrders(orders){
    $("#recentOrders ul").children().remove();
    $.each(orders, function(index, value){
        $(sprintf(orderFormat, value["color"], value["size"], value["message"], value["id"])).appendTo("#recentOrders ul");
    });
    $.each(orders, function(index, value){
       $(sprintf("#delete-%d", value["id"])).click(function(event){
           deleteOrder(value["id"]);
       });
    });
}

function deleteOrder(id){
    $.ajax({
        url: sprintf("http://localhost:8080/workshop/api/orders/%d", id),
        type: "DELETE",
        dataType: "json",
        success: function(){loadOrders()}
    });
}

function createOrder(){
    var serialized=  $("#createOrderForm").serializeArray();
    var order = {};
    $.each(serialized, function(index, item){
        order[item.name] = item.value;
    });
    return order;
}