class CustomerUI {

  formToCustomer() {
    const dob = GeneralUtil.getDateFromString(document.getElementById('dob').value);
    const customer = {
      firstName: document.getElementById('first_name').value,
      lastName: document.getElementById('last_name').value,
      email: document.getElementById('email').value,
      dob: dob
    };
    return customer;
  }

  customerToForm(customer) {
    document.getElementById('first_name').value = customer.firstName;
    document.getElementById('last_name').value = customer.lastName;
    document.getElementById('email').value = customer.email;
    document.getElementById('phone').value = '12341234';
    document.getElementById('dob').value = GeneralUtil.formatDate(new Date(customer.dob));
  }

  displayCustomerList(customers, tableSize) {
    const tableBodyEl = document.getElementById('customer-table-body');
    for (let i = 0; i < tableSize; i++) {
      const row = document.createElement('tr');
      row.appendChild( this.createNameCol(customers[i]) );
      row.appendChild( this.createAgeCol(customers[i]) );
      row.appendChild( this.createJoinDateCol(customers[i]) );
      row.appendChild( this.createActionBtnCol(customers[i]) );
      tableBodyEl.appendChild(row);
    }
  }

  clearCustomerList() {
    const tableBodyEl = document.getElementById('customer-table-body');
    tableBodyEl.innerHTML = '';
  }

  createNameCol(customer) {
    const tdName = document.createElement('td');
    tdName.innerHTML = `${customer.firstName} ${customer.lastName}`;
    return tdName;
  }

  createAgeCol(customer) {
    const tdAge = document.createElement('td');
    const birthDate = new Date(customer.dob);
    tdAge.innerHTML = `${GeneralUtil.computeAge(birthDate)}`;
    return tdAge;
  }
  
  createJoinDateCol(customer) {
    const tdJoinDate = document.createElement('td');
    const dateObj = new Date(customer.registerTime);
    tdJoinDate.innerHTML = `${GeneralUtil.formatDate(dateObj)}`;
    return tdJoinDate;
  }

  createActionBtnCol(customer) {
    
    const tdActions = document.createElement('td');
    
    const btnEdit = this.createActionBtn('edit',customer.customerId);
    tdActions.appendChild(btnEdit);

    const btnDelete = this.createActionBtn('delete',customer.customerId);
    tdActions.appendChild(btnDelete);

    const btnMail = this.createActionBtn('mail',customer.customerId);
    tdActions.appendChild(btnMail);

    return tdActions;

  }

  createActionBtn(action,custId) {
    const btn = document.createElement('a');
    btn.href = '#';
    btn.id = `${action}-${custId}`;
    btn.className = `customer-${action}`;
    btn.innerHTML = `<i class="material-icons indigo-text">${action}</i>`;
    return btn;
  }

}