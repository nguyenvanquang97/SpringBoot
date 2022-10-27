import React, { useEffect, useState } from "react";
import "./User.css";
function User() {
  const [data, setData] = useState([]);
  const [temp,setTemp]=useState(0);
  useEffect(() => {
    fetch(`https://api.github.com/users`)
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => console.log(error));
  }, [temp]);
  const [userName, setUserName] = useState("");
  const clone = data.slice();
  const search = () => {
    if (userName === "") {
      setData(clone);
      return;
    }
    const findList = [];
    for (let item of clone) {
      if (item.login.indexOf(userName) > -1) {
        findList.push(item);
      }
    }

    setData(findList);
    setUserName("");
  };
  const allUser=()=>{
    setTemp(temp+1);

  }
  return (
    <div className="container">
      <h3>Search Github Profile by Username</h3>
      <div className="input">
        <div class="input-group mb-3">
        <button
            onClick={allUser}
            class="btn btn-outline-secondary"
            type="button"
          >
            All Users
          </button>
          <input
            type="text"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            class="form-control"
            placeholder="Enter your name"
            width="500"
          />
          <button
            onClick={search}
            class="btn btn-outline-secondary"
            type="button"
          >
            Search
          </button>
           
        </div>
      </div>

      <table class="table table-striped table-dark text-white table-hover">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Avatar</th>
            <th scope="col">Name</th>
            <th scope="col">Link github</th>
            <th scope="col">Link repo</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr>
              <th scope="row">{item.id}</th>
              <td>
                <div className="avatar">
                  <img
                    src={item.avatar_url}
                    alt=""
                    class="rounded-circle"
                    width="30"
                  />
                </div>
              </td>
              <td>{item.login}</td>
              <td>{item.html_url}</td>
              <td>{item.repos_url}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default User;
