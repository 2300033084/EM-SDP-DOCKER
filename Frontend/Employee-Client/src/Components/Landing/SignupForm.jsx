import React, { useState } from "react";
import HomeNavbar from "../NavBars/HomeNavbar";
import "../css/SignupForm.css";

const SignupForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    org: "", // Organization field for Manager
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }

    try {
      // FIX: Changed from hardcoded URL to relative path
      const response = await fetch("/manager/addManager", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: formData.name,
          org: formData.org,
          email: formData.email,
          // WARNING: Password is sent/stored in plaintext - this is a major security flaw.
          password: formData.password, 
        }),
      });

      if (response.ok) {
        setSuccess("Manager account created successfully! Please log in.");
        setFormData({ name: "", org: "", email: "", password: "", confirmPassword: "" });
      } else {
        // Fetch detailed error message if available, otherwise use default message
        const errorText = await response.text();
        setError(errorText || "Signup failed. A manager with this email or organization may already exist. Try again.");
      }
    } catch (error) {
      // This catches true network/CORS errors
      console.error("Signup network error:", error);
      setError("Network error. Could not connect to the server.");
    }
  };

  return (
    <div>
      <HomeNavbar />
      <div className="container-signup">
        <div className="card-signup">
          <a className="signup">Sign Up as Manager</a>

          <form onSubmit={handleSubmit}>
            <div className="inputBox-signup" style={{ marginBottom: "20px" }}>
              <input type="text" name="name" value={formData.name} onChange={handleChange} required />
              <span>Full Name</span>
            </div>
            <div className="inputBox-signup" style={{ marginBottom: "20px" }}>
              <input type="text" name="org" value={formData.org} onChange={handleChange} required />
              <span>Organization</span>
            </div>
            <div className="inputBox-signup" style={{ marginBottom: "20px" }}>
              <input type="email" name="email" value={formData.email} onChange={handleChange} required />
              <span>Email</span>
            </div>
            <div className="inputBox-signup" style={{ marginBottom: "20px" }}>
              <input type="password" name="password" value={formData.password} onChange={handleChange} required />
              <span>Password</span>
            </div>
            <div className="inputBox-signup" style={{ marginBottom: "20px" }}>
              <input
                type="password"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
              />
              <span>Confirm Password</span>
            </div>

            {error && <p style={{ color: "red" }}>{error}</p>}
            {success && <p style={{ color: "green" }}>{success}</p>}

            <button className="enter-signup" type="submit" style={{ marginLeft: "50%", translate: "-50%" }}>
              Sign Up
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignupForm;