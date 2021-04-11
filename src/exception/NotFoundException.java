package exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String what) {
		super(what + "을(를) 찾을 수 없습니다");
	}

}
