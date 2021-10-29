const customerId = UIUtil.getIdParamFromUrl();

const customerUI = new CustomerUI();
const customerXhr = new CustomerXhr();
const addressUI = new AddressUI();
const addressXhr = new AddressXhr(customerId);

let addressAction = '';
let addressId;


document.addEventListener('DOMContentLoaded', UIUtil.configureMaterialDatepicker);
document.addEventListener('DOMContentLoaded', UIUtil.configureMaterialModal);

document.getElementById('customer_form').addEventListener('submit', (event) => {
  event.preventDefault();
});
document.getElementById('addr_form').addEventListener('submit', (event) => {
  event.preventDefault();
});

document.getElementById('customer_submit').addEventListener('click', editCustomer);

document.getElementById('addr_submit').addEventListener('click', () => {
  saveAddress(saveAddressCallback);
});

document.getElementById('create-address').addEventListener('click', () => {
  addressAction = 'AddAddress';
  addressUI.clearAddressForm();
});

displayCustomerData();
//displayAddressList();

function displayCustomerData() {
  customerXhr.getCustomer(customerId, (err,data) => {
    if( !err ) {
      const customerData = JSON.parse(data);
      customerUI.customerToForm(customerData);
      addressUI.displayAddressList(customerData.addresses);
      bindActions();
    } else {
      console.log(err, data);
    }
  });
}


function displayAddressList() {
  addressXhr.getAddressesByCustomer( (err,data) => {
    if( !err ) {
      addressUI.displayAddressList(JSON.parse(data));
      bindActions();
    } else {
      console.log(err, data);
    }
  });
}


//////////////////////////////////////////////////////////
//    Handle edit customer info
//////////////////////////////////////////////////////////

function editCustomer() {
  const customer = customerUI.formToCustomer();
  customer.customerId = customerId;
  customerXhr.editCustomer(customer, customerId, editCustomerCallback);
}

function editCustomerCallback(err,data) {
  if( err!==null ) {
    console.log(err,data);
  }
}

//////////////////////////////////////////////////////
//    Handle delete / edit address buttons addresslist
//////////////////////////////////////////////////////

function bindActions() {
  document.querySelectorAll('.address-delete').forEach( elem => {
    elem.addEventListener('click', deleteAddress);
  });
  document.querySelectorAll('.address-edit').forEach( elem => {
    elem.addEventListener('click', editAddress);
  });
}

function deleteAddress(event) {
  addressId = UIUtil.getAIdFromElem(event);
  addressXhr.deleteAddress(addressId, (err,data,addrId) => {
    if(!err) {
      console.log(`deleted address id=${addrId}`);
      addressUI.clearAddressList();
      displayAddressList();
    } else {
      console.log(err,data);
    }
  });
}

function editAddress(event) {
  addressId = UIUtil.getAIdFromElem(event);
  addressAction = 'EditAddress';
  addressXhr.getAddress(addressId, (err,data) => {
    if( !err ) {
      addressUI.addressToForm(JSON.parse(data));
    } else {
      console.log(err,data);
    }
  });  
}


//////////////////////////////////////////////////////////
//    Handle save address data for both create new or edit
//////////////////////////////////////////////////////////

function saveAddress(callback) {
  const addr = addressUI.formToAddress();
  addr.customerId = customerId;
  if( addressAction === 'AddAddress' ) {
    addressXhr.addAddress(addr, callback);
  } else if( addressAction === 'EditAddress' ) {
    addr.addressId = addressId;
    addressXhr.editAddress(addr, addressId, callback);
  }
}

function saveAddressCallback(err, data) {
  if( err === null ) {
    addressUI.clearAddressList();
    displayAddressList();
  } else if( err === 'ERR_POST') {
    console.log( 'error in saving address ' + data );
  }
}

  



