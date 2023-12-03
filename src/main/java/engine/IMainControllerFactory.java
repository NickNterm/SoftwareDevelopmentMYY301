package engine;

public class IMainControllerFactory {

	/**
	 * Regulates which of all the possible implementations of IMainController to use
	 * 
	 * For the moment there is only one. 
	 */
	public enum ControllerTypeEnum{DEFAULT}; //to add more options if new versions appear
	
	
	/**
	 * Returns a new concrete implementation of IMainController 
	 * 
	 * @param controllerType a ControllerTypeEnum that regulates which concrete class to use
	 * @return  a concrete object which is an implementation of IMainController
	 */
	public IMainController createMainController(ControllerTypeEnum controllerType) {
		if (controllerType == ControllerTypeEnum.DEFAULT)
			return new MainControllerImpl(); 
		return null;
	}
}
