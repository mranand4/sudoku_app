import logo from "./media/logo.png";
import "./App.css";
import Main from "./pages/Main";
import Account from "./pages/Account";

function App() {
  return (
    <div className="app">
      <nav class="global-nav">
        <img src={logo} alt="Logo" />
        <span>
          <button className="btn small-btn green">Stats</button>
          <button className="btn small-btn green">Account</button>
          <button className="btn small-btn green">Log In</button>
          <button className="btn small-btn green">Settings</button>
          <button className="btn small-btn green">Menu</button>
        </span>
      </nav>
      <Account />
    </div>
  );
}

export default App;
