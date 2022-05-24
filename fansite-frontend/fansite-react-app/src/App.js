import FetchDrivers from "./components/FetchDrivers";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NotFound from "./components/NotFound";
import NavBar from "./components/NavBar";
import Favorites from "./components/Favorites";
import About from "./components/About";

function App() {
  return (
    <Router>
      <NavBar />
        <Switch>
          <Route exact path="/">
            <FetchDrivers />
          </Route>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/favorites">
            <Favorites />
          </Route>
          <Route>
            <NotFound />
          </Route>
        </Switch>
      </Router>
  );
}

export default App;