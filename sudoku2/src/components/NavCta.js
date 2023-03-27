import { NavLink } from "react-router-dom";

export function LoggedOutView() {
  return (
    <div className="nav-cta-container">
      <NavLink to="register">Register</NavLink>
      <NavLink to="login">Login</NavLink>
    </div>
  );
}

export function LoggedInView(props) {
  return (
    <div className="nav-cta-container">
      <label>Hello {props.name} !</label>
      <NavLink to="me">My Statistics</NavLink>
      <NavLink to="me">My Account</NavLink>
      <NavLink to="logout">Logout</NavLink>
    </div>
  );
}
