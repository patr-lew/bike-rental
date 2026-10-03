import { Bike } from "../models/Bike.ts";
import { Modal } from "./Modal.tsx";
import { ChangeEvent, SubmitEventHandler, useState } from "react";
import { rentBike } from "../services/bikeApi.service.ts";

type Props = {
  selectedBike: Bike | null;
  isOpen: boolean;
  onClose: () => void;
  onBooking: () => void;
};

export function RentingModal({
  selectedBike,
  isOpen,
  onClose,
  onBooking,
}: Props) {
  const [username, setUsername] = useState<string>("");

  const handleNameInput = (
    event: ChangeEvent<HTMLInputElement, HTMLInputElement>,
  ) => {
    event.preventDefault();
    setUsername(event.target.value);
  };

  const isFormValid = username !== "";

  const handleModalClosing = () => {
    setUsername("");
    onClose();
  };

  const handleFormSubmit: SubmitEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();

    if (!selectedBike) return;
    rentBike({ userName: username, bikeId: selectedBike.id }).then(onBooking);
    handleModalClosing();
  };

  if (!isOpen || selectedBike === null) return null;

  return (
    <Modal onClose={handleModalClosing}>
      <div style={{ display: "flex", flexDirection: "column" }}>
        <h3>Bike renting</h3>
        <p>
          Would you like to rent our {selectedBike.color}{" "}
          {selectedBike.manufacturer} bike?
        </p>
        <form
          onSubmit={handleFormSubmit}
          style={{ display: "flex", flexDirection: "column", gap: "1rem" }}
        >
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              gap: "1rem",
            }}
          >
            <label htmlFor={"nameInput"}>Please enter your name</label>
            <input
              id="nameInput"
              name="name"
              style={{ width: "200px" }}
              value={username}
              onChange={handleNameInput}
            />
          </div>

          <div style={{ display: "flex", justifyContent: "space-between" }}>
            <button onClick={onClose} style={{ width: "180px" }}>
              Cancel
            </button>
            <button
              disabled={!isFormValid}
              type="submit"
              style={{ width: "180px" }}
            >
              Book
            </button>
          </div>
        </form>
      </div>
    </Modal>
  );
}
