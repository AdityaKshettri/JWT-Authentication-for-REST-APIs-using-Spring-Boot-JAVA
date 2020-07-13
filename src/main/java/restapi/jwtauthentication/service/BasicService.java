package restapi.jwtauthentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.jwtauthentication.entity.Basic;
import restapi.jwtauthentication.repository.BasicRepository;

@Service
public class BasicService 
{
	@Autowired
	private BasicRepository basicRepository;
	
	public List<Basic> findAll() {
		return basicRepository.findAll();
	}
	
	public Basic findById(int id) {
		return basicRepository.findById(id);
	}
	
	public void save(Basic basic) {
		basicRepository.save(basic);
	}
	
	public void delete(Basic basic) {
		basicRepository.delete(basic);
	}
}
