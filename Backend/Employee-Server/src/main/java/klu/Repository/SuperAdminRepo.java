package klu.Repository;

import klu.Model.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
    SuperAdmin findByEmail(String email);
}