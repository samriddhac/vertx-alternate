package demo.bank.core.account.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link demo.bank.core.account.model.Customer}.
 * NOTE: This class has been automatically generated from the {@link demo.bank.core.account.model.Customer} original class using Vert.x codegen.
 */
public class CustomerConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Customer obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "id":
          if (member.getValue() instanceof Number) {
            obj.setId(((Number)member.getValue()).intValue());
          }
          break;
        case "name":
          if (member.getValue() instanceof String) {
            obj.setName((String)member.getValue());
          }
          break;
      }
    }
  }

  public static void toJson(Customer obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Customer obj, java.util.Map<String, Object> json) {
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
  }
}
