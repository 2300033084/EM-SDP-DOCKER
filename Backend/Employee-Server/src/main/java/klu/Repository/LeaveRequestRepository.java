package klu.Repository;

import klu.Model.LeaveRequest;
import klu.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);
    boolean existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatus(
            Long employeeId, LocalDate endDate, LocalDate startDate, LeaveStatus status);
}