$(function () {
    ko.applyBindings(new CreateDareViewModel());
});

function CreateDareViewModel() {
    var self = this;
    self.friendDescriptions = ko.observableArray([]);
    self.loading = ko.observable(false);
    self.selectUser = function(user){
        console.log(user); 
    }; 
    self.searchText = ko.observable(''); 
    self.searchText.subscribe(function(){
        getFriendsList(self); 
    }); 
    self.friendDescriptions = ko.observableArray([]); 
}

function getFriendsList(self) {
    self.loading(true); 
    var pageNumber = 1;
    var cxtPath = '/dareu/rest/client/friends/find?query=';
    
    if (self.searchText() === '')
        return;
    //creates a request
    var path = cxtPath + self.searchText();
    //appends pageNumber
    path += '&pageNumber=' + pageNumber;
    //get csrf token 
    var csrfToken = $('#csrfToken').val();
    //creates ajax 
    $.ajax({
        headers: {
            '_csrf': csrfToken,
            'accept': 'application/json'
        },
        method: 'GET',
        url: path,
        success: function (response) {
            var items = response.items; 
            for(var i = 0; i < items.length; i ++)
                self.friendDescriptions.push(items[i]); 
            //processResponse(response);
            self.loading(false); 
        },
        error: function (response) {
            console.log(response);
        }
    });
}
function processResponse(response) {
    //clear table 
    $("#friendsTable tr").remove();

    $("#friendsTable").append("<thead><td class='table-data'></td><td class='table-data'>Name</td><td class='table-data'></td></thead>")

    var row = null;
    var imageTd = null;
    var nameTd = null;
    var selectTd = null;
    var image = null;
    var form = null;
    var button = null;
    var csrfToken = $('#csrfToken').val();
    if (response.items != null && response.items.length > 0) {
        $.each(response.items, function (index, value) {
            //create a new row 
            row = $('<tr></tr>');
            imageTd = $('<td></td>').addClass('table-data');
            nameTd = $('<td></td>').addClass('table-data');
            selectTd = $('<td></td>').addClass('table-data');

            image = $('<img>').attr('src', value.imageUrl);
            $(imageTd).append(image);
            $(nameTd).append(value.name);
            $(selectTd).append("<form action='' class='form-inline'>")
                    .append("<button type='submit' class='btn btn-success'>Select</button>")
                    .append("<input type='hidden' name='_csrf' value='" + csrfToken + "'>")
                    .append("</form>");

            row.append(imageTd);
            row.append(nameTd);
            row.append(selectTd);
            $('#friendsTable').append(row);
        });
    } else {

    }
}