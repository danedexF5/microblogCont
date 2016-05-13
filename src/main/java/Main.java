import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

import static spark.Spark.halt;

public class Main {
    static HashMap<String, User> users = new HashMap<>();



    public static void main(String[] args) {

        // this creates an end-point for the webroot
        Spark.get(
                "/",
                (request, response) -> {
                    // create a HashMap to hold our model
                    HashMap modelMap = new HashMap();
                    // check if the session contains an element with a key "user"
                    if (request.session().attributes().contains("user")) {

                        // IF SO:
                        // get the user from the session

                        // place the user into the model HashMap
                        modelMap.put("user", request.session().attribute("user"));

                        // return a ModelAndView for the messages template
                        return new ModelAndView(modelMap, "messages.mustache");
                    }


                    // IF NOT:
                    // return a ModelAndView for the index template
                    else {
                        return new ModelAndView(modelMap, "index.mustache");
                    }
                },
                new MustacheTemplateEngine()

        );


        Spark.post(
                "/login",
                (request, response) -> {
                    String userName = request.queryParams("loginName");
                    if (userName == null) {
                        //throw new Exception("Login name not found.");
                        response.redirect("/?loginFailed=true");
                    }
                    String password = request.queryParams("password");
                    if (password == null) {
                        //throw new Exception("password not provided.");
                        response.redirect("/?loginFailed=true");
                    }

                    User user = users.get(userName);
                    if (user == null) {
                        user = new User(userName, password);
                        users.put(userName, user);
                    }

                    if (!user.getPassword().equals(password)) {
                        //throw new Exception("password incorrect");
                        response.redirect("/?loginFailed=true");
                    }


                    // Add the user into the session
                    request.session().attribute("user", user);

                    // Redirect to /
                    response.redirect("/");
                    // halt the request
                    halt();
                    // return an empty string
                    return "";
                }

        );

        Spark.post(
                "/create-user",
                (request, response) -> {

                    // get the loginName value from the request's queryParams
                    String name = request.queryParams("loginName");
                    String password = request.queryParams("loginPassword");
                    // create a new instance of a User for the loginName
                    User user = new User(name, password);
                    // Add the user into the session
                    request.session().attribute("user", user);

                    // Redirect to /
                    response.redirect("/");
                    // halt the request
                    halt();
                    // return an empty string
                    return "";
                }
        );

                    Spark.post(
                            "/create-message",
                            (request, response) -> {
                                // get the user from the session
                                User user = request.session().attribute("user");
                                // get the submitted message text from the request's queryParams
                                String message = request.queryParams("message");
                                // Create a new message for submitted message text
                                Message newMessage = new Message(message);
                                // add the new message to the user's array of messages
                                user.getMessages().add(newMessage);
                                // redirect to the webroot, /
                                response.redirect("/");
                                // halt this request
                                halt();
                                // return an empty string
                                return "";
                            }
                    );


                }

    }