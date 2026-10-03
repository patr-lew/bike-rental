export interface BookingRequest {
  bikeId: string;
  userName: string;
}

export interface BookingResponse {
  uuid: string;
  bikeId: string;
  bikeManufacturer: string;
  bookedBy: string;
}
