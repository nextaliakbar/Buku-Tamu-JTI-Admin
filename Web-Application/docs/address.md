# Address API Spec

## Create Address With Admin
Endpoint : POST/api/admin/guests/{GuestId}/addresses

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "country" : "Indonesia",
  "province" : "Jawa Timur",
  "city" : "Jember",
  "subdistrict" : "Bangsalsari",
  "description" : "Jl. Rambutan, ...."
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "address id",
    "country" : "Indonesia",
    "province" : "Jawa Timur",
    "city" : "Jember",
    "subdistrict" : "Bangsalsari",
    "description" : "Jl. Rambutan, ...."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Address not valid, ...."
}
```

## Create Address Without Admin
Endpoint : POST/api/guests/{GuestId}/addresses

Request Body :
```json
{
  "country" : "Indonesia",
  "province" : "Jawa Timur",
  "city" : "Jember",
  "subdistrict" : "Bangsalsari",
  "description" : "Jl. Rambutan, ...."
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "address id",
    "country" : "Indonesia",
    "province" : "Jawa Timur",
    "city" : "Jember",
    "subdistrict" : "Bangsalsari",
    "description" : "Jl. Rambutan, ...."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Address not valid, ...."
}
```

## Update Address
Endpoint : PUT/api/guests/{GuestId}/addresses/{idAddress}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Request Body :
```json
{
  "country" : "Indonesia",
  "province" : "Jawa Timur",
  "city" : "Jember",
  "subdistrict" : "Bangsalsari",
  "description" : "Jl. Rambutan, ...."
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "address id",
    "country" : "Indonesia",
    "province" : "Jawa Timur",
    "city" : "Jember",
    "subdistrict" : "Bangsalsari",
    "description" : "Jl. Rambutan, ...."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Address not valid, ...."
}
```

## Get Address
Endpoint : GET/api/guests/{GuestId}/addresses/{idAddress}

Request Header :
- GUEST-BOOK-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "id" : "address id",
    "country" : "Indonesia",
    "province" : "Jawa Timur",
    "city" : "Jember",
    "subdistrict" : "Bangsalsari",
    "description" : "Jl. Rambutan, ...."
  }
}
```

Response Body (Failed) :
```json
{
  "data" : "Address not found"
}
```

## Remove Address
Endpoint : DELETE/api/guests/{GuestId}/addresses/{idAddress}

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