import { Route, Routes } from 'react-router-dom';
import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import NotFound from './components/Notfound';
import UserAdd from './features/user/UserAdd';
import UserEdit from './features/user/UserEdit';
import UserList from './features/user/UserList';

function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path='/users'>
          <Route index element={<UserList />} />
          <Route path='create' element={<UserAdd />} />
          <Route path=':userId' element={<UserEdit />} />
        </Route>
        <Route path='*' element={<NotFound />} />
      </Routes>

      <Footer />
    </>
  );
}

export default App;
