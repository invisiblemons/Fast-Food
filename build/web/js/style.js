'use strict';
var numberOfItems = $("#loopp .list-groupp").length;
var limitPerPage = 20;
$("#loopp .list-groupp:gt(" + (limitPerPage - 1) + ")").hide();
var totalPages = Math.ceil(numberOfItems / limitPerPage);
$(".pagination").append("<li class='page-item disabled' id='previous-page'><a class='page-link' href='javascript:void(0)' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");

$(".pagination").append("<li class='current-page active'><a class='page-link' href='javascript:void(0)'>" + 1 + "</a></li>");

for (var i = 2; i <= totalPages; i++) {
    $(".pagination").append("<li class='current-page '><a class='page-link' href='javascript:void(0)'>" + i + "</a></li>");
}

$(".pagination").append("<li class='page-item' id='next-page'><a class='page-link' href='javascript:void(0)' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");

$(".pagination li.current-page").on("click", function () {
    if($(this).index===1)
        $("#previous-page").addClass("disabled");
    else 
        $("#previous-page").removeClass("disabled");
        
    if ($(this).hasClass("active")) {
        return false;
    } else {
        var currentPage = $(this).index();
        //active
        $(".pagination li").removeClass("active");
        $(this).addClass("active");
        //paging
        $("#loopp .list-groupp").hide();
        var grandTotal = limitPerPage * currentPage;
        for(var i = grandTotal - limitPerPage; i<grandTotal; i++) {
            $("#loopp .list-groupp:eq(" + i + ")").show();
        }
    }


});

$("#next-page").on("click",function(){
    var currentPage = $(".pagination li.active").index();
    if(currentPage+1===1)
        $("#previous-page").addClass("disabled");
        
    else 
        $("#previous-page").removeClass("disabled");
    if(currentPage === totalPages) {
        return false;
    } else {
        currentPage++;
        $(".pagination li").removeClass("active");
        $("#loopp .list-groupp").hide();
        var grandTotal = limitPerPage * currentPage;
        for(var i = grandTotal - limitPerPage; i<grandTotal; i++) {
            $("#loopp .list-groupp:eq(" + i + ")").show();
        }
        $(".pagination li.current-page:eq(" + (currentPage-1) + ")").addClass("active");//index in array start from 0
    }
});

$("#previous-page").on("click",function(){
    var currentPage = $(".pagination li.active").index();
    if(currentPage-1===1)
        $("#previous-page").addClass("disabled");
        
    else 
        $("#previous-page").removeClass("disabled");
    if(currentPage === 1) {
        return false;
    } else {
        currentPage--;
        $(".pagination li").removeClass("active");
        $("#loopp .list-groupp").hide();
        var grandTotal = limitPerPage * currentPage;
        for(var i = grandTotal - limitPerPage; i<grandTotal; i++) {
            $("#loopp .list-groupp:eq(" + i + ")").show();
        }
        $(".pagination li.current-page:eq(" + (currentPage-1) + ")").addClass("active");//index in array start from 0
    }
});
