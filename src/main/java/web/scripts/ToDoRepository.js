"use strict";

function ToDoRepository(){

    this.getItems = function(){
        $.ajax({
            type: 'GET',
            url: '/api/todo/Items',
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.insertItem = function(value) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/Item',
            contentType: 'text/plain',
            data: value,
            success: function () {
                console.log('data send!');
                toDoRepository.getItems();
            }
        });
    };

    this.deleteItem = function(id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo/Item/' + id
        });
    };

    this.checkItem = function(id, check){
        $.ajax({
            type: 'PUT',
            url: '/api/todo/Item',
            contentType: 'application/json',
            data: JSON.stringify({id: id, check: check}),
            success: function () {
                console.log("check query success");
            }
        });
    }

}

