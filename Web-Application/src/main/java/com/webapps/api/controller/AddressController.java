package com.webapps.api.controller;
import com.webapps.api.model.AddressResponse;
import com.webapps.api.model.CreateAddressRequest;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/api/guests/{GuestId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddressResponse> createAddressWithoutAdmin(@RequestBody CreateAddressRequest addressRequest,
    @PathVariable("GuestId") String guestId) {
        addressRequest.setGuestId(guestId);
        AddressResponse response = addressService.createAddressWithOutAdmin(addressRequest);
        return WebResponse.<AddressResponse>builder().data(response).build();
    }
}
