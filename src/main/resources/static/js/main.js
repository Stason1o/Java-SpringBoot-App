$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var search = {};
    search["username"] = $("#username").val();
    // search["email"] = $("#email").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            // alert(data.retrievedUsers.length);

            var json1 = "<h4>Ajax Response</h4><div class='container container-table'>" +
                "<div class='row vertical-center-row'>"; //+ JSON.stringify(data, null, 4) + "</div></div>" ;
            for (var i = 0; i < data.retrievedUsers.length; i++) { // retrievedUsers from json. It works fine.
                json1 += "<div class='text-center col-md-8 col-md-offset-2'>" +
                    data.retrievedUsers[i].username + "&Tab;" +
                    data.retrievedUsers[i].email + "&Tab;" +
                    data.retrievedUsers[i].firstName + "&Tab;" +
                    data.retrievedUsers[i].lastName + "&Tab;" + "</div>"
            }
            json1 += "</div></div>";

            $('#feedback').html(json1);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response ERROR</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}