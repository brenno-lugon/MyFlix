package domain.exception;

public class MovieException extends RuntimeException {

    public MovieException(String message) {
        super(message);
    }

    public static MovieException alreadyExists(String title) {
        return new MovieException("Filme '" + title + "' já está cadastrado.");
    }

    public static MovieException isMandatory(String campo) {
        return new MovieException("Campo '" + campo + "' é obrigatório.");
    }

    public static MovieException notFound(String id) {
        return new MovieException("Filme com id '" + id + "' não encontrado.");
    }

    public static MovieException invalidReleaseYear(Integer year) {
        return new MovieException("Ano de lançamento deve ser maior que 1888.");
    }
}
