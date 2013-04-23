package thgg.android.protocolo.util;

import java.util.Calendar;

public class StringUtils {

    public static String lpad(String valueToPad, String filler, int size) {  
        while (valueToPad.length() < size) {  
            valueToPad = filler + valueToPad;  
        }  
        return valueToPad;  
    }  
      
    public static String rpad(String valueToPad, String filler, int size) {  
        while (valueToPad.length() < size) {  
            valueToPad = valueToPad+filler;  
        }  
        return valueToPad;  
    }
    
	public static Calendar getCalendar(String sData, String sHora) {
		Calendar data = Calendar.getInstance(); 

		data.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sData.substring(8, 10)));
		data.set(Calendar.MONTH, Integer.parseInt(sData.substring(5, 7)));
		data.set(Calendar.YEAR, Integer.parseInt(sData.substring(0, 4)));
		data.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sHora.substring(0, 2)));
		data.set(Calendar.MINUTE, Integer.parseInt(sHora.substring(3, 5)));
		return data;
	}
	
	public static String getHora(Calendar data) {
		String sTime = StringUtils.lpad(String.valueOf(data.get(Calendar.HOUR_OF_DAY)), "0", 2) + ":" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.MINUTE)), "0", 2);
		return sTime;
	}

	public static String getData(Calendar data) {
		String sData =  StringUtils.lpad(String.valueOf(data.get(Calendar.YEAR)), "0", 4) + "-" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.MONTH)), "0", 2) + "-" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.DAY_OF_MONTH)), "0", 2);
		return sData;
	}
	
	public static String getDataVisual(Calendar data) {
		String sData = StringUtils.lpad(String.valueOf(data.get(Calendar.DAY_OF_MONTH)), "0", 2) + "/" +
				StringUtils.lpad(String.valueOf(data.get(Calendar.MONTH)), "0", 2) + "/" +
				StringUtils.lpad(String.valueOf(data.get(Calendar.YEAR)), "0", 4);
		return sData;
	}
	
	public static String getDataHora(Calendar data) {
		String sData = StringUtils.lpad(String.valueOf(data.get(Calendar.DAY_OF_MONTH)), "0", 2) + "/" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.MONTH)+1), "0", 2) + "/" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.YEAR)), "0",  4) + " " +
			StringUtils.lpad(String.valueOf(data.get(Calendar.HOUR_OF_DAY)), "0",  2) + ":" +
			StringUtils.lpad(String.valueOf(data.get(Calendar.MINUTE)), "0", 2) + "h";
		return sData;
	}

}
