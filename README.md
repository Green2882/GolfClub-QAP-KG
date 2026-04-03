# GolfClub-QAP-KG

## Supported Search APIs

### Search members by:

- Name: GET /api/1.0.0/members/search?name=John Smith
- Membership type: GET /api/1.0.0/members/search?membershipType=Gold
- Phone number: GET /api/1.0.0/members/search?phoneNumber=1234567890
- Tournament start date: GET /api/1.0.0/members/search?tournamentStartDate=2024-10-01

### Member Endpoints

- Create member: POST /api/1.0.0/members
- Get all members: GET /api/1.0.0/members
- Get single member by id: GET /api/1.0.0/members/{id}
- Update member info: PUT /api/1.0.0/members/{id}
- Delete a member: DELETE /api/1.0.0/members/{id}

### Search tournaments by:

- Start date: GET /api/1.0.0/tournaments/search?startDate=2024-10-01
- Location: GET /api/1.0.0/tournaments/search?location=Avalon Golf Course
- Members signed up: GET /api/1.0.0/tournaments/search?memberId=1

### Tournament Endpoints

- Create tournament: POST /api/1.0.0/tournaments
- Get all tournaments: GET /api/1.0.0/tournaments
- Get tournament by id: GET /api/1.0.0/tournaments/{id}
- Update tournament info: PUT /api/1.0.0/tournaments/{id}
- Delete a tournament: DELETE /api/1.0.0/tournaments/{id}
- Add member to a tournament: POST /api/1.0.0/tournaments/{tournamentId}/add-member/{memberId}

# Running Docker
- Build project - mvn clean package
- Start application - docker compose up --build

# Connecting to RDS
- Create database in RDS
- Copy RDS endpoint
- Add endpoint, username, and password to docker-compose.yml and application.properties

# Issues
I had no issues really, Jordan's support sessions really helped and made the process quite easy


  
