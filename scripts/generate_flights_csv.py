import csv
import random
from datetime import datetime, timedelta
from pathlib import Path

random.seed(42)

OUTPUT_PATH = Path("src/main/resources/flights.csv")

AIRLINES = {
    "BA": "British Airways",
    "VS": "Virgin Atlantic",
    "U2": "easyJet",
    "FR": "Ryanair",
    "KL": "KLM",
    "LH": "Lufthansa",
    "AF": "Air France",
    "EK": "Emirates",
}

CITIES = [
    "London",
    "Manchester",
    "Birmingham",
    "Edinburgh",
    "Paris",
    "Amsterdam",
    "Berlin",
    "Madrid",
    "Rome",
    "Dubai",
    "New York",
    "Toronto",
    "Singapore",
    "Tokyo",
    "Dublin",
]

BASE_PRICES = {
    "domestic": (60, 180),
    "europe": (90, 320),
    "longhaul": (350, 950),
}


def route_type(origin: str, destination: str) -> str:
    uk = {"London", "Manchester", "Birmingham", "Edinburgh"}
    europe = {"Paris", "Amsterdam", "Berlin", "Madrid", "Rome", "Dublin"}

    if origin in uk and destination in uk:
        return "domestic"

    if destination in europe or origin in europe:
        return "europe"

    return "longhaul"


def generate_flight_number(existing: set[str]) -> tuple[str, str]:
    while True:
        code = random.choice(list(AIRLINES.keys()))
        number = random.randint(100, 999)
        flight_number = f"{code}{number}"

        if flight_number not in existing:
            existing.add(flight_number)
            return flight_number, AIRLINES[code]


def generate_flights(count: int = 100) -> list[dict[str, str]]:
    flights = []
    used_flight_numbers = set()
    start_date = datetime.now().replace(hour=6, minute=0, second=0, microsecond=0)

    for _ in range(count):
        origin, destination = random.sample(CITIES, 2)
        flight_number, airline = generate_flight_number(used_flight_numbers)

        days_ahead = random.randint(1, 45)
        hour = random.randint(5, 22)
        minute = random.choice([0, 15, 30, 45])
        departure_time = start_date + timedelta(days=days_ahead)
        departure_time = departure_time.replace(hour=hour, minute=minute)

        route = route_type(origin, destination)
        min_price, max_price = BASE_PRICES[route]

        price = round(random.uniform(min_price, max_price), 2)
        total_seats = random.choice([80, 120, 150, 180, 220, 300])
        available_seats = random.randint(0, total_seats)

        flights.append({
            "flightNumber": flight_number,
            "airline": airline,
            "departureCity": origin,
            "arrivalCity": destination,
            "departureTime": departure_time.strftime("%Y-%m-%d %H:%M"),
            "totalSeats": str(total_seats),
            "availableSeats": str(available_seats),
            "price": f"{price:.2f}",
        })

    flights.sort(key=lambda flight: flight["departureTime"])
    return flights


def write_csv(flights: list[dict[str, str]]) -> None:
    OUTPUT_PATH.parent.mkdir(parents=True, exist_ok=True)

    fieldnames = [
        "flightNumber",
        "airline",
        "departureCity",
        "arrivalCity",
        "departureTime",
        "totalSeats",
        "availableSeats",
        "price",
    ]

    with OUTPUT_PATH.open("w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(flights)


if __name__ == "__main__":
    flights = generate_flights(100)
    write_csv(flights)

    print(f"Generated {len(flights)} flights")
    print(f"Saved to: {OUTPUT_PATH}")

