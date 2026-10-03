import { Bike } from "../models/Bike.ts";
import { Modal } from "./Modal.tsx";

type Props = {
  selectedBike: Bike | null;
  isOpen: boolean;
  onClose: () => void;
};

export function BookingSummaryModal({ selectedBike, isOpen, onClose }: Props) {
  if (!isOpen || selectedBike === null) return null;
  return (
    <Modal onClose={onClose}>
      <div style={{ display: "flex", flexDirection: "column" }}>
        <h3>This bike is currently rented by {selectedBike.rentedBy}!</h3>
        <p>
          The bike will be available again as soon as {selectedBike.rentedBy}{" "}
          returns it.
        </p>
        <button
          onClick={onClose}
          style={{ width: "200px", alignSelf: "center" }}
        >
          Close
        </button>
      </div>
    </Modal>
  );
}
