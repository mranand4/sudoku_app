import { useEffect, useRef, useState } from "react";
import { getUser, getFormattedDate, secondsToHS } from "../Utils";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router";

function Grid(props) {
  let [duration, setDuration] = useState("00:00");
  let [msgCode, setMsgCode] = useState(0);
  let [errRow, setErrRow] = useState(-1);
  let [errCol, setErrCol] = useState(-1);
  let [errBox, setErrBox] = useState(false);
  let [initTime, setInitTime] = useState(props.startedAt);
  let [totalTime, setTotalTime] = useState(0);

  let [board, setBoard] = useState([]);
  let [solution, setSolution] = useState([]);
  let [ogBoard, setOgBoard] = useState([]);

  let [puzzleId, setPuzzleId] = useState(-1);

  let [numMisfilledCells, setNumMisfilledCells] = useState(0);

  let [numMistakes, setNumMistakes] = useState(0);

  let [difficulty, setDifficulty] = useState("e");

  let navigate = useNavigate();

  let intervalId = useRef(null);

  const MSG = {
    0: "Good Luck ! Here's you puzzle.",
    1: "Carry on ...",
    10: "Game is paused.",
    20: "Saved ! You can select this level from your account to continue anytime !",
    21: "Bookmarked as favourites !",
    30: "You've made some errors.",
    31: `Something is not quite right in ${numMisfilledCells} of the cells!

    `,
  };

  /**
   * https://medium.com/programming-essentials/how-to-create-a-digital-clock-with-react-hooks-aa30f76cfe3f
   */
  useEffect(() => {
    fetch("http://localhost:8080/api/sudoku/" + props.code)
      .then((resp) => resp.json())
      .then((data) => {
        ogBoard = createGridFromStr(data.puzzle);
        solution = createGridFromStr(data.solution);
        setBoard(ogBoard);
        setOgBoard(ogBoard);
        setSolution(solution);
        setPuzzleId(data.id);
        setDifficulty(data.difficulty);
      });

    startTimer();
  }, [props.code]);

  let startTimer = () => {
    intervalId.current = setInterval(() => {
      let elapsedTimeMs = Math.floor(Date.now() - initTime);
      setDuration(secondsToHS(elapsedTimeMs));
    }, 1000);
  };

  let onPauseBtnClicked = (e) => {
    if (e.target.innerHTML.toLowerCase().trim() === "pause") {
      setTotalTime(totalTime + (Date.now() - initTime));
      clearInterval(intervalId.current);
      intervalId.current = null;
      e.target.innerHTML = "Continue";
    } else {
      setInitTime(Date.now());
      startTimer();
      e.target.innerHTML = "Pause";
    }
    setMsgCode(10);
  };

  let createGridFromStr = (str) => {
    let grid = [];
    for (let i = 0; i < 9; i++) {
      let row = str.substring(i * 9, (i + 1) * 9);
      grid.push(row.split("").map((v) => parseInt(v)));
    }
    return grid;
  };

  let checkErrors = () => {
    setErrRow(-1);
    setErrCol(-1);
    setErrBox(false);
    setMsgCode(1);

    /**
     * Checks for duplicate values in rows and columns
     */
    for (let i = 0; i < board.length; i++) {
      let seenRow = new Set();
      let seenCol = new Set();

      for (let j = 0; j < board.length; j++) {
        if (seenRow.has(board[i][j])) {
          setErrRow(i);
          setNumMistakes(numMistakes + 1);
          return;
        }

        if (board[i][j] != 0) seenRow.add(board[i][j]);

        if (seenCol.has(board[j][i])) {
          setErrCol(i);
          setNumMistakes(numMistakes + 1);
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
              setNumMistakes(numMistakes + 1);
              return;
            }

            seen.add(board[i + k][j + l]);
          }
        }
      }
    }

    let mNumMisfilledCells = 0;
    let hasNoEmptyCell = true;

    for (let i = 0; i < board.length; i++) {
      for (let j = 0; j < board.length; j++) {
        if (
          board[i][j] != 0 &&
          board[i][j] < 10 &&
          board[i][j] != solution[i][j]
        ) {
          mNumMisfilledCells++;
        }
        if (board[i][j] == 0) {
          hasNoEmptyCell = false;
        }
      }
    }

    if (mNumMisfilledCells > 0) {
      setNumMisfilledCells(mNumMisfilledCells);
      setNumMistakes(numMistakes + 1);
      setMsgCode(31);
      return;
    }

    //completed successfully !
    if (hasNoEmptyCell) {
      save(null, "solved");
    }

    save(null, "solved");
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
      e.target.style = "font-size: 22px; color: #1976d2";
    } else if (val < 100) {
      e.target.style = "font-size: 18px; color: #388E3C";
    } else if (val < 1000) {
      e.target.style = "font-size: 16px; color: #388E3C";
    } else {
      e.target.style = "font-size: 14px; color: #388E3C";
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

  let save = (e, mode) => {
    console.log(mode);

    let user = getUser();

    if (!user && (mode == "save" || mode == "boomark")) {
      toast.error(`You need to be logged in to ${mode} a puzzle.`);
      return;
    }

    let body = {
      userId: user.id,
      puzzleId: puzzleId,
      createdAt: getFormattedDate(new Date()),
      elapsedSeconds: totalTime,
      numMistakes: numMistakes,
    };

    if (mode == "save") {
      body["state"] = flattenBoardAsStr(board);
    }

    fetch(`http://localhost:8080/api/sudoku/${mode}`, {
      method: "POST",
      body: JSON.stringify(body),
      headers: new Headers({
        "content-type": "application/json",
        Authorization: "Bearer " + user.jwt,
      }),
    })
      .then((response) => {
        if (response.ok) {
          if (mode == "solved") {
            return response.json().then((data) => {
              navigate("complete", {
                state: {
                  timeTaken: totalTime,
                  numMistakes: numMistakes,
                  allSolutions: data,
                  puzzleId: puzzleId,
                  difficulty: difficulty,
                },
              });
            });
          }
          toast.success(
            `${mode.charAt(0).toUpperCase() + mode.slice(1)}ed successfully !`
          );
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        toast.error(`Error. Please try again later or try logging again.`);
      });
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
        <ToastContainer
          position="bottom-center"
          autoClose={5000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover={false}
          theme="colored"
        />
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
          <button className="btn action" onClick={onCheckBtnClicked}>
            Complete
          </button>
          <button className="btn action" onClick={onPauseBtnClicked}>
            Pause
          </button>
          <button className="btn action" onClick={() => save(this, "save")}>
            Save
          </button>
          <button className="btn action" onClick={() => save(this, "bookmark")}>
            Bookmark
          </button>
        </span>
      </div>
    );
  }

  return <div></div>;
}

export default Grid;
