package asteroids;


import asteroids.control.Controller;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            Controller controller = new Controller();
            controller.run() ;
        }catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
