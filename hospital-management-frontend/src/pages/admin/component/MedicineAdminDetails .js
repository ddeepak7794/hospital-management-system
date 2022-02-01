import axios from "axios";
import React, { useState } from "react";
import { Button } from "react-bootstrap";
import { toast } from "react-toastify";
import { URL } from "../../../config";

const MedicineAdminDetails  = (props) => {
  const { medicine ,togleDataFlag} = props;
  
  //delete ward on server
  const removeMedicine = () => {
    const url = `${URL}/medicine/removeMedicine/${medicine.medicineId}`;
    axios.get(url).then((res) => {
      const result = res.data;
      console.log(result);
      if (result.status == "success") 
      toast.success("medicine removed success");
    });
    togleDataFlag();
  };

  return (
    <tr>
      <td>{medicine.medicineName}</td>
      <td>{medicine.medicinePrice}</td>
      <Button size="sm" variant="danger" onClick={removeMedicine} style={{ margin: "6px" }}>
        Remove
      </Button>
    </tr>
  );
};
export default MedicineAdminDetails ;
