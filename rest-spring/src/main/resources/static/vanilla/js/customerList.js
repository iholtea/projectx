const customerUI = new CustomerUI();
const customerXhr = new CustomerXhr();

displayCustomers();

function displayCustomers() {
  customerXhr.getCustomers(getCustomersCallback);
}

function getCustomersCallback(err,data) {
  if(!err) {
    customerUI.displayCustomerList(JSON.parse(data),10);
    bindActions();
  } else if( err === 'ERR_LOAD') {
    const custContainer = document.getElementById('customer-container');
    if(data === 404) {
      custContainer.innerHTML = `
        <h6>Eror 404</h6>
        Resource not found on endpoint API
      `;
    } else {
      custContainer.innerHTML = `
        <h6>Eror within the response from customer API</h6>
      `;
    }
  } else if( err === 'ERR_OTHER') {
    const custContainer = document.getElementById('customer-container');
    custContainer.innerHTML = `
      <h6>General error.</h6> 
      Check if customer API is reachable</h6>
    `;
  }
}

function bindActions() {
  document.querySelectorAll('.customer-delete').forEach( elem => {
    elem.addEventListener('click', deleteCustomer);
  });
  document.querySelectorAll('.customer-edit').forEach( elem => {
    elem.addEventListener('click', editCustomer);
  });
}

function editCustomer(event) {
  const customerId = UIUtil.getAIdFromElem(event);
  document.location.href = `customerEdit.html?customerId=${customerId}`;
}

function deleteCustomer(event) {
  const customerId = UIUtil.getAIdFromElem(event);
  customerXhr.deleteCustomer(customerId,deleteCustomerCallback);
}

function deleteCustomerCallback(err, data, customerId) {
  if( !err ) {
    console.log(`deleted customer id=${customerId}`);   
    customerUI.clearCustomerList();
    displayCustomers();
  }
  else {
    console.log(err, data);
  }
}



