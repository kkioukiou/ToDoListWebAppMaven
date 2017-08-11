$(document).ready(function (){
    var toDoList = $("#to-do-list");
    var inputField = $("#input-field");
    var iter = 0;
    var toDoObject;
//    var toDoArray = [];
//    var addButton = $("#input-field-add-button");
//    var subInputFieldAddButton = $('#sub-input-field-add-button');
//    var subInputField = $('#sub-input-field');
    getStartContent();

    function getStartContent(){
        $.ajax({
            type: 'POST',
            url: '/startDataSend',
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
            url: '/HelloWorld',
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
            type: 'POST',
            url: '/deleteItem',
            contentType: 'application/json',
            data: JSON.stringify({id: id}),
            success: function (response) {
                console.log('delete item!'); //TODO remove
                getStartContent();
            }
        });
    }

    inputField
        .change(function(){
            toDoObject = {'id':iter++, 'value':inputField.val()};
            inputField.val('');
            sendItemToServer(toDoObject);
            console.log('change work!'); //TODO remove
        });

    function printFullArray(array) {
        clearOutPrint();
        $.each(array, function(index, object){
            toDoList.append('<div id="' + object.id + '" class="listElement">' + object.value
            + '<button class="elementButton" id="removeElement">X</button>'
            + '<button class="elementButton" id="addSubElement">add</button>'
            + '</div>');
        });

    }

    function printSubInputField(event){

        var subInputElement = $('#subInputElement');
        $target = $(event.target);

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

        if($target.attr('id') == 'listElement'){
            $target.toggleClass('checkedElement');
        }

        if($target.attr('id') == 'removeElement'){
            var removeElement = $target.parents().attr('id');
            console.log(removeElement);
            deleteItemFromDataBase(removeElement);
            getStartContent();
        }

        if($target.attr('id') == 'sub-input-field-add-button'){
            console.log('click');
            addSubElement();
        }

        if($target.attr('id') == 'addSubElement'){
            printSubInputField(event);
        }
    });


    function clearOutPrint(){
        $('#to-do-list').empty();
    }

});