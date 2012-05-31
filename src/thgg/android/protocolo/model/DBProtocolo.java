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
				"via integer not null, canal integer not null, consultor integer not null," +
				"data date, hora time)");
		
		//eventos
		db.execSQL("create table eventos(" +
				"_id integer primary key autoincrement, titulo text not null," +
				"cliente integer not null, consultor integer not null, numero_pessoas integer," + 
				"data date, hora time)");
		
		//vias
		db.execSQL("create table vias(" +
				"_id integer primary key autoincrement," +
				"descricao text not null)");
		db.execSQL("insert into vias (descricao) values ('_Não informada')");
		db.execSQL("insert into vias (descricao) values ('Em mãos')");
		db.execSQL("insert into vias (descricao) values ('SEDEX')");
		db.execSQL("insert into vias (descricao) values ('Transportadora')");

		//canais
		db.execSQL("create table canais(" +
				"_id integer primary key autoincrement," +
		"descricao text not null)");
		db.execSQL("insert into canais (descricao) values ('_Não informado')");
		db.execSQL("insert into canais (descricao) values ('Clube')");
		db.execSQL("insert into canais (descricao) values ('Rede')");
		
		//consultores
		db.execSQL("create table consultores(" +
				"_id integer primary key autoincrement," +
		"nome text not null)");
		db.execSQL("insert into consultores (nome) values ('_Não informado')");
		db.execSQL("insert into consultores (nome) values ('Clidenor')");
		db.execSQL("insert into consultores (nome) values ('Fábio')");
		db.execSQL("insert into consultores (nome) values ('Renato')");
		db.execSQL("insert into consultores (nome) values ('Sávio')");

		//clientes
		db.execSQL("create table clientes(" +
				"_id integer primary key autoincrement," +
		"nome text not null)");
		db.execSQL("insert into clientes (nome) values ('_Não informado')");
		db.execSQL("insert into clientes (nome) values ('AGAE')");
		db.execSQL("insert into clientes (nome) values ('Ferreira Costa')");
		db.execSQL("insert into clientes (nome) values ('Ferreira Pinto')");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
