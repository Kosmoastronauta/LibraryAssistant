// console.log(123);
// const Http = new XMLHttpRequest();
// const url='localhost:8080/books/';
//
//
// Http.onreadystatechange = (e) => {
//     console.log(321)
//     console.log(Http.responseText)
// }

$(document).ready(function() {
    // $.get("http://localhost:8080/books/", function(data, status) {
    //     $('.books').append(data);
    // });

    $.ajax({
        url: "http://localhost:8080/books/"
    }).then(function(data) {
        $('.books').append(data);

        for(i = 0; i < data.length; i++)
        {
            document.write("" + data[i].id);
        }
        console.log(data);
    });

});