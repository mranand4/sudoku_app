import logo from "./media/logo.png";
import "./App.css";
import Main from "./pages/Main";
import Auth from "./pages/Auth";

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
      <Auth />
    </div>
  );
}

export default App;
