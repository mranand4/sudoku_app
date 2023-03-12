import logo from "../media/logo.png";
import Grid from "../components/Grid";
import { useEffect, useState } from "react";

function Main() {
  let appName = "Sudoku Nation";
  let [code, setCode] = useState("hard");

  let changeLevel = (e) => {
    let currSelectedLevel = document.querySelector(
      ".playground-aside .selected"
    );
    console.log(currSelectedLevel);
    currSelectedLevel.classList.remove("selected");
    console.log(e.target);
    e.target.classList.add("selected");
    currSelectedLevel = e.target;
    let newCode = e.target.innerText.trim().toLowerCase();
    setCode(newCode);
    console.log(e.target.innerText.trim().toLowerCase());
    console.log(code);
  };

  return (
    <div id="playground">
      <aside className="playground-aside">
        <div>
          <img className="logo" src={logo} alt={appName + " Logo"} />
          <span>
            <button className="btn small-btn green" onClick={changeLevel}>
              Easy
            </button>
            <button className="btn small-btn yellow" onClick={changeLevel}>
              Medium
            </button>
            <button
              className="btn small-btn red selected"
              onClick={changeLevel}
            >
              Hard
            </button>
            <button className="btn small-btn purple" onClick={changeLevel}>
              Random
            </button>
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
        <Grid code={code} startedAt={Date.now()} />
      </main>
    </div>
  );
}

export default Main;
