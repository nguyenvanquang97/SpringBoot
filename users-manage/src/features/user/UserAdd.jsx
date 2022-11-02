
import React, { useState,useEffect} from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import userApi from "../../api/userApi";
import axios from "axios";
import { Link } from "react-router-dom";
function UserAdd() {
    const navigate = useNavigate();
    const [provinces, setProvinces] = useState([]);
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userAddress, setUserAddress] = useState("");
    const [userPhone, setUserPhone] = useState("");
    const [userPassword, setUserPassword] = useState("");
    useEffect(()=>{
        const fetchProvinces = async () => {
            try {
                let res = await axios.get(
                    "https://provinces.open-api.vn/api/p/"
                );
                setProvinces(res.data);
            } catch (error) {
                console.log(error);
            }
        };
        fetchProvinces();
    })
    const handleAddUser=
    async()=>{
       try{
        if (!userName || !userEmail || !userPhone || !userPassword) {
          alert("Bạn cần điền hết mọi thông tin");
          return;
        }
        let newUser = {
            name: userName,
            email: userEmail,
            phone: userPhone,
            address: userAddress,
            password: userPassword,
          };
        console.log(newUser)
        await userApi.createUser(newUser);
        toast.success("Tạo user thành công");

        setTimeout(() => {
            navigate("/users");
        }, 2000);
       }
       catch (e) {
        alert(e.response.data.message);
    }
    
    }
    return (
        <>
            <div className="container mt-5 mb-5">
                <h2 className="text-center text-uppercase mb-3">Tạo user</h2>

                <div className="row justify-content-center">
                    <div className="col-md-6">
                        <div className="bg-light p-4">
                            <div className="mb-3">
                                <label className="col-form-label">Name</label>
                                <input
                type="text"
                id="name"
                onChange={(e) => setUserName(e.target.value)}
                value={userName}
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label className="col-form-label">Email</label>
              <input
                type="text"
                id="email"
                value={userEmail}
                onChange={(e) => setUserEmail(e.target.value)}
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label className="col-form-label">Phone</label>
              <input
                type="text"
                value={userPhone}
                onChange={(e) => setUserPhone(e.target.value)}
                id="phone"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label className="col-form-label">Address</label>
              <select
                className="form-select"
                value={userAddress}
                onChange={(e) => setUserAddress(e.target.value)}
                id="address"
              >
                {" "}
                <option hidden>-- Chọn tỉnh/thành phố</option>
                {provinces.map((p) => (
                  <option value={p.name} key={p.name}>
                    {p.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label className="col-form-label">Password</label>
              <input
                type="text"
                id="password"
                value={userPassword}
                onChange={(e) => setUserPassword(e.target.value)}
                className="form-control"
                                />
                            </div>
                        </div>
                        <div className="text-center mt-3">
                            <button className="btn btn-secondary btn-back">
                            <Link className="navbar-brand" to={"/users"}>
                       Quay lại
                    </Link>
                            </button>
                            <button
                                className="btn btn-success"
                                id="btn-save"
                                onClick={handleAddUser}
                            >
                                Tạo User
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <ToastContainer position="top-center" />
        </>
    );
}

export default UserAdd;
