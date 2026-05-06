public class pestaña {

    String tituloPagina;
    String url;
    String horaApertura;
    pestaña anterior;
    pestaña siguiente;

    public pestaña(String tituloPagina, String url, String horaApertura) {
        this.tituloPagina  = tituloPagina;
        this.url           = url;
        this.horaApertura  = horaApertura;
        this.anterior      = null;
        this.siguiente     = null;
    }

    @Override
    public String toString() {
        return "Titulo: " + tituloPagina + " | URL: " + url + " | Abierta: " + horaApertura;
    }
}