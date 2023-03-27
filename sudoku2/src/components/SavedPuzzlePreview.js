import { useState } from "react";
import trash from "../media/trash-2.svg";
import { Link } from "react-router-dom";
import { getUser } from "../Utils";

import { ToastContainer, toast } from "react-toastify";

function SavedPuzzlePreview(props) {
  let [data, setData] = useState(props.data);
  let [topRows, setTopRows] = useState([
    data.puzzle.puzzle.substring(0, 9).split(""),
    data.puzzle.puzzle.substring(9, 18).split(""),
  ]);

  console.log("*****************");
  console.log(data);

  let numFilledCells = 10;
  let numEmptyCells = 25;
  let pausedAt = 167;

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
        if (resp.ok) toast.success("Deleted !");
        else throw new Error();
      })
      .catch((err) => {
        toast.error("Error. Can't delete right now.");
      });
  };

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
                Filled {numFilledCells} of {numEmptyCells}
              </li>
            )}{" "}
            {props.type === "save" && <li>Paused at {pausedAt}</li>}
            <li>Puzzle #{data?.puzzle?.id}</li>
          </ul>
        </div>
        <a className="overlay" href={`/?puzzleId=${data.puzzle.id}`}></a>
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
  );
}

export default SavedPuzzlePreview;
