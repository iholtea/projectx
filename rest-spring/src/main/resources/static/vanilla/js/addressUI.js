class AddressUI {

  formToAddress() {
    const addr = {
      country: document.getElementById('addr_country').value,
      city: document.getElementById('addr_city').value,
      street: document.getElementById('addr_street').value,
      number: document.getElementById('addr_number').value,
      zipCode: document.getElementById('addr_zip').value,
      additionalInfo: document.getElementById('addr_other').value
    }
    return addr;
  }

  addressToForm(addr) {
    document.getElementById('addr_country').value = addr.country; 
    document.getElementById('addr_city').value = addr.city; 
    document.getElementById('addr_street').value = addr.street;
    document.getElementById('addr_number').value = addr.number;
    document.getElementById('addr_zip').value = addr.zipCode;
    document.getElementById('addr_other').value = addr.additionalInfo;
  }

  clearAddressForm() {
    document.getElementById('addr_country').value = ''; 
    document.getElementById('addr_city').value = ''; 
    document.getElementById('addr_street').value = '';
    document.getElementById('addr_number').value = '';
    document.getElementById('addr_zip').value = '';
    document.getElementById('addr_other').value = '';
  }

  displayAddressList( addresses ) {
    const ul = document.getElementById('address_list');
    addresses.forEach( addr => {
      const li = document.createElement('li');
      li.className = 'collection-item avatar';
      li.appendChild( this.createHomeIcon() );
      li.appendChild( this.createAddressText(addr) );
      li.appendChild( this.createSecondaryContent(addr) );
      ul.appendChild(li);
    });
  }
  
  clearAddressList() {
    document.getElementById('address_list').innerHTML = '';
  }

  createHomeIcon() {
    const iHome = document.createElement('i');
    iHome.className = 'material-icons circle';
    iHome.innerText = 'home';
    return iHome;
  }

  createAddressText(addr) {
    const pElem = document.createElement('p');
    pElem.innerHTML = `
      ${addr.city}, ${addr.country} <br>
      ${addr.street}, No. ${addr.number}, Zip ${addr.zipCode} <br>
      ${addr.additionalInfo}
    `;
    return pElem;
  }

  createSecondaryContent(addr) {
    const secDiv = document.createElement('div');
    secDiv.className = 'secondary-content';
  
    const btnEdit = this.createActionBtn('edit',addr.addressId);
    // add modal info for edit
    btnEdit.href = '#edit-address';
    btnEdit.classList.add('modal-trigger');
    secDiv.appendChild(btnEdit);
  
    const btnDelete = this.createActionBtn('delete',addr.addressId);
    secDiv.appendChild(btnDelete);

    return secDiv;
  }

  createActionBtn(action,addrId) {
    const btn = document.createElement('a');
    btn.href = '#';
    btn.id = `${action}-${addrId}`;
    btn.className = `address-${action}`;
    btn.innerHTML = `<i class="material-icons indigo-text">${action}</i>`;
    return btn;  
  }

}