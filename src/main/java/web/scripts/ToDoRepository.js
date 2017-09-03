"use strict";

function ToDoRepository(){

    this.getItems = function(){
        $.ajax({
            type: 'GET',
            url: '/api/todo',
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.insertItem = function(value) {
        $.ajax({
            type: 'POST',
            url: '/api/todo',
            contentType: 'application/json',
            data: JSON.stringify(value),
            success: function (response) {
                console.log('data send!');
                toDoRepository.getItems();
            }
        });
    };

    this.deleteItem = function(id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo',
            contentType: 'application/json',
            data: JSON.stringify({id: id}),
            success: function (response) {
                //toDoRepository.getItems();
            }
        });
    };

    this.checkItem = function(id, check){
        $.ajax({
            type: 'PUT',
            url: '/api/todo',
            contentType: 'application/json',
            data: JSON.stringify({id: id, check: check}),
            success: function () {
                console.log("check query success");
            }
        });
    }

}

