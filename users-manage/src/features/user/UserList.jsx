import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import userApi from "../../api/userApi";

function UserList() {
  const [users, setUsers] = useState([]);
  const [term, setTerm] = useState("");
  const [renderedUser, setRenderedUser] = useState(users);

  useEffect(() => {
    // async function dùng để lập trình bất đồng bộ
    const fetchUsers = async () => {
      let res = await userApi.getUsers();
      console.log(res);

      setUsers(res.data);
      setRenderedUser(res.data);
    };

    fetchUsers();
  
  }, []);

  const handleDelete = async (id) => {
    try {
      console.log("parent : ", id);

      // Gọi API xóa phía server
      await userApi.deleteUser(id);

      // Cập nhật trong state ban đầu
      const newUsers = users.filter((user) => user.id !== id);
      setUsers(newUsers);
      setRenderedUser(newUsers);
    } catch (e) {
      console.log(e);
    }
  };

  const handleSearch = async () => {
    if (!term) {
      setRenderedUser(users);
    }
    let res = await userApi.searchUser(term);
    const newUsers = res.data;
    console.log(res);
    setRenderedUser(newUsers);
  };

  return (
    <div className="container mt-5 mb-5">
      <h2 className="text-center text-uppercase">Danh sách user</h2>

      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
            <Link to={"/users/create"} className="btn btn-warning">
              Tạo user
            </Link>
            <input
                            type="text"
                            id="search"
                            className="form-control w-50"
                            placeholder="Tìm kiếm user"
                            value={term}
                            onKeyPress={(e) => {
                                if (e.key === "Enter") {
                                   handleSearch();
                                }
                            }}
                            onChange={(e)=>setTerm(e.target.value)}
                        />
            
          </div>

          <div className="bg-light p-4">
            <table className="table table-hover">
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Phone</th>
                  <th>Address</th>
                  <th></th>
                </tr>
              </thead>

              <tbody>
              {renderedUser.map((user)=>(
                                  <tr key={user.id}>
                                  <td>{user.id}</td>
                                  <td>{user.name}</td>
                                  <td>{user.email}</td>
                                  <td>{user.phone}</td>
                                  <td>{user.address}</td>
                                  <td>
                                  <Link
                                            to={`/users/${user.id}`}
                                            className="btn btn-success"
                                        >
                                            Xem chi tiết
                                        </Link>
                                      <button onClick={()=>handleDelete(user.id)} className="btn btn-danger">
                                          Xóa
                                      </button>
                                  </td>
                              </tr>
               
                              ))}
              </tbody>
            </table>

            <p className="message d-none"></p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserList;
