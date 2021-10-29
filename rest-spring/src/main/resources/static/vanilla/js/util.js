class GeneralUtil {

  /**
   * get a Date object from string with format dd/mm/yyyy
   */
  static getDateFromString( dateStr ) {
    const dob = new Date();
    const dobSplit = dateStr.split('/');
    dob.setDate(dobSplit[0]);
    dob.setMonth(dobSplit[1]-1);
    dob.setFullYear(dobSplit[2]);
    return dob;
  }

  /**
   * compute age give the birth date as a standard Date object
   */
  static computeAge(birthDate) {
    const diff = Date.now() - birthDate.getTime();
    const ageDate = new Date(diff); // miliseconds from epoch
    return Math.abs(ageDate.getUTCFullYear() - 1970);
  }
  
  /**
   * left pad with zeroes a numeric value up to a maxLength 
   */
   static leftPadZeros(numValue, maxLength) {
    let str = numValue.toString();
    for (let i = 0; i < maxLength-str.length; i++) {
      str = "0" + str;
    }
    return str;
  }
  
  /**
   * get a standard Date object in format dd/mm/yyyy
   */
  static formatDate(dateObj) {
    const year = dateObj.getFullYear();
    const month = GeneralUtil.leftPadZeros(1 + dateObj.getMonth(), 2);
    const day = GeneralUtil.leftPadZeros(dateObj.getDate(),2);
    return `${day}/${month}/${year}`;
  }

  

}

class UIUtil {

  /**
   * get the "id" part of the element id of an <a> element that was clicked
   * it represent for example a customer id for a series of edit/delete buttons from a list
   * the <a> element might have other inner elements so the
   * the event.target of the click might be one those inner elements
   * Example: return the value 234
   * <a href="#" id="mail-234">
   *   <i class="material-icons">mail</i>
   * </a>
   */
  static getAIdFromElem(event) {
    let targetElem = event.target;
    while( !(targetElem.nodeName === 'A')) {
      targetElem = targetElem.parentElement;
    }
    return targetElem.id.split("-")[1];
  }

  /**
   * get the id parameter value from the curent page url. 
   * For example: http://localhost:5500/customerEdit.html?customerId=52#!
   * return velue 52
   */
  static getIdParamFromUrl() {
    const qString = window.location.href.split('?');
    let idVal = qString[1].split('=')[1];
    if( idVal.includes('#') ) {
      idVal = idVal.substr(0, idVal.indexOf('#'));  
    }
    return idVal;
  }

  static configureMaterialDatepicker() {
    const elems = document.querySelectorAll('.datepicker');
    const options = {
      format: 'dd/mm/yyyy',
      yearRange: [1950, 2010],
      defaultDate: new Date(1990,0,1),
    };
    const instances = M.Datepicker.init(elems,options);
  }

  static configureMaterialModal() {
    const elem = document.getElementById('edit-address');
    const instances = M.Modal.init(elem);
  }

}