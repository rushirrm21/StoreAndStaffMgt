import React from "react";
import { useState, useRef } from "react";
import { toast } from "react-toastify";
import "./UploadFile.css";
import axios from 'axios';
import { useContext } from 'react';
import { TokenContext } from './TokenContext';
import { useNavigate } from 'react-router-dom';

const UploadFile = (props) => {

    const {loginToken, setLoginToken} = useContext(TokenContext)
    // const navigate = useNavigate()
    const [excelFile, setExcelFile] = useState(null);
    // it will store excel sheet data after submit
    const [fileTypeError, setFileTypeError] = useState(null);
    //file name
    const [fileName, setFileName] = useState("No file choosen")

    const navigate = useNavigate()
    ///
    const fileInputFiled = useRef(null);
    //
    const fileType = ['text/csv']
    const handleFile = (event) => {
        setFileTypeError('')
        setFileName(fileInputFiled.current.files[0].name)

        let selectedFile = event.target.files[0];

        if (selectedFile && fileType.includes(selectedFile.type)) {
            setExcelFile(event.target.files[0]);
        }

        else {
            setFileTypeError('Please select only CSV file types !');
            setExcelFile(null);
        }
    }

    const handleInputFileButton = (e) => {

        setFileName("No file choosen")
        setFileTypeError('')
        setExcelFile(null);
        fileInputFiled.current.click()
    }

    const handleReset = (e) => {
        setFileName("No file choosen")
        setFileTypeError('')
        setExcelFile(null);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(loginToken)
        if (excelFile !== null) {
            let formData = new FormData();
            formData.append('file', excelFile);

//for jwt testing start


// axios.interceptors.request.use((config)=>{
//     console.log(`${loginToken}`)
//     config.headers.Authorization = `${loginToken}`;
//     return config;
// }, error => {
//     return Promise.reject(error);
// }
// );

///

const config = {
    headers: { Authorization: `${loginToken}` }
};
console.log(config)
console.log({loginToken})
///// jwt end
            const response = await axios.post(props.url, formData, config)
            const m1 = response.data.message
            console.log(m1)
            if (m1 === "FAILED") {
                toast.error("Failed to save details into the Database", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
            else if (m1 === "INDUCTED") {
                toast.success('Details saved successfully into the Database', {
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


        }
        else {
            toast.warn('Please select file', {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            setExcelFile(null);

        }
    }

    return (




        <div className="formInputs">
            <form onSubmit={handleSubmit} onReset={handleReset}>
                <center>
                    <div>
                        <label><h1 className='h11'>Upload Your File</h1></label>
                        <hr
                            style={{
                                background: 'black',
                                color: 'black',
                                borderColor: 'black',
                                width: '1400px',
                            }} />
                        <div>
                            <input type='file' ref={fileInputFiled} onChange={handleFile}
                                style={{ display: 'none' }} />

                            <input type='text' disabled style={{ width: '25%', marginTop: '70px', marginLeft: '40px' }} value={fileName} />
                            <button type="button" style={{ marginLeft: '40px' }} className="buttons" onClick={handleInputFileButton}>Add File</button>

                        </div>
                        <div>
                            <span style={{ marginRight: '240px', fontSize: '01.1em' }}>{fileTypeError}</span>
                        </div>
                        <div style={{ marginTop: '70px' }}>
                            <button className="buttons" type="Submit" style={{ marginLeft: '40px', backgroundColor: 'green' }}>Upload</button>
                            <button className="buttons" type="reset" style={{ backgroundColor: 'red', marginLeft: '150px' }}>Cancel</button>
                        </div>
                    </div>
                </center>
            </form>
        </div>

    );
};

export default UploadFile;