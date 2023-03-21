function DetailedStatsViewer() {
  let stats = [
    {
      puzzleId: 1,
      date: "today",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 2,
      date: "yesterday",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },

    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
    {
      puzzleId: 3,
      date: "day before",
      numMistakes: 0,
      timeTaken: "2 min",
    },
  ];

  return (
    <div className="detailed-stats-container">
      <span className="header">
        <button className="selected">Easy</button>
        <button>Medium</button>
        <button>Hard</button>
      </span>
      <div class="body">
        <div className="detailed-stats-summary">
          <ul>
            <li>Total Puzzles Solved : 23</li>
            <li>Avergae Time : 23</li>
            <li>Average Mistakes : 23</li>
            <li>First solved date : 23</li>
            <li>Last : 23</li>
          </ul>
        </div>
        <div class="details-stats-list-container">
          <table className="detailed-stats-list">
            <thead>
              <td>Puzzle Id</td>
              <td>Solved On</td>
              <td>Time Taken</td>
              <td>Mistakes</td>
            </thead>
            {stats.map((i) => (
              <tbody>
                <tr>
                  <td>{i.puzzleId}</td>
                  <td>{i.date}</td>
                  <td>{i.timeTaken}</td>
                  <td>{i.numMistakes}</td>
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
