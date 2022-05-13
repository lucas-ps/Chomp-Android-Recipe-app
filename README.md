# ECM2425 Project – "Chomp" Recipe app

This recipe app was my 2nd year Mobile Development project at the University of Exeter. My app idea was to make something that I might actually use, so I decided to make a recipe app as I've been getting more into cooking recently. The aim of the app is to provide an easy user experience, delivering relevant recipes without all the waffle that's on many recipe sites nowadays. I just wanted something simple, no ads, no 10-page blog post for SEO, and no confusing layouts.

# App functionality

https://user-images.githubusercontent.com/75811985/168324831-1ab43f90-b086-489b-b6a2-3225249bdaff.mp4

# Key app components, acknowledgements

The app uses the Spoonacular API ([https://spoonacular.com/](https://spoonacular.com/)) to deliver recipe information.

For communicating with the API, I used 4 main types of files per API request type:

- Adapters – These deal with populating the page with fetched data.
- Models – The java objects that the JSON request is converted to. I used [https://freecodegenerators.com/code-converters/json-to-pojo](https://freecodegenerators.com/code-converters/json-to-pojo) to generate these objects from sample JSON responses.
- Listeners – These are objects used to listen for a response and deal with errors.
- Data – These objects include the actual API calls and methods for executing them, and the retrofit client configuration.

I used the Retrofit and GSON library to get and decode JSON responses. I used the Picasso Library to render images on tabs where images are needed.

## License
[MIT](https://choosealicense.com/licenses/mit/)

