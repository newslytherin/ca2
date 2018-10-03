import 'bootstrap/dist/css/bootstrap.css'

var target = document.getElementById('target');
var filter = document.getElementById('filter');

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
            break;
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

