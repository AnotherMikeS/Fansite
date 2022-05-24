import { Link } from "react-router-dom";

function NavBar() {
  return (
    <nav>
      <table>
        <tbody>
          <tr>
            <Link to="/"><button className="btn btn-success">Home</button></Link>
            <Link to="/about"><button className="btn btn-success">About</button></Link>
            <Link to="/favorites"><button className="btn btn-success">Favorites</button></Link>          
          </tr>
        </tbody>
      </table>
    </nav>
  );
}

export default NavBar;
