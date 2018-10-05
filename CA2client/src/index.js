//import 'bootstrap/dist/css/bootstrap.css'

var target = document.getElementById('target');
var filter = document.getElementById('filter');
var tableHead = document.getElementById('tablehead');
var tableBody = document.getElementById('tablebody');
var personPhoneContainer = document.getElementById('person-phone-container');
var companyPhoneContainer = document.getElementById('company-phone-container');
var hobbyContainer = document.getElementById('hobby-container');

document.getElementById('find-btn').addEventListener('click', getDefault);
document.getElementById('add-person-phone').addEventListener('click', addPersonPhoneInput);
document.getElementById('add-company-phone').addEventListener('click', addCompanyPhoneInput);
document.getElementById('companyParamBtn').addEventListener('click', getCompanyQuery);
document.getElementById('personParamBtn').addEventListener('click', getPersonQuery);
document.getElementById('add-hobby').addEventListener('click', addHobbyInput);
document.getElementById('tablebody').addEventListener('click', viewDetails);
document.getElementById('add-person').addEventListener('click', addPerson);
document.getElementById('add-company').addEventListener('click', addCompany);

var fetchData = null;
target.onchange = setFilter;

var inputs;

function getPersonQuery() {
    getData(getPersonParams);
}

function getCompanyQuery() {
    getData(getCompanyParams);
}

function getDefault() {
    getData(getInputs);
}

//  CHANGE IP TO LOCAL!!
const URL = "http://localhost:8090/CA2api/api/";

function getData(callback) {
    resetTable();
    fetch(URL + callback())
        .then(res => handleHttpErrors(res))
        .then(data => {
            fetchData = data;
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

function resetTable() {
    tableHead.innerHTML = null;
    tableBody.innerHTML = null;
}

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

// adds advanced button
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

// adds filters to option
function renderFilters(filters) {
    filter.innerHTML = filters.map(
        value => "<option value=" + value + ">" + value + "</option>").join();
}

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

function setEditValuesPerson(index) {

    var jsonObject = (!Array.isArray(fetchData)) ? fetchData : fetchData[index];

    // general
    document.getElementById('edit-title').innerText = jsonObject['name'];
    document.getElementById('edit-email').value = jsonObject['email'];

    // phones
    document.getElementById('edit-phone-container').innerHTML = "<h5><b>phones</b></h5>";
    for (var phone in jsonObject['phones']) {
        document.getElementById('edit-phone-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['phones'][phone] + "'>";
        console.log(jsonObject['phones'][phone]);
    }

    // address
    if(jsonObject['address'] != undefined) document.getElementById('edit-street').value = jsonObject['address'];
    if(jsonObject['city'] != undefined) document.getElementById('edit-city').value = jsonObject['city'];
    if(jsonObject['zipCode'] != 0) document.getElementById('edit-zipcode').value = jsonObject['zipCode'];

    // hobbies
    document.getElementById('edit-hobby-container').innerHTML = "";
    for (var hobby in jsonObject['hobbies']) {
        document.getElementById('edit-hobby-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['hobbies'][hobby] + "'>";
    }
}

function setEditValuesCompany(index) {

    var jsonObject = (!Array.isArray(fetchData)) ? fetchData : fetchData[index];

    // general
    document.getElementById('edit-company-title').innerText = jsonObject['name'] + " (cvr: " + jsonObject['cvr'] + ")";
    document.getElementById('edit-company-email').value = jsonObject['email'];

    // phones
    document.getElementById('edit-phone-container').innerHTML = "<h5><b>phones</b></h5>";
    for (var phone in jsonObject['phones']) {
        document.getElementById('edit-phone-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['phones'][phone] + "'>";
        console.log(jsonObject['phones'][phone]);
    }

    // address
    if(jsonObject['address'] != undefined) document.getElementById('edit-company-street').value = jsonObject['address'];
    if(jsonObject['city'] != undefined) document.getElementById('edit-company-city').value = jsonObject['city'];
    if(jsonObject['zipCode'] != undefined) document.getElementById('edit-company-zipcode').value = jsonObject['zipCode'];

    // company info
    document.getElementById('edit-company-value').value = jsonObject['marketValue'];
    document.getElementById('edit-company-employees').value = jsonObject['numEmployees'];
}

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

function getInputs() {
    var targetValue = target.value;
    var filterValue = filter.value;
    var keyValue = key.value;
    return filterValue == 'all' ? targetValue : targetValue + "/" + filterValue + "/" + keyValue;
}

// note: get phone numers by class!
function addPersonPhoneInput() {
    personPhoneContainer.innerHTML += "<hr>";
    personPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    personPhoneContainer.innerHTML += "<input type='text' class='phone-description form-control' placeholder='phone description'>";
}

// note: get phone numers by class!
function addCompanyPhoneInput() {
    companyPhoneContainer.innerHTML += "<hr>";
    companyPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    companyPhoneContainer.innerHTML += "<input type='text' class='phone-description form-control' placeholder='phone description'>";
}

// note: get hobby numers by class!
function addHobbyInput() {
    hobbyContainer.innerHTML += "<hr>";
    hobbyContainer.innerHTML += "<input type='text' class='hobby-name form-control' placeholder='name (optional)'>";
    hobbyContainer.innerHTML += "<input type='text' class='hobby-desciption form-control' placeholder='desciption (optional)'>";
}

function getPersonParams() {
    var identities = [];
    identities.push('zipcode');
    identities.push('street');

    var params = [];
    params.push(document.getElementById('zipcode').value);
    params.push(document.getElementById('street').value);

    var queryParams = [];
    for (var index in params) {
        if (params[index] != '' && params[index] != null) {
            queryParams.push(identities[index] + "=" + params[index]);
        }
    }
    $('#person-params-modal').modal('hide');
    return "person?" + queryParams.join("&");
}

function getCompanyParams() {
    var identities = [];
    identities.push('empmin');
    identities.push('empmax');
    identities.push('valuemin');
    identities.push('valuemax');
    identities.push('street');
    identities.push('zipCode');

    var params = [];
    params.push(document.getElementById('qp-min-emps').value);
    params.push(document.getElementById('qp-max-emps').value);
    params.push(document.getElementById('qp-min-market').value);
    params.push(document.getElementById('qp-max-market').value);
    params.push(document.getElementById('qp-street').value);
    params.push(document.getElementById('qp-zip-code').value);

    var queryParams = [];
    for (var index in params) {
        if (params[index] != '' && params[index] != null) {
            queryParams.push(identities[index] + "=" + params[index]);
        }
    }
    $('#company-params-modal').modal('hide');
    return "company?" + queryParams.join("&");
}

function addPerson() {
    var person = {};
    person.firstName = document.getElementById('firstName').value;
    person.lastName = document.getElementById('lastName').value;
    person.email = document.getElementById('email').value;
    var data = JSON.stringify(person);
    setData(data, "person");
}

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

function addPhoneAsJson() {
    var phone = {};
    // fk ie id?
    phone.number = document.getElementById('number');
    phone.description = document.getElementById('description');
    return JSON.stringify(phone);
}

function addHobbyAsJson() {
    var hobby = {};
    // fk person id?
    hobby.name = document.getElementById('name');
    hobby.description = document.getElementById('description');
    return JSON.stringify(hobby);
}

function addAddressAsJson() {
    var address = {};
    // fk ie id?
    // fk zipcode?
    address.street = document.getElementById('street');
    address.addressIfo = document.getElementById('description');
    return JSON.stringify(address);
}

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
