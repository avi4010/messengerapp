api_endpoint = 'http://localhost:8079/webapi/messages';

$(document).ready(function() {
	
	$.ajax({
	    url: api_endpoint,
	    crossDomain: true,
	    headers: {
	        'Content-Type': 'application/x-www-form-urlencoded'
	    },
	    type: "GET",
	    dataType: "json",
	    data: {
	    },
	    success: function (result) {
	        console.log(result);
	        load_messages(result, '#messages');
	    },
	    error: function () {
	        console.log("error");
	    }
	});
});

//add message
function addMessage(){
	var msg = $('#MessageTitle').val().trim();
	var auth = $('#author').val().trim();
	
	$.ajax({
		url: api_endpoint,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify({message: msg, author: auth}),
		success: function(result){
			alert("Message with message ID "+result['id']+" is added");
			location.reload();
		}
	})
}

//update message
function updateMessage(id){

	var msg = $('#editMessageTitle').val().trim();
	var auth = $('#editAuthor').val().trim();
	
	$.ajax({
		url: api_endpoint+"/"+editId,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify({message: msg, author: auth}),
		success: function(result){
			alert("Message with message ID "+result['id']+" is edited");
			location.reload();
		}
	})
}

var editID;

function updateMessageView(msgID){
	editId = msgID.toString();
}

//delete Message
function deleteBook(msgID){
	
	var conformation = confirm('Are you sure to delete message ID '+msgID)
	
	if(conformation == true){
		$.ajax({
			url: api_endpoint+"/"+msgID.toString(),
			type: 'DELETE',
			contentType: 'application/json',
			success: function(result){
				alert("Message with message ID "+result['id']+" is deleted");
				location.reload();
			}
		})
	}
	
}

function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}


function load_messages(messages, appendTo) {
    var $parentTable = $(appendTo);
    $parentTable.empty();
    var articleTableTemplate = "<tr><td class='w-10'>{id}</td><td class>{message}</td><td>{created}</td><td>{author}</td>" +
    "<td class='float-right'>" +
    "<a class='nav-link' href='#' data-toggle='modal' data-target='#editModal' onclick='updateMessageView({id})'><i class='far fa-edit'></i> Edit</a>" +
    "<a class='nav-link text-danger book-delete'href='#' onclick='deleteBook({id})'><i class='far fa-trash-alt'></i> Delete</a></td>";

    for(var message_idx=1; message_idx<= messages.length; message_idx++) {
        var cur_tag = articleTableTemplate;
        cur_tag = replaceAll(cur_tag, '{id}', messages[message_idx -1]['id']);
        cur_tag = replaceAll(cur_tag, '{message}', messages[message_idx -1]['message']);
        cur_tag = replaceAll(cur_tag, '{author}', messages[message_idx -1]['author']);
        cur_tag = replaceAll(cur_tag, '{created}', messages[message_idx -1]['created']);
        $parentTable.append(cur_tag);
    }
    return 0;
}
