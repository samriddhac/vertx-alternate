package demo.bank.core.account.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link demo.bank.core.account.model.Account}.
 * NOTE: This class has been automatically generated from the {@link demo.bank.core.account.model.Account} original class using Vert.x codegen.
 */
public class AccountConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Account obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "allowScheduledTransactions":
          if (member.getValue() instanceof String) {
            obj.setAllowScheduledTransactions((String)member.getValue());
          }
          break;
        case "balance":
          if (member.getValue() instanceof Number) {
            obj.setBalance(((Number)member.getValue()).doubleValue());
          }
          break;
        case "customer":
          if (member.getValue() instanceof JsonObject) {
            obj.setCustomer(new demo.bank.core.account.model.Customer((JsonObject)member.getValue()));
          }
          break;
        case "id":
          if (member.getValue() instanceof Number) {
            obj.setId(((Number)member.getValue()).longValue());
          }
          break;
        case "number":
          if (member.getValue() instanceof String) {
            obj.setNumber((String)member.getValue());
          }
          break;
        case "type":
          if (member.getValue() instanceof JsonObject) {
            obj.setType(new demo.bank.core.account.model.AccountType((JsonObject)member.getValue()));
          }
          break;
      }
    }
  }

  public static void toJson(Account obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Account obj, java.util.Map<String, Object> json) {
    if (obj.getAllowScheduledTransactions() != null) {
      json.put("allowScheduledTransactions", obj.getAllowScheduledTransactions());
    }
    if (obj.getBalance() != null) {
      json.put("balance", obj.getBalance());
    }
    if (obj.getCustomer() != null) {
      json.put("customer", obj.getCustomer().toJson());
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getNumber() != null) {
      json.put("number", obj.getNumber());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType().toJson());
    }
  }
}
