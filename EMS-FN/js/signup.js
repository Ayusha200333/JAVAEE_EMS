// $('#sign-up-btn').on('click', function() {
//     var name = $('#name').val();
//     var email = $('#email').val();
//     var password = $('#password').val();
//
//     $.ajax({
//         method: 'POST',
//         url: 'http://localhost:8080/EMS_Web_exploded/api/v1/signup',
//         contentType: 'application/json',
//         data: JSON.stringify({
//             uname: name,
//             uemail: email,
//             upassword: password
//         }),
//         success: function(response) {
//             console.log(response);
//
//             if (response.code === '200') {
//                 alert('Sign up successful!');
//                 console.log("Redirecting to signin...");
//                 window.location.href = 'signin.html';
//             } else {
//                 alert('Error: ' + response.message);
//             }
//         },
//     })
// });


$('#sign-up-btn').on('click', function() {
    var name = $('#name').val();
    var email = $('#email').val();
    var password = $('#password').val();

    $.ajax({
        method: 'POST',
        url: 'http://localhost:8080/EMS_Web_exploded/api/v1/signup',
        contentType: 'application/json',
        data: JSON.stringify({
            uname: name,
            uemail: email,
            upassword: password
        }),
        success: function(response) {
            console.log(response);

            if (response.code === '200') {
                alert('Sign up successful!');
                window.location.href = 'signin.html';
            } else {
                alert('Error: ' + response.message);
            }
        },
    })
});