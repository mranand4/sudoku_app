export default function Complete(props) {
  let allSolutions = [];

  const sumNumMistakes = allSolutions.reduce((a, b) => a.numMistakes + b, 0);
  const avgNumMistakes = sumNumMistakes / allSolutions.length || 0;

  const sumTimeTaken = allSolutions.reduce((a, b) => a.elapsedSeconds + b, 0);
  const avgTimeTaken = sumTimeTaken / allSolutions.length || 0;

  return (
    <div className="completion-page">
      <div class="container">
        <h2>Congratulations !</h2>
        <p>
          You completed Puzzle #{props.puzzleId} in {props.elapsedSeconds} and
          made {props.numMistakes} mistakes.
        </p>
        <p>
          Others who attempted this puzzle, took an average {avgNumMistakes}{" "}
          mins while making {avgNumMistakes} mistakes.
        </p>
      </div>
    </div>
  );
}
