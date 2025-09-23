package klu.Repository;

import org.springframework.stereotype.Repository;

import klu.Model.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ManagerRepo extends JpaRepository<Manager,Long>{

	Manager findByEmail(String email);
}
