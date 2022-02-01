import axios from "axios";
import React, { useState } from "react";
import { Button } from "react-bootstrap";
import { toast } from "react-toastify";
import { URL } from "../../../config";

const WardAdminDetails = (props) => {
  const { ward ,togleDataFlag} = props;
  
  //delete ward on server
  const removeWard = () => {
    const url = `${URL}/ward/removeWard/${ward.wardId}`;
    axios.get(url).then((res) => {
      const result = res.data;
      console.log(result);
      if (result.status == "success") 
      toast.success("ward removed success");
    });
    togleDataFlag();
  };

  return (
    <tr>
      <td>{ward.type}</td>
      <td>{ward.maxCapacity}</td>
      <Button size="sm" variant="danger" onClick={removeWard} style={{ margin: "6px" }}>
        Remove
      </Button>
    </tr>
  );
};
export default WardAdminDetails;
