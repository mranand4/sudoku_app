import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { secondsToHS } from "../Utils";

function DetailedStatsViewer(props) {
  let [currentTab, setCurrentTab] = useState("easy");
  let [easyPuzzles, setEasyPuzzles] = useState([]);
  let [mediumPuzzles, setMediumPuzzles] = useState([]);
  let [hardPuzzles, setHardPuzzles] = useState([]);
  let [currentPuzzles, setCurrentPuzzles] = useState([]);

  useEffect(() => {
    let eps = [],
      mps = [],
      hps = [];

    for (let puzzle of props.solved) {
      let difficulty = puzzle.puzzle.difficulty;
      if (difficulty === "e") eps.push(puzzle);
      else if (difficulty === "m") mps.push(puzzle);
      else hps.push(puzzle);
    }

    setEasyPuzzles(eps);
    setMediumPuzzles(mps);
    setHardPuzzles(hps);
    setCurrentPuzzles(eps);
  }, [props.solved]);

  let tabBtnClicked = (e) => {
    let difficulty = e.target.innerText.toLowerCase();
    if (difficulty === "easy") setCurrentPuzzles(easyPuzzles);
    else if (difficulty === "medium") setCurrentPuzzles(mediumPuzzles);
    else setCurrentPuzzles(hardPuzzles);
    setCurrentTab(difficulty);
  };

  return (
    <div className="detailed-stats-container">
      <div className="header">
        <button
          className={currentTab === "easy" ? "selected" : ""}
          onClick={tabBtnClicked}
        >
          Easy
        </button>
        <button
          className={currentTab === "medium" ? "selected" : ""}
          onClick={tabBtnClicked}
        >
          Medium
        </button>
        <button
          className={currentTab === "hard" ? "selected" : ""}
          onClick={tabBtnClicked}
        >
          Hard
        </button>
      </div>
      <div class="body">
        <div className="detailed-stats-summary">
          <ul>
            <li>Total Puzzles Solved : {currentPuzzles.length}</li>
            <li>Avergae Time : 23</li>
            <li>Average Mistakes : 23</li>
          </ul>
        </div>
        <div class="details-stats-list-container">
          <table className="detailed-stats-list">
            <thead>
              <td>Puzzle</td>
              <td>Solved On</td>
              <td>Time Taken</td>
              <td>Mistakes</td>
            </thead>
            {currentPuzzles.map((puzzle) => (
              <tbody>
                <tr>
                  <td>
                    <Link to={`/?puzzleId=${puzzle.puzzle.id}`}>
                      #{puzzle.puzzle.id}
                    </Link>
                  </td>
                  <td>
                    {puzzle.createdAt.substring(
                      0,
                      puzzle.createdAt.indexOf("T")
                    )}
                  </td>
                  <td>{secondsToHS(puzzle.elapsedSeconds)}</td>
                  <td>{puzzle.numMistakes}</td>
                </tr>
              </tbody>
            ))}
          </table>
        </div>
      </div>
    </div>
  );
}

export default DetailedStatsViewer;
