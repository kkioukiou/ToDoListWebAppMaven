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

function Item(id, text){
    var item = this;

    this.element = prepareElement();

    function prepareDelBtn() {
        var btn = $("<BUTTON>");
        btn.text("X");
        btn.addClass("btn btn-danger")
        btn.click(function(){
            deleteItem(item.element.attr("id"));
            item.element.remove();
        });
        return btn;
    }

    function createSubList() {
        var btn = $("<BUTTON>");
        btn.text("+");
        btn.addClass("btn btn-success")
        btn.click(function(){
            item.element.append(new ToDoList().element);
        });
        return btn;
    }

    function prepareElement() {
        var elem = $("<LI>");
        var div = $("<DIV>");

//        elem.addClass("list-group-item");

        elem.append(prepareCheckbox());
        elem.append(text);
        elem.attr("id", id);
        div.append(prepareDelBtn());
        div.append(createSubList());
        elem.append(div);
        div.addClass("btn-group");
        return elem;
    }

    function prepareCheckbox(){
        var cb = $("<INPUT>", {type: "checkbox"});
        cb.change(function(e){
            console.log(this.checked);
            if(this.checked){
                item.element.addClass("done");
            } else {
                item.element.removeClass("done");
            }
        });
        return cb;
    }
}

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

var list = new ToDoList();
getItems();

$("#to-do-list").append(list.element);