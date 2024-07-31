ddocument.addEventListener('DOMContentLoaded', () => {
  const userForm = document.getElementById('userForm');
  const userList = document.getElementById('userList');
  const viewAllUsersBtn = document.getElementById('viewAllUsersBtn');
  const searchInput = document.getElementById('searchInput');

  let users = [];

  function fetchUsers() {
    fetch('/app_users')
      .then(response => response.json())
      .then(data => {
        users = data;
        displayUsers(users);
      });
  }

  function displayUsers(users) {
    userList.innerHTML = '';
    users.forEach(user => {
      addUserToList(user);
    });
  }

  function addUserToList(user) {
    const li = document.createElement('li');
    li.textContent = `${user.firstName} ${user.lastName} (${user.email}) - ${user.phoneNumber}`;
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.addEventListener('click', () => {
      fetch(`/app_users/${user.id}`, {
        method: 'DELETE'
      })
        .then(response => {
          if (response.ok) {
            userList.removeChild(li);
          }
        });
    });
    li.appendChild(deleteButton);
    userList.appendChild(li);
  }

  userForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const newUser = {
      firstName: document.getElementById('firstName').value,
      lastName: document.getElementById('lastName').value,
      email: document.getElementById('email').value,
      phoneNumber: document.getElementById('phoneNumber').value
    };

    fetch('/app_users', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newUser)
    })
      .then(response => response.json())
      .then(user => {
        users.push(user);
        addUserToList(user);
        userForm.reset();
      });
  });

  viewAllUsersBtn.addEventListener('click', fetchUsers);

  searchInput.addEventListener('input', () => {
    const query = searchInput.value.toLowerCase();
    const filteredUsers = users.filter(user =>
      user.firstName.toLowerCase().includes(query) ||
      user.lastName.toLowerCase().includes(query) ||
      user.email.toLowerCase().includes(query) ||
      user.phoneNumber.includes(query)
    );
    displayUsers(filteredUsers);
  });

  fetchUsers();
});
