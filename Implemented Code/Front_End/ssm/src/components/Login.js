import { useState } from 'react';
import { Link } from 'react-router-dom';
import "./Login.css";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useContext } from 'react';
// import bcrypt from 'bcryptjs';
import { LoginContext } from './LoginContext';
import { TokenContext } from './TokenContext';
import { RoleContext } from './RoleContext';

const Login = (props) => {
    const { loggedIn, setLoggedIn } = useContext(LoginContext)
    const {loginToken, setLoginToken} = useContext(TokenContext)
    const {roleId, setRoleId} = useContext(RoleContext)
    const navigate = useNavigate()
    const [username, setUsername] = useState('')
    const [plainpassword, setPassword] = useState('')
    const [patternValid, setPatternValid] = useState('')
    const [patternValidE, setPatternValidE] = useState('')


    const onSubmit = async () => {

        if (username === '' && plainpassword === '') {
            // alert("Please fill in the fields")
            toast.warn('Please fill in the fields', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
        else {
            // const password = bcrypt.hashSync(plainpassword, 1);
            const password = plainpassword;
            const payload = {
                username,
                password
            }
            console.log(payload)
            const response = await axios.post(props.url, payload)
            console.log(response.data)
            if (response.data.token === "Invalid Username") {
                toast.error('Incorrect Username', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
               
            }
            else if (response.data.token === "Invalid Password") {
                // alert("Incorrect Password");
                toast.error('Incorrect Password', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
            else {
                toast.success('Login Successfull', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
                 setLoggedIn(true)
                var combineBT = "Bearer "+response.data.token;
                setLoginToken(combineBT)
                setRoleId(response.data.roleId)
                console.log(response.data)
                console.log(response.data.roleId)
                navigate('/menu')
            }
        }
    }



    const isvalid = (plainpassword) => {
        const pattern = /(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,20}).*$/
        return pattern.test(plainpassword);
    }

    const isPasswordValid = () => {
        const isPassValid = plainpassword && isvalid(plainpassword)
        setPatternValid(isPassValid ? '' : 'Invalid Passwordd')
    }


    const isvalidEmail = (username) => {
        const pattern = /[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
        return pattern.test(username);
    }

    const isEmailValid = () => {
        const isEValid = username && isvalidEmail(username)
        setPatternValidE(isEValid ? '' : 'Invalid Username/Email')
    }

    return (

        <div className='formInputs' >
            
            <form onSubmit={e => e.preventDefault()} className="form" >
                <h1 className='h12'>Log In</h1>
                <div>
                    <center>
                        <hr
                            style={{
                                background: 'black',
                                color: 'black',
                                borderColor: 'black',
                                width: '1400px',
                            }} />
                        <div>
                            <label>USERNAME</label>
                            <input
                                onBlur={isEmailValid}
                                onFocus={() => setPatternValidE('')}
                                type="email" name="username"
                                required
                                value={username} onChange={(event) => setUsername(event.target.value)}
                            />
                        </div>
                        <span>{patternValidE}</span>

                        <div>
                            <label>PAASWORD</label>
                            <input
                                onBlur={isPasswordValid}
                                onFocus={() => setPatternValid('')}
                                type="password" name='plainpassword' required
                                value={plainpassword} onChange={(event) => setPassword(event.target.value)}
                            />
                        </div>
                        <span>{patternValid}</span>
                        <div>
                            <a style={{ color: 'red', fontSize: '1.3em', marginLeft: '0px' }}>Forgot Password?</a>
                            <button className='successButton' onClick={onSubmit}>Login</button>
                        </div>
                    </center>
                </div>
            </form>
        </div>
    )
}

export default Login