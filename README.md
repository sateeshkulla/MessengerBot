# Facebook MessengerBot using Spring Boot

## Step: 1 
  Start the app using **mvn spring-boot:run**, this will run the embedded **Tomcat Server on port 8080**. You can change to a different port in file **src/main/resources/application.properties. 
  
  Update the **facebook_page_access_token** value with **Page Access Token** after configuring the webhooks in Step 3 and restart the server.

## **Step: 2 Create Facebook developer account and Page for bot**

**Facebook Page** will be used as the identity of your bot. When people chat with your app, they will see the Page name and the Page profile picture. To create a new Page, visit https://www.facebook.com/pages/create.

**Developer account**, it is required to create new apps, which are the core of any Facebook integration. You can create a new developer account by going to https://developers.facebook.com

Create a **New App** in the developer account.

## Step: 3 Configure the Webhook and messenger

Visit the App Dashboard in the developer account https://developers.facebook.com/apps

Click on the Products (+) and then click on the Messenger Set Up Button.

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/Add_product.png)


In the Messenger settings page scroll down to Webhook Section, click on Subscribe to Events button to configure the webhook created in Step 1.

Enter the Webhook URL and enter the **FACEBOOK_WEBHOOK_VERIFY_KEY** token value from the ENV variable.

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/configure_webhook.png)

Select **messages** and **messaging_postbacks** Click on Verify and Save button and webhooks will be added to Products. This will call the GET method of Webhook to verify the URL.

https://cfdc8490.ngrok.io is the Local URL exposed by ngrok

Go to the **Access Tokens** Section in the Messenger Settings, Select the Facebook Page you want to subscribe to this app. This is the Page that you want your webhook to receive events for when people on Messenger chat with it. (If there is no page, create new one clicking on Create a new page link). 

Copy the **Page Access Token** and update the ENV variable FACEBOOK_PAGE_ACCESS_TOKEN

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/page_access_token.png)

Go to the **Webhook Section**, select the Page from the drop down and click on the Subscribe button

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/subscribe_webhook.png)

In **NLP Section** enable NLP for the page: NLP is used to identify email, city, postal code information

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/enable_nlp.png)

## **Test the Bot**

Login to Facebook messenger and search for the page we you just created.

Now when you send a message to the Bot, it will reply with a Welcome message and two buttons. When you click on any button it will ask to confirm the email you provided on your account page and finally will send We sent Offers…. message (No Email will be sent it's just a test message).

Whitelist the Truecar domain. Click Settings at the top of your Page Click Messenger Platform on the left
Edit whitelisted domains for your page in the Whitelisted Domains section Add https://www.truecar.com/

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/search_page.png)

![alt text](https://github.com/sateeshkulla/MessengerBot/blob/master/docs/images/mesenger_message.jpg)

After testing submit the App for the review, https://developers.facebook.com/docs/apps/managing-development-cycle/

## References
For a more in-depth explanation on how to build a Facebook bot, check out Facebook's own quickstart guide: https://developers.facebook.com/docs/messenger-platform/getting-started/quick-start
