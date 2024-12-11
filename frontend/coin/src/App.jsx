import React from 'react';
import './App.css';

function App() {
  return (
    <div>
      <header>
        <h1>Coin Analysis</h1>
      </header>
      <main>
        <section className="coin-info">
          <div className="column column-1">
            {/* Content for column 1 */}
          </div>
          <div className="column column-2">
            <div className="coin-name-dropdown">
              <input type="text" placeholder="Coin name" />
              <button>▼</button>
            </div>
          </div>
          <div className="column column-3">
            {/* Content for column 3 */}
          </div>
        </section>
      </main>
      <footer>
        <p>© 2024 Coin Analysis</p>
      </footer>
    </div>
  );
}

export default App;