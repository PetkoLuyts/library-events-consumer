# library-events-consumer
Consumer microservice for the Library Inventory Application

## Library Inventory Application Architecture

After having a new message published into the library-events topic, we consume it with a kafka consumer. We persist the data in our H2 database

<img width="763" alt="Screenshot 2023-02-25 at 12 44 26" src="https://user-images.githubusercontent.com/35624159/221352657-1a49730f-a8f7-4f0c-b83a-b2f7df5bf1de.png">
