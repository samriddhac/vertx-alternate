package demo.bank.core.account.dao.util;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.json.JsonArray;

public class AccountBalanceEntityMapper {

	public Map<String, Double> mapRow(JsonArray row) {
		Map<String, Double> result = new HashMap<String, Double>();
		result.put(row.getString(2), row.getDouble(1));
		return result;
	}
}
