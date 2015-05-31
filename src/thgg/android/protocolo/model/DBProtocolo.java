package thgg.android.protocolo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProtocolo extends SQLiteOpenHelper {

	public DBProtocolo(Context newContext) {
		super(newContext, "dbProtocolo", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//documentos
		db.execSQL("create table documentos(" +
				"_id integer primary key autoincrement," +
				"descricao text not null, destino text, via integer not null," + 
				"data date, hora time)");

		//verbas
		db.execSQL("create table verbas(" +
				"_id integer primary key autoincrement," +
				"cliente integer not null, acao text, valor number," + 
				"via integer not null, canal integer not null, representante integer not null, representada integer not null," +
				"data date, hora time)");
		
		//eventos
		db.execSQL("create table eventos(" +
				"_id integer primary key autoincrement, titulo text not null," +
				"cliente integer not null, representante integer not null, numero_pessoas integer," + 
				"data date, hora time)");
		
		//pedidos
		db.execSQL("create table pedidos(" +
				"_id integer primary key autoincrement, numero text not null," +
				"representada integer not null, cliente integer not null, representante integer not null," +
				"valor_total number," + 
				"data date, hora time)");

		//vias
		db.execSQL("create table vias(" +
				"_id integer primary key autoincrement," +
				"descricao text not null)");
		db.execSQL("insert into vias (descricao) values ('_Nao informada')");
		db.execSQL("insert into vias (descricao) values ('Em maos')");
		db.execSQL("insert into vias (descricao) values ('SEDEX')");
		db.execSQL("insert into vias (descricao) values ('Transportadora')");

		//canais
		db.execSQL("create table canais(" +
				"_id integer primary key autoincrement," +
		"descricao text not null)");
		db.execSQL("insert into canais (descricao) values ('_Nao informado')");
		
		//representada
		db.execSQL("create table representadas(" +
				"_id integer primary key autoincrement," +
				"razao_social text not null)");
		db.execSQL("insert into representadas (razao_social) values ('_Nao informado')");
		
		//clientes
		db.execSQL("create table clientes(" +
				"_id integer primary key autoincrement," +
		"nome text not null)");
		db.execSQL("insert into clientes (nome) values ('_Nao informado')");

		//representante
		db.execSQL("create table representantes(" +
				"_id integer primary key autoincrement," +
				"nome text not null)");
		db.execSQL("insert into representantes (nome) values ('_Nao informado')");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
