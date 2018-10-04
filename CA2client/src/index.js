//import 'bootstrap/dist/css/bootstrap.css'

var target = document.getElementById('target');
var filter = document.getElementById('filter');
var tableHead = document.getElementById('tablehead');
var tableBody = document.getElementById('tablebody');
document.getElementById('find-btn').addEventListener('click', getData);
document.getElementById('add-person-phone').addEventListener('click', addPersonPhoneInput);
document.getElementById('add-company-phone').addEventListener('click', addCompanyPhoneInput);
document.getElementById('add-hobby').addEventListener('click', addHobbyInput);

var personPhoneContainer = document.getElementById('person-phone-container');
var companyPhoneContainer = document.getElementById('company-phone-container');
var hobbyContainer = document.getElementById('hobby-container');

target.onchange = setFilter;

const URL = "http://localhost:8090/CA2api/api/";

function getData() {
    fetch(URL + getInputs())
        .then(res => handleHttpErrors(res))
        .then(data => renderTable(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(eJson => console.log("Error: " + eJson.detail))
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

// set values for filter option
function setFilter() {
    switch (target.value) {
        case 'person':
            addFilters('all', 'phone', 'email', 'hobby', 'city');
            break;
        case 'company':
            addFilters('all', 'cvr', 'name', 'phone', 'email', 'city');
            break;
        case 'city':
            addFilters('all', 'zip');
            break;
        case 'address':
            addFilters('all', 'zip');
            break;
        case 'phone':
            addFilters('all', 'number');
            break;
        case 'hobby':
            addFilters('all', 'name');
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

// iterate through data and adds it to the table 
function renderTable(data) {
    var tHead = "";
    var tBody = "";
    var first = true;

    // adds data to array
    if (!Array.isArray(data)) {
        var dataTmp = data;
        data = [dataTmp];
    }

    // iterate through json objects in array
    for (var object in data) {
        tBody += "<tr>";

        // iterate through values in json object
        for (var key in data[object]) {
            // adds table header from key values
            if (first) tHead += "<th>" + key + "</th>";
            tBody += "<td>" + data[object][key] + "</td>";
        }
        // stops adding table headers
        first = false;
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
function addPersonPhoneInput(){
    personPhoneContainer.innerHTML += "<hr>";
    personPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    personPhoneContainer.innerHTML += "<input type='text' id='phone-description' class='form-control' placeholder='phone description'>";
}

// note: get phone numers by class!
function addCompanyPhoneInput(){
    companyPhoneContainer.innerHTML += "<hr>";
    companyPhoneContainer.innerHTML += "<input type='text' class='form-control phone' placeholder='phone number'>";
    companyPhoneContainer.innerHTML += "<input type='text' id='phone-description' class='form-control' placeholder='phone description'>";
}

// note: get phone numers by class!
function addHobbyInput(){
    hobbyContainer.innerHTML += "<hr>";
    hobbyContainer.innerHTML += "<input type='text' id='hobby-name' class='form-control' placeholder='name (optional)'>";
    hobbyContainer.innerHTML += "<input type='text' id='hobby-desciption' class='form-control' placeholder='desciption (optional)'>";
}