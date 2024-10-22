import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import { ToastContainer } from 'react-toastify';
import Header from './components/Header';
import Login from './components/Login';
import UploadFile from './components/UploadFile'
import { LoginContext } from './components/LoginContext';
import { TokenContext } from './components/TokenContext';
import { useState } from 'react';
import ProtectRoutes from './components/ProtectedRoutes';
import Menu from './components/Menu';
import UpdateStore from "./components/UpdateStore";
import StoreApproval from "./components/StoreApproval";
import UpdateStaff from "./components/UpdateStaff";
import StaffApproval from "./components/StaffApproval";
import { RoleContext } from "./components/RoleContext";

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [loginToken, setLoginToken] = useState("")
  const [roleId, setRoleId] = useState("0")
  return (
    <LoginContext.Provider value={{ loggedIn, setLoggedIn }}>
      <TokenContext.Provider value={{ loginToken, setLoginToken }}>
        <RoleContext.Provider value={{ roleId, setRoleId}}>
      <div>
        <Header />
        <div>
          <ToastContainer
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
          />

          <BrowserRouter>
            <Routes>
              <Route exact path="/" element={<Login url="http://localhost:8080/login"/>}/>
              {/* below are protected routes */}
              <Route path="/" element={<ProtectRoutes />} >
              <Route path="/menu" element={<Menu />} />
                <Route path="/menu/upload-store-file" element={<UploadFile url="http://localhost:8080/loadstoredata"/>} />
                <Route path="/menu/upload-staff-file" element={<UploadFile url="http://localhost:8080/loadstaffdata"/>} />
                <Route path="/menu/update-store" element={<UpdateStore url="http://localhost:8080/retrievestore" url2="http://localhost:8080/sotreforapproval"/>} />
                <Route path="/menu/approval-store" element={<StoreApproval url="http://localhost:8080/approvaldata" url2="http://localhost:8080/updatestore" url3="http://localhost:8080/deletestore"/>} />
                <Route path="/menu/update-staff" element={<UpdateStaff url="http://localhost:8080/retrievestaff" url2="http://localhost:8080/staffforapproval"/>} />
                <Route path="/menu/approval-staff" element={<StaffApproval url="http://localhost:8080/approvalstaffdata" url2="http://localhost:8080/updatestaff" url3="http://localhost:8080/deletestaff"/>} />
              </Route>
            </Routes>
          </BrowserRouter>
        </div>
      </div>
      </RoleContext.Provider>
      </TokenContext.Provider>
    </LoginContext.Provider>
  );
}

export default App;
