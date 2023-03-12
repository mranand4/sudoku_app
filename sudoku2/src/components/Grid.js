import { useEffect, useState } from "react";

function Grid(props) {
  const MSG = {
    0: "Good Luck ! Here's you puzzle.",
    10: "Game is paused.",
    20: "Saved ! You can select this level from your account to continue anytime !",
    21: "Bookmarked as favourites !",
    30: "You've made some errors.",
  };

  let [duration, setDuration] = useState("00:00");
  let [elapsed, setElapsed] = useState(0);
  let [msgCode, setMsgCode] = useState(0);
  let [errRow, setErrRow] = useState(-1);
  let [errCol, setErrCol] = useState(-1);
  let [errBox, setErrBox] = useState(false);

  let [board, setBoard] = useState([]);
  let [solution, setSolution] = useState([]);
  let [ogBoard, setOgBoard] = useState([]);

  /**
   * https://medium.com/programming-essentials/how-to-create-a-digital-clock-with-react-hooks-aa30f76cfe3f
   */
  useEffect(() => {
    fetch("http://localhost:8080/api/sudoku/" + props.code)
      .then((resp) => resp.json())
      .then((data) => {
        ogBoard = createGridFromStr(data.puzzle);
        solution = createGridFromStr(data.solution);
        console.log(createGridFromStr(data.solution));
        setBoard(ogBoard);
        setOgBoard(ogBoard);
        setSolution(solution);
      });

    setElapsed(props.startedAt);

    console.log(elapsed);

    let timeout = setInterval(incrementDuration, 1000);
    return function cleanup() {
      clearInterval(timeout);
    };
  }, [props.code]);

  let createGridFromStr = (str) => {
    let grid = [];
    for (let i = 0; i < 9; i++) {
      let row = str.substring(i * 9, (i + 1) * 9);
      grid.push(row.split("").map((v) => parseInt(v)));
    }
    return grid;
  };

  let incrementDuration = () => {
    setElapsed(Date.now() - props.startedAt);

    let min = parseInt(elapsed / 60);
    if (min < 10) min = "0" + min;

    let sec = parseInt(elapsed % 60);
    if (sec < 10) sec = "0" + sec;

    setDuration(min + ":" + sec);
  };

  let checkErrors = () => {
    setErrRow(-1);
    setErrCol(-1);
    setErrBox(false);
    setMsgCode(30);

    /**
     * Checks for duplicate values in rows and columns
     */
    for (let i = 0; i < board.length; i++) {
      let seenRow = new Set();
      let seenCol = new Set();

      for (let j = 0; j < board.length; j++) {
        if (seenRow.has(board[i][j])) {
          setErrRow(i);
          return;
        }

        if (board[i][j] != 0) seenRow.add(board[i][j]);

        if (seenCol.has(board[j][i])) {
          setErrCol(i);
          return;
        }

        if (board[j][i] != 0) seenCol.add(board[j][i]);
      }
    }

    /**
     * Checks for duplicate values in 3x3 boxes
     *
     * SRC : https://stackoverflow.com/questions/5484629/check-if-sudoku-solution-is-valid
     */
    for (let i = 0; i < 9; i += 3) {
      for (let j = 0; j < 9; j += 3) {
        let seen = new Set();

        for (let k = 0; k < 3; k++) {
          for (let l = 0; l < 3; l++) {
            if (board[i + k][j + l] == 0) continue;

            if (seen.has(board[i + k][j + l])) {
              setErrRow(i);
              setErrCol(j);
              setErrBox(true);
              return;
            }

            seen.add(board[i + k][j + l]);
          }
        }
      }
    }

    console.log(solution);
    console.log(board);

    //no errors
    for (let i = 0; i < board.length; i++) {
      for (let j = 0; j < board.length; j++) {
        if (board[i][j] != 0 && board[i][j] != solution[i][j]) {
          setMsgCode(30);
          return;
        }
      }
    }

    //no errs
    setMsgCode(0);
  };

  /**
   *
   * https://stackoverflow.com/questions/14636536/how-to-check-if-a-variable-is-an-integer-in-javascript
   */
  let isInt = (value) =>
    !isNaN(value) &&
    parseInt(Number(value)) == value &&
    !isNaN(parseInt(value, 10));

  let onCellValueChange = (e, rowIdx, colIdx) => {
    let val = 0;

    if (isInt(e.target.value)) val = parseInt(e.target.value);

    if (val < 10) {
      e.target.style = "font-size: 22px; color: blue";
    } else if (val < 100) {
      e.target.style = "font-size: 18px; color: darkgreen";
    } else if (val < 1000) {
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

  let getCellClassName = (row, col) => {
    if (
      errBox &&
      row >= errRow &&
      row < errRow + 3 &&
      col >= errCol &&
      col < errCol + 3
    ) {
      return "err-cell";
    }

    if (!errBox && (row == errRow || col == errCol)) {
      return "err-cell";
    }

    return "";
  };

  let save = (e, mode = "save") => {
    let boardId;
    let ogBoard = flattenBoardAsStr(props.board);
    let currBoard = flattenBoardAsStr(board);

    setMsgCode(mode === "save" ? 20 : 21);

    // save
  };

  let pause = (e) => {
    if (e.target.innerHTML.toLowerCase().trim() === "pause") {
      e.target.innerHTML = "Continue";
    } else {
      e.target.innerHTML = "Pause";
    }
    setMsgCode(10);
  };

  let flattenBoardAsStr = (board) => {
    let boardStr = "";
    for (let row of board) {
      boardStr = boardStr + row.join("");
    }
    return boardStr;
  };

  if (ogBoard.length > 0) {
    return (
      <div id="grid">
        <span
          className="msg-container"
          style={{ color: msgCode >= 30 ? "#ff453a" : "#000" }}
        >
          <label>{MSG[msgCode] + duration}</label>
        </span>
        <table id="board">
          <tbody>
            {[0, 1, 2, 3, 4, 5, 6, 7, 8].map((row) => (
              <tr key={row}>
                {[0, 1, 2, 3, 4, 5, 6, 7, 8].map((col) => (
                  <td id={"cell-" + row + col} key={row + col}>
                    <input
                      type="text"
                      value={board[row][col] != 0 ? board[row][col] : ""}
                      disabled={ogBoard[row][col] != 0}
                      onChange={(e) => onCellValueChange(e, row, col)}
                      className={getCellClassName(row, col)}
                    />
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
        <span className="button-container">
          <button className="btn small-btn gray" onClick={onCheckBtnClicked}>
            Complete
          </button>
          <button className="btn small-btn gray" onClick={pause}>
            Pause
          </button>
          <button className="btn small-btn gray" onClick={save}>
            Save
          </button>
          <button
            className="btn small-btn gray"
            onClick={() => save(this, "bookmark")}
          >
            Bookmark
          </button>
        </span>
      </div>
    );
  }

  return <div></div>;
}

export default Grid;
