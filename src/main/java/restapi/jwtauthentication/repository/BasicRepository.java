package restapi.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restapi.jwtauthentication.entity.Basic;

@Repository
public interface BasicRepository extends JpaRepository<Basic, Integer> 
{
	public Basic findById(int id);
}
