package thgg.android.protocolo.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FileUtil {
	
	public static void copiaBanco(Context ctx, String nomeDB, String nomeArquivo, boolean restaurar) {  
			  
		// Cria o banco vazio
		SQLiteDatabase db = ctx.openOrCreateDatabase(nomeDB,
				Context.MODE_WORLD_WRITEABLE, null);

		db.close();

		try {
			InputStream is;
			FileOutputStream fos;
			if (!restaurar) {
				// Abre o arquivo que deve estar na pasta assets
	//			InputStream is = ctx.getAssets().open(nomeDB);
				is = new BufferedInputStream(new FileInputStream(new File(nomeDB)));
				// Abre o arquivo do banco vazio ele fica em:
				// /data/data/nome.do.pacote.da.app/databases
				fos = new FileOutputStream(ctx.getDatabasePath(nomeDB));
			} else {
				// Abre o arquivo que deve estar na pasta assets
				//			InputStream is = ctx.getAssets().open(nomeDB);
				is = new BufferedInputStream(new FileInputStream(new File(nomeDB)));
				// Abre o arquivo do banco vazio ele fica em:
				// /data/data/nome.do.pacote.da.app/databases
				fos = new FileOutputStream(ctx.getDatabasePath(nomeDB));
			}
			// Copia byte a byte o arquivo do assets para
			// o aparelho/emulador
			byte[] buffer = new byte[1024];
			int read;
			while ((read = is.read(buffer)) > 0) {
				fos.write(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
