class AddressXhr {
  
  constructor(customerId) {
    this.customerId = customerId;
    this.baseUrl = `http://localhost:8080/projectx-rest-jersey/webapi/customers/${customerId}/addresses`;
  }

  getAddressesByCustomer(callback) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', this.baseUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      if(xhr.status === 200) {
        callback(null, xhr.responseText);
      } else {
        callback('ERR', xhr.status);
      }
    }
    xhr.send();
  }

  getAddress(addrId, callback) {
    const xhr = new XMLHttpRequest();
    const addrUrl = this.baseUrl + '/' + addrId;
    xhr.open('GET', addrUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      if(xhr.status === 200) {
        callback(null, xhr.responseText);
      } else {
        callback('ERR', xhr.status);
      }
    }
    xhr.send();
  }

  addAddress(addr, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', this.baseUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      if(xhr.status === 201) {
        callback(null, null);
      } else {
        callback('ERR_POST', xhr.status);
      }
    }
    xhr.send( JSON.stringify(addr) );
  }
  
  editAddress(addr, addrId, callback) {
    const xhr = new XMLHttpRequest();
    const addrUrl = this.baseUrl + '/' + addrId;
    xhr.open('PUT', addrUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
      if(xhr.status === 200) {
        callback(null, null);
      } else {
        callback('ERR_PUT', xhr.status);
      }
    }
    xhr.send( JSON.stringify(addr) );
  }

  deleteAddress(addrId, callback) {
    const xhr = new XMLHttpRequest();
    const addrUrl = this.baseUrl + '/' + addrId;
    xhr.open('DELETE', addrUrl, true);
    xhr.setRequestHeader('content-type','application/json');
    xhr.onload = function() {
       callback(null, null, addrId);
    }
    xhr.send();
  }

}


