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
    // here a notification about an error would be fine, but returning empty list also makes sense.
    // the page should not crash because of a failed request.
    return [];
  }
}

export async function rentBike(
  request: BookingRequest,
): Promise<
  | { success: true; bookingDetail: BookingResponse }
  | { success: false; error: string }
> {
  try {
    const response = await axios.post(BIKE_RENTAL_API + "/bookings", request);
    return { bookingDetail: response.data, success: true };
  } catch (error) {
    console.error(error);
    if (axios.isAxiosError(error) && error.response?.data?.title) {
      return { error: error.response.data.title, success: false };
    }
    return { error: "Something went wrong", success: false };
  }
}
