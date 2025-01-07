Here's an updated version of the README with the names of your colleagues added:

---

# Stadium Reservation System

A simple Java application for managing stadium reservations. The system allows users to reserve stadiums, view available stadiums, and manage reservations through an admin interface. Data is stored in plain text files (`stades.txt`, `reservations.txt`, and `admins.txt`).

---

## Features

### 1. **Reserve a Stadium**
- Users can view all available stadiums from `stades.txt`.
- Reserve a stadium by entering the required details:
  - Stadium Name
  - Reservation Date and Time
  - Team Name
  - Bank Transaction Number
- Reservations are stored in `reservations.txt`.
- The stadium's availability status in `stades.txt` is updated to `unavailable`.

### 2. **View Available Stadiums**
- Displays all stadiums marked as `disponible` in `stades.txt`.

### 3. **Admin Features**
Admin users can log in to access advanced management features:
- **Cancel Reservation**: Cancel a reservation and mark the stadium as `disponible`.
- **Add Stadium**: Add new stadiums to `stades.txt`.
- **Edit Stadium**: Modify details of existing stadiums.
- **Delete Stadium**: Remove a stadium from `stades.txt`.

### 4. **Exit**
- Exit the application.

---

## File Descriptions

### 1. `stades.txt`
Stores stadium information in the format:
```
StadiumName:Location:Availability
```
Example:
```
Stade1:Algiers:disponible
Stade2:Oran:unavailable
```

### 2. `reservations.txt`
Stores reservation details in the format:
```
StadiumName,FirstName,LastName,Email,TeamName,Date,Hour,BankTransactionNumber
```
Example:
```
Stade1,John,Doe,john.doe@example.com,TeamA,2025-01-15,15:00,12345678
```

### 3. `admins.txt`
Stores admin credentials in the format:
```
Username,Password
```
Example:
```
admin,admin123
```

---

## How to Use

1. **Run the Program**
   - Compile and run the `ReservationDesStades` class.

2. **Main Menu Options**
   - Choose from:
     - Reserve a Stadium
     - View Available Stadiums
     - Admin Login
     - Exit

3. **Admin Login**
   - Log in using credentials from `admins.txt`.
   - Access options to manage reservations and stadiums.

4. **Text File Data**
   - Ensure `stades.txt`, `reservations.txt`, and `admins.txt` exist in the working directory.

---

## Example Workflow

1. **Reserve a Stadium**
   - View available stadiums.
   - Reserve a stadium with required details.
   - Verify `reservations.txt` and `stades.txt` are updated.

2. **Cancel a Reservation**
   - Log in as admin.
   - Cancel the reservation by entering the stadium and team name.
   - Verify the reservation is removed from `reservations.txt` and marked `disponible` in `stades.txt`.

3. **Add, Edit, or Delete a Stadium**
   - Use admin options to modify stadium data in `stades.txt`.

---

## Requirements

- Java Development Kit (JDK)
- Text files: `stades.txt`, `reservations.txt`, `admins.txt`

---

## Contributing

1. Fork this repository.
2. Create a new branch: `git checkout -b feature-branch`.
3. Make changes and commit: `git commit -m 'Add new feature'`.
4. Push to the branch: `git push origin feature-branch`.
5. Submit a pull request.

---

## License

This project is licensed under the MIT License.

---

## Team Members
- Hachim Fernane
- Soheyb Radjetti
- Khetatba Ilyes

---

You can now find all the project details and resources in the GitHub repository: [https://github.com/hachim112/gestion-des-stades](https://github.com/hachim112/gestion-des-stades)
