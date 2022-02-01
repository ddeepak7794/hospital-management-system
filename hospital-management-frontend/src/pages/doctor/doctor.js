import { Col, Container, Button, Image, Row } from "react-bootstrap";


import { useEffect, useState } from "react";
import AddNewPatientModal from "../../components/patient/AddNewPatient";
import axios from "axios";
import Patient from "../../components/patient/patient";
import { URL } from "../../config";
import { useNavigate } from "react-router";
import "./doctor.css"
import PatientDoctor from "./component/patientDoctor";
import {  useLocation } from "react-router-dom";

const DoctorHome = () => {
    // ============================all constants=======================
        const [addNewPatientModalFlag,setAddNewPatientModalFlag]=useState(false)
        const [show, setShow] = useState(false);
        const [patients,setPatients]=useState([]);
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);
        const [dataChangedFlag, setDataChangedFlag] = useState(false);
//************************=======fuctions=========--------------- */
    const location = useLocation();
     const { doctorId } = location.state;
    
    const getPatientsFromServer = () => {
      const url = `${URL}/doctor/getPatients/${doctorId}`;
      axios.get(url).then((res) => {
        setDataChangedFlag(false);
        console.log("data flag inside getEmployeesFromServer " + dataChangedFlag);
        const result = res.data;
        if (result.status == "success") {
          setPatients(result.data);
          console.log(res);
        } else {
          console.log("unable to fetch result");
        }
      });
    };
    useEffect(() => {
      getPatientsFromServer();
      console.log("inside useEffect of adminDetails");
    }, [dataChangedFlag]);
    const navigate=useNavigate();
    //******************=-------------=======----------=======------------- */
  return (
    <div className="doctorContainer">

     <hr />
      {/* ===================================Add New Patient button =============== */}
      <div class="d-flex  container-fluid" style={{width:"900px"}}>
        <div class="p-2 bd-highlight"><Button variant="primary"  onClick={()=>{
          navigate("/")
        }}>Logout</Button></div>
      
      </div>
      {/* =======================================table container of patients====================================== */}
      <div className="cointainer-fluid DoctorTableContainer">
        <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">Patient Id</th>
              <th scope="col">Name</th>
              <th scope="col">Payment Status</th>
              <th scope="col">Details</th>
            </tr>
          </thead>
          <tbody>
            {patients.map((e) => {
              return (
                <PatientDoctor
                  patient={e}
                  setDataChangedFlag={setDataChangedFlag}
                  
                />
              );
            })}
          </tbody>
        </table>
      </div>
      {/* ================================================table container over========================== */}
    </div>
  );
};

export default DoctorHome;
