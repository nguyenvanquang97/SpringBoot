import axiosClient from "./axiosClient";

const userApi = {
  getUsers() {
    const url = "/users";
    return axiosClient.get(url);
  },
  createUser(newUser) {
    const url = "/users";
    return axiosClient.post(url, newUser);
  },
  updateUser(id, updatedUser) {
    const url = `users/${id}`;
    return axiosClient.put(url, updatedUser);
  },
  deleteUser(id) {
    const url = `users/${id}`;
    return axiosClient.delete(url);
  },
  searchUser(finUserName) {
    const url = `users/search?name=${finUserName}`;
    return axiosClient.get(url);
  },
  updatePassword(id,updatePasswordRequest){
    const url=`users/${id}/update-password`;
    return axiosClient.put(url,updatePasswordRequest);
  },
  forgotPassword(id) {
    const url=`users/${id}/forgot-password`;
    return axiosClient.post(url);
  },
  uploadAvatar(id,file) {
    const url=`users/${id}/update-avatar-v2`;
    return axiosClient.post(url,file);
  }
};

export default userApi;