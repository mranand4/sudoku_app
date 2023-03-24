import { NavLink } from "react-router-dom";

export function LoggedOutView() {
  return (
    <div className="nav-cta-container">
      <NavLink to="auth">Register</NavLink>
      <NavLink to="auth">Login</NavLink>
    </div>
  );
}

export function LoggedInView() {
  return (
    <div className="nav-cta-container">
      <label>Hello !</label>
      <NavLink to="me">My Statistics</NavLink>
      <NavLink to="me">My Account</NavLink>
      <NavLink to="logout">Logout</NavLink>
    </div>
  );
}
