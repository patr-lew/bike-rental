import { Bike } from "../models/Bike.ts";
import { useCallback, useEffect, useState } from "react";
import { getAllBikes } from "../services/bikeApi.service.ts";
import { RentingModal } from "./RentingModal.tsx";

export function Content() {
  const [bikes, setBikes] = useState<Bike[]>([]);
  const [selectedBike, setSelectedBike] = useState<Bike | null>(null);

  const fetchBikes = useCallback(() => {
    getAllBikes().then((bikes) => setBikes(bikes));
  }, [setBikes]);

  useEffect(() => {
    fetchBikes();
  }, [fetchBikes]);

  const handleRentPress = (bike: Bike) => {
    setSelectedBike(bike);
    fetchBikes();
  };

  const handleModalClosing = () => {
    setSelectedBike(null);
  };

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Manufacturer</th>
            <th>Rim Size</th>
            <th>Frame Size</th>
            <th>Color</th>
            <th>Rented</th>
            <th>Book me</th>
          </tr>
        </thead>
        <tbody>
          {bikes.map((bike) => (
            <tr key={bike.id}>
              <td>{bike.manufacturer}</td>
              <td>{bike.rimSize}</td>
              <td>{bike.frameSize}</td>
              <td>{bike.color}</td>
              <td>{bike.rented ? "Rented" : "Available"}</td>
              <td>
                <button
                  disabled={bike.rented}
                  onClick={() => handleRentPress(bike)}
                >
                  Rent
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <RentingModal selectedBike={selectedBike} onClose={handleModalClosing} />
    </>
  );
}
