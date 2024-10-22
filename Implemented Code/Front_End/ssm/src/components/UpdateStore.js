import React from "react";
import { useState, useRef } from "react";
import { toast } from "react-toastify";
import "./UpdateStore.css";
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import imgedit from './edit.png'
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { TokenContext } from './TokenContext';

const UpdateStore = (props) => {
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
    const { loginToken, setLoginToken } = useContext(TokenContext)
    const navigate = useNavigate()
    //for edit fields
    const [addressField, setAddressField] = useState('')
    const [phoneField, setPhoneField] = useState('')
    const [arcField, setARCField] = useState('')
    const [hrsFields, setHrsFields] = useState('')

    //for edit buttons
    const [allEditButtons, setAllEditButtons] = useState('')

    //search field
    const [searchField, setSearchField] = useState('true')
    const [searchButton, setSearchButton] = useState('true')

    const onSubmit = async (e) => {
        e.preventDefault();
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.get(`${props.url}/${storeNum}`);
        console.log(response.data)
        if (response.data.detailsAvailable === "Yes") {
            setSearchField("")
            setSearchButton("")
            setAllEditButtons('false')
            setStoreAddress(response.data.address)
            setStorePhone(response.data.phoneNumber)
            setStoreARC(response.data.areaRegionCode)
            setMonHrs(response.data.monHours)
            setTueHrs(response.data.tueHours)
            setWedHrs(response.data.wedHours)
            setThuHrs(response.data.thuHours)
            setFriHrs(response.data.friHours)
            setSatHrs(response.data.satHours)
            setSunHrs(response.data.sunHours)
            console.log(response.data)
        }
        else {
            setSearchField("true")
            setSearchButton("true")
            toast.error('Store details not found', {
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

    const handleApproval = async () => {
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
        console.log(payload)
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.post(props.url2, payload);
        if (response.data.message === "Success") {
            toast.success('Store Details sent for approval', {
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
            toast.error('Failed to save store updation details', {
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
            <form onSubmit={e => e.preventDefault()} style={{ height: '800px' }}>
                <center>
                    <div>
                        <label><h1 className='h11'>Store Information</h1></label>
                        <hr
                            style={{
                                background: 'black',
                                color: 'black',
                                borderColor: 'black',
                                width: '1400px',
                            }} />
                        <div style={{ marginLeft: '-120px' }}>
                            <label>Store number</label>
                            <input type="text"
                                style={{ width: '220px' }}
                                name="storeNumber"
                                value={storeNum}
                                onChange={(event) => setStoreNum(event.target.value)}
                                required
                                disabled={searchField.length === 0}
                            />
                        </div>
                        <div>
                            <button
                                disabled={searchButton.length === 0}
                                className="buttons" onClick={onSubmit}
                                style={{ width: '130px', marginLeft: '40px' }}>Search</button>
                        </div>
                        <Table border="1px solid black" >

                            <tbody>
                                <tr>
                                    <td>Store Address</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '420px', height: "05px" }}
                                            name="storeAddress" value={address}
                                            onChange={(event) => setStoreAddress(event.target.value)}
                                            required
                                            disabled={addressField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setAddressField("false")}
                                            style={{ marginRight: "3px", marginLeft: "50px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                    </td>



                                </tr>
                                <tr>
                                    <td>Store Phone Number</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '220px', height: "05px" }}
                                            name="storePhone" value={phoneNumber}
                                            onChange={(event) => setStorePhone(event.target.value)}
                                            required
                                            disabled={phoneField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setPhoneField("false")}
                                            style={{ marginRight: "3px", marginLeft: "250px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Store Area Region and Code</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '450px', height: "05px" }}
                                            name="storeARC" value={areaRegionCode}
                                            onChange={(event) => setStoreARC(event.target.value)}
                                            required
                                            disabled={arcField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setARCField("false")}
                                            style={{ marginRight: "3px", marginLeft: "20px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Store Hours</td>
                                    <td>
                                        <div>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                mon_hours<input type="text" value={monHours}
                                                    style={{ width: '120px', height: "05px" }}
                                                    onChange={(event) => setMonHrs(event.target.value)}
                                                    name="monHrs" required
                                                    disabled={hrsFields.length === 0}
                                                /></span>
                                            <button
                                                disabled={allEditButtons.length === 0}
                                                onClick={(event) => setHrsFields("false")}
                                                style={{ marginRight: "3px", marginLeft: "270px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                        </div>
                                        <div>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                tue_hours<input type="text" value={tueHours}
                                                    style={{ marginLeft: '50px', width: '120px', height: "05px" }}
                                                    onChange={(event) => setTueHrs(event.target.value)}
                                                    name="tueHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required />
                                            </span>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                fri_hours<input type="text" value={friHours}
                                                    style={{ width: '120px', height: "05px" }}
                                                    onChange={(event) => setFriHrs(event.target.value)}
                                                    name="friHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required />
                                            </span>
                                        </div>
                                        <div>

                                        </div>
                                        <div>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                wed_hours<input type="text" value={wedHours}
                                                    style={{ marginLeft: '45px', width: '120px', height: "05px" }}
                                                    onChange={(event) => setWedHrs(event.target.value)}
                                                    name="wedHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required />
                                            </span>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                sat_hours<input type="text" value={satHours}
                                                    style={{ marginLeft: '35px', width: '120px', height: "05px" }}
                                                    onChange={(event) => setSatHrs(event.target.value)}
                                                    name="satHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required />
                                            </span>
                                        </div>
                                        <div>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                thu_hours<input type="text" value={thuHours}
                                                    style={{ marginLeft: '50px', width: '120px', height: "05px" }}
                                                    onChange={(event) => setThuHrs(event.target.value)}
                                                    name="thuHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required /></span>
                                            <span style={{ color: "black", fontSize: "15px" }}>
                                                sun_hours<input type="text" value={sunHours}
                                                    style={{ marginLeft: '33Px', width: '120px', height: "05px" }}
                                                    onChange={(event) => setSunHrs(event.target.value)}
                                                    name="sunHrs"
                                                    disabled={hrsFields.length === 0}
                                                    required />
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </Table>
                        <div style={{ marginTop: '70px' }}>
                            <button className="buttons"
                                disabled={allEditButtons.length === 0}
                                onClick={handleApproval}
                                style={{ width: "300px", marginLeft: '40px', backgroundColor: 'green' }}>Send for Approval</button>
                        </div>
                    </div>
                </center>
            </form>
        </div>

    );
};

export default UpdateStore;