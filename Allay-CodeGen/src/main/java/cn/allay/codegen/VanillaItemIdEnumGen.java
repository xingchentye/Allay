package cn.allay.codegen;

import cn.allay.dependence.StringUtils;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

import static cn.allay.codegen.CodeGen.MAPPED_ITEM_DATA;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/13 <br>
 * Allay Project <br>
 */
public class VanillaItemIdEnumGen {
    private static final ClassName STRING_CLASS = ClassName.get("java.lang", "String");
    private static final ClassName GETTER_CLASS = ClassName.get("lombok", "Getter");
    private static final String JAVA_DOC = """
            @author daoge_cmd | Cool_Loong<br>
            Automatically generated by {@code cn.allay.codegen.VanillaItemIdEnumCodeGen} <br>
            Allay Project <br>
            """;

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        generateToDependenceModule();
        generateToAPIModule();
    }

    @SneakyThrows
    public static void generateToDependenceModule() {
        var identifierClass = ClassName.get("cn.allay.dependence", "Identifier");
        TypeSpec.Builder codeBuilder = commonBuilder(identifierClass);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("cn.allay.dependence", codeBuilder.build()).build();
        Files.writeString(Path.of("Allay-CodeGen/src/main/java/cn/allay/dependence/VanillaItemId.java"), javaFile.toString());
    }

    @SneakyThrows
    public static void generateToAPIModule() {
        var identifierClass = ClassName.get("cn.allay.api.identifier", "Identifier");
        var itemTypeRegistryClass = ClassName.get("cn.allay.api.item.type", "ItemTypeRegistry");
        var itemTypeClass = ClassName.get("cn.allay.api.item.type", "ItemType");
        TypeSpec.Builder codeBuilder = commonBuilder(identifierClass).addMethod(MethodSpec.methodBuilder("getItemType")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $T.getRegistry().get(this.getIdentifier())", itemTypeRegistryClass)
                .returns(itemTypeClass)
                .build()
        );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("cn.allay.api.data", codeBuilder.build()).build();
        Files.writeString(Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaItemId.java"),
                javaFile.toString().replace("public ItemType", "public ItemType<?>"));
    }

    private static void addEnums(TypeSpec.Builder codeBuilder) {
        for (var entry : MAPPED_ITEM_DATA.entrySet()) {
            var split = StringUtils.fastTwoPartSplit(
                    StringUtils.fastTwoPartSplit(entry.getKey(), ":", "")[1],
                    ".", "");
            var valueName = split[0].isBlank() ? split[1].toUpperCase() : split[0].toUpperCase() + "_" + split[1].toUpperCase();
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S", entry.getKey()).build());
        }
    }

    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("VanillaItemId")
                .addJavadoc(JAVA_DOC)
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(GETTER_CLASS)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(STRING_CLASS, "identifier")
                        .addStatement("this.$N = new $T($N)", "identifier", identifierClass, "identifier")
                        .build()
                );
    }
}
