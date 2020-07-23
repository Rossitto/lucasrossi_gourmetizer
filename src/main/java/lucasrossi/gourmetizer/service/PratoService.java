package lucasrossi.gourmetizer.service;

import java.util.LinkedHashSet;

import lucasrossi.gourmetizer.model.Prato;

public class PratoService {

    private static LinkedHashSet<Prato> pratos = new LinkedHashSet<Prato>();

    static {
        pratos.add(new Prato("Lasanha", "Massa"));
    }

    public LinkedHashSet<Prato> obterPratos() {
        return pratos;
    }

    public static void salvar(Prato prato) {
        pratos.add(prato);
    }

}