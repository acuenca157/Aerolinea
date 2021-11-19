package org.izv.ad.acl.aerolinea2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Vuelo implements Parcelable {
    private static final Double precioSeguro = 20.0;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String numeroTlf;
    private String fecha;
    private String ciudadIda;
    private String ciudadVuelta;
    private String[] extras;
    private boolean diversidadFuncional;
    private boolean seguro;
    private boolean premium;


    //Constructor dle objeto vuelo
    public Vuelo(String nombre, String apellido, String direccion, String email, String numeroTlf, String fecha, String ciudadIda, String ciudadVuelta, String[] extras, boolean diversidadFuncional, boolean seguro, boolean premium) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.numeroTlf = numeroTlf;
        this.fecha = fecha;
        this.ciudadIda = ciudadIda;
        this.ciudadVuelta = ciudadVuelta;
        this.extras = extras;
        this.diversidadFuncional = diversidadFuncional;
        this.seguro = seguro;
        this.premium = premium;
    }

    //Generamos una string con el despliegue completo de todos los gastos
    public String getDespliegue(){
        String res = "";
        res += "Datos del cliente" + '\n';
        res += "Nombre y apellidos: " + nombre + " " + apellido + '\n';
        res += "Teléfono: " + numeroTlf + '\n';
        res += "Dirección: " + direccion + '\n';
        res += "Email: " + email + '\n';
        res += "Datos del Cliente: " + '\n';
        res += "Fecha: " + fecha +  '\n';
        res += "Origen / Destino: " + '\n';
        res +=  ciudadIda + " " + ciudadVuelta + '\n';
        res += "Precio base del viaje: " + getPrecioViaje() + '\n';
        res += "Extras: " + '\n';
        for (String extra : extras) {
            if (extra != null)
                res += '\t' + extra + ": 50€" + '\n';
        }
        res += "Otros Extras: " + '\n';
        if (seguro)
            res += "Seguro: " + precioSeguro + '\n';
        if (diversidadFuncional)
            res += "Descuento Movilidad Funcional: -" + getDescuentoDiversidadFuncional() + '\n';
        if (premium)
            res += "Viaje Premium: " + getPrecioPremium() + '\n';

        res += "Total: " + getTotal() + "€";

        return res;
    }


    protected Vuelo(Parcel in) {
        nombre = in.readString();
        apellido = in.readString();
        direccion = in.readString();
        email = in.readString();
        numeroTlf = in.readString();
        fecha = in.readString();
        ciudadIda = in.readString();
        ciudadVuelta = in.readString();
        extras = in.createStringArray();
        diversidadFuncional = in.readByte() != 0;
        seguro = in.readByte() != 0;
        premium = in.readByte() != 0;
    }

    public static final Creator<Vuelo> CREATOR = new Creator<Vuelo>() {
        @Override
        public Vuelo createFromParcel(Parcel in) {
            return new Vuelo(in);
        }

        @Override
        public Vuelo[] newArray(int size) {
            return new Vuelo[size];
        }
    };

    public Double getPrecioViaje(){
        int seed = 0;
        String values = ciudadIda + ciudadVuelta + fecha;
        for (char c : values.toCharArray()) {
            seed += (int) c;
        }
        Random rand = new Random(seed);
        Double res = rand.nextInt(1000 - 100) + 100.0;
        return res;
    }

    public Double getTotal(){
        return getPrecioViaje() + getPrecioSeguro() + getPrecioTotalExtras() - getDescuentoDiversidadFuncional() + getPrecioPremium();
    }

    public Double getDescuentoDiversidadFuncional(){
        if (diversidadFuncional)
            return 50.0;
        else
            return 0.0;
    }

    public Double getPrecioTotalExtras(){
        Double res = 0.0;

        for (String extra : extras) {
            if (extra != null)
                res += 50;
        }
        return res;
    }

    public Double getPrecioSeguro(){
        if (seguro)
            return precioSeguro;
        else
            return 0.0;
    }

    public Double getPrecioPremium(){
        if (premium)
            return 100.0;
        else
            return 0.0;
    }

    public String[] getExtras(){
        return this.extras;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(direccion);
        parcel.writeString(email);
        parcel.writeString(numeroTlf);
        parcel.writeString(fecha);
        parcel.writeString(ciudadIda);
        parcel.writeString(ciudadVuelta);
        parcel.writeStringArray(extras);
        parcel.writeByte((byte) (diversidadFuncional ? 1 : 0));
        parcel.writeByte((byte) (seguro ? 1 : 0));
        parcel.writeByte((byte) (premium ? 1 : 0));
    }
}
