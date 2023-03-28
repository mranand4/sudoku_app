import { useState } from "react";
import trash from "../media/trash-2.svg";
import { getUser, secondsToHS } from "../Utils";

import { ToastContainer, toast } from "react-toastify";
import { Navigate, useNavigate } from "react-router";

function SavedPuzzlePreview(props) {
  let [data, setData] = useState(props.data);

  let [topRows, setTopRows] = useState([
    data.puzzle.puzzle.substring(0, 9).split(""),
    data.puzzle.puzzle.substring(9, 18).split(""),
  ]);

  let [show, setShow] = useState(true);

  let navigate = useNavigate();

  let ec = 0;
  let iec = 0;
  if (props.type === "save") {
    for (let i = 0; i < data.puzzle.puzzle.length; i++) {
      if (data.puzzle.puzzle.charAt(i) === "0") iec++;
      if (data.puzzle.puzzle.charAt(i) === "0" && data.state.charAt(i) != 0)
        ec++;
    }
  }

  let [emptyCells, setEmptyCells] = useState(ec);
  let [initEmptyCells, setInitEmptyCells] = useState(iec);

  let deleteSave = () => {
    let user = getUser();

    fetch(`http://localhost:8080/api/sudoku/${props.type}`, {
      method: "DELETE",
      body: JSON.stringify({
        userId: user.id,
        puzzleId: data.puzzle.id,
      }),
      headers: new Headers({
        "content-type": "application/json",
        Authorization: "Bearer " + user.jwt,
      }),
    })
      .then((resp) => {
        if (resp.ok) {
          toast.success("Deleted !");
          setShow(false);
        } else throw new Error();
      })
      .catch((err) => {
        toast.error("Error. Can't delete right now.");
      });
  };

  let onTileClicked = () => {
    let state = {
      numMistakes: 0,
      totalTime: 0,
      state: "",
    };

    if (props.type === "save") {
      state = {
        numMistakes: data.numMistakes,
        totalTime: data.elapsedSeconds,
        state: data.state,
      };
    }

    console.log(state);

    navigate(`/?puzzleId=${data.puzzle.id}`, {
      state: state,
    });
  };

  return (
    show && (
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
                          value={
                            topRows[row][col] != "0" ? topRows[row][col] : ""
                          }
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
              {props.type === "save" && (
                <li>
                  {emptyCells} filled, {initEmptyCells - emptyCells} more to go.
                </li>
              )}{" "}
              {props.type === "save" && (
                <li>Paused at {secondsToHS(data.elapsedSeconds)}</li>
              )}
              <li>Puzzle #{data?.puzzle?.id}</li>
            </ul>
          </div>
          <div className="overlay" onClick={onTileClicked}></div>
          <div
            className="delete-btn-container"
            style={{ backgroundImage: `url(${trash})` }}
            onClick={deleteSave}
          ></div>
        </div>
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
      </div>
    )
  );
}

export default SavedPuzzlePreview;
