import { useState } from "react";

function SavedPuzzlePreview() {
  let [topRows, setTopRows] = useState([
    [1, 0, 0, 1, 2, 3, 0, 0, 1],
    [1, 0, 0, 5, 2, 6, 0, 7, 9],
  ]);
  let numFilledCells = 10;
  let numEmptyCells = 25;
  let pausedAt = 167;
  let puzzleId = 1;

  // setTopRows([[1, 0, 0, 1, 2, 3, 0, 0, 1], [1, 0, 0, 5, 2, 36, 0, 7, 9]]);

  return (
    <div className="saved-puzzle-container">
      <div className="saved-puzzle-preview-container">
        <div className="pseudo-img-container">
          <table>
            <tbody>
              {[0, 1].map((row) => (
                <tr key={row}>
                  {[0, 1, 2, 3, 4, 5, 6, 7, 8].map((col) => (
                    <td id={"cell-" + row + col} key={row + col}>
                      <input
                        type="text"
                        value={topRows[row][col] != 0 ? topRows[row][col] : ""}
                        readOnly
                      />
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
          <div className="overlay"></div>
        </div>
        <div className="info-container">
          <ul>
            <li>
              Filled {numFilledCells} of {numEmptyCells}
            </li>
            <li>Paused at {pausedAt}</li>
            <li>Puzzle #{puzzleId}</li>
          </ul>
        </div>
        <a className="overlay" href="/"></a>
      </div>
      <div>
        <a href="/">Continue</a>
        <a href="">Delete</a>
      </div>
    </div>
  );
}

export default SavedPuzzlePreview;
