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

const UpdateStaff = (props) => {


    const navigate = useNavigate()
    const { loginToken, setLoginToken } = useContext(TokenContext)
    //for edit fields
    const [positionField, setPositionField] = useState('')
    const [emailField, setEmailField] = useState('')
    const [arcField, setARCField] = useState('')
    const [officePhNoFields, setOfficePhNoFieldsFields] = useState('')
    const [cellphoneField, setcellphoneField] = useState('')

    //for edit buttons
    const [allEditButtons, setAllEditButtons] = useState('')

    //search field
    const [searchField, setSearchField] = useState('true')
    const [searchButton, setSearchButton] = useState('true')


    //value fields
    const [staffId, setStaffId] = useState('')
    const [position, setPosition] = useState('')
    const [email, setEmail] = useState('')
    const [officePhoneNo, setOfficePhoneNo] = useState('')
    const [cellPhone, setCellPhone] = useState('')
    const [deptAreaRegionDistrict, setDeptAreaRegionDistrict] = useState('')

    const onSubmit = async (e) => {
        axios.interceptors.request.use((config) => {
            console.log(`${loginToken}`)
            config.headers.Authorization = `${loginToken}`;
            return config;
        }, error => {
            return Promise.reject(error);
        }
        );
        const response = await axios.get(`${props.url}/${staffId}`);
        console.log(response.data)
        if (response.data.detailsAvailable === "Yes") {
            setPosition(response.data.position)
            setEmail(response.data.email)
            setOfficePhoneNo(response.data.officePhoneNo)
            setCellPhone(response.data.cellPhone)
            setDeptAreaRegionDistrict(response.data.deptAreaRegionDistrict)
            setAllEditButtons("True")
            setSearchField("")
            setSearchButton("")
        } else {
            setSearchField("true")
            setSearchButton("true")
            toast.error('Staff details not found', {
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
        const response = await axios.post(props.url2, payload)
        if (response.data.message === "Success") {
            toast.success('Staff Details sent for approval', {
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
            toast.error('Failed to save staff updation details', {
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
            <form onSubmit={e => e.preventDefault()} style={{ height: '660px' }}>
                <center>
                    <div>
                        <label><h1 className='h11'>Employee Information</h1></label>
                        <hr
                            style={{
                                background: 'black',
                                color: 'black',
                                borderColor: 'black',
                                width: '1400px',
                            }} />
                        <div style={{ marginLeft: '-120px' }}>
                            <label>Employee Id</label>
                            <input type="text"
                                style={{ width: '220px' }}
                                name="staffId"
                                value={staffId}
                                onChange={(event) => setStaffId(event.target.value)}
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
                                    <td>Position</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '220px', height: "05px" }}
                                            name="staffPosition" value={position}
                                            onChange={(event) => setPosition(event.target.value)}
                                            required
                                            disabled={positionField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setPositionField("false")}
                                            style={{ marginRight: "3px", marginLeft: "270px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                    </td>



                                </tr>
                                <tr>
                                    <td>Email Id</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '220px', height: "05px" }}
                                            name="staffEmailId" value={email}
                                            onChange={(event) => setEmail(event.target.value)}
                                            required
                                            disabled={emailField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setEmailField("false")}
                                            style={{ marginRight: "3px", marginLeft: "270px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Office Phone no.</td>
                                    <td>
                                        <div>
                                            <input type="text"
                                                style={{ width: '220px', height: "05px" }}
                                                name="staffOfficePhno" value={officePhoneNo}
                                                onChange={(event) => setOfficePhoneNo(event.target.value)}
                                                required
                                                disabled={officePhNoFields.length === 0}
                                            />
                                            <button
                                                disabled={allEditButtons.length === 0}
                                                onClick={(event) => setOfficePhNoFieldsFields("false")}
                                                style={{ marginRight: "3px", marginLeft: "270px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Cellphone</td>
                                    <td>
                                        <div>
                                            <input type="text"
                                                style={{ width: '220px', height: "05px" }}
                                                name="staffCellphone" value={cellPhone}
                                                onChange={(event) => setCellPhone(event.target.value)}
                                                required
                                                disabled={cellphoneField.length === 0}
                                            />
                                            <button
                                                disabled={allEditButtons.length === 0}
                                                onClick={(event) => setcellphoneField("false")}
                                                style={{ marginRight: "3px", marginLeft: "270px" }}><img src={imgedit} height="30px" width="30px" /></button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Staff Dept, Area, Region, and District</td>
                                    <td>
                                        <input type="text"
                                            style={{ width: '450px', height: "05px" }}
                                            name="staffDARD" value={deptAreaRegionDistrict}
                                            onChange={(event) => setDeptAreaRegionDistrict(event.target.value)}
                                            required
                                            disabled={arcField.length === 0}
                                        />
                                        <button
                                            disabled={allEditButtons.length === 0}
                                            onClick={(event) => setARCField("false")}
                                            style={{ marginRight: "3px", marginLeft: "40px" }}><img src={imgedit} height="30px" width="30px" /></button>
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

export default UpdateStaff;