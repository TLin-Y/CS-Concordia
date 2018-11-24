/**
 * Tianlin Yang
 * 40010303
 * Gaoshuo Cui
 * 40085020
 */

public class CharStackInvalidSizeException extends Exception
{
          public CharStackInvalidSizeException()
          {
                  super("Invalid stack size specified.");
          }
          public CharStackInvalidSizeException (int piStackSize)
          {
                  super ("Invalid stack size specified: " + piStackSize);
           }
}

