import SavedPuzzlePreview from "../components/SavedPuzzlePreview";
import DetailedStatsViewer from "../components/DetailedStatsViewer";
import { useEffect, useState } from "react";
import { getUser } from "../Utils";

function Me() {
  let [bookmarks, setBookmarks] = useState([]);
  let [saves, setSaves] = useState([]);
  let [solved, setSolved] = useState([]);

  useEffect(() => {
    let user = getUser();

    fetch(`http://localhost:8080/api/user/${user?.id}`, {
      headers: new Headers({
        "content-type": "application/json",
        Authorization: "Bearer " + user.jwt,
      }),
    })
      .then((resp) => {
        if (resp.ok) return resp.json();
        else throw new Error();
      })
      .then((data) => {
        data.bookmarks.sort(descSort);
        data.saves.sort(descSort);
        data.solved.sort(descSort);
        setBookmarks(data.bookmarks);
        setSaves(data.saves);
        setSolved(data.solved);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  let descSort = (a, b) => {
    return b?.createdAt.localeCompare(a?.createdAt);
  };

  return (
    <div className="me-container">
      <h2>Stats</h2>
      <DetailedStatsViewer solved={solved} />
      <div className="card saved-puzzles-container">
        <h2>Saved Puzzles</h2>
        <div class="container">
          {saves.map((i) => (
            <SavedPuzzlePreview data={i} type="save" />
          ))}
        </div>
      </div>

      <div className="card saved-puzzles-container">
        <h2>Bookmarked Puzzles</h2>
        <div class="container">
          {bookmarks.map((i) => (
            <SavedPuzzlePreview data={i} type="bookmark" />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Me;
