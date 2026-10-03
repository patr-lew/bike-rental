import { Bike } from "../models/Bike.ts";
import { useCallback, useEffect, useState } from "react";
import { getAllBikes } from "../services/bikeApi.service.ts";
import { RentingModal } from "./RentingModal.tsx";
import { BookingSummaryModal } from "./BookingSummaryModal.tsx";

export function Content() {
  const [bikes, setBikes] = useState<Bike[]>([]);
  const [selectedBike, setSelectedBike] = useState<Bike | null>(null);
  const [isRentingModalOpen, setIsRentingModalOpen] = useState(false);
  const [isSummaryModalOpen, setIsSummaryModalOpen] = useState(false);

  const fetchBikes = useCallback(() => {
    getAllBikes().then((bikes) => setBikes(bikes));
  }, [setBikes]);

  useEffect(() => {
    fetchBikes();
  }, [fetchBikes]);

  const handleRentPress = (bike: Bike) => {
    setSelectedBike(bike);
    setIsRentingModalOpen(true);
  };

  const handleSummaryPress = (bike: Bike) => {
    setSelectedBike(bike);
    setIsSummaryModalOpen(true);
  };

  const handleRentModalClosing = () => {
    setSelectedBike(null);
    setIsRentingModalOpen(false);
  };

  const handleSummaryModalClosing = () => {
    setSelectedBike(null);
    setIsSummaryModalOpen(false);
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
              <td className={bike.rented ? "rented-bike" : "available-bike"}>
                {bike.rented ? "Rented" : "Available"}
              </td>
              <td>
                {bike.rented ? (
                  <button onClick={() => handleSummaryPress(bike)}>
                    Summary
                  </button>
                ) : (
                  <button onClick={() => handleRentPress(bike)}>Rent</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <RentingModal
        isOpen={isRentingModalOpen}
        selectedBike={selectedBike}
        onClose={handleRentModalClosing}
        onBooking={fetchBikes}
      />
      <BookingSummaryModal
        selectedBike={selectedBike}
        isOpen={isSummaryModalOpen}
        onClose={handleSummaryModalClosing}
      />
    </>
  );
}
