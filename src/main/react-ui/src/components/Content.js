import React from "react";
import GameComponent1 from "./GameComponent1";
import GameComponent2 from "./GameComponent2";

const Content = ({ styles }) => {
  const contentStyle = {
    paddingTop: styles.topBarHeight + 20,
    paddingRight: 20,
    paddingBottom: styles.footerMenuHeight + 20,
    paddingLeft: 20
  };

  return (
    <div style={contentStyle}>
      <GameComponent2 />
      <GameComponent1 />
    </div>
  );
};

export default Content;