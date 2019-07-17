import com.alibaba.fastjson.JSON;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.reborn.parse.visito.AnnotationPrinter;
import com.reborn.parse.visito.IntegerLiteralModifier;
import com.reborn.parse.visito.MethodNamePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class App {

    private static final String FILE_PATH = "D:\\git3333\\practice\\parse\\src\\main\\java\\com\\reborn\\parse\\source\\Api.java";

    public static void main(String[] args) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        NodeList<ImportDeclaration> importDeclarations = cu.getImports();
        for (ImportDeclaration importDeclaration : importDeclarations) {
            System.out.println(importDeclaration.getName().asString());
        }
        System.out.println("-----------------------");
        NodeList<TypeDeclaration<?>> typeDeclarations =  cu.getTypes();
        PackageDeclaration packageDeclaration = cu.getPackageDeclaration().get();
        System.out.println("package:" + packageDeclaration.getName());
        for (TypeDeclaration typeDeclaration : typeDeclarations) {
            //类名
            System.out.println("接口名:" + typeDeclaration.getName());
            NodeList<BodyDeclaration> bodyDeclarations = typeDeclaration.getMembers();
            for (BodyDeclaration bodyDeclaration : bodyDeclarations) {
                if (bodyDeclaration instanceof MethodDeclaration) {
                    MethodDeclaration methodDeclaration = (MethodDeclaration)bodyDeclaration;
                    System.out.println("方法名:" + methodDeclaration.getName().asString());
                    NodeList<AnnotationExpr> annotations = methodDeclaration.getAnnotations();
                    for (AnnotationExpr expr : annotations) {
                        if (expr instanceof NormalAnnotationExpr) {
                            NormalAnnotationExpr annotationExpr = (NormalAnnotationExpr)expr;
                            NodeList<MemberValuePair> valuePairs = annotationExpr.getPairs();
                            for (MemberValuePair pair : valuePairs) {
                                System.out.println(pair.getName().asString());
                                System.out.println(pair.getValue().asStringLiteralExpr().asString());
                            }
                        }
                    }
                }
            }
           /* NodeList<AnnotationExpr> annotationExprs = typeDeclaration.getAnnotations();
            for (AnnotationExpr annotationExpr : annotationExprs) {
                System.out.println(annotationExpr.getName());
            }*/
            //System.out.println(typeDeclaration.get);
        }
/*        AnnotationPrinter annotationPrinter = new AnnotationPrinter();
        annotationPrinter.visit(cu, null);*/


       /* MethodNamePrinter printer = new MethodNamePrinter();
        printer.visit(cu, null);*/
/*        IntegerLiteralModifier modifier = new IntegerLiteralModifier();
        modifier.visit(cu, null);
        System.out.println(cu.toString());*/
    }
}
