import { useEffect, useState } from "react";

let data =
  "....5..6....7.35...2......7...6.....4.3..9....86.25.....9...7.4.7.96....5...8..3.".split(
    ""
  );

let board2 = [];
for (let i = 1; i <= 9; i++) {
  board2.push(data.slice(9 * (i - 1), 9 * i));
}

function Grid(props) {
  let [board, setBoard] = useState(board2);
  let [msg, setMsg] = useState("Good Luck ! Here's you puzzle.");
  let [errRow, setErrRow] = useState(-1);
  let [errCol, setErrCol] = useState(-1);

  let checkErrors = () => {
    setErrRow(-1);
    setErrCol(-1);

    for (let i = 0; i < board.length; i++) {
      let seen = new Set();
      let seenCol = new Set();
      for (let j = 0; j < board.length; j++) {
        if (board[i][j] !== "." && seen.has(board[i][j])) {
          setErrRow(i);
        }

        if (board[i][j] !== ".") seen.add(board[i][j]);

        if (board[j][i] !== "." && seenCol.has(board[j][i])) {
          setErrCol(i);
        }

        if (board[j][i] !== ".") seenCol.add(board[j][i]);
      }
    }

    // square checking
  };

  let onCellValueChange = (e, rowIdx, colIdx) => {
    let val = e.target.value + "";

    let valLen = val.length;

    if (valLen <= 1) {
      e.target.style = "font-size: 22px; color: blue";
    } else if (valLen == 2) {
      e.target.style = "font-size: 18px; color: darkgreen";
    } else if (valLen == 3) {
      e.target.style = "font-size: 16px; color: darkgreen";
    } else {
      e.target.style = "font-size: 14px; color: darkgreen";
    }

    let newBoard = [];

    for (let row of board) {
      let newRow = [];
      for (let i = 0; i < 9; i++) {
        newRow.push(row[i]);
      }
      newBoard.push(newRow);
    }

    newBoard[rowIdx][colIdx] = val;

    setBoard(newBoard);
  };

  let onCheckBtnClicked = () => {
    checkErrors();
  };

  return (
    <div id="grid">
      <span className="msg-container">
        <label>{msg}</label>
      </span>
      <table id="board">
        <tbody>
          {[0, 1, 2, 3, 4, 5, 6, 7, 8].map((row) => (
            <tr key={row}>
              {[0, 1, 2, 3, 4, 5, 6, 7, 8].map((col) => (
                <td id={"cell-" + row + col} key={row + col}>
                  <input
                    type="text"
                    value={board[row][col] !== "." ? board[row][col] : ""}
                    disabled={board2[row][col] !== "."}
                    onChange={(e) => onCellValueChange(e, row, col)}
                    className={row == errRow || col == errCol ? "err-cell" : ""}
                  />
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <span className="button-container">
        <button className="btn small-btn gray" onClick={onCheckBtnClicked}>
          Check
        </button>
        <button className="btn small-btn gray">Pause</button>
        <button className="btn small-btn gray">Save</button>
        <button className="btn small-btn gray">Bookmark</button>
      </span>
    </div>
  );
}

export default Grid;
