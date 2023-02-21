package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    private static Path getFullPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) throws IOException {

        CompletableFuture<String> file1 = CompletableFuture.supplyAsync(() -> {
            String f1 = null;
            try {
                f1 = Files.readString(getFullPath(src1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return f1;
        }).exceptionally(ex -> {
            System.out.println("Возникло исключение при чтении файла 1: \n" + ex.getMessage());
            return null;
        });


        CompletableFuture<String> file2 = CompletableFuture.supplyAsync(() -> {
            String f2 = null;
            try {
                f2 = Files.readString(getFullPath(src2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return f2;
        }).exceptionally(ex -> {
            System.out.println("Возникло исключение при чтении файла 2: \n" + ex.getMessage());
            return null;
        });

        return file2.thenCombine(file1, (f1, f2) -> {
            String output = f1 + f2;
            try {
                Files.writeString(getFullPath(dest), output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        }).exceptionally(ex -> {
            return null;
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String directory) {
        CompletableFuture<Long> size = CompletableFuture.supplyAsync(() -> {
            Long n;
            try {
                n = Files.list(getFullPath(directory)).count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return n;
        });
        return size;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles(
                "src/resources/main/file1.txt",
                "src/resources/main/file2.txt",
                "src/resources/main/dest.txt"
        );

        CompletableFuture<Long> size = getDirectorySize("src/main");
        size.get();
        System.out.println(size.join());

        result.get();
        // END
    }
}

