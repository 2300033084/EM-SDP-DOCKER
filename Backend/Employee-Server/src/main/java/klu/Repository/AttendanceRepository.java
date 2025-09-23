package klu.Repository;

import klu.Model.Attendance;
import klu.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
   
    List<Attendance> findByEmployee_Id(Long employeeId); // Note the underscore
    
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByStatus(AttendanceStatus status);
    List<Attendance> findByEmployee_IdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
    Optional<Attendance> findByEmployee_IdAndDate(Long employeeId, LocalDate date);
}