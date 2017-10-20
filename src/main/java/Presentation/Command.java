/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.LegoHouseException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stanislav
 */
abstract class Command {
    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put( "login", new Login() );
        commands.put( "register", new Register() );
        commands.put( "placeOrder", new PlaceOrder());
        commands.put( "calculator", new OrderCalculator());
        commands.put( "redirectCustomerPage", new RedirectCustomerPage());
        commands.put( "redirectEmployeePage", new RedirectEmployeePage());
        commands.put( "invoice", new Invoice());
        commands.put( "sendOrder", new SendOrder());
    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) throws LegoHouseException;

}
