export interface Bike {
  id: string;
  manufacturer: string | null;
  rimSize: number;
  frameSize: number;
  color: string | null;
  rented: boolean;
  rentedBy: string | null;
}

export interface BikeResponse {
  uuid: string;
  manufacturer: string | null;
  rimSize: number;
  frameSize: number;
  color: string | null;
  rented: boolean;
  rentedBy: string | null;
}
