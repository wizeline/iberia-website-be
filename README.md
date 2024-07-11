# iberia-website-be
Iberia Website PoC

# Endpoints:

## Generate image url (currently on develop):
Accepts the desired prompt and the engine to be used:

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