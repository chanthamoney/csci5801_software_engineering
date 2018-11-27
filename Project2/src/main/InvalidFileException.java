package main;

public class InvalidFileException extends Exception {
    private static final long serialVersionUID = 4483520665884643068L;

    public InvalidFileException(String message) {
	super(message);
    }
}