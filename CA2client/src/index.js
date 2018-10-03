import 'bootstrap/dist/css/bootstrap.css'

var target = document.getElementById('target');
var filter = document.getElementById('filter');
var tableHead = document.getElementById('tablehead');
var tableBody = document.getElementById('tablebody');
document.getElementById('find-btn').addEventListener('click', getData)

target.onchange = setFilter;

function setFilter(){
    switch(target.value){
        case 'person': filterPerson();
            break;
        case 'company': filterCompany();
            break;
        case 'city': filterCity();
            break;
        case 'address': filterAddress();
            break;
        case 'phone': filterPhone();
            break;
        case 'hobby': filterHobby();
    }
}

function renderFilters(filters){
    var optionDefault = "<option value='' disabled selected hidden>Select</option>";
    filter.innerHTML = optionDefault + filters.map(value => "<option value=" + value + ">" + value + "</option>").join();
}

function filterPerson(){
    var filters = ['all', 'id', 'phone', 'email'];
    renderFilters(filters);
}

function filterCompany(){
    var filters = ['all', 'id', 'cvr', 'name', 'phone', 'email'];
    renderFilters(filters);
}

function filterCity(){
    var filters = ['all', 'zip'];
    renderFilters(filters);
}

function filterAddress(){
    var filters = ['all', 'zip'];
    renderFilters(filters);
}

function filterPhone(){
    var filters = ['all', 'number'];
    renderFilters(filters);
}

function filterHobby(){
    var filters = ['all', 'name'];
    renderFilters(filters);
}

const URL = "http://localhost:8090/CA2api/api/";

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


function renderTable(data){
    var tHead = "";
    var tBody = "";
    var first = true;

    if(data.length == undefined) {
        var dataTmp = data;
        data = [dataTmp];
    }

    for(var i in data){
        tBody += "<tr>";
        
        for(var j in data[i]){
            if (first) {
                tHead += "<th>" + j + "</th>";
            }
            tBody += "<td>" + data[i][j] + "</td>";
        }

        first = false;
        tBody += "</tr>";
    }
    tableHead.innerHTML = tHead;
    tableBody.innerHTML = tBody;
}

function getData() {
    fetch(URL)
        .then(res => handleHttpErrors(res))
        .then(data => {
            console.log(data);
            renderTable(data);
        })
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(eJson => console.log("Error: " + eJson.detail))
            } else {
                console.log("Netværks fejl")
            }
        })
}