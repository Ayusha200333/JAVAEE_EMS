//
// $(document).ready(function () {
//     const email = localStorage.getItem('email');
//     if (!email) {
//         window.location.href = 'signin.html';
//     } else {
//         $('#welcome-message').text('Welcome, ' + email);
//         fetchEmployees();
//     }
// });
//
// $('#save-employee').on('click', function () {
//     const formData = new FormData();
//     formData.append('ename', $('#ename').val());
//     formData.append('eaddress', $('#eaddress').val());
//     formData.append('eemail', $('#eemail').val());
//
//
//     const fileInput = $('#eimage')[0];
//     if (fileInput.files.length > 0) {
//         formData.append('eimage', fileInput.files[0]);
//     } else {
//         alert('Please select an image to upload.');
//         return;
//     }
//
//     $.ajax({
//         method: 'POST',
//         url: 'http://localhost:8080/EMS_Web_exploded/api/v1/employee',
//         processData: false,
//         contentType: false,
//         data: formData,
//         success: function (response) {
//             if (response.code === '200') {
//                 alert('Employee saved successfully!');
//                 window.location.reload();
//             } else {
//                 alert('Error: ' + response.message);
//             }
//         },
//         error: function () {
//             alert('Failed to save employee.');
//         }
//     });
// });
//
// // Fetch and render employee data
// function fetchEmployees() {
//     $.ajax({
//         method: 'GET',
//         url: 'http://localhost:8080/EMS_Web_exploded//api/v1/employee',
//         success: function (response) {
//             if (response.code === '200') {
//                 const employees = response.data;
//                 const employeeTable = $('#employee-table tbody');
//                 employeeTable.empty();
//
//                 employees.forEach(function (employee) {
//                     employeeTable.append(`
//                         <tr>
//
//                             <td>${employee.ename}</td>
//                             <td>${employee.eaddress}</td>
//                             <td>${employee.eemail}</td>
//
//                             <td>
//                                 <img src="/assets/${employee.eimage}" alt="Employee Image" width="60" height="60" />
//                             </td>
//                         </tr>
//                     `);
//                 });
//             } else {
//                 alert('Error fetching employees: ' + response.message);
//             }
//         },
//         error: function () {
//             alert('Failed to fetch employees.');
//         }
//     });
// }



$(document).ready(function () {
    const email = localStorage.getItem('email');
    if (!email) {
        window.location.href = 'signin.html';
    } else {
        $('#welcome-message').text('Welcome, ' + email);
        fetchEmployees();
    }

    let selectedEid = null;

    // Save (Add) employee
    $('#save-employee').on('click', function () {
        const formData = new FormData();
        formData.append('ename', $('#ename').val());
        formData.append('eaddress', $('#eaddress').val());
        formData.append('eemail', $('#eemail').val());

        const fileInput = $('#eimage')[0];
        if (fileInput.files.length > 0) {
            formData.append('eimage', fileInput.files[0]);
        } else {
            alert('Please select an image to upload.');
            return;
        }

        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/EMS_Web_exploded//api/v1/employee',
            processData: false,
            contentType: false,
            data: formData,
            success: function (response) {
                if (response.code === '200') {
                    alert('Employee saved successfully!');
                    resetForm();
                    fetchEmployees();
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function () {
                alert('Failed to save employee.');
            }
        });
    });

    // Update employee
    $('#update-employee').on('click', function () {
        if (!selectedEid) {
            alert("Please select an employee to update.");
            return;
        }

        const formData = new FormData();
        formData.append('eid', selectedEid);
        formData.append('ename', $('#ename').val());
        formData.append('eaddress', $('#eaddress').val());
        formData.append('eemail', $('#eemail').val());

        const fileInput = $('#eimage')[0];
        if (fileInput.files.length > 0) {
            formData.append('eimage', fileInput.files[0]);
        }

        $.ajax({
            method: 'PUT',
            url: 'http://localhost:8080/EMS_Web_exploded//api/v1/employee',
            processData: false,
            contentType: false,
            data: formData,
            success: function (response) {
                if (response.code === '200') {
                    alert('Employee updated successfully!');
                    resetForm();
                    fetchEmployees();
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function () {
                alert('Failed to update employee.');
            }
        });
    });

    // Delete employee
    $('#delete-employee').on('click', function () {
        if (!selectedEid) {
            alert("Please select an employee to delete.");
            return;
        }

        $.ajax({
            method: 'DELETE',
            url: 'http://localhost:8080/EMS_Web_exploded//api/v1/employee?eid=' + selectedEid,
            success: function (response) {
                if (response.code === '200') {
                    alert('Employee deleted successfully!');
                    resetForm();
                    fetchEmployees();
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function () {
                alert('Failed to delete employee.');
            }
        });
    });

    // Fetch and render employees
    function fetchEmployees() {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/EMS_Web_exploded//api/v1/employee',
            success: function (response) {
                if (response.code === '200') {
                    const employees = response.data;
                    const employeeTable = $('#employee-table tbody');
                    employeeTable.empty();

                    employees.forEach(function (employee) {
                        employeeTable.append(`
                            <tr data-eid="${employee.eid}">
                                <td>${employee.ename}</td>
                                <td>${employee.eaddress}</td>
                                <td>${employee.eemail}</td>
                                <td>
                                    <img src="/assets/${employee.eimage}" alt="Image" width="60" height="60" />
                                </td>
                            </tr>
                        `);
                    });

                    // Click to select row and fill form
                    $('#employee-table tbody tr').on('click', function () {
                        const row = $(this);
                        selectedEid = row.data('eid');

                        const ename = row.find('td:eq(0)').text();
                        const eaddress = row.find('td:eq(1)').text();
                        const eemail = row.find('td:eq(2)').text();

                        $('#ename').val(ename);
                        $('#eaddress').val(eaddress);
                        $('#eemail').val(eemail);
                        $('#eimage').val(''); // Clear file input
                    });

                } else {
                    alert('Error fetching employees: ' + response.message);
                }
            },
            error: function () {
                alert('Failed to fetch employees.');
            }
        });
    }

    function resetForm() {
        $('#ename').val('');
        $('#eaddress').val('');
        $('#eemail').val('');
        $('#eimage').val('');
        selectedEid = null;
    }
});
