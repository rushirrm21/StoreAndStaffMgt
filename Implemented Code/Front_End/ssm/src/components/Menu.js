import { Link } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
import { RoleContext } from './RoleContext';
import { useContext } from 'react';

const Menu = () => {
    const { roleId, setRoleId } = useContext(RoleContext)

    return (
        <div style={{ fontSize: "1.2em" }}>
            <div className='formMenu' style={{ height: "40px", width: "350px", marginTop: '95px', marginLeft: "15px" }}><div style={{ color: "green" }}><b>Assigned Roles:</b></div>
                <div style={{ fontSize: "0.8em" }}>  {roleId == "1" ? <div>ADMIN_USER</div> : roleId == "2" ? <div>STORE_ADMIN</div> : roleId == "3" ? <div>STORE_MANAGER</div> : roleId == "12" ? <div>ADMIN_USER, STORE_ADMIN</div> : roleId == "23" ? <div>STORE_ADMIN, STORE_MANAGER</div> : roleId == "13" ? <div>ADMIN_USER, STORE_MANAGER</div> : <div>ADMIN_USER, STORE_ADMIN, STORE_MANAGER</div>}</div>
            </div>
            <div className='formInputs' >
                {/* <ToastContainer /> */}
                <div>
                    <form className="formMenu" style={{ marginTop: '50px', width: "450px", height: "270px" }}>
                        <h1 className='h12'>Select Action</h1>

                        <div style={{ marginTop: "10px", marginLeft: "90px" }}>
                            {roleId == "1" || roleId == "12" || roleId == "13" || roleId == "123" ?
                                <div>
                                    <Link className='menu' to="/menu/upload-store-file">Store Upload</Link>
                                </div>
                                : console.log(roleId)}

                            {roleId == "2" || roleId == "12" || roleId == "23" || roleId == "123" ?
                                <div>
                                    <Link className='menu' to="/menu/upload-staff-file">Staff Upload</Link>
                                </div>
                                : console.log(roleId)}

                            {roleId == "3" || roleId == "23" || roleId == "13" || roleId == "123" ?
                                <div>
                                    <Link className='menu' to="/menu/update-store">Store Update</Link>
                                </div>
                                : console.log(roleId)}

                            {roleId == "3" || roleId == "23" || roleId == "13" || roleId == "123" ?
                                <div>
                                    <Link className='menu' to="/menu/update-staff">Staff Update</Link>
                                </div>
                                : console.log(roleId)}

                            {roleId == "123" || roleId == "23" || roleId == "13" || roleId == "3" ?
                                <div>
                                    <Link className='menu' to="/menu/approval-store">Store Approval</Link>
                                </div>
                                : console.log(roleId)}

                            {roleId == "123" || roleId == "23" || roleId == "13" || roleId == "3" ?
                                <div>
                                    <Link className='menu' to="/menu/approval-staff">Staff Approval</Link>
                                </div>
                                : console.log(roleId)}

                        </div>

                    </form>
                </div>

            </div>
        </div>
    )
}
export default Menu