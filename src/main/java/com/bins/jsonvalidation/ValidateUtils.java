package com.bins.jsonvalidation;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.schema.JsonSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songrongbin on 2016/11/9.
 */
public class ValidateUtils {

    //private static final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

    private static final Map<String, JsonSchema> schemas = new HashMap<String, JsonSchema>();

    public static void main(String[] args){
       /* String json = "{\"coupons\":[],\"hasInvoice\":1,\"invoice\":{\"content\":\"明细\",\"titleType\":1,\"type\":1},\"payType\":4,\"orders\":[{\"shop\":{\"id\":14269,\"name\":\"烟雨江南居家日用专营店\",\"type\":1},\"shippingCost\":0,\"orderItems\":[{\"sourceCode\":\"{\\\"adSourceCode\\\":\\\"\\\",\\\"activityNo\\\":\\\"\\\",\\\"sourceCode\\\":\\\"\\\"}\",\"quantity\":1,\"sku\":{\"id\":1889155,\"price\":5900,\"item\":{\"id\":1711301,\"name\":\"超能婴幼儿洗衣液800g\"},\"image\":\"https://i3.meixincdn.com/T15FdTB4VT1RXrhCrK.jpg\",\"attributes\":[{\"name\":\"颜色\",\"value\":\"淡蓝\"},{\"name\":\"规格\",\"value\":\"婴幼儿洗衣液800g\"}],\"outSkuid\":\"\",\"discount\":0},\"discount\":0,\"kid\":\"0\",\"mshop\":{\"id\":4455,\"name\":\"GM_泽的小店\"},\"originFee\":5900}]}],\"gomeMoney\":0,\"delivery\":{\"needConfirmation\":false,\"memo\":\"\",\"receivingTimeType\":1},\"addressId\":26141}";

        JsonSchema schema = getJsonSchema(module, resource, method);
        JsonNode jsonNode = JsonLoader.fromString(jsonStr);
        ProcessingReport report = schema.validate(jsonNode);
        if (report.isSuccess())
            return;*/

    }
/*
    private static JsonSchema getJsonSchema(String module, String resource, String method) throws Exception {
        String path = "/validate/json/" + module + "/" + resource + "_" + method + ".json";
        if (schemas.containsKey(path)) {  //检测内存中存在，直接返回，不用每次加载。
            return schemas.get(path);
        } else {
            JsonNode jsonSchema = JsonLoader.fromResource(path);
            JsonSchema schema = factory.getJsonSchema(jsonSchema);
            schemas.put(path, schema);
            return schema;
        }
    }*/
}
