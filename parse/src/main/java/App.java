import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;

public class App {

    private static final String FILE_PATH = "D:\\git3333\\practice\\parse\\src\\main\\java\\com.reborn.parse\\source\\ReversePolishNotation.java";

    public static void main(String[] args) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
    }
}
