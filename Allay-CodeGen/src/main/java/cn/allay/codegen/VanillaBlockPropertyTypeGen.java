package cn.allay.codegen;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static cn.allay.codegen.CodeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/8 <br>
 * Allay Project <br>
 */
public class VanillaBlockPropertyTypeGen {

    private static final Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/block/property");

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        for (CodeGen.BlockPropertyTypeFile.BlockPropertyTypeInfo blockPropertyTypeInfo : BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.values()) {
            if (blockPropertyTypeInfo.valueType == CodeGen.BlockPropertyTypeFile.BlockPropertyType.ENUM) {
                generateEnumClass(blockPropertyTypeInfo);
            }
        }
        //generate VanillaBlockProperties.java
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder("VanillaBlockPropertyTypes")
                .addJavadoc(
                        "@author: daoge_cmd <br>\n" +
                                "Automatically generated by {@code cn.allay.codegen.VanillaBlockPropertyTypeGen} <br>\n" +
                                "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var enumPropertyClass = ClassName.get("cn.allay.api.block.property.type", "EnumPropertyType");
        var booleanPropertyClass = ClassName.get("cn.allay.api.block.property.type", "BooleanPropertyType");
        var intPropertyClass = ClassName.get("cn.allay.api.block.property.type", "IntPropertyType");
        for (var entry : BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.entrySet()) {
            var key = entry.getKey().toUpperCase();
            var blockPropertyTypeInfo = entry.getValue();
            var name = blockPropertyTypeInfo.serializationName;
            switch (blockPropertyTypeInfo.valueType) {
                case ENUM -> {
                    var enumClass = ClassName.get("cn.allay.api.block.property.enums", blockPropertyTypeInfo.getEnumClassName());
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(ParameterizedTypeName.get(enumPropertyClass, enumClass), key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S ,$T.class ,$T.values()[0])", enumPropertyClass, name, enumClass, enumClass)
                                    .build()
                    );
                }
                case BOOLEAN -> {
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(booleanPropertyClass, key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S, $N)", booleanPropertyClass, name, blockPropertyTypeInfo.values.get(0))
                                    .build()
                    );
                }
                case INTEGER -> {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;
                    for (var value : blockPropertyTypeInfo.values) {
                        var intValue = Integer.parseInt(value);
                        if (intValue < min) {
                            min = intValue;
                        }
                        if (intValue > max) {
                            max = intValue;
                        }
                    }
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(intPropertyClass, key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S, $L, $L, $L)", intPropertyClass, name, min, max, blockPropertyTypeInfo.values.get(0))
                                    .build()
                    );
                }
            }
        }
        var propertyClass = ClassName.get("cn.allay.api.block.property.type", "BlockPropertyType");
        var listClass = ParameterizedTypeName.get(ClassName.get("java.util", "List"), ParameterizedTypeName.get(propertyClass, WildcardTypeName.subtypeOf(Object.class)));
        String paramStr = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.keySet().stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        codeBuilder.addField(
                FieldSpec
                        .builder(listClass, "VALUES", Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                        .initializer("List.of($N)", paramStr)
                        .build()
        );
        codeBuilder.addMethod(
                MethodSpec
                        .methodBuilder("values")
                        .returns(listClass)
                        .addStatement("return VALUES")
                        .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                        .build()
        );
        var javaFile = JavaFile.builder("cn.allay.api.data", codeBuilder.build()).build();
        Files.writeString(Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaBlockPropertyTypes.java"), javaFile.toString());
    }

    @SneakyThrows
    protected static void generateEnumClass(CodeGen.BlockPropertyTypeFile.BlockPropertyTypeInfo info) {
        var enumName = info.getEnumClassName();
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder(enumName)
                .addJavadoc(
                        "@author: daoge_cmd <br>\n" +
                        "Automatically generated by {@code cn.allay.codegen.VanillaBlockPropertyTypeGen} <br>\n" +
                        "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        for (var value : info.values) {
            codeBuilder.addEnumConstant(value.toUpperCase());
        }
        var javaFile = JavaFile.builder("cn.allay.api.block.property.enums", codeBuilder.build()).build();
        var path = FILE_OUTPUT_PATH_BASE.resolve("enums/" + enumName + ".java");
        if (Files.exists(path))
            Files.delete(path);
        Files.writeString(path, javaFile.toString());
    }
}
