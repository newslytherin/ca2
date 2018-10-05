# CA2

[Course Assignment 2](https://docs.google.com/document/d/1hHtsSG-cZxqjkq-Ii2bbdir-lmZDgvxUFwT6RHwPYCw/edit)


# API Documetation

## Person
#### **GET** `api/person?street=street&zipcode=zipcode`
returns a list of all persons filtered by street and/or zipcode as json
if neither street nor zipcode is specified return a list of all persons

**Succes**
```json
[
  {
    "id": 1,
    "email": "xxx",
    "name": "xxx",
    "zipCode": 1234,
    "phones": [12345678, 23456789, ...],
    "hobbies": ["hobby1", "hobby2", ...]
  },
  {...}
]
```
**Fejl forbindelse**
```json
{
  "statusCode": 404,
   "message": "xxx",
   "description": "xxx"
}
```
newlines added for readability

---
#### **GET** `api/person/hobby/{hobby}`
returns a list of persons with a specific hobby as json

**Succes**
```json
[
  {
    "id": 1,
    "email": "xxx",
    "name": "xxx",
    "zipCode": 1234,
    "phones": [12345678, 23456789, ...],
    "hobbies": ["hobby1", "hobby2", ...]
  },
  {...}
]
```
**Fejl forbindelse**
```json
{
  "statusCode": 404,
   "message": "xxx",
   "description": "xxx"
}
```
newlines added for readability

---
#### **GET** `api/person/id/{id}`
returns a person with the specific id as json

**Succes**
```json
{
  "id": 1,
  "email": "xxx",
  "name": "xxx",
  "zipCode": 1234,
  "phones": [12345678, 23456789, ...],
  "hobbies": ["hobby1", "hobby2", ...]
}
```
**Fejl forbindelse**
```json
{
  "statusCode": 404,
   "message": "xxx",
   "description": "xxx"
}
```
newlines added for readability

---
#### **GET** `api/person/email/{email}`
returns a person with the specific email as json

**Succes**
```json
{
  "id": 1,
  "email": "xxx",
  "name": "xxx",
  "zipCode": 1234,
  "phones": [12345678, 23456789, ...],
  "hobbies": ["hobby1", "hobby2", ...]
}
```
**Fejl forbindelse**
```json
{
  "statusCode": 404,
   "message": "xxx",
   "description": "xxx"
}
```
newlines added for readability

---

#### **POST** `api/person`
Consumes json
```json
{
	"firstName": "Daniel",
	"lastName": "Lindholm",
	"email": "mail@mail.dk" 
}
```
**Succes**
```json
{
  "firstName": "xxx",
  "lastName": "xxx",
  "id": 1,
  "email": "xxx",
}
```
**Fejl forbindelse**
```json
{
  "statusCode": 500,
   "message": "xxx",
   "description": "xxx"
}
```
newlines added for readability
---


## Company

#### **GET** `api/company`
*returns a list of all companies*

url filters:
set minimum marketvalue<br> 
?valuemin=5

set maximum marketvalue<br>
?valuemax=2500

set minimum employee count<br>
?empmin=400

set maximum employee count<br>
?empmax=400

only allow companies with an address on a certain street<br>
?street=nørgaardsvej

only allow companies with an address on in a certain area<br>
?zipcode=2800

**Fuldført forbindelse**
```js
[
    {
        "id": 1,
        "name": "Testcompany",
        "email": "test@company.dk",
        "address": "Nørgaardsvej 30",
        "zipCode": 2800,
        "city": "Lyngby",
        "cvr": "554-1122-33",
        "description": "beskrivelsen",
        "numEmployees": 500,
        "marketValue": 2000,
        "phones": [
            "83838485",
            "12345678"
        ]
    },
    {
        "id": 2,
        "name": "Testcompany",
        "email": "test@company.dk",
        "address": "Nørgaardsvej 30",
        "zipCode": 2800,
        "city": "Lyngby",
        "cvr": "554-1122-33",
        "description": "beskrivelsen",
        "numEmployees": 500,
        "marketValue": 2000,
        "phones": [
            "83838485",
            "12345678"
        ]
    },
    {
        "id": 3,
        "name": "Testcompany",
        "email": "test@company.dk",
        "address": "Nørgaardsvej 30",
        "zipCode": 2800,
        "city": "Lyngby",
        "cvr": "554-1122-33",
        "description": "beskrivelsen",
        "numEmployees": 500,
        "marketValue": 2000,
        "phones": [
            "83838485",
            "12345678"
        ]
    }
]
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/name/{name}`
*returns a company with specific name*

**Fuldført forbindelse**
```js
  {
    "name": "name",
    "description": "description",
    "cvr": "cvr",
    "numEmployees": "numEmployees",
    "marketValue": "marketValue"
  }
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/cvr/{`cvr`}`
*returns a company with specific cvr*

**Fuldført forbindelse**
```js
  {
    "name": "name",
    "description": "description",
    "cvr": "cvr",
    "numEmployees": "numEmployees",
    "marketValue": "marketValue"
  }
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/id/{`id`}`
*returns a company with specific id*

**Fuldført forbindelse**
```js
  {
    "name": "name",
    "description": "description",
    "cvr": "cvr",
    "numEmployees": "numEmployees",
    "marketValue": "marketValue"
  }
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/phone/{`phone`}`
*returns a company with specific phone number*

**Fuldført forbindelse**
```js
  {
    "name": "name",
    "description": "description",
    "cvr": "cvr",
    "numEmployees": "numEmployees",
    "marketValue": "marketValue"
  }
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/email/{`email`}`
*returns a company with specific email*

**Fuldført forbindelse**
```js
  {
    "name": "name",
    "description": "description",
    "cvr": "cvr",
    "numEmployees": "numEmployees",
    "marketValue": "marketValue"
  }
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---

#### **GET** `api/company/?countmin=countmin&countmax=countmax&zip=zip&street=street&valuemin=valuemin&valuemax=valuemax`
*returns a companies with specific parameters*

**Fuldført forbindelse**
```js
[
  {"name": "name",
  "description": "description",
  "cvr": "cvr",
  "numEmployees": "numEmployees",
  "marketValue": "marketValue"},
  { ... }
]
```

**Fejl forbindelse**
```js
{
  "status code": "status code",
   "detail": "detail",
 }
```
---



**City**

* api/city
* api/city/zip/{`zip`}

**Hobby**

* api/hobby
* api/hobby/name/{`name`}

**Phone**

* api/phone
* api/phone/number/{`number`}

**Address**

* api/address
* api/address/zip/{`zip`}
