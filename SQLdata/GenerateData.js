var fNames = [
    "Daniel", "Jacob", "Nikolaj", "Stephan", "Bob", "Kurt", "Lars", "Peter", "Jens", "Michael", "Henrik", "Thomas", "Søren", "Jan", "Christian", "Martin", "Niels", "Anders", "Morten", "Jesper", "Jørgen", "Hans", "Per", "Ole", "Rasmus",
    "Anne", "Kirsten", "Mette", "Hanne", "Susanne", "Lene", "Maria", "Marianne", "Lone", "Camilla", "Inge", "Pia", "Karen", "Bente", "Louise", "Charlotte", "Jette", "Tina", "Asger", "Lars", "Gitte", "Magne", "Thor", "Sigurd", "Jakob",
    "Ida", "Emma", "Sofia", "Ella", "Freja", "Josefine", "Alma", "Alberte", "Anna", "Agnes", "Laura", "Nora", "Clara", "Karla", "Isabella", "Olivia", "Lærke", "Victoria", "Mille", "Luna", "Aya", "Sofie", "Ellen", "Lily", "Mathilde",
    "Maja", "Frida", "Emilie", "Marie", "Esther", "Liva", "Emily", "Caroline", "Sara", "Astrid", "Ellie", "Rosa", "Asta", "Alba", "Liv", "Hannah", "Andrea", "Vilma", "Mynte", "Eva", "Naja", "Nanna", "Lea", "Saga", "Vigga", "William",
    "Noah", "Oscar", "Lucas", "Carl", "Oliver", "Victor", "Alfred", "Malthe", "Emil", "Valdemar", "Elias", "Magnus", "Aksel", "Frederik", "Felix", "Elliot", "August", "Anton", "Nohr", "Alexander", "Villads", "Christian", "Johan", "Adam",
    "Arthur", "Liam", "Albert", "Theo", "Mikkel", "Viggo", "Benjamin", "Theodor", "Storm", "Sebastian", "Mads", "Mathias", "Milas", "Philip", "Otto", "Konrad", "Lauge", "Louie", "Marius", "Villum"
];

var lNames = [
    "Nielsen", "Jensen", "Hansen", "Pedersen", "Andersen", "Christensen", "Larsen", "Sørensen", "Rasmussen", "Jørgensen", "Petersen", "Madsen", "Kristensen", "Olsen", "Thmsen", "Christiansen", "Poulsen", "Johansen", "Møller", "Mortensen"
];

var endOfLname = [
    "sen", "son", ".rar", ".zip", "hus", "-san", "mand", "berg", "høj", "holm"
];

var roadNames = [
    "vej", "strede", "gade", "boulevard", "torv", "gågade", "avenue", "venget", "led", "bakken", "vang"
];

var hArr = [
    new Hobby("Fodbold", "at sparke til en bold"), new Hobby("computer spil", "at slå på et keyboard"), new Hobby("sove", "zZzzZzzZZz"), new Hobby("samle frimærker", "whyy"), new Hobby("løbe", "gotta go fast"), new Hobby("gemmeleg", "gemme fars bælte"), new Hobby("Stilleleg", "sammen med onkel")
];

var phoneArr = [
    "Arbejde", "Fastnet", "Mobil", "Satellit"
];

var companyNames = [
    " & co", "A/S", " inc", ".dk", ".com", ".org", ".co.uk", ".co"
];

var compDesc = [
    "Lorem", "ipsum", "dolor", "sit", "amet", "volutpat", "donec", "enim", "neque", "Justo", "mi", "ligula", "quis", "senectus", "tincidunt", "tellus"
];

var boolAddress = new Array(6840).fill(false);

function genLNames() {
    return fNames[randonNum(fNames.length)] + endOfLname[randonNum(endOfLname.length)];
}

function randonNum(n) {
    var res = Math.random() * n;
    return res - (res % 1);
}

function hobbySql(h) {
    return "INSERT INTO HOBBY (NAME, DESCRIPTION) VALUES ('" + h.name + "', '" + h.desc + "');";
}

function Hobby(name, desc) {
    this.name = name;
    this.desc = desc;
}

function genVName() {
    return fNames[randonNum(fNames.length)] + roadNames[randonNum(roadNames.length)];
}

function saveToFile(name, text) {
    const fs = require('fs');

    // write to a new file named text.txt
    fs.writeFile(name + '1.txt', text.join("\n"), (err) => {
        // throws an error, you could also catch it here
        if (err) throw err;

        // success case, the file was saved
        console.log(name + ' saved!');
    });
}

function genAddresses() {
    var total = [];
    for (var i = 1; i <= 1352; i++) {
        var numOfRoads = randonNum(4) + 1;
        for (var l = 1; l <= numOfRoads; l++) {
            var numOfHouses = randonNum(3) + 1;
            var nameOfRoad = genVName();
            for (let z = 1; z <= numOfHouses; z++) {
                total.push("INSERT INTO address (Addressinfo, street, cityinfo_id) VALUES ('" + z + "', '" + nameOfRoad + "', " + i + ");");
            }
        }
    }
    saveToFile("Address", total);
}

function genPhoneNumbers(max) {
    var pnumbers = [];
    for (let i = 1; i <= max; i++) {
        // console.log(Math.floor(Math.random() * 89999999) + 10000000);
        var num = randonNum(3);
        for (let l = 0; l <= num; l++) {
            pnumbers.push("INSERT INTO phone (DESCRIPTION, NUMBER, INFOENTITY_id) VALUES ('" + phoneArr[randonNum(phoneArr.length)] + "', " + (Math.floor(Math.random() * 89999999) + 10000000) + ", " + i + ");");
        }
    }
    saveToFile("Phone numbers", pnumbers);
}

function genPerson(max) {
    var persons = [];
    for (var i = 1; i < max; i++) {
        var fName = fNames[randonNum(fNames.length)];
        var lName = genLNames();
        var address = getAddress();
        var email = fName + "." + lName + i + "@mail.dk";
        persons.push("INSERT INTO INFO (type, email, firstname, lastname, address_id) VALUES ('P', '" + email + "', '" + fName + "', '" + lName + "', '" + address + "');");
    }
    saveToFile("Person", persons);
}

function genCompanies(max) {
    var comps = [];
    for (let i = 0; i < max; i++) {
        var name = fNames[randonNum(fNames.length)] + companyNames[randonNum(companyNames.length)];
        var email = name + "@mail.dk";
        var cvr = randonNum(90000000);
        var emps = randonNum(50) + 1;
        var description = compDesc[randonNum(compDesc.length)];
        var value = randonNum(1000000000) + 10000;
        comps.push("INSERT INTO INFO (type, email, name, cvr, numemployees, description, marketvalue, address_id) VALUES ('C', '" + email + "', '" + name + "', '" + cvr + "', '" + emps + "', '" + description + "', '" + value + "', '" + getAddress() + "');");
    }
    // console.log(comps[0]);
    saveToFile("companies", comps);
}

function genHobbiesForPerson(max) {
    var totalHobs = [];
    for (let i = 1; i < max; i++) {
        var numOfHobbies = randonNum(5);
        var hobs = [];
        for (let l = 0; l < numOfHobbies; l++) {
            var rnunm = randonNum(8) + 1;
            if (!hobs.includes(rnunm)) {
                hobs.push(rnunm);
                totalHobs.push("INSERT INTO hobby_info (hobbies_id, people_id) VALUES (" + rnunm + ", " + i + ");");
            } else {
                l--;
            }

        }//INSERT INTO hobby_info (hobbies_id, people_id) VALUES (2, 1400);
    }
    saveToFile("persons and hobbies", totalHobs);
}

genCompanies(150);
// genPerson(3000);
// genPhoneNumbers(3149);
// genAddresses();
// genHobbiesForPerson(3000);

function getAddress() {
    for (let i = 0; i < 10000; i++) {
        var num = randonNum(boolAddress.length);
        var used = boolAddress[num];
        if (!used && num !== 0) {
            boolAddress[num] = true;
            return num;
        }
    }
    throw new console.error('out of addresses');
}