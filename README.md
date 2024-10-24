AirSense Backend Service

AirSense is a backend service that processes environmental data to provide recommendations on whether to air out or heat a room. It is part of a system that helps maintain optimal indoor climate conditions.
Overview

AirSense is triggered by a broker that receives sensory data from EPS systems. These systems track both temperature and humidity within a specific environment. Every 2.4 hours (planned), the broker sends this data to the AirSense backend service for analysis.

Based on the received data, the service determines whether it is more appropriate to air out or heat the room to improve air quality and comfort. The service uses predefined thresholds for temperature and humidity to make these decisions.

Once the analysis is complete, AirSense sends a notification to a Discord server, alerting all members about the recommended action. Users can receive these notifications on both mobile and desktop devices, ensuring they stay informed wherever they are.
How It Works:

    EPS Systems monitor temperature and humidity.
    Every 2.4 hours, the broker sends the collected data to AirSense.
    AirSense evaluates the data and determines whether to air out or heat the room.
    A notification with the recommendation is sent to the Discord server, where all connected users receive the alert.

Features:

    Real-time environmental analysis: The system provides regular assessments of the room's conditions.
    Automated recommendations: Based on temperature and humidity, AirSense tells you when to air or heat the room.
    Discord notifications: Instant alerts are sent to all server members for seamless communication across devices.

Please keep in mind, it's not the final version and just used for personal usage
