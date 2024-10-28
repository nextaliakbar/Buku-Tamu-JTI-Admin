package com.webapps.service;

import com.webapps.api.repository.AddressRepository;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.InstitutionRepository;
import com.webapps.api.repository.NeedRepository;
import com.webapps.model.ModelGuestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GuestDetailService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private NeedRepository needRepository;

    public Page<ModelGuestDetail> getAll() {
        return null;
    }
}
