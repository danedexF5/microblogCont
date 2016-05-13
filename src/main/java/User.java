import java.util.ArrayList;

/**
 * Created by danedexheimer on 5/8/16.
 */
public class User {

    // create a name property
    String name;

    // create password property
    String password;

    // create a message property. This should be an ArrayList that holds Message objects.
    ArrayList<Message> messages = new ArrayList<>();

    // Create a constructor that accepts arguments for name and password


    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    // create a getter for name

    public String getName() {
        return name;
    }


    // create a setter for name

    public void setName(String name) {
        this.name = name;
    }

    //getter for password
    public String getPassword() {
        return password;
    }

    //setter for password
    public void setPassword(String password) {
        this.password = password;
    }



    // create a getter for messages.mustache

    public ArrayList<Message> getMessages() {
        return messages;
    }


    // create a setter for messages.mustache

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}