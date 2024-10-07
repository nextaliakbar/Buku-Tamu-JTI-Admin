# Need API Spec

## Create Need With Admin
Endpoint : POST/api/guests/{GuestId}/needs

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "title" : "Konsultasi",
  "createdAt" : "2024-09-21T08:00:00;00",
  "description" : "....."
}
```
Response Body (Success) :
```json
{
  "data" : {
    "id" : "need id",
    "title" : "Konsultasi",
    "createdAt" : "2024-09-21T08:00:00;00",
    "description" : "....."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Need not valid"
}
```

## Create Need Without Admin
Endpoint : POST/api/guests/{GuestId}/needs

Request Body : 
```json
{
  "title" : "Konsultasi",
  "createdAt" : "2024-09-24T10:07:20",
  "description" : "....."
}
```
Response Body (Success) :
```json
{
  "data" : {
    "id" : "need id",
    "title" : "Konsultasi",
    "createdAt" : "2024-09-24T10:07:20",
    "description" : "....."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Need not valid"
}
```

## Update Need
Endpoint : PUT/api/guests/{GuestId}/needs/{idNeed}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "title" : "Konsultasi",
  "createdAt" : "2024-09-24T10:07:20",
  "description" : "....."
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "need id",
    "title" : "Konsultasi",
    "createdAt" : "2024-09-24T10:07:20",
    "description" : "....."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Need not valid"
}
```

## Get Need
Endpoint : GET/api/guest/{GuestId}/needs/{idNeed}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "id" : "need id",
    "title" : "Konsultasi",
    "createdAt" : "2024-09-24T10:07:20",
    "description" : "....."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Unauthorized"
}
```

## Remove Need
Endpoint : DELETE/api/guest/{GuestId}/needs/{idNeed}

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