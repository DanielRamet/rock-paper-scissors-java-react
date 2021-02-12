import { shallow } from 'enzyme';
import App from './App';
import Content from './components/Content';
import Footer from './components/Footer';
import GameComponent1 from './components/GameComponent1';
import GameComponent2 from './components/GameComponent2';
import Header from './components/Header';

const styles = {
  white: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
  black: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
  topBarHeight: 50,
  footerMenuHeight: 50
};

it("renders without crashing", () => {
  shallow(<App styles={styles} />);
});

it("renders Header", () => {
  shallow(<Header styles={styles} />);
});

it("renders Content", () => {
  shallow(<Content styles={styles} />);
});

it("renders Footer", () => {
  shallow(<Footer styles={styles} />);
});

it("renders Game component 1", () => {
  shallow(<GameComponent1 />);
});

it("renders Game component 2", () => {
  shallow(<GameComponent2 />);
});
