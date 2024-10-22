import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useContext } from 'react';
import { LoginContext } from './LoginContext';

const useAuth = () => {
    const { loggedIn, setLoggedIn } = useContext(LoginContext)
    if (loggedIn === true) {
        return true
    }
    else {
        return false
    }
}

const ProtectedRoutes = () => {
    let auth = useAuth()
    return auth ? <Outlet /> : <Navigate to="/" />
}
export default ProtectedRoutes;