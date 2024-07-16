# iberia-website-be

Iberia Website PoC

How to start:  
Create configuration.env and add the api keys. For example:

OPEN_KEY=sk-proj-etc  
FREE_KEY=other_key

Go to [DemoiberiaApplication.java](src%2Fmain%2Fjava%2Fcom%2Fwizeline%2Fdemoiberia%2FDemoiberiaApplication.java)  
Click on the green arrow and run.  
Add to the IDEA run configuration the configuration.env route you created in the environment variables field

# Endpoints:

## Generate image url (working):

Accepts the desired prompt and the engine to be used.
Returns url of the image:

**Engines supported:**

- **open** (OpenAI)
- **free** (FreePik)

curl --location 'http://localhost:8080/generate' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
"prompt": "soltero playa",
"engine": "open"
}'

## Save image (working):

Saves image selected and accepted by the user

If the combination of destination and tag already exists, replaces with the new url

Returns same url if saving was done successfully:

    curl --location 'http://localhost:8080/save' \
    --header 'Accept: application/json' \
    --header 'Content-Type: application/json' \
    --data '{
    "url": "https://shotstack-create-api-v1-assets.s3.amazonaws.com/xoypme01ob/01j2b-pbqs3-80g28-3ez7j-n6y6ee.png",
    "tag": "business",
    "destination": "Paris"
    }'

## Retrieve images (working):

Retrieves all images loaded by the backend and saved by the user

To load images on the application start, use the /resources/data.sql file.

    curl --location 'http://localhost:8080/retrieve' \
    --header 'Accept: application/json'

Response example:

    [
        {
        "url": "https://shotstack-create-api-v1-assets.s3.amazonaws.com/xoypme01ob/01j2b-pbqs3-80g28-3ez7j-n6y6ee.png",
        "tag": "family",
        "destination": "France"
        },
        {
        "url": "https://shotstack-create-api-v1-assets.s3.amazonaws.com/xoypme01ob/01j2b-qhky5-tfvex-p6ymr-y9a8ry.png",
        "tag": "family",
        "destination": "Rome"
        },
        {
        "url": "https://wizeline.okta.com/2",
        "tag": "soltero",
        "destination": "ibiza"
        }
    ]

## Retrieve videos (working):

Retrieves all videos loaded by the backend and saved by the user

To load videos on the application start, use the /resources/data.sql file.

    curl --location 'http://localhost:8080/videos' \
    --header 'Accept: application/json'

Response example:

    [
        {
        "url": "https://www.video-link.com",
        "tag": "family",
        "destination": "France"
        },
        {
        "url": "https://www.video-link2.com",
        "tag": "family",
        "destination": "Amsterdam"
        }
    ]