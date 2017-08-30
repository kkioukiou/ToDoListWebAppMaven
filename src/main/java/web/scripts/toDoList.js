"use strict";

function ToDoList(){
    var list = this;
    this.element = $("<UL>");

    function prepareAdd() {
        var input = $("<INPUT>");
        input.keydown(function(e){
            if(e.keyCode === 13 && this.value){
                insertItem(this.value);
                //list.addItem(this.value);
                input.val("");
            }
        });
        return input;
    }

    this.element.append(prepareAdd());
//    this.element.addClass("list-group");

    this.addItem = function (id, text){
        var item = new Item(id, text);
        list.element.append(item.element);
    }
}