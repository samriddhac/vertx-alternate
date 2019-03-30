package demo.bank.core.account.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link demo.bank.core.account.model.AccountType}.
 * NOTE: This class has been automatically generated from the {@link demo.bank.core.account.model.AccountType} original class using Vert.x codegen.
 */
public class AccountTypeConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, AccountType obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "description":
          if (member.getValue() instanceof String) {
            obj.setDescription((String)member.getValue());
          }
          break;
        case "id":
          if (member.getValue() instanceof Number) {
            obj.setId(((Number)member.getValue()).intValue());
          }
          break;
      }
    }
  }

  public static void toJson(AccountType obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(AccountType obj, java.util.Map<String, Object> json) {
    if (obj.getDescription() != null) {
      json.put("description", obj.getDescription());
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
  }
}
