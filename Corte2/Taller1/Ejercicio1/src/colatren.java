public class colatren {
  vagon cabeza;

  public colatren() {
    this.cabeza = null;
  }

  public void agregarVagon(vagon nuevoVagon) {

    if (cabeza == null) {
      cabeza = nuevoVagon;
    } else {
      vagon actual = cabeza;
      while (actual.siguiente != null) {
        actual = actual.siguiente;
      }
      actual.siguiente = nuevoVagon;
    }
  }

  public double calcularPesoTotal() {
    double pesoTotal = 0.0;
    vagon actual = cabeza;

    while (actual != null) {
      pesoTotal += actual.pesoToneladas;
      actual = actual.siguiente;
    }

    return pesoTotal;
  }

}