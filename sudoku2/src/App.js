import logo from "./media/logo.png";
import "./App.css";
import Auth from "./pages/Auth";
import Main from "./pages/Main";
import Complete from "./pages/Complete";
import Me from "./pages/Me";
import { LoggedInView, LoggedOutView } from "./components/NavCta";

import { BrowserRouter, Routes, Route, NavLink } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <header>
        <nav>
          <NavLink className="nav-logo-container" to="/">
            <img src={logo} />
            <h2>Sudoku 2</h2>
          </NavLink>
          <LoggedOutView />
        </nav>
      </header>
      <div className="app">
        <Routes>
          <Route index element={<Main />} />
          <Route path="complete" element={<Complete />} />
          <Route path="register" element={<Auth />} />
          <Route path="login" element={<Auth />} />
          <Route path="me" element={<Me />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
