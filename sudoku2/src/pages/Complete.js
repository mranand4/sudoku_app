import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import { secondsToHS } from "../Utils";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

import { Line } from "react-chartjs-2";

export default function Complete() {
  let location = useLocation();

  let allSolutions = location.state.allSolutions;

  const totalNumMistakes = allSolutions.reduce(
    (a, { numMistakes }) => a + numMistakes,
    0
  );
  const avgNumMistakes = totalNumMistakes / allSolutions.length || 0;

  const totalTimeTaken = allSolutions.reduce(
    (a, { elapsedSeconds }) => a + elapsedSeconds,
    0
  );
  const avgTimeTaken = totalTimeTaken / allSolutions.length || 0;

  let standingIndicatorStyle = {
    left: `${(location.state.timeTaken / 5400) * 100}%`,
  };

  /**
   * density plot, line histogram, bell curve, gaussian distribution curve, smooth histogram, frequency curve,
   * simple line chart
   */
  ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
  );

  const options = {
    responsive: true,
    tension: 0.4,
    elements: {
      point: {
        radius: 0,
      },
    },
    plugins: {
      legend: {
        display: false,
      },
      title: {
        display: false,
      },
    },
    scales: {
      x: {
        ticks: {
          display: false,
        },
      },
      y: {
        ticks: {
          display: false,
        },
      },
    },
  };

  let labels = [];
  for (let i = 0; i <= 12; i++) {
    labels.push(i * 450);
  }

  let frequency = new Array(labels.length).fill(0);

  for (let s of allSolutions) {
    for (let i = 0; i < frequency.length; i++) {
      if (s.elapsedSeconds <= labels[i]) {
        frequency[i] += 1;
        break;
      }
    }
  }
  const data = {
    labels: labels,
    datasets: [
      {
        data: frequency,
        borderColor: "#1976d2",
      },
    ],
  };

  return (
    <div className="completion-page">
      <div class="container">
        <h2>Congratulations !</h2>
        <p>
          You completed
          <Link to={"/?puzzleId=" + location.state.puzzleId}>
            {" "}
            Puzzle #{location.state.puzzleId}{" "}
          </Link>
          in {secondsToHS(location.state.timeTaken)} and made{" "}
          {location.state.numMistakes} mistakes.
        </p>
        <p>Average stats for a {location.state.difficulty} puzzle :</p>
        <ul>
          <li>Time Taken : {secondsToHS(avgTimeTaken)}</li>
          <li>Mistakes {parseInt(avgNumMistakes)}.</li>
        </ul>
        <div className="chart-container">
          <Line options={options} data={data} />
          <div className="floater" style={standingIndicatorStyle}></div>
          <label className="floater">0 min</label>
          <label className="floater">90 min</label>
        </div>
      </div>
    </div>
  );
}
