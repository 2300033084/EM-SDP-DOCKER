package klu.Service;

import klu.Model.SuperAdmin;
import klu.Repository.SuperAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {
    @Autowired
    private SuperAdminRepo repo;

    public SuperAdmin findByEmail(String email) {
        return repo.findByEmail(email);
    }

    // You can add other service methods here if needed, like addSuperAdmin, etc.
}