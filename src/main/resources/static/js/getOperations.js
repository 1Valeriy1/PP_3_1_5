async function getUser() {
    let temp = '';
    const table = document.querySelector('#tableUser tbody');
    await userFetch.findUserByUsername()
        .then(res => res.json())
        .then(user => {
            temp = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.roles.map(e => " " + e.role.substr(5))}</td>
                </tr>
            `;
            table.innerHTML = temp;

            $(function (){
                let role = ""
            for (let i = 0; i < user.roles.length; i++) {
                role = user.roles[i].role
                if (role === "ROLE_ADMIN") {
                    isUser = false;
                }
            }
            if (isUser) {
            $("#userTable").addClass("show active");
            $("#userTab").addClass("show active");
            } else {
            $("#adminTable").addClass("show active");
            $("#adminTab").addClass("show active");
            }
            })
        })
}

async function tittle() {
    temp = `
        <h1 className="h1 a1" id="h1a1">Admin panel</h1>
        `;
    h1a1.innerHTML = temp;
}

async function getUsers() {
    let temp = '';
    const table = document.querySelector('#tableAllUsers tbody');
    await userFetch.findAllUsers()
        .then(res => res.json())
        .then(users => {
            users.forEach(user => {
                temp += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.roles.map(e => " " + e.role.substr(5))}</td>
                    <td>
                        <button type="button" data-id="${user.id}" data-action="edit" class="btn btn-info"
                            className data-toggle="modal" data-target="#editModal">Edit</button>
                    </td>
                    <td>
                        <button type="button" data-id="${user.id}" data-action="delete" class="btn btn-danger"
                            className data-toggle="modal" data-target="#deleteModal">Delete</button>
                    </td>
                </tr>
               `;
            })
            table.innerHTML = temp;

        })

    $("#tableAllUsers").find('button').on('click', (event) => {
        let defaultModal = $('#defaultModal');

        let targetButton = $(event.target);
        let buttonId = targetButton.attr('data-id');
        let buttonAction = targetButton.attr('data-action');

        defaultModal.attr('data-id', buttonId);
        defaultModal.attr('data-action', buttonAction);
        defaultModal.modal('show');
    })
}

async function getNewUserForm() {
    let button = $(`#addUser`);
    let form = $(`#addForm`)
    button.on('click', () => {
        form.show()
    })
}

async function getDefaultModal() {
    $('#defaultModal').modal({
        keyboard: true,
        backdrop: "static",
        show: false
    }).on("show.bs.modal", (event) => {
        let thisModal = $(event.target);
        let id = thisModal.attr('data-id');
        let action = thisModal.attr('data-action');
        switch (action) {
            case 'edit':
                editUser(thisModal, id);
                break;
            case 'delete':
                deleteUser(thisModal, id);
                break;
        }
    }).on("hidden.bs.modal", (e) => {
        let thisModal = $(e.target);
        thisModal.find('.modal-title').html('');
        thisModal.find('.modal-body').html('');
        thisModal.find('.modal-footer').html('');
    })
}