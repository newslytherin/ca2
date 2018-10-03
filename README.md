# CA2

[Course Assignment 2](https://docs.google.com/document/d/1hHtsSG-cZxqjkq-Ii2bbdir-lmZDgvxUFwT6RHwPYCw/edit)


## API Documetation

### GET

**Person**

| GET |  |
| --- | --- |
| api/person/                 | returns a list of all persons |
| api/person/id/{`id`}        | returns a person with specific id |
| api/person/phone/{`phone`}  | returns a person with specific phone number |
| api/person/email/{`email`}  | returns a person with specific email |
| api/person/person?zip=`zip`&street=`street`&hobby=`hobby` | returns a person with specific query params |

## Company

**GET** `api/company/`  
*returns a list of all persons*

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
  
* api/company/name/{`name`}
  * returns a person with specific id

* api/company/cvr/{`cvr`}
  * returns a person with specific phone number
  
* api/company/id/{`id`}
  * returns a person with specific email
  
* api/company/phone/{`phone`}
  * returns a person with specific query params
  
* api/company/email/{`email`}
  * returns a person with specific query params
  
* api/company/?countmin=`countmin`&countmax=`countmax`&zip=`zip`&street=`street`&valuemin=`valuemin`&valuemax=`valuemax` 
  * returns a person with specific query params


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
