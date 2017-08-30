"use strict";

function getItems(){
    $.ajax({
        type: 'GET',
        url: '/Servlet',
        success: function(response){
            printFullArray(response);
        }
    });
}

function insertItem(value) {
    $.ajax({
        type: 'POST',
        url: '/Servlet',
        contentType: 'application/json',
        data: JSON.stringify(value),
        success: function (response) {
            console.log('data send!');
            getItems();
        }
    });
}

function deleteItem(id) {
    $.ajax({
        type: 'DELETE',
        url: '/Servlet',
        contentType: 'application/json',
        data: JSON.stringify({id: id}),
        success: function (response) {
            getItems();
        }
    });
}

function printFullArray(array) {
    clearOutPrint();
    $.each(array, function(index, toDoObject){
        list.addItem(toDoObject.id, toDoObject.value);
    });
}

function clearOutPrint(){
    $('#to-do-list li').remove();
}