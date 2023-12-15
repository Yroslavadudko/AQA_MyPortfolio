package customexceptions;

public class PropertiesFileNotFoundException extends RuntimeException{
    public PropertiesFileNotFoundException(String pathToProperties, Throwable cause) {
        super(String.format("Properties file was not found at path:%s " +
                "%nPlease check if file exist or check correctness of path! %nCause: %s", pathToProperties, cause.getMessage()));
    }
}
