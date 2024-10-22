import React from "react";
import { useState, useRef } from "react";
import { toast } from "react-toastify";
import "./UpdateStore.css";
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import imgdislike from './like3.png'
import imglike from './like4.png'
import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { TokenContext } from './TokenContext';

const StaffApproval = (props) => {

    //like dislike buttons
    const [likeDislikeButtons, setLikeDislikeButtons] = useState('')
    const { loginToken, setLoginToken } = useContext(TokenContext)
    //value fields from
    const [staffIdF, setStaffIdF] = useState('')
    const [positionF, setPositionF] = useState('')
    const [emailF, setEmailF] = useState('')
    const [officePhoneNoF, setOfficePhoneNoF] = useState('')
    const [cellPhoneF, setCellPhoneF] = useState('')
    const [deptAreaRegionDistrictF, setDeptAreaRegionDistrictF] = useState('')

    //value fields TO
    const [staffIdT, setStaffIdT] = useState('')
    const [positionT, setPositionT] = useState('')
    const [emailT, setEmailT] = useState('')
    const [officePhoneNoT, setOfficePhoneNoT] = useState('')
    const [cellPhoneT, setCellPhoneT] = useState('')
    const [deptAreaRegionDistrictT, setDeptAreaRegionDistrictT] = useState('')

    //value fields 
    const [staffId, setStaffId] = useState('')
    const [position, setPosition] = useState('')
    const [email, setEmail] = useState('')
    const [officePhoneNo, setOfficePhoneNo] = useState('')
    const [cellPhone, setCellPhone] = useState('')
    const [deptAreaRegionDistrict, setDeptAreaRegionDistrict] = useState('')

    const navigate = useNavigate()


    const handleApprovalClick = async (e) => {

        e.preventDefault();
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.get(props.url);
        console.log(response.data)
        if (response.data.staffIdF != null) {
            setStaffIdF(response.data.staffIdF)
            setPositionF(response.data.positionF)
            setEmailF(response.data.emailF)
            setOfficePhoneNoF(response.data.officePhoneNoF)
            setCellPhoneF(response.data.cellPhoneF)
            setDeptAreaRegionDistrictF(response.data.deptAreaRegionDistrictF)

            setStaffIdT(response.data.staffIdT)
            setPositionT(response.data.positionT)
            setEmailT(response.data.emailT)
            setOfficePhoneNoT(response.data.officePhoneNoT)
            setCellPhoneT(response.data.cellPhoneT)
            setDeptAreaRegionDistrictT(response.data.deptAreaRegionDistrictT)

            setStaffId(staffIdF)
            setPosition(positionF)
            setEmail(emailF)
            setOfficePhoneNo(officePhoneNoF)
            setCellPhone(cellPhoneF)
            setDeptAreaRegionDistrict(deptAreaRegionDistrictF)
            setLikeDislikeButtons("True")
        }
        else {
            toast.warn('No record is pending for approval', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    }

    const handleLike = async (e) => {
        e.preventDefault();
        const payload = {
            staffId,
            position,
            email,
            officePhoneNo,
            cellPhone,
            deptAreaRegionDistrict
        }
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.put(props.url2, payload);
        console.log(response.data)
        if (response.data.message === "Success") {
            toast.success('Details Approved', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            navigate('/menu')
        }
        else {
            toast.error('Something went wrong while approving details, data is incorrect', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    }

    const handelDislike = async (e) => {
        e.preventDefault();
        const payload = {
            staffId,
            position,
            email,
            officePhoneNo,
            cellPhone,
            deptAreaRegionDistrict
        }
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.delete(props.url3, payload);
        if (response.data.message === "Success") {
            toast.success('Details Disapproved', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            navigate('/menu')
        }
        else {
            toast.error('Something went wrong while disapproving details', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    }


    return (
        <div className="formInputs">
            <form style={{ height: '800px' }}>
                <center>
                    <div>
                        <label><h1 className='h11'>Employee Request Information</h1></label>
                        <hr
                            style={{
                                background: 'black',
                                color: 'black',
                                borderColor: 'black',
                                width: '1400px',
                            }} />


                        <Table border="1px solid black" >

                            <tbody>
                                <tr>
                                    <td><b>Changes From</b></td>
                                    <td><b>Changes To</b></td>
                                    <td><button
                                        className="buttons"
                                        style={{ fontSize: '1em', height: '40px', width: '130px' }}
                                        onClick={handleApprovalClick}
                                    >Approval</button></td>
                                </tr>

                                <tr>
                                    <td>
                                        Employee Id:{staffIdF}<hr />
                                        Position:{positionF}<hr />
                                        Email Id:{emailF}<hr />
                                        Office Phone No:{officePhoneNoF}<hr />
                                        Cellphone:{cellPhoneF}<hr />
                                        Dept, Area, Region, District:{deptAreaRegionDistrictF}<hr />
                                    </td>

                                    <td>
                                        Employee Id:{staffIdT}<hr />
                                        Position:{positionT}<hr />
                                        Email Id:{emailT}<hr />
                                        Office Phone No:{officePhoneNoT}<hr />
                                        Cellphone:{cellPhoneT}<hr />
                                        Dept, Area, Region, District:{deptAreaRegionDistrictT}<hr />

                                    </td>
                                    <td>


                                        <button
                                            disabled={likeDislikeButtons.length === 0}
                                            onClick={handleLike}
                                            style={{ marginRight: "5px", marginLeft: "35px" }}>
                                            <img src={imglike} height="50px" width="50px" />
                                        </button>
                                        <hr />
                                        <button
                                            disabled={likeDislikeButtons.length === 0}
                                            onClick={handelDislike}
                                            style={{ marginRight: "5px", marginLeft: "35px" }}>
                                            <img src={imgdislike} height="50px" width="50px" />
                                        </button>

                                    </td>
                                </tr>
                            </tbody>
                        </Table>

                    </div>
                </center>
            </form>
        </div>
    );
}

export default StaffApproval;