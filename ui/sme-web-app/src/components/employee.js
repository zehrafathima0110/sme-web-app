// src/components/Employee.js
import React, { useState, useEffect } from "react";
import api from "../service/apis";

const Employee = () => {
  const [employees, setEmployees] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [designation, setDesignation] = useState("");
  const [ctc, setCtc] = useState("");
  const [editEmailId, setEditEmailId] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = () => {
    api
      .get("/employee/allEmployees")
      .then((response) => setEmployees(response.data))
      .catch((error) => console.error("Error fetching employees:", error));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const employeeData = { name, email, designation, ctc };
    if (email == "") {
      return;
    }
    if (editEmailId) {
      api
        .put("/employee/updateEmployee/" + editEmailId, employeeData)
        .then(() => {
          fetchEmployees();
          setName("");
          setEmail("");
          setDesignation("");
          setCtc("");
          setEditEmailId(null);
          window.alert("Employee details updated successfully!");
        })
        .catch((error) => {
          console.error("Error updating employee:", error);
          window.alert("Failed to update employee");
        });
    } else {
      api
        .post("/employee/createEmployee", employeeData)
        .then(() => {
          fetchEmployees();
          setName("");
          setEmail("");
          setDesignation("");
          setCtc("");
          window.alert("Employee created successfully");
        })
        .catch((error) => {
          console.error("Error creating employee:", error);
          window.alert("Failed to create employee");
        });
    }
  };

  const handleEdit = (employee) => {
    setName(employee.name);
    setEmail(employee.email);
    setDesignation(employee.designation);
    setCtc(employee.ctc);
    setEditEmailId(employee.email);
  };

  const handleDelete = (id) => {
    api
      .delete("/employee/deleteEmployee/" + id)
      .then(() => {
        fetchEmployees();
        window.alert("Employee deleted successfully");
      })
      .catch((error) => {
        console.error("Error deleting employee:", error);
        window.alert("Failed to delete employee");
      });
  };
  const handleClear = () => {
    setName("");
    setEmail("");
    setDesignation("");
    setCtc("");
    setEditEmailId(null);
  };

  return (
    <div>
      <h2>Employees</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="employeeName">Name</label>
          <input
            type="text"
            className="form-control"
            id="employeeName"
            placeholder="Enter name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="employeeEmail">Email</label>
          <input
            type="email"
            className="form-control"
            id="employeeEmail"
            placeholder="Enter email"
            value={email}
            disabled={editEmailId}
            onChange={(e) => setEmail(e.target.value)}
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="employeeDesignation">Designation</label>
          <input
            type="text"
            className="form-control"
            id="employeeDesignation"
            placeholder="Enter designation"
            value={designation}
            onChange={(e) => setDesignation(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="employeeCtc">CTC</label>
          <input
            type="number"
            className="form-control"
            id="employeeCtc"
            placeholder="Enter CTC"
            value={ctc}
            onChange={(e) => setCtc(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-2 me-2">
          {editEmailId ? "Update" : "Create"} Employee
        </button>
        <button
          type="button"
          className="btn btn-secondary mt-2 me-2"
          onClick={handleClear}
        >
          Clear
        </button>
      </form>
      <h3 className="mt-3">List of Employees</h3>

      <div style={{ maxHeight: "40vh", overflowY: "auto" }}>
        {employees.length > 0 ? (
          <table className="table">
            <thead>
              <tr>
                <th scope="col">S.No</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Designation</th>
                <th scope="col">CTC</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              {employees.map((employee, index) => (
                <tr key={employee.email}>
                  <th scope="row">{index + 1}</th>
                  <td>{employee.name}</td>
                  <td>{employee.email}</td>
                  <td>{employee.designation}</td>
                  <td>{employee.ctc}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-info mr-2 me-1"
                      onClick={() => handleEdit(employee)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-danger me-1"
                      onClick={() => handleDelete(employee.email)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No employees available</p>
        )}
      </div>
    </div>
  );
};

export default Employee;
