package conexion;

public class Mensaje {
	int instrument;
	int note;
	int time;//in milliseconds
	
	public Mensaje(int _instrument, int _note, int _time) {
		instrument=_instrument;
		note=_note;
		time=_time;
	}
	public Mensaje(Mensaje _mensaje) {
		instrument=_mensaje.instrument;
		note=_mensaje.note;
		time=_mensaje.time;
	}
	
	public Mensaje() {
		instrument=0;
		note=0;
		time=0;
	}
	
	public int getInstrument() {return instrument;}
	public int getNote() {return note;}
	public int getTime() {return time;}
	public void rewrite(int _instrument, int _note, int _time) {
		instrument=_instrument;
		note=_note;
		time=_time;
	}
	public void show() {
		System.out.println("Instrument= "+instrument+"   Note:"+note+"   Time:"+time);
	}
	public String toString(){
		String cadena=instrument+";"+note+";"+time+";";
		return cadena;
	}
	public void fromString(String _values) {
		String[] parts=_values.split(";");
		instrument=Integer.parseInt(parts[0]);
		note=Integer.parseInt(parts[1]);
		time=Integer.parseInt(parts[2]);
		//show();
		
	}
	
}
