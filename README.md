# Issue Tracker
Project for learning

### Pre requisites
- Install Java
- Install NodeJS
- Install Docker

### How to run
- Run PostgreSQL database instance in docker
    - Prepare docker volume (it is required to store database files)
      ```bash
      docker volume create it-pgdata
      ```
    - Run PostgreSQL
      ```bash
      cd ./issue-tracker-db
      docker-compose up -d
      ```
    - During first run user ```issue_tracker_admin/secret``` and  database ```issuet_tracker_dev``` are created
    - Useful docker commands
      ```bash
      docker-compose down                     # shutdown all services defined in docker-compose.yml
      docker-compose up -d                    # startup all services defined in docker-compose.yml
      docker-compose logs -f issue-tracker-db # tail logs for issue-tracker-db service
      docker volume rm it-pgdata              # remove volume
      docker volume create it-pgdata          # create volume
      ```
- Run issue tracker API
    ```bash
    cd ./issue-tracker-api
    gradlew clean bootRun
    ```
- Run issue tracker UI
    ```bash
    cd ./issue-tracker-ui
    npm install
    npm start
    ```