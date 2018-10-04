# CA2

[Course Assignment 2](https://docs.google.com/document/d/1hHtsSG-cZxqjkq-Ii2bbdir-lmZDgvxUFwT6RHwPYCw/edit)


# API Documetation

## Person
#### **GET** `api/person`
returns a list of all persons as json

**Succes**
```js
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
```js
{
  "status code": 404,
   "detail": "xxxx",
}
```
newlines added for readability

---
#### **GET** `api/person/id/{id}`
returns a person with specific id as json

**Succes**
```js
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
```js
{
  "status code": 404,
   "detail": "xxxx",
}
```
newlines added for readability

---

| GET |  |
| --- | --- |
| api/person/                 | returns a list of all persons |
| api/person/id/{`id`}        | returns a person with specific id |
| api/person/phone/{`phone`}  | returns a person with specific phone number |
| api/person/email/{`email`}  | returns a person with specific email |
| api/person/person?zip=`zip`&street=`street`&hobby=`hobby` | returns a person with specific query params |

## Company

#### **GET** `api/company/`
*returns a list of all companies*

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
