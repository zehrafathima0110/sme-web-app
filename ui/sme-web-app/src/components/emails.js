import React, { useState, useEffect } from "react";
import api from "../service/apis";

const SendEmail = () => {
  const [vendors, setVendors] = useState([]);
  const [selectedVendors, setSelectedVendors] = useState([]);
  const [emails, setEmails] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    api
      .get("/vendor/allVendors")
      .then((response) => {
        setVendors(response.data);
      })
      .catch((error) => {
        console.error("Error fetching vendors:", error);
      });
  }, []);

  const getAllEmails = () => {
    api
      .get("/allEmails")
      .then((response) => {
        setEmails(response.data);
      })
      .catch((error) => {
        console.error("Error fetching emails:", error);
      });
  };
  useEffect(() => {
    getAllEmails();
  }, []);
  const handleCheckboxChange = (vendorEmail) => {
    setSelectedVendors((prevSelected) => {
      if (prevSelected.includes(vendorEmail)) {
        return prevSelected.filter((email) => email !== vendorEmail);
      } else {
        return [...prevSelected, vendorEmail];
      }
    });
  };
  const handleSelectAll = (e) => {
    if (e.target.checked) {
      setSelectedVendors(vendors.map((vendor) => vendor.email));
    } else {
      setSelectedVendors([]);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (selectedVendors.length == 0) {
      window.alert("Select atleast one vendor to send email");
      return;
    }
    const selectedVendorDetails = vendors.filter((vendor) =>
      selectedVendors.includes(vendor.email)
    );

    api
      .post("/sendMail", selectedVendorDetails)
      .then((response) => {
        window.alert("Emails sent successfully to selected vendors!");
        setSelectedVendors([]);
        setErrorMessage("");
        getAllEmails();
      })
      .catch((error) => {
        setErrorMessage("Failed to send emails.");
        setSuccessMessage("");
        console.error("Error sending emails:", error);
      });
  };

  return (
    <div>
      <h2>Select Vendors to Email</h2>

      {vendors.length > 0 ? (
        <>
          <div
            className="table-wrapper"
            style={{ maxHeight: "45vh", overflowY: "auto" }}
          >
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">
                    <input
                      type="checkbox"
                      onChange={handleSelectAll}
                      checked={
                        vendors.length > 0 &&
                        selectedVendors.length === vendors.length
                      }
                    />
                  </th>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                </tr>
              </thead>
              <tbody>
                {vendors.map((vendor) => (
                  <tr key={vendor.email}>
                    <td>
                      <input
                        type="checkbox"
                        id={vendor.email}
                        checked={selectedVendors.includes(vendor.email)}
                        onChange={() => handleCheckboxChange(vendor.email)}
                      />
                    </td>
                    <td>{vendor.name}</td>
                    <td>{vendor.email}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <button className="btn btn-primary mt-3" onClick={handleSubmit}>
            Send Emails
          </button>
        </>
      ) : (
        <p>No Vendors to display</p>
      )}

      <h3 className="mt-3">Sent Emails</h3>
      {emails.length > 0 ? (
        <div style={{ maxHeight: "250px", overflowY: "auto" }}>
          <table className="table">
            <thead>
              <tr>
                <th scope="col">S.No</th>
                <th scope="col">Email</th>
              </tr>
            </thead>
            <tbody>
              {emails.map((mail, index) => (
                <tr key={mail.email}>
                  <td>{index + 1}</td>
                  <td>{mail.mailBody}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <p>No records available</p>
      )}
    </div>
  );
};

export default SendEmail;
