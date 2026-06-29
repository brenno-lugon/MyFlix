package domain.exception;

public class WatchedMovieException extends RuntimeException {

    public WatchedMovieException(String message) {
        super(message);
    }

    public static WatchedMovieException isMandatory(String campo) {
        return new WatchedMovieException("Campo '" + campo + "' é obrigatório.");
    }
}
