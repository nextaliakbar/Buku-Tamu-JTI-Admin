# Institution API Spec

## Create Institution With Admin
Endpoint : POST/api/admin/guests/{GuestId}/institutions

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "name" : "Politeknik Negeri Jember",
  "noTelp" : "0443048987",
  "noFax" : "0331 082 041"
}
```

Response Body (Success) :
```json
{
    "data" :{
      "id" : "institution id",
      "name" : "Politeknik Negeri Jember",
      "noTelp" : "0443048987",
      "noFax" : "0331 082 041" 
    }
}
```

Response Body (Failed) :
```json
{
  "data" : "Instituotion not valid"
}
```

## Create Institution Without Admin
Endpoint : POST/api/guests/{GuestId}/institutions

Request Body :
```json
{
  "name" : "Politeknik Negeri Jember",
  "noTelp" : "0443048987",
  "noFax" : "0331 082 041"
}
```

Response Body (Success) :
```json
{
    "data" :{
      "id" : "institution id",
      "name" : "Politeknik Negeri Jember",
      "noTelp" : "0443048987",
      "noFax" : "0331 082 041" 
    }
}
```

Response Body (Failed) :
```json
{
  "data" : "Instituotion not valid"
}
```

## Update Intitution
Endpoint : PUT/api/guests/{GuestId}/institutions/{idInstitution}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "name" : "Politeknik Negeri Jember",
  "noTelp" : "0443048987",
  "noFax" : "0331 082 041"
}
```

Response Body (Success) :
```json
{
    "data" :{
      "id" : "institution id",
      "name" : "Politeknik Negeri Jember",
      "noTelp" : "0443048987",
      "noFax" : "0331 082 041" 
    }
}
```

Response Body (Failed) :
```json
{
  "data" : "Instituotion not valid"
}
```

## Get Institution
Endpoint : GET/api/guests/{GuestId}/institutions/{idInstitution}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)


Response Body (Success) :
```json
{
    "data" :{
      "id" : "institution id",
      "name" : "Politeknik Negeri Jember",
      "noTelp" : "0443048987",
      "noFax" : "0331 082 041" 
    }
}
```

Response Body (Failed) :
```json
{
  "data" : "Unauthorized"
}
```

## Remove Intitution
Endpoint : DELETE/api/guest/{GuestId}/instituions/{idInstitution}

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