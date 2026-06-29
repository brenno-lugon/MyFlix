package domain.exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public static UserException alreadyExists(String name) {
        return new UserException("Usuário '" + name + "' já está cadastrado.");
    }
}
