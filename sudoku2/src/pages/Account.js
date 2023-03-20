import SavedPuzzlePreview from "../components/SavedPuzzlePreview";

function Account() {
  return (
    <div>
      <div className="card saved-puzzles-container">
        <h2>Saved Puzzles</h2>
        <div class="container">
          {[0, 1, 2, 3, 4, 5, 6].map((i) => (
            <SavedPuzzlePreview />
          ))}
        </div>
      </div>

      <div className="card saved-puzzles-container">
        <h2>Bookmarked Puzzles</h2>
        <div class="container">
          {[0, 1, 2, 3, 4, 5, 6].map((i) => (
            <SavedPuzzlePreview />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Account;
