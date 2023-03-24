class CustomerXhr {
  
  baseUrl = 'http://localhost:8080/api/customers/';

  //TODO  add telephone number to customer
  getCustomers(callback) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', this.baseUrl, true);
    xhr.onload = function() {
      if(xhr.status === 200) {
        // TODO - responseText should not be empty
        // I get empty when an exception happens on the the server side
        // this is the default stuff Jersey does probably.
        // should handle it and send some kind of error message
        callback(null, xhr.responseText);
      } else {
        callback('ERR_LOAD', xhr.status);
      }
    }
    xhr.onerror = function() {
      callback( 'ERR_OTHER', null );
    }
    xhr.send();
  }

  getCustomer(customerId, callback) {
    const xhr = new XMLHttpRequest();
    const custUrl = this.baseUrl + '/' + customerId; 
    xhr.open('GET', custUrl, true);
    xhr.onload = function() {
      if(xhr.status === 200) {
        callback(null, xhr.responseText);
      } else {
        callback('ERR_LOAD', xhr.status);
      }
    }
    xhr.send();
  }
  
  addCustomer(data, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', this.baseUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      callback(null, xhr.responseText);
    }
    xhr.send(JSON.stringify(data));
  }
  
  editCustomer(data, customerId, callback) {
    const xhr = new XMLHttpRequest();
    const custUrl = this.baseUrl + '/' + customerId; 
    xhr.open('PUT', custUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      callback(null, xhr.responseText);
    }
    xhr.send(JSON.stringify(data));
  }

  deleteCustomer(customerId, callback) {
    const xhr = new XMLHttpRequest();
    const custUrl = this.baseUrl + '/' + customerId; 
    xhr.open('DELETE', custUrl, true);
    xhr.onload = function() {
      callback(null, xhr.responseText, customerId);
    }
    xhr.send();
  }

}