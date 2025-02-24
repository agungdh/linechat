# LINE Chat Management for ERP Integration

## Overview
This project provides a solution for managing LINE chat interactions within a Spring Boot application, enabling seamless integration with an existing ERP system. The goal is to facilitate automated responses, customer support, and real-time communication while maintaining data synchronization with the ERP backend.

## Features
- **LINE Bot Integration**: Connects with LINE messaging API for chat automation.
- **ERP System Integration**: Syncs chat data with the existing ERP system.
- **Message Management**: Handles incoming and outgoing messages efficiently.
- **User Authentication**: Secure authentication for accessing chat features.
- **Logging & Monitoring**: Tracks interactions for analysis and reporting.

## Installation
### Prerequisites
- Java 17 or later
- Spring Boot framework
- LINE Developer Account & API Key
- ERP system API access

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/agungdh/linechat.git
   cd linechat
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Configure environment variables:
    - Create an `application.properties` file in `src/main/resources/` and set API keys, ERP credentials, and LINE bot tokens.
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Configuration
Update the `application.properties` file with the following:
```
line.bot.channelToken=your_line_token
line.bot.channelSecret=your_line_secret
erp.api.url=your_erp_api_endpoint
erp.api.key=your_erp_api_key
```

## Usage
- Register your LINE bot on the LINE Developer console.
- Set the webhook URL to your application endpoint.
- Users can interact with the bot, and messages will be processed within the ERP system.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For inquiries, reach out to [GitHub Issues](https://github.com/agungdh/linechat/issues).

