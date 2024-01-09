package poet;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.Modifier;
import java.sql.PreparedStatement;

public class ResultSetStatement {
    public static void main(String[] args) {
        MethodSpec.Builder mainBuilder = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T stmt = targetConn.preparedStatement(sql)", PreparedStatement.class);
        int columnSize = 5;
        int idx = 0;
        for(int cnt=0; cnt<3; cnt++) {
            idx = cnt * columnSize;
            for (int i = 1; i <= columnSize; i++) {
                idx += 1;
                mainBuilder.addStatement("stmt.$N($L, rs.$N($S))", "setString", idx, "getString", "COL1");
            }
        }
        System.out.println(mainBuilder.build());
    }
}
