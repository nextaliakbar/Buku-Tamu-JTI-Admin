# User API Spec

## Register User
Endpoint : POST/api/users

Request Body :
```json
{
  "username" : "aliakbar",
  "password" : "aliakbarrafsanjani_123#",
  "name" : "Ali akbar rafsanjani"
}
```

## Login User
Endpoint : POST/api/auth

Request Body :
```json
{
  "username" : "aliakbar"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 1726622392646 // milis
  }
}
```

## Get User
Endpoint : GET/api/users/current

Request Header :
 - GUEST-BOOK-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "username" : "aliakbarr",
    "name" : "Ali Akbar Rafsanjani"
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Unauthorized"
}
```

## Logout User
Endpoint : DELETE/api/auth/logout

Request Header : 
- GUEST-BOOK-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": "OK"
}
```

Response Body (Failed) :
```json
{
  "data" : "Failed"
}
```
