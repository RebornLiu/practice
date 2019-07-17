package com.reborn.parse.visito;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import java.util.regex.Pattern;

public class IntegerLiteralModifier extends ModifierVisitor<Void> {

    @Override
    public FieldDeclaration visit(FieldDeclaration n, Void arg) {
        super.visit(n, arg);
        n.getVariables().forEach(v -> {
            v.getInitializer().ifPresent(i -> {
                if (i instanceof IntegerLiteralExpr) {
                    v.setInitializer(formatWithUnderscores(((IntegerLiteralExpr)i).getValue()));
                }
            });
        });

        return n;
    }

    static String formatWithUnderscores(String value) {
        Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");
        String withoutUnderscores = value.replaceAll("_", "");
        return LOOK_AHEAD_THREE.matcher(withoutUnderscores).replaceAll("$1_");
    }

}
