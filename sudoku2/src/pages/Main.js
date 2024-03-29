import logo from "../media/logo.png";
import Grid from "../components/Grid";
import { setDifficulty, getDifficulty } from "../Utils";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

function Main() {
  let appName = "Sudoku Nation";
  let [code, setCode] = useState(getDifficulty());
  let location = useLocation();

  useEffect(() => {
    let puzzleId = new URLSearchParams(window.location.search).get("puzzleId");
    if (puzzleId) {
      setCode(puzzleId);
    }
  }, []);

  let changeLevel = (e) => {
    let newCode = e.target.innerText.trim().toLowerCase();
    setCode(newCode);
    setDifficulty(newCode);
  };

  return (
    <div id="playground">
      <aside className="playground-aside">
        <div>
          <img className="logo" src={logo} alt={appName + " Logo"} />
          <span className="btn-group">
            <button
              className={`${code === "easy" ? "selected" : ""}`}
              onClick={changeLevel}
            >
              Easy
            </button>
            <button
              className={`${code === "medium" ? "selected" : ""}`}
              onClick={changeLevel}
            >
              Medium
            </button>
            <button
              className={`${code === "hard" ? "selected" : ""}`}
              onClick={changeLevel}
            >
              Hard
            </button>
            <button
              className={`${code === "random" ? "selected" : ""}`}
              onClick={changeLevel}
            >
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
        <Grid
          code={code}
          totalTime={location?.state?.totalTime ?? 0}
          numMistakes={location?.state?.numMistakes ?? 0}
          state={location?.state?.state ?? ""}
        />
      </main>
    </div>
  );
}

export default Main;
