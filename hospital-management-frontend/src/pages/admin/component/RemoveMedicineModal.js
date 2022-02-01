import React, { useEffect } from "react";
import { useState } from "react";
import { Modal, Button, Badge } from "react-bootstrap";
import "react-phone-number-input/style.css";
import { toast } from "react-toastify";
import axios from "axios";
import { URL } from "../../../config";
import WardAdminDetails from "./WardAdminDetails";
import RemoveWardAdminDetails from "./WardAdminDetails";
import MedicineAdminDetails from "./MedicineAdminDetails ";

const RemoveMedicineModal = (props) => {
  const {
    show,
    setShow,
    handleClose,
    setDataChangedFlag,
    setRemoveMedicineModalFlag,
  } = props;

  const [dataChangeFlagRemoveMedicine, setDataChangeFlagRemoveMedicine] =
    useState(false);
  //to collect all wards from server
  const [medicines, setMedicines] = useState([]);
  //to get wards from server
  const GetAllMedicinesFromServer = () => {
    setDataChangeFlagRemoveMedicine(false)
    const url = `${URL}/medicine/getAllMedicines`;
    axios.get(url).then((res) => {
      const result = res.data;
      if (result.status == "success") {
        setMedicines(result.data);
        console.log(res);
      } else {
        console.log("unable to fetch result");
      }
    });
  };

  const togleDataFlag = () => {
    setDataChangeFlagRemoveMedicine(true);
  };
  useEffect(() => {
    GetAllMedicinesFromServer();
  }, [dataChangeFlagRemoveMedicine]);
  /**==================================================================== */
  return (
    <div className="">
      {/* <Button variant="primary" onClick={handleShow}>
      Launch static backdrop modal
    </Button> */}

      <Modal
        show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header>
          <Modal.Title>Add Ward</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <table className="table" style={{ textAlign: "center" }}>
            <tr>
              <th>Medicine Name</th>
              <th>Price</th>
            </tr>
            {medicines.map((medicine) => {
              return (
                <MedicineAdminDetails  medicine={medicine} togleDataFlag={togleDataFlag} />
              );
            })}
          </table>
        </Modal.Body>
        <Modal.Footer>
          <div style={{ position: "relative", left: "-120px" }}>
            <Button
              size="sm"
              variant="warning"
              onClick={() => {
                setRemoveMedicineModalFlag(false);
                setShow(false);
              }}
            >
              Go back
            </Button>
          </div>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default RemoveMedicineModal;
