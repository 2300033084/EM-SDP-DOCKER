import React, { useEffect, useState } from "react";
import axios from "axios";


const SuperAdminDashboard = () => {
  const [employees, setEmployees] = useState([]);

  // Fetch all employees on page load
  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    try {
      const response = await axios.get("http://localhost:8080/employees/allEmployees");
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching employees:", error);
    }
  };

  // Update Employee Status
  const updateStatus = async (id, newStatus) => {
    try {
      await axios.post(`http://localhost:8080/updateEmployeeStatus/${id}`, null, {
        params: { status: newStatus },
      });

      // Update UI after status change
      setEmployees((prevEmployees) =>
        prevEmployees.map((emp) =>
          emp.id === id ? { ...emp, status: newStatus } : emp
        )
      );

      alert(`Employee status updated to ${newStatus}`);
    } catch (error) {
      console.error("Error updating employee status:", error);
    }
  };

  return (
    <div className="super-container mt-4">
      <h2 className="text-center text-primary mb-4">Super Admin Dashboard</h2>
      <div className="table-responsive">
        <table className="table table-bordered table-hover">
          <thead className="table-dark">
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.length === 0 ? (
              <tr>
                <td colSpan="4" className="text-center text-muted">
                  No employees found.
                </td>
              </tr>
            ) : (
              employees.map((employee) => (
                <tr key={employee.id}>
                  <td>{employee.name}</td>
                  <td>{employee.email}</td>
                  <td>
                    <span
                      className={`badge ${
                        employee.status === "ACCEPTED"
                          ? "bg-success"
                          : employee.status === "REJECTED"
                          ? "bg-danger"
                          : "bg-warning text-dark"
                      }`}
                    >
                      {employee.status}
                    </span>
                  </td>
                  <td>
                    {employee.status === "PENDING" ? (
                      <>
                        <button
                          className="btn btn-success btn-sm me-2"
                          onClick={() => updateStatus(employee.id, "ACCEPTED")}
                        >
                          ✔ Accept
                        </button>
                        <button
                          className="btn btn-danger btn-sm"
                          onClick={() => updateStatus(employee.id, "REJECTED")}
                        >
                          ✖ Reject
                        </button>
                      </>
                    ) : (
                      <span className="fw-bold">
                        {employee.status === "ACCEPTED" ? "✔ Approved" : "✖ Rejected"}
                      </span>
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default SuperAdminDashboard;
