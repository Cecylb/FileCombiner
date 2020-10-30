package fileFetcher;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorker {

    // Also can be done with apache commons IO
    public static List<File> fetchAllFilesWithExtension(String path, List<File> files, String extension) {
        File filepath = new File(path);
        File[] filesAndDir = filepath.listFiles();
        if (filesAndDir != null) {
            for (File file : filesAndDir) {
                if (file.isFile() && file.getName().matches(".*\\." + extension)) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    fetchAllFilesWithExtension(file.getAbsolutePath(), files, extension);
                }
            }
        }
        return files;
    }

    public static List<File> sort(final List<File> fileList) {
        return fileList.stream().sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
    }

    public static void mergeFiles(List<File> input, File output) {
        try(PrintWriter outputWriter = new PrintWriter(output)) {
            input.forEach(file -> writeFile(file, outputWriter));
            outputWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(File inputFile, PrintWriter outputWriter) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null) {
                outputWriter.println(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
