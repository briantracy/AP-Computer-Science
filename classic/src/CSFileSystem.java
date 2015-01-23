
public class CSFileSystem
{

    public static final String CS_FILE_EXTENSION = ".cs";

    public static boolean isAcceptableFile(String file)
    {
        return file.contains(CS_FILE_EXTENSION);
    }
}
