import React, { useState, useEffect } from "react";
import api from "../service/apis";

const Vendor = () => {
  const [vendors, setVendors] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [upi, setUpi] = useState("");
  const [editEmailId, setEditEmailId] = useState(null);
  const [selectedVendors, setSelectedVendors] = useState([]);

  useEffect(() => {
    fetchVendors();
  }, []);

  const fetchVendors = () => {
    api
      .get("/vendor/allVendors")
      .then((response) => setVendors(response.data))
      .catch((error) => console.error("Error fetching vendors:", error));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const vendorData = { name, email, upi };

    if (editEmailId) {
      api
        .put("/vendor/updateVendor/" + editEmailId, vendorData)
        .then(() => {
          fetchVendors();
          resetForm();

          window.alert("Vendor updated successfully");
        })
        .catch((error) => {
          console.error("Error updating vendor:", error);
          window.alert("Failed to update vendor");
        });
    } else {
      api
        .post("/vendor/createVendor", vendorData)
        .then(() => {
          fetchVendors();
          resetForm();
          window.alert("Vendor created successfully!");
        })
        .catch((error) => {
          console.error("Error creating vendor:", error);
          window.alert("Vendor creation failed");
        });
    }
  };

  const handleEdit = (vendor) => {
    setName(vendor.name);
    setEmail(vendor.email);
    setUpi(vendor.upi);
    setEditEmailId(vendor.email);
  };

  const handleDelete = (id) => {
    api
      .delete("/vendor/deleteVendor/" + id)
      .then(() => {
        fetchVendors();
        window.alert("Vendor deleted successfully");
      })
      .catch((error) => {
        console.error("Error deleting vendor:", error);
      });
  };

  const handleSendEmails = (vendorEmail) => {
    const selectedVendorDetails = vendors.filter(
      (vendor) => vendor.email === vendorEmail
    );

    api
      .post("/sendMail", selectedVendorDetails)
      .then((response) => {
        window.alert(`Email sent successfully to ${vendorEmail}!`);
      })
      .catch((error) => {
        window.alert(`Failed to send email to ${vendorEmail}.`);
        console.error("Error sending email:", error);
      });
  };

  const resetForm = () => {
    setName("");
    setEmail("");
    setUpi("");
    setEditEmailId(null);
  };

  return (
    <div>
      <h2>Vendors</h2>
      {/* {successMessage && <p className="text-success">{successMessage}</p>}
            {errorMessage && <p className="text-danger">{errorMessage}</p>} */}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="vendorName">Name</label>
          <input
            type="text"
            className="form-control"
            id="vendorName"
            placeholder="Enter name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="vendorEmail">Email</label>
          <input
            type="email"
            className="form-control"
            id="vendorEmail"
            placeholder="Enter email"
            value={email}
            disabled={editEmailId}
            onChange={(e) => setEmail(e.target.value)}
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="vendorUpi">UPI ID</label>
          <input
            type="text"
            className="form-control"
            id="vendorUpi"
            placeholder="Enter UPI ID"
            value={upi}
            onChange={(e) => setUpi(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-2 me-2">
          {editEmailId ? "Update" : "Create"} Vendor
        </button>
        <button
          type="button"
          className="btn btn-secondary mt-2"
          onClick={resetForm}
        >
          Clear
        </button>
      </form>

      <h3 className="mt-3">List of Vendors</h3>
      <div className="mt-3" style={{ maxHeight: "50vh", overflowY: "auto" }}>
        {vendors.length > 0 ? (
          <table className="table">
            <thead>
              <tr>
                <th scope="col">S.No</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">UPI</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              {vendors.map((vendor, index) => (
                <tr key={vendor.email}>
                  <th scope="row">{index + 1}</th>
                  <td>{vendor.name}</td>
                  <td>{vendor.email}</td>
                  <td>{vendor.upi}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-info mr-2 me-1"
                      onClick={() => handleEdit(vendor)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-danger me-1"
                      onClick={() => handleDelete(vendor.email)}
                    >
                      Delete
                    </button>
                    <button
                      className="btn btn-sm btn-info ml-2"
                      onClick={() => handleSendEmails(vendor.email)}
                    >
                      Send Email
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No vendors available</p>
        )}
      </div>
    </div>
  );
};

export default Vendor;
