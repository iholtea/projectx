const customerUI = new CustomerUI();
const customerXhr = new CustomerXhr();

document.addEventListener('DOMContentLoaded', UIUtil.configureMaterialDatepicker);
document.getElementById('customer_form').addEventListener('submit', (event) => {
  event.preventDefault();
});
document.getElementById('customer_submit').addEventListener('click', addCustomer);


function addCustomer(event) {
  const customer = customerUI.formToCustomer();
  customerXhr.addCustomer(customer, addCustomerCallback);
}

function addCustomerCallback(err, data) {
  const customer = JSON.parse(data);
  document.location.href = `customerEdit.html?customerId=${customer.customerId}`;
}