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

**Company**

* api/company
* api/company/name/{`name`}
* api/company/cvr/{`cvr`}
* api/company/id/{`id`}
* api/company/phone/{`phone`}
* api/company/email/{`email`}
* api/company/?count=`count`&marketvalue=`marketvalue`&zip=`zip`&street=`street`&min=`min`&max=`max`

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
