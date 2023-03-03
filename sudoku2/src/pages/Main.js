import logo from "../media/logo.png";
import Grid from "../components/Grid";

function Main() {
  let appName = "Sudoku Nation";

  return (
    <div id="playground">
      <aside className="playground-aside">
        <div>
          <img className="logo" src={logo} alt={appName + " Logo"} />
          <span>
            <button className="btn small-btn green">Easy</button>
            <button className="btn small-btn yellow">Medium</button>
            <button className="btn small-btn red selected">Hard</button>
            <button className="btn small-btn purple">Alien</button>
          </span>
          <p>
            Every Sudoku has a unique solution that can be reached logically.
            Enter numbers into the blank spaces so that each row, column and 3x3
            box contains the numbers 1 to 9 without repeats.
          </p>
        </div>
        <p>Made by Shivansh Anand</p>
      </aside>
      <main className="playground-main">
        <Grid />
      </main>
    </div>
  );
}

export default Main;
