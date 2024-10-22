import React from "react";
import { useState, useRef } from "react";
import { toast } from "react-toastify";
import "./UpdateStore.css";
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import imgdislike from './like3.png'
import imglike from './like4.png'
import { useContext } from 'react';
import { TokenContext } from './TokenContext';
import { useNavigate } from 'react-router-dom';

const StoreApproval = (props) => {

    //like dislike buttons
    const [likeDislikeButtons, setLikeDislikeButtons] = useState('')
    const { loginToken, setLoginToken } = useContext(TokenContext)
    //CHNAGES FROM
    const [storeNumF, setStoreNumF] = useState('')
    const [addressF, setStoreAddressF] = useState('')
    const [phoneNumberF, setStorePhoneF] = useState('')
    const [areaRegionCodeF, setStoreARCF] = useState('')
    const [monHoursF, setMonHrsF] = useState('')
    const [tueHoursF, setTueHrsF] = useState('')
    const [wedHoursF, setWedHrsF] = useState('')
    const [thuHoursF, setThuHrsF] = useState('')
    const [friHoursF, setFriHrsF] = useState('')
    const [satHoursF, setSatHrsF] = useState('')
    const [sunHoursF, setSunHrsF] = useState('')

    //CHANGES TO
    const [storeNumT, setStoreNumT] = useState('')
    const [addressT, setStoreAddressT] = useState('')
    const [phoneNumberT, setStorePhoneT] = useState('')
    const [areaRegionCodeT, setStoreARCT] = useState('')
    const [monHoursT, setMonHrsT] = useState('')
    const [tueHoursT, setTueHrsT] = useState('')
    const [wedHoursT, setWedHrsT] = useState('')
    const [thuHoursT, setThuHrsT] = useState('')
    const [friHoursT, setFriHrsT] = useState('')
    const [satHoursT, setSatHrsT] = useState('')
    const [sunHoursT, setSunHrsT] = useState('')

    //send to delete and update
    const [storeNum, setStoreNum] = useState('')
    const [address, setStoreAddress] = useState('')
    const [phoneNumber, setStorePhone] = useState('')
    const [areaRegionCode, setStoreARC] = useState('')
    const [monHours, setMonHrs] = useState('')
    const [tueHours, setTueHrs] = useState('')
    const [wedHours, setWedHrs] = useState('')
    const [thuHours, setThuHrs] = useState('')
    const [friHours, setFriHrs] = useState('')
    const [satHours, setSatHrs] = useState('')
    const [sunHours, setSunHrs] = useState('')
    const navigate = useNavigate()

    const handleApprovalClick = async (e) => {
        e.preventDefault();
        axios.interceptors.request.use((config)=>{
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.get(props.url);
        console.log(response.data)

        if (response.data.satHoursF != null) {
            setStoreNumF(response.data.storeNumF)
            setStoreAddressF(response.data.addressF)
            setStorePhoneF(response.data.phoneNumberF)
            setStoreARCF(response.data.areaRegionCodeF)
            setMonHrsF(response.data.monHoursF)
            setTueHrsF(response.data.tueHoursF)
            setWedHrsF(response.data.wedHoursF)
            setThuHrsF(response.data.thuHoursF)
            setFriHrsF(response.data.friHoursF)
            setSatHrsF(response.data.satHoursF)
            setSunHrsF(response.data.sunHoursF)

            setStoreNumT(response.data.storeNumT)
            setStoreAddressT(response.data.addressT)
            setStorePhoneT(response.data.phoneNumberT)
            setStoreARCT(response.data.areaRegionCodeT)
            setMonHrsT(response.data.monHoursT)
            setTueHrsT(response.data.tueHoursT)
            setWedHrsT(response.data.wedHoursT)
            setThuHrsT(response.data.thuHoursT)
            setFriHrsT(response.data.friHoursT)
            setSatHrsT(response.data.satHoursT)
            setSunHrsT(response.data.sunHoursT)

            setStoreNum(storeNumF)
            setStoreAddress(storeNumF)
            setStorePhone(phoneNumberF)
            setStoreARC(areaRegionCodeF)
            setMonHrs(monHoursF)
            setTueHrs(tueHoursF)
            setWedHrs(wedHoursF)
            setThuHrs(thuHoursF)
            setFriHrs(friHoursF)
            setSatHrs(satHoursF)
            setSunHrs(sunHoursF)
            setLikeDislikeButtons("false")
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
            storeNum,
            address,
            phoneNumber,
            areaRegionCode,
            monHours,
            tueHours,
            wedHours,
            thuHours,
            friHours,
            satHours,
            sunHours
        }
        axios.interceptors.request.use((config)=>{
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
            storeNum,
            address,
            phoneNumber,
            areaRegionCode,
            monHours,
            tueHours,
            wedHours,
            thuHours,
            friHours,
            satHours,
            sunHours
        }
        axios.interceptors.request.use((config)=>{
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
                        <label><h1 className='h11'>Store Request Information</h1></label>
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
                                        onClick={handleApprovalClick}>Approval</button></td>
                                </tr>

                                <tr>
                                    <td>
                                        Store Number:{storeNumF}<hr />
                                        Store Address:{addressF}<hr />
                                        Store Phone Number:{phoneNumberF}<hr />
                                        Store Area,Region,Code:{areaRegionCodeF}<hr />
                                        Store mon_hours:{monHoursF}<hr />
                                        Store tue_hours:{tueHoursF}<hr />
                                        Store wed_hours:{wedHoursF}<hr />
                                        Store thu_hours:{thuHoursF}<hr />
                                        Store fri_hours:{friHoursF}<hr />
                                        Store sat_hours:{satHoursF}<hr />
                                        Store sun_hours:{sunHoursF}
                                    </td>

                                    <td>
                                        Store Number:{storeNumT}<hr />
                                        Store Address:{addressT}<hr />
                                        Store Phone Number:{phoneNumberT}<hr />
                                        Store Area,Region,Code:{areaRegionCodeT}<hr />
                                        Store mon_hours:{monHoursT}<hr />
                                        Store tue_hours:{tueHoursT}<hr />
                                        Store wed_hours:{wedHoursT}<hr />
                                        Store thu_hours:{thuHoursT}<hr />
                                        Store fri_hours:{friHoursT}<hr />
                                        Store sat_hours:{satHoursT}<hr />
                                        Store sun_hours:{sunHoursT}
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
export default StoreApproval;