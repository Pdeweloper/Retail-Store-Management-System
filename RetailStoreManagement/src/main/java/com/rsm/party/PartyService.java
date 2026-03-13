package com.rsm.party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

	@Autowired
	private PartyRepository partyrepo;
	
	public Party save(Party party)
	{
		return partyrepo.save(party);
	}
	
	public List<Party> getAll() {
        return partyrepo.findAll();
    }

    public Party getById(Long id) {
        return partyrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found"));
    }

    public Party update(Long id, Party updatedParty) {
        Party existing = getById(id);

        existing.setPartyName(updatedParty.getPartyName());
        existing.setContactNumber(updatedParty.getContactNumber());
        existing.setTaxIdentifier(updatedParty.getTaxIdentifier());
        existing.setAddress(updatedParty.getAddress());
        existing.setPartyType(updatedParty.getPartyType());

        return partyrepo.save(existing);
    }

    public void delete(Long id) {
    	partyrepo.deleteById(id);
    }
}
