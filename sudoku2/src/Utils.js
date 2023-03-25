const KEY_NAME = "sudoku2DifficultyLevel";
const JWT_KEY_NAME = "sudoku2DifficultyLevelCookie";
const storage = window.localStorage;

export function setDifficulty(difficultyCode) {
  storage.setItem(KEY_NAME, difficultyCode);
}

export function getDifficulty() {
  let difficultyCode = storage.getItem(KEY_NAME);
  return difficultyCode ?? "easy";
}

export function setUser(data) {
  storage.setItem(JWT_KEY_NAME, JSON.stringify({ id: data.id, jwt: data.jwt }));
}

export function getUser() {
  return JSON.parse(storage.getItem(JWT_KEY_NAME));
}

function padTo2Digits(num) {
  return num.toString().padStart(2, "0");
}

export function getFormattedDate(date) {
  return (
    [
      date.getFullYear(),
      padTo2Digits(date.getMonth() + 1),
      padTo2Digits(date.getDate()),
    ].join("-") +
    " " +
    [
      padTo2Digits(date.getHours()),
      padTo2Digits(date.getMinutes()),
      padTo2Digits(date.getSeconds()),
    ].join(":")
  );
}

export function secondsToHS(seconds) {
  // 3- Extract minutes:
  return new Date(seconds * 1000).toISOString().slice(14, 19); // HH:MM:SS
}
