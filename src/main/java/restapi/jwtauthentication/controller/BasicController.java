package restapi.jwtauthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restapi.jwtauthentication.entity.Basic;
import restapi.jwtauthentication.service.BasicService;

@RestController
@RequestMapping("/basics")
public class BasicController 
{
	@Autowired
	private BasicService basicService;
	
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<Basic> basics = basicService.findAll();
		if(basics.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No basic found");
		}
		return ResponseEntity.ok(basics);
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Basic basicData)
	{
		basicService.save(basicData);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Successfully created");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) 
	{
		Basic theBasic = basicService.findById(id);
		if(theBasic != null) {
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(theBasic);
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Basic form) 
	{
        Basic theBasic = basicService.findById(id);
        if(theBasic != null) {
        	form.setId(id);
        	basicService.save(form);
        	return ResponseEntity
    				.status(HttpStatus.OK)
    				.body("Successfully updated basic at given id");
		}
        return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		Basic theBasic = basicService.findById(id);
        if(theBasic != null) {
        	basicService.delete(theBasic);
            return ResponseEntity
            		.status(HttpStatus.OK)
            		.body("Successfully Deleted");
        }
        return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
	}
}
