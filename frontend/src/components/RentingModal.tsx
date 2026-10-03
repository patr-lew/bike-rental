import { Bike } from "../models/Bike.ts";
import { Modal } from "./Modal.tsx";
import { ChangeEvent, useState } from "react";
import { rentBike } from "../services/bikeApi.service.ts";

type Props = {
  selectedBike: Bike | null;
  onClose: () => void;
};

export function RentingModal({ selectedBike, onClose }: Props) {
  const [username, setUsername] = useState<string>("");

  const handleNameInput = (
    event: ChangeEvent<HTMLInputElement, HTMLInputElement>,
  ) => {
    event.preventDefault();
    setUsername(event.target.value);
  };

  const isFormValid = username !== "";

  const handleFormSubmit = async () => {
    if (!selectedBike) return;
    await rentBike({ userName: username, bikeId: selectedBike.id });
  };

  if (selectedBike === null) return null;

  return (
    <Modal onClose={onClose}>
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

          <button disabled={!isFormValid} type="submit">
            Book
          </button>
        </form>
      </div>
    </Modal>
  );
}
