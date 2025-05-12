/**• Program will be responsible for starting the application via its main()
 method and then creating the user interface and getting it started. **/

package com.pluralsight;

public class Program {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(); // create the UI
        userInterface.display(); // start the menu loop
    }
}
