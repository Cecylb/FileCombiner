package fileFetcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Incorrect number of arguments, enter the files folder path and the output file path");
            System.exit(1);
        }
        final String path = args[0];
        final String outputPath = args[1];
        String extension = "txt";
        List<File> fileList = FileWorker.fetchAllFilesWithExtension(path, new ArrayList<>(), extension);
        List<File> sortedFileList = FileWorker.sort(fileList);
        File output = new File(outputPath);
        FileWorker.mergeFiles(sortedFileList, output);
    }
}
