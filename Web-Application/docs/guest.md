# Guest API Spec

## Create Guest With Admin
Endpoint : POST/api/admin/guests

Request Header : 
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "guestType" : "local",
  "name" : "Ali Akbar Rafsanjani",
  "gender" : "Laki-laki",
  "placeOfBirth" : "Jember",
  "dateOfBirth" : "2003-07-02",
  "noHp" : "081234567890",
  "noTelp" : "4255551212",
  "email" : "mohaliakbarrafsanjani@gmail.com",
  "position" : "Vice Precident"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "guest id",
    "guestType" : "local",
    "name" : "Ali Akbar Rafsanjani",
    "gender" : "Laki-laki",
    "placeOfBirth" : "Jember",
    "dateOfBirth" : "2003-07-02",
    "noHp" : "081234567890",
    "noTelp" : "4255551212",
    "email" : "mohaliakbarrafsanjani@gmail.com",
    "position" : "Vice Precident"
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Data not valid, ...."
}
```


## Create Guest Without Admin
Endpoint : POST/api/guests

Request Body : 
```json
{
  "guestType" : "local",
  "name" : "Ali Akbar Rafsanjani",
  "gender" : "Laki-laki",
  "placeOfBirth" : "Jember",
  "dateOfBirth" : "2003-07-02",
  "noHp" : "081234567890",
  "noTelp" : "4255551212",
  "email" : "mohaliakbarrafsanjani@gmail.com",
  "position" : "Vice Precident"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "guest id",
    "guestType" : "local",
    "name" : "Ali Akbar Rafsanjani",
    "gender" : "Laki-laki",
    "placeOfBirth" : "Jember",
    "dateOfBirth" : "2003-07-02",
    "noHp" : "081234567890",
    "noTelp" : "4255551212",
    "email" : "mohaliakbarrafsanjani@gmail.com",
    "position" : "Vice Precident"
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Data not valid, ...."
}
```

## Update Guest
Endpoint : PUT/api/guests/{idGuest}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body : 
```json
{
  "guestType" : "local",
  "name" : "Ali Akbar Rafsanjani",
  "gender" : "Laki-laki",
  "placeOfBirth" : "Jember",
  "dateOfBirth" : "2003-07-02",
  "noHp" : "081234567890",
  "noTelp" : "4255551212",
  "email" : "mohaliakbarrafsanjani@gmail.com",
  "position" : "Vice Precident"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "guest id",
    "guestType" : "local",
    "name" : "Ali Akbar Rafsanjani",
    "gender" : "Laki-laki",
    "placeOfBirth" : "Jember",
    "dateOfBirth" : "2003-07-02",
    "noHp" : "081234567890",
    "noTelp" : "4255551212",
    "email" : "mohaliakbarrafsanjani@gmail.com",
    "position" : "Vice Precident"
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Data not valid, ...."
}
```

## Get Guest
Endpoint : GET/api/guests/{idGuest}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "id" : "guest id",
    "guestType" : "local",
    "name" : "Ali Akbar Rafsanjani",
    "gender" : "Laki-laki",
    "placeOfBirth" : "Jember",
    "dateOfBirth" : "2003-07-02",
    "noHp" : "081234567890",
    "noTelp" : "4255551212",
    "email" : "mohaliakbarrafsanjani@gmail.com",
    "position" : "Vice Precident"
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Data not found"
}
```

## List Guest
Endpoint : GET/api/guests

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :
```json
{
  "data" : [
    {
      "id" : "guest id",
      "guestType" : "local",
      "name" : "Ali Akbar Rafsanjani",
      "gender" : "Laki-laki",
      "placeOfBirth" : "Jember",
      "dateOfBirth" : "2003-07-02",
      "noHp" : "081234567890",
      "noTelp" : "4255551212",
      "email" : "mohaliakbarrafsanjani@gmail.com",
      "position" : "Vice Precident"
    }
  ]
}
```

Response Body (Failed) :
```json
{
  "data" : "Unauthorized"
}
```

## Search Guest
Endpoint : GET/api/guest
Query Param :
- name : String, visitor name, using like query, optional
- page : Integer, start form 0, default 0
- size : Integer, default 10

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body :
```json
{
  "data" : [
    {
      "id" : "guest id",
      "guestType" : "local",
      "name" : "Ali Akbar Rafsanjani",
      "gender" : "Laki-laki",
      "placeOfBirth" : "Jember",
      "dateOfBirth" : "2003-07-02",
      "noHp" : "081234567890",
      "noTelp" : "4255551212",
      "email" : "mohaliakbarrafsanjani@gmail.com",
      "position" : "Vice Precident"
    }
  ]
}
```
Response Body (Failed) :
```json
{
  "data" : "Unauthorized"
}
```

## Remove Guest
Endpoint : DELETE/api/guests/{idGuest}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :
```json
{
  "data" : "Ok"
}
```

Response Body (Failed) :
```json
{
  "data" : "Failed"
}
```