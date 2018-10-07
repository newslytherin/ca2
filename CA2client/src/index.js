//import 'bootstrap/dist/css/bootstrap.css'

// DOM variables
var target = document.getElementById('target');
var filter = document.getElementById('filter');
var tableHead = document.getElementById('tablehead');
var tableBody = document.getElementById('tablebody');
var personPhoneContainer = document.getElementById('person-phone-container');
var companyPhoneContainer = document.getElementById('company-phone-container');
var hobbyContainer = document.getElementById('hobby-container');
var addHobbyContainer = document.getElementById('add-hobby-container');

// evenet listeners
document.getElementById('find-btn').addEventListener('click', getDefault);
document.getElementById('add-person-phone').addEventListener('click', addPersonPhoneInput);
document.getElementById('add-company-phone').addEventListener('click', addCompanyPhoneInput);
document.getElementById('companyParamBtn').addEventListener('click', getCompanyQuery);
document.getElementById('personParamBtn').addEventListener('click', getPersonQuery);
document.getElementById('add-hobby').addEventListener('click', addHobbyInput);
document.getElementById('save-hobby').addEventListener('click', addHobbyAsJson);
document.getElementById('tablebody').addEventListener('click', viewDetails);
document.getElementById('add-person').addEventListener('click', addPerson);
document.getElementById('add-company').addEventListener('click', addCompany);
target.onchange = setFilter; // changes filter options
filter.onchange = toggleKeyInput; // toggles key input

// api root url
const URL = "https://stephandjurhuus.com/CA2api/api/";

// data from fetch
var fetchData = null;
var id = null;

////////////////////////////////////////
//  FETCH CALLS WITH CALLBACKS 
////////////////////////////////////////

// call fetch with parson query params
function getPersonQuery() {
    getData(getPersonParams);
}

// call fetch with company query params
function getCompanyQuery() {
    getData(getCompanyParams);
}

// call fetch with target, filter and key params
function getDefault() {
    getData(getInputs);
}

// returns input from target, filter and key as uri string
// returns target if filter value equals 'all'
function getInputs() {
    return filter.value == 'all' ? target.value : target.value + "/" + filter.value + "/" + key.value;
}

// call fetch with callback param to specify query request
function getData(callback) {
    resetTable();
    fetch(URL + callback())
        .then(res => handleHttpErrors(res))
        .then(data => {
            fetchData = data;
            console.log(data);
            return renderTable(data)
        })
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(eJson => console.log("Error: " + eJson.message))
            } else {
                console.log("Netværks fejl")
            }
        })
}

// error handler, if the res status code is above 300 and returns a json error
function handleHttpErrors(res) {
    if (res.ok) {
        return res.json();
    } else {
        return Promise.reject({
            httpError: res.status,
            fullError: res.json()
        })
    }
}

// set tables to be empty
// (the idea was to implement a loader when fetch calls would come instantly)
function resetTable() {
    tableHead.innerHTML = null;
    tableBody.innerHTML = null;
}

////////////////////////////////////////
//  SET FILTERS AND BUTTONS
////////////////////////////////////////

// set values for filter option
function setFilter() {
    switch (target.value) {
        case 'person':
            addFilters('all', 'id', 'phone', 'email', 'hobby');
            toggelAdvancedbtn("person");
            break;
        case 'company':
            addFilters('all', 'cvr', 'name', 'phone', 'email');
            toggelAdvancedbtn("company");
            break;
        case 'city':
            addFilters('all', 'zip');
            toggelAdvancedbtn();
            break;
        case 'address':
            addFilters('all', 'zip');
            toggelAdvancedbtn();
            break;
        case 'phone':
            addFilters('all', 'number');
            toggelAdvancedbtn();
            break;
        case 'hobby':
            addFilters('all', 'name');
            toggelAdvancedbtn();
    }
}

// iterate through arguments and adds to array
function addFilters() {
    var filters = [];
    for (var key in arguments) {
        filters.push(arguments[key]);
    }
    renderFilters(filters);
}

// adds filters to option
function renderFilters(filters) {
    filter.innerHTML = filters.map(
        value => "<option value=" + value + ">" + value + "</option>").join();
}

function toggleKeyInput(){
    filter.value == 'all' ? $('#key').addClass('hidden') : $('#key').removeClass('hidden');
}

// adds advanced button (jquery)
function toggelAdvancedbtn(target) {
    if (target === "person") {
        $('#p-advanced-btn').removeClass('hidden');
        $('#c-advanced-btn').addClass('hidden');
    } else if (target === "company") {
        $('#c-advanced-btn').removeClass('hidden');
        $('#p-advanced-btn').addClass('hidden');
    } else {
        $('#p-advanced-btn').addClass('hidden');
        $('#c-advanced-btn').addClass('hidden');
    }
}

////////////////////////////////////////
//  OPENS MODALS AND SET VALUES
////////////////////////////////////////

// opens edit modal for person or company
function viewDetails(object) {
    if (target.value == 'person') {
        $('#person-edit-model').modal('show');
        setEditValuesPerson(object.target.id);
    } else if (target.value == 'company') {
        $('#company-edit-model').modal('show');
        setEditValuesCompany(object.target.id);
    } else {
        return;
    }
}

// sets values in edit person modal
function setEditValuesPerson(index) {

    id = null;
    // generic list/ object initializer
    var jsonObject = (!Array.isArray(fetchData)) ? fetchData : fetchData[index];

    // general setters
    id = jsonObject['id'];
    document.getElementById('edit-title').innerText = jsonObject['name'];
    document.getElementById('edit-email').value = jsonObject['email'];

    // phones setters
    document.getElementById('person-phone-container').innerHTML = "<h5><b>phones</b></h5>";
    for (var phone in jsonObject['phones']) {
        document.getElementById('person-phone-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['phones'][phone] + "'>";
    }

    // address setters
    if(jsonObject['address'] != undefined) document.getElementById('edit-street').value = jsonObject['address'];
    if(jsonObject['city'] != undefined) document.getElementById('edit-city').value = jsonObject['city'];
    if(jsonObject['zipCode'] != 0) document.getElementById('edit-zipcode').value = jsonObject['zipCode'];

    // hobbies setters
    document.getElementById('hobby-container').innerHTML = "";
    for (var hobby in jsonObject['hobbies']) {
        document.getElementById('hobby-container').innerHTML += "<p type='text' class='form-control'>"+jsonObject['hobbies'][hobby]+"</p>";
    }
    
}

// sets values in edit company modal
function setEditValuesCompany(index) {

    // generic list/ object initializer
    var jsonObject = (!Array.isArray(fetchData)) ? fetchData : fetchData[index];

    // general setters
    document.getElementById('edit-company-title').innerText = jsonObject['name'] + " (cvr: " + jsonObject['cvr'] + ")";
    document.getElementById('edit-company-email').value = jsonObject['email'];

    // phones setters
    document.getElementById('company-phone-container').innerHTML = "<h5><b>phones</b></h5>";
    for (var phone in jsonObject['phones']) {
        document.getElementById('company-phone-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['phones'][phone] + "'>";
    }

    // address setters
    if(jsonObject['address'] != undefined) document.getElementById('edit-company-street').value = jsonObject['address'];
    if(jsonObject['city'] != undefined) document.getElementById('edit-company-city').value = jsonObject['city'];
    if(jsonObject['zipCode'] != undefined) document.getElementById('edit-company-zipcode').value = jsonObject['zipCode'];

    // company info setters
    document.getElementById('edit-company-value').value = jsonObject['marketValue'];
    document.getElementById('edit-company-employees').value = jsonObject['numEmployees'];
}

// adds phone input fields in person edit modal
function addPersonPhoneInput() {
    personPhoneContainer.innerHTML += "<hr>";
    personPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    personPhoneContainer.innerHTML += "<input type='text' class='phone-description form-control' placeholder='phone description'>";
}

// adds hobby input fields in person edit modal
function addHobbyInput() {
    addHobbyContainer.innerHTML = "<hr";
    addHobbyContainer.innerHTML += "<b>add hobby</b>";
    addHobbyContainer.innerHTML += "<input type='text' id='add-hoby-name' class='hobby-name form-control' placeholder='name'>";
    addHobbyContainer.innerHTML += "<input type='text' id='add-hoby-desc' class='hobby-desciption form-control' placeholder='desciption'>";
    $('#save-hobby').removeClass('hidden');
    $('#add-hobby').addClass('hidden');
}

// adds phone input fields in company edit modal
function addCompanyPhoneInput() {
    companyPhoneContainer.innerHTML += "<hr>";
    companyPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    companyPhoneContainer.innerHTML += "<input type='text' class='phone-description form-control' placeholder='phone description'>";
}

////////////////////////////////////////
//  RENDERS TABLE DATA
////////////////////////////////////////

// iterate through data and adds it to the table 
function renderTable(data) {
    var tHead = "";
    var tBody = "";
    var header = true;

    // adds data to array
    if (!Array.isArray(data)) {
        var dataTmp = data;
        data = [dataTmp];
    }

    // iterate through json objects in array
    for (var index in data) {
        tBody += "<tr>";
        // iterate through values in json object
        for (var key in data[index]) {
            // adds table header from key values
            if (header) tHead += "<th>" + key + "</th>";
            tBody += "<td id = '" + index + "'>" + data[index][key] + "</td>";
        }
        // stops adding table headers
        header = false;
        tBody += "</tr>";
    }
    tableHead.innerHTML = tHead;
    tableBody.innerHTML = tBody;
}

////////////////////////////////////////
//  SET QUERY PARAMETERS IN ADVANCED SEARCH
////////////////////////////////////////

// converts person values into a query param
function getPersonParams() {

    // adds value specification to array
    var identities = [];
    identities.push('zipcode');
    identities.push('street');

    // adds value to array
    var params = [];
    params.push(document.getElementById('zipcode').value);
    params.push(document.getElementById('street').value);

    // adds value specification and value to array if value exist
    var queryParams = [];
    for (var index in params) {
        if (params[index] != '' && params[index] != null) {
            queryParams.push(identities[index] + "=" + params[index]);
        }
    }
    $('#person-params-modal').modal('hide');

    // returns a joined string with '&' seperation
    return "person?" + queryParams.join("&");
}

// converts company values into a query param
function getCompanyParams() {

    // adds value specification to array
    var identities = [];
    identities.push('empmin');
    identities.push('empmax');
    identities.push('valuemin');
    identities.push('valuemax');
    identities.push('street');
    identities.push('zipCode');

    // adds value to array
    var params = [];
    params.push(document.getElementById('qp-min-emps').value);
    params.push(document.getElementById('qp-max-emps').value);
    params.push(document.getElementById('qp-min-market').value);
    params.push(document.getElementById('qp-max-market').value);
    params.push(document.getElementById('qp-street').value);
    params.push(document.getElementById('qp-zip-code').value);

    // adds value specification and value to array if value exist
    var queryParams = [];
    for (var index in params) {
        if (params[index] != '' && params[index] != null) {
            queryParams.push(identities[index] + "=" + params[index]);
        }
    }
    $('#company-params-modal').modal('hide');

    // returns a joined string with '&' seperation
    return "company?" + queryParams.join("&");
}

////////////////////////////////////////
//  CONVERTS INPUT TO JSON
////////////////////////////////////////

// adds person values to object and converts to it json
function addPerson() {
    var person = {};
    person.firstName = document.getElementById('firstName').value;
    person.lastName = document.getElementById('lastName').value;
    person.email = document.getElementById('email').value;
    var data = JSON.stringify(person);
    setData(data, "person");
}

// adds company values to object and converts to it json
function addCompany() {
    var company = {};
    company.name = document.getElementById('c-name').value;
    company.email = document.getElementById('c-email').value;
    company.description = document.getElementById('c-desc').value;
    company.cvr = document.getElementById('c-cvr').value;
    company.numEmployees = document.getElementById('c-emps').value;
    company.marketValue = document.getElementById('c-value').value;
    var data = JSON.stringify(company);
    setData(data, "company");
}

// adds phone values to object and converts to it json
function addPhoneAsJson() {
    var phone = {};
    // fk ie id?
    phone.number = document.getElementById('number');
    phone.description = document.getElementById('description');
    return JSON.stringify(phone);
}

// adds hobby values to object and converts to it json
function addHobbyAsJson() {
    var hobby = {};
    //hobby. = id;
    hobby.name = document.getElementById('add-hoby-name');
    hobby.description = document.getElementById('add-hoby-desc');

    addHobbyContainer.innerHTML = null;
    $('#person-edit-model').modal('hide');
    $('#add-hobby').removeClass('hidden');
    $('#save-hobby').addClass('hidden');

    var data = JSON.stringify(hobby);
    //setData(data, "hobby");
}

// adds address values to object and converts to it json
function addAddressAsJson() {
    var address = {};
    // fk ie id?
    // fk zipcode?
    address.street = document.getElementById('street');
    address.addressIfo = document.getElementById('description');
    return JSON.stringify(address);
}

// calls fetch POST to add data at given path
function setData(data, path) {
    resetTable();
    fetch(URL + path, {
            method: "POST",
            redirect: "follow",
            body: data,
            headers: {
                'Content-Type': 'application/json'
            }
        }).catch(err => {
            if (err.httpError) {
                err.fullError.then(eJson => console.log("Error: " + eJson.detail))
            } else {
                console.log("Netværks fejl")
            }
        })
    }

