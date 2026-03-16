import React, { useEffect, useState } from "react";

function Employees() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9090/employees")
      .then((res) => res.json())
      .then((data) => {
        setEmployees(data);
      })
      .catch((error) => {
        console.error("Error fetching employees:", error);
      });
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Employees List</h2>

      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
          </tr>
        </thead>

        <tbody>
          {employees.length > 0 ? (
            employees.map((emp) => (
              <tr key={emp.id}>
                <td>{emp.id}</td>
                <td>{emp.name}</td>
                <td>{emp.email}</td>
                <td>{emp.department}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No Employees Found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Employees;