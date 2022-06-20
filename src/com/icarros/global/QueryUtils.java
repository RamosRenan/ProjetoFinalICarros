package com.icarros.global;

public class QueryUtils {

	public static final String QUERY_GET_ALL_CORRENTISTA = "SELECT * FROM correntista.correntista";

	public static final String QUERY_GET_CORRENTISTA_BY_ID = "SELECT * FROM correntista.correntista where id = %d";
	
	public static final String INSERT_FLUXO_CAIXA = "INSERT INTO correntista.fluxo_caixa VALUES"
													+ " (%d, %d, %d, %s, %s);";
	
	public static final String UPDATE_CORRENTISTA = "UPDATE correntista.correntista"
													+ " SET nome = '%s', email = '%s', telefone = '%s', saldo = '%s'"
													+ " WHERE ag = %d AND conta = %d;";
}
