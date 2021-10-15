package tn.sip.ams2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.sip.ams2.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role); 

}
