$(document).ready(function () {
    setActionToButtons();
});


function setActionToButtons() {
    var buttons = document.getElementsByTagName('button');

    for (var i = 0; i < buttons.length; i++) {
        var button = buttons[i];
        button.onclick = function () {
            modal.style.display = "block";
        }
    }

// Get the modal
    var modal = document.getElementById('myModal');

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal


// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}