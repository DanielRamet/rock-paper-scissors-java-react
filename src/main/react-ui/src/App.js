import './App.css';
import GameComponent1 from './components/GameComponent1';
import GameComponent2 from './components/GameComponent2';

function App() {
  return (
    <div id="container">
      <div id="header">
          <h2>Rock, Paper, Scissors: web demo Java+React</h2>
          <h4>Author: Daniel Ramet (2021)</h4>
      </div>
        <GameComponent1 />
        <GameComponent2 />
    </div>
  );
}

export default App;
