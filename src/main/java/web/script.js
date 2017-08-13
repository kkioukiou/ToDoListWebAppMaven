$(document).ready(function (){
    var toDoList = $("#to-do-list");
    var inputField = $("#input-field");
    var iter = 0;

    var toDoObject = function(id, value){
        this.id = id;
        this.value = value;
    };
//    var toDoArray = [];
//    var addButton = $("#input-field-add-button");
//    var subInputFieldAddButton = $('#sub-input-field-add-button');
//    var subInputField = $('#sub-input-field');
    getStartContent();

    function getStartContent(){
        $.ajax({
            type: 'GET',
            url: '/WorkServlet',
            contentType: 'application/json',
            data: 'getArray',
            success: function(response){
                iter = response[response.length - 1].id + 1;
                printFullArray(response);
            }
        });
    }

    function sendItemToServer(toDoObject) {
        $.ajax({
            type: 'POST',
            url: '/WorkServlet',
            contentType: 'application/json',
            data: JSON.stringify(toDoObject),
            success: function (response) {
                console.log('data send!');
                getStartContent();
            }
        });
    }

    function deleteItemFromDataBase(id) {
        $.ajax({
            type: 'DELETE',
            url: '/WorkServlet',
            contentType: 'application/json',
            data: JSON.stringify({id: id}),
            success: function (response) {
                getStartContent();
            }
        });
    }

    inputField
        .change(function(){
            sendItemToServer(new toDoObject(iter++, inputField.val()));
            inputField.val('');
        });

    function printFullArray(array) {
        clearOutPrint();
        $.each(array, function(index, toDoObject){
            toDoList.append('<div id="' + toDoObject.id + '" class="listElement">' + toDoObject.value
            + '<button class="elementButton" id="removeElement">X</button>'
            + '<button class="elementButton" id="addSubElement">add</button>'
            + '</div>');
        });

    }

    function printSubInputField(event){

        var subInputElement = $('#subInputElement');
        var $target = $(event.target);

        if($('div').is(subInputElement)){
            $('#subInputElement').remove();
            $target.parent().append('<div id="subInputElement"><input type="text" id="sub-input-field">' +
            '<button id="sub-input-field-add-button">Add</button></div>');
        } else {
            $target.parent().append('<div id="subInputElement"><input type="text" id="sub-input-field">' +
            '<button id="sub-input-field-add-button">Add</button></div>');
        }

    };

    toDoList.click(function checkElement(event){
        var $target = $(event.target);

        switch ($target.attr('id')) {

            case 'listElement':
                $target.toggleClass('checkedElement');
                break;

            case 'removeElement':
                var removeElement = $target.parents().attr('id');
                console.log(removeElement);
                deleteItemFromDataBase(removeElement);
                break;

            case 'sub-input-field-add-button':
                addSubElement();
                break;

            case 'addSubElement':
                printSubInputField(event);
                break;

        }
    });

    function clearOutPrint(){
        $('#to-do-list').empty();
    }
});