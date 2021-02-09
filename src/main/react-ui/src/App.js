import { Component } from 'react';
import './App.css';
import Content from './components/Content';
import Footer from './components/Footer';
import Header from './components/Header';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render(){

    const styles = {
      white: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
      black: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
      topBarHeight: 50,
      footerMenuHeight: 50
    };

    return (
    <div 
      style={{
        backgroundColor: styles.black(0.05),
        minHeight: "100vh",
        position: "relative"
      }}
      >
      <Header styles={styles} />
      <Content styles={styles} />
      <Footer styles={styles} />
    </div>
  );
}
}

export default App;
