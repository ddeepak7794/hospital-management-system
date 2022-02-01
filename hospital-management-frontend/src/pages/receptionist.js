import {
  Container,
  Button,
  Navbar,
  Nav,
} 
from "react-bootstrap";

import "./receptionist.css";
import { useEffect, useState } from "react";
import AddNewPatientModal from "../components/patient/AddNewPatient";
import axios from "axios";
import Patient from "../components/patient/patient";
import { URL } from "../config";
import { useNavigate } from "react-router";

const ReceptionistHome = () => {
  // ============================all constants=======================
  const [addNewPatientModalFlag, setAddNewPatientModalFlag] = useState(false);
  const [show, setShow] = useState(false);
  const [patients, setPatients] = useState([]);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [dataChangedFlag, setDataChangedFlag] = useState(false);
  //************************=======fuctions=========--------------- */

  const ToggleAddNewPatientModal = () => {
    setAddNewPatientModalFlag(true);
    handleShow();
    console.log(
      "value of ToggleAddNewPatientModal in function : " +
        addNewPatientModalFlag
    );
  };
  const getPatientsFromServer = () => {
    const url = `${URL}/patient/getAllPatients`;
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
  //toggle data change flag
  const ToggleDataChangeFlag = () => {
    console.log("datachange flag addnew : " + dataChangedFlag);
    setDataChangedFlag(true);
  };
  useEffect(() => {
    getPatientsFromServer();
    console.log("inside useEffect of adminDetails");
  }, [dataChangedFlag]);
  const navigate = useNavigate();
  //******************=-------------=======----------=======------------- */
  return (
    <div className="receptionContainer">
      <Navbar bg="" expand="lg" sticky="top">
        <Container>
          <Navbar.Toggle
            aria-controls="basic-navbar-nav"
            style={{ color: "brown", fontWeight: "bold",background:"chartreuse" }}
          >
            Click For Options
          </Navbar.Toggle>
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              {/* ==============================add new patient==================== */}
              <Nav.Link>
                <Button onClick={ToggleAddNewPatientModal} variant="danger">
                  Add new Patient
                </Button>
              </Nav.Link>
              <Nav.Link>
                {/* fourth menu operration */}
                <Button
                  variant="primary"
                  onClick={() => {
                    navigate("/");
                  }}
                >
                  Logout
                </Button>
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div>
        {addNewPatientModalFlag && (
          <AddNewPatientModal
            ToggleDataChangeFlag={ToggleDataChangeFlag}
            setAddNewPatientModalFlag={setAddNewPatientModalFlag}
            handleClose={handleClose}
            show={show}
          />
        )}
      </div>

      <hr />
      {/* =======================================table container of patients====================================== */}
      <div className="cointainer-fluid receptionTableContainer">
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
                <Patient patient={e} setDataChangedFlag={setDataChangedFlag} />
              );
            })}
          </tbody>
        </table>
      </div>
      {/* ================================================table container over========================== */}
    </div>
  );
};

export default ReceptionistHome;