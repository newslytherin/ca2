//import 'bootstrap/dist/css/bootstrap.css'

var target = document.getElementById('target');
var filter = document.getElementById('filter');
var tableHead = document.getElementById('tablehead');
var tableBody = document.getElementById('tablebody');
document.getElementById('find-btn').addEventListener('click', getData);
document.getElementById('add-person-phone').addEventListener('click', addPersonPhoneInput);
document.getElementById('add-company-phone').addEventListener('click', addCompanyPhoneInput);
document.getElementById('add-hobby').addEventListener('click', addHobbyInput);
document.getElementById('tablebody').addEventListener('click', viewDetails);

var personPhoneContainer = document.getElementById('person-phone-container');
var companyPhoneContainer = document.getElementById('company-phone-container');
var hobbyContainer = document.getElementById('hobby-container');

var fetchData = null;

target.onchange = setFilter;

// CHANGE IP TO LOCAL!!
const URL = "http://localhost:8090/CA2api/api/";

function getData() {
    resetTable();
    fetch(URL + getInputs())
        .then(res => handleHttpErrors(res))
        .then(data => {
            fetchData = data;
            return renderTable(data)
        })
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

function resetTable() {
    tableHead.innerHTML = null;
    tableBody.innerHTML = null;
}

// set values for filter option
function setFilter() {
    switch (target.value) {
        case 'person':
            addFilters('all', 'id', 'phone', 'email', 'hobby');
            break;
        case 'company':
            addFilters('all', 'cvr', 'name', 'phone', 'email');
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

function viewDetails(object) {
    // 
    if(target.value != 'person' && target.value != 'company') return;
    $('#person-edit-model').modal('show');
    setEditValues(object.target.id);
}

function setEditValues(index) {
    var jsonObject = fetchData[index];
    console.log(jsonObject);

    // general
    document.getElementById('edit-title').innerText = jsonObject['name'];
    document.getElementById('edit-email').value = jsonObject['email'];

    document.getElementById('edit-phone-container').innerHTML = "<h5><b>phones</b></h5>";
    for(var phone in jsonObject['phones']){  
        document.getElementById('edit-phone-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['phones'][phone] + "'>";
        console.log(jsonObject['phones'][phone]);
    }

    // address
    document.getElementById('edit-street').value = jsonObject['address'];
    document.getElementById('edit-city').value = jsonObject['city'];
    document.getElementById('edit-zipcode').value = jsonObject['zipCode'];

    // hobbies
    document.getElementById('edit-hobby-container').innerHTML = "";
    for(var hobby in jsonObject['hobbies']){  
        document.getElementById('edit-hobby-container').innerHTML += "<input type='text' class='form-control' value='" + jsonObject['hobbies'][hobby] + "'>";
    }
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
        // tBody += "<td><button value = '" + index + "'>edit</button></td>";
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