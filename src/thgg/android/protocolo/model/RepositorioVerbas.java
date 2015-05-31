package thgg.android.protocolo.model;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.util.StringUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioVerbas {

	private static RepositorioVerbas instancia;
	
	private SQLiteDatabase db;
	
	private Context ctx;
	
	private RepositorioVerbas(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
		ctx = newContext;
	}
	
	public static RepositorioVerbas getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioVerbas(newContext);
		}
		return instancia;
	}
	
	public void salvar(Verba verba) {
		if (verba.getId() == 0) {
			inserir(verba);
		} else {
			atualizar(verba);
		}
	}
	
	public void excluir(long id) {
		db.delete("verbas", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Verba procurar(long id) {
		Cursor cursor = db.query("verbas", 
				new String[] {"_id", "cliente", "acao", "valor", "via", "canal", "representante", "representada", "data", "hora"},
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Via via = RepositorioVias.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("via")));
			Canal canal = RepositorioCanais.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("canal")));
			Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
			Representante representante = RepositorioRepresentantes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representante")));
			Representada representada = RepositorioRepresentadas.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representada")));
			
			Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
					cursor.getString(cursor.getColumnIndexOrThrow("hora")));
			
			Verba verba = new Verba(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cliente, 
					cursor.getString(cursor.getColumnIndexOrThrow("acao")),
					cursor.getFloat(cursor.getColumnIndexOrThrow("valor")),
					via,
					canal,
					representante,
					representada,
					data);
			return verba;
		}
		return null;
	}
	
	public ArrayList<Verba> listar(String colunaOrdenar) {
		ArrayList<Verba> listaVerbas = new ArrayList<Verba>();
		Cursor cursor = db.query("verbas", 
				new String[] {"_id", "cliente", "acao", "valor", "via", "canal", "representante", "representada",  "data", "hora"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Verba verba;
			while (!cursor.isAfterLast()) {
				Via via = RepositorioVias.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("via")));
				Canal canal = RepositorioCanais.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("canal")));
				Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
				Representante representante = RepositorioRepresentantes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representante")));
				Representada representada = RepositorioRepresentadas.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representada")));
				
				Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
						cursor.getString(cursor.getColumnIndexOrThrow("hora")));
				
				verba = new Verba(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cliente, 
						cursor.getString(cursor.getColumnIndexOrThrow("acao")),
						cursor.getFloat(cursor.getColumnIndexOrThrow("valor")),
						via,
						canal,
						representante,
						representada,
						data);				
				listaVerbas.add(verba);
				cursor.moveToNext();
			}
		}
		return listaVerbas;
	}
	
	public double getValorResumo(long idCliente, long idCanal, long idRepresentante, Calendar dataIni, Calendar dataFim ) {
//		ArrayList<Verba> listaVerbas = new ArrayList<Verba>();
		double total = 0.0;
		
		Cursor cursor = db.query("verbas", 
				new String[] {"cliente", "canal", "representante", "valor"}, 
				"(data>='"+ StringUtils.getData(dataIni) + "' and data<='" + StringUtils.getData(dataFim) + "')" 
				+ (idCliente > 1 ? " and cliente=" + idCliente : "") 
				+ (idRepresentante > 1 ? " and representante=" + idRepresentante : "") 
				+ (idCanal > 1 ? " and canal=" + idCanal : ""),
				null, 
				null, 
				null, 
				null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			
//			Canal canal = RepositorioCanais.getRepositorio(ctx).procurar(idCanal);
//			Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(idCliente);
//			Representante representante = RepositorioRepresentantes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representante")));
			
			while (!cursor.isAfterLast()) {
//				Via via = RepositorioVias.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("via")));

				
//				Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
//						cursor.getString(cursor.getColumnIndexOrThrow("hora")));
				
				total += cursor.getLong(cursor.getColumnIndexOrThrow("valor"));
				cursor.moveToNext();
			}
		}
		return total;
	}
	
	private void inserir(Verba verba) {
		ContentValues cv = new ContentValues();
		cv.put("cliente", verba.getCliente().getId());
		cv.put("acao", verba.getAcao());
		cv.put("valor", verba.getValor());
		cv.put("via", verba.getVia().getId());
		cv.put("canal", verba.getCanal().getId());
		cv.put("representante", verba.getRepresentante().getId());
		cv.put("representada", verba.getRepresentada().getId());
		cv.put("data", StringUtils.getData(verba.getData()));
		cv.put("hora", StringUtils.getHora(verba.getData()));
		
		db.insert("verbas", null, cv);
	}
	
	private void atualizar(Verba verba) {
		ContentValues cv = new ContentValues();
		cv.put("_id", verba.getId());
		cv.put("cliente", verba.getCliente().getId());
		cv.put("acao", verba.getAcao());
		cv.put("valor", verba.getValor());
		cv.put("via", verba.getVia().getId());
		cv.put("canal", verba.getCanal().getId());
		cv.put("representante", verba.getRepresentante().getId());
		cv.put("representada", verba.getRepresentada().getId());
		cv.put("data", StringUtils.getData(verba.getData()));
		cv.put("hora", StringUtils.getHora(verba.getData()));
		
		db.update("verbas", cv, "_id=?", new String[]{String.valueOf(verba.getId())});		
	}
	
		
	
	
}
