
package org.uganda.constitution.api.model.exception;

/**
 *  Represents an exception thrown after a database operation.
 *
 * @author Jonathan
 */
public class DatabaseException extends Exception{

    public DatabaseException(){}

     public DatabaseException(String message){
        super(message);
     }
     
     public DatabaseException(Throwable cause){
        super(cause);
     }

     public DatabaseException(String message, Throwable cause){
        super(message, cause);
     }


}
