package com.rsm.party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

	@Autowired
	private PartyService service;
	
	@PostMapping
    public Party save(@RequestBody Party party) {
        return service.save(party);
    }

    @GetMapping
    public List<Party> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Party getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Party update(@PathVariable Long id,
                        @RequestBody Party party) {
        return service.update(id, party);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
