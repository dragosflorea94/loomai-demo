This is my sample app for managing user profiles.
I've used Docker to simplify the process of running the app and make sure you can run it.
<br>
The user profile entity I've used here is just a sample so it contains just a few fields,
a real one would be a lot more complex for sure.
<br>
I wrote a few unittests for the service class, I didn't spend time covering all the classes or methods.

<h5> Instructions: </h5>
<ol> 
<li> Start MySQL by running this command
<br>
<code>
docker run -e MYSQL_USER=loomai -e MYSQL_PASSWORD=loomai -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loomaidemo -p 3307:3306 mysql:5.7.8
</code>
</li>
<li> Build the app
<br>
<code>docker build  -t loomai/demo .</code>
<br>
</li>
<li>
Start the app
<br>
<code>
docker run --expose 8080 -p 8080:8080 loomai/demo .
</code>
</li>
</ol>

<h5> Test by doing some requests </h5>
Create user profile
<br>
<code>
curl --request POST 'localhost:8080/userprofile/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "John",
    "lastName": "Doe",
    "alias": "Johnny",
    "country": "US"
}'
</code>
<br>
Get all user profiles
<br>
<code>
curl --request GET 'localhost:8080/userprofile/all'
</code>
<br>
Update the user profile
<br>
<code>
curl --request PUT 'localhost:8080/userprofile/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "firstName": "Jack",
    "lastName": "Doe",
    "alias": "Jack D.",
    "country": "England"
}'
</code>
<br>
Delete the user profile
<br>
<code>
curl --request DELETE 'localhost:8080/userprofile/1'
</code>