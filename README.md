# Localization Platform

This project is a Spring Boot application built with a hexagonal architecture. It receives text content, translates it via an external API (using MyMemory in this demo), and returns the translated result. 

By using Ports and Adapters, the core business logic remains independent of external concerns like HTTP requests or third-party APIs, making the system easier to test, maintain, and update.


## Exploring Translation APIs

Currently, we use the MyMemory API since it's free for demo purposes. However, here are some alternatives:

**Amazon Translate:** Which is a scalable neural machine translation with pricing based on characters translated.

Another option is using open-source large language models (LLMs) like [MarianMT](https://huggingface.co/docs/transformers/en/model_doc/marian) via Hugging Face. This could be a cost-effective solution compared to AWS's cloud translation service

I have added some costs in below that goes through a scenario of a user uploading 5 videos a day for 7 days and what it looks like at scale:
- If a video produces 2,000 characters and a user uploads 5 videos a day (10,000 characters daily or 70,000 weekly), at \$15 per million characters, it costs about \$1.05 per user per week. Scaling to 1,000 users would be around \$1,050 weekly—highlighting the importance of cost management.

## Running the Project Locally

### Prerequisites
- **Java JDK 21 or higher:**  
  I recommend [Eclipse Temurin](https://adoptium.net/).
- **Docker:**  
  Needed to containerize and run the project.
- **Internet Connection:**  
  To access the MyMemory Translation API.

### Running with Docker

From the project root, simply run:

```bash
./scripts/build-and-run-w-docker.sh
```

## API Endpoints

### POST /api/v1/localization/translate

**Request Example:**

```json
{
  "sourceLanguage": "en",
  "targetLanguage": "es",
  "content": "Hello world"
}
```

**Response Example:**

```json
{
  "translatedContent": "Hola mundo"
}
```

With this hexagonal design, adding new features or swapping translation services becomes a smooth process, keeping the system both flexible and scalable.

There is a lot missing from this project still but it gives a solid entry point to adapt on adding in:
- Authentication
- CI/CD
- Terraform to manage AWS Infrastructure
- The correct translation service we will be using
- A postgres persistence layer
- Alerting/Logging

---