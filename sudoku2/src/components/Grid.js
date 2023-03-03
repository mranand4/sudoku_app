function Grid() {
  let data =
    "....5..6....7.35...2......7...6.....4.3..9....86.25.....9...7.4.7.96....5...8..3.".split(
      ""
    );

  let generateGrid = (row, prefixId) => {
    return (
      <tr>
        {row.map((val, idx) => (
          <td>
            <input
              type="text"
              id={"cell-" + prefixId + idx}
              value={val !== "." ? val : null}
              disabled={val !== "."}
            />
          </td>
        ))}
      </tr>
    );
  };

  let generateBoard = () => {
    return (
      <table id="grid">
        {[1, 2, 3, 4, 5, 6, 7, 8, 9].map((val) =>
          generateGrid(data.slice(9 * (val - 1), 9 * val), val)
        )}
      </table>
    );
  };

  return generateBoard();
}

export default Grid;
