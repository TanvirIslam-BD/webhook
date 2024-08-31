
## What it does

A webhook is a way for an application to provide other applications with real-time information. It allows one system to send data to another system automatically when a specific event occurs. Here's how it typically works:

Setup: You register a URL with the system that will be sending the data. This URL is called the webhook endpoint.

Trigger: When a predefined event happens in the source system (e.g., a new user signs up, a payment is processed, etc.), the source system sends an HTTP request (usually a POST request) to the registered URL.

Processing: The receiving system (your application) processes the data sent by the source system and performs any necessary actions, like updating a database, sending notifications, or triggering other processes.

Webhooks are often used for integrating different services, automating workflows, and handling real-time data. For example, you might use a webhook to automatically update your CRM system when a new lead is generated on a marketing platform.

## How it works:

* You need to register webhook url using this convention.

  ```text
  Webhook url format: 
  https://{application_base_url}/connect/{integration_name}
  
  As hubspot functionality fully implemented it's webhook url will be -   
  Example: 
  https://{application_base_url}/connect/hubspot
  ```
* IntegrationProperty path is being used by this application to call instance when a webhook call is received. 
 
    
