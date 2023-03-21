const KEY_NAME = "sudoku2DifficultyLevel";
const storage = window.localStorage;

export function setDifficulty(difficultyCode) {
  storage.setItem(KEY_NAME, difficultyCode);
}

export function getDifficulty() {
  let difficultyCode = storage.getItem(KEY_NAME);
  return difficultyCode ?? "easy";
}
