"use strict";

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

        //elem.addClass("list-group-item");
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