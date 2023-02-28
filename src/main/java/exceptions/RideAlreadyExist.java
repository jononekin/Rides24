package exceptions;
public class RideAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public RideAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public RideAlreadyExist(String s)
  {
    super(s);
  }
}