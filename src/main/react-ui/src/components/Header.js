import React from "react";

const Header = ({ styles }) => {
  const headerStyle = {
    position: "fixed",
    top: 0,
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    width: "100%",
    height: styles.topBarHeight,
    backgroundColor: styles.white(),
    borderBottom: `1px solid ${styles.black(0.1)}`,
    fontWeight: "bold",
    padding: "0px 20px",
    boxSizing: "border-box"
  };

  return (
    <div style={headerStyle}>
      <h3>Rock, Paper, Scissors: web demo Java+React</h3>
      <h4>Author: Daniel Ramet (2021)</h4>
    </div>
  );
};

export default Header;