var imgCxtPath = '/dareu/rest/client/profile/image?userId=';
var defaultAccountImage = "/dareu/resources/img/account.png"; 
$(function () {
    ko.applyBindings(new CreateDareViewModel());
});

function CreateDareViewModel() {
    var self = this;
    /**self.errorLoading = function(item){
        $("#" + item.id + ".image-profile")
                .attr("src", defaultAccountImage); 
    };
    self.load = function(item){
        $("#" + item.id + ".image-profile")
                .attr("src", imgCxtPath + item.id);
    }; **/
    self.friendDescriptions = ko.observableArray([]);
    self.loading = ko.observable(false);
    self.selectedUser = ko.observable(false);
    self.selectedUserName = ko.observable();
    self.selectedUserId = ko.observable();
    self.removeSelectedUser = function () {
        //remove all selected user variables 
        self.selectedUser(false);
        self.selectedUserName('');
        self.selectedUserId('');
    };

    self.selectUser = function (user) {
        self.selectedUser(true);
        //select user 
        self.selectedUserName(user.name);
        self.selectedUserId(user.id);
        //hides modal 
        $('#findFriends').modal('hide');
    };
    self.searchText = ko.observable('');
    self.searchText.subscribe(function () {
        if (self.searchText() === '')
            self.friendDescriptions.removeAll();
        getFriendsList(self);
    });
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
            //remove all rows in search table 
            self.friendDescriptions.removeAll();
            var items = response.items;
            $.each(items, function (key, value) {
                if(value.profileImageAvailable)
                    value.imageUrl = imgCxtPath + value.id;
                else value.imageUrl = defaultAccountImage; 
                self.friendDescriptions.push(value);
            });
            self.loading(false);
        },
        error: function (response) {
            console.log(response);
        }
    });
}