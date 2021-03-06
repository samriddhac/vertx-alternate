package demo.bank.core.account.dao.util;

public interface IQuery {

	interface ACCOUNT {
		String FIND_BY_CUSTID = "SELECT ACCT_ID, ACCT_NO, ACCT_ALLW_SCHD_TXN, T_ACCT_TYPE.ACCT_TYPE_ID, "
				+ "ACCT_TYPE_DESC, T_CUST.CUST_ID, CUST_NAME FROM T_ACCT, T_ACCT_TYPE, T_CUST "
				+ "WHERE T_ACCT.ACCT_TYPE_ID = T_ACCT_TYPE.ACCT_TYPE_ID AND T_ACCT.CUST_ID = T_CUST.CUST_ID "
				+ "AND T_ACCT.CUST_ID = ?";
		
		String FIND_BY_ACCID = "SELECT ACCT_ID, ACCT_NO, ACCT_ALLW_SCHD_TXN, T_ACCT_TYPE.ACCT_TYPE_ID, "
				+ "ACCT_TYPE_DESC, T_CUST.CUST_ID, CUST_NAME FROM T_ACCT, T_ACCT_TYPE, T_CUST "
				+ "WHERE T_ACCT.ACCT_TYPE_ID = T_ACCT_TYPE.ACCT_TYPE_ID AND T_ACCT.CUST_ID = T_CUST.CUST_ID "
				+ "AND ACCT_ID = ?";
		
		String FIND_BALANCE = "SELECT ACCT_ID, SUM(ACCT_TXN_AMT) AS AVAILABLE_BALANCE, ACCT_TXN_TYPE_DESC "
				+ "FROM T_ACCT_TXN, T_ACCT_TXN_SUBTYPE, T_ACCT_TXN_TYPE "
				+ "WHERE T_ACCT_TXN_SUBTYPE.ACCT_TXN_SUBTYPE_ID = T_ACCT_TXN.ACCT_TXN_SUBTYPE_ID "
				+ "AND T_ACCT_TXN_TYPE.ACCT_TXN_TYPE_ID = T_ACCT_TXN_SUBTYPE.ACCT_TXN_TYPE_ID "
				+ "AND ACCT_ID = ? GROUP BY ACCT_ID, ACCT_TXN_TYPE_DESC";
	}
}