import axios from "axios";
import { Bike, BikeResponse } from "../models/Bike.ts";
import { BookingRequest, BookingResponse } from "../models/Booking.ts";

const BIKE_RENTAL_API = "http://localhost:8089/bikerental";

export async function getAllBikes(): Promise<Bike[]> {
  try {
    const response = await axios.get<BikeResponse[]>(
      BIKE_RENTAL_API + "/bikes",
    );
    return response.data.map((bike) => ({
      id: bike.uuid,
      manufacturer: bike.manufacturer,
      rimSize: bike.rimSize,
      frameSize: bike.frameSize,
      color: bike.color,
      rented: bike.rented,
      rentedBy: bike.rentedBy,
    }));
  } catch (error) {
    console.error(error);
    return [];
  }
}

export async function rentBike(
  request: BookingRequest,
): Promise<BookingResponse | null> {
  try {
    const response = await axios.post(BIKE_RENTAL_API + "/bookings", request);
    return response.data;
  } catch (error) {
    console.error(error);
    return null;
  }
}
